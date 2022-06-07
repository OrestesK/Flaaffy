package com.orestesk;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class LoadingThread implements Runnable{
    private News news;
    private boolean alive = true;
    private ImageView loadingIcon;
    public LoadingThread(News news, Pane pane){
        this.news = news;
        this.loadingIcon = (ImageView) ((VBox) (pane.getChildren().get(0))).getChildren().get(0);
        
    }
    public void run(){
        while(alive){
            Platform.runLater(new Runnable(){
                public void run(){
                spinLoading();
                }});
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }
    }
    public void spinLoading(){
        loadingIcon.setRotate(loadingIcon.getRotate() + 1);
    }
    public void kill(){
        alive = false;
    }

}
