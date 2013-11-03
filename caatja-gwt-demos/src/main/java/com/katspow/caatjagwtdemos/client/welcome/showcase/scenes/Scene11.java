package com.katspow.caatjagwtdemos.client.welcome.showcase.scenes;

import com.katspow.caatja.behavior.Interpolator;
import com.katspow.caatja.behavior.RotateBehavior;
import com.katspow.caatja.core.canvas.CaatjaGradient;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.ActorContainer;
import com.katspow.caatja.foundation.actor.ActorRender;
import com.katspow.caatja.foundation.ui.TextActor;

/**
 * Scene 11.
 * Shows some accelerometer based demo.
 */
// FIXME Acceleration ...
public class Scene11 {
    
    public static Scene init(Director director) throws Exception {
        int i;
        Scene scene= new Scene();

        __scene11_text(director,scene);

        final TextActor text= new TextActor().
                setFont("100px sans-serif").
                setText("Rotate Device").
                setTextFillStyle("red").
                setOutline(true).
                calcTextSize(director);
        
        text.cacheAsBitmap();
        text.setLocation((director.canvas.getCoordinateSpaceWidth())/2,
                (director.canvas.getCoordinateSpaceHeight())/2);
        scene.addChild(text);
        
        TextActor alpha= new TextActor().
                setFont("15px sans-serif").
                setText("Alpha: ").
                setLocation(10,80);
        TextActor beta= new TextActor().
                setFont("15px sans-serif").
                setText("Beta: ").
                setLocation(10,100);
        TextActor gamma= new TextActor().
                setFont("15px sans-serif").
                setText("Gamma: ").
                setLocation(10,120);
        scene.addChild(alpha);
        scene.addChild(beta);
        scene.addChild(gamma);
        
        scene.onRenderEnd = new ActorRender() {
            @Override
            public void call(double time) {
                // FIXME TODO Accel disabled -> Change this method
//              int rx= window.innerWidth > window.innerHeight ? accelerationIncludingGravity.y : accelerationIncludingGravity.x;
//                
//                          //  rx/=10; // 9.8 m/s^2
//                
//                            text.setRotation(rx*Math.PI/3.3 );
                
//          //      tx.setText("Acceleration X: "+accelerationIncludingGravity.x);
//          //      ty.setText("Acceleration Y: "+accelerationIncludingGravity.y);
//           //     tz.setText("Acceleration Z: "+accelerationIncludingGravity.z);
//
//                alpha.setText("Alpha: "+rotationRate.alpha);
//                beta.setText( "Beta:  "+rotationRate.beta);
//                gamma.setText("Gamma: "+rotationRate.gamma);
            }
        };

        
        return scene;
    }
    
    private static void __scene11_text(Director director, Scene scene) throws Exception {
    	CaatjaGradient gradient= director.ctx.createLinearGradient(0,0,0,50);
        gradient.addColorStop(0,"orange");
        gradient.addColorStop(0.5,"yellow");
        gradient.addColorStop(1,"#7f00ff");

        ActorContainer cc= new ActorContainer().
                setBounds( 450,30, 150, 100 ).
                enableEvents(false).
                addBehavior(
                    new RotateBehavior().
                            setCycle(true).
                            setFrameTime( 0, 4000 ).
                            setValues( -Math.PI/8, Math.PI/8, .5, 0d ).
                            setInterpolator(
                                Interpolator.createExponentialInOutInterpolator(3,true)
                            )
                );

        TextActor text= new TextActor().
                setFont("50px sans-serif").
                setText("Accelerometer").
                setTextFillStyle(gradient).
                setOutline(true).
                calcTextSize(director);
        text.cacheAsBitmap();
        cc.addChild(text.setLocation((cc.width-text.textWidth)/2,0));

        TextActor text2= new TextActor().
                setFont("50px sans-serif").
                setText("Enabled").
                calcTextSize(director).
                setTextFillStyle(gradient).
                setOutline(true);
        text2.cacheAsBitmap();
        cc.addChild(text2.setLocation((cc.width-text2.textWidth)/2,50));

        scene.addChild(cc);
    }

}
