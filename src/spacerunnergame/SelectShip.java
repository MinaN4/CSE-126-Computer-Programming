package spacerunnergame;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class SelectShip
{
    private Pane gamePane;
    private Scene gameScene;
    private Stage gameStage;
    private static final int GAME_HEIGHT = 600;
    private static final int GAME_WiDTH = 800;
    private Stage menuStage;
    private static  String selection;
    private static final  String moon="/GameGraphics/moon.png";
    private static final  String darkmoon="/GameGraphics/darkmoon.png";
    private static final  String mode1dark="/GameGraphics/mode1dark.png";
    private static final  String mode2dark="/GameGraphics/mode2dark.png";
    private static final  String mode3dark="/GameGraphics/mode3dark.png";
    private static final  String mode1="/GameGraphics/mode1.png";
    private static final  String mode2="/GameGraphics/mode2.png";
    private static final  String mode3="/GameGraphics/mode3.png";
    private Boolean mod1=false;
    private Boolean mod2=false;
    private Boolean mod3=false;
    GameManager gm;
    
    public SelectShip()
    {
        initializeStage();
        ships();
        gameModes();
        Labels();
        
    }
    
    private void initializeStage() {
        gamePane = new Pane();
        gameScene = new Scene(gamePane,GAME_WiDTH,GAME_HEIGHT);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
        gameStage.setTitle("Space Invaders");
        gameStage.setResizable(false);
        
        

        //Start Button
        
        ButtonsController playbtn = new ButtonsController("START");
        playbtn.setLayoutX(300);
        playbtn.setLayoutY(500);
        gamePane.getChildren().add(playbtn);
        playbtn.setOnAction(e->{
            if(selection!=null){gm = new GameManager();
            gm.createNewGame(menuStage, selection,mod1,mod2,mod3);gameStage.close();}
        });
        
        
        
        //Background
        Image backgroundImage = new Image ("/GameGraphics/background3.png",800, 600,false,true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        gamePane.setBackground (new Background(background));
        
        
        
    }
    private void ships()
    {
        //selection cirlces
        HBox box = new  HBox();
        box.setSpacing(60);
        box.setLayoutX(100);
        box.setLayoutY(70);
        ImageView c1=new ImageView(darkmoon);
        ImageView c2=new ImageView(darkmoon);
        ImageView c3=new ImageView(darkmoon);
        ImageView c4 = new ImageView(darkmoon);
        c1.setFitHeight(110);
        c1.setFitWidth(110);
        c2.setFitHeight(110);
        c2.setFitWidth(110);
        c3.setFitHeight(110);
        c3.setFitWidth(110);
        c4.setFitHeight(110);
        c4.setFitWidth(110);
        
        
        box.getChildren().addAll(c1 ,c2 ,c3 ,c4 );
        gamePane.getChildren().add(box);
        //change circles colours
        
        c2.setOnMouseClicked(e->{
            Image img1 = new Image(moon);
            c2.setImage(img1);selection = "/GameGraphics/Ship2.png";});
        c3.setOnMouseClicked(e->{
            Image img1 = new Image(moon);
            c3.setImage(img1);selection = "/GameGraphics/Ship3.png";});
        c4.setOnMouseClicked(e->{
            Image img1 = new Image(moon);
            c4.setImage(img1);selection = "/GameGraphics/Ship4.png";});
        //ships
        HBox box2 = new  HBox();
        box2.setSpacing(92);
        box2.setLayoutX(110);
        box2.setLayoutY(60);
        ImageView s1=new ImageView("/GameGraphics/Ship1.png")
                ,s2=new ImageView("/GameGraphics/Ship2.png")
                ,s3=new ImageView("/GameGraphics/Ship3.png")
                ,s4 = new ImageView("/GameGraphics/Ship4.png");
        s1.setFitWidth(80);
        s1.setFitHeight(120);
        s2.setFitWidth(80);
        s2.setFitHeight(120);
        s3.setFitWidth(80);
        s3.setFitHeight(120);
        s4.setFitWidth(80);
        s4.setFitHeight(120);
        box2.getChildren().addAll(s1 ,s2 ,s3 ,s4 );
        //Ship Selection
        gamePane.getChildren().add(box2);
        s1.setOnMousePressed
                (e->{Image img1 = new Image(moon);Image img2 = new Image(darkmoon);
                c1.setImage(img1);c2.setImage(img2);c3.setImage(img2);c4.setImage(img2);
                selection = "/GameGraphics/Ship1.png";});
        s2.setOnMousePressed
                (e->{Image img1 = new Image(moon);Image img2 = new Image(darkmoon);
                c1.setImage(img2);c2.setImage(img1);c3.setImage(img2);c4.setImage(img2);
                selection = "/GameGraphics/Ship2.png";});
        s3.setOnMousePressed
                (e->{Image img1 = new Image(moon);Image img2 = new Image(darkmoon);
                c1.setImage(img2);c2.setImage(img2);c3.setImage(img1);c4.setImage(img2);
                selection = "/GameGraphics/Ship3.png";});
        s4.setOnMousePressed
                (e->{Image img1 = new Image(moon);Image img2 = new Image(darkmoon);
                c1.setImage(img2);c2.setImage(img2);c3.setImage(img2);c4.setImage(img1);
                selection = "/GameGraphics/Ship4.png";});
        
    }
    
    private void Labels()
    {
        Label lb1 = new Label();
        lb1.setText("Select Your Ship");
        lb1.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 50));
        lb1.setTextFill(Color.GOLD);
        lb1.setLayoutX(180);
        lb1.setLayoutY(5);
        
        Label lb2 = new Label();
        lb2.setText("Select Mode");
        lb2.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 50));
        lb2.setTextFill(Color.GOLD);
        lb2.setLayoutX(220);
        lb2.setLayoutY(200);
        
        Label lb3 = new Label();
        lb3.setText("Rush");
        lb3.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        lb3.setTextFill(Color.GOLD);
        lb3.setLayoutX(350);
        lb3.setLayoutY(450);
        
        Label lb4 = new Label();
        lb4.setText("1 Minute");
        lb4.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        lb4.setTextFill(Color.GOLD);
        lb4.setLayoutX(550);
        lb4.setLayoutY(450);
        
        Label lb5 = new Label();
        lb5.setText("Classic");
        lb5.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        lb5.setTextFill(Color.GOLD);
        lb5.setLayoutX(100);
        lb5.setLayoutY(450);
        
        
        gamePane.getChildren().addAll(lb1,lb2,lb3,lb4,lb5);
        
    }
    
    
    private void gameModes()
    {
        ImageView m1=new ImageView(mode1dark);
        ImageView m2=new ImageView(mode2dark);
        ImageView m3=new ImageView(mode3dark);
        m1.setFitWidth(200);
        m1.setFitHeight(200);
        m2.setFitWidth(200);
        m2.setFitHeight(200);
        m3.setFitWidth(200);
        m3.setFitHeight(200);
        HBox b = new HBox();
        b.setSpacing(25);
        b.setLayoutX(70);
        b.setLayoutY(250);
        b.getChildren().addAll(m1,m2,m3 );
        gamePane.getChildren().add(b);
        m1.setOnMousePressed(e->{Image img1 = new Image(mode1);Image img2 = new Image(mode2dark);Image img3 = new Image(mode3dark);
        m1.setImage(img1);m2.setImage(img2);m3.setImage(img3);mod1=true;mod2=false;mod3=false;
        });
        
        m2.setOnMousePressed(e->{Image img1 = new Image(mode1dark);Image img2 = new Image(mode2);Image img3 = new Image(mode3dark);
        m1.setImage(img1);m2.setImage(img2);m3.setImage(img3);mod2=false;mod2=true;mod3=false;
        });
        
        m3.setOnMousePressed(e->{Image img1 = new Image(mode1dark);Image img2 = new Image(mode2dark);Image img3 = new Image(mode3);
        m1.setImage(img1);m2.setImage(img2);m3.setImage(img3);mod3=false;mod2=false;mod3=true;
        });
        
    }
    public void createNewGame(Stage menuStage){
        this.menuStage= menuStage;
        this.menuStage.hide();
        gameStage.show();
    }
    
    
}

