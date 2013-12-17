package com.katspow.caatjagwtdemos.client.welcome.showcase.scenes;

import com.katspow.caatja.behavior.Interpolator;
import com.katspow.caatja.behavior.RotateBehavior;
import com.katspow.caatja.core.canvas.CaatjaGradient;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.ActorContainer;
import com.katspow.caatja.foundation.ui.TextActor;
import com.katspow.caatja.foundation.ui.TextFont;

/**
 * Scene 10.
 * Shows some collision management. 
 * TODO Buggy ...
 */
public class Scene10 {
    
    private static PackedCircleScene packedCircleScene;

    public static Scene init(Director director) throws Exception {
        
     // Start our scene created below
        packedCircleScene = new PackedCircleScene();
        packedCircleScene.initDirector(director);

        __scene10_text(director, packedCircleScene.scene);
        
        return packedCircleScene.getScene();
    }
    
    private static void __scene10_text(Director director, Scene scene) throws Exception {
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
                            setValues( -Math.PI/8, Math.PI/8, .50, 0d ).
                            setInterpolator(
                                Interpolator.createExponentialInOutInterpolator(3,true)
                            )
                );

        TextActor text= new TextActor().
                setFont(new TextFont(50, "px", "sans-serif")).
                setText("PackedCircle").
                setTextFillStyle(gradient).
                setOutline(true).
                calcTextSize(director);
        text.cacheAsBitmap();
        cc.addChild(text.setLocation((cc.width-text.textWidth)/2,0));

        TextActor text2= new TextActor().
                setFont(new TextFont(30, "px", "sans-serif")).
                setText("Collision demo").
                calcTextSize(director).
                setTextFillStyle(gradient).
                setOutline(true);
        text2.cacheAsBitmap();
        
        cc.addChild(text2.setLocation((cc.width-text2.textWidth)/2,50));

        scene.addChild(cc);
    }
    
}
