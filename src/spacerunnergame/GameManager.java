package spacerunnergame;

import javafx.animation.AnimationTimer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.Random; //mark
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class GameManager {

    private Pane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private String choosenShip;
    private static final int GAME_HEIGHT = 600;
    private static final int GAME_WiDTH = 400;
    private Stage menuStage;
//    private static boolean left ,right;
    private int angle;
    private static ImageView PShip;
    private final static String METEOR_1 = "/GameGraphics/meteor2.png"; //mark
    private final static String METEOR_2 = "/GameGraphics/meteor1.png"; //mark
    private final static String Star_1 = "/GameGraphics/shape 3.png";
    private final static String life_s = "/GameGraphics/life.png";
    private ImageView[] BrownMeteor;//mark
    private ImageView[] GreyMeteor; //mark
    private ImageView[] Star;
    private ImageView[] life;
    Random RandomPosition; //mark
    private AnimationTimer gametimer;
    private final static double METEOR_RADIUS = 15;
    private final static double Pship_RADIUS = 30;
    private final static double Star_1_RADIUS = 15;
    private int points = 0;
    private int x1 = 280;
    private int x2 = 340;
    private int lifes = 3;
    private int mils = 0;
    private int sec = 0;
    private Label timer;
    private Boolean classic = false;
    private Boolean minute = false;
    private Boolean rush = false;
    private int nostars;
    private int nometeors;
    private int speed;

    public GameManager() {
        initializeStage();
        Score();

        Image backgroundImage = new Image("/GameGraphics/backgroundgame.png", 400, 600, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        gamePane.setBackground(new Background(background));
        RandomPosition = new Random(); //mark
    }

    private void TimerMode() {
        if (classic == false && rush == false && minute == true) {
            nometeors = 5;
            nostars = 5;
            speed = 5;
            timer = new Label();
            timer.setLayoutX(270);
            timer.setLayoutY(80);
            timer.setFont(Font.font("Verdana", FontWeight.BOLD, 40));
            timer.setTextFill(Color.GOLD);
            gamePane.getChildren().add(timer);

        }

    }

    private void ClassicMode() {
        if (classic == true && rush == false && minute == false) {
            nometeors = 5;
            nostars = 5;
            speed = 5;
        }
    }

    private void RushMode() {
        if (classic == false && rush == true && minute == false) {
            nometeors = 8;
            nostars = 8;
            speed = 8;
        }
    }

    private void timer() {
        if (classic == false && rush == false && minute == true) {
            mils += 1;
            if (mils % 60 == 0) {
                mils = 0;
                sec += 1;
                timer.setText("" + sec + ":" + mils);
            } else if (sec == 60) {
                nometeors = 0;
                nostars = 0;
                speed = 0;
                GameOver go = new GameOver(points);
                go.createNewGame(menuStage);
                gameStage.close();
                gametimer.stop();
            } else {
                timer.setText("" + sec + ":" + mils);
            }
        }
    }

    public void setNewElementsPosition(ImageView image) {
        image.setLayoutX(RandomPosition.nextInt(370));
        image.setLayoutY(-(RandomPosition.nextInt(3200) + 600));
    }

    public void createGameElements() {
        Star = new ImageView[nostars];
        for (int i = 0; i < Star.length; i++) {
            Star[i] = new ImageView(Star_1);
            Star[i].setFitHeight(30);
            Star[i].setFitWidth(30);
            gamePane.getChildren().add(Star[i]);
            setNewElementsPosition(Star[i]);
        }
        BrownMeteor = new ImageView[nometeors];
        for (int i = 0; i < BrownMeteor.length; i++) {
            BrownMeteor[i] = new ImageView(METEOR_2);
            BrownMeteor[i].setFitHeight(60);
            BrownMeteor[i].setFitWidth(40);
            gamePane.getChildren().add(BrownMeteor[i]);
            setNewElementsPosition(BrownMeteor[i]);
        }
        GreyMeteor = new ImageView[nometeors];
        for (int i = 0; i < GreyMeteor.length; i++) {
            GreyMeteor[i] = new ImageView(METEOR_1);
            GreyMeteor[i].setFitHeight(60);
            GreyMeteor[i].setFitWidth(60);
            gamePane.getChildren().add(GreyMeteor[i]);
            setNewElementsPosition(GreyMeteor[i]);
        }


    }

    public void moveGameElements() {
        for (ImageView Star1 : Star) {
            Star1.setLayoutY(Star1.getLayoutY() + speed);
            
        }
        for (ImageView BrownMeteor1 : BrownMeteor) {
            BrownMeteor1.setLayoutY(BrownMeteor1.getLayoutY() + speed);
            
        }
        for (ImageView GreyMeteor1 : GreyMeteor) {
            GreyMeteor1.setLayoutY(GreyMeteor1.getLayoutY() + speed);
            
        }

        timer();

    }

    private void checkElementsBehindSHip() {

        for (ImageView Star1 : Star) {
            if (Star1.getLayoutY() > 900) {
                setNewElementsPosition(Star1);
            }
        }
        for (ImageView BrownMeteor1 : BrownMeteor) {
            if (BrownMeteor1.getLayoutY() > 900) {
                setNewElementsPosition(BrownMeteor1);
            }
        }
        for (ImageView GreyMeteor1 : GreyMeteor) {
            if (GreyMeteor1.getLayoutY() > 900) {
                setNewElementsPosition(GreyMeteor1);
            }
        }

    }

    private double distanceBetweenElements(double x1, double x2, double y1, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));

    }

    private void Collision() {

        for (ImageView Star1 : Star) {
            if ((Pship_RADIUS + Star_1_RADIUS) > distanceBetweenElements(PShip.getLayoutX()+10, Star1.getLayoutX() + 15, PShip.getLayoutY()+10, Star1.getLayoutY() + 15)) {
                points += 1;
                Score();
                setNewElementsPosition(Star1);
                gamePane.getChildren().remove(Star1);
            }
        }
        for (ImageView BrownMeteor1 : BrownMeteor) {
            if ((Pship_RADIUS + METEOR_RADIUS) > distanceBetweenElements(PShip.getLayoutX()+10, BrownMeteor1.getLayoutX() + 15, PShip.getLayoutY()+10, BrownMeteor1.getLayoutY() + 15)) {
                lifes -= 1;
                removelife();
                setNewElementsPosition(BrownMeteor1);
                gamePane.getChildren().remove(BrownMeteor1);
            }
        }
        for (ImageView GreyMeteor1 : GreyMeteor) {
            if ((Pship_RADIUS + Star_1_RADIUS) > distanceBetweenElements(PShip.getLayoutX()+10, 
                    GreyMeteor1.getLayoutX()+15, PShip.getLayoutY()+10, GreyMeteor1.getLayoutY()+15)) {
                lifes -= 1;
                removelife();
                setNewElementsPosition(GreyMeteor1);
                gamePane.getChildren().remove(GreyMeteor1);
            }
        }
    }

    public void removelife() {
        if (lifes == 0) {
            nometeors = 0;
            nostars = 0;
            speed = 0;
            GameOver go = new GameOver(points);
            go.createNewGame(menuStage);
            gameStage.close();
            gametimer.stop();

        } else {
            ImageView rvlife = new ImageView("/GameGraphics/greylife.png");
            rvlife.setLayoutX(x2);
            rvlife.setLayoutY(40);
            x2 -= 30;
            gamePane.getChildren().add(rvlife);
        }

    }

    private void initializeStage() {
        gamePane = new Pane();
        gameScene = new Scene(gamePane, GAME_WiDTH, GAME_HEIGHT);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
        gameStage.setTitle("Space Invaders");
        gameStage.setResizable(false);
    }

    public void createShip(String Ship) {
        PShip = new ImageView(Ship);
        gamePane.getChildren().add(PShip);
        PShip.setFitWidth(50);
        PShip.setFitHeight(50);
        PShip.setLayoutX(150);
        PShip.setLayoutY(500);

    }

    private void Score() {
        Label lb = new Label("Score :" + points);
        lb.setAlignment(Pos.CENTER);
        lb.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        lb.setMinWidth(120);
        lb.setMinHeight(25);
        lb.setLayoutX(270);
        lb.setLayoutY(15);
        Image backgroundImage = new Image("/GameGraphics/score.png", 120, 25, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        lb.setBackground(new Background(background));

        gamePane.getChildren().add(lb);

    }

    private void GameLoop() {
        gametimer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                moveGameElements();
                checkElementsBehindSHip();
                Collision();
            }
        };
        gametimer.start();
    }

    private void Life() {
        life = new ImageView[lifes];
        for (int i = 0; i < lifes; i++) {
            life[i] = new ImageView(life_s);
            life[i].setLayoutX(x1);
            life[i].setLayoutY(40);
            x1 += 30;
            gamePane.getChildren().add(life[i]);
        }
    }

    private void keyController() {
        gameScene.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.LEFT) {
                if (GAME_WiDTH - PShip.getLayoutX() <= GAME_WiDTH) {
                    PShip.setLayoutX(PShip.getLayoutX() - 20);
                    PShip.setRotate(-angle - 30);
                }
            } else if (e.getCode() == KeyCode.RIGHT) {
                if (PShip.getLayoutX() <= (GAME_WiDTH - 60.0)) {
                    PShip.setLayoutX(PShip.getLayoutX() + 20);
                    PShip.setRotate(angle + 30);
                }
            }
        });

        gameScene.setOnKeyReleased((javafx.scene.input.KeyEvent e)
                -> {
            PShip.setRotate(angle);
        });
    }

    public void createNewGame(Stage menuStage, String Ship, Boolean classic, Boolean rush, Boolean minute) {
        this.menuStage = menuStage;
        this.choosenShip = Ship;
        this.classic = classic;
        this.rush = rush;
        this.minute = minute;
        this.menuStage.hide();
        gameStage.show();
        TimerMode();
        ClassicMode();
        RushMode();
        createShip(Ship);
        createGameElements();
        GameLoop();
        keyController();
        Life();

    }

}
