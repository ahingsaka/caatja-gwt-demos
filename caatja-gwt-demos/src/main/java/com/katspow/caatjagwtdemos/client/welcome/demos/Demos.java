package com.katspow.caatjagwtdemos.client.welcome.demos;

import com.katspow.caatja.core.canvas.CaatjaImage;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatjagwtdemos.client.welcome.HomeView.SimpleDemo;

public class Demos {
    
    private static Director director;
    private static DemosScene demosScene;
    private static boolean started;

    public static void start(Director director) throws Exception {
        if (!started) {
            Demos.director = director;
            demosScene = new DemosScene(director);
            director.addScene(demosScene);
            started = true;
        }
        
        director.setScene(demosScene);
        preview(null);
    }

    public static void preview(SimpleDemo simpleDemo) {
        if (simpleDemo != null) {
            CaatjaImage previewImage = director.getImage(simpleDemo.getImg());
            demosScene.setBackgroundImage(previewImage);
        } else {
            demosScene.setBackgroundImage(null);
        }
    }

}
