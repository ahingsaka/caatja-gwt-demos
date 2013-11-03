package com.katspow.caatjagwtdemos.client.welcome.demos;

import com.katspow.caatja.behavior.GenericBehavior;
import com.katspow.caatja.behavior.GenericBehaviorCallback;
import com.katspow.caatja.behavior.Interpolator;
import com.katspow.caatja.behavior.RotateBehavior;
import com.katspow.caatja.behavior.SetForTimeReturnValue;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.core.canvas.CaatjaGradient;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;
import com.katspow.caatja.foundation.actor.ActorContainer;
import com.katspow.caatja.foundation.ui.ShapeActor;
import com.katspow.caatja.foundation.ui.StarActor;
import com.katspow.caatja.foundation.ui.TextActor;
import com.katspow.caatja.modules.colorutil.Color;

/**
 * TODO Update with source 
 *
 */
public class Demo8 {
    
    public void start(CaatjaCanvas canvas) throws Exception {
        final Director director = new Director().initialize(800, 500, canvas);
        hierarchy(director);
        
    }
    
    private ShapeActor __createShape(int w, int h) {
        return new ShapeActor().setShape(ShapeActor.Shape.CIRCLE).setSize(w, h);
    }

    private ShapeActor __addPlanet(ActorContainer parent, double rotationTime, int pos,int radius, String color, double d) throws Exception {
        ShapeActor planet = __createShape(radius, radius);

        double x = pos * Math.cos(d);
        double y = pos * Math.sin(d);

        planet.setFillStyle(color).
                setLocation(x + parent.width / 2, y + parent.height / 2).
                addBehavior(
                new RotateBehavior().
                        setFrameTime(0, rotationTime).
                        setValues(0, 2 * Math.PI).
                        setCycle(true).
                        //setAnchor( planet, -x, -y )
                        setAnchor(planet, -x, -y)
                );

        parent.addChild(planet);

        return planet;
    }

    private Scene hierarchy(Director director) throws Exception {

        Color colorHelper= new Color();
        int i;
        Scene scene = director.createScene();
        final int maxR= 500;
        final int minR= 75;
        StarActor sun = (StarActor) new StarActor().
                initialize( 32, maxR, 5 ).
                setFillStyle("yellow").
                setLocation(director.width / 2 - maxR, director.height / 2 - maxR).
                addBehavior(
                        new RotateBehavior().
                                setCycle(true).
                                setValues(0,2*Math.PI).
                                setFrameTime(0,30000)
                ).
                addBehavior(
                        new GenericBehavior().
                                setCycle(true).
                                setFrameTime(0,20000).
                                setValues( 5d, (double) minR, (Actor) null, (String) null, new GenericBehaviorCallback() {
                                    
                                    @Override
                                    public SetForTimeReturnValue call(double value, Actor target, Actor actor) {
                                        
                                        StarActor starActor = (StarActor) actor;
                                        
                                        int ivalue = (int) value>>0;
                                        starActor.initialize( 32,maxR,value );

                                        ivalue-=5;
                                        ivalue= ((ivalue/minR)*64+192)>>0;
                                        if ( ivalue>255 ) { ivalue=0; } else if (ivalue<0) {ivalue=0;}
                                        String svalue= "0"; // TODO FUCK Java ! value.toString(16);
                                        if (svalue.length()<2 ) {
                                            svalue="0"+svalue;
                                        }

                                        starActor.setFillStyle( "#ff"+svalue+"3f" );
                                        
                                        return null;
                                    }
                                }
                                        
                                       ).
                                setInterpolator(
                                        Interpolator.createBounceInInterpolator(true)
                                )
                );

        ShapeActor earth = __addPlanet(sun, 4000, 90, 15, "blue", 0);
        __addPlanet(earth, 4000, 15, 5, "green", Math.PI / 3);
        __addPlanet(earth, 5000, 22, 5, "rgb(32,255,192)", 0);

        ShapeActor mercury = __addPlanet(sun, 6000, 40, 10, "rgb(255,64,128)", Math.PI / 3);
        ShapeActor saturn = __addPlanet(sun, 15000, 200, 30, "rgb(255,64,128)", 0);

        ShapeActor io = __addPlanet(saturn, 5000 + 5000 * Math.random(), 20, 8, "rgb(32,255,192)", 0);
        ShapeActor europe = __addPlanet(saturn, 5000 + 5000 * Math.random(), 35, 5, "rgb(255,32,192)", Math.PI * 2 / 3);
        ShapeActor moon = __addPlanet(saturn, 5000 + 5000 * Math.random(), 70, 10, "rgb(32,192,255)", 2 * Math.PI * 2 / 3);

        __addPlanet(moon, 9000 + 4000 * Math.random(), 20, 4, "rgb(0,  0,255)", Math.random() * 2 * Math.PI);
        __addPlanet(moon, 6000 + 4000 * Math.random(), 12, 4, "rgb(0,255,255)", Math.random() * 2 * Math.PI);

        scene.addChild(sun);

        __scene8_text(director, scene);

        return scene;
    }

    private void __scene8_text(Director director, Scene scene) throws Exception {
        CaatjaGradient gradient = director.ctx.createLinearGradient(0, 0, 0, 50);
        gradient.addColorStop(0, "orange");
        gradient.addColorStop(0.5, "red");
        gradient.addColorStop(1, "#3f00ff");

        ActorContainer cc = new ActorContainer().
                setBounds(550, 30, 150, 100).
                enableEvents(false).
                addBehavior(
                new RotateBehavior().
                        setCycle(true).
                        setFrameTime(0, 4000).
                        setValues(-Math.PI / 8, Math.PI / 8, .50, 0d).
                        setInterpolator(
                            Interpolator.
                                    createExponentialInOutInterpolator(3, true)
                        )
                );
        scene.addChild(cc);

        TextActor text = new TextActor().
                setFont("50px sans-serif").
                setText("Hierarchycal").
                setTextFillStyle(gradient).
                setOutline(true).
                calcTextSize(director);
                text.cacheAsBitmap();
        cc.addChild(text.setLocation((cc.width - text.width) / 2, 0));

        TextActor text2 = new TextActor().
                setFont("50px sans-serif").
                setText("Rotations").
                calcTextSize(director).
                setTextFillStyle(gradient).
                setOutline(true);
                text.cacheAsBitmap();
        cc.addChild(text2.setLocation((cc.width - text2.width) / 2, 50));
    }

}
