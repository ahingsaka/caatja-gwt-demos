package com.katspow.caatjagwtdemos.client.showcase.scenes;

import com.katspow.caatja.behavior.Interpolator;
import com.katspow.caatja.behavior.RotateBehavior;
import com.katspow.caatja.core.canvas.CaatjaGradient;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.ActorContainer;
import com.katspow.caatja.foundation.ui.PathActor;
import com.katspow.caatja.foundation.ui.TextActor;
import com.katspow.caatja.math.Pt;
import com.katspow.caatja.pathutil.Path;

public class Scene5 {

    public static Scene init(Director director) throws Exception {
        Scene scene = new Scene();
        
     // path
        Path p= new Path();
        p.beginPath(155,250);
        p.addCubicTo(155, 0, 535, 0, 535, 250, null);
        p.addCubicTo(535, 500, 155, 500, 155, 250, null);
        p.endPath();

        // actor de path para poder verlo y manipularlo
        PathActor pa= new PathActor();
        pa.setPath(p);
        pa.setBounds(0,0,director.canvas.getCoordinateSpaceWidth(),director.canvas.getCoordinateSpaceHeight());
        scene.addChild(pa); 

        CaatjaGradient gradient= director.ctx.createLinearGradient(0,0,0,-40);
        gradient.addColorStop(0,"#ffff00");
        gradient.addColorStop(0.5,"#00ffff");
        gradient.addColorStop(1,"blue");    
        
        TextActor text= new TextActor();
        text.setFont("40px sans-serif");
        text.setText("Text on path   :D");
        text.textAlign="left";
        text.setTextFillStyle(gradient);
        text.textBaseline="bottom";
        text.setPath(p, new Interpolator().createBounceInOutInterpolator(false), null);
        scene.addChild(text);

        
        CaatjaGradient gradient2= director.ctx.createLinearGradient(0,0,0,40);
        gradient2.addColorStop(0,"#0000ff");
        gradient2.addColorStop(0.5,"#ff0000");
        gradient2.addColorStop(1,"#ffff00");    
        
        TextActor text2= new TextActor();
        
        text2.setFont("40px sans-serif");
        text2.setText("Text under path   :D");
        text2.textAlign="left";
        text2.setTextFillStyle(gradient2);
        text2.textBaseline="top";
        text2.setPath( p, new Interpolator().createExponentialInOutInterpolator(3, false), null );
        scene.addChild(text2);
        
        text2.sign= -1;
        
        
        __scene5_text(director,scene);
        
        return scene;
    }

    private static void __scene5_text(Director director, Scene scene) throws Exception {
        
        ActorContainer cc1= new ActorContainer();
        cc1.setBounds( 200,200, 280, 120 );
        cc1.mouseEnabled= false;
        scene.addChild(cc1);
        
        RotateBehavior rb= new RotateBehavior();
        rb.cycleBehavior= true;
        rb.setFrameTime( 0, 4000 );
        rb.setValues( -Math.PI/8, Math.PI/8, .5, 0d );
        rb.setInterpolator( new Interpolator().createCubicBezierInterpolator(new Pt().set(0, 0),new Pt().set(1, 0), new Pt().set(0, 1), new Pt().set(1, 1), true ) );
        cc1.addBehavior(rb);
        
        CaatjaGradient gradient= director.ctx.createLinearGradient(0,0,0,30);
        gradient.addColorStop(0,"black");
        gradient.addColorStop(0.5,"gray");
        gradient.addColorStop(1,"#d0d0d0"); 
        
        TextActor text= new TextActor();
        text.setFont("40px sans-serif");
        text.setText("Text on Path");
        text.calcTextSize(director);
        text.setLocation((cc1.width-text.width)/2,0);
        text.setTextFillStyle(gradient);
        text.outline= true;
        text.cacheAsBitmap();
        cc1.addChild(text.setLocation((cc1.width-text.textWidth)/2,0));

        TextActor text2= new TextActor();
        text2.setFont("40px sans-serif");
        text2.setText("Interpolated");
        text2.calcTextSize(director);
        text2.setLocation((cc1.width-text2.width)/2,40);
        text2.setTextFillStyle(gradient);
        text2.outline= true;
        text2.cacheAsBitmap();
        cc1.addChild(text2.setLocation((cc1.width-text2.textWidth)/2,40));

        TextActor text4= new TextActor();
        text4.setFont("40px sans-serif");
        text4.setText("As well");
        text4.calcTextSize(director);
        text4.setLocation((cc1.width-text4.width)/2,80);
        text4.setTextFillStyle(gradient);
        text4.outline= true;
        text4.cacheAsBitmap();
        cc1.addChild(text4.setLocation((cc1.width-text4.textWidth)/2,80));

    }
    

}
