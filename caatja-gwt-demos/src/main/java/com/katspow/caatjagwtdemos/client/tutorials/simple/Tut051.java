package com.katspow.caatjagwtdemos.client.tutorials.simple;

import com.katspow.caatja.behavior.Interpolator;
import com.katspow.caatja.behavior.PathBehavior;
import com.katspow.caatja.behavior.RotateBehavior;
import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.ui.PathActor;
import com.katspow.caatja.foundation.ui.ShapeActor;
import com.katspow.caatja.pathutil.Path;

public class Tut051 {
    
    public void init() throws Exception  {
        Director director_6 = new Director().initialize(
                300,250);

        Scene scene_6= director_6.createScene();

        // create an almost circular path.
        Path _path_c6= new Path().
            beginPath(25,125).
            addCubicTo( 25,25,   225,25,   225,125, null ).
            addCubicTo( 225,225,  25,225,  25,125, null ).
            endPath().
            setInteractive(false);

        ShapeActor shape_c6_0= new ShapeActor().
                setLocation( 50,50 ).
                setSize(50,50).
                setFillStyle("#ffff00").
                setStringStrokeStyle("#0000ff").
                setAlpha(.75).
                addBehavior(
                    new PathBehavior().
                            setPath( _path_c6 ).
                            setFrameTime(0,5000).
                            setCycle(true).
                            setAutoRotate( true ).
                            setTranslation( 25,25 )

                );
        ShapeActor shape_c6_1= new ShapeActor().
                setLocation( 50,50 ).
                setSize(50,50).
                setFillStyle("#00ff00").
                setStringStrokeStyle("#ff00ff").
                setAlpha(.75).
                addBehavior(
                    new PathBehavior().
                            setPath( _path_c6 ).
                            setFrameTime(0,5000).
                            setCycle(true).
                            setTranslation(25,25).
                            setInterpolator(
                                Interpolator.createLinearInterpolator(
                                        false,true)
                            )
                ).
                addBehavior(
                    new RotateBehavior().
                            setValues( 0, 2*Math.PI ).
                            setCycle(true).
                            setFrameTime(0,5000)
                );

        // add two rectangle shapes to the scene.
        scene_6.addChild(shape_c6_0);
        scene_6.addChild(shape_c6_1);

        PathActor _pathactor_c6= new PathActor().
                setPath(_path_c6).
                setBounds(0,0,director_6.width,director_6.height);
        scene_6.addChild( _pathactor_c6 );

        // setup up a path traverser for the path.
        Caatja.loop(30);
    }

}
