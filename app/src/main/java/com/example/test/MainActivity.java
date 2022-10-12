package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.Database.DataBaseHelper;
import com.example.test.Database.Entities.Profile;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    protected FloatingActionButton FragmentOption;
    protected ImageButton DisplayButton_;
    protected TextView DisplayViewer;
    protected ScrollView Scroller;

    List<Profile> ListofProfiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayViewer = findViewById(R.id.InformationDisplay);
        FragmentOption = findViewById(R.id.FloatingFragment);
        DisplayButton_ = findViewById(R.id.DisplayButton);
        Scroller = findViewById(R.id.ScrollView);
        DataBaseHelper db = DataBaseHelper.CreateDatabase(getApplicationContext());
        for (int i=0;i<10;i++) {

            db.profileDao().InsertProfile(new Profile(0, "Amine" + i, "Bouras" + i, 3.0));
        };

        ListofProfiles = db.profileDao().GetAllElements();

        DisplayButton_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast=Toast. makeText(getApplicationContext(),"Hello Javatpoint",Toast. LENGTH_SHORT);
                toast.show();

                for (int i=0;i<10;i++){
                db.profileDao().delete(ListofProfiles.get(i));


                }
            }
        });


        FragmentOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast=Toast. makeText(getApplicationContext(),"Hello Javatpoint, the other button is working",Toast. LENGTH_SHORT);
                toast.show();
            }
        });

    }
}