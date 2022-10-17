package com.example.test.Database.DAO;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.test.Database.Entities.Profile;

import java.util.List;

@Dao
public interface ProfileDao {


    //Getter for all elements
    @Query("SELECT * FROM profiletable")
    List<Profile> GetAllElements();

    // get function for a specific element
    @Query("SELECT * FROM  profiletable WHERE ProfileId=:ProfileID")
    Profile GetProfileWithId(int ProfileID);

    @Query("SELECT * FROM profiletable ORDER BY ProfileSurname  ASC ")
    List<Profile> GetAllElementsAlpha();

    // Set Function for a profile object
    @Insert
    void InsertProfile(Profile... profiles);


    // Delete an object from the database
    @Delete
    void delete(Profile ProfileObject);



}
