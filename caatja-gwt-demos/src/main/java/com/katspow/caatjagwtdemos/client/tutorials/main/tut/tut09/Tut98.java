package com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut09;

import java.util.Map;

import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.core.canvas.CaatjaImage;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.ui.PathActor;
import com.katspow.caatja.pathutil.Path;

public class Tut98 {
    
    public void start(CaatjaCanvas canvas, Map<String, CaatjaImage> images) throws Exception {
        Director director = new Director();
        director.initialize(800, 500, canvas);
        director.imagesCache = images;
        
        Scene scene= director.createScene().setFillStyle("#fff");

        // path actor. to show the path and manipulate its control points.
        PathActor pa= new PathActor().
            setBounds(100,0,600,director.height).
            setPath(
                new Path().
                    beginPath(200,200).
                    addCubicTo( 300,15, 400,10, 500,200 ).
                    addQuadricTo( 550,300, 450,350 ).
                    addQuadricTo( 400,400, 350,200 ).
                    addCubicTo( 100,300, 300,450, 10,400).
                    addQuadricTo( 40,200, 200,200 ).
                    endPath() ).
            setInteractive(true);


        scene.addChild(pa);

        Caatja.loop(20);
       
    }

}
