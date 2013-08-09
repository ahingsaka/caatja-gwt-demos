package com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut08;

import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.ui.PathActor;
import com.katspow.caatja.pathutil.Path;

public class Tut83 {
    
    public void start(CaatjaCanvas canvas) throws Exception {
        final Director director = new Director();
        director.initialize(200, 200, canvas);
        director.setClear(false);
        
     // create scene.
        Scene _scene = director.createScene().setFillStyle("white");

        Path p = new Path().
                beginPath(40, 40).
                addCubicTo(60, 160, 180, 60, 100, 140, "red").
                addQuadricTo(160, 120, 130, 170, "blue").
                addLineTo(199, 80, "rgb(0,255,255)").
                endPath().
                setInteractive(false);

        PathActor path = new PathActor().
                setBounds(0, 0, 100, 100).
                setPath(p);
        
        
        Path p2 = new Path().
        beginPath(40, 40).
        addCubicTo(60, 160, 180, 60, 100, 140, "red").
        addQuadricTo(160, 120, 130, 170, "blue").
        addLineTo(199, 80, "rgb(0,255,255)").
        closePath().
        setInteractive(false);
        
        PathActor path2 = new PathActor().
        setBounds(0, 0, 100, 100).
        setPath(p2);

//        _scene.addChild(path);
        _scene.addChild(path2);

        Caatja.loop(30);
    }

}
