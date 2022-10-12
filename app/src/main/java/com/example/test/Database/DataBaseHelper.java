package com.example.test.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.test.Database.DAO.AccessDAO;
import com.example.test.Database.DAO.ProfileDao;
import com.example.test.Database.Entities.Access;
import com.example.test.Database.Entities.Profile;


@Database(entities={Profile.class, Access.class},version = 1)

public abstract class DataBaseHelper extends RoomDatabase {

    private static volatile DataBaseHelper DataBaseInstance;
    private static final String DatabaseName="ProjectDatabase";
    // the constructor is protected to stop people from using it
    protected DataBaseHelper(){};


    // Database creator
    private static DataBaseHelper Create(Context context){
        return Room.databaseBuilder(context,DataBaseHelper.class,DatabaseName).allowMainThreadQueries().build();

    }

    public static synchronized DataBaseHelper CreateDatabase(Context context){

        if (DataBaseInstance==null){

            DataBaseInstance=Create(context);
        }

        return DataBaseInstance;



    }

    public abstract ProfileDao profileDao();
    public abstract AccessDAO accessDAO();


}
