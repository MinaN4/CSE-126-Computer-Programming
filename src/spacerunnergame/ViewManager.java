package spacerunnergame;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public final class ViewManager
{
    private final Pane mainPain;
    private final Scene mainScene;
    private Stage mainStage;
    private subScene scoresubscene;
    private subScene startsubscene;
    private subScene chooseshipsubscene;
    List<ButtonsController> menuButtons;
    
    public ViewManager()
    {
        menuButtons = new ArrayList<>();
        mainPain = new Pane();
        mainScene = new Scene(mainPain,800, 550);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        mainStage.setResizable(false);
        
        
        createButtons();
        createBackground();
        createLogo();
        createSubscne();
      
        
    }
    
    
    public Stage getMainStage()
    {
        return mainStage;
    } 
    
    private void createButtons()
    {
       startButton();
       scoresButton();
       exitButton();
    }
    
    private void startButton()
    {
        ButtonsController startButton = new ButtonsController("PLAY");
        addMenuButtons(startButton);
        
        startButton.setOnAction(e->{
            SelectShip select = new SelectShip();
            select.createNewGame(mainStage);
        });
        
        
    }
    
    private void scoresButton()
    {
        ButtonsController scoresButton = new ButtonsController("SCORES");
        addMenuButtons(scoresButton);
        scoresButton.setOnMouseEntered(e->{createSubscne();mainPain.getChildren().add(scoresubscene); });
        scoresButton.setOnMouseExited(e->{mainPain.getChildren().remove(scoresubscene); });
        
       
    }
    
    private void exitButton()
    {
        ButtonsController exitButton = new ButtonsController("EXIT");
        addMenuButtons(exitButton);
        
        exitButton.setOnAction((ActionEvent e) -> {
            mainStage.close();
        });
    }
    
    private void createBackground()
    {
        Image backgroundImage = new Image ("/GameGraphics/backgroundselect.png",800, 550,false,true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mainPain.setBackground (new Background(background));
    }
    
    private void addMenuButtons(ButtonsController button)
    {
        button.setLayoutX(50);
        button.setLayoutY(250 + menuButtons.size() *100);
        menuButtons.add(button);
        mainPain.getChildren().add(button);
    }
    public void createLogo()
    {
        ImageView logo =new ImageView("/GameGraphics/space-invaders-logo.png");
        logo.setFitWidth(350);
        logo.setFitHeight(240);
        logo.setLayoutX(350);
        logo.setLayoutY(10);
        mainPain.getChildren().add(logo);
    }
    public void createSubscne()
    {
        scoresubscene = new subScene();
        scoresubscene.setLayoutX(300-scoresubscene.getLayoutX());
        scoresubscene.setLayoutY(250-scoresubscene.getLayoutY());
      
       
      
        
       
    }
    public void createNewGame(Stage menuStage){
        this.mainStage= menuStage;
        
        
        this.mainStage.hide();
        mainStage.show();
    }
    
}