package com.katspow.caatjagwtdemos.client.welcome.demos;

import java.util.Map;

import com.katspow.caatja.behavior.BaseBehavior;
import com.katspow.caatja.behavior.BehaviorListener;
import com.katspow.caatja.behavior.ContainerBehavior;
import com.katspow.caatja.behavior.PathBehavior;
import com.katspow.caatja.behavior.SetForTimeReturnValue;
import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.core.canvas.CaatjaImage;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;
import com.katspow.caatja.foundation.image.SpriteImage;
import com.katspow.caatja.pathutil.CurvePath;
import com.katspow.caatja.pathutil.Path;

public class Demo15 {
    
    public void start(CaatjaCanvas canvas, Map<String, CaatjaImage> images) throws Exception {
        final Director director = new Director().initialize(800, 500, canvas);
        director.imagesCache = images;
        final Scene slide = director.createScene();
        
        int rows= 4;
        int columns= 16;

        SpriteImage logo_ci= new SpriteImage().initialize(
            director.getImage("logo"), rows, columns
        );

        int i,j;
        double xoff= (slide.width-logo_ci.width)/2;
        double yoff= (slide.height-logo_ci.height)/2;

        for( i=0; i<rows; i++ ) {
            for( j=0; j<columns; j++ ) {
                Actor actor= new Actor().
                    setBackgroundImage( logo_ci.getRef(), true ).
                    setSpriteIndex( j + i * columns ).
                    setLocation(-100,-100);

                ContainerBehavior bc= new ContainerBehavior().
                            setFrameTime(0, 23000).
                            setCycle( true );


                PathBehavior b1=new PathBehavior().
                            setFrameTime( Math.random()*2000, 5000+Math.random()*2000 ).
                            setValues(
                                new Path().
                                    setCubic(
                                        Math.random()<.5 ? slide.width+Math.random() * 50 : -50-Math.random()*slide.width,
                                        Math.random()<.5 ? slide.width+Math.random() * 50 : -50-Math.random()*slide.height,
                                        (Math.random()<.5 ?1 :-1) * Math.random() * slide.width,
                                        (Math.random()<.5 ?1 :-1) * Math.random() * slide.height,
                                        (Math.random()<.5 ?1 :-1) * Math.random() * slide.width,
                                        (Math.random()<.5 ?1 :-1) * Math.random() * slide.height,
                                        xoff + j * logo_ci.singleWidth,
                                        yoff + i * logo_ci.singleHeight
                                    )
                            ).
                            addListener(
                                    new BehaviorListener() {
                                        @Override
                                        public void behaviorExpired(BaseBehavior behavior, double time, Actor actor) {
                                            ((CurvePath) ((PathBehavior) behavior).path.pathSegments.get(0)).curve.coordlist.get(0).set(
                                                    Math.random()<.5 ? slide.width+Math.random() * 50 : -20-Math.random()*slide.width,
                                                    Math.random()<.5 ? slide.width+Math.random() * 50 : -20-Math.random()*slide.height);
                                        }
                                        
                                        @Override
                                        public void behaviorApplied(BaseBehavior behavior, double time, double normalizeTime, Actor actor, SetForTimeReturnValue value)
                                                throws Exception {
                                        }

                                        @Override
                                        public void behaviorStarted(BaseBehavior behavior, double time, Actor actor) {
                                            
                                        }
                                    });
                
                PathBehavior b2= new PathBehavior().
                            setFrameTime( 15000+Math.random()*2000, 5000 ).
                            setValues(
                                new Path().
                                    setCubic(
                                        xoff + j * logo_ci.singleWidth,
                                        yoff + i * logo_ci.singleHeight,
                                        (Math.random()<.5 ?1 :-1) * Math.random() * slide.width,
                                        (Math.random()<.5 ?1 :-1) * Math.random() * slide.height,
                                        (Math.random()<.5 ?1 :-1) * Math.random() * slide.width,
                                        (Math.random()<.5 ?1 :-1) * Math.random() * slide.height,
                                        Math.random()<.5 ? slide.width+Math.random() * 50 : -20-Math.random()*slide.width,
                                        Math.random()<.5 ? slide.width+Math.random() * 50 : -20-Math.random()*slide.height
                                    )
                            ).
                            addListener(
                                    new BehaviorListener() {
                                        @Override
                                        public void behaviorExpired(BaseBehavior behavior, double time, Actor actor) {
                                            ((CurvePath) ((PathBehavior) behavior).path.pathSegments.get(0)).curve.coordlist.get(3).set(
                                                    Math.random()<.5 ? slide.width+Math.random() * 50 : -20-Math.random()*slide.width,
                                                    Math.random()<.5 ? slide.width+Math.random() * 50 : -20-Math.random()*slide.height
                                                );
                                        }
                                        
                                        @Override
                                        public void behaviorApplied(BaseBehavior behavior, double time, double normalizeTime, Actor actor, SetForTimeReturnValue value)
                                                throws Exception {
                                        }

                                        @Override
                                        public void behaviorStarted(BaseBehavior behavior, double time, Actor actor) {
                                            
                                        }
                                    });

                bc.addBehavior(b1);
                bc.addBehavior(b2);

                actor.addBehavior( bc );
                slide.addChild( actor );
            }
        }
        
        Caatja.loop(30);
    }

}
