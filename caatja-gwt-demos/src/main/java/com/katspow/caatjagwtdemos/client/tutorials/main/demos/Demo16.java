package com.katspow.caatjagwtdemos.client.tutorials.main.demos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.katspow.caatja.behavior.BaseBehavior;
import com.katspow.caatja.behavior.BehaviorListener;
import com.katspow.caatja.behavior.Interpolator;
import com.katspow.caatja.behavior.PathBehavior;
import com.katspow.caatja.behavior.RotateBehavior;
import com.katspow.caatja.behavior.ScaleBehavior;
import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.core.canvas.CaatjaGradient;
import com.katspow.caatja.core.canvas.CaatjaImage;
import com.katspow.caatja.event.CAATMouseEvent;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;
import com.katspow.caatja.foundation.actor.ActorContainer;
import com.katspow.caatja.math.Pt;
import com.katspow.caatja.pathutil.Path;
import com.katspow.caatjagwtdemos.client.demos.main.fish.FishScene12;

public class Demo16 {
    
    private int pathIndex;
    private Path[] path;

    public void start(CaatjaCanvas canvas, Map<String, CaatjaImage> images) throws Exception {
        final Director director = new Director().initialize(800, 500, canvas);
        director.imagesCache = images;
        final Scene scene = director.createScene();
        
        CaatjaGradient gradient= director.ctx.createLinearGradient(0,0,director.width,director.height);
        gradient.addColorStop(0,"#000000");
        gradient.addColorStop(1,"#00007f");

        ActorContainer gr= new ActorContainer() {
            @Override
            public void mouseClick(CAATMouseEvent mouseEvent) throws Exception {
                pathIndex++;
                setClip( true, path[ pathIndex%path.length ] );
            }
            
        }.
                setBounds(0,0,director.width,director.height).
                setFillStrokeStyle(gradient);
                gr.cacheAsBitmap();

        scene.addChild(gr);
        
        addMask( director, scene ,gr );
        createFish(director,scene,gr);
        
        Caatja.loop(30);  
    }
    
    private void addMask(Director director, Scene scene, ActorContainer gr) {
        
        double dw= director.width;
        double dh= director.height;


        int i;
        double R= (Math.min( dw,dh ) - 40)/2;
        List<Pt> pp= new ArrayList<Pt>();
        double angle;
        int NP=5;
        for( i=0; i<NP; i++ ) {
            angle= i*Math.PI/(NP);

            pp.add( new Pt(
                    dw/2 + R*Math.cos(angle + (Math.PI*(i%2)) ) ,
                    dh/2 + R*Math.sin(angle + (Math.PI*(i%2))) ) );

        }
        List<Pt> pp2= new ArrayList<Pt>();
        for( i=0; i<pp.size(); i++ ) {
            pp2.add(new Pt( pp.get(i).x, pp.get(i).y ) );
        }
        
        Path path2= new Path().
                setCatmullRom(
                    pp,
                    true
                ).
                endPath();
        
//        Path path2= new Path().
//                            beginPath(100,director.height/2).
//                            addCubicTo(
//                                200,10,
//                                director.width-200,10,
//                                director.width-200,director.height/2 ).
//                            addCubicTo(
//                                director.width-200,director.height-10,
//                                200, director.height-10,
//                                200, director.height/2 ).
//                            closePath();

        path = new Path[]
                {
                    new Path().
                            beginPath(100, director.height / 2).
                            addCubicTo(
                            100, 10,
                            director.width - 100, 10,
                            director.width - 100, director.height / 2).
                            addCubicTo(
                            director.width - 100, director.height - 10,
                            100, director.height - 10,
                            100, director.height / 2).
                            closePath().
                            setPositionAnchor(.5,.5),

                    new Path().
                            beginPath(100,100).
                            addRectangleTo( director.width-100, director.height-100, null, null ).
                            endPath().
                            setPositionAnchor(.5,.5),

//                    new Path().
//                            beginPath(200,200).
//                            addCubicTo( 300,15, 400,10, 550,250 ).
//                            addQuadricTo( 550,300, 450,350 ).
//                            addQuadricTo( 400,400, 350,200 ).
//                            addCubicTo( 100,300, 300,450, 10,400).
//                            addQuadricTo( 40,200, 200,200 ).
//                            closePath().
//                            setPositionAnchor(.5,.5)
                            
                            new Path().
                            setCatmullRom(
                                pp2,
                                true
                            ).
                            endPath().
                            setPositionAnchor(.5,.5)

                };

        for( i=0; i<path.length; i++ ) {
            path[i].addBehavior(
                new PathBehavior().
                    setValues( path2 ).
                    setFrameTime( 0,15000 ).
                    setCycle( true )
            ).addBehavior(
                new RotateBehavior().
                    setValues( 0,Math.PI*2, .5, .5 ).
                    setFrameTime( 0,5000 ).
                    setCycle( true )
            ).addBehavior(
                new ScaleBehavior().
                    setValues( .2, 1, .2, 1 ).
                    setFrameTime( 0,10000 ).
                    setCycle( true ).
                    setInterpolator(
                        new Interpolator().createLinearInterpolator(true, false)
                )
            );
        }

        pathIndex=0;
        gr.setClip( true, path[0] );

//        gr.mouseClick= function(e) {
//            pathIndex++;
//            gr.setClip( true, path[ pathIndex%path.length ] );
//        }
    }

    private void createFish(final Director director, final Scene scene, ActorContainer gr) throws Exception {
        String[] colors= new String[]{"red", "blue", "white", "rgb(0,255,255)", "yellow"};
        int NP=20;

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
    }

}
