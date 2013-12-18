package com.katspow.caatjagwtdemos.client.tutorials.simple;

import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.event.CAATMouseEvent;
import com.katspow.caatja.event.MouseListener;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;

/**
 * Shows some transformations : rotation, scale
 * @author ahingsaka
 *
 */
public class Tut032 {
    
    public void init() throws Exception {
     // Initialize director.
        Director _director_3= new Director().initialize(
                400,
                120,
                null);

        // create scene.
        Scene _scene_3=     _director_3.createScene();

        // create a simple actor. no transformations.
        Actor _pulsating_actor_3_0= new Actor().
                setBounds(10,20,80,80).
                setFillStyle("#00ff00");
        _pulsating_actor_3_0.name= "no transformation";
        
        // rotated 30 degrees
        Actor _pulsating_actor_3_1= new Actor().
                setBounds(120,20,80,80).
                setFillStyle("#00ff00").
                setRotation( Math.PI/6 );
        _pulsating_actor_3_1.name= "rotated 30 degrees";

        // half in width
        Actor _pulsating_actor_3_2= new Actor().
                setBounds(200,20,80,80).
                setFillStyle("#00ff00").
                setScale( .5, 1 );
        _pulsating_actor_3_2.name= "scaled(.5,1)";

        // 125% width, half height
        Actor _pulsating_actor_3_3= new Actor().
                setBounds(300,20,80,80).
                setFillStyle("#00ff00").
                setScale( 1.2, .4 );
        _pulsating_actor_3_3.name= "scaled(1.25,.5)";

        // from example 1
//        var mouseMoveHandler= function(mouseEvent) {
            // get the scene Actor the event was generated for.
//            var actor= mouseEvent.source;

            // show some event info:
//            document.getElementById('_c3_coords').innerHTML=
//                    "<b>Actor:</b>"+ actor.name+" "+
//                    "<b>Local Coord:</b> ("+
//                        // with all this stuff i'm just stripping
//                        // off any decimal beyond .99
//                        ((mouseEvent.point.x*100)>>0)/100+", "+
//                        ((mouseEvent.point.y*100)>>0)/100+") "+
//                    "<b>Screen Coord:</b> ("+
//                        mouseEvent.screenPoint.x+", "+
//                        mouseEvent.screenPoint.y+") ";
//        };

        // change default mouse handler to report coordinates.
        MouseListener mouseMoveListener = new MouseMoveHandler();
        _pulsating_actor_3_0.setMouseMoveListener(mouseMoveListener);
        _pulsating_actor_3_1.setMouseMoveListener(mouseMoveListener);
        _pulsating_actor_3_2.setMouseMoveListener(mouseMoveListener);
        _pulsating_actor_3_3.setMouseMoveListener(mouseMoveListener);

        // don't forget to actors to the scene.
        _scene_3.addChild(_pulsating_actor_3_0);
        _scene_3.addChild(_pulsating_actor_3_1);
        _scene_3.addChild(_pulsating_actor_3_2);
        _scene_3.addChild(_pulsating_actor_3_3);

        // set 20 fps animation
        Caatja.loop(20);
    }
    
    class MouseMoveHandler implements MouseListener {

        @Override
        public void call(CAATMouseEvent e) throws Exception {
            Actor actor = e.source;
            
            
        }
        
    }

}
