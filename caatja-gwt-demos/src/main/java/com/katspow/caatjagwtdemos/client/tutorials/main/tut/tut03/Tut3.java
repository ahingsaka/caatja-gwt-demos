package com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut03;

import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.core.canvas.CaatjaGradient;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.ActorContainer;
import com.katspow.caatja.foundation.ui.ShapeActor;

public class Tut3 {
    
    public void start(CaatjaCanvas canvas) throws Exception {
        
        Director _director_9 = new Director().initialize(400, 200, canvas);

        // create a vertical gradient to apply to text.
        CaatjaGradient gradient= _director_9.ctx.createLinearGradient(0,0,0,_director_9.canvas.getCoordinateSpaceHeight());
        gradient.addColorStop(0,"#ffff00");
        gradient.addColorStop(0.5,"#00ffff");
        gradient.addColorStop(1,"blue");

        Scene _scene_9= _director_9.createScene().
                setFillStrokeStyle(gradient);

        _director_9.addScene( _scene_9 );

        ActorContainer _c9_c0= new ActorContainer().
                setBounds( 10,10, 180,180 ).
                setFillStyle( "red" ).
                setAlpha(.25);

        ShapeActor _c9_child0= new ShapeActor().
                setShape( ShapeActor.Shape.CIRCLE ).
                setFillStyle( "green" ).
                setBounds( 20, 20, 140, 140 );

        _c9_c0.addChild( _c9_child0 );

        ActorContainer _c9_c1= new ActorContainer().
                setBounds( 210,10, 180,180 ).
                setFillStyle( "red" ).
                setAlpha(.25).
                setGlobalAlpha(true);

        ShapeActor _c9_child1= new ShapeActor().
                setShape( ShapeActor.Shape.CIRCLE ).
                setFillStyle( "green" ).
                setBounds( 20, 20, 140, 140 );

        _c9_c1.addChild( _c9_child1 );

        _scene_9.addChild( _c9_c0 );
        _scene_9.addChild( _c9_c1 );

        Caatja.loop(1);
        
    }

}
