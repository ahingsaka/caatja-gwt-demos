package com.katspow.caatjagwtdemos.client.tutorials.simple;

import com.katspow.caatja.behavior.AlphaBehavior;
import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaFillStrokeStyle;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.ui.ShapeActor;

/**
 * A small red square outlined in black that fades.
 * 
 * @author ahingsaka
 *
 */
public class Tut021 {

    public void init() throws Exception {

        Director director_1 = new Director().initialize(100, 80, null);
        Scene scene_1 = director_1.createScene();
        ShapeActor rectangle_1 = (ShapeActor) new ShapeActor();
        
        rectangle_1.setShape(ShapeActor.Shape.RECTANGLE)
            .setLocation(10, 10)
            .setSize(60, 60)
            .setFillStrokeStyle(new CaatjaFillStrokeStyle("#ff0000"));
        
        rectangle_1.setStrokeStyle(new CaatjaFillStrokeStyle("#000000"));

        scene_1.addChild(rectangle_1);

        // setup a behaviour. take 5 seconds to change alpha transparency from 1
        // to .1
        // start fading when 2 seconds have passed from scene start time.
        AlphaBehavior alpha_1 = new AlphaBehavior().
                setValues(1,.1).
                setFrameTime( 2000, 5000 );

        rectangle_1.addBehavior(alpha_1);

        // set animation to 10fps.
        Caatja.loop(10);

    }
}
