package com.dotdex.frenzy;

import android.content.Intent;

import com.daimajia.androidanimations.library.Techniques;
import com.dotdex.frenzy.util.Constants;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

public class Splash extends AwesomeSplash {


    @Override
    public void initSplash(ConfigSplash configSplash) {

        //Customize Circular Reveal
        configSplash.setBackgroundColor(R.color.primary); //any color you want form colors.xml
        configSplash.setAnimCircularRevealDuration(2000); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP

        //Choose LOGO OR PATH; if you don't provide String value for path it's logo by default

        //Customize Logo
        configSplash.setLogoSplash(R.mipmap.ic_launcher); //or any other drawable
        configSplash.setAnimLogoSplashDuration(2000); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.Bounce);
         //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)


        //Customize Path
        configSplash.setPathSplash(Constants.DROID_LOGO); //set path String
        configSplash.setOriginalHeight(400); //in relation to your svg (path) resource
        configSplash.setOriginalWidth(400); //in relation to your svg (path) resource
        configSplash.setAnimPathStrokeDrawingDuration(3000);
        configSplash.setPathSplashStrokeSize(5); //I advise value be <5
        configSplash.setPathSplashStrokeColor(R.color.accent); //any color you want form colors.xml
        configSplash.setAnimPathFillingDuration(3000);
        configSplash.setPathSplashFillColor(R.color.Wheat); //path object filling color


        //Customize Title
        configSplash.setTitleSplash("Frenzy Foods");
        configSplash.setTitleTextColor(R.color.Wheat);
        configSplash.setTitleTextSize(30f); //float value
        configSplash.setAnimTitleDuration(5000);
        configSplash.setAnimTitleTechnique(Techniques.SlideInUp);
        configSplash.setTitleFont(Constants.DITI_SWEET_FONT); //provide string to your font located in assets/fonts/
    }

    @Override
    public void animationsFinished() {

        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}
