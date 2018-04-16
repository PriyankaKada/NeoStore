package com.example.webwerks.neostore.Address.RoomDb;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by webwerks on 4/16/18.
 */
@Entity(tableName = "address")

public class Address {
    @PrimaryKey(autoGenerate = true)
    private int address_id;
    @ColumnInfo(name = "user_address")
    private String address;
    private String city;
    private String Street_name;
    private String state;
    private int zip_code;
    private String country;

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet_name() {
        return Street_name;
    }

    public void setStreet_name(String street_name) {
        Street_name = street_name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip_code() {
        return zip_code;
    }

    public void setZip_code(int zip_code) {
        this.zip_code = zip_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
