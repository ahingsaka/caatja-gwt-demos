package com.katspow.caatjagwtdemos.client.welcome.demos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.katspow.caatja.behavior.BaseBehavior;
import com.katspow.caatja.behavior.Interpolator;
import com.katspow.caatja.behavior.RotateBehavior;
import com.katspow.caatja.behavior.ScaleBehavior;
import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.core.canvas.CaatjaGradient;
import com.katspow.caatja.core.canvas.CaatjaImage;
import com.katspow.caatja.event.CAATMouseEvent;
import com.katspow.caatja.event.MouseListener;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;
import com.katspow.caatja.foundation.actor.ActorContainer;
import com.katspow.caatja.foundation.image.SpriteImage;
import com.katspow.caatja.foundation.ui.ShapeActor;
import com.katspow.caatja.foundation.ui.TextActor;
import com.katspow.caatja.foundation.ui.TextFont;
import com.katspow.caatja.math.Pt;

/**
 * TODO Update with source 
 *
 */
public class Demo7 {
    
    private Demo7ShapeActor circle;
    private ArrayList<Actor> peces;
    private ActorContainer container;
    private int i;

    public void start(CaatjaCanvas canvas, Map<String, CaatjaImage> images) throws Exception {
        final Director director = new Director().initialize(800, 500, canvas);
        director.imagesCache = images;
        
        final Scene scene = new Scene() {

            // TODO Remove
//            @Override
//            public void mouseEnter(CAATMouseEvent mouseEvent) {
//                this.pointed= true;
//                if (circle != null) {
//              circle.setVisible(false);
//                }
//              
//            }

            // TODO Remove
//            @Override
//            public void mouseExit(CAATMouseEvent mouseEvent) {
//              this.pointed= false;
//              if (circle != null) {
//                  circle.setVisible(true);
//              }
//            }

            // TODO Remove
//            @Override
//            public void mouseMove(CAATMouseEvent mouseEvent) throws Exception {
//                double x= mouseEvent.point.x;
//                double y= mouseEvent.point.y;
//                fishLookAt(x,y, peces);
//            }
        };
        
        scene.setMouseMoveListener(new MouseListener() {
            public void call(CAATMouseEvent e) throws Exception {
                double x= e.point.x;
                double y= e.point.y;
                fishLookAt(x,y, peces);
            }
        });
        
        scene.setMouseExitListener(new MouseListener() {
            public void call(CAATMouseEvent e) throws Exception {
                scene.pointed = false;
                if (circle != null) {
                    circle.setVisible(true);
                }
            }
        });
        
        scene.setMouseEnterListener(new MouseListener() {
            public void call(CAATMouseEvent e) throws Exception {
                scene.pointed = true;
                if (circle != null) {
                    circle.setVisible(false);
                }
            }
        });
        
        director.addScene(scene);
        
        Actor actor;
        List<SpriteImage> cimages= new ArrayList<SpriteImage>();
        cimages.add( new SpriteImage().initialize((director.getImage("fish")),  1, 3) );
        cimages.add( new SpriteImage().initialize(( director.getImage("fish2")), 1, 3) );
        cimages.add( new SpriteImage().initialize(( director.getImage("fish3")), 1, 3) );
        cimages.add( new SpriteImage().initialize(( director.getImage("fish4")), 1, 3) );

        double[] anchor= new double[] {
                0,0,   .50, 0,   1.00, 0,
                0,.50,  .50, .50,  1.00, .50,
                0,1.00, .50, 1.00, 1.00, 1.00};
        
        for(int i=0; i<9; i++ ) {
            actor= createElement(
                    cimages.get(0),
                    new RotateBehavior().
                            setCycle(true).
                            setFrameTime( 0, 2000 ).
                            setValues( 0, 2*Math.PI, (double) anchor[i*2], (double) anchor[i*2+1] )
                    );

            actor.setLocation(60+(cimages.get(0).singleWidth*2)*(i%3), 170+(cimages.get(0).singleWidth)*((i/3)>>0) );
            scene.addChild(actor);
        }

        for(int i=0; i<9; i++ ) {
            actor= createElement(
                    cimages.get(1),
                    new ScaleBehavior().
                            setCycle(true).
                            setFrameTime( 0, 2000 ).
                            setValues( .5,1.5, .5,1.5,  (double)  anchor[i*2],   (double)  anchor[i*2+1] ).
                            setPingPong()
                    );

            actor.setLocation(60+(cimages.get(0).singleWidth*2)*(i%3), 340+(cimages.get(0).singleWidth)*((i/3)>>0) );
            scene.addChild(actor);
        }

        createField(director, scene, cimages);
        createDescription(director,scene);
        
        Caatja.loop(30);
        
    }
    
