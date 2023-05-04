package com.example.tannubirthday;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    private int currentPosition = 0;
    private TextView lyric , textOnCardView;
    private String lyrics[] = {
            "Hey send this to a girl who is really hardworking,",
            "and tell her to keep going because",
            "ki tu rukti nahi hai 🙅‍♀,",
            "ki tu tut'ti💔 nahi h,",
            "paise💸 aage jhuki hogi apsara🧚‍",
            "Raani👑 jhukti nahi h,",
            "tujhe kabhi maine bheed mai na dheka 👀",
            "tu rheti kinaro pe🏖️,",
            "tu chamkali chizon pe ky maragi",
            "tu khud hai sitaro mein🌟"
    };
    private ImageView heartIcon, fadeCircle, largeCircle, smallAndmMediumCircle;
    private CardView imageCardview;
    private MediaPlayer mediaPlayer;
    private LottieAnimationView cardViewLottieAnimation, withTextAnimation , nextPageAnim;
    private Button moveForward;
    Animation anim = new Animation(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getSupportActionBar().hide();
        setIds();
        heartIcon.setOnClickListener(new MainActivityAllContent());  //I've defined all the things into this class for this activity.
        moveForward.setOnClickListener(new NextActivity()); // going to next activity
    }

    public void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
        }
    }

    public void setIds() {
        heartIcon = findViewById(R.id.activityMainHeartIconImage);
        fadeCircle = findViewById(R.id.fadecircle);
        largeCircle = findViewById(R.id.largeCircle);
        smallAndmMediumCircle = findViewById(R.id.smallAndMediumCircle);
        imageCardview = findViewById(R.id.imageCardView);
        lyric = findViewById(R.id.textViewForLyrics);
        cardViewLottieAnimation = findViewById(R.id.lottie_animation_for_card_view);
        withTextAnimation = findViewById(R.id.lottieAnimationWithText);
        moveForward = findViewById(R.id.forwardButton);
        nextPageAnim = findViewById(R.id.nextPageLoadingAnimation);
        textOnCardView = findViewById(R.id.cardViewText);
    }

    private class MainActivityAllContent implements View.OnClickListener {

        @Override
        public void onClick(View view) {
                vibrate();
                startAllAnimations();
                startSong();
            }
        public void cancelAllAnimations() {
            heartIcon.clearAnimation();
            fadeCircle.clearAnimation();
            largeCircle.clearAnimation();
            smallAndmMediumCircle.clearAnimation();
            heartIcon.setPressed(false);
            imageCardview.clearAnimation();
            cardViewLottieAnimation.cancelAnimation();
            withTextAnimation.cancelAnimation();
            lyric.clearAnimation();
            withTextAnimation.setAnimation(R.raw.song_waves);
            heartIcon.setImageResource(R.drawable.heart_icon_default);
            textOnCardView.setText("Press the heart");
            textOnCardView.clearAnimation();
        }

        public void startAllAnimations(){
            anim.setHeartBeatAnimation(MainActivity.this, heartIcon);
            anim.setHeartBeatAnimationLongRange(MainActivity.this, fadeCircle);
            anim.setUpAndDownAnimation(MainActivity.this, largeCircle);
            anim.setUpAndDownAnimationReverse(MainActivity.this, smallAndmMediumCircle);
            anim.setUpAndDownAnimationReverse(MainActivity.this, imageCardview);
            anim.setLyricUpDownAnimation(MainActivity.this , lyric);
            heartIcon.setImageResource(R.drawable.hearticon);
            cardViewLottieAnimation.setAnimation(R.raw.song_waves);
            cardViewLottieAnimation.playAnimation();
            withTextAnimation.setAnimation(R.raw.circles_lottie_animation);
            withTextAnimation.playAnimation();
            textOnCardView.setText(R.string.wishes);
            anim.setFadeAwayAnimation(MainActivity.this , textOnCardView);
        }


        public void startSong() {
            if (mediaPlayer == null) {
                // Create a new media player and set its onCompletionListener as before
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.songaugio);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        cancelAllAnimations();
                    }
                });
            }
            // Start the media player
            mediaPlayer.start();

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    setLyricsIntoTextView(handler);
                }
            }, 500);

        }

        public void setLyricsIntoTextView(Handler handler) {
//        calling function recuersively in every 500 miliseconds for displaying the text.
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                currentPosition = mediaPlayer.getCurrentPosition();
                int index = (int) (currentPosition / (mediaPlayer.getDuration() / lyrics.length));
                lyric.setText(lyrics[index]);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setLyricsIntoTextView(handler);
                    }
                }, 1000);
            } else {
                lyric.setText("\uD835\uDC3B\uD835\uDCB6\uD835\uDCC5\uD835\uDCC5\uD835\uDCCE \uD835\uDC35\uD835\uDCBE\uD835\uDCC7\uD835\uDCC9\uD835\uDCBD\uD835\uDCB9\uD835\uDCB6\uD835\uDCCE");
            }
        }
    }

    private class NextActivity implements View.OnClickListener{

        public void onClick(View view) {
            nextPageAnim.setAnimation(R.raw.loading_animation);
            nextPageAnim.playAnimation();
            vibrate();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent inten = new Intent(MainActivity.this , DrawerForPoetriesAndMemories.class);
                    startActivity(inten);
                }
            },2000);
        }
    }
}