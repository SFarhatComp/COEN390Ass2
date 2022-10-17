package com.example.test.Database.Entities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName= "ProfileTable")
public class Profile {


    @PrimaryKey(autoGenerate = true)
    public int ProfileId;
    @ColumnInfo(name="ProfileName")
    public String ProfileName;
    @ColumnInfo(name="ProfileSurname")
    public String ProfileSurname;
    @ColumnInfo(name="ProfileGPA")
    public double ProfileGPA;

    public Profile (int ProfileId, String ProfileSurname,String ProfileName,  double ProfileGPA) {
        this.ProfileId = ProfileId;
        this.ProfileName = ProfileName;
        this.ProfileSurname = ProfileSurname;
        this.ProfileGPA = ProfileGPA;
    }
}
