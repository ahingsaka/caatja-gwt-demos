package com.katspow.caatjagwtdemos.client.tutorials.simple;

import com.katspow.caatja.behavior.Interpolator;
import com.katspow.caatja.behavior.PathBehavior;
import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.ui.PathActor;
import com.katspow.caatja.foundation.ui.ShapeActor;
import com.katspow.caatja.pathutil.Path;

/**
 * 2 squares that follows a path.
 * 
 * @author ahingsaka
 *
 */
public class Tut024 {

    public void init() throws Exception {
        Director director_6 = new Director().initialize(
                250,250, null);
        Scene scene_6=    director_6.createScene();

        ShapeActor shape_c6_0= (ShapeActor) new ShapeActor().
                setShape( ShapeActor.Shape.RECTANGLE ).
                setLocation( 50,50 ).
                setSize(50,50).
                setFillStyle("#ffff00").
                setStringStrokeStyle("#0000ff").
                setAlpha(.75);
        ShapeActor shape_c6_1= (ShapeActor) new ShapeActor().
                setShape( ShapeActor.Shape.RECTANGLE ).
                setLocation( 50,50 ).
                setSize(50,50).
                setFillStyle("#00ff00").
                setStringStrokeStyle("#ff00ff").
                setAlpha(.75);

        // add two rectangle shapes to the scene.
        scene_6.addChild(shape_c6_0);
        scene_6.addChild(shape_c6_1);

        // create a circular path.
        Path _path_c6= new Path().
                beginPath(25,125).
                addCubicTo( 25d,25d,   225d,25d,   225d,125d, null ).
                addCubicTo( 225,225,  25,225,  25,125, null ).
                endPath();

        PathActor _pathactor_c6= (PathActor) new PathActor().
                setPath(_path_c6).
                setBounds(0,0,director_6.width,director_6.height);
        scene_6.addChild( _pathactor_c6 );

        // setup up a path traverser for the path.
        PathBehavior _pathbehavior_c6_0= new PathBehavior().
                setPath( _path_c6 ).
                setFrameTime(0,5000).
                setCycle(true).
                setAutoRotate( true ).
                // set path traverse by the center of the rectangle shape.
                setTranslation(
                    shape_c6_0.width/2,
                    shape_c6_0.height/2);

        // setup up an inverse path traverser for the path.
        // ie, traverse from final path position to start position.
        PathBehavior _pathbehavior_c6_1= new PathBehavior().
                setPath( _path_c6 ).
                setFrameTime(0,5000).
                setCycle(true).
                setInterpolator(
                    new Interpolator().createLinearInterpolator(
                            false,  // no pingpong.
                            true    // traverse the path inversely, from end to beginning.
                            ) );

        shape_c6_0.addBehavior( _pathbehavior_c6_0 );
        shape_c6_1.addBehavior( _pathbehavior_c6_1 );

        Caatja.loop(30);
    }

}
