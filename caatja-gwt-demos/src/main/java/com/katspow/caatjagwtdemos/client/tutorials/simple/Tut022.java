package com.katspow.caatjagwtdemos.client.tutorials.simple;

import com.katspow.caatja.behavior.BaseBehavior;
import com.katspow.caatja.behavior.BehaviorListener;
import com.katspow.caatja.behavior.RotateBehavior;
import com.katspow.caatja.behavior.ScaleBehavior;
import com.katspow.caatja.behavior.listener.BehaviorExpiredListener;
import com.katspow.caatja.core.canvas.CaatjaColor;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;
import com.katspow.caatja.foundation.ui.ShapeActor;

/**
 * A small red square outlined in black that scales and rotates (loop).
 * 
 * @author ahingsaka
 *
 */
public class Tut022 {

    public static final void init(Director director_4) throws Exception {

//        Director director_4 = new Director().initialize(160, 160, null);
        Scene scene_4 = director_4.createScene();
        scene_4.setFillStyle("#ffffff");

        ShapeActor _c4_rectangle_0 = (ShapeActor) new ShapeActor();
        
        _c4_rectangle_0.setShape(ShapeActor.Shape.RECTANGLE)
            .setLocation(50, 50)
            .setSize(60, 60)
            .setFillStrokeStyle(CaatjaColor.valueOf("#ff0000"));
        
        _c4_rectangle_0.setStrokeStyle(CaatjaColor.valueOf("#000000"));
        scene_4.addChild(_c4_rectangle_0);

        final ScaleBehavior _sb_c4_text_0 = new ScaleBehavior().setPingPong().
                setValues( 1d,2d,1d,2d, .5,.5 ).
                // scale Behavior enabled by default. Start at time=2000ms, and
                // last for 3000ms.
                setFrameTime(2000,3000);

        // unless otherwise stated, Behaviors are expired by default, so this
        // actor won't rotate until instrumented to do so.
        final RotateBehavior _rb_c4_text_0 = new RotateBehavior().
                setValues(0,2*Math.PI, .5, .5);

        _c4_rectangle_0.addBehavior(_sb_c4_text_0);
        _c4_rectangle_0.addBehavior(_rb_c4_text_0);

        // when scale Behavior finishes, start rotation Behavior.
        
        BehaviorListener behaviorListener = new BehaviorListener();
        
        _sb_c4_text_0.addListener(behaviorListener);
        
        _sb_c4_text_0.addListener(BehaviorListener.valueOfExpired(new BehaviorExpiredListener() {
            public void call(BaseBehavior behavior, double time, Actor actor) {
                _rb_c4_text_0.setFrameTime(time, 3000);
            }
        }));
        

        // TODO Remove
//        _sb_c4_text_0.addListener(new BehaviorListener() {
//            @Override
//            public void behaviorExpired(BaseBehavior behaviour, double time, Actor actor) {
//                _rb_c4_text_0.setFrameTime(time, 3000);
//            }
//
//            @Override
//            public void behaviorApplied(BaseBehavior behavior, double time, double normalizeTime, Actor actor, SetForTimeReturnValue value) {
//                
//            }
//
//            @Override
//            public void behaviorStarted(BaseBehavior behavior, double time, Actor actor) {
//                
//            }
//        });

        // when rotation Behavior finishes, start scale Behavior.
        _rb_c4_text_0.addListener(BehaviorListener.valueOfExpired(new BehaviorExpiredListener() {
            public void call(BaseBehavior behavior, double time, Actor actor) {
                _sb_c4_text_0.setFrameTime(time, 3000);
            }
        }));
        
//        _rb_c4_text_0.addListener(new BehaviorListener() {
//            @Override
//            public void behaviorExpired(BaseBehavior behaviour, double time, Actor actor) {
//                _sb_c4_text_0.setFrameTime(time, 3000);
//            }
//
//            @Override
//            public void behaviorApplied(BaseBehavior behavior, double time, double normalizeTime, Actor actor, SetForTimeReturnValue value) {
//                
//            }
//
//            @Override
//            public void behaviorStarted(BaseBehavior behavior, double time, Actor actor) {
//                
//            }
//        });

//        Caatja.loop(30);

    }

}
