package com.shimizubrix.shimizu.projectx;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

/**
 * Created by shimizu on 4/18/16.
 */
public class IntroActivity extends AppIntro{
    @Override
    public void init(Bundle savedInstanceState) {
        addSlide(AppIntroFragment.newInstance("ProjectX, insert a catchy phrase here.", "This app is blah blah blah...", R.drawable.logo, Color.parseColor("#FFC107")));
        addSlide(AppIntroFragment.newInstance("ProjectX, insert a catchy phrase here.", "This app is blah blah blah...", R.drawable.logo, Color.parseColor("#FF9800")));
        addSlide(AppIntroFragment.newInstance("ProjectX, insert a catchy phrase here.", "This app is blah blah blah...", R.drawable.logo, Color.parseColor("#F57C00")));
    }

    @Override
    public void onSkipPressed() {
    }

    @Override
    public void onNextPressed() {

    }

    @Override
    public void onDonePressed() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void onSlideChanged() {

    }
}
