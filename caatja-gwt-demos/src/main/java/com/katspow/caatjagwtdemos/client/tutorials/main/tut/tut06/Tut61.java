package com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut06;

import com.katspow.caatja.behavior.BaseBehavior;
import com.katspow.caatja.behavior.BehaviorListener;
import com.katspow.caatja.behavior.RotateBehavior;
import com.katspow.caatja.behavior.ScaleBehavior;
import com.katspow.caatja.behavior.SetForTimeReturnValue;
import com.katspow.caatja.behavior.listener.BehaviorExpiredListener;
import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;
import com.katspow.caatja.foundation.ui.ShapeActor;

public class Tut61 {
    
    public void start(CaatjaCanvas canvas) throws Exception {
        
        Director director_4 = new Director().initialize(160, 160, canvas);
        Scene scene_4 = director_4.createScene();
        
        ShapeActor _c4_rectangle_0 = new ShapeActor().
                setShape(ShapeActor.Shape.RECTANGLE).
                setLocation(50, 50).
                setSize(60, 60).
                setFillStyle("#f00").
                setStringStrokeStyle("#000");
        scene_4.addChild(_c4_rectangle_0);

        final ScaleBehavior _sb_c4_text_0 = new ScaleBehavior().
                setPingPong().
            // 50, 50 means to scale at 50% actor width and 50% actor height
            // its center.
                setValues(1d, 2d, 1d, 2d, .50, .50).
            // scale Behavior enabled by default. Start at time=2000ms, and
            // last for 3000ms.
                setFrameTime(2000, 3000);

        // unless otherwise stated, Behaviors are expired by default,
        // so this actor won"t rotate until instrumented to do so.
        final RotateBehavior _rb_c4_text_0 = new RotateBehavior().
            // 50, 50 means to scale at 50% actor width and 50% actor height
            // its center.
                setValues(0d, 2 * Math.PI, .50, .50);

        _c4_rectangle_0.addBehavior(_sb_c4_text_0);
        _c4_rectangle_0.addBehavior(_rb_c4_text_0);

        // when scale Behavior finishes, start rotation Behavior.
        _sb_c4_text_0.addListener(
                
                BehaviorListener.valueOfExpired(new BehaviorExpiredListener() {
                    public void onExpired(BaseBehavior behavior, double time, Actor actor) {
                        _rb_c4_text_0.setFrameTime(time, 3000);
                    }
                })
                
//                new BehaviorListener() {
//            @Override
//            public void behaviorExpired(BaseBehavior behavior, double time, Actor actor) {
//                _rb_c4_text_0.setFrameTime(time, 3000);
//            }
//            
//            @Override
//            public void behaviorApplied(BaseBehavior behavior, double time, double normalizeTime, Actor actor, SetForTimeReturnValue value)
//                    throws Exception {
//            }
//
//            @Override
//            public void behaviorStarted(BaseBehavior behavior, double time, Actor actor) {
//                
//            }
//        }
                );
        
        _rb_c4_text_0.addListener(
                
                BehaviorListener.valueOfExpired(new BehaviorExpiredListener() {
                    public void onExpired(BaseBehavior behavior, double time, Actor actor) {
                        _sb_c4_text_0.setFrameTime(time, 3000);
                    }
                })
                
                // TODO Remove
//                new BehaviorListener() {
//            @Override
//            public void behaviorExpired(BaseBehavior behavior, double time, Actor actor) {
//                _sb_c4_text_0.setFrameTime(time, 3000);
//            }
//            
//            @Override
//            public void behaviorApplied(BaseBehavior behavior, double time, double normalizeTime, Actor actor, SetForTimeReturnValue value)
//                    throws Exception {
//            }
//
//            @Override
//            public void behaviorStarted(BaseBehavior behavior, double time, Actor actor) {
//                
//            }
//        }
                
                );
        
        Caatja.loop(30);
        
    }

}
