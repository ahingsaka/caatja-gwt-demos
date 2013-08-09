package com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut02;

import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;

public class Tut23 {
    
    public void start(CaatjaCanvas canvas) throws Exception {
        Director director = new Director().initialize(400, 120, canvas);
        Scene _scene_3 = director.createScene();
        
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
                setScale( 1.8, .25 );
        _pulsating_actor_3_3.name= "scaled(1.8,.25)";

//        // from example 1
//        var mouseMoveHandler= function(mouseEvent) {
//            // get the scene Actor the event was generated for.
//            var actor= mouseEvent.source;
//
//            // show some event info:
//            document.getElementById("_c3_coords").innerHTML=
//                    "<b>Actor:</b>"+ actor.name+" "+
//                    "<b>Local Coord:</b> ("+
//                        // with all this stuff i"m just stripping
//                        // off any decimal beyond .99
//                        ((mouseEvent.point.x*100)>>0)/100+", "+
//                        ((mouseEvent.point.y*100)>>0)/100+") "+
//                    "<b>Screen Coord:</b> ("+
//                        mouseEvent.screenPoint.x+", "+
//                        mouseEvent.screenPoint.y+") ";
//        };
//
//        // change default mouse handler to report coordinates.
//        _pulsating_actor_3_0.mouseMove= mouseMoveHandler;
//        _pulsating_actor_3_1.mouseMove= mouseMoveHandler;
//        _pulsating_actor_3_2.mouseMove= mouseMoveHandler;
//        _pulsating_actor_3_3.mouseMove= mouseMoveHandler;

        // don"t forget to actors to the scene.
        _scene_3.addChild( _pulsating_actor_3_0 );
        _scene_3.addChild( _pulsating_actor_3_1 );
        _scene_3.addChild( _pulsating_actor_3_2 );
        _scene_3.addChild( _pulsating_actor_3_3 );

        // set 20 fps animation
        Caatja.loop(20);
    }

}
