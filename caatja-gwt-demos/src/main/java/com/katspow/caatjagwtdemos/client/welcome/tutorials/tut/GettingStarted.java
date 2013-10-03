package com.katspow.caatjagwtdemos.client.welcome.tutorials.tut;

import com.katspow.caatja.core.canvas.CaatjaColor;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.ui.ShapeActor;
import com.katspow.caatja.foundation.ui.TextActor;

public class GettingStarted extends Scene {
    
    private static boolean started;
    
    private static GettingStarted instance;

    private GettingStarted() {
    }
    
    public static void launch(Director director) throws Exception {
        if (!started) {
            director.addScene(getInstance());
            getInstance().setFillStyle("#ffffff");
            
            TextActor intro = new TextActor().
                    setFont("12px sans-serif").
                    setTextBaseline("top").
                    setText("CAATJA uses Canvas as rendering engine. (CSS and WebGL are not supported at the moment) ").
                    calcTextSize(director).
                    setTextFillStyle("black");
            
            intro.setLocation(5, 5);
            
            TextActor installation = new TextActor().
                    setFont("12px sans-serif").
                    setTextBaseline("top").
                    setText("CAATJA uses Maven, you just have to include the dependency in the pom.xml : ").
                    calcTextSize(director).
                    setTextFillStyle("black");
            
            installation.setLocation(5, 20);
            
            
            ShapeActor circle = new ShapeActor()
                .setLocation(20, 40)
                .setSize(60, 60)
                .setFillStrokeStyle(CaatjaColor.valueOf("#ff0000"));
    
            circle.setStrokeStyle(CaatjaColor.valueOf("#000000"));
            
            getInstance().addChild(intro);
            getInstance().addChild(installation);
            getInstance().addChild(circle);
            
        }
        
        director.setScene(getInstance());
    }
    
    public static GettingStarted getInstance() {
        
        if (instance == null) {
            instance = new GettingStarted();
        }
        
        return instance;
    }

}
