package com.example.fightclubapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fightclubapp.model.DataBaseHandler;
import com.example.fightclubapp.model.MartialArt;

public class AddMartialArtActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtName, edtPrice, edtColor;
    Button btnAddMartialArt, btnBack;

    DataBaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_martial_art);
        setTitle("Add Martial Art");

        edtName =  findViewById(R.id.edtName);
        edtPrice =  findViewById(R.id.edtPrice);
        edtColor =  findViewById(R.id.edtColor);

        btnAddMartialArt = (Button) findViewById(R.id.btnAddMartialArt);
        btnBack =  findViewById(R.id.btnGoBack);


        databaseHandler = new DataBaseHandler(AddMartialArtActivity.this);



        btnAddMartialArt.setOnClickListener(this);
        btnBack.setOnClickListener(this);


    }



    private void addMartialArtObjectToDatabase()
    {
        String nameValue = edtName.getText().toString();
        String priceValue = edtPrice.getText().toString();
        String colorValue = edtColor.getText().toString();


        try {


            double priceDoubleValue = Double.parseDouble(priceValue);
            MartialArt martialArtObject = new MartialArt(0,nameValue,priceDoubleValue,colorValue);
            databaseHandler.addMartialArt(martialArtObject);
            Toast.makeText(AddMartialArtActivity.this,
                    martialArtObject + " Martial Art Object is added to Database",
                    Toast.LENGTH_SHORT).show();

        }
          catch (Exception e)
        {
            e.printStackTrace();
        }



    }






    @Override
    public void onClick(View v) {

        switch (v.getId()) {


            case R.id.btnAddMartialArt:
                addMartialArtObjectToDatabase();
                break;

            case R.id.btnGoBack:
                finish();
                break;

        }

    }
}