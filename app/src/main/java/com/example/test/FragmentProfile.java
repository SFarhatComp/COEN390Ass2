package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.test.Database.DataBaseHelper;
import com.example.test.Database.Entities.Access;
import com.example.test.Database.Entities.Profile;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class FragmentProfile extends DialogFragment {


    protected EditText ProfileSurname_,ProfileName_,ProfileId_,Profile_GPA;
    protected Button SaveButton_,CancelButton_;
    protected int Ids;
    protected double GPAs;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmentforprofile,container);

        ProfileSurname_=view.findViewById(R.id.SurnameInput);
        ProfileName_=view.findViewById(R.id.NameInput);
        ProfileId_=view.findViewById(R.id.IdInput);
        Profile_GPA=view.findViewById(R.id.GpaInput);
        SaveButton_=view.findViewById(R.id.SaveButton);
        CancelButton_=view.findViewById(R.id.CancelButton);

        CancelButton_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        SaveButton_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Surnames_= ProfileSurname_.getText().toString();
                String Names = ProfileName_.getText().toString();
                String ProfileIds = ProfileId_.getText().toString();
                String ProfileGPAs = Profile_GPA.getText().toString();

                if(!ProfileIds.isEmpty() && !ProfileGPAs.isEmpty() && !Surnames_.isEmpty() && !Names.isEmpty()){

                    Ids= Integer.parseInt(ProfileIds);
                    GPAs=Double.valueOf(ProfileGPAs);


                    DataBaseHelper db= DataBaseHelper.CreateDatabase(getContext());
                    db.profileDao().InsertProfile(new Profile(Ids, Surnames_,Names,GPAs));
                    db.accessDAO().InsertAccess(new Access(0,Ids,"Created",new SimpleDateFormat("yyyy.MM.dd @ hh:mm:ss").format(new Timestamp(System.currentTimeMillis()))));
                    Toast.makeText(view.getContext(), "The profile has been inserted",Toast.LENGTH_LONG).show();



                    ((MainActivity)getActivity()).SetupView();
                    ((MainActivity)getActivity()).ResetCount();
                    dismiss();
                }

                else {

                    Toast.makeText(view.getContext(), "Some Input are invalid, Please Verify",Toast.LENGTH_LONG).show();
                    return;

                }



            }
        });
        return view;
    }
}
