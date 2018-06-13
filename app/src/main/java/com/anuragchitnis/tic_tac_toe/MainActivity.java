package com.anuragchitnis.tic_tac_toe;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Create a 2 dimensional array to store the user moves
    private Player userMoves[][] = new Player[3][3];
    private int row, col;
    private int n = 3;
    private int moveCount = 0;
    private Player currentPlayer;
    private Button buttonArray[];
    private TextView messageTextView;

    enum Player {
        X,O, NONE;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonArray = new Button[9];

        buttonArray[0] = findViewById(R.id.button1);
        buttonArray[1] = findViewById(R.id.button2);
        buttonArray[2] = findViewById(R.id.button3);
        buttonArray[3] = findViewById(R.id.button4);
        buttonArray[4] = findViewById(R.id.button5);
        buttonArray[5] = findViewById(R.id.button6);
        buttonArray[6] = findViewById(R.id.button7);
        buttonArray[7] = findViewById(R.id.button8);
        buttonArray[8] = findViewById(R.id.button9);

        messageTextView = findViewById(R.id.messageTextView);


        //By default the game starts with player X
        currentPlayer = Player.X;
    }

    public void buttonClicked(View view) {
        switch (view.getId()) {
            case R.id.button1:
                if(buttonArray[0].isEnabled()) {
                    buttonArray[0].setText(currentPlayer.toString());
                    userMoves[0][0] = currentPlayer;
                    buttonArray[0].setEnabled(false);
                }
                break;
            case R.id.button2:
                if(buttonArray[1].isEnabled()) {
                    buttonArray[1].setText(currentPlayer.toString());
                    userMoves[0][1] = currentPlayer;
                    buttonArray[1].setEnabled(false);
                }
                break;
            case R.id.button3:
                if(buttonArray[2].isEnabled()) {
                    buttonArray[2].setText(currentPlayer.toString());
                    userMoves[0][2] = currentPlayer;
                    buttonArray[2].setEnabled(false);
                }
                break;
            case R.id.button4:
                if(buttonArray[3].isEnabled()) {
                    buttonArray[3].setText(currentPlayer.toString());
                    userMoves[1][0] = currentPlayer;
                    buttonArray[3].setEnabled(false);
                }
                break;
            case R.id.button5:
                if(buttonArray[4].isEnabled()) {
                    buttonArray[4].setText(currentPlayer.toString());
                    userMoves[1][1] = currentPlayer;
                    buttonArray[4].setEnabled(false);
                }
                break;
            case R.id.button6:
                if(buttonArray[5].isEnabled()) {
                    buttonArray[5].setText(currentPlayer.toString());
                    userMoves[1][2] = currentPlayer;
                    buttonArray[5].setEnabled(false);
                }
                break;
            case R.id.button7:
                if(buttonArray[6].isEnabled()) {
                    buttonArray[6].setText(currentPlayer.toString());
                    userMoves[2][0] = currentPlayer;
                    buttonArray[6].setEnabled(false);
                }
                break;
            case R.id.button8:
                if(buttonArray[7].isEnabled()) {
                    buttonArray[7].setText(currentPlayer.toString());
                    userMoves[2][1] = currentPlayer;
                    buttonArray[7].setEnabled(false);
                }
                break;
            case R.id.button9:
                if(buttonArray[8].isEnabled()) {
                    buttonArray[8].setText(currentPlayer.toString());
                    userMoves[2][2] = currentPlayer;
                    buttonArray[8].setEnabled(false);
                }
                break;
            default:
                break;

        }

        if(hasWon(currentPlayer)) {
            declareWinner(currentPlayer);
        } else
            toggleCurrentPlayer();

        if(moveCount >= 9) {
            messageTextView.setText("Match is Drawn - Please start again");
            messageTextView.setTextColor(Color.RED);
        }
    }

    private void disableAllButtons() {
        for(Button button : buttonArray) {
            button.setEnabled(false);
        }
    }

    /**
     * Reset all the moves and enable all the buttons when reset is clicked
     * @param view clicked view
     */
    public void resetClicked(View view) {
        userMoves = new Player[3][3];

        for(Button button : buttonArray) {
            button.setEnabled(true);
            button.setText("");
        }

        messageTextView.setText("Make your move - "+currentPlayer.toString());
        messageTextView.setTextColor(Color.BLACK);

        moveCount = 0;
    }

    /**
     * Toggle the current player so the next player could take his turn
     */
    private void toggleCurrentPlayer() {
        if(currentPlayer == Player.X)
            currentPlayer = Player.O;
        else
            currentPlayer = Player.X;

        messageTextView.setText("Make your move - "+currentPlayer.toString());
    }

    void declareWinner(Player winnerPlayer) {
        String winnerString = "Winner is Player "+winnerPlayer.toString();
        messageTextView.setText(winnerString);
        messageTextView.setTextColor(Color.RED);
        disableAllButtons();
    }

    /**
     * Determine if the player has won the game
     * @param recentPlayer player to be tested for winning condition
     * @return true if the recentPlayer has won, false otherwise
     */
    private boolean hasWon(Player recentPlayer) {
        moveCount++;
        return(userMoves[0][0] == recentPlayer && userMoves[0][1] == recentPlayer && userMoves[0][2] == recentPlayer) ||
                (userMoves[0][0] == recentPlayer && userMoves[1][1] == recentPlayer && userMoves[2][2] == recentPlayer) ||
                (userMoves[0][0] == recentPlayer && userMoves[1][0] == recentPlayer && userMoves[2][0] == recentPlayer) ||
                (userMoves[0][1] == recentPlayer && userMoves[1][1] == recentPlayer && userMoves[2][1] == recentPlayer) ||
                (userMoves[0][2] == recentPlayer && userMoves[1][2] == recentPlayer && userMoves[2][2] == recentPlayer) ||
                (userMoves[1][0] == recentPlayer && userMoves[1][1] == recentPlayer && userMoves[1][2] == recentPlayer) ||
                (userMoves[2][0] == recentPlayer && userMoves[2][1] == recentPlayer && userMoves[2][2] == recentPlayer) ||
                (userMoves[0][2] == recentPlayer && userMoves[1][1] == recentPlayer && userMoves[2][0] == recentPlayer);
    }
}
