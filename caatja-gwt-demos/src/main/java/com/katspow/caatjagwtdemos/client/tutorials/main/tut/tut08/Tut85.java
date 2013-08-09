package com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut08;

import java.util.Map;

import com.katspow.caatja.behavior.Interpolator;
import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.core.canvas.CaatjaImage;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.ui.InterpolatorActor;
import com.katspow.caatja.math.Pt;

public class Tut85 {
    
    public void start(CaatjaCanvas canvas, Map<String, CaatjaImage> images) throws Exception {
        final Director director = new Director();
        director.initialize(200, 100, canvas);
        director.imagesCache = images;
        director.setClear(false);
        
        final Scene scene= director.createScene();
        
        scene.addChild(
                new InterpolatorActor().
                    setInterpolator(
                        new Interpolator().createQuadricBezierInterpolator(
                                new Pt(0, 0),
                                new Pt(1, 0),
                                new Pt(1, 1),
                                false
                                ), null
                    ).
                    setBounds( 10, 10, 80, 80 ).
                    setFillStyle("#d0d0d0").
                    setGap(0)
        );

        scene.addChild(
                new InterpolatorActor().
                    setInterpolator(
                        new Interpolator().createCubicBezierInterpolator(
                                new Pt(0, 0),
                                new Pt(0, 1),
                                new Pt(1, 0),
                                new Pt(1, 1),
                                true
                                ), null
                    ).
                    setBounds( 100, 10, 80, 80 ).
                    setFillStyle("#d0d0d0").
                    setGap(0)
        );

        Caatja.loop(30);
        
    }

}
