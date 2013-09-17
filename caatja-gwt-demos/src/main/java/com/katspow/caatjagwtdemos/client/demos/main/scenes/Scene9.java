package com.katspow.caatjagwtdemos.client.demos.main.scenes;

import com.katspow.caatja.behavior.Interpolator;
import com.katspow.caatja.behavior.RotateBehavior;
import com.katspow.caatja.core.canvas.CaatjaGradient;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.ActorContainer;
import com.katspow.caatja.foundation.timer.Callback;
import com.katspow.caatja.foundation.timer.TimerTask;
import com.katspow.caatja.foundation.ui.IMActor;
import com.katspow.caatja.foundation.ui.TextActor;
import com.katspow.caatja.modules.image.imageprocess.IMBumpMapping;
import com.katspow.caatja.modules.image.imageprocess.IMPlasma;

/**
 * 
 * Scene 9.
 * Shows some image processing actors.
 * Heavy processor intensive.
 *
 */
public class Scene9 {
    
    private static TimerTask timer;

    public static Scene init(Director director) throws Exception {
        int i;
        final Scene scene= new Scene();

        __scene9_text(director,scene);

        IMActor ip0= new IMActor().
                setBounds(100,100,400,400).
                setImageProcessor(
                    new IMPlasma().
                        initialize( 400,400,new int [] {0xffff0000, 0xffff7f00, 0xff7f00ff, 0xff0000ff })
                );
        scene.addChild(ip0);
//
//        IMActor ip1= new IMActor().
//                create().
//                setBounds(100,200,100,100).
//                setImageProcessor(
//                    new IMPlasma().
//                        initialize( 100,100,new int [] {0xff00ff00, 0xff00ffff, 0xffffff00, 0xff000000, 0xffffffff })
//                );
//        scene.addChild(ip1);
//
//        IMActor ip2= new IMActor().
//                create().
//                setBounds(100,300,100,100).
//                setImageProcessor(
//                    new IMPlasma().
//                        initialize( 100,100,new int [] { 0xffff0000, 0xff00ff00, 0xff0000ff, 0xffffff })
//                );
//        scene.addChild(ip2);
//        IMActor ip4= new IMActor().
//                create().
//                setBounds( 400, 150, 250, 250 ).
//                setImageProcessor(
//                    new IMRotoZoom().
//                            initialize( 250,250, director.getImage("bump") )
//                );
//        scene.addChild(ip4);
//
//        IMActor ip3 = new IMActor() {
//            @Override
//            public void mouseMove(CAATMouseEvent mouseEvent) {
//                ((IMBump) this.imageProcessor).lightPosition[0].x= mouseEvent.point.x;
//                ((IMBump) this.imageProcessor).lightPosition[0].y= mouseEvent.point.y;
//            }
//
//            @Override
//            public void mouseEnter(CAATMouseEvent mouseEvent) {
//                timer.cancel();
//            }
//
//            @Override
//            public void mouseExit(CAATMouseEvent mouseEvent) {
//                timer = __scene9_createtimer(scene, this);
//            }
//            
//        }.
//                create().
//                setBounds( 250,150, 128, 128 ).
//                setImageProcessor(
//                    new IMBump().
//                            initialize( director.getImage("bump"), 48 )
//                );
//        
//        
//        timer= __scene9_createtimer(scene, ip3);
//        
//        scene.addChild(ip3);

        return scene;
    }
    
    private static TimerTask __scene9_createtimer(Scene scene, final IMActor ip3) {
        
        return scene.createTimer(scene.time, Double.MAX_VALUE, null, new Callback() {
            @Override
            public void call(double time, double ttime, TimerTask timerTask) {
                double r= ip3.imageProcessor.width-10;
                r/=2;

                ((IMBumpMapping) ip3.imageProcessor).lightPosition[0].x= ip3.imageProcessor.width/2 + r*Math.cos(ttime*.001);
                ((IMBumpMapping) ip3.imageProcessor).lightPosition[0].y= ip3.imageProcessor.height/2 - r*Math.sin(ttime*.001);
            }
        }, null);
        
    }
    
    private static void __scene9_text(Director director, Scene scene) throws Exception {
        CaatjaGradient gradient= director.ctx.createLinearGradient(0,0,0,50);
        gradient.addColorStop(0,"orange");
        gradient.addColorStop(0.5,"red");
        gradient.addColorStop(1,"#3f00ff");

        ActorContainer cc= new ActorContainer().
                setBounds( 450,30, 150, 100 ).
                enableEvents(false).
                addBehavior(
                    new RotateBehavior().
                            setCycle(true).
                            setFrameTime( 0, 4000 ).
                            setValues( -Math.PI/8, Math.PI/8, .5, 0d ).
                            setInterpolator(
                                new Interpolator().createExponentialInOutInterpolator(3,true)
                            )
                );
        scene.addChild(cc);

        TextActor text= new TextActor().
                setFont("50px sans-serif").
                setText("Image").
                setTextFillStyle(gradient).
                setOutline(true).
                calcTextSize(director);
        text.cacheAsBitmap();
        cc.addChild(text.setLocation((cc.width-text.textWidth)/2,0));

        TextActor text2= new TextActor().
                setFont("50px sans-serif").
                setText("Processing").
                calcTextSize(director).
                setTextFillStyle(gradient).
                setOutline(true);
        text2.setLocation((cc.width-text2.width)/2,50);
        text2.cacheAsBitmap();
        cc.addChild(text2.setLocation((cc.width-text2.textWidth)/2,50));

        scene.addChild(cc);
    }

}
