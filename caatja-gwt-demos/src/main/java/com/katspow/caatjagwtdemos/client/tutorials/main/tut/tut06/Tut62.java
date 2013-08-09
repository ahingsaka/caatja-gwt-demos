package com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut06;

import com.katspow.caatja.behavior.PathBehavior;
import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.ui.PathActor;
import com.katspow.caatja.foundation.ui.StarActor;
import com.katspow.caatja.pathutil.Path;

public class Tut62 {
    
    public void start(CaatjaCanvas canvas) throws Exception {
        
        Director director = new Director().initialize(800, 600, canvas);
        Scene scene = director.createScene().setFillStyle("#fff");

        StarActor shape= new StarActor().
                initialize( 8, 30, 10 ).
                setLocation( 50,50 ).
                setSize(50,50).
                setFillStyle("#00f").
                setStringStrokeStyle("#0f0").
                setOutlined(true).
                setAlpha(.75);

        // add two rectangle shapes to the scene.
        scene.addChild(shape);

        // create a circular path.
        Path path= new Path().
                beginPath(25,125).
                addCubicTo( 25,25,   225,25,   225,125 ).
                addCubicTo( 225,225,  25,225,  25,125 ).
                endPath();

        // add an actor to show the path.
        PathActor path_actor= new PathActor().
                setPath(path).
                setBounds(0,0,director.width,director.height);
        scene.addChild( path_actor );

        // setup up a path traverser for the path.
        PathBehavior path_behavior= new PathBehavior().
                setPath( path ).
            // take 5 seconds to traverse the path
                setFrameTime(0,5000).
            // do it continuously, not just one time
                setCycle(true).
            // head the actor across the path to the next point.
                setAutoRotate( true ).
            // set path traverse by the center of the rectangle shape.
                setTranslation(
                    shape.width/2,
                    shape.height/2);

        shape.addBehavior( path_behavior );

        Caatja.loop(30);
        
    }

}
