package com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut06;

import com.katspow.caatja.behavior.ContainerBehavior;
import com.katspow.caatja.behavior.RotateBehavior;
import com.katspow.caatja.behavior.ScaleBehavior;
import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.ui.ShapeActor;

public class Tut63 {
    
    public void start(CaatjaCanvas canvas) throws Exception {
        
        Director director = new Director().initialize(150, 150, canvas);
        Scene scene = director.createScene();
        
        ShapeActor shape = new ShapeActor().
                setShape(ShapeActor.Shape.RECTANGLE).
                setLocation(50, 50).
                setSize(50, 50).
                setFillStyle("#ff0").
                setStringStrokeStyle("#000");
        scene.addChild(shape);

        // set a Container for behaviors up.
        ContainerBehavior cb = new ContainerBehavior().
                setCycle(true).
            // take 3 seconds to perform contained behaviors. If any
            // takes more than such time, it will be truncated.
                setFrameTime(0, 3000);

        // setup an Scaling behavior. Min scale 1, Max scale 2
        // (twice in size)
        ScaleBehavior sb = new ScaleBehavior().
                setPingPong().
                setValues(1d, 2d, 1d, 2d, .50, .50).
            // takes 2 seconds to scale. time measured from parent"s
            // zero time.
                setFrameTime(0, 2000);

        // setup a Rotating behavior. 0-2PI, ie 360 degrees.
        RotateBehavior rb = new RotateBehavior().
                setValues(0d, Math.PI, .50, .50).
            // takes 1 second, starting half a second after parent"s
            // time.
                setFrameTime(500, 1000);

        // add scale and rotation to the rectangle.
        cb.addBehavior(sb);
        cb.addBehavior(rb);

        // add path and conpound behavior of rotation and scale to
        // the rectanble shape.
        shape.addBehavior(cb);

        Caatja.loop(30);
        
    }

}
