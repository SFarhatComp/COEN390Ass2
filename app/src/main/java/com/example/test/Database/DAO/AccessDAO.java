package com.example.test.Database.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.test.Database.Entities.Access;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface AccessDAO {



    //Getter for all elements
    @Query("SELECT * FROM AccessTable")
    List<Access> GetAllElements();

    // get function for a specific element
    @Query("SELECT * FROM  AccessTable WHERE ProfileId=:ProfileIdNumber")
    Access GetAccessWithId(int ProfileIdNumber);

    @Query("SELECT TimeStamp FROM  AccessTable WHERE ProfileId=:ProfileIdNumber LIMIT 2 ")
    String GetCreatedDate(int ProfileIdNumber);

    @Query("SELECT TimeStamp FROM  AccessTable WHERE ProfileId=:ProfileIdNumber ORDER BY AccessId DESC ")
    List<String> GetListOfAccesses(int ProfileIdNumber);

    @Query("SELECT AccessType FROM  AccessTable WHERE ProfileId=:ProfileIdNumber ORDER BY AccessId DESC ")
    List<String> GetListOfAccessesTypes(int ProfileIdNumber);


    // Set Function for a profile object
    @Insert
    void InsertAccess(Access... Accesses);






}
