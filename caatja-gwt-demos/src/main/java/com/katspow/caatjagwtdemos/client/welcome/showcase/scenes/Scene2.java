package com.katspow.caatjagwtdemos.client.welcome.showcase.scenes;

import java.util.Arrays;

import com.katspow.caatja.behavior.BaseBehavior;
import com.katspow.caatja.behavior.BaseBehavior.Status;
import com.katspow.caatja.behavior.Interpolator;
import com.katspow.caatja.behavior.RotateBehavior;
import com.katspow.caatja.behavior.ScaleBehavior;
import com.katspow.caatja.core.canvas.CaatjaColor;
import com.katspow.caatja.core.canvas.CaatjaGradient;
import com.katspow.caatja.event.CAATMouseEvent;
import com.katspow.caatja.event.MouseListener;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;
import com.katspow.caatja.foundation.actor.ActorContainer;
import com.katspow.caatja.foundation.actor.SpriteActor;
import com.katspow.caatja.foundation.image.CompoundImage;
import com.katspow.caatja.foundation.ui.TextActor;
import com.katspow.caatja.math.Pt;

public class Scene2 {

    public static Scene init(Director director) throws Exception {
        Scene scene = new Scene();
        scene.setFillStrokeStyle(CaatjaColor.valueOf("#FFFFFF"));

        CompoundImage conpoundimage = new CompoundImage();
        conpoundimage.initialize(director.getImage("chapas"), 6, 6);
        int __index = 0;

        int padding = 10;

        int cw = director.canvas.getCoordinateSpaceWidth() - padding * 2;
        int ch = director.canvas.getCoordinateSpaceHeight() - padding * 2;
        
        int cols = (cw / ((int) conpoundimage.singleHeight)) >> 0;
        int rows = (ch / ((int) conpoundimage.singleWidth)) >> 0;

        int w = cw / cols;
        int h = ch / rows;
        
        int i, j;

        ActorContainer cc = new ActorContainer().
                setBounds(0, 0, director.canvas.getCoordinateSpaceWidth(), director.canvas.getCoordinateSpaceHeight());
//                setClip(true, new Path().
//                        beginPath(200,200).
//                        addCubicTo( 300,15, 400,10, 550,250 ).
//                        addQuadricTo( 550,300, 450,350 ).
//                        addQuadricTo( 400,400, 350,200 ).
//                        addCubicTo( 100,300, 300,450, 10,400).
//                        addQuadricTo( 40,200, 200,200 ).
//                        closePath()
//                );
        
        scene.addChild(cc);

        for (i = 0; i < rows; i++) {
            for (j = 0; j < cols; j++) {

                SpriteActor actor = new SpriteActor() {
                    // TODO Remove
//                    @Override
//                    public void mouseDblClick(CAATMouseEvent mouseEvent) {
//                        Actor actor = mouseEvent.source;
//                        if (null == actor) {
//                            return;
//                        }
//                        BaseBehavior behaviour = actor.behaviorList.get(0);
//                        if (null == behaviour) {
//                            return;
//                        }
//
//                        if (behaviour.status == Status.EXPIRED) {
//                            actor.parent.setZOrder(actor.parent, Integer.MAX_VALUE);
//                            actor.behaviorList.get(0).setFrameTime(mouseEvent.source.time, 1000);
//                        }
//                    }

                    // TODO Remove
//                    @Override
//                    public void mouseEnter(CAATMouseEvent mouseEvent) {
//                        Actor actor = mouseEvent.source;
//                        if (null == actor) {
//                            return;
//                        }
//                        BaseBehavior behaviour = actor.behaviorList.get(0);
//                        if (null == behaviour) {
//                            return;
//                        }
//
//                        if (behaviour.status == Status.NOT_STARTED || behaviour.status == Status.EXPIRED) {
//                            
//                            actor.parent.setZOrder(actor, Integer.MAX_VALUE);
//
//                            actor.behaviorList.get(0).setFrameTime(mouseEvent.source.time, 500);
//                            actor.behaviorList.get(1).setFrameTime(mouseEvent.source.time, 500);
//                        }
//                    }

                };
                
                actor.setMouseEnterListener(new MouseListener() {
                    public void call(CAATMouseEvent e) throws Exception {
                        Actor actor = e.source;
                        if (null == actor) {
                            return;
                        }
                        BaseBehavior behaviour = actor.behaviorList.get(0);
                        if (null == behaviour) {
                            return;
                        }

                        if (behaviour.status == Status.NOT_STARTED || behaviour.status == Status.EXPIRED) {
                            
                            actor.parent.setZOrder(actor, Integer.MAX_VALUE);

                            actor.behaviorList.get(0).setFrameTime(e.source.time, 500);
                            actor.behaviorList.get(1).setFrameTime(e.source.time, 500);
                        }
                    }
                });
                
                actor.setMouseDblClickListener(new MouseListener() {
                    public void call(CAATMouseEvent mouseEvent) throws Exception {
                        Actor actor = mouseEvent.source;
                        if (null == actor) {
                            return;
                        }
                        BaseBehavior behaviour = actor.behaviorList.get(0);
                        if (null == behaviour) {
                            return;
                        }

                        if (behaviour.status == Status.EXPIRED) {
                            actor.parent.setZOrder(actor.parent, Integer.MAX_VALUE);
                            actor.behaviorList.get(0).setFrameTime(mouseEvent.source.time, 1000);
                        }
                    }
                });

                actor.setBounds(j * w + padding, i * h + padding, w, h).
                    setSpriteImage(conpoundimage).
                    //setBackgroundImage( conpoundimage.getRef(), true ). TODO ???
                    setAnimationImageIndex(Arrays.asList((__index++) % conpoundimage.getNumImages()));
                cc.addChild(actor);

                ScaleBehavior sb = new ScaleBehavior().
                        setPingPong().
                        setValues(1,2, 1,2).
                        setInterpolator(
                // new Interpolator().createBounceOutInterpolator(true) );
                // new Interpolator().createElasticOutInterpolator(1.1, .4)
                // );
                new Interpolator().createExponentialInOutInterpolator(3, true));

                actor.addBehavior(sb);

                RotateBehavior rb = new RotateBehavior().
                        setValues(0,Math.PI*2);
                actor.addBehavior(rb);

            }
        }

        CaatjaGradient gradient = director.ctx.createLinearGradient(0, 0, 0, 30);
        gradient.addColorStop(0, "#ffff00");
        gradient.addColorStop(0.5, "#ff00ff");
        gradient.addColorStop(1, "blue");

        ActorContainer cc1 = new ActorContainer().
                setBounds(380, 30, 300, 150).
                enableEvents(false);
        scene.addChild(cc1);

        RotateBehavior rb1 = new RotateBehavior().
                setCycle(true).
                setFrameTime( 0, 4000 ).
                setValues(-Math.PI/8, Math.PI/8, .5,0d).
                setInterpolator(new Interpolator().createCubicBezierInterpolator(new Pt().set(0, 0),
                new Pt().set(1, 0), new Pt().set(0, 1), new Pt().set(1, 1), true));
        cc1.addBehavior(rb1);

        TextActor text = new TextActor().
                setFont("50px sans-serif").
                setText("One Image.").
                calcTextSize(director).
                setTextFillStyle(gradient).
                setOutline(true);
        text.cacheAsBitmap();
        cc1.addChild( text.setLocation((cc1.width-text.textWidth)/2,0) );

        TextActor text2 = new TextActor().
                setFont("30px sans-serif").
                setText("Behaviors on").
                calcTextSize(director).
                setTextFillStyle(gradient).
                setOutline(true);
        text2.cacheAsBitmap();
        cc1.addChild(text2.setLocation((cc1.width-text2.width)/2,50));

        TextActor text3 = new TextActor().
                setFont("30px sans-serif").
                setText("MouseMove").
                calcTextSize(director).
                setTextFillStyle(gradient).
                setOutline(true);
        text.cacheAsBitmap();
        cc1.addChild(text3.setLocation((cc1.width-text3.textWidth)/2,80));

        TextActor text4 = new TextActor().
                setFont("10px sans-serif").
                setText("and").
                calcTextSize(director).
                setTextFillStyle("black").
                setOutline(true);
        text4.cacheAsBitmap();
        cc1.addChild(text4);

        TextActor text5 = new TextActor().
                setFont("30px sans-serif").
                setText("MouseDblClick").
                calcTextSize(director).
                setTextFillStyle(gradient).
                setOutline(true);
        text5.cacheAsBitmap();
        
        cc1.addChild(text5.setLocation((cc1.width-text5.textWidth)/2,120));
        cc1.enableEvents(false);

        return scene;
    }

}
