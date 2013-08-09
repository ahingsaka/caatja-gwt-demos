package com.katspow.caatjagwtdemos.client.tutorials.simple;

import com.katspow.caatja.behavior.ContainerBehavior;
import com.katspow.caatja.behavior.RotateBehavior;
import com.katspow.caatja.behavior.ScaleBehavior;
import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaFillStrokeStyle;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.ui.ShapeActor;

/**
 * A small yellow square outlined in black that scales and rotates (loop).
 * 
 * @author ahingsaka
 *
 */
public class Tut023 {

    public void init() throws Exception {

        Director director_5 = new Director().initialize(150,150,null);
        Scene scene_5=     director_5.createScene();

        ShapeActor shape_5 = (ShapeActor) new ShapeActor();
        
        shape_5.setShape(ShapeActor.Shape.RECTANGLE)
            .setLocation(50, 50)
            .setSize(50, 50)
            .setFillStrokeStyle(new CaatjaFillStrokeStyle("#ffff00"));
        
        shape_5.setStrokeStyle(new CaatjaFillStrokeStyle("#000000"));
        scene_5.addChild(shape_5);

        // set a Container for behaviors up.
        ContainerBehavior _cb_c5= new ContainerBehavior().
                setCycle(true).
                // take 3 seconds to perform contained behaviors. If any
                // takes more than such time, it will be truncated.
                setFrameTime(0,3000);

            // setup an Scaling behavior. Min scale 1, Max scale 2 (twice in size)
        ScaleBehavior _sb_c5= new ScaleBehavior().
                setPingPong().
                setValues(1,2,1,2, .5,.5).
                // takes 2 seconds to scale. time measured from parent's
                // zero time.
                setFrameTime(0,2000);

            // setup a Rotating behavior. 0-2PI, ie 360 degrees.
            RotateBehavior _rb_c5= new RotateBehavior().
                    setValues( 0, Math.PI, .5,.5 ).
                    // takes 1 second, starting half a second after parent's
                    // time.
                    setFrameTime(500,1000);

            // add scale and rotation to the rectangle.
            _cb_c5.addBehavior(_sb_c5);
            _cb_c5.addBehavior(_rb_c5);

        // add path and conpound behavior of rotation and scale to the rectanble shape.
        shape_5.addBehavior(_cb_c5);

        Caatja.loop(30);

    }
}
