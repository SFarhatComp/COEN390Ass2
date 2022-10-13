package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.test.Database.DataBaseHelper;
import com.example.test.Database.Entities.Profile;

public class ProfileInformationActivity extends AppCompatActivity {


    protected int profileId;
    protected TextView Surname_,Name_,Id_,Date_,GPA;
    protected ListView Scroller;
    protected DataBaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_information);
        Surname_=findViewById(R.id.Surname);
        Name_=findViewById(R.id.Name);
        Id_=findViewById(R.id.Id);
        Date_=findViewById(R.id.Date_Created);
        GPA=findViewById(R.id.Gpa);
        Intent intent=getIntent();
        profileId=intent.getIntExtra("profile_id",0);

    if(profileId!=0){
        db=DataBaseHelper.CreateDatabase(this);
        Profile profile = db.profileDao().GetProfileWithId(profileId);

       Surname_.setText("Surname: " + profile.ProfileSurname);
        Name_.setText("Profile Name : " + profile.ProfileName);
        Id_.setText("Profile ID : " + profile.ProfileId);
        GPA.setText("Profile GPA: " + profile.ProfileGPA);
    }

    }
}