package com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut06;

import com.katspow.caatja.behavior.AlphaBehavior;
import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.ui.ShapeActor;

public class Tut6 {
    
    public void start(CaatjaCanvas canvas) throws Exception {
        
        Director director = new Director().initialize(150, 80, canvas);
        Scene scene = director.createScene();
        
        // create a rectangle
        ShapeActor rectangle = new ShapeActor().
                setShape(ShapeActor.Shape.RECTANGLE).
                setLocation(10, 10).
                setSize(60, 60).
                setFillStyle("#ff0000").
                setStringStrokeStyle("#000000");

        // create a circle
        ShapeActor circle = new ShapeActor().
                setShape(ShapeActor.Shape.CIRCLE).
                setBounds(80, 10, 60, 60).
                setFillStyle("#00ff00").
                setStringStrokeStyle("#000000");

        scene.addChild(rectangle);
        scene.addChild(circle);

        // create an alpha transparency behavior, which takes two seconds to start,
        // and will be applied for five seconds. During this time, the trsnsparency
        // will fade from 1 (total opacity) to 0 (total transparency)
        AlphaBehavior alpha_2 = new AlphaBehavior().
                setValues(1, 0).

            // by cycling a behavior, upon expiration (on time = 7000 milliseconds),
            // it will start applying again from the beginning.
                setCycle(true).
                setFrameTime(2000, 5000);

        // set the behavior to the rectangle.
        rectangle.addBehavior(alpha_2);

        AlphaBehavior alpha_3 = new AlphaBehavior().
                setValues(1, 0).
                setFrameTime(2000, 5000);
        circle.addBehavior(alpha_3);

        Caatja.loop(10);
        
    }

}
