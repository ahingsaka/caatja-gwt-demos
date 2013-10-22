package com.katspow.caatjagwtdemos.client.welcome.demos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.katspow.caatja.behavior.BaseBehavior;
import com.katspow.caatja.behavior.BehaviorListener;
import com.katspow.caatja.behavior.PathBehavior;
import com.katspow.caatja.behavior.RotateBehavior;
import com.katspow.caatja.behavior.ScaleBehavior;
import com.katspow.caatja.behavior.SetForTimeReturnValue;
import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.core.canvas.CaatjaImage;
import com.katspow.caatja.event.CAATMouseEvent;
import com.katspow.caatja.event.MouseListener;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;
import com.katspow.caatja.foundation.actor.ActorContainer;
import com.katspow.caatja.foundation.image.SpriteImage;
import com.katspow.caatja.pathutil.Path;

public class Demo9 {
    
    int starCacheIndex= 0;
    
    public void start(CaatjaCanvas canvas, Map<String, CaatjaImage> images) throws Exception {
        final Director director = new Director().initialize(800, 500, canvas);
        director.imagesCache = images;
        starCacheIndex = 0;
        
        Scene scene = director.createScene();
        
        SpriteImage ci= new SpriteImage().
                initialize((director.getImage("numbers")), 9, 9);
        SpriteImage cistars= new SpriteImage().
                initialize((director.getImage("stars")), 1, 6);

//    int i,j;
    int rows=           8;
    int columns=        13;

    final List<Actor> starCache= new ArrayList<Actor>();

    /**
     * create a cache of 400 falling stars.
     */
    final ActorContainer starContainer= new ActorContainer().
            setBounds(0,0,director.width,director.height).
            enableEvents(false);
    for( int i=0; i<400; i++ ) {
        starCache.add( createStarCache(director, cistars) );
    }
    ActorContainer numberContainer= new ActorContainer().
            setBounds(0,0,director.width,director.height);


    scene.addChild(numberContainer);
    scene.addChild(starContainer);
    

    /**
     * create 10x10 actors with random index.
     */
    int numberW= ci.singleWidth;
    int numberH= ci.singleHeight;

    int paddingLeft= 15;
    int paddingTop=  15;

    int numberPadding= 10;

    for( int i=0; i<rows; i++ ) {
        for( int j=0; j<columns; j++ ) {

            final Actor number= new Actor() {

                // TODO Remove
//                @Override
//                public void mouseClick(CAATMouseEvent mouseEvent) throws Exception {
//                    int NS= 20;
//                  for( int i=0; i<NS; i++ ) {
//                      Actor star= starCache.get((i+starCacheIndex++)%starCache.size());
//  
//                      double x= this.x+this.width/2;
//                      double y= this.y+this.height/2;
//                      int sgnX = (Math.random()<.5?1:-1);
//                      int sgnY= (Math.random()<.5?1:-1);
//                      double cpx = x+ (20+Math.random()*80)*sgnX;
//                      double cpy= y+ (20+Math.random()*40)*sgnY;
//  
//                      double fpy = director.height+(50*Math.random());
//                      double fpx= cpx+(80*Math.random())*sgnX;
//                      
//                      star.emptyBehaviorList().
//                              addBehavior(
//                                      new PathBehavior().
//                                              setFrameTime( this.time, 600+(400*Math.random()) ).
//                                              setPath(
//                                                      new Path().
//                                                              beginPath( x,y ).
//                                                              addQuadricTo( cpx, cpy, fpx, fpy ).
//                                                              endPath()
//                                              ).
//                                              addListener(
//                                                      new BehaviorListener() {
//                                                        
//                                                        @Override
//                                                        public void behaviorExpired(BaseBehavior behavior, double time, Actor actor) {
//                                                            actor.setExpired(true);
//                                                        }
//                                                        
//                                                        @Override
//                                                        public void behaviorApplied(BaseBehavior behavior, double time, double normalizeTime, Actor actor, SetForTimeReturnValue value)
//                                                                throws Exception {
//                                                            
//                                                        }
//
//                                                        @Override
//                                                        public void behaviorStarted(BaseBehavior behavior, double time,
//                                                                Actor actor) {
//                                                            
//                                                        }
//                                                    }
//                                      )).
//                              setDiscardable(true).
//                              setFrameTime( this.time, Double.MAX_VALUE );
//                      
//                      starContainer.addChild(star);
//                  }
//                }

                // TODO Remove
//                @Override
//                public void mouseEnter(CAATMouseEvent mouseEvent) {
//                    this.parent.setZOrder( this, Integer.MAX_VALUE );
//                  this.emptyBehaviorList();
//                  this.addBehavior(
//                          new ScaleBehavior().
//                                  setFrameTime( this.time, 500 ).
//                                  setValues( 1, 2, 1, 2 ).
//                                  setPingPong()
//                          ).
//                      addBehavior(
//                          new RotateBehavior().
//                                  setFrameTime( this.time, 500 ).
//                                  setValues( 0, 2*Math.PI )
//                          );
//  
//                  Caatja.setCursor("pointer");
//                }

                // TODO Remove
//                @Override
//                public void mouseExit(CAATMouseEvent mouseEvent) {
//                	Caatja.setCursor("default");
//                }
                
                
            }.
                    setBackgroundImage(ci.getRef(),true).
                    setLocation(
                        paddingLeft + j*(numberPadding/2+numberW),
                        paddingTop  + i*(numberPadding/2+numberH)).
                    setSpriteIndex( (int)(Math.random()*ci.rows*ci.columns)>>0 );
            
            number.setMouseExitListener(new MouseListener() {
                public void call(CAATMouseEvent e) throws Exception {
                    Caatja.setCursor("default");
                }
            });
            
            number.setMouseEnterListener(new MouseListener() {
                public void call(CAATMouseEvent e) throws Exception {
                    number.parent.setZOrder( number, Integer.MAX_VALUE );
                    number.emptyBehaviorList();
                    number.addBehavior(
                            new ScaleBehavior().
                                    setFrameTime( number.time, 500 ).
                                    setValues( 1, 2, 1, 2 ).
                                    setPingPong()
                            ).
                        addBehavior(
                            new RotateBehavior().
                                    setFrameTime( number.time, 500 ).
                                    setValues( 0, 2*Math.PI )
                            );
    
                    Caatja.setCursor("pointer");
                }
            });
            
            number.setMouseClickListener(new MouseListener() {
                public void call(CAATMouseEvent e) throws Exception {
                    
                    int NS= 20;
                    for( int i=0; i<NS; i++ ) {
                        Actor star= starCache.get((i+starCacheIndex++)%starCache.size());
    
                        double x= number.x+number.width/2;
                        double y= number.y+number.height/2;
                        int sgnX = (Math.random()<.5?1:-1);
                        int sgnY= (Math.random()<.5?1:-1);
                        double cpx = x+ (20+Math.random()*80)*sgnX;
                        double cpy= y+ (20+Math.random()*40)*sgnY;
    
                        double fpy = director.height+(50*Math.random());
                        double fpx= cpx+(80*Math.random())*sgnX;
                        
                        star.emptyBehaviorList().
                                addBehavior(
                                        new PathBehavior().
                                                setFrameTime( number.time, 600+(400*Math.random()) ).
                                                setPath(
                                                        new Path().
                                                                beginPath( x,y ).
                                                                addQuadricTo( cpx, cpy, fpx, fpy ).
                                                                endPath()
                                                ).
                                                addListener(
                                                        new BehaviorListener() {
                                                          
                                                          @Override
                                                          public void behaviorExpired(BaseBehavior behavior, double time, Actor actor) {
                                                              actor.setExpired(true);
                                                          }
                                                          
                                                          @Override
                                                          public void behaviorApplied(BaseBehavior behavior, double time, double normalizeTime, Actor actor, SetForTimeReturnValue value)
                                                                  throws Exception {
                                                              
                                                          }

                                                          @Override
                                                          public void behaviorStarted(BaseBehavior behavior, double time,
                                                                  Actor actor) {
                                                              
                                                          }
                                                      }
                                        )).
                                setDiscardable(true).
                                setFrameTime( number.time, Double.MAX_VALUE );
                        
                        starContainer.addChild(star);
                    }
                    
                }
            });

            numberContainer.addChild(number);
        }
    }
    
    Caatja.loop(30);
    }
    
    private Actor createStarCache(Director director, SpriteImage ci) {
        Actor actor= new Actor().
            setBackgroundImage( ci.getRef(), true ).
            setSpriteIndex( (int)(Math.random()*6)>>0 ).
            enableEvents(false).
            setOutOfFrameTime();

        return actor;
    }

}
