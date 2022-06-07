package com.orestesk;

import java.util.List;

import javafx.scene.image.ImageView;

public abstract class Template {
    public abstract void setContent(List<String> content, List<ImageView> altContent);
}
