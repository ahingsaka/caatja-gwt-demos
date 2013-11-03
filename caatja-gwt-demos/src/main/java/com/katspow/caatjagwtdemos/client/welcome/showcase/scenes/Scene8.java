package com.katspow.caatjagwtdemos.client.welcome.showcase.scenes;

import com.katspow.caatja.behavior.Interpolator;
import com.katspow.caatja.behavior.RotateBehavior;
import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaColor;
import com.katspow.caatja.core.canvas.CaatjaGradient;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.ActorContainer;
import com.katspow.caatja.foundation.ui.ShapeActor;
import com.katspow.caatja.foundation.ui.TextActor;
import com.katspow.caatja.modules.colorutil.Color;

/**
 * Scene 8.
 * Shows hierarchycally applied transformations to Actors.
 *
 */
public class Scene8 {

    private static ShapeActor __createShape(int w, int h) {
        return (ShapeActor) new ShapeActor().setShape(ShapeActor.Shape.CIRCLE).setSize(w, h);
    }

    private static ShapeActor __addPlanet(ShapeActor parent, double rotationTime, int pos, int radius, String color,
            double startAngle) throws Exception {
        ShapeActor planet = __createShape(radius, radius);

        double x = pos * Math.cos(startAngle);
        double y = pos * Math.sin(startAngle);

        planet.setFillStyle(color)
                .setLocation(x + parent.width / 2, y + parent.height / 2)
                .addBehavior(
                        new RotateBehavior().
                            setFrameTime(0, rotationTime).
                            setValues(0, 2 * Math.PI).
                            setCycle(true).
                            setAnchor(planet, -x, -y));

        parent.addChild(planet);

        return planet;
    }

    public static Scene init(Director director) throws Exception {

        int i;
        Scene scene = new Scene();
        scene.setFillStrokeStyle(CaatjaColor.valueOf("#FFFFFF"));
        
        ShapeActor sun = (ShapeActor) __createShape(50, 50).setFillStyle("yellow").setLocation(director.width / 2-25,
                director.height / 2-25);
        
        __createSunRays(director,scene);

        ShapeActor earth = __addPlanet(sun, 4000, 90, 15, "blue", 0);
        __addPlanet(earth, 4000, 15, 5, "green", Math.PI / 3);
        __addPlanet(earth, 5000, 22, 5, "rgb(32,255,192)", 0);

        ShapeActor mercury = __addPlanet(sun, 6000, 40, 10, "rgb(255,64,128)", Math.PI / 3);
        ShapeActor saturn = __addPlanet(sun, 15000, 200, 30, "rgb(255,64,128)", 0);

        ShapeActor io = __addPlanet(saturn, 5000 + 5000 * Math.random(), 20, 8, "rgb(32,255,192)", 0);
        ShapeActor europe = __addPlanet(saturn, 5000 + 5000 * Math.random(), 35, 5, "rgb(255,32,192)", Math.PI * 2 / 3);
        ShapeActor moon = __addPlanet(saturn, 5000 + 5000 * Math.random(), 70, 10, "rgb(32,192,255)",
                2 * Math.PI * 2 / 3);

        __addPlanet(moon, 9000 + 4000 * Math.random(), 20, 4, "rgb(0,  0,255)", Math.random() * 2 * Math.PI);
        __addPlanet(moon, 6000 + 4000 * Math.random(), 12, 4, "rgb(0,255,255)", Math.random() * 2 * Math.PI);

        scene.addChild(sun);
        
        __scene8_text(director,scene); 

        return scene;

    }
    
    private static void __createSunRays(Director director, Scene scene) throws Exception {
        ShapeActor root= new ShapeActor().
                setShape( ShapeActor.Shape.CIRCLE).
                setBounds( director.width/2, director.height/2, 1, 1 ).
                setFillStyle( "blue" ).
                addBehavior(
                    new RotateBehavior().
                        setFrameTime(0,20000).
                        setValues(0,2*Math.PI).
                        setCycle(true)
                );

        int NumSegments=20;
        __createStar(root, 16, NumSegments, 2, 20, Math.PI/NumSegments/2 );
        scene.addChild( root );
    }

    private static void __createStar(ShapeActor root,int arms,int armSegments,int armSegmentSizeW,int armSegmentSizeH, double maxAngle) throws Exception {

        int i;
        for( i=0; i<arms; i++ ) {
            __createArm( root, 2*Math.PI/arms * i, armSegments, armSegmentSizeW, armSegmentSizeH, i, maxAngle );
        }
    }

    private static void __createArm(ShapeActor root, double angle, final int segments, final double armSegmentSizeW, final double armSegmentSizeH, final int armIndex, final double maxAngle ) throws Exception {

        int i;
        ShapeActor segment = root;

        for (i = 0; i < segments; i++) {

            int[] color = Color.interpolate(255, 255, 0, 255, 128, 0, segments, i);

            Scene8ShapeActor newSegment = (Scene8ShapeActor) new Scene8ShapeActor() {

                @Override
                public boolean animate(Director director, double time) throws Exception {
                    this.setRotationAnchored(
                            this.oldAngle + maxAngle
                                    * Math.sin(Caatja.getTime() * .0005 + armIndex * Math.PI / segments / 2),
                            .5, 1d);

                    return super.animate(director, time);
                }

            }.
            setShape(ShapeActor.Shape.RECTANGLE).
            setSize(armSegmentSizeH - 4, armSegmentSizeH - 4).
            setFillStyle("rgb(" + color[0] + "," + color[1] + "," + color[2] + ")").
            setLocation(0, -armSegmentSizeH);

            if (segment == root) {
                newSegment.setRotationAnchored(angle, .5, 1d);
                newSegment.oldAngle = angle;
            } else {
                newSegment.oldAngle = 0;
            }

            segment.addChild(newSegment);
            segment = newSegment;
        }
    }

    private static void __scene8_text(Director director, Scene scene) throws Exception {
        CaatjaGradient gradient= director.ctx.createLinearGradient(0,0,0,50);
        gradient.addColorStop(0,"orange");
        gradient.addColorStop(0.5,"red");
        gradient.addColorStop(1,"#3f00ff");

        ActorContainer cc= new ActorContainer().
                setBounds( 450,30, 150, 100 ).
                enableEvents(false).
                addBehavior(
                    new RotateBehavior().
                            setCycle(true).
                            setFrameTime( 0, 4000 ).
                            setValues( -Math.PI/8, Math.PI/8, .5, 0d).
                            setInterpolator(
                                Interpolator.createExponentialInOutInterpolator(3,true)
                            )
                );

        TextActor text= new TextActor().
                setFont("50px sans-serif").
                setText("Hierarchycal").
                setTextFillStyle(gradient).
                setOutline(true).
                calcTextSize(director);
        text.cacheAsBitmap();
        
        cc.addChild(text.setLocation((cc.width-text.width)/2,0));

        TextActor text2= new TextActor().
                setFont("50px sans-serif").
                setText("Rotations").
                calcTextSize(director).
                setTextFillStyle(gradient).
                setOutline(true);
        text2.cacheAsBitmap();
        cc.addChild(text2.setLocation((cc.width-text2.width)/2,50));

        scene.addChild(cc);
    }

}
