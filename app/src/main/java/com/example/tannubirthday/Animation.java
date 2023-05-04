package com.example.tannubirthday;

import android.content.Context;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import org.w3c.dom.Text;

//this class is using for all the animations.
public class Animation{

    Context context;
    private MainActivity mainActivity;

    public Animation(Context context){
        this.context = context;
    }
    public void setHeartBeatAnimation(Context context ,ImageView imageview){
        android.view.animation.Animation anim = AnimationUtils.loadAnimation(context,R.anim.heart_pump_animation);
        imageview.startAnimation(anim);
    }

    public void setHeartBeatAnimationLongRange(Context context , ImageView imageview){
        android.view.animation.Animation anim = AnimationUtils.loadAnimation(context, R.anim.from_pump_animation_long_time_with_area);
        imageview.startAnimation(anim);
    }

    public void setUpAndDownAnimation(Context context , ImageView imageview){
        android.view.animation.Animation anim = AnimationUtils.loadAnimation(context , R.anim.float_up_and_down);
        imageview.startAnimation(anim);
    }

    public void setUpAndDownAnimation(Context context , CardView cardview){
        android.view.animation.Animation anim = AnimationUtils.loadAnimation(context , R.anim.float_up_and_down);
        cardview.startAnimation(anim);
    }

    public void setUpAndDownAnimationReverse(Context context , ImageView imageView){
        android.view.animation.Animation anim = AnimationUtils.loadAnimation(context, R.anim.reverse_from_down_to_up);
        imageView.startAnimation(anim);
    }

    public void setUpAndDownAnimationReverse(Context context , CardView cardview){
        android.view.animation.Animation anim = AnimationUtils.loadAnimation(context , R.anim.reverse_from_down_to_up);
        cardview.startAnimation(anim);
    }

    public void setLeftToRightAnimation(Context context , ImageView imageView){
        android.view.animation.Animation anim = AnimationUtils.loadAnimation(context , R.anim.from_left_to_right);
        imageView.startAnimation(anim);
    }

    public void setLyricUpDownAnimation(Context context , TextView textview){
        android.view.animation.Animation anim  = AnimationUtils.loadAnimation(context, R.anim.text_face_up_animation);
        textview.startAnimation(anim);
    }


    public void setFadeAwayAnimation(Context context , TextView textview){
        android.view.animation.Animation anim = AnimationUtils.loadAnimation(context , R.anim.fade_away);
        textview.startAnimation(anim);
    }
}
