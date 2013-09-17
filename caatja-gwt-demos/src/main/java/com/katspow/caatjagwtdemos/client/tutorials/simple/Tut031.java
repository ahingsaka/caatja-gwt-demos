package com.katspow.caatjagwtdemos.client.tutorials.simple;

import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaFillStrokeStyle;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;
import com.katspow.caatja.foundation.actor.Actor.EventType;
import com.katspow.caatja.foundation.actor.ActorListener;

/**
 * A green square that disappears and reappears.
 * 
 * @author ahingsaka
 *
 */
public class Tut031 {
    
    public void init() throws Exception {
        
     // Initialize director.
        Director _director_2= new Director().initialize(
                400,
                100,
                null);

        // create scene.
        Scene _scene_2=    _director_2.createScene();

        _director_2.addScene(_scene_2);

        // create a simple actor. will last for two seconds on Scene.
        Actor _pulsating_actor_2= new Actor().
                setBounds(10,10,80,80).
                setFillStrokeStyle(new CaatjaFillStrokeStyle("#00ff00")).
                setFrameTime(0,2000);

        // add a life cycle listener to the actor.
        _pulsating_actor_2.addListener(new ActorListener() {
            @Override
            public void actorLifeCycleEvent(Actor actor, EventType eventType, double time) {
                // on expiration notification,
                if (eventType == EventType.EXPIRED) {
                    // just make the Actor sleep for 1 second.
                    // After waking up, last for 2 seconds.
                    actor.setFrameTime( time+1000, 2000 );
                }
            }
        });

        // don't forget to add the actor to the scene.
        _scene_2.addChild( _pulsating_actor_2 );

        // set 20 fps animation
        Caatja.loop(20);
        
    }

}
