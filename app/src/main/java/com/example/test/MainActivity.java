package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
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
    List<Profile> ListofProfiles2;

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


                PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
                popupMenu.getMenuInflater().inflate(R.menu.dotted_menu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        switch (menuItem.getItemId()){

                            case R.id.Name_Mode:


                                Toast toast=Toast. makeText(getApplicationContext(),"The Display has been changed to Name Mode",Toast. LENGTH_SHORT);
                                toast.show();

                                TypeOfView="Surname";
                                DisplayViewer.setText(ListofProfiles.size() +" Profiles, by " + TypeOfView);
                                SetupView(0);
                                return true;

                            case R.id.Id_Mode:


                                Toast toast2=Toast. makeText(getApplicationContext(),"The Display has been changed to ID Mode ",Toast. LENGTH_SHORT);
                                toast2.show();

                                SetupView(1);
                                TypeOfView="Id";
                                DisplayViewer.setText(ListofProfiles.size() +" Profiles, by " + TypeOfView);

                                return true;




                            default :
                                return false;


                        }



                    }
                });

                popupMenu.show();






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
        SetupView(0);
    }

    protected void SetupView(int a){

        ListofProfiles = db.profileDao().GetAllElementsAlpha();
        ListofProfiles2= db.profileDao().GetALlElementsWithIDSorted();
        DisplayViewer.setText(ListofProfiles.size() +" Profiles, by " + TypeOfView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        if (a==0){
        customRecyclerViewAdapter = new CustomRecyclerViewAdapter(ListofProfiles,a);}
        else{
            customRecyclerViewAdapter = new CustomRecyclerViewAdapter(ListofProfiles2,a);
        }
        ListViewer_.setLayoutManager(linearLayoutManager);
        ListViewer_.setAdapter(customRecyclerViewAdapter);

    };




    protected void ResetCount(){DisplayViewer.setText(ListofProfiles.size()+" Profiles, by" + " Surname");};
}