package com.example.test.Database.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.test.Database.Entities.Access;

import java.util.List;

@Dao
public interface AccessDAO {



    //Getter for all elements
    @Query("SELECT * FROM AccessTable")
    List<Access> GetAllElements();

    // get function for a specific element
    @Query("SELECT * FROM  AccessTable WHERE AccessId=:AccessId")
    Access GetAccessWithId(int AccessId);

    // Set Function for a profile object
    @Insert
    void InsertAccess(Access... Accesses);






}
