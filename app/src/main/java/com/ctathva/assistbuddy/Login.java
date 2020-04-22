package com.ctathva.assistbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;

import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class Login extends AppCompatActivity {
ImageView logo;
Animation top,left,right,fade,zoom;
EditText edt1,edt2,edt3;
RelativeLayout relativeLayout;
Button verify;
String number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        logo = findViewById(R.id.main_logo);
        edt1 = findViewById(R.id.name);
        edt2 = findViewById(R.id.phno);
        edt3 = findViewById(R.id.email);
        relativeLayout = findViewById(R.id.details);
        verify = findViewById(R.id.verify);
        fade = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        left = AnimationUtils.loadAnimation(this,R.anim.slide_in);
        right = AnimationUtils.loadAnimation(this,R.anim.slide_right);
        top = AnimationUtils.loadAnimation(this,R.anim.top);
        zoom = AnimationUtils.loadAnimation(this,R.anim.zoom_in);
        logo.startAnimation(zoom);
        Handler hd = new Handler();
        hd.postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator tAnimation = ObjectAnimator.ofFloat(logo,"Y",-10f);
                tAnimation.setDuration(1800);
                tAnimation.start();
                relativeLayout.setVisibility(View.VISIBLE);
                verify.setVisibility(View.VISIBLE);
                edt1.startAnimation(left);
                edt2.startAnimation(right);
                edt3.startAnimation(left);
                verify.startAnimation(fade);
            }
        },2100);

        isNetworkConnectionAvailable();
        verification();
        // Configure Google Sign In

    }



   public boolean isNetworkConnectionAvailable(){
       ConnectivityManager cm =
               (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

       NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
       boolean isConnected = activeNetwork != null &&
               activeNetwork.isConnected();
       if(isConnected) {
           Log.d("Network", "Connected");
           return true;
       }
       else{
           Snackbar.make(findViewById(R.id.main_layout),"No Internet.", BaseTransientBottomBar.LENGTH_SHORT).show();
           Log.d("Network","Not Connected");
           return false;
       }
   }
   public void verification()
   {
       verify.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (isNetworkConnectionAvailable()) {
                   if (edt1.getText().toString().isEmpty() && edt2.getText().toString().isEmpty() && edt3.getText().toString().isEmpty()) {
                       Snackbar.make(v, "All Fields must be Compulsory", Snackbar.LENGTH_SHORT).show();
                   }
                   else if (edt1.getText().toString().isEmpty()) {
                           edt1.setError("Name should not be empty");
                           edt1.requestFocus();
                       }
                    else if (edt2.getText().toString().isEmpty()) {
                           edt2.setError("Phone Number should not be empty");
                           edt2.requestFocus();
                   }
                   else if (edt3.getText().toString().isEmpty()) {
                       edt3.setError("Email should not be empty");
                       edt3.requestFocus();
                   }
                   else if (!Patterns.EMAIL_ADDRESS.matcher(edt3.getText().toString()).matches()) {
                       edt3.setError("Invalid Email");
                       edt3.requestFocus();
                   }
                   else if (edt2.getText().toString().length() < 10) {
                       edt2.setError("Invalid Phone Number");
                       edt2.requestFocus();
                   }
                    else{
                        Intent intent = new Intent(Login.this, OTPActivity.class);
                        intent.putExtra("number",edt2.getText().toString());
                        startActivity(intent);
                   }
               }
               else {
                   Snackbar.make(findViewById(R.id.main_layout),"No Internet.", BaseTransientBottomBar.LENGTH_SHORT).show();
               }
           }
       });
   }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}
//client id
/*608987318103-lj9jq9rrbmkqjbc2iqt5p8up5jke5pvb.apps.googleusercontent.com*/
//client secret
/*-ZYEC8-bfh2vk_P76hMAXdS-*/