package com.katspow.caatjagwtdemos.client.welcome;

import com.katspow.caatja.behavior.ContainerBehavior;
import com.katspow.caatja.behavior.RotateBehavior;
import com.katspow.caatja.behavior.ScaleBehavior;
import com.katspow.caatja.core.canvas.CaatjaColor;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.ActorContainer;
import com.katspow.caatja.foundation.ui.TextActor;
import com.katspow.caatja.foundation.ui.TextFont;

public class HomeScene extends Scene {

    private Director director;
    private ActorContainer root;

    public void load(Director director) throws Exception {

        this.director = director;

        this.root = new ActorContainer();
        root.setBounds(0, 0, director.canvas.getCoordinateSpaceWidth(), director.canvas.getCoordinateSpaceHeight());
        root.setFillStrokeStyle(CaatjaColor.valueOf("#000000"));

        addChild(root);

    }

    private TextActor createCaatjaTxt() {

        TextActor caatjaTxt = new TextActor().
                setFont(new TextFont(35, "px", "sans-serif")).
                setText("CAATJA").
                calcTextSize(director).
                setTextFillStyle("white");
        
        caatjaTxt.cacheAsBitmap();
        
        RotateBehavior rb = new RotateBehavior().
                setValues(0,2*Math.PI, .5, .5).
                setFrameTime(0, 1300);
        
        ScaleBehavior sb = new ScaleBehavior().
                setFrameTime(500, 800).
                setValues(1, 2, 1, 2, .5, .5);
        
        ContainerBehavior cb = new ContainerBehavior();
            cb.setFrameTime(0, 1300);
            
        cb.addBehavior(rb);
        cb.addBehavior(sb);

        caatjaTxt.addBehavior(cb);

        return caatjaTxt;
    }
    
    private TextActor createDemoTxt() {
        TextActor demoTxt = new TextActor().
                setFont(new TextFont(30, "px", "sans-serif")).
                setText("GWT Implementation").
                calcTextSize(director).
                setTextFillStyle("white");
        
        demoTxt.cacheAsBitmap();
        
        return demoTxt;
    }

    public void showIntroduction() throws Exception {
        TextActor caatjaTxt = createCaatjaTxt();
        TextActor demoTxt = createDemoTxt();
        
        root.addChild(demoTxt.setLocation((director.canvas.getCoordinateSpaceWidth() - demoTxt.width) / 2,
                (director.canvas.getCoordinateSpaceHeight()) / 2 + 10.0) );

        root.addChild(caatjaTxt.setLocation((director.canvas.getCoordinateSpaceWidth() - caatjaTxt.width) / 2,
                (director.canvas.getCoordinateSpaceHeight()) / 2));

    }

}
