package com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut04;

import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.ActorContainer;
import com.katspow.caatja.foundation.ui.TextActor;
import com.katspow.caatja.foundation.ui.TextFont;

public class Tut48 {

    public void start(CaatjaCanvas canvas) throws Exception {

        Director director = new Director().initialize(700, 400, canvas).setClear(false);
        Scene scene = director.createScene();
        
        ActorContainer root = new ActorContainer().setBounds(0, 0, director.width, director.height);
        scene.addChild(root);

        TextActor text = new TextActor().
                setFont(new TextFont(100, "px", "sans-serif")).
                setText("Rotate Device").
                setFillStyle("red").
                setOutlineColor("#ffff00").
                setOutline(true).
                calcTextSize(director);
                text.cacheAsBitmap();
        scene.addChild(text.setLocation((director.canvas.getCoordinateSpaceWidth() - text.width) / 2,
                (director.canvas.getCoordinateSpaceHeight() - text.height) / 2));

        // Hook on scene life cycle. After rendering, calculate an angle based on accelerometer
        // information for the text.
        // TODO No accelerometer
//        scene.onRenderEnd = function(director, time) {
//            var rx = rotationRate.gamma;
//
//            var ixy = -rx * Math.PI / 180;
//            text.setRotation(ixy);
//        };

        Caatja.loop(33);
    }

}
