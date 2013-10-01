package com.katspow.caatjagwtdemos.client.welcome.showcase.scenes;

import java.util.List;

import com.katspow.caatja.behavior.AlphaBehavior;
import com.katspow.caatja.behavior.Interpolator;
import com.katspow.caatja.behavior.ScaleBehavior;
import com.katspow.caatja.core.canvas.CaatjaColor;
import com.katspow.caatja.event.CAATMouseEvent;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;
import com.katspow.caatja.foundation.actor.ActorContainer;
import com.katspow.caatja.foundation.actor.ActorRender;
import com.katspow.caatja.foundation.ui.ShapeActor;
import com.katspow.caatja.math.Pt;
import com.katspow.caatja.modules.circlemanager.PackedCircle;
import com.katspow.caatja.modules.circlemanager.PackedCircleManager;
import com.katspow.caatja.modules.colorutil.Color;
import com.katspow.caatja.modules.colorutil.RGB;

public class PackedCircleScene {

    PackedCircleManager packedCirleManager = null;
    Director director = null;
    Scene scene = null;
    ActorContainer root = null;
    Pt mousePosition = null;
    int sineOffset = 1212; // some arbitary number i liked

    public void initDirector(Director director) throws Exception
    {
        this.mousePosition = new Pt(director.canvas.getCoordinateSpaceWidth()/2, director.canvas.getCoordinateSpaceHeight()/2);
        this.director = director;
        this.scene = new Scene();
        
        this.scene.onRenderEnd = new ActorRender() {
            @Override
            public void call(double time) {
                
                packedCirleManager.pushAllCirclesTowardTarget(null);
                packedCirleManager.handleCollisions();
                sineOffset += 0.01;
                List<PackedCircle> circleList = packedCirleManager.allCircles;
              int len = circleList.size();
    
                // color it
                Color color = new Color();
                double longestDistance = 40000 + Math.sin(sineOffset) * 30000;
                if(longestDistance < 0) longestDistance *= -1; // abs
                while(len-- > 0) {
                    PackedCircle packedCircle = circleList.get(len);
                    Actor circleActor = packedCircle.delegate;
                    double distanceFromTarget = packedCircle.position.getDistanceSquared(packedCircle.targetPosition);
                    if(distanceFromTarget > longestDistance) distanceFromTarget = longestDistance;
    
                    double amplitude = (distanceFromTarget / longestDistance);
                    double hue = 360 - (amplitude * 95);
    
                    circleActor.x = packedCircle.position.x-packedCircle.radius;
                    circleActor.y = packedCircle.position.y-packedCircle.radius;
                    // color
                    circleActor.setFillStyle("#" + color.hsvToRgb((int)hue, 95, 99).toHex() );
    
                    // Here we are doing an interesting trick.
                    // By randomly changing the targetChaseSpeed +/- 0.002 randomly
                    // we introduce a seemingly complex hive behavior whereby certain circles
                    // seem to want to 'leave' sometimes, and others decide to force their way to the center more strongly
                    if(Math.random() < 0.2)
                        packedCircle.setTargetChaseSpeed(packedCircle.targetChaseSpeed + Math.random() * 0.004 - 0.002);
                }
                
            }
        };
        
        this.root = new ActorContainer() {
            @Override
            public void mouseMove(CAATMouseEvent mouseEvent) {
                mousePosition.set(mouseEvent.point.x, mouseEvent.point.y);
            }
            
        }.
            setBounds(0,0, director.canvas.getCoordinateSpaceWidth(), director.canvas.getCoordinateSpaceHeight());
        
        this.scene.addChild( this.root );

        // Collision simulation
        this.packedCirleManager = new PackedCircleManager();
        this.packedCirleManager.setBounds(0, 0, (int) director.width, (int) director.height);
        this.packedCirleManager.setNumberOfCollisionPasses(2);
        this.packedCirleManager.setNumberOfTargetingPasses(1);

        // Create a bunch of circles!
        Color colorHelper = new Color();
            RGB rgb = new RGB(0, 0, 0);
            int total = 75;
        for(int i = 0; i < total; i++)
        {
            // Size
             double aRadius = Math.random() * 25 + 9;

            // color it
            int hue = (360-((i/total) * 360) ); // HSV uses 0 - 360
                String hex = colorHelper.hsvToRgb(hue, 80, 99).toHex(); // Convert to hex value

                ShapeActor circleActor = new ShapeActor()
                .setShape( ShapeActor.Shape.CIRCLE)
                .setLocation( Math.random() * director.canvas.getCoordinateSpaceWidth(), Math.random() * director.canvas.getCoordinateSpaceHeight())
                .setSize(aRadius*2, aRadius*2) // Size is in diameters
                .setFillStyle('#' + hex );

            // The 'packedCircle' in the simulation is considered completely separate entity than the circleActor itself
                PackedCircle packedCircle = new PackedCircle()
                .setDelegate(circleActor)
                .setRadius(aRadius)
                .setCollisionMask(1)    // packedCircle instnace - will collide against this group
                .setCollisionGroup(1) // packedCircle instance - is in this group
                .setTargetPosition(this.mousePosition)
                .setTargetChaseSpeed(Math.random() * 0.02);

            // disable mouse on specific circle
                // TODO Check
//            packedCircle.mouseEnabled = false;

            this.animateInUsingScale(circleActor, this.scene.time+Math.random() * 3000, 500, 0.1, 1);

            // Add to the collision simulation
            this.packedCirleManager.addCircle(packedCircle);

            // Add actor to the scene
            this.root.addChild(circleActor);
        }

        // Useless : should be done by another class (Showcase) 
        //this.director.addScene(this.scene);

        // Force all packedCircles to move to the position of their delegates
        this.packedCirleManager.forceCirclesToMatchDelegatePositions();

    }

    /**
     * Adds a ScaleBehavior to the entity, used on animate in
     */
    public ScaleBehavior animateInUsingScale(Actor actor, double starTime, double endTime, double startScale,
            double endScale) {
        ScaleBehavior scaleBehavior = new ScaleBehavior();
        actor.scaleX = actor.scaleY = scaleBehavior.startScaleX = scaleBehavior.startScaleY = startScale; // Fall
                                                                                                          // from
                                                                                                          // the
                                                                                                          // 'sky'
                                                                                                          // !
        scaleBehavior.endScaleX = scaleBehavior.endScaleY = endScale;
        scaleBehavior.setFrameTime(starTime, starTime + endTime);
        scaleBehavior.setCycle(false);
        scaleBehavior.setInterpolator(new Interpolator().createBounceOutInterpolator(false));
        actor.addBehavior(scaleBehavior);

        return scaleBehavior;
    }

    /**
     * Adds a ScaleBehavior to the entity, used on animate in
     */
    public AlphaBehavior animateInUsingAlpha(Actor actor, double starTime, double endTime, double startAlpha,
            double endAlpha) {
        AlphaBehavior fadeBehavior = new AlphaBehavior();

        //  TODO Check
//        fadeBehavior.anchor = Actor.Anchor.CENTER;
        
        actor.alpha = fadeBehavior.startAlpha = startAlpha;
        fadeBehavior.endAlpha = endAlpha;
        fadeBehavior.setFrameTime(starTime, endTime);
        fadeBehavior.setCycle(false);
        fadeBehavior.setInterpolator(new Interpolator().createExponentialOutInterpolator(2, false));
        actor.addBehavior(fadeBehavior);

        return fadeBehavior;
    }
    
    public Scene getScene() {
        return scene;
    }

}
