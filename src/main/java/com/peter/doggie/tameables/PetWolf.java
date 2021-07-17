package com.peter.doggie.tameables;

import org.bukkit.DyeColor;



public class PetWolf extends Pet
{
    private DyeColor collarColor;


    public PetWolf()
    {
    }



    public DyeColor getCollarColor()                    { return collarColor;     }
    public void     setCollarColor( DyeColor dyeColor ) { collarColor = dyeColor; }
}
