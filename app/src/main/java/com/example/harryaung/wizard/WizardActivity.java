package com.example.harryaung.wizard;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by Harry on 1/22/2016.
 */
public class WizardActivity extends AppCompatActivity {

    private int numberOfScreens = 0;
    private String colorSequence = "";
    private String[] colors;
    private int count=0;
    private Menu menu;
    private int[] colorCodes;
    private final String DONE = "DONE";
    private final String NEXT = "NEXT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.info_container);
        layout.setVisibility(View.GONE);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null){
            numberOfScreens = bundle.getInt(MainActivity.NUMBER_OF_SCREENS);
            colorSequence = bundle.getString(MainActivity.COLOR_SEQUENCE);

            colors = colorSequence.split(",");
            colorCodes = new int[colors.length];
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Screen " + (count + 1) + "/" + colors.length);

        for (int i=0; i < colors.length; i++){
            Log.d("WizardActivity","color:" + colors[i]);
            String colorStr = colors[i].trim();
            if (colorStr.equals("red"))
                colorCodes[i] = Color.RED;
            else if (colorStr.equals("green"))
                colorCodes[i] = Color.GREEN;
            else if (colorStr.equals("blue"))
                colorCodes[i] = Color.BLUE;
            else if (colorStr.equals("brown"))
                colorCodes[i] = Color.parseColor("#f4a460");
            else if (colorStr.equals("yellow"))
                colorCodes[i] = Color.parseColor("#FFFF00");
        }

        getFragmentManager().beginTransaction()
                .add(R.id.container, WizardFragment.newInstance(colorCodes[count]), ""+count)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                onBackPressed();
                return true;
            }
            case R.id.menu1:
            {
                if (item.getTitle().equals(DONE))
                    closeApp();
                else {
                    if (count < numberOfScreens) {
                        count++;
                        Log.d("WizardActivity", "count:" + count);
                        getFragmentManager().beginTransaction()
                                .add(R.id.container, WizardFragment.newInstance(colorCodes[count]), "" + count)
                                .addToBackStack(null)
                                .commit();
                    }

                    if (count == numberOfScreens - 1) {
                        item.setTitle(DONE);
                    }

                    getSupportActionBar().setTitle("Screen " + (count + 1) + "/" + colors.length);
                }
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void closeApp(){
        Toast.makeText(this,colorSequence,Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onBackPressed() {
        Log.d("WizardActivity", "onBackPressed");
        FragmentManager fm = getFragmentManager();

        if (fm.getBackStackEntryCount() > 1){
            fm.popBackStack();
            changeTitle();
        }
        if (fm.getBackStackEntryCount() == 1){
            finish();
        }
    }

    private void changeTitle(){
        Log.d("WizardActivity", "changeTitle");
        count--;
        getSupportActionBar().setTitle("Screen " + (count + 1) + "/" + colors.length);
        MenuItem item = menu.findItem(R.id.menu1);
        if (item.getTitle().equals(DONE))
            item.setTitle(NEXT);
    }

}
