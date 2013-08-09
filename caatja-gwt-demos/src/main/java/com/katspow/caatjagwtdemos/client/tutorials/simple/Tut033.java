package com.katspow.caatjagwtdemos.client.tutorials.simple;

import com.katspow.caatja.behavior.RotateBehavior;
import com.katspow.caatja.behavior.ScaleBehavior;
import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;

/**
 * FIXME Seems broken
 * @author ahingsaka
 *
 */
public class Tut033 {

    public void init() throws Exception {

        Director _director_4 = new Director().initialize(600, 200, null);

        Scene _scene_4 = _director_4.createScene();

//        List<Anchor> anchor = Arrays.asList(Anchor.TOP_LEFT, Anchor.TOP, Anchor.TOP_RIGHT, Anchor.LEFT, Anchor.CENTER,
//                Anchor.RIGHT, Anchor.BOTTOM_LEFT, Anchor.BOTTOM, Anchor.BOTTOM_RIGHT);
        
        int[] anchor = new int[] {0,0,   50,0,   100,0,
                0,50,  50,50,  100,50,
                0,100, 50,100, 100,100};

        int i;

        for (i = 0; i < 9; i++) {

            // background actors under rotating ones. Just to have a reference
            // of where the anchor is.
            Actor _scene_4_rotating_actor_background = new Actor()
                    .setLocation(50 + 50 * (i % 3), 35 + 50 * ((i / 3) >> 0)).setSize(30, 30).setFillStyle("#ffffff")
                    .setStringStrokeStyle("#000000").
                    // do not accept mouse events.
                    enableEvents(false);
            _scene_4.addChild(_scene_4_rotating_actor_background);

            // rotating actors.
            Actor _scene_4_rotating_actor = new Actor()
                    .setLocation(50 + 50 * (i % 3), 35 + 50 * ((i / 3) >> 0)).setSize(30, 30).setFillStyle("#ff0000");

            // never ending rotating behavior
            RotateBehavior _scene_4_rotating_behavior = (RotateBehavior) new RotateBehavior().
                    setCycle(true)
                    .setFrameTime(0, 2000).
                    setValues(0, 2*Math.PI, (double) anchor[i*2], (double) anchor[i*2+1] );
            _scene_4_rotating_actor.addBehavior(_scene_4_rotating_behavior);
            _scene_4.addChild(_scene_4_rotating_actor);

            // scaling actors
            Actor _scene_4_scaling_actor = new Actor()
                    .setLocation(300 + 60 * (i % 3), 30 + 60 * ((i / 3) >> 0)).
                    setSize(30, 30).
                    setFillStyle("#ff00ff");
            // never ending scaling behavior
            ScaleBehavior _scene_4_scaling_behavior = (ScaleBehavior) new ScaleBehavior().
                    setCycle(true).
                    setFrameTime(0, 2000).
                    setValues(.5, 1.5, .5, 1.5,(double) anchor[i*2],(double) anchor[i*2+1]).
                    setPingPong();
            _scene_4_scaling_actor.addBehavior(_scene_4_scaling_behavior);
            _scene_4.addChild(_scene_4_scaling_actor);
        }

        Caatja.loop(20);

    }

}
