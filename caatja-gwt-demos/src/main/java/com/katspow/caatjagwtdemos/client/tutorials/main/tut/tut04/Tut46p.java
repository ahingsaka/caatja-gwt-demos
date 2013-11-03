package com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut04;

import java.util.Map;

import com.katspow.caatja.behavior.AlphaBehavior;
import com.katspow.caatja.behavior.Interpolator;
import com.katspow.caatja.behavior.ScaleBehavior;
import com.katspow.caatja.core.CAAT;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.core.canvas.CaatjaImage;
import com.katspow.caatja.event.CAATMouseEvent;
import com.katspow.caatja.event.MouseListener;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Director.Resize;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;
import com.katspow.caatja.foundation.actor.ActorContainer;
import com.katspow.caatja.foundation.image.SpriteImage;

public class Tut46p {
    

    int T= 2500;

    public void start(CaatjaCanvas canvas, Map<String, CaatjaImage> images) throws Exception {
        
        CAAT.setCoordinateClamp(false);
        
        Director director = new Director().initialize(800, 600, canvas);
        director.imagesCache = images;
        director.enableResizeEvents(Resize.PROPORTIONAL, null);
        
        T= 2500;
        
        final Scene scene = director.createScene().setFillStyle("#fff");
        
        // create a sprite image of 1 row by 6 columns
        final SpriteImage starsImage= new SpriteImage().
                initialize((director.getImage("stars")), 1,6 );
        
        final ActorContainer bg= new ActorContainer() {

            // TODO Remove
//            @Override
//            public void mouseMove(CAATMouseEvent mouseEvent) throws Exception {
//                
//              Actor actorStar= new Actor().
//              // set background image to be a reference of a SpriteImage instance
//              // and set actor"s size equal to a SpriteImage"s subimage size
//                  setBackgroundImage(
//                      starsImage.getRef(), true ).
//              // set background as a random SpriteImage"s subimage.
//                  setSpriteIndex((int) (Math.random()*6)>>0 ).
//              // center the actor on mouse position
//                  centerOn( mouseEvent.point.x, mouseEvent.point.y).
//              // when the actor expires, remove in from the director
//                  setDiscardable(true).
//              // avoid mouse event handling.
//                  enableEvents(false).
//              // make this actor last to T milliseconds (1000)
//                  setFrameTime(scene.time, T).
//              // add a scaling behavior
//                  addBehavior(
//                      new ScaleBehavior().
//                          setFrameTime(scene.time, T).
//                          setValues( 1,5, 1,5 ).
//                          setInterpolator(
//                              new Interpolator().createExponentialInInterpolator(
//                                  3,
//                                  false)
//                          )
//                  ).
//              // add an alpha behavior so the actor takes 1000 ms to fade out to zero alpha
//                  addBehavior(
//                      new AlphaBehavior().
//                          setFrameTime(scene.time, T).
//                          setValues( 1, 0 ) );
//
//          // add the actor.
//          this.addChild(actorStar);
//                
//            }

            // TODO Remove
//            @Override
//            public void mouseDrag(CAATMouseEvent mouseEvent) {
//                
//                Actor actorStar= new Actor().
//                        // set background image to be a reference of a SpriteImage instance
//                        // and set actor"s size equal to a SpriteImage"s subimage size
//                            setBackgroundImage(
//                                starsImage.getRef(), true ).
//                        // set background as a random SpriteImage"s subimage.
//                            setSpriteIndex((int) (Math.random()*6)>>0 ).
//                        // center the actor on mouse position
//                            centerOn( mouseEvent.point.x, mouseEvent.point.y).
//                        // when the actor expires, remove in from the director
//                            setDiscardable(true).
//                        // avoid mouse event handling.
//                            enableEvents(false).
//                        // make this actor last to T milliseconds (1000)
//                            setFrameTime(scene.time, T).
//                        // add a scaling behavior
//                            addBehavior(
//                                new ScaleBehavior().
//                                    setFrameTime(scene.time, T).
//                                    setValues( 1,5, 1,5 ).
//                                    setInterpolator(
//                                        new Interpolator().createExponentialInInterpolator(
//                                            3,
//                                            false)
//                                    )
//                            ).
//                        // add an alpha behavior so the actor takes 1000 ms to fade out to zero alpha
//                            addBehavior(
//                                new AlphaBehavior().
//                                    setFrameTime(scene.time, T).
//                                    setValues( 1, 0 ) );
//
//                    // add the actor.
//                    try {
//                        this.addChild(actorStar);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//            }
            
            
        }.
                setBounds(0,0,director.width,director.height).
                setFillStyle("#222");
        
        bg.setMouseDragListener(new MouseListener() {
            public void call(CAATMouseEvent mouseEvent) throws Exception {
                Actor actorStar= new Actor().
                        // set background image to be a reference of a SpriteImage instance
                        // and set actor"s size equal to a SpriteImage"s subimage size
                            setBackgroundImage(
                                starsImage.getRef(), true ).
                        // set background as a random SpriteImage"s subimage.
                            setSpriteIndex((int) (Math.random()*6)>>0 ).
                        // center the actor on mouse position
                            centerOn( mouseEvent.point.x, mouseEvent.point.y).
                        // when the actor expires, remove in from the director
                            setDiscardable(true).
                        // avoid mouse event handling.
                            enableEvents(false).
                        // make this actor last to T milliseconds (1000)
                            setFrameTime(scene.time, T).
                        // add a scaling behavior
                            addBehavior(
                                new ScaleBehavior().
                                    setFrameTime(scene.time, T).
                                    setValues( 1,5, 1,5 ).
                                    setInterpolator(
                                        Interpolator.createExponentialInInterpolator(
                                            3,
                                            false)
                                    )
                            ).
                        // add an alpha behavior so the actor takes 1000 ms to fade out to zero alpha
                            addBehavior(
                                new AlphaBehavior().
                                    setFrameTime(scene.time, T).
                                    setValues( 1, 0 ) );

                    // add the actor.
                    try {
                        bg.addChild(actorStar);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }
        });
        
        bg.setMouseMoveListener(new MouseListener() {
            public void call(CAATMouseEvent e) throws Exception {
                Actor actorStar= new Actor().
                        // set background image to be a reference of a SpriteImage instance
                        // and set actor"s size equal to a SpriteImage"s subimage size
                            setBackgroundImage(
                                starsImage.getRef(), true ).
                        // set background as a random SpriteImage"s subimage.
                            setSpriteIndex((int) (Math.random()*6)>>0 ).
                        // center the actor on mouse position
                            centerOn( e.point.x, e.point.y).
                        // when the actor expires, remove in from the director
                            setDiscardable(true).
                        // avoid mouse event handling.
                            enableEvents(false).
                        // make this actor last to T milliseconds (1000)
                            setFrameTime(scene.time, T).
                        // add a scaling behavior
                            addBehavior(
                                new ScaleBehavior().
                                    setFrameTime(scene.time, T).
                                    setValues( 1,5, 1,5 ).
                                    setInterpolator(
                                        Interpolator.createExponentialInInterpolator(
                                            3,
                                            false)
                                    )
                            ).
                        // add an alpha behavior so the actor takes 1000 ms to fade out to zero alpha
                            addBehavior(
                                new AlphaBehavior().
                                    setFrameTime(scene.time, T).
                                    setValues( 1, 0 ) );

                    // add the actor.
                    bg.addChild(actorStar);
            }
        });

        scene.addChild(bg);

    }

}
