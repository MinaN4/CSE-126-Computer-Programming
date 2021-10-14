package spacerunnergame;

import javafx.scene.control.Button;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class ButtonsController extends Button
{
    
    private final String fontSrc = "/spacerunnergame/kenvector_future.ttf";
    private final String buttonStyle = "-fx-background-color: transparent; -fx-background-image: url('/GameGraphics/yellowButton1.png');";
    private final String buttonReleased = "-fx-background-color: transparent; -fx-background-image: url('/GameGraphics/yellowButton2.png');";
    
    public ButtonsController(String text) 
    {
        setText(text);
        setButtonFont();
        setPrefWidth(190);
        setPrefHeight(49);
        setStyle(buttonStyle);
        buttonListeners();
    }
    private void setButtonFont()
    {
        try
        {
            setFont(Font.loadFont(new FileInputStream(fontSrc), 23));
        }
        catch(FileNotFoundException e)
        {
            setFont(Font.font("Arial",26));
        }
    }
    private void setButtonPressedStyle()
    {
        setStyle(buttonStyle);
        setPrefHeight(45);
        setLayoutY(getLayoutY() + 4);
    }
    
    private void setButtonReleasedStyle()
    {
        setStyle(buttonStyle);
        setPrefHeight(49);
        setLayoutY(getLayoutY() - 4);
    }
    
    private void buttonListeners()
    {
        setOnMousePressed((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY))
            {
                setButtonPressedStyle();
            }
        });
        
        setOnMouseReleased((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY))
            {
                setButtonReleasedStyle();
            }
        });
    }   
}