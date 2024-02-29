package com.example.return_3.gameCenter.spaceInvaders;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class SpaceInvaders extends Application {
    // Random number generator
    private static final Random RAND = new Random();

    // Screen dimensions
    private static final int width = 800;
    private static final int height = 600;

    // Player dimensions
    private static final int playerSize = 60;

    // Images for player, explosions, and bombs
    static final Image PLAYER_IMG = new Image("/boy_down_1.png");
    static final Image EXPLOSION_IMG = new Image("/boy_up_1.png");
    static final int EXPLOSION_W = 128;
    static final int EXPLOSION_ROWS = 3;
    static final int EXPLOSION_COL = 3;
    static final int EXPLOSION_H = 128;
    static final int EXPLOSION_STEPS = 15;
    static final Image BOMBS_IMG[] = {new Image("/boy_right_1.png"), new Image("/boy_right_2.png"),
            new Image("/boy_left_1.png"), new Image("/boy_left_2.png")};

    // Maximum number of bombs and shots
    final int max_bombs = 10;
    final int max_shots = max_bombs * 2;

    // Game state variables
    boolean gameOver = false;
    private GraphicsContext gc;
    Rocket player;
    List<Shot> shots;
    List<Universe> univ;
    List<Bomb> bombs;
    private double mouseX;
    private int score;

    // Start method
    @Override
    public void start(Stage stage) throws Exception {
        // Create canvas
        Canvas canvas = new Canvas(width, height);
        gc = canvas.getGraphicsContext2D();

        // Create timeline for game loop
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> run(gc)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // Set up event handlers
        canvas.setCursor(Cursor.MOVE);
        canvas.setOnMouseMoved(e -> mouseX = e.getX());
        canvas.setOnMouseClicked(e -> {
            if (shots.size() < max_shots) {
                shots.add(player.shoot());
            }
            if (gameOver) {
                gameOver = false;
                setup();
            }
        });

        // Initialize game
        setup();

        // Set up scene
        stage.setScene(new Scene(new StackPane(canvas)));
        stage.setTitle("Space fight");
        stage.show();
    }

    // Method to set up the game
    public void setup() {
        univ = new ArrayList<>();
        shots = new ArrayList<>();
        bombs = new ArrayList<>();
        player = new Rocket(width / 2, height - playerSize, playerSize, PLAYER_IMG);
        score = 0;
        IntStream.range(0, max_bombs).mapToObj(i -> newBomb()).forEach(bombs::add);
    }

    // Game loop method
    private void run(GraphicsContext gc) {
        // Clear the screen
        gc.setFill(Color.grayRgb(20));
        gc.fillRect(0, 0, width, height);

        // Draw score
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setFont(Font.font(20));
        gc.setFill(Color.WHITE);
        gc.fillText("Score: " + score, 60, 20);

        // Display game over message if the game is over
        if (gameOver) {
            gc.setFont(Font.font(35));
            gc.setFill(Color.YELLOW);
            gc.fillText("Game over \n Your score is " + score + " \n Click to play again", width / 2, height / 2.5);
        }

        // Draw universe
        univ.forEach(Universe::draw);

        // Update and draw player
        player.update();
        player.draw();
        player.posX = (int) mouseX;

        // Update and draw bombs
        bombs.stream().peek(Rocket::update).peek(Rocket::draw).forEach(e -> {
            if (player.colide(e) && !player.exploding) {
                player.explode();
            }
        });

        // Update and draw shots
        for (int i = shots.size() - 1; i >= 0; i--) {
            Shot shot = shots.get(i);
            if (shot.posY < 0 || shot.toRemove) {
                shots.remove(i);
                continue;
            }
            shot.update();
            shot.draw();
            for (Bomb bomb : bombs) {
                if (shot.colide(bomb) && !bomb.exploding) {
                    score++;
                    bomb.explode();
                    shot.toRemove = true;
                }
            }
        }

        // Replace destroyed bombs with new ones
        for (int i = bombs.size() - 1; i >= 0; i--) {
            if (bombs.get(i).destroyed) {
                bombs.set(i, newBomb());
            }
        }

        // Check game over condition
        gameOver = player.destroyed;

        // Add new stars to the universe
        if (RAND.nextInt(10) > 2) {
            univ.add(new Universe());
        }

        // Remove stars that are out of the screen
        for (int i = 0; i < univ.size(); i++) {
            if (univ.get(i).posY > height) {
                univ.remove(i);
            }
        }
    }

    // Player class
    public class Rocket {

        int posX, posY, size;
        boolean exploding, destroyed;
        Image img;
        int explosionsStep = 0;

        // Constructor
        public Rocket(int posX, int posY, int size, Image image) {
            this.posX = posX;
            this.posY = posY;
            this.size = size;
            img = image;
        }

        // Method to shoot
        public Shot shoot() {
//            return new Shot((posX + size) / 2 - Shot.size / 2, posY - Shot.size);
            return new Shot(posX + (size / 2) - (Shot.size / 2), posY - Shot.size);
        }

        // Update method
        public void update() {
            if (exploding) {
                explosionsStep++;
            }
            destroyed = explosionsStep > EXPLOSION_STEPS;
        }

        // Draw method
        public void draw() {
            if (exploding) {
                gc.drawImage(EXPLOSION_IMG, explosionsStep % EXPLOSION_COL * EXPLOSION_W, (explosionsStep / EXPLOSION_ROWS) * EXPLOSION_H + 1, EXPLOSION_W, EXPLOSION_H, posX, posY, size, size);
            } else {
                gc.drawImage(img, posX, posY, size, size);
            }
        }

        // Method to check collision with another object
        public boolean colide(Rocket other) {
            int d = distance(this.posX + size / 2, this.posY + size / 2,
                    other.posX + other.size / 2, other.posY + other.size / 2);
            return d < other.size / 2 + this.size / 2;
        }

        // Method to explode the player
        public void explode() {
            exploding = true;
            explosionsStep = -1;
        }
    }

    // Bomb class
    public class Bomb extends Rocket {
        int SPEED = (score / 5) + 2;

        public Bomb(int posX, int posY, int size, Image image) {
            super(posX, posY, size, image);
        }

        @Override
        public void update() {
            super.update();
            if (!exploding && !destroyed) {
                posY += SPEED;
            }
            if (posY > height) {
                destroyed = true;
            }
        }
    }

    // Shot class
    public class Shot {
        public boolean toRemove;
        int posX, posY, speed = 10;
        static final int size = 6;

        public Shot(int posX, int posY) {
            this.posX = posX;
            this.posY = posY;
        }

        public void update() {
            posY -= speed;
        }

        public void draw() {
            gc.setFill(Color.RED);
            if (score >= 50 && score <= 70 || score >= 120) {
                gc.setFill(Color.YELLOWGREEN);
                speed = 50;
                gc.fillRect(posX - 5, posY - 5, size + 10, size + 30);
            } else {
                gc.fillOval(posX, posY, size, size);
            }
        }

        public boolean colide(Rocket rocket) {
            int distance = distance(this.posX + size / 2, this.posY + size / 2,
                    rocket.posX + size / 2, rocket.posY + size / 2);
            return distance < rocket.size / 2 + size / 2;
        }
    }

    // Universe class
    public class Universe {
        int posX, posY;
        private int h, w, r, g, b;
        private double opacity;

        public Universe() {
            posX = RAND.nextInt(width);
            posY = 0;
            w = RAND.nextInt(5) + 1;
            h = RAND.nextInt(5) + 1;
            r = RAND.nextInt(100) + 150;
            g = RAND.nextInt(100) + 150;
            b = RAND.nextInt(100) + 150;
            opacity = RAND.nextFloat();
            if (opacity < 0) {
                opacity *= -1;
            }
            if (opacity > 0.5) {
                opacity = 0.5;
            }
        }

        public void draw() {
            if (opacity > 0.8) {
                opacity -= 0.01;
            }
            if (opacity < 0.1) {
                opacity += 0.01;
            }
            gc.setFill(Color.rgb(r, g, b, opacity));
            gc.fillOval(posX, posY, w, h);
            posY += 20;
        }
    }

    // Helper method to calculate distance between two points
    int distance(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    // Method to create a new bomb
    Bomb newBomb() {
        return new Bomb(50 + RAND.nextInt(width - 100), 0, playerSize, BOMBS_IMG[RAND.nextInt(BOMBS_IMG.length)]);
    }

    // Main method
    public static void main(String[] args) {
        launch();
    }
}

