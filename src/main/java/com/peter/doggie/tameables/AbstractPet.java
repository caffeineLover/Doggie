package com.peter.doggie.tameables;


import com.peter.doggie.exception.MissingDataException;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;

import java.util.Date;
import java.util.UUID;

public class AbstractPet
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
            throw new MissingDataException("AbstractPet.name isn't set, but I was expecting it to be.");
        }
        return petName;
    }

    public String getBirthday() throws MissingDataException {
        if( birthday == null ) {
            throw new MissingDataException("AbstractPet.birthday isn't set, but I was expecting it to be.");
        }
        return petName;
    }





}
