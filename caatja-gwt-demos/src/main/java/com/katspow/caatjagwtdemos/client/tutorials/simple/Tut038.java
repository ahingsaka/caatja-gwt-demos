package com.katspow.caatjagwtdemos.client.tutorials.simple;

import com.katspow.caatja.behavior.AlphaBehavior;
import com.katspow.caatja.behavior.BaseBehavior;
import com.katspow.caatja.behavior.BehaviorListener;
import com.katspow.caatja.behavior.ContainerBehavior;
import com.katspow.caatja.behavior.PathBehavior;
import com.katspow.caatja.behavior.SetForTimeReturnValue;
import com.katspow.caatja.behavior.listener.BehaviorAppliedListener;
import com.katspow.caatja.behavior.listener.BehaviorExpiredListener;
import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.event.CAATMouseEvent;
import com.katspow.caatja.event.MouseListener;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;
import com.katspow.caatja.foundation.actor.ActorContainer;
import com.katspow.caatja.foundation.ui.ShapeActor;
import com.katspow.caatja.pathutil.Path;

/**
 * On mouse move, red circles appears and go up like bubbles.
 * 
 * @author ahingsaka
 *
 */
public class Tut038 {
    
    public void init() throws Exception {
        final Director _director_8= new Director().initialize(
                400,
                200,
                null);

        final Scene _scene_8= _director_8.createScene();

        _director_8.addScene( _scene_8 );

        // create a container, equals in size to the director.
        final ActorContainer root= (ActorContainer) new ActorContainer() {
            
            // TODO Remove
//            @Override
//            public void mouseMove(CAATMouseEvent mouseEvent) throws Exception {
//                double r= 1+10*Math.random();
//
//                ShapeActor bubble;
//
//                // is pressing control, add a Rectangle-Shaped blue Actor
//                if (mouseEvent.isControlDown()) {
//                    bubble= (ShapeActor) new ShapeActor().
//                        setLocation( mouseEvent.point.x, mouseEvent.point.y ).
//                        setSize( 5+r, 5+r );
//                        bubble.setShape( ShapeActor.Shape.RECTANGLE ).
//                        enableEvents(false);
//                        bubble.setCompositeOp("lighter").
//                        setFillStyle("blue");
//
//                } else {
//                // else, add a Circle-Shaped red Actor
//                    bubble= (ShapeActor) new ShapeActor().
//                        setLocation( mouseEvent.point.x, mouseEvent.point.y ).
//                        setSize( 5+r, 5+r ).
//                        enableEvents(false);
//                        bubble.setCompositeOp("lighter").
//                        setFillStyle("red");
//                }
//
//                this.addChild(bubble);
//
//                // Add a container behavior, to hold a fading behavior and a moving
//                // behavior.
//                ContainerBehavior cb= (ContainerBehavior) new ContainerBehavior().
//                    setFrameTime( _scene_8.time+2000+1000*Math.random(), 500 ).
//                    addListener(
//                            new BehaviorListener() {
//                                
//                                @Override
//                                public void behaviorExpired(BaseBehavior behaviour, double time, Actor actor) {
//                                    actor.
//                                    setDiscardable(true).
//                                    setExpired(true);
//                                }
//
//                                @Override
//                                public void behaviorApplied(BaseBehavior behavior, double time, double normalizeTime,
//                                        Actor actor, SetForTimeReturnValue value) {
//                                    
//                                }
//
//                                @Override
//                                public void behaviorStarted(BaseBehavior behavior, double time, Actor actor) {
//                                    
//                                }
//                            }
//                            );
//
//                AlphaBehavior ab= (AlphaBehavior) new AlphaBehavior().
//                    setFrameTime( 0, 500 );
//                    ab.setValues( 1, 0 );
//
//                cb.addBehavior(ab);
//
//                PathBehavior tb= (PathBehavior) new PathBehavior().
//                    setFrameTime( 0, 500 );
//                    tb.setPath(
//                        new Path().setLinear(
//                                bubble.x, bubble.y,
//                                bubble.x, bubble.y-100-100*Math.random() ) );
//                cb.addBehavior(tb);
//
//                bubble.addBehavior( cb );
//            }
        }.
            setBounds(0,0,
                 _director_8.width,_director_8.height).
            setFillStyle("#000000");
        
        
        root.setMouseMoveListener(new MouseListener() {
            public void call(CAATMouseEvent e) throws Exception {
                double r= 1+10*Math.random();

                ShapeActor bubble;

                // is pressing control, add a Rectangle-Shaped blue Actor
                if (e.isControlDown()) {
                    bubble= (ShapeActor) new ShapeActor().
                        setLocation( e.point.x, e.point.y ).
                        setSize( 5+r, 5+r );
                        bubble.setShape( ShapeActor.Shape.RECTANGLE ).
                        enableEvents(false);
                        bubble.setCompositeOp("lighter").
                        setFillStyle("blue");

                } else {
                // else, add a Circle-Shaped red Actor
                    bubble= (ShapeActor) new ShapeActor().
                        setLocation( e.point.x, e.point.y ).
                        setSize( 5+r, 5+r ).
                        enableEvents(false);
                        bubble.setCompositeOp("lighter").
                        setFillStyle("red");
                }

                root.addChild(bubble);

                // Add a container behavior, to hold a fading behavior and a moving
                // behavior.
                ContainerBehavior cb= (ContainerBehavior) new ContainerBehavior().
                    setFrameTime( _scene_8.time+2000+1000*Math.random(), 500 ).
                    addListener(
                            BehaviorListener.valueOfExpired(new BehaviorExpiredListener() {
                                public void onExpired(BaseBehavior behavior, double time, Actor actor) {
                                    actor.setDiscardable(true).setExpired(true);
                                }
                            })
                            
                            // TODO Remove
//                            new BehaviorListener() {
//                                
//                                @Override
//                                public void behaviorExpired(BaseBehavior behaviour, double time, Actor actor) {
//                                    actor.
//                                    setDiscardable(true).
//                                    setExpired(true);
//                                }
//
//                                @Override
//                                public void behaviorApplied(BaseBehavior behavior, double time, double normalizeTime,
//                                        Actor actor, SetForTimeReturnValue value) {
//                                    
//                                }
//
//                                @Override
//                                public void behaviorStarted(BaseBehavior behavior, double time, Actor actor) {
//                                    
//                                }
//                            }
                            );

                AlphaBehavior ab= (AlphaBehavior) new AlphaBehavior().
                    setFrameTime( 0, 500 );
                    ab.setValues( 1, 0 );

                cb.addBehavior(ab);

                PathBehavior tb= (PathBehavior) new PathBehavior().
                    setFrameTime( 0, 500 );
                    tb.setPath(
                        new Path().setLinear(
                                bubble.x, bubble.y,
                                bubble.x, bubble.y-100-100*Math.random() ) );
                cb.addBehavior(tb);

                bubble.addBehavior( cb );
            }
        });
        
        _scene_8.addChild( root );

//        var span= document.getElementById("_c8_data");
//
//        // hook on endAnimate to show on document how many Actors are alive.
//        _scene_8.endAnimate= function _endAnimate(director,time) {
//            // chain to previous method. DON"T FORGET TO DO THIS!!!.
//            Scene.superclass.endAnimate.call(this,director,time);
//            span.innerText= "Actors: "+root.childrenList.length;
//        }

        Caatja.loop(20);
    }

}
