package com.example.return_3.gameCenter.ticTacToe;

import com.example.return_3.main.Game;
import com.example.return_3.main.UtilityTool;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


import javax.imageio.ImageIO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TicTacToe {
    private String ip = "localhost";
    public int port = 22222;



    private Scanner scanner = new Scanner(System.in);
   // private JFrame frame;
    private final int WIDTH = 506;
    private final int HEIGHT = 527;
    private Thread thread;

    //private Painter painter;
    public Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;

    public ServerSocket serverSocket;

    private Image board;
    private Image redX;
    private Image blueX;
    private Image redCircle;
    private Image blueCircle;

    private String[] spaces = new String[9];

    private boolean yourTurn = false;
    private boolean circle = true;
    private boolean accepted = false;
    private boolean unableToCommunicateWithOpponent = false;
    private boolean won = false;
    private boolean enemyWon = false;
    private boolean tie = false;

    private int lengthOfSpace = 160;
    private int errors = 0;
    private int firstSpot = -1;
    private int secondSpot = -1;

    private Font font = new Font("Verdana", 32);
    private Font smallerFont = new Font("Verdana",  20);
    private Font largerFont = new Font("Verdana",  50);

    private String waitingString = "Waiting for another player";
    private String unableToCommunicateWithOpponentString = "Unable to communicate with opponent.";
    private String wonString = "You won!";
    private String enemyWonString = "Opponent won!";
    private String tieString = "Game ended in a tie.";

    private int[][] wins = new int[][] { { 0, 1, 2 }, { 3, 4, 5 }, { 6, 7, 8 }, { 0, 3, 6 }, { 1, 4, 7 }, { 2, 5, 8 }, { 0, 4, 8 }, { 2, 4, 6 } };

    /**
     * <pre>
     * 0, 1, 2
     * 3, 4, 5
     * 6, 7, 8
     * </pre>
     */

    UtilityTool uTool = new UtilityTool();
    Game game;
    GraphicsContext gc;
    Canvas canvas;
    private MouseHandler mouseHandler;


    public TicTacToe(Game game, GraphicsContext gc) {
        this.game = game;
        this.gc = gc;
        this.canvas=game.mainGameCanvas;
        this.mouseHandler = new MouseHandler(this, canvas);

    }

    public void startClient(){
        System.out.println("Client started...");
        startGame();
//        thread=new Thread(this);
//        thread.start();
    }
    public void startServer(){
        initializeServer();
        System.out.println("server started...");
        startGame();
//        thread=new Thread(this);
//        thread.start();
    }

    public boolean connect(String ip,int port) {

        try {
            socket = new Socket(ip, port);
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            accepted = true;
        } catch (IOException e) {
            System.out.println("Unable to connect to the address: " + ip + ":" + port );
            return false;
        }
        loadImages();
        System.out.println("Successfully connected to the server.");
        return true;
    }


    private void initializeServer() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("ip: " + serverSocket.getInetAddress().getHostAddress());
            System.out.println("port: "+ port);
            loadImages();
            System.out.println("Server Inititialize");
        } catch (Exception e) {
            e.printStackTrace();
        }
        yourTurn = true;
        circle = false;
    }

    private void loadImages() {
            board = uTool.loadImage("/gameCenter/tictactoe/blueCircle.png",160,160);
            redX = uTool.loadImage("/gameCenter/tictactoe/redX.png",160,160);
            redCircle =  uTool.loadImage("/gameCenter/tictactoe/redCircle.png",160,160);
            blueX = uTool.loadImage("/gameCenter/tictactoe/blueX.png",160,160);
            blueCircle =  uTool.loadImage("/gameCenter/tictactoe/blueCircle.png",160,160);
    }




