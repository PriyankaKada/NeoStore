package com.example.webwerks.neostore.Address.RoomDb;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by webwerks on 4/16/18.
 */
@Dao
public interface MyDao {
@Insert
public void addAddress(Address address);

    @Query("select * from address")
    public List<Address> readAddress();

}