    private void createDescription(Director director,Scene scene) throws Exception {
        CaatjaGradient gradient = director.ctx.createLinearGradient(0, 0, 0, 50);
        gradient.addColorStop(0, "blue");
        gradient.addColorStop(0.5, "orange");
        gradient.addColorStop(1, "yellow");

        ActorContainer cc = new ActorContainer().
                setBounds(35, 30, 300, 150).
                enableEvents(false).
                addBehavior(
                        new RotateBehavior().
                                setCycle(true).
                                setFrameTime(0, 4000).
                                setValues(-Math.PI / 8,Math.PI / 8, 0.50, 0d).
                                setInterpolator(
                                        Interpolator.createCubicBezierInterpolator(
                                                new Pt(0,0),
                                                new Pt(1,0),
                                                new Pt(0,1),
                                                new Pt(1,1),
                                                true))
                );
        
        scene.addChild(cc);

        TextActor text = new TextActor().
                setFont(new TextFont(50, "px", "sans-serif")).
                setText("Anchored").
                setTextAlign("center").
                setTextFillStyle(gradient).
                setOutline(true).
                calcTextSize(director);
                text.cacheAsBitmap();
        cc.addChild(text.setLocation(cc.width / 2, 0));
        

        TextActor text2 = new TextActor().
                setFont(new TextFont(35, "px", "sans-serif")).
                setTextAlign("center").
                setText("Affine transforms").
                setTextFillStyle(gradient).
                setOutline(true).
                calcTextSize(director);
                text2.cacheAsBitmap();
                cc.addChild(text2.setLocation(cc.width / 2, 50));
    }
    
    private void fishLookAt(double x, double y, List<Actor> peces) {
        
        if (peces != null) {
            for (Actor actor : peces) {
                double angle = Math.atan2(y - (actor.y + container.y + actor.height / 2), x
                        - (actor.x + container.x + actor.width / 2));
                actor.setRotation(angle);
            }
        }
        
    }

    private void createField(Director director,final Scene scene,List<SpriteImage> ci) throws Exception {
        int w= 400;
        int h= 460;
        peces= new ArrayList<Actor>();

        container= new ActorContainer().
                setBounds(380,20,w,h).
                enableEvents(false);

        int j;
        int maxw= 0;
        int maxh= 0;
        for( i=0; i<ci.size(); i++ ) {
            if ( ci.get(i).singleWidth>maxw ) {
                maxw= ci.get(i).singleWidth;
            }
            if ( ci.get(i).singleHeight>maxh ) {
                maxh= ci.get(i).singleHeight;
            }
        }
        maxw= maxh= Math.max(maxw,maxh);

        int cols= (w/maxw)>>0;
        int rows= (h/maxh)>>0;
        int xoffset= (w-cols*maxw)/2;
        int yoffset= (h-rows*maxh)/2;

        for( i=0; i<rows; i++ ) {
            for( j=0; j<cols; j++ ) {
                Actor fish= createSprite( ci.get((int)(Math.random()*ci.size())>>0)).
                        setLocation( xoffset+maxw*j, yoffset+maxh*i ).
                        setChangeFPS(200+(int)(Math.random()*300)>>0);
                peces.add(fish);
                container.addChild(fish);
            }
        }

        scene.addChild(container);

        circle= (Demo7ShapeActor) new Demo7ShapeActor() {
            @Override
            public boolean animate(Director director, double time) throws Exception {
                
                if ( false==scene.pointed ) {
                      double angle= Math.PI*2*Math.sin(time*3E-4) + i*Math.PI/50;
                      double radius= this.parent.width/8*Math.cos(time*3E-4);
                      this.setLocation(
        
                                  this.__orgX +
                                  this.parent.width/4*Math.cos(time*3E-4) +   // move horizontally with time
                                  radius*Math.cos(angle)/2,
        
                                  this.__orgY +
                                  this.parent.height/4*Math.sin(time*3E-4) +  // move vertically with time
                                  radius*Math.sin(angle)/2
                      );
        
                      fishLookAt( this.x, this.y, peces );
                  }
                
                return super.animate(director, time);
            }
            
        }.
                setBounds( director.canvas.getCoordinateSpaceWidth()/2, director.canvas.getCoordinateSpaceHeight()/2, 25, 25 ).
                setFillStyle("blue").
                enableEvents(false).
                setAlpha(.8);

        circle.__orgX= circle.x;
        circle.__orgY= circle.y;

        scene.addChild(circle);
    }
    
    private Actor createSprite(SpriteImage compoundImage) {
        return new Actor().
                setBackgroundImage(compoundImage.getRef(), true).
                setAnimationImageIndex(new int[] {0,1,2,1}).
                setChangeFPS(300).
                enableEvents(false);
    }

    private ActorContainer createElement(SpriteImage compoundImage, BaseBehavior behavior) throws Exception {
        ActorContainer ac= new ActorContainer();

        Actor fish = createSprite(compoundImage).
                addBehavior( behavior );

        ShapeActor bg= new ShapeActor().
                setShape( ShapeActor.Shape.RECTANGLE ).
                setFillStyle(null).
                setStringStrokeStyle("black").
                setSize( fish.width, fish.height );

        ac.setSize( fish.width, fish.height ).
                addChild( bg ).
                addChild( fish ).
                enableEvents(false);

        return ac;
    }

}
