package com.example.harryaung.wizard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String NUMBER_OF_SCREENS = "numberOfScreens";
    public static final String COLOR_SEQUENCE = "colorSequence";
    private EditText number_of_screens;
    private EditText color_sequence;
    private TextView click_to_open;
    private String[] colors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number_of_screens = (EditText) findViewById(R.id.number_of_screens);
        color_sequence = (EditText) findViewById(R.id.color_sequence);
        click_to_open = (TextView) findViewById(R.id.click_to_open);
        click_to_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startWizard();
            }
        });
    }

    private void startWizard(){
        if (compare()){
            Bundle bundle = new Bundle();
            bundle.putInt(NUMBER_OF_SCREENS,Integer.parseInt(number_of_screens.getText().toString()));
            bundle.putString(COLOR_SEQUENCE,color_sequence.getText().toString().trim().toLowerCase());
            Intent intent = new Intent(getApplicationContext(),WizardActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }else{
            Toast.makeText(this, "Number of screens and color sequence do not match", Toast.LENGTH_LONG).show();
        }
    }

    private boolean compare(){
        colors = color_sequence.getText().toString().split(",");
        if (colors.length == Integer.parseInt(number_of_screens.getText().toString()))
            return true;

        return false;
    }
}
