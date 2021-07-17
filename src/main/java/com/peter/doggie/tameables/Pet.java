package com.peter.doggie.tameables;

import com.peter.doggie.exception.MissingDataException;
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



    public String getName() throws MissingDataException {
        if( petName.isBlank() || petName.isEmpty() || petName == null ) {
            throw new MissingDataException("Pet.name isn't set, but I was expecting it to be.");
        }
        return petName;
    }

    public String getBirthday() throws MissingDataException {
        if( birthday == null ) {
            throw new MissingDataException("Pet.birthday isn't set, but I was expecting it to be.");
        }
        return petName;
    }





}
