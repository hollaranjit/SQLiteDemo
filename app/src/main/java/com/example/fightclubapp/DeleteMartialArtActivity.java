package com.example.fightclubapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.fightclubapp.model.DataBaseHandler;
import com.example.fightclubapp.model.MartialArt;

import java.util.ArrayList;

public class DeleteMartialArtActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {


    private DataBaseHandler databaseHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_martial_art);


        databaseHandler = new DataBaseHandler(DeleteMartialArtActivity.this);

        updateTheUserInterface();
    }

    private void updateTheUserInterface() {

        ArrayList<MartialArt> allMartialArtObjects =
                databaseHandler.returnAllMartialArtObject();

        RelativeLayout relativeLayout = new RelativeLayout(DeleteMartialArtActivity.this);
        ScrollView scrollView = new ScrollView(DeleteMartialArtActivity.this);
        RadioGroup radioGroup = new RadioGroup(DeleteMartialArtActivity.this);


        for (MartialArt martialArt : allMartialArtObjects) {


            RadioButton currentRadioButton = new RadioButton(DeleteMartialArtActivity.this);
            currentRadioButton.setId(martialArt.getMartialID());
            currentRadioButton.setText(martialArt.toString());
            radioGroup.addView(currentRadioButton);


        }

        radioGroup.setOnCheckedChangeListener(DeleteMartialArtActivity.this);

        Button btnBack = new Button(DeleteMartialArtActivity.this);
        btnBack.setText("Go Back");
        btnBack.setOnClickListener(DeleteMartialArtActivity.this);
        btnBack.setOnClickListener(DeleteMartialArtActivity.this);

        scrollView.addView(radioGroup);

        RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);

        buttonParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        buttonParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        buttonParams.setMargins(0, 0, 0, 70);



        relativeLayout.addView(btnBack, buttonParams);


        ScrollView.LayoutParams scrollViewParams = new ScrollView.LayoutParams(
                ScrollView.LayoutParams.MATCH_PARENT,
                ScrollView.LayoutParams.MATCH_PARENT
        );

        relativeLayout.addView(scrollView, scrollViewParams);

        setContentView(relativeLayout);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        databaseHandler.deleteMartialArtObjectFromDatabaseByID(checkedId);
        Toast.makeText(DeleteMartialArtActivity.this, "the martial art object is deleted",
                Toast.LENGTH_SHORT).show();
        updateTheUserInterface();
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}