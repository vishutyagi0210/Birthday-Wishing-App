package com.example.tannubirthday;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class DrawerForPoetriesAndMemories extends AppCompatActivity {

    DrawerLayout drawer;
    NavigationView navigate;
    Toolbar toolbar;
    ViewPager viewpager;
    TabLayout tabs;

    LottieAnimationView userImage;

//  this will notify the dialog box
    boolean passwordEnteredInPoetry;
    boolean passwordEnteredInPersonal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_for_poetries_and_memories);
        setIds();

//      setting up drawer on the layout.
        Dialog dialog = new Dialog(DrawerForPoetriesAndMemories.this);
        toolbar.setTitle("Happy Birthday");
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



        userImage.setOnClickListener(new UserAnimation());

//      setting ids for dialog box
        dialog.setContentView(R.layout.enter_password_dialog_box);
        Button btn = dialog.findViewById(R.id.buttonPassword_);
        TextView text = dialog.findViewById(R.id.textview_);
        EditText password1 = dialog.findViewById(R.id.numericPassword);
        EditText password2 = dialog.findViewById(R.id.textPassword);
        LottieAnimationView passwordAnimation = dialog.findViewById(R.id.PasswordAnimation);



//      please take time to read it carefully.. because it is the longest function.
//      setting the entire navigation layouts and fragments and its animations here.
        navigate.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//              setting up the dialog box
                int ids = item.getItemId();
                dialog.setCancelable(false);
                passwordAnimation.setAnimation(R.raw.emptyanimation);
                passwordAnimation.playAnimation();

//              dedicated to poetry layout frames, animations.
                if(ids == R.id.poetriesOpt){
                    toolbar.setTitle("Poetries");
//                  showing dialogbox for checking if the birthday/boy/girl is opening the app.

                    if(passwordEnteredInPoetry == false){
                        dialog.show();
                        text.setText(R.string.PoetrySectionPermissionText);

//                      checking if in the dialog box the password is correct or not and calling the animations.
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(password1.getText().toString().equals("123") && password2.getText().toString().equals("vishal")){
//                              showing you are correct animation
                                    passwordAnimation.setAnimation(R.raw.success_tick);
                                    passwordAnimation.playAnimation();
                                    passwordEnteredInPoetry = true;
//                              first showing the animation then removing dialog box
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            dialog.dismiss();
                                            vibrate();
                                            passwordAnimation.clearAnimation();
                                            passwordAnimation.cancelAnimation();
                                        }
                                    },2000);
                                }
                                else{
                                    vibrate();
                                    Toast.makeText(DrawerForPoetriesAndMemories.this, "You are not authorized", Toast.LENGTH_SHORT).show();
                                }
                                password1.setText("");
                                password2.setText("");
                            }
                        });
                    }
                    else{
//                      noting to do here.
                    }
                    ViewPagerPoetryAdapter adapter = new ViewPagerPoetryAdapter(getSupportFragmentManager());
                    viewpager.setAdapter(adapter);
                    tabs.setupWithViewPager(viewpager);

                }

//              this entire code is for the second option that is perosnal message section.
                else if(ids == R.id.mymessageopt){
                    toolbar.setTitle("My Message");
                    if(passwordEnteredInPersonal == false){
                        dialog.show();
                        text.setText(R.string.PersonalMessageSectionPermission);
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(password1.getText().toString().equals("0210") && password2.getText().toString().equals("tyagi")){
//                              showing you are correct animation
                                    passwordAnimation.setAnimation(R.raw.success_tick);
                                    passwordAnimation.playAnimation();
                                    passwordEnteredInPersonal = true;
//                              first showing the animation then removing dialog box
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            dialog.dismiss();
                                            vibrate();
                                            passwordAnimation.clearAnimation();
                                            passwordAnimation.cancelAnimation();
                                        }
                                    },500);
                                }
                                else{
                                    vibrate();
                                    Toast.makeText(DrawerForPoetriesAndMemories.this, "You are not authorized", Toast.LENGTH_SHORT).show();
                                }
                                password1.setText("");
                                password2.setText("");
                                passwordAnimation.cancelAnimation();
                            }
                        });
                    }
                    ViewPagerMessageAdapter adapter = new ViewPagerMessageAdapter(getSupportFragmentManager());
                    viewpager.setAdapter(adapter);
                    tabs.setupWithViewPager(viewpager);
                }

                else{
                    Intent iDial = new Intent(Intent.ACTION_DIAL);
                    iDial.setData(Uri.parse("tel: +919210763630"));
                    startActivity(iDial);
                }

//              independent code.
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

//  if drawer is open so, this function first close the drawer on you first back press.
    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }


//   setting all the instance reference id's
    public void setIds(){
        drawer = findViewById(R.id.drawarLayout);
        navigate = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);
        View navigationViewItems = navigate.getHeaderView(0);
        userImage = navigationViewItems.findViewById(R.id.userAnimationLottie);
        viewpager = findViewById(R.id.viewPager);
        tabs = findViewById(R.id.tabs);
    }
    public void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
        }
    }

//   this class is specificely for user Icon animation
    private class UserAnimation implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            userImage.playAnimation();

            vibrate();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    userImage.setProgress(0);
                    userImage.cancelAnimation();
                }
            }, 600);
        }
    }
}