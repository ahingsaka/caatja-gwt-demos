package com.katspow.caatjagwtdemos.client.tutorials.main.demos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.katspow.caatja.behavior.BaseBehavior;
import com.katspow.caatja.behavior.BehaviorListener;
import com.katspow.caatja.behavior.Interpolator;
import com.katspow.caatja.behavior.PathBehavior;
import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.core.canvas.CaatjaGradient;
import com.katspow.caatja.core.canvas.CaatjaImage;
import com.katspow.caatja.event.CAATMouseEvent;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;
import com.katspow.caatja.foundation.actor.ActorContainer;
import com.katspow.caatja.foundation.image.SpriteImage;
import com.katspow.caatja.math.Pt;
import com.katspow.caatja.pathutil.Path;

public class Demo3 {
    
    private int imageIndex;
    private Director director;

    public void start(CaatjaCanvas canvas, Map<String, CaatjaImage> images) throws Exception {
        director = new Director().initialize(800, 500, canvas).setClear(false);
        director.imagesCache = images;
        final Scene scene = director.createScene();
        
     // when the scene is activated, avoid the director clearing the viewport since it"ll be
        // totally erased by the background.

        imageIndex=0;
        final List<SpriteImage> conpoundimagefish = new ArrayList<SpriteImage>();
        conpoundimagefish.add(
                new SpriteImage().initialize(
                        director.getImage("fish1"),  1, 3) );
        conpoundimagefish.add(
                new SpriteImage().initialize(
                        director.getImage("fish2"), 1, 3) );
        conpoundimagefish.add(
                new SpriteImage().initialize(
                        director.getImage("fish3"), 1, 3) );
        conpoundimagefish.add(
                new SpriteImage().initialize(
                        director.getImage("fish4"), 1, 3) );

        CaatjaGradient gradient= director.ctx.createLinearGradient(0,0,director.width,director.height);
        gradient.addColorStop(0,"#000000");
        gradient.addColorStop(1,"#00007f");

        ActorContainer gr= new ActorContainer() {
            @Override
            public void mouseClick(CAATMouseEvent ev) throws Exception {
                addFish(scene, ev.point.x,ev.point.y, conpoundimagefish);
            }
        }.
                setBounds(0,0,director.width,director.height).
                setFillStrokeStyle(gradient).
                enableEvents(false);
                gr.cacheAsBitmap();

        scene.addChild(gr);

        gr.enableEvents(true);

        addFish(scene, 100,100, conpoundimagefish);
        
        Caatja.loop(30);
    }
    
    private void addFish(final Scene scene, double x, double y, List<SpriteImage> conpoundimagefish) throws Exception {

        double scale= Math.random() +.5;

        Actor fish = new Actor().
                setBackgroundImage(
                        conpoundimagefish.get((imageIndex++)%conpoundimagefish.size()),
                        true).
                setLocation( x,y ).
                setScale( scale,scale ).
                setAnimationImageIndex(new int []{0,1,2,1} ).
                setChangeFPS(300).
                enableEvents(false);

        scene.addChild(fish);

        PathBehavior pbfish= (PathBehavior) new PathBehavior().
                setAutoRotate(true, PathBehavior.AutoRotate.LEFT_TO_RIGHT).
                setPath(
                    new Path().setLinear( x,y, x,y ) ).
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
                                pathBehavior.setFrameTime( scene.time, 3000+Math.random()*3000 );
                            }
                            
                            @Override
                            public void behaviorApplied(BaseBehavior behavior, double time, double normalizeTime, Actor actor, Object value)
                                    throws Exception {
                            }

                            @Override
                            public void behaviorStarted(BaseBehavior behavior, double time, Actor actor) {
                            }
                        }
                       );

        fish.addBehavior( pbfish );
    }

}
