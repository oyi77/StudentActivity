package id.sch.smktelkom_mlg.project.xiirpl105152535.studentassistant;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;


public class Intro extends AppIntro {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Add your slide's fragments here
        // AppIntro will automatically generate the dots indicator and buttons.
//        addSlide(first_fragment);
//        addSlide(second_fragment);
//        addSlide(third_fragment);
//        addSlide(fourth_fragment);

        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest
//        addSlide(AppIntroFragment.newInstance(title, description, image, background_colour));
        addSlide(AppIntroFragment.newInstance("Welcome in Student Assistant!", "", R.drawable.ts, Color.parseColor("#90CAF9")));
        addSlide(AppIntroFragment.newInstance("What's is the feature?", "", R.drawable.help, Color.parseColor("#90CAF9")));
        addSlide(AppIntroFragment.newInstance("You can add task", "", R.drawable.task, Color.parseColor("#90CAF9")));
        addSlide(AppIntroFragment.newInstance("You can add schedule", "", R.drawable.task, Color.parseColor("#90CAF9")));
        addSlide(AppIntroFragment.newInstance("Receive reminder for Assignment", "", R.drawable.task, Color.parseColor("#90CAF9")));

        // OPTIONAL METHODS

        // Override bar/separator color
        setBarColor(Color.parseColor("#1565C0"));
        setSeparatorColor(Color.parseColor("#2196F3"));

        // SHOW or HIDE the statusbar
        showStatusBar(true);

        // Edit the color of the nav bar on Lollipop+ devices
//        setNavBarColor(Color.parseColor("#3F51B5"));

        // Hide Skip/Done button
        showSkipButton(false);
        showDoneButton(true);

        // Turn vibration on and set intensity
        // NOTE: you will probably need to ask VIBRATE permisssion in Manifest
        setVibrate(true);
        setVibrateIntensity(30);

        // Animations -- use only one of the below. Using both could cause errors.
        // setFadeAnimation(); // OR
//        setCustomTransformer(transformer);
//        setZoomAnimation(); // OR
//        setFlowAnimation(); // OR
//        setSlideOverAnimation(); // OR
        setDepthAnimation(); // OR
//        setCustomTransformer(yourCustomTransformer);

        // Permissions -- takes a permission and slide number
//        askForPermissions(new String[]{Manifest.permission.CAMERA}, 3);
    }

    @Override
    public void onSkipPressed() {
        // Do something when users tap on Skip button.
        System.out.print("slide :");
        System.out.println(getSlides().toString());

    }

    @Override
    public void onNextPressed() {
        // Do something when users tap on Next button.

    }

    @Override
    public void onDonePressed() {
        // Do something when users tap on Done button.
        finish();
        startActivity(new Intent(Intro.this, LoginActivity.class));
    }

    @Override
    public void onSlideChanged() {
        // Do something when slide is changed
    }
}