///THread Code Start
    public void startGame(){
        System.out.println("Game Start");
        Game.gameInstance.gameStatus=Game.gameInstance.gameTicTacToeStatus;

        Pane gameTicTacToeRoot = new Pane();
        Scene scene = new Scene(gameTicTacToeRoot, game.screenWidth, game.screenHeight); // Set the scene before creating KeyHandler
        Game.gameTimer.start();
        Game.primaryStage.setScene(scene);

    }


    //AdditionAL method for update
    private void checkForEnemyWin() {
        for (int i = 0; i < wins.length; i++) {
            if (circle) {
                if (spaces[wins[i][0]] == "X" && spaces[wins[i][1]] == "X" && spaces[wins[i][2]] == "X") {
                    firstSpot = wins[i][0];
                    secondSpot = wins[i][2];
                    enemyWon = true;
                }
            } else {
                if (spaces[wins[i][0]] == "O" && spaces[wins[i][1]] == "O" && spaces[wins[i][2]] == "O") {
                    firstSpot = wins[i][0];
                    secondSpot = wins[i][2];
                    enemyWon = true;
                }
            }
        }
    }

    void checkForTie() {
        for (int i = 0; i < spaces.length; i++) {
            if (spaces[i] == null) {
                return;
            }
        }
        tie = true;
    }


    public void update(){
        if (errors >= 10) unableToCommunicateWithOpponent = true;

        if (!yourTurn && !unableToCommunicateWithOpponent) {
            try {
                int space = dis.readInt();
                if (circle) spaces[space] = "X";
                else spaces[space] = "O";
                checkForEnemyWin();
                checkForTie();
                yourTurn = true;
            } catch (IOException e) {
                e.printStackTrace();
                errors++;
            }
        }
    }




    public double getWidthOfText(String text,GraphicsContext gc){
        // Create a temporary Text node to measure the width
        Text textNode = new Text(text);
        textNode.setFont(gc.getFont());
        double textWidth = textNode.getBoundsInLocal().getWidth();
        return textWidth;
    }




    public void draw(GraphicsContext gc){
        gc.drawImage(board, 0, 0);
        if (unableToCommunicateWithOpponent) {
            gc.setFill(Color.RED);
            gc.setFont(smallerFont);
            gc.setTextAlign(TextAlignment.CENTER);
            gc.fillText(unableToCommunicateWithOpponentString, WIDTH / 2, HEIGHT / 2);
            return;
        }

        if (accepted) {
            for (int i = 0; i < spaces.length; i++) {
                if (spaces[i] != null) {
                    if (spaces[i].equals("X")) {
                        if (circle) {
                            gc.drawImage(redX, (i % 3) * lengthOfSpace + 10 * (i % 3), (int) (i / 3) * lengthOfSpace + 10 * (int) (i / 3));
                        } else {
                            gc.drawImage(blueX, (i % 3) * lengthOfSpace + 10 * (i % 3), (int) (i / 3) * lengthOfSpace + 10 * (int) (i / 3));
                        }
                    } else if (spaces[i].equals("O")) {
                        if (circle) {
                            gc.drawImage(blueCircle, (i % 3) * lengthOfSpace + 10 * (i % 3), (int) (i / 3) * lengthOfSpace + 10 * (int) (i / 3));
                        } else {
                            gc.drawImage(redCircle, (i % 3) * lengthOfSpace + 10 * (i % 3), (int) (i / 3) * lengthOfSpace + 10 * (int) (i / 3));
                        }
                    }
                }
            }
            if (won || enemyWon) {
                gc.setStroke(Color.BLACK);
                gc.setLineWidth(10);
                gc.strokeLine(firstSpot % 3 * lengthOfSpace + 10 * firstSpot % 3 + lengthOfSpace / 2, (int) (firstSpot / 3) * lengthOfSpace + 10 * (int) (firstSpot / 3) + lengthOfSpace / 2, secondSpot % 3 * lengthOfSpace + 10 * secondSpot % 3 + lengthOfSpace / 2, (int) (secondSpot / 3) * lengthOfSpace + 10 * (int) (secondSpot / 3) + lengthOfSpace / 2);

                gc.setFill(Color.RED);
                gc.setFont(largerFont);
                if (won) {
                    gc.fillText(wonString, WIDTH / 2, HEIGHT / 2);
                } else if (enemyWon) {
                    gc.fillText(enemyWonString, WIDTH / 2, HEIGHT / 2);
                }
            }
            if (tie) {
                gc.setFill(Color.BLACK);
                gc.setFont(largerFont);
                gc.fillText(tieString, WIDTH / 2, HEIGHT / 2);
            }
        } else {
            gc.setFill(Color.RED);
            gc.setFont(font);
            gc.setTextAlign(TextAlignment.CENTER);
            gc.fillText(waitingString, WIDTH / 2, HEIGHT / 2);
        }
    }
















    public void showTicTacToeMenuPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/return_3/tictactoe/tictactoeMenu.fxml"));
        Scene menuScene = new Scene(fxmlLoader.load());
        Game.gameInstance.primaryStage.setScene(menuScene);
    }
    public void showTicTacToeServerPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/return_3/tictactoe/tictactoeServer.fxml"));
        Scene menuScene = new Scene(fxmlLoader.load());
        Game.gameInstance.primaryStage.setScene(menuScene);
    }
    public void showTicTacToeRoomPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/return_3/tictactoe/tictactoeRoom.fxml"));
        Scene menuScene = new Scene(fxmlLoader.load());
        Game.gameInstance.primaryStage.setScene(menuScene);
    }





