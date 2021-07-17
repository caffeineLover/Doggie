package com.peter.doggie.handlers;

import com.peter.doggie.tameables.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;



public class PetHandler
{
    private FileConfiguration petCensus = null;


    public Pet createPet( LivingEntity le )
    {
        switch( le.getType() )
        {
            case CAT:    return new PetCat();
            case DONKEY: return new PetDonkey();
            case HORSE:  return new PetHorse();
            case LLAMA:  return new PetLlama();
            case MULE:   return new PetMule();
            case PARROT: return new PetParrot();
            case WOLF:   return new PetWolf();
            default: return null;
        }
    }





}
