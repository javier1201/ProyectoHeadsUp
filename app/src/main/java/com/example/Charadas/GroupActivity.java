package com.example.Charadas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Adapters.GroupAdapter;

public class GroupActivity extends Activity {
    public int whosTurn = 0;
    private int whoseScore = 0;
    private int size;
    private int rounds;
    private int roundsPlayed = 0;


    private GroupAdapter groupAdapter;

    private ArrayList<int[]> groups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        this.size = intent.getExtras().getInt("groupNumber") - 1;
        this.rounds = intent.getExtras().getInt("rounds");

        setContentView(R.layout.group_layout);

        this.groups= new ArrayList<>();

        for(int i = 0; i <= this.size; i++) {
            this.groups.add(new int[] {i, 0});
        }


        this.groupAdapter = new GroupAdapter(getLayoutInflater(), this.groups);
        groupAdapter.notifyDataSetChanged();

        ListView lv = findViewById(R.id.groupList);
        lv.setAdapter(groupAdapter);

        this.updateButtonTitle();

    }

    private void updateButtonTitle() {
        Button button = findViewById(R.id.play);
        button.setText("Comenzar ronda " + (this.whosTurn + 1));
    }

    private int winnerGroup () {
        int max = 0;
        int groupNumber = 1;
        for(int[] group : this.groups) {
            max = max > group[1] ? max : group[1];
            groupNumber = max > group[1] ? groupNumber : group[0] + 1;
        }
        return groupNumber;
    }

    public void goToGame(View view) {
        startActivityForResult(new Intent(GroupActivity.this, ChooseActivity.class), 1);
        if(this.rounds == this.roundsPlayed ) {
            Toast.makeText(getApplicationContext(), "El grupo ganador es " + this.winnerGroup() + " ", Toast.LENGTH_SHORT).show();
            view.setClickable(false);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1) {
            if(resultCode == Activity.RESULT_OK) {

                this.whoseScore = this.whosTurn;

                if(this.whosTurn == this.size) {
                    this.whosTurn = 0;
                    this.roundsPlayed++;
                } else {
                    this.whosTurn += 1;
                }

                this.groupAdapter.update(this.whoseScore, data.getExtras().getInt("score"));

                this.updateButtonTitle();
            }

            if(this.rounds == this.roundsPlayed ) {
                TextView winner = findViewById(R.id.winner);
                winner.setVisibility(View.VISIBLE);
                winner.setText( "El ganador es el grupo " + this.winnerGroup() + "");
                return;
            }
        }
    }
}
