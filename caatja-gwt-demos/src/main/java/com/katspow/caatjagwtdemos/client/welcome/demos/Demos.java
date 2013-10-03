package com.katspow.caatjagwtdemos.client.welcome.demos;

import com.katspow.caatja.core.canvas.CaatjaImage;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatjagwtdemos.client.welcome.HomeView.SimpleDemo;
import com.katspow.caatjagwtdemos.client.welcome.demos.simple.Demo15;

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

    public static void launch(SimpleDemo simpleDemo) {
        switch (simpleDemo) {
        case LOGO_FRENZY:
            //Demo15.start();
            break;
        case MASKING:
            break;
        case KEYBOARD:
            break;
        case QUADTREE_BASED_COLLISION:
            break;
        default:
            break;
        }
    }

}
