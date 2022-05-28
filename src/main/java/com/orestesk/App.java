package com.orestesk;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {  
    public static STATE state = STATE.MENU;
    public static VBox content;
    private static Scene scene;
    private double xOffset = 0;
    private double yOffset = 0;

    public enum STATE{
        MENU,SETTINGS,NEWS;
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("menu"), 700, 500);
        setDraggable(stage, scene, true);
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
    
    private void setDraggable(Stage stage, Scene scene, boolean val){
        scene.setOnMousePressed(val ? new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        } : null);
        scene.setOnMouseDragged(val ? new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        } : null);
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}