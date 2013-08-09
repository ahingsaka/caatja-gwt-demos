package com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut09;

import java.util.Map;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.katspow.caatja.behavior.AlphaBehavior;
import com.katspow.caatja.behavior.BaseBehavior;
import com.katspow.caatja.behavior.BehaviorListener;
import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.core.canvas.CaatjaImage;
import com.katspow.caatja.event.CAATMouseEvent;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;
import com.katspow.caatja.foundation.actor.ActorContainer;
import com.katspow.caatja.foundation.actor.ActorRender;
import com.katspow.caatja.foundation.ui.StarActor;

public class Tut92 {
    
    int color_index=0;
    Label label = new Label();
    
    public void start(CaatjaCanvas canvas, Map<String, CaatjaImage> images) throws Exception {
        
        RootPanel.get().add(label);
        
        final Director _director_8 = new Director();
        _director_8.initialize(700, 300, canvas);
        _director_8.imagesCache = images;
        _director_8.setClear(false);
        
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
                
                            StarActor star = new StarActor().
                                    initialize( 6, r, 5 ).
                                    setInitialAngle( Math.random()*2*Math.PI ).
                                    setLocation(mouseEvent.point.x, mouseEvent.point.y).
                                    setSize(r, r).
                                    enableEvents(false).
                                    setCompositeOp("lighter").
                                    setFillStyle( colors[(color_index++)%colors.length] );
                
                            this.addChild(star);
                
                            // fade from opacity to total transparency
                            AlphaBehavior ab = new AlphaBehavior().
                                    setFrameTime(_scene_8.time + 2000, 500 +  500 * Math.random()).
                                    setValues(1, 0).
                                    addListener(new BehaviorListener() {
                                        
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
                                    });
                                
                
                            star.addBehavior(ab);
                
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
