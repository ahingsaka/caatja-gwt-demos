package com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut02;

import com.katspow.caatja.behavior.Interpolator;
import com.katspow.caatja.behavior.RotateBehavior;
import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.core.canvas.CaatjaGradient;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.ActorContainer;
import com.katspow.caatja.foundation.ui.TextActor;

public class Tut27 {

    public void start(CaatjaCanvas canvas) throws Exception {
        Director director = new Director().initialize(800, 400, canvas);
        Scene scene = director.createScene();
        
        ActorContainer cc1 = new ActorContainer().
                setBounds(0, 100, 700, 300).
                enableEvents(false);
        scene.addChild(cc1);

        cc1.addBehavior(
                new RotateBehavior().
                        setCycle(true).
                        setFrameTime(0, 4000).
                        setValues(-Math.PI / 8, Math.PI / 8, .50, 0d).    // anchor at 50%, 0%
                        setInterpolator(
                        new Interpolator().createExponentialInOutInterpolator(3, true))
                );

        CaatjaGradient gradient = director.crc.createLinearGradient(0, 0, 0, 50);
        gradient.addColorStop(0, "#00ff00");
        gradient.addColorStop(0.5, "red");
        gradient.addColorStop(1, "blue");

        TextActor text = new TextActor().
                setFont("50px sans-serif").
                setText("Conpound Path").
                calcTextSize(director).
                setAlign("center").
                setFillStrokeStyle(gradient).
                setOutline(true).
                setOutlineColor("white");
        text.cacheAsBitmap();
        cc1.addChild(text.setLocation((cc1.width - text.textWidth) / 2, 0));

        TextActor text2 = new TextActor().
                setFont("50px sans-serif").
                setText("Quadric,Cubic,Line segments").
                calcTextSize(director).
                setAlign("center").
                setFillStrokeStyle(gradient).
                setOutline(true).
                setOutlineColor("white");
        text.cacheAsBitmap();
        cc1.addChild(text2.setLocation((cc1.width - text2.textWidth) / 2, 50));

        TextActor text4 = new TextActor().
                setFont("50px sans-serif").
                setText("Fish path").
                calcTextSize(director).
                setAlign("center").
                setFillStrokeStyle(gradient).
                setOutline(true).
                setOutlineColor("white");
        text.cacheAsBitmap();
        cc1.addChild( text4.setLocation((cc1.width - text4.textWidth) / 2, 100) );

        TextActor text3 = new TextActor().
                setFont("50px sans-serif").
                setText("Interpolators").
                calcTextSize(director).
                setAlign("center").
                setFillStrokeStyle(gradient).
                setOutline(true).
                setOutlineColor("white");
        text.cacheAsBitmap();
        cc1.addChild(text3.setLocation((cc1.width - text3.textWidth) / 2, 150));
        
        Caatja.loop(30);
    }

}
