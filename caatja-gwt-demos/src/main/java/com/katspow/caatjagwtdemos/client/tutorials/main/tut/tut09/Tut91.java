package com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut09;

import java.util.Map;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.katspow.caatja.behavior.AlphaBehavior;
import com.katspow.caatja.behavior.BaseBehavior;
import com.katspow.caatja.behavior.BehaviorListener;
import com.katspow.caatja.behavior.ContainerBehavior;
import com.katspow.caatja.behavior.PathBehavior;
import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.core.canvas.CaatjaImage;
import com.katspow.caatja.event.CAATMouseEvent;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;
import com.katspow.caatja.foundation.actor.ActorContainer;
import com.katspow.caatja.foundation.actor.ActorRender;
import com.katspow.caatja.foundation.ui.ShapeActor;
import com.katspow.caatja.pathutil.Path;

public class Tut91 {
    
    int color_index=0;
    Label label = new Label();
    
    public void start(CaatjaCanvas canvas, Map<String, CaatjaImage> images) throws Exception {
        
        RootPanel.get().add(label);
        
        final Director _director_8 = new Director();
        _director_8.initialize(700, 300, canvas);
        _director_8.imagesCache = images;
        
        final Scene _scene_8 = _director_8.createScene();
        
        color_index=0;
        
        final String[] colors= new String[]{ "blue", "red", "yellow", "white", "gray" ,"orange" };

        // create a container, equals in size to the director.
        final ActorContainer root = new ActorContainer() {

            @Override
            public void mouseEnter(CAATMouseEvent mouseEvent) {
            }

            @Override
            public void mouseExit(CAATMouseEvent mouseEvent) {
            }

            @Override
            public void mouseMove(CAATMouseEvent mouseEvent) throws Exception {
                
                double r = 10 + 15 * Math.random();
                
                ShapeActor bubble;

                // is pressing control, add a Rectangle-Shaped blue Actor
                if ( Math.random()<.5 ) {
                    bubble = new ShapeActor().
                            setLocation(mouseEvent.point.x, mouseEvent.point.y).
                            setSize(r, r).
                            setShape(ShapeActor.Shape.RECTANGLE).
                            enableEvents(false).
                            setCompositeOp("lighter").
                            setFillStyle( colors[(color_index++)%colors.length] );

                } else {
                    // else, add a Circle-Shaped red Actor
                    bubble = new ShapeActor().
                            setLocation(mouseEvent.point.x, mouseEvent.point.y).
                            setSize(r, r).
                            enableEvents(false).
                            setCompositeOp("lighter").
                            setFillStyle( colors[(color_index++)%colors.length] );
                }

                this.addChild(bubble);

                // Add a container behavior, to hold a fading behavior and a moving
                // behavior.
                ContainerBehavior cb = new ContainerBehavior().
                        setFrameTime(_scene_8.time + 2000 + 1000 * Math.random(), 500).
                        addListener(
                                new BehaviorListener() {
                                    
                                    @Override
                                    public void behaviorExpired(BaseBehavior behavior, double time, Actor actor) {
                                        actor.
                                        setDiscardable(true).
                                        setExpired(true);
                                    }
                                    
                                    @Override
                                    public void behaviorApplied(BaseBehavior behavior, double time, double normalizeTime, Actor actor, Object value)
                                            throws Exception {
                                        
                                    }

                                    @Override
                                    public void behaviorStarted(BaseBehavior behavior, double time, Actor actor) {
                                        
                                    }
                                }
                        // when the container behavior is expired, expire and discard
                        // the actor.
                       );

                // fade from opacity to total transparency
                AlphaBehavior ab = new AlphaBehavior().
                        setFrameTime(0, 500).
                        setValues(1, 0);

                cb.addBehavior(ab);

                // follow a vertical path of at least 100 pixels
                PathBehavior tb = new PathBehavior().
                        setFrameTime(0, 500).
                        setPath(
                        new Path().setLinear(
                                bubble.x, bubble.y,
                                bubble.x, bubble.y - 100 - 100 * Math.random()));
                cb.addBehavior(tb);

                bubble.addBehavior(cb);
                
            }
            
            
        }.
                setBounds(0, 0,
                _director_8.canvas.getCoordinateSpaceWidth(), _director_8.canvas.getCoordinateSpaceHeight()).
                setFillStyle("#000000");


        _scene_8.addChild(root);
        
        _scene_8.onRenderEnd = new ActorRender() {
            @Override
            public void call(double time) {
                label.setText("Actors : " + root.getNumChildren());
            }
        };

        Caatja.loop(20);
        
    }

}
