package com.example.webwerks.neostore.address.roomDb;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by webwerks on 4/16/18.
 */
@Database(entities = {Address.class}, version = 1)
public abstract class MyAddressDatabase extends RoomDatabase {
    public abstract MyDao myDao();


}
