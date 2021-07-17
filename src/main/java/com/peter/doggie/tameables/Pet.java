package com.peter.doggie.tameables;

import org.bukkit.ChatColor;
import org.bukkit.Location;

import java.util.Date;
import java.util.UUID;



public class Pet
{
    private UUID      petId;
    private UUID      petownerId;
    private String    petName;
    private Integer   level;
    private Integer   experience;
    private Date      birthday;
    private Location  location;       // Last known location
    private ChatColor nameColor;



    public String getName() throws IllegalStateException {
        if( petName.isBlank() || petName.isEmpty() ) {
            throw new IllegalStateException("Pet.name isn't set, but I was expecting it to be.");
        }
        return petName;
    }

    public String getBirthday() throws IllegalStateException {
        if( birthday == null ) {
            throw new IllegalStateException("Pet.birthday isn't set, but I was expecting it to be.");
        }
        return petName;
    }





}
