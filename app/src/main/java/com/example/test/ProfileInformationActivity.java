package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.test.Database.DataBaseHelper;
import com.example.test.Database.Entities.Access;
import com.example.test.Database.Entities.Profile;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ProfileInformationActivity extends AppCompatActivity {


    protected int profileId;
    protected TextView Surname_,Name_,Id_,Date_,GPA;
    protected ListView Scroller; // this represent the scroller list of all time stamps
    protected ArrayList<String> ListOfAccessesForGivenId;
    protected String temp;
    protected DataBaseHelper db;
    protected ImageButton BackBB;
    protected Profile profile;
    protected ImageButton DeleteButton_;
    protected ArrayAdapter<String> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_information);

        // Initialising Variables;
        Surname_=findViewById(R.id.Surname);
        Name_=findViewById(R.id.Name);
        Id_=findViewById(R.id.Id);
        Date_=findViewById(R.id.Date_Created);
        GPA=findViewById(R.id.Gpa);
        BackBB=findViewById(R.id.BackButton);
        DeleteButton_=findViewById(R.id.DeleteButton);
        Scroller=findViewById(R.id.ScrollerList2);

        // Getting the values passed from the Intent which will allow to keep the player profile
        Intent intent=getIntent();
        profileId=intent.getIntExtra("profile_id",0);

    if(profileId!=0){

        // If statement that allow us to make sure that the profile passed is not a null referenced object
        db=DataBaseHelper.CreateDatabase(this);
        profile = db.profileDao().GetProfileWithId(profileId);

        // getting the current profile object

        db.accessDAO().InsertAccess(new Access(0,profile.ProfileId,"Opened",new SimpleDateFormat("yyyy.MM.dd @ hh:mm:ss").format(new Timestamp(System.currentTimeMillis()))));
        Surname_.setText("Surname: " + profile.ProfileSurname);
        Name_.setText("Profile Name : " + profile.ProfileName);
        Id_.setText("Profile ID : " + profile.ProfileId);
        GPA.setText("Profile GPA: " + profile.ProfileGPA);
        Date_.setText("Date Created: " + db.accessDAO().GetCreatedDate(profileId));



        // List of Strings of accesses that allow us to create an ArrayList
        List<String> a = db.accessDAO().GetListOfAccesses(profileId);
        List<String> b = db.accessDAO().GetListOfAccessesTypes(profileId);
        ListOfAccessesForGivenId = new ArrayList<String>();

        for (int i=0;i<a.size();i++){

            ListOfAccessesForGivenId.add(a.get(i) + " " +b.get(i));

        }

        // This method was implemented late at night, after review it could have been made easier by simply returnning an object of the access and
        // calling access.type and access.time

         adapter = new ArrayAdapter(ProfileInformationActivity.this, android.R.layout.simple_list_item_1,ListOfAccessesForGivenId);
         Scroller.setAdapter(adapter); // Show my Vertical list using adapters
            // setting up view for the scroller



    }


    BackBB.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            db.accessDAO().InsertAccess(new Access(0,profile.ProfileId,"Closed",new SimpleDateFormat("yyyy.MM.dd @ hh:mm:ss").format(new Timestamp(System.currentTimeMillis()))));
            // set the closed type in the access database when closing the profile activity
            finish();
        }
    });

    DeleteButton_.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            db.profileDao().delete(profile);
            db.accessDAO().InsertAccess(new Access(0,profile.ProfileId,"Closed",new SimpleDateFormat("yyyy.MM.dd @ hh:mm:ss").format(new Timestamp(System.currentTimeMillis()))));
            db.accessDAO().InsertAccess(new Access(0,profile.ProfileId,"Deleted",new SimpleDateFormat("yyyy.MM.dd @ hh:mm:ss").format(new Timestamp(System.currentTimeMillis()))));
            finish();

            // deleting the profile and closing it before deleting it .
        }
    });


    }
}