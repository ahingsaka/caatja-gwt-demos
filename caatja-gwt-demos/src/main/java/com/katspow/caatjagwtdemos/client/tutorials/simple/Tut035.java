package com.katspow.caatjagwtdemos.client.tutorials.simple;

import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaGradient;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.ActorContainer;
import com.katspow.caatja.foundation.ui.ShapeActor;

/**
 * Shows some color effects.
 * 
 * @author ahingsaka
 *
 */
public class Tut035 {
    
    public void init() throws Exception {
        
        Director _director_9= new Director().initialize(
                400,
                200,
                null);

        // create a vertical gradient to apply to text.
        CaatjaGradient gradient= _director_9.ctx.createLinearGradient(0,0,0,_director_9.canvas.getCoordinateSpaceHeight());
        gradient.addColorStop(0,"#ffff00");
        gradient.addColorStop(0.5,"#00ffff");
        gradient.addColorStop(1,"blue");

        Scene _scene_9= _director_9.createScene().
                setFillStrokeStyle(gradient);

        _director_9.addScene( _scene_9 );

        ActorContainer _c9_c0= (ActorContainer) new ActorContainer().
                setBounds( 10,10, 180,180 ).
                setFillStyle( "red" ).
                setAlpha(.25);

        ShapeActor _c9_child0= (ShapeActor) new ShapeActor().
                setShape( ShapeActor.Shape.CIRCLE ).
                setFillStyle( "green" ).
                setBounds( 20, 20, 140, 140 );

        _c9_c0.addChild( _c9_child0 );

        ActorContainer _c9_c1= (ActorContainer) new ActorContainer().
                setBounds( 210,10, 180,180 ).
                setFillStyle( "red" ).
                setAlpha(.25).
                setGlobalAlpha(true);

        ShapeActor _c9_child1= (ShapeActor) new ShapeActor().
                setShape( ShapeActor.Shape.CIRCLE ).
                setFillStyle( "green" ).
                setBounds( 20, 20, 140, 140 );

        _c9_c1.addChild( _c9_child1 );
        
        _scene_9.addChild( _c9_c0 );
        _scene_9.addChild( _c9_c1 );

        Caatja.loop(1);
        
    }

}
