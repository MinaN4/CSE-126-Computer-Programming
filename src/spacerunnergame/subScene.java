package spacerunnergame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class subScene extends SubScene {
    
    private Pane p;
    private String txt;
    
    
    
    public subScene()
    {
        super(new Pane(),450,250);
        BackgroundImage img = new BackgroundImage(new Image("/GameGraphics/background2.png",450,250,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        p = (Pane) this.getRoot();
        p.setBackground(new Background(img));
        Label lb = new Label("Best Scores");
        lb.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        lb.setLayoutX(150);
        lb.setLayoutY(10);
        addScores();
        p.getChildren().add(lb);
    }
    
    public void addText(String txt, int i)
    {
        this.txt = txt;
        Label lb = new Label(txt);
        lb.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        if(i%2==0){
            lb.setLayoutX(40);
            lb.setLayoutY(30+i*12);
        }
        else{
            lb.setLayoutX(200);
            lb.setLayoutY(30+(i-1)*12);
        }
        p.getChildren().add(lb);
        
    }
    private void addScores()
    {
        
        File highScores = new File("bestScores.csv");
        try {
            BufferedReader br = new BufferedReader(new FileReader(highScores));
            String scores = br.readLine();
            String[] split = scores.split(",");
            
            
            for (int i =0; i<=10 && i<split.length ;i++ )
            {
                if(i%2 != 0)
                {
                    addText(split[i],i);
                    
                }
                else
                    addText(split[i],i);
                
            }
        } catch (FileNotFoundException ex) {
            
        } catch (IOException ex) {
            Logger.getLogger(subScene.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
