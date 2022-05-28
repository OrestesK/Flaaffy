package com.orestesk.python;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.orestesk.Template;

import javafx.application.Platform;

public class PythonRunner implements Runnable{
    private String[] args;
    private List<String> content = new ArrayList<String>();
    private Template template;
    public PythonRunner(Template template){
        this(template, new String[]{"","//*[@id=\"content\"]/main/div[1]/div[2]/p[2]"});
    }
    public PythonRunner(Template template, String[] args){
        this.args = new String[]{"https://www.serebii.net/" + args[0],args[1]};
        this.template = template;
    }
    public void run() {
        System.out.println("running");
        getData();
        sendUpdate();
    }
    private void getData(){
        String s = null;
        try{
        Process p = new ProcessBuilder("python","src/main/java/com/orestesk/python/main.py", args[0], args[1]).start();
        p.waitFor();
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String thing = "";
        while ((s = stdInput.readLine()) != null) {
            thing += s;
        }
        content.add(thing);
        }
        catch(Exception e){}
    }
    private void sendUpdate(){
        Platform.runLater(new Runnable(){
            public void run(){
            template.setContent(content);}
        });
    }
}
