package com.orestesk;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class Menu {
    private Pane menuContents;
    @FXML
    private AnchorPane contentAnchor;
    @FXML 
    private Pane originalPane;
    @FXML
    private HBox iconHolder;

    public void initialize(){
        menuContents = originalPane;
    }
    @FXML
    private void switchToMenu(){
        disableClick(true);
        setContent(menuContents);
        disableClick(false);
    }
    @FXML
    private void switchToNews(){
        new News(this);
    }
    @FXML
    private void switchToSettings(){
        disableClick(true);
        contentAnchor.getChildren().clear();
        disableClick(false);
    }

    public void setContent(Node content){
        contentAnchor.getChildren().setAll(content);
    }

    public void disableClick(boolean val){
        for(Node child: iconHolder.getChildren()){
            child.setMouseTransparent(val);
        }
    }
}