//    @Override
//    public void mouseClicked(MouseEvent e) {
//        if (accepted) {
//            if (yourTurn && !unableToCommunicateWithOpponent && !won && !enemyWon) {
//                int x = e.getX() / lengthOfSpace;
//                int y = e.getY() / lengthOfSpace;
//                y *= 3;
//                int position = x + y;
//
//                if (spaces[position] == null) {
//                    if (!circle) spaces[position] = "X";
//                    else spaces[position] = "O";
//                    yourTurn = false;
////                    repaint();
//                    Toolkit.getDefaultToolkit().sync();
//
//                    try {
//                        dos.writeInt(position);
//                        dos.flush();
//                    } catch (IOException e1) {
//                        errors++;
//                        e1.printStackTrace();
//                    }
//
//                    System.out.println("DATA WAS SENT");
//                    checkForWin();
//                    checkForTie();
//
//                }
//            }
//        }
//    }
//
//    @Override
//    public void mousePressed(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseReleased(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseEntered(MouseEvent e) {
//
//    }
//
//    @Override
//    public void mouseExited(MouseEvent e) {
//
//    }

    void checkForWin() {
        for (int i = 0; i < wins.length; i++) {
            if (circle) {
                if (spaces[wins[i][0]] == "O" && spaces[wins[i][1]] == "O" && spaces[wins[i][2]] == "O") {
                    firstSpot = wins[i][0];
                    secondSpot = wins[i][2];
                    won = true;
                }
            } else {
                if (spaces[wins[i][0]] == "X" && spaces[wins[i][1]] == "X" && spaces[wins[i][2]] == "X") {
                    firstSpot = wins[i][0];
                    secondSpot = wins[i][2];
                    won = true;
                }
            }
        }
    }

    ///GETTER AND SETTER
    public boolean isAccepted() {
        return accepted;
    }

    public DataOutputStream getDos() {
        return dos;
    }

    public DataInputStream getDis() {
        return dis;
    }

    public String[] getSpaces() {
        return spaces;
    }

    public boolean isYourTurn() {
        return yourTurn;
    }

    public boolean isCircle() {
        return circle;
    }

    public boolean isUnableToCommunicateWithOpponent() {
        return unableToCommunicateWithOpponent;
    }

    public int getLengthOfSpace() {
        return lengthOfSpace;
    }

    public int getErrors() {
        return errors;
    }

    public boolean isWon() {
        return won;
    }

    public boolean isEnemyWon() {
        return enemyWon;
    }

    public void incrementErrors() {
        errors++;
    }

    public void setYourTurn(boolean yourTurn) {
        this.yourTurn = yourTurn;
    }
    //    @Override
//    public void run() {
//
//    }
}
