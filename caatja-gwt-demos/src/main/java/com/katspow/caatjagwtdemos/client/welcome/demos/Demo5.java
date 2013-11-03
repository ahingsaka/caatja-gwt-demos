package com.katspow.caatjagwtdemos.client.welcome.demos;

import java.util.Map;

import com.katspow.caatja.behavior.Interpolator;
import com.katspow.caatja.behavior.RotateBehavior;
import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.core.canvas.CaatjaGradient;
import com.katspow.caatja.core.canvas.CaatjaImage;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.ActorContainer;
import com.katspow.caatja.foundation.ui.PathActor;
import com.katspow.caatja.foundation.ui.TextActor;
import com.katspow.caatja.math.Pt;
import com.katspow.caatja.pathutil.Path;

public class Demo5 {
    
    public void start(CaatjaCanvas canvas, Map<String, CaatjaImage> images) throws Exception {
        final Director director = new Director().initialize(800, 500, canvas);
        director.imagesCache = images;
        Scene scene = director.createScene();
        
        Path p = new Path().
                beginPath(25, 250).
                addCubicTo(25, 0, 275, 0, 275, 250).
                addCubicTo(275, 400, 525, 400, 525, 250).
                addCubicTo(525, 0, 775, 0, 775, 250).
                addCubicTo(775, 500, 25, 500, 25, 250).
                closePath();


        // actor de path para poder verlo y manipularlo
        PathActor pa = new PathActor().
                setPath(p).
                setBounds(0, 0, director.width, director.height).
                setFillStyle("#aaf").
                setInteractive(true);
        scene.addChild(pa);

        CaatjaGradient gradient = director.ctx.createLinearGradient(0, 0, 0, -40);
        gradient.addColorStop(0, "#ffff00");
        gradient.addColorStop(0.5, "#00ffff");
        gradient.addColorStop(1, "blue");

        TextActor text = new TextActor().
            setFont("40px sans-serif").
            setText("Text on path, easily traverse a path with text.").
            setTextAlign("left").
            setTextFillStyle(gradient).
            setTextBaseline("bottom").
            setPath(
                p,
                Interpolator.createLinearInterpolator(false, false),
                30000d);
        scene.addChild(text);

        CaatjaGradient gradient2 = director.ctx.createLinearGradient(0, 0, 0, 40);
        gradient2.addColorStop(0, "#0000ff");
        gradient2.addColorStop(0.5, "#ff0000");
        gradient2.addColorStop(1, "#ffff00");
        
        // FIXME    
//        SpriteImage font = new SpriteImage().initializeAsMonoTypeFontMap(new Image(director.getImage("numbers5")), "0123456789");

        // FIXME Remove comment
        TextActor text2 = new TextActor().
//            setFont( font ).
            setText( "0123456789" ).
            setTextAlign("left").
            setTextFillStyle(gradient2).
            setTextBaseline("top").
            setPath(
                p,
                Interpolator.createExponentialInOutInterpolator(3, false), null).
            setPathTraverseDirection( TextActor.TRAVERSE_PATH_BACKWARD );
        scene.addChild(text2);


        createText(director, scene);
        
        Caatja.loop(30);
    }
    
    private void createText(Director director, Scene scene) throws Exception {
        ActorContainer cc1 = new ActorContainer().
                setBounds(255, 30, 280, 120).
                enableEvents(false);
        scene.addChild(cc1);

        RotateBehavior rb = new RotateBehavior().
                setCycle(true).
                setFrameTime(0, 4000).
                setValues( -Math.PI / 8, Math.PI / 8, .50, 0d ).
                setInterpolator(
                        Interpolator.createCubicBezierInterpolator(
                                new Pt(0,0),
                                new Pt(1,0),
                                new Pt(0,1),
                                new Pt(1,1),
                                true));
        cc1.addBehavior(rb);

        CaatjaGradient gradient = director.ctx.createLinearGradient(0, 0, 0, 30);
        gradient.addColorStop(0, "black");
        gradient.addColorStop(0.5, "gray");
        gradient.addColorStop(1, "#d0d0d0");

        TextActor text = new TextActor().
                setFont("40px sans-serif").
                setText("Text on Path").
                calcTextSize(director).
                setTextFillStyle(gradient).
                setOutline(true);
                text.cacheAsBitmap();
        cc1.addChild(text.setLocation((cc1.width - text.width) / 2, 0));

        TextActor text2 = new TextActor().
                setFont("40px sans-serif").
                setText("Interpolated").
                calcTextSize(director).
                setTextFillStyle(gradient).
                setOutline(true);
                text2.cacheAsBitmap();
        cc1.addChild(text2.setLocation((cc1.width - text2.width) / 2, 40));

        TextActor text4 = new TextActor().
                setFont("40px sans-serif").
                setText("As well").
                calcTextSize(director).
                setTextFillStyle(gradient).
                setOutline(true);
                text4.cacheAsBitmap();
        cc1.addChild(text4.setLocation((cc1.width - text4.width) / 2, 80));

    }

}
