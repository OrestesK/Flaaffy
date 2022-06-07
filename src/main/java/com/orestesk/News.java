package com.orestesk;

import java.util.List;


import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class News extends Template{
    private Menu menu;
    private LoadingThread loadingThread; 
    public static int fontSize = 14;
    public News(Menu menu){
        this.menu = menu;
        setLoadingIcon();
        getNewsContent();
    }
    private void setLoadingIcon(){
        menu.setIconClickDisabled(true);
        Pane newsContents = null;
        try {
            newsContents = (Pane) (new Pane(App.loadFXML("loading"))).getChildren().get(0);
        } catch (Exception e) {e.printStackTrace();}
        menu.setContent(newsContents);
        loadingThread = new LoadingThread(this, newsContents);
        new Thread(loadingThread).start();
        menu.setIconClickDisabled(false);
    }
    private void getNewsContent(){
        new Thread(new PythonRunnerThread(this)).start();
    }
    @Override
    public void setContent(List<String> content, List<ImageView>  altContent){    
        VBox newsContents = new VBox(); 
        newsContents.setPrefWidth(685);
        newsContents.setSpacing(20);
        newsContents.setAlignment(Pos.TOP_CENTER);
        for(int i = 0; i < content.size(); i++){
            String text = " " + content.get(i);
            ImageView image = altContent.get(i);

            HBox alignWithPicture = new HBox();
            //alignWithPicture.setPrefWidth(685);
            //alignWithPicture.setAlignment(Pos.CENTER);

            Label label = new Label(text);
            label.setPrefWidth(700);
            label.setLineSpacing(3);
            //label.setAlignment(Pos.CENTER);
            label.setWrapText(true);
            label.setFont(Font.font("Roboto Regular", fontSize));

            alignWithPicture.getChildren().add(label);
            newsContents.getChildren().addAll(image, alignWithPicture);
        }
        loadingThread.kill();
        menu.setContent(newsContents);
        menu.getContentAnchor().requestFocus();
        menu.setIconClickDisabled(false);
    }
}
