package com.example.fightclubapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;

import com.example.fightclubapp.model.DataBaseHandler;
import com.example.fightclubapp.model.MartialArt;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DataBaseHandler databaseHandler;
    private double totalMartialArtsPrice;
    private ScrollView scrollView;
    private int martialArtButtonWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        databaseHandler = new DataBaseHandler(MainActivity.this);
        totalMartialArtsPrice = 0.0;
        scrollView = findViewById(R.id.scroll_view);

        Point  screenSize =  new Point();
        getWindowManager().getDefaultDisplay().getSize(screenSize);

        martialArtButtonWidth = screenSize.x / 2;

        modifyUserInterface();

    }

    private void modifyUserInterface() {
        ArrayList<MartialArt> allMartialArtObjects =
                databaseHandler.returnAllMartialArtObject();

        scrollView.removeAllViewsInLayout();

        if(allMartialArtObjects.size() > 0)
        {
            GridLayout gridLayout = new GridLayout(MainActivity.this);
            gridLayout.setRowCount((allMartialArtObjects.size()+1)/2);
            gridLayout.setColumnCount(2);

           MartialArtButton[] martialArtButtons = new MartialArtButton[allMartialArtObjects.size()];

            int index = 0;

            for (MartialArt martialArtObject : allMartialArtObjects)
            {
                martialArtButtons[index] = new MartialArtButton(MainActivity.this,martialArtObject);
                martialArtButtons[index].setText(martialArtObject.getMartialID() +
                        "\n" + martialArtObject.getMartialArtName() + "\n"
                        + martialArtObject.getMartialArtPrice());


                switch (martialArtObject.getMartialArtColor().toLowerCase()) {


                    case "red":
                        martialArtButtons[index].setBackgroundColor(Color.RED);
                        break;
                    case "blue":
                        martialArtButtons[index].setBackgroundColor(Color.BLUE);
                        break;
                    case "black":
                        martialArtButtons[index].setBackgroundColor(Color.BLACK);
                        break;
                    case "yellow":
                        martialArtButtons[index].setBackgroundColor(Color.YELLOW);
                        break;
                    case "purple":
                        martialArtButtons[index].setBackgroundColor(Color.CYAN);
                        break;
                    case "green":
                        martialArtButtons[index].setBackgroundColor(Color.GREEN);
                        break;
                    case "white":
                        martialArtButtons[index].setBackgroundColor(Color.WHITE);
                        break;
                    default:
                        martialArtButtons[index].setBackgroundColor(Color.GRAY);
                        break;


                }

                martialArtButtons[index].setOnClickListener(MainActivity.this);
                gridLayout.addView(martialArtButtons[index],
                        martialArtButtonWidth,
                        ViewGroup.LayoutParams.WRAP_CONTENT);

            }
            scrollView.addView(gridLayout);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        switch (id)
        {
            case R.id.add_martial_art:
                navigationActitivity(this,AddMartialArtActivity.class);
                break;
            case R.id.delete_martial_art:
                navigationActitivity(this,DeleteMartialArtActivity.class);
                break;
            case R.id.update_martial_art:
                navigationActitivity(this,UpdateMartialArtActivity.class);
                break;
            case R.id.martial_art_price_reset:
                totalMartialArtsPrice = 0.0;
                Toast.makeText(MainActivity.this, totalMartialArtsPrice + "", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    void navigationActitivity(Context packageContext,Class<?> cls)
    {
        Intent intent = new Intent(packageContext,cls);
        startActivity(intent);
    }



    @Override
    public void onClick(View view) {
        MartialArtButton martialArtButton = (MartialArtButton) view;
        totalMartialArtsPrice = totalMartialArtsPrice + martialArtButton
                .getMartialArtPrice();
        String martialArtsPriceFormatted = NumberFormat.getCurrencyInstance()
                .format(totalMartialArtsPrice);
        Toast.makeText(MainActivity.this, martialArtsPriceFormatted,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        modifyUserInterface();
    }
}