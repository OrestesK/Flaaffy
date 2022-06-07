package com.orestesk;

import java.util.List;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Settings extends Template{
    Menu menu;
    public Settings(Menu menu){
        this.menu = menu;
        menu.setIconClickDisabled(true);
        setContent(null, null);
    }
    @Override
    public void setContent(List<String> content, List<ImageView> altContent) {
        try {
            Pane settingsContents = (Pane) (new Pane(App.loadFXML("settings"))).getChildren().get(0);
            menu.setContent(settingsContents);
            menu.setIconClickDisabled(false);
        } catch (Exception e) {}
    }

    
    
    
}
