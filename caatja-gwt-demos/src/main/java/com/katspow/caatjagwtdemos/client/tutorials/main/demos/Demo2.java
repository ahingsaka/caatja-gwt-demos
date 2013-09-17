package com.katspow.caatjagwtdemos.client.tutorials.main.demos;

import java.util.Map;

import com.katspow.caatja.behavior.BaseBehavior;
import com.katspow.caatja.behavior.BehaviorListener;
import com.katspow.caatja.behavior.PathBehavior;
import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.core.canvas.CaatjaGradient;
import com.katspow.caatja.core.canvas.CaatjaImage;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;
import com.katspow.caatja.foundation.actor.ActorContainer;
import com.katspow.caatja.pathutil.Path;
import com.katspow.caatjagwtdemos.client.demos.main.fish.FishScene12;

public class Demo2 {
    
    public void start(CaatjaCanvas canvas, Map<String, CaatjaImage> images) throws Exception {
        final Director director = new Director().initialize(800, 500, canvas).setClear(false);
        director.imagesCache = images;
        final Scene scene = director.createScene();
        
        int NP=20;
        String[] colors= new String[] {"red", "blue", "white", "rgb(0,255,255)", "yellow"};
        CaatjaGradient gradient= director.ctx.createLinearGradient(0,0,director.width,director.height);
        gradient.addColorStop(0,"#000000");
        gradient.addColorStop(1,"#00007f");

        ActorContainer gr= new ActorContainer().
                setBounds(0,0,director.width,director.height).
                setFillStrokeStyle(gradient).
                enableEvents(false);
                gr.cacheAsBitmap();

        for( int i=0; i<NP; i++ ) {

            final int fw= (int)(100 + Math.random()*40*(Math.random()<.5?1:-1))>>0;
            int fh= (int)(20+ Math.random()*5*(Math.random()<.5?1:-1))>>0;

            int inTime= i*1000;

            PathBehavior pb = new PathBehavior().
                    setPath(new Path().setCubic(
                    -fw - Math.random() * 300,
                    Math.random() * director.height,

                    director.width * Math.random(),
                    Math.random() * director.height,

                    director.width * Math.random(),
                    Math.random() * director.height,

                    Math.random() < .5 ? director.width + fw + Math.random() * 150 : Math.random() * director.width,
                    Math.random() < .5 ? -director.height * Math.random() - 300 : director.height + Math.random() * director.height
                    )).
                    setFrameTime(scene.time + inTime, (int)(20000 + 5000 * Math.random()) >> 0).
                    setCycle(true).
                    setAutoRotate(true).
                    addListener(
                            new BehaviorListener() {
                                
                                @Override
                                public void behaviorExpired(BaseBehavior behavior, double time, Actor actor) {
                                    ((PathBehavior) behavior).path.setCubic(
                                            -fw - Math.random() * 300,
                                            Math.random() * director.height,

                                            director.width * Math.random(),
                                            -Math.random() * director.height / 2 + Math.random() * director.height,

                                            director.width * Math.random(),
                                            -Math.random() * director.height / 2 + Math.random() * director.height,

                                            Math.random() < .5 ? director.width + fw + Math.random() * 150 : Math.random() * director.width,
                                            Math.random() < .5 ? -director.height * Math.random() - 300 : director.height + Math.random() * director.height
                                            );
                                    behavior.setFrameTime(scene.time, (int)(20000 + 5000 * Math.random()) >> 0);
                                    ((FishScene12) actor).born();
                                    
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

            FishScene12 f= new FishScene12();
                    f.setBounds(300,400,fw,fh);
                    f.born();
                    f.setFrameTime( scene.time+inTime, Double.MAX_VALUE );
                    f.setBodyColor(colors[i%colors.length]);

            f.addBehavior(pb);
            gr.addChild(f);
        }

        scene.addChild(gr);
        
        Caatja.loop(30);
    }

}
