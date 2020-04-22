package com.ctathva.assistbuddy;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ramotion.paperonboarding.PaperOnboardingFragment;
import com.ramotion.paperonboarding.PaperOnboardingPage;
import com.ramotion.paperonboarding.listeners.PaperOnboardingOnRightOutListener;

import java.util.ArrayList;


public class
Intro extends AppCompatActivity {
    FragmentManager fragmentManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_intro);
        fragmentManager = getSupportFragmentManager();

        final PaperOnboardingFragment onBoardingFragment = PaperOnboardingFragment.newInstance(getDataForOnboarding());

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, onBoardingFragment);
        fragmentTransaction.commit();
        onBoardingFragment.setOnRightOutListener(new PaperOnboardingOnRightOutListener() {
            @Override
            public void onRightOut() {
               startActivity(new Intent(Intro.this,Login.class));
               overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
            }
        });
    }

    private ArrayList<PaperOnboardingPage> getDataForOnboarding() {
        // prepare data
        PaperOnboardingPage scr1 = new PaperOnboardingPage("Live Illusion", "All hotels and hostels are sorted by hospitality rating",
                Color.parseColor("#DCDCDC"), R.drawable.ic_webinar, R.drawable.ic_dot);
        PaperOnboardingPage scr2 = new PaperOnboardingPage("Questions", "We carefully verify all banks before add them into the app",
                Color.parseColor("#ffdb58"), R.drawable.ic_question, R.drawable.ic_dot);
        PaperOnboardingPage scr3 = new PaperOnboardingPage("Enhance your Skill", "All local stores are categorized for your convenience",
                Color.parseColor("#87CEEB"), R.drawable.ic_skills, R.drawable.ic_dot);

        ArrayList<PaperOnboardingPage> elements = new ArrayList<>();
        elements.add(scr1);
        elements.add(scr2);
        elements.add(scr3);
        return elements;
    }
}
