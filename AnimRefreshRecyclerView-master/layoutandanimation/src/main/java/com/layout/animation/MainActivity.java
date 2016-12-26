package com.layout.animation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
   private RelativeLayout rlAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rlAnimation = (RelativeLayout) findViewById(R.id.rl_animation);
        rlAnimation.startAnimation(getAnimation());

    }
    protected Animation getAnimation() {
        Animation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);// 从0.5倍放大到1倍
        anim.setDuration(1500);
        return anim;
    }


}
