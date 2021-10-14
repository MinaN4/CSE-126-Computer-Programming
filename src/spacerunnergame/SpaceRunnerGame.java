package spacerunnergame;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;

public class SpaceRunnerGame extends Application
{

    public static void main(String[] args)
    {
         launch (args);
    }

    @Override
    public void start(Stage primaryStage) 
    {
        ViewManager manager = new ViewManager();
        Sound muisc = new Sound();
        muisc.start();     
        primaryStage = manager.getMainStage();
        primaryStage.setTitle("Space Runner");
        primaryStage.show();
            
          
    }
    
}
