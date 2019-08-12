package com.example.Charadas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class ChooseActivity extends Activity {
    private int group;

    private SeekBar chooseScore;
    private TextView scoreText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_layout);
        chooseScore = findViewById(R.id.scoreSeekBar);
        scoreText = findViewById(R.id.score);
        scoreText.setText(String.valueOf(0 * 2) + " Puntos ");

        chooseScore.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                scoreText.setText(String.valueOf(progress * 2) + " Puntos ");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


    public void goToPlayBoard(View view) {

        SeekBar seekBar = findViewById(R.id.scoreSeekBar);

        if(seekBar.getProgress() == 0){
            Toast.makeText(getApplicationContext(),"Por favor seleccione una calificación", Toast.LENGTH_SHORT).show();
            return;
        }


        String category = "";

        switch (view.getId()) {
            case R.id.animals:
                category = "animals";
                break;
            case R.id.places:
                category = "places";
                break;
            case R.id.sports:
                category = "sports";
                break;
            case R.id.films:
                category = "films";
                break;
            case R.id.foods:
                category = "foods";
                break;
            case R.id.jobs:
                category = "jobs";
                break;
            case R.id.kings:
                category = "kings";
                break;
            case R.id.publics:
                category = "publics";
                break;
            case R.id.things:
                category = "things";
                break;

        }

        int score = seekBar.getProgress() * 2;

        Intent intent = new Intent(ChooseActivity.this, GameActivity.class);
        Bundle bundle = new Bundle();
        System.out.println(score);
        bundle.putInt("score", score);
        bundle.putString("category", category);

        intent.putExtras(bundle);

        startActivityForResult(intent, 2);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode == 2) {
            if(resultCode == Activity.RESULT_OK) {
                Intent returnIntent = new Intent();

                Bundle bundle = new Bundle();
                bundle.putInt("score", data.getExtras().getInt("score"));
                returnIntent.putExtras(bundle);

                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        }

    }
}
