package com.example.test.Database.Entities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName= "AccessTable")
public class Access {

    // Primary Key implementation
    @PrimaryKey(autoGenerate = true)
    public int AccessId;
    // Different Columns
    @ColumnInfo(name = "ProfileId")
    public int ProfileIdNumber;
    @ColumnInfo(name = "AccessType")
    public String AccessType;

    public Access(int AccessId, int ProfileIdNumber, String AccessType) {
        this.AccessId = AccessId;
        this.ProfileIdNumber = ProfileIdNumber;
        this.AccessType = AccessType;
    }
}