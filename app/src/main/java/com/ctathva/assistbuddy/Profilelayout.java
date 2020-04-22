package com.ctathva.assistbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profilelayout extends AppCompatActivity {

    TextView uname,uemail,uid;
    CircleImageView circleImageView;
    GoogleSignInClient googleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);
        uname  =findViewById(R.id.person_name);
        uemail  =findViewById(R.id.person_email);
        uid  =findViewById(R.id.person_id);
        circleImageView  =findViewById(R.id.person_image);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(this,gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account!=null)
        {
             String name = account.getGivenName();
             String id = account.getId();
             String email = account.getEmail();
             Uri pic = account.getPhotoUrl();
             uname.setText("Name is: "+name);
            uemail.setText("Email is: "+email);
            uid.setText("Id is: "+id);
            Glide.with(this).load(pic).into(circleImageView);

        }
    }
}
