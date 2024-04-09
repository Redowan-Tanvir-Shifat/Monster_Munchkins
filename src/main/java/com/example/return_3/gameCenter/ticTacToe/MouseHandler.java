package com.example.return_3.gameCenter.ticTacToe;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class MouseHandler {
    private TicTacToe ticTacToe;
    private Canvas canvas;

    public MouseHandler(TicTacToe ticTacToe, Canvas canvas) {
        this.ticTacToe = ticTacToe;
        this.canvas = canvas;
        attachMouseEventHandler();
    }

    private void attachMouseEventHandler() {
        canvas.setOnMouseClicked(event -> handleMouseClick(event));
        // Add other mouse event attachments if needed
    }

    public void handleMouseClick(MouseEvent event) {
        // Mouse click handling logic
        if (ticTacToe.isAccepted()) {
            if (ticTacToe.isYourTurn() && !ticTacToe.isUnableToCommunicateWithOpponent() && !ticTacToe.isWon() && !ticTacToe.isEnemyWon()) {
                int x = (int) (event.getX() / ticTacToe.getLengthOfSpace());
                int y = (int) (event.getY() / ticTacToe.getLengthOfSpace());
                y *= 3;
                int position = x + y;

                if (ticTacToe.getSpaces()[position] == null) {
                    if (!ticTacToe.isCircle()) ticTacToe.getSpaces()[position] = "X";
                    else ticTacToe.getSpaces()[position] = "O";
                    ticTacToe.setYourTurn(false);

                    try {
                        ticTacToe.getDos().writeInt(position);
                        ticTacToe.getDos().flush();
                    } catch (IOException e1) {
                        ticTacToe.incrementErrors();
                        e1.printStackTrace();
                    }

                    System.out.println("DATA WAS SENT");
                    ticTacToe.checkForWin();
                    ticTacToe.checkForTie();
                }
            }
        }
    }

    // Other mouse event handling methods
}
