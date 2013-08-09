package com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut02;

import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;
import com.katspow.caatja.foundation.actor.ActorListener;

public class Tut22 {

    public void start(CaatjaCanvas canvas) throws Exception {
        Director director = new Director().initialize(400, 100, canvas);
        Scene _scene_2 = director.createScene();

        // create a simple actor. will last for two seconds on Scene.
        Actor _pulsating_actor_2 = new Actor().setBounds(10, 10, 80, 80).setFillStyle("#00ff00").setFrameTime(0, 2000);

        // add a life cycle listener to the actor.
        _pulsating_actor_2.addListener(new ActorListener() {
            @Override
            public void actorLifeCycleEvent(Actor actor, String eventType, double time) {
                if (eventType.equals("expired")) {
                    // just make the Actor sleep for 1 second.
                    // After waking up, last for 2 seconds.
                    actor.setFrameTime(time + 1000, 2000);
                }
            }
        });

        // don"t forget to add the actor to the scene.
        _scene_2.addChild(_pulsating_actor_2);

        // set 20 fps animation
        Caatja.loop(20);
    }

}
