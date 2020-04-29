package com.example.ramansehmbi.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //0 for Yellow and 1 for Red

    // Game State is 2 which means empty

    int activePlayer = 0;

    int[] gameState ={2,2,2,2,2,2,2,2,2,2};

    int [][] winningPositions ={{0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public void playAgain(View view)
    {
        Button playAgainButton =(Button) findViewById(R.id.playAgainButton);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        playAgainButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout myGridView = (GridLayout) findViewById(R.id.gridLayout);

        for(int i=0; i< myGridView.getChildCount(); i++) {
            ImageView child = (ImageView) myGridView.getChildAt(i);
            child.setImageDrawable(null);
        }


        for (int i =0; i < gameState.length; i++)
        {
            gameState[i] = 2;
        }

        activePlayer = 0;
        gameActive = true;
    }


    boolean gameActive = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        counter.setTranslationY(-1500);
        //Log.i("Tag", counter.getTag().toString());

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameActive) {
            gameState[tappedCounter] = activePlayer;

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).setDuration(300);

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[0]] != 2) {
                    String winner = "";
                    //Someone has won!

                    gameActive = false;

                    if (activePlayer == 1) {
                        //Winner is yellow
                        winner = "Yellow-has-won";
                    } else {
                        winner = "Red-has-won";
                    }

                    Toast.makeText(this, winner, Toast.LENGTH_SHORT).show();

                    Button playAgain = (Button) findViewById(R.id.playAgainButton);
                    TextView winnerView = (TextView) findViewById(R.id.winnerTextView);

                    winnerView.setText(winner);
                    playAgain.setVisibility(View.VISIBLE);

                    winnerView.setVisibility(View.VISIBLE);
                }
            }
        }
    }

}
