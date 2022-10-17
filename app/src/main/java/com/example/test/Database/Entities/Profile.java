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
    @ColumnInfo(name="Creation_Date")
    public String Date;

    public Profile (int ProfileId, String ProfileSurname,String ProfileName,  double ProfileGPA,String Date) {
        this.ProfileId = ProfileId;
        this.ProfileName = ProfileName;
        this.ProfileSurname = ProfileSurname;
        this.ProfileGPA = ProfileGPA;
        this.Date=Date;
    }
}
