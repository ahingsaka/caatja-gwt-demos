package com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut08;

import java.util.Map;

import com.katspow.caatja.behavior.Interpolator;
import com.katspow.caatja.behavior.PathBehavior;
import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.event.CAATMouseEvent;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;
import com.katspow.caatja.foundation.ui.Dock;
import com.katspow.caatja.foundation.ui.InterpolatorActor;
import com.katspow.caatja.foundation.ui.PathActor;
import com.katspow.caatja.pathutil.Path;

public class Tut84 {
    
    public void start(CaatjaCanvas canvas) throws Exception {
        final Director director = new Director();
        director.initialize(700, 500, canvas);
        director.setClear(false);
        
        Scene scene= director.createScene().setFillStyle("white");
        
     // path actor. to show the path and manipulate its control points.
        // by default CAAT.PathActor instances are interactive, so the control points
        // can be moved and the path interactively changed.
        PathActor pa= new PathActor().
                setBounds(0,0,600,director.canvas.getCoordinateSpaceHeight()).
                setPath(
                // create a complex path composed of some heterogeneous segments.
                    new Path().
                        beginPath(200,200).
                        addCubicTo( 300,15, 400,10, 500,200 ).
                        addQuadricTo( 550,300, 450,350 ).
                        addQuadricTo( 400,400, 350,200 ).
                        addCubicTo( 100,300, 300,450, 10,400).
                        addQuadricTo( 40,200, 200,200 ).
                        endPath() );

        // a green actor
            Actor fish = new Actor().
                    setLocation( 50,50 ).
                    setSize(50,20).
                    setFillStyle("#00ff00").
                    setStringStrokeStyle("#ff00ff").
                    enableEvents(false);
        // a red actor
            Actor fish2 = new Actor().
                    setLocation( 50,50 ).
                    setSize(50,20).
                    setFillStyle("#f00").
                    setStringStrokeStyle("#ff00ff").
                    setAlpha(.75).
                    enableEvents(false);

        // path measurer behaviour
            PathBehavior pb= new PathBehavior().
                setPath(pa.getPath()).
                setFrameTime(0,10000).
                setCycle(true).
        // set the traverse anchor in actor"s center
                setTranslation( fish.width/2, fish.height/2 ).
        // make the actor heading across the path
                setAutoRotate(true);

            fish.addBehavior( pb );
        // create anothe behavior for the same path.
            fish2.addBehavior(
                    new PathBehavior().
                        setPath(pa.getPath()).
                        setFrameTime(0,10000).
                        setCycle(true).
                        setTranslation( fish.width/2, fish.height/2 ).
                        setAutoRotate(true) );

            scene.addChildImmediately(pa);
            scene.addChildImmediately(fish);
            scene.addChildImmediately(fish2);

            __scene1_generateInterpolators(director, scene, pb);

            director.addScene(scene);
            Caatja.loop(30);
    }
    
    InterpolatorActor selectedInterpolatorActor= null;

    private void __scene1_generateInterpolators(Director director, Scene scene, final PathBehavior pathBehavior) throws Exception {
        
        Map<String, Interpolator> lerps = Interpolator.enumerateInterpolators();

        int cols= 21;
        int j=0, i=0;
        int rows= lerps.size()/cols;
        int min= 20;
        int max= 45;

        // generate interpolator actors.
        for( j=0; j<rows; j++ ) {

            Dock root= new Dock().
                    setBounds(
                        director.canvas.getCoordinateSpaceWidth()-(j+1)*max,
                        0,
                        max,
                        director.canvas.getCoordinateSpaceHeight()).
                    setSizes(min, max).
                    setApplicationRange( 3 ).
                    setLayoutOp( Dock.OpLayout.RIGHT);
            root.scene= scene;

            scene.addChildImmediately(root);

            for( i=0; i<cols; i++ ) {

                if ( j*cols+i>=lerps.size() ) {
                    break;
                }

                InterpolatorActor actor= new InterpolatorActor() {

                    @Override
                    public void mouseClick(CAATMouseEvent mouseEvent) throws Exception {
                        if ( null!=selectedInterpolatorActor ) {
                          selectedInterpolatorActor.setFillStyle(null);
                      }
                      selectedInterpolatorActor= (InterpolatorActor) mouseEvent.source;
                      mouseEvent.source.setFillStyle("#00ff00");
                      selectedInterpolatorActor= (InterpolatorActor) mouseEvent.source;
  
                      pathBehavior.setInterpolator( ((InterpolatorActor) mouseEvent.source).getInterpolator() );
                    }

                    @Override
                    public void mouseEnter(CAATMouseEvent mouseEvent) {
                        if (mouseEvent.source != selectedInterpolatorActor) {
                            mouseEvent.source.setFillStyle("#f0f0f0");
                        }
                    }

                    @Override
                    public void mouseExit(CAATMouseEvent mouseEvent) {
                        if (mouseEvent.source != selectedInterpolatorActor) {
                            mouseEvent.source.setFillStyle(null);
                        }
                    }
                    
                }.
                     setInterpolator((Interpolator) lerps.values().toArray()[(j*cols+i)], null ).
                     setBounds( 0, 0, min, min ).
                     setStringStrokeStyle( "blue" );

                root.addChildImmediately( actor );
            }

            root.layout();
        }
        
        
    }

}
