package spacerunnergame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameOver {
    
    private Pane fPane;
    private Scene fScene;
    private Stage fStage;
    private static final int GAME_HEIGHT = 550;
    private static final int GAME_WiDTH = 600;
    private Stage recivedStage;
    private String txt;
    private int points;
    private String name;
    private String nameSaved;
    
    public GameOver(int points) {
        this.points = points;
        initializeStage();
        createLogo();
        createButtons();
        Scoretxt(points);
        writeName();
    }
    
    public void updateHallOfFame(int points, String name) {
        BufferedReader br = null;
        try {
            File scoreFile = new File("bestScores.csv");
            br = new BufferedReader(new FileReader(scoreFile));
            String scores = br.readLine();
            String[] scoreArray = {};
            if (scores != null) {
                scoreArray = scores.split(",");
            }
            ArrayList<String> scoreList = new ArrayList<String>(Arrays.asList(scoreArray));
            int i = 1;
            boolean f = false;
            for (; i < scoreList.size(); i += 2) {
                if (points > Integer.parseInt(scoreList.get(i))) {
                    scoreList.add(i - 1, name);
                    scoreList.add(i, Integer.toString(points));
                    f = true;
                    break;
                }
            }
            if (!f && i < 9) {
                scoreList.add(i - 1, name);
                scoreList.add(i, Integer.toString(points));
            }
            String result = "";
            for (String text : scoreList) {
                result += text + ",";
            }
            result = result.substring(0, result.length() - 1);
            FileWriter writer = new FileWriter(scoreFile, false);
            writer.write(result);
            writer.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GameOver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GameOver.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(GameOver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void initializeStage() {
        fPane = new Pane();
        fScene = new Scene(fPane, GAME_WiDTH, GAME_HEIGHT);
        fStage = new Stage();
        fStage.setScene(fScene);
        fStage.setTitle("Space Invaders");
        
        Image backgroundImage = new Image("/GameGraphics/backgroundselect.png", 600, 550, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        fPane.setBackground(new Background(background));
        
    }
    
    public void createNewGame(Stage Stage) {
        this.recivedStage = Stage;
        
        this.recivedStage.hide();
        fStage.show();
    }
    
    private void createLogo() {
        ImageView logo = new ImageView("/GameGraphics/gameover.png");
        logo.setScaleX(0.6);
        logo.setScaleY(0.7);
        logo.setLayoutY(-10);
        logo.setLayoutX(-10);
        fPane.getChildren().add(logo);
    }
    
    private void createButtons() {
        ButtonsController exit = new ButtonsController("EXIT");
        ButtonsController menu = new ButtonsController("Menu");
        menu.setLayoutX(185);
        menu.setLayoutY(380);
        exit.setLayoutX(185);
        exit.setLayoutY(450);
        fPane.getChildren().addAll(exit, menu);
        menu.setOnAction(e -> {
            ViewManager fm = new ViewManager();
            fm.createNewGame(recivedStage);
            fStage.close();
        });
        exit.setOnAction(e -> {
            fStage.close();
        });
    }
    
    public void Scoretxt(int points) {
        this.points = points;
        Label s = new Label();
        s.setText("Your Score Is " + points);
        s.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        s.setTextFill(Color.GOLD);
        s.setLayoutX(95);
        s.setLayoutY(250);
        fPane.getChildren().add(s);
    }
    
    public void writeName() {
        Label label = new Label("Name:");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        label.setTextFill(Color.GOLD);
        
        TextField tf = new TextField();
        name = tf.getText();
        
        Button bt = new Button("Save");
        HBox dhb = new HBox();
        bt.setOnAction((ActionEvent e) -> {
            
            name = tf.getText();
            updateHallOfFame(points, name);
            Label dLabel = new Label("Your Name Has Been Saved!!!");
            dLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
            dLabel.setTextFill(Color.GOLD);
            
            dhb.setLayoutX(90);
            dhb.setLayoutY(350);
            dhb.getChildren().add(dLabel);
        });
        
        HBox hb = new HBox();
        hb.setLayoutX(90);
        hb.setLayoutY(310);
        
        hb.getChildren().addAll(label, tf, bt);
        hb.setSpacing(10);
        fPane.getChildren().addAll(hb, dhb);
    }
}
