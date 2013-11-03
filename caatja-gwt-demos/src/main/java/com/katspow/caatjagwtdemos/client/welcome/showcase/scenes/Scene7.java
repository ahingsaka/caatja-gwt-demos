package com.katspow.caatjagwtdemos.client.welcome.showcase.scenes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.katspow.caatja.behavior.AlphaBehavior;
import com.katspow.caatja.behavior.BaseBehavior;
import com.katspow.caatja.behavior.BehaviorListener;
import com.katspow.caatja.behavior.ContainerBehavior;
import com.katspow.caatja.behavior.Interpolator;
import com.katspow.caatja.behavior.PathBehavior;
import com.katspow.caatja.behavior.SetForTimeReturnValue;
import com.katspow.caatja.behavior.listener.BehaviorExpiredListener;
import com.katspow.caatja.core.canvas.CaatjaColor;
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
import com.katspow.caatja.pathutil.Path;

public class Scene7 {
    
    public static Scene init(final Director director) throws Exception {
        
        final TextActor children= new TextActor();
        children.setFont("20px sans-serif");
        children.textAlign="left";
        children.textBaseline="top";
        children.setText("");
        children.setLocation(15,20);
        children.setTextFillStyle(CaatjaColor.valueOf("white"));
        children.outlineColor= "red";
        children.outline= true;
        
        final Scene scene = new Scene() {
            @Override
            public boolean animate(Director director, double time) throws Exception {
                
                int bubble = 0;
                if (this.childrenList.size() > 0) {
                    bubble = ((ActorContainer) this.childrenList.get(0)).childrenList.size();
                }
                
                children.setText( "Bubles: "+ bubble );
                super.animate(director, time);
                return true;
            }
        };
        
        
        
        final ActorContainer root= new ActorContainer() {
            
            @Override
            public void paint(Director director, double time) {
                director.ctx.drawImage( director.getImage("plants"), 0, 0, this.width, this.height );
            }

            // TODO Remove
//            @Override
//            public void mouseEnter (CAATMouseEvent mouseEvent) {
//                
//            }
            
            // TODO Remove
//            @Override
//            public void mouseExit (CAATMouseEvent mouseEvent) {
//                
//            }
            
            // TODO Remove
//            @Override
//            public void mouseMove (CAATMouseEvent mouseEvent) throws Exception {
//                int imgIndex= ((int)(Math.random()*3.99) >> 0)+1;
//                
//                CompoundImage conpoundimage = new CompoundImage();
//                conpoundimage.initialize(director.getImage("buble"+imgIndex),1,1);
//
//                SpriteActor burbuja= new SpriteActor();
//                burbuja.setAnimationImageIndex(Arrays.asList(0) );
//                burbuja.setSpriteImage( conpoundimage );
//                burbuja.setLocation( mouseEvent.point.x, mouseEvent.point.y );
//                burbuja.mouseEnabled= false;
//                
//                this.addChild(burbuja);
//
//                ContainerBehavior cb= new ContainerBehavior();
//                cb.actor= burbuja;
//
//                cb.setFrameTime( scene.time+2000+1000*Math.random(), 500 );
//                cb.addListener(new BehaviorListener() {
//                    @Override
//                    public void behaviorExpired(BaseBehavior behaviour, double time, Actor actor) {
//                        behaviour.actor.discardable= true;
//                        behaviour.actor.setExpired(true);
//                    }
//
//                    public void behaviorApplied(BaseBehavior behavior, double time, double normalizeTime, Actor actor,
//                            SetForTimeReturnValue value) {
//                    }
//
//                    @Override
//                    public void behaviorStarted(BaseBehavior behavior, double time, Actor actor) {
//                        
//                    }
//                });
//                
//                AlphaBehavior ab = new AlphaBehavior();
//                ab.setFrameTime(0, 500);
//                ab.startAlpha = 1;
//                ab.endAlpha = 0;
//                cb.addBehavior(ab);
//
//                PathBehavior tb = new PathBehavior();
//                tb.setFrameTime(0, 500);
//                tb.setPath(new Path().setLinear(burbuja.x, burbuja.y, burbuja.x, burbuja.y - 100 - 100 * Math.random()));
//                cb.addBehavior(tb);
//
//                burbuja.addBehavior(cb);
//
//            }
            
        };
        
        root.setMouseEnterListener(null);
        root.setMouseExitListener(null);
        root.setMouseMoveListener(new MouseListener() {
            public void call(CAATMouseEvent e) throws Exception {
                int imgIndex= ((int)(Math.random()*3.99) >> 0)+1;
                
                CompoundImage conpoundimage = new CompoundImage();
                conpoundimage.initialize(director.getImage("buble"+imgIndex),1,1);

                SpriteActor burbuja= new SpriteActor();
                burbuja.setAnimationImageIndex(Arrays.asList(0) );
                burbuja.setSpriteImage( conpoundimage );
                burbuja.setLocation( e.point.x, e.point.y );
                burbuja.mouseEnabled= false;
                
                root.addChild(burbuja);

                ContainerBehavior cb= new ContainerBehavior();
                cb.actor= burbuja;

                cb.setFrameTime( scene.time+2000+1000*Math.random(), 500 );
                cb.addListener(
                        
                        BehaviorListener.valueOfExpired(new BehaviorExpiredListener() {
                            public void call(BaseBehavior behavior, double time, Actor actor) {
                                behavior.actor.discardable= true;
                                behavior.actor.setExpired(true);
                            }
                        })
                        
//                        new BehaviorListener() {
//                    @Override
//                    public void behaviorExpired(BaseBehavior behaviour, double time, Actor actor) {
//                        behaviour.actor.discardable= true;
//                        behaviour.actor.setExpired(true);
//                    }
//
//                    public void behaviorApplied(BaseBehavior behavior, double time, double normalizeTime, Actor actor,
//                            SetForTimeReturnValue value) {
//                    }
//
//                    @Override
//                    public void behaviorStarted(BaseBehavior behavior, double time, Actor actor) {
//                        
//                    }
//                }
                        
                        );
                
                AlphaBehavior ab = new AlphaBehavior();
                ab.setFrameTime(0, 500);
                ab.startAlpha = 1;
                ab.endAlpha = 0;
                cb.addBehavior(ab);

                PathBehavior tb = new PathBehavior();
                tb.setFrameTime(0, 500);
                tb.setPath(new Path().setLinear(burbuja.x, burbuja.y, burbuja.x, burbuja.y - 100 - 100 * Math.random()));
                cb.addBehavior(tb);

                burbuja.addBehavior(cb);

            }
        });
        
        root.setBounds(0,0,director.canvas.getCoordinateSpaceWidth(),director.canvas.getCoordinateSpaceHeight());
      //root.setImage( director.getImage('plants') );
        root.fillStyle=null;
        scene.addChild( root );
        
        List<CompoundImage> conpoundimagefish = new ArrayList<CompoundImage>();
        conpoundimagefish.add( new CompoundImage().initialize( director.getImage("fish"),  1, 3)) ;
        conpoundimagefish.add( new CompoundImage().initialize( director.getImage("fish2"), 1, 3) );
        conpoundimagefish.add( new CompoundImage().initialize( director.getImage("fish3"), 1, 3) );
        conpoundimagefish.add( new CompoundImage().initialize( director.getImage("fish4"), 1, 3) );

        for( int j=0; j<200; j++ ) {
            SpriteActor fish = new SpriteActor();
            fish.setAnimationImageIndex(Arrays.asList(0,1,2,1) );
            fish.changeFPS= 300;
            fish.setSpriteImage(conpoundimagefish.get(j % 4));
            scene.addChild(fish);

            PathBehavior pbfish= new PathBehavior();
            pbfish.autoRotate= true;
            pbfish.setPath( new Path().setLinear(
                    Math.random()*director.width,
                    Math.random()*director.height,
                    Math.random()*director.width,
                    Math.random()*director.height) );
            pbfish.setInterpolator( Interpolator.createExponentialInOutInterpolator(2,false) );
            pbfish.setFrameTime( 0, 2500+2500*Math.random() );
            
            pbfish.addListener(
                    
                    BehaviorListener.valueOfExpired(new BehaviorExpiredListener() {
                        public void call(BaseBehavior behavior, double time, Actor actor) {
                            Pt endCoord= ((PathBehavior) behavior).path.endCurvePosition();
                            
                            ((PathBehavior) behavior).setPath( new Path().setCubic(
                                endCoord.x,
                                endCoord.y,
                                Math.random()*director.width,
                                Math.random()*director.height,
                                Math.random()*director.width,
                                Math.random()*director.height,
                                Math.random()*director.width,
                                Math.random()*director.height));
                            
                            behavior.setFrameTime( scene.time, 3000+Math.random()*3000 );
                        }
                    })
                    
//                    new BehaviorListener() {
//                @Override
//                public void behaviorExpired(BaseBehavior behaviour, double time, Actor actor) {
//                    Pt endCoord= ((PathBehavior) behaviour).path.endCurvePosition();
//                    
//                    ((PathBehavior) behaviour).setPath( new Path().setCubic(
//                        endCoord.x,
//                        endCoord.y,
//                        Math.random()*director.width,
//                        Math.random()*director.height,
//                        Math.random()*director.width,
//                        Math.random()*director.height,
//                        Math.random()*director.width,
//                        Math.random()*director.height));
//                    
//                    behaviour.setFrameTime( scene.time, 3000+Math.random()*3000 );
//                }
//
//                public void behaviorApplied(BaseBehavior behavior, double time, double normalizeTime, Actor actor,
//                        SetForTimeReturnValue value) {
//                }
//
//                @Override
//                public void behaviorStarted(BaseBehavior behavior, double time, Actor actor) {
//                    
//                }
//            }
                    
                    );
            
            fish.addBehavior( pbfish );
        }


        root.fillStyle= CaatjaColor.valueOf("#3f3fff");

        
        scene.addChild(children);

        return scene;
    }

}
