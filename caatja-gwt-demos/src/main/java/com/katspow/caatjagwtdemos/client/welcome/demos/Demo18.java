package com.katspow.caatjagwtdemos.client.welcome.demos;

import com.katspow.caatja.CAATKeyListener;
import com.katspow.caatja.behavior.RotateBehavior;
import com.katspow.caatja.behavior.ScaleBehavior;
import com.katspow.caatja.core.CAAT;
import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.event.CAATKeyEvent;
import com.katspow.caatja.event.CAATMouseEvent;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;
import com.katspow.caatja.foundation.timer.Callback;
import com.katspow.caatja.foundation.timer.TimerTask;
import com.katspow.caatja.foundation.ui.ShapeActor;
import com.katspow.caatja.foundation.ui.StarActor;

public class Demo18 {

    double prevTime=           -1;
    Actor selected=           null;
    
    public void start(CaatjaCanvas canvas) throws Exception {
        final Director director = new Director().initialize(800, 500, canvas);
        
        int size=               80;
        final int pixelsPerSecond=    200;
        Scene scene=              director.createScene();
        final int[] keys=     new int[]          {0,0,0,0};

        /**
         * This timer makes the process to increment actor position based on elapsed time.
         * it will move pixelsPerSecond pixels on any direction.
         */
        scene.createTimer(scene.time, Double.MAX_VALUE, null, new Callback() {
            @Override
            public void call(double time, double ttime, TimerTask timerTask) {
                if ( selected == null) {
                    return;
                }

                double ottime= ttime;
                if ( -1!=prevTime ) {
                    ttime-= prevTime;

                    selected.x += (ttime/1000)*pixelsPerSecond * (keys[1]-keys[0]);
                    selected.y += (ttime/1000)*pixelsPerSecond * (keys[3]-keys[2]);

                    if ( selected.x > director.width-20 ) {
                        selected.x= director.width-20;
                    } else if ( selected.x<-20 ) {
                        selected.x= -20;
                    }
                    if ( selected.y > director.height-20 ) {
                        selected.y= director.height-20;
                    } else if ( selected.y<-20 ) {
                        selected.y= -20;
                    }
                }

                prevTime= ottime;                
            }
        }, null);
        
        CAAT.registerKeyListener(new CAATKeyListener() {
            @Override
            public void call(CAATKeyEvent keyEvent) {
                if (selected == null) {
                    return;
                }

                if (keyEvent.getKeyCode() == CAAT.Keys.UP.getValue()) {
//                    keyEvent.preventDefault();
                    keys[2] = (keyEvent.getAction().equals("up")) ? 0 : 1;
                }

                if (keyEvent.getKeyCode() == CAAT.Keys.DOWN.getValue()) {
//                    keyEvent.preventDefault();
                    keys[3] = (keyEvent.getAction().equals("up")) ? 0 : 1;
                }
                if (keyEvent.getKeyCode() == CAAT.Keys.LEFT.getValue()) {
//                    keyEvent.preventDefault();
                    keys[0] = (keyEvent.getAction().equals("up")) ? 0 : 1;
                }
                if (keyEvent.getKeyCode() == CAAT.Keys.RIGHT.getValue()) {
//                    keyEvent.preventDefault();
                    keys[1] = (keyEvent.getAction().equals("up")) ? 0 : 1;
                }

            }
        });
        
        Actor s0 = new Actor() {
            @Override
            public void mouseClick(CAATMouseEvent mouseEvent) throws Exception {
                Demo18.this.mouseClick(this);
            }
            
        }.
                setFillStyle( "#f00" ).
                setBounds(
                        40 + Math.random() * (director.width - 80),
                        40 + Math.random() * (director.height - 80), size, size);
        scene.addChild(s0);

        ShapeActor s1 = new ShapeActor() {
            @Override
            public void mouseClick(CAATMouseEvent mouseEvent) throws Exception {
                Demo18.this.mouseClick(this);
            }
        }.
                setShape( ShapeActor.Shape.CIRCLE ).
                setFillStyle( "#0f0" ).
                setBounds(
                        40 + Math.random() * (director.width - 80),
                        40 + Math.random() * (director.height - 80), size, size);

        scene.addChild(s1);

        StarActor s2= new StarActor() {
            @Override
            public void mouseClick(CAATMouseEvent mouseEvent) throws Exception {
                Demo18.this.mouseClick(this);
            }
        }.
        setBounds(
                40 + Math.random() * (director.width - 80),
                40 + Math.random() * (director.height - 80), size, size ).
                setStringStrokeStyle( "#00f" ).
                setFilled( false ).
                setOutlined( true ).
                initialize( 12, size, size/2 );
        scene.addChild( s2 );

        s0.mouseClick(null);
        
        Caatja.loop(30);
    }
    
    private void mouseClick(Actor actor) {

        if (selected != null) {
            selected.setAlpha(1);
            selected.emptyBehaviorList();
        }
        actor.setAlpha(.5);
        actor.emptyBehaviorList();
        actor.addBehavior(new RotateBehavior().setValues(0, 2 * Math.PI).setFrameTime(0, 5000).setCycle(true))
                .addBehavior(
                        new ScaleBehavior().setValues(.8, 1.5, .8, 1.5).setFrameTime(0, 3500).setPingPong()
                                .setCycle(true));
        actor.parent.setZOrder(actor, Integer.MAX_VALUE);
        selected = actor;

    }
}
