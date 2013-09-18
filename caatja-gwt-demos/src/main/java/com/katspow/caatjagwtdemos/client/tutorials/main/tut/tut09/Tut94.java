package com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut09;

import java.util.Map;

import com.katspow.caatja.behavior.BaseBehavior;
import com.katspow.caatja.behavior.BehaviorListener;
import com.katspow.caatja.behavior.Interpolator;
import com.katspow.caatja.behavior.PathBehavior;
import com.katspow.caatja.behavior.SetForTimeReturnValue;
import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.core.canvas.CaatjaImage;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;
import com.katspow.caatja.foundation.actor.ImageActor;
import com.katspow.caatja.math.Pt;
import com.katspow.caatja.pathutil.Path;

@Deprecated
public class Tut94 {
    
    public void start(CaatjaCanvas canvas, Map<String, CaatjaImage> images) throws Exception {
        final Director director = new Director();
        director.initialize(700, 350, canvas);
        director.imagesCache = images;
        director.setClear(false);
        
        final Scene scene= director.createScene();
        
        for(int i=0; i<20; i++ ) {
            ImageActor fish =
                (ImageActor) new ImageActor().
                setImage(director.getImage("logo")).
                setImageTransformation(ImageActor.Tr.FIXED_TO_SIZE).
                addBehavior(
                    new PathBehavior().
                        setAutoRotate(true).
                        setPath( new Path().setLinear(0,0,0,0) ).
                        setInterpolator(
                            new Interpolator().createExponentialInOutInterpolator(2,false) ).
                        setFrameTime( scene.time, 10 ).
                        addListener(
                                new BehaviorListener() {
                                    @Override
                                    public void behaviorExpired(BaseBehavior behaviour, double time, Actor actor) {
                                        PathBehavior pathBehavior = (PathBehavior) behaviour;
                                        Pt endCoord= pathBehavior.path.endCurvePosition();
                                        pathBehavior.setPath(
                                                new Path().setCubic(
                                                    endCoord.x,
                                                    endCoord.y,
                                                    Math.random()*director.width,
                                                    Math.random()*director.height,
                                                    Math.random()*director.width,
                                                    Math.random()*director.height,
                                                    Math.random()*director.width,
                                                    Math.random()*director.height) );
                                        behaviour.setFrameTime( scene.time, 3000+Math.random()*3000 );
                                        
                                    }
                                    
                                    @Override
                                    public void behaviorApplied(BaseBehavior behavior, double time, double normalizeTime, Actor actor, SetForTimeReturnValue value)
                                            throws Exception {
                                        
                                    }

                                    @Override
                                    public void behaviorStarted(BaseBehavior behavior, double time, Actor actor) {
                                        
                                    }
                        })
                );
            scene.addChild(fish);
        }
        
        Caatja.loop(60);
    }
    

}
