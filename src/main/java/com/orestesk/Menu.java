package com.orestesk;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class Menu {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private AnchorPane contentAnchor;
    @FXML 
    private Pane originalPane;
    @FXML
    private HBox iconHolder;
    private Pane menuContents;
    public void initialize(){
        menuContents = originalPane;
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }
    @FXML
    private void switchToMenu(){
        setIconClickDisabled(true);
        setContent(menuContents);
        setIconClickDisabled(false);
    }
    @FXML
    private void switchToNews(){
        new News(this);
    }
    @FXML
    private void switchToSettings(){
        new Settings(this);
    }
    public void setContent(Node content){
        contentAnchor.getChildren().setAll(content);
    }
    public AnchorPane getContentAnchor(){
        return contentAnchor;
    }
    public void setIconClickDisabled(boolean val){
        for(Node child: iconHolder.getChildren()){
            child.setMouseTransparent(val);
        }
    }
}
