package com.orestesk;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class PythonRunnerThread implements Runnable{
    private String[] args;
    private List<String> texts = new ArrayList<String>();
    private List<String> links = new ArrayList<String>();
    private List<ImageView> altContent = new ArrayList<ImageView>();
    private Template template;
    public PythonRunnerThread(Template template){
        this(template, new String[]{"","//*[@id=\"content\"]/main/div[1]/div[2]/p[2]"});
    }
    public PythonRunnerThread(Template template, String[] args){
        this.args = new String[]{"https://www.serebii.net/" + args[0],args[1]};
        this.template = template;
    }
    public void run() {
        getData();
        getImages();
        sendUpdate();
    }
    private void getData(){
        String s = null;
        try{
        Process p = new ProcessBuilder("python","src/main/java/com/orestesk/python/main.py", args[0], args[1]).start();
        p.waitFor();
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
        boolean joined = false;
        while ((s = stdInput.readLine()) != null) {
                if(s.substring(0, 5).equals("https")){
                    links.add(s);
                    joined = false;
                }
                else{
                    if(joined == true){
                        texts.set(texts.size() - 1, texts.get(texts.size() - 1) + s);
                    }
                    else{
                        texts.add(s);
                    }
                    texts.set(texts.size() - 1, texts.get(texts.size() - 1)
                    .replaceAll("([a-z])([A-Z])", "$1.\n $2")
                    .replaceAll("([1-9])([A-Z])", "$1.\n $2"));
                joined = true;
                }
        }
        }
        catch(Exception e){e.printStackTrace();}
    }

    private void getImages(){
        BufferedImage image = null;
        try {
            URL url = new URL(links.get(0));
            image = ImageIO.read(url);
            WritableImage wr = null;
        if (image != null) {
            wr = new WritableImage(image.getWidth(), image.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    pw.setArgb(x, y, image.getRGB(x, y));
                }
            }
            altContent.add(new ImageView(wr));
        }
        } catch (IOException e) {}
    }

    private void sendUpdate(){
        Platform.runLater(new Runnable(){
            public void run(){
            template.setContent(texts, altContent);}
        });
    }
}
