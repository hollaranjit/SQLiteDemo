package com.example.fightclubapp;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import androidx.appcompat.widget.AppCompatButton;

import com.example.fightclubapp.model.MartialArt;

public class MartialArtButton extends AppCompatButton {

    private MartialArt martialArtObject;


    public MartialArtButton(Context context, MartialArt martialArt) {
        super(context);
        martialArtObject = martialArt;

    }

    public  String getMartialArtColor()
    {
        return martialArtObject.getMartialArtColor();
    }


    public double getMartialArtPrice()
    {
        return martialArtObject.getMartialArtPrice();
    }

}
