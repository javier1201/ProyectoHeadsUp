package com.example.Charadas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> words;
    private SeekBar roundSeekBar;
    private TextView roundtxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.number_layout);


        roundSeekBar = findViewById(R.id.seekBar);
        roundtxt = findViewById(R.id.round);

        roundSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                roundtxt.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void  goToGroups(View view) {
        roundSeekBar = findViewById(R.id.seekBar);
        System.out.println("progress" + roundSeekBar.getProgress());

        if(roundSeekBar.getProgress() == 0) {

            Toast.makeText(getApplicationContext(),"Seleccione el numero de rondas", Toast.LENGTH_LONG).show();
            view.setClickable(false);
            return;
        }

        Intent intent = new Intent(MainActivity.this, GroupActivity.class);

        Bundle bundle = new Bundle();

        Button button = (Button) view;

        bundle.putInt("groupNumber", Integer.valueOf(button.getText().toString()));
        bundle.putInt("rounds", Integer.valueOf(roundtxt.getText().toString()));

        intent.putExtras(bundle);

        startActivity(intent);
    }
}
