package com.example.connect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    boolean state = true;
    int Flag = 1;
    int mm=0;
    int [] game = {2,2,2,2,2,2,2,2,2};
    int[][] winners = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};



    public void dropIn(View view){
        TextView p1name = (TextView) findViewById(R.id.p1Name);
        TextView p2name = (TextView) findViewById(R.id.p2Name);

        ImageView counter = (ImageView) view;
        counter.setTranslationZ(-1500);
        int present = Integer.parseInt(counter.getTag().toString());
        if(game[present]==2 && state==true) {

            game[present] = Flag;
            mm=mm+1;


            if (Flag == 1) {
                counter.setImageResource(R.drawable.o);
                Flag = 0;
            } else {
                counter.setImageResource(R.drawable.x);
                Flag = 1;
            }
            counter.animate().translationZBy(1500).rotation(360).setDuration(300);

            for (int[] winners : winners) {
                if (game[winners[0]] == game[winners[1]] && game[winners[1]] == game[winners[2]] && game[winners[0]] != 2 && mm!=9) {

                    String msg = "";

                    if (Flag == 0) {

                        if(p1name.getText().toString().isEmpty()){

                            msg = "PLAYER 1";
                        }
                        else {
                            msg += p1name.getText().toString();
                        }
                    } else {
                        if(p2name.getText().toString().isEmpty()){

                            msg = "PLAYER 2";
                        }
                        else {
                            msg += p2name.getText().toString();
                        }
                    }

                    Toast.makeText(this,  msg.toUpperCase() + " HAS WON", Toast.LENGTH_LONG).show();
                    Button pA = (Button) findViewById(R.id.playAgain);
                    pA.setVisibility(View.VISIBLE);
                    state = false;
                    mm=0;
                }
                else if(mm==9){

                    Toast.makeText(this,  "DRAW MATCH", Toast.LENGTH_LONG).show();
                    Button pA = (Button) findViewById(R.id.playAgain);
                    pA.setVisibility(View.VISIBLE);
                    state = false;
                    mm=0;
                }
            }
        }
    }


    public void playAgain(View view){

        TextView p1name = (TextView) findViewById(R.id.p1Name);
        TextView p2name = (TextView) findViewById(R.id.p2Name);
        p1name.setText("");
        p2name.setText("");

        Button pA = (Button) findViewById(R.id.playAgain);
        pA.setVisibility(View.INVISIBLE);


        GridLayout gl = (GridLayout) findViewById(R.id.gridLayout);
        for (int i=0; i<gl.getChildCount();i++) {
            ImageView counter = (ImageView) gl.getChildAt(i);
            counter.animate().rotation(360).translationZBy(1000);
            counter.setImageDrawable(null);
        }

        for(int j =0;j<game.length;j++){

            game[j]=2;
        }
        state = true;
        Flag = 1;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
