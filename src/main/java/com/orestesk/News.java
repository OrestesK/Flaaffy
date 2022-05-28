package com.orestesk;

import java.io.FileInputStream;
import java.util.List;

import com.orestesk.python.PythonRunner;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class News extends Template{
    public Menu menu;
    public News(Menu menu){
        this.menu = menu;
        setLoadingIcon();
        getNewsContent();
    }
    private void setLoadingIcon(){
        menu.disableClick(true);
        try{
        ImageView loadingIcon = new ImageView(
            new Image(
                new FileInputStream("src/main/resources/com/orestesk/misc/icons/loading.png")));
        HBox middle1 = new HBox();
        middle1.setAlignment(Pos.CENTER);
        middle1.getChildren().add(loadingIcon);
        
        menu.setContent(middle1);
        }
        catch(Exception e){}
    }
    private void getNewsContent(){
        PythonRunner pRunner = new PythonRunner(this);
        Thread multi = new Thread(pRunner);
        multi.start();
    }
    public void setContent(List<String> content){    
        VBox newsContents = new VBox(); 
        newsContents.setAlignment(Pos.TOP_CENTER);
        for(String text : content){
            Label label = new Label(text);
            label.setPrefWidth(100);
            label.setAlignment(Pos.CENTER);
            label.setWrapText(true);
            label.setFont(Font.font("Roboto Regular", 20));
            newsContents.getChildren().add(label);
        }        
        menu.setContent(newsContents);
        menu.disableClick(false);
    }
}
