package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.Database.DataBaseHelper;
import com.example.test.Database.Entities.Profile;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    protected FloatingActionButton FragmentOption;
    protected ImageButton DisplayButton_;
    protected TextView DisplayViewer;
    protected RecyclerView ListViewer_;
    protected CustomRecyclerViewAdapter customRecyclerViewAdapter;
    protected DataBaseHelper db;
    protected String TypeOfView = "Surname";
    List<Profile> ListofProfiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayViewer = findViewById(R.id.InformationDisplay);
        FragmentOption = findViewById(R.id.FloatingFragment);
        DisplayButton_ = findViewById(R.id.DisplayButton);
        ListViewer_ = findViewById(R.id.ScrollerList);
        db = DataBaseHelper.CreateDatabase(getApplicationContext());



        DisplayButton_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast=Toast. makeText(getApplicationContext(),"Hello Javatpoint",Toast. LENGTH_SHORT);
                toast.show();

            }
        });


        FragmentOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentProfile object= new FragmentProfile();
                object.show(getSupportFragmentManager(),"ProfileFragmentDialog");
            }
        });




    }

    @Override
    protected void onResume() {
        super.onResume();
        SetupView();
    }

    protected void SetupView(){

        ListofProfiles = db.profileDao().GetAllElementsAlpha();
        DisplayViewer.setText(ListofProfiles.size() +" Profiles, by " + TypeOfView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        customRecyclerViewAdapter = new CustomRecyclerViewAdapter(ListofProfiles);
        ListViewer_.setLayoutManager(linearLayoutManager);
        ListViewer_.setAdapter(customRecyclerViewAdapter);

    };

    protected void ResetCount(){DisplayViewer.setText(ListofProfiles.size()+" Profiles, by" + " Surname");};
}