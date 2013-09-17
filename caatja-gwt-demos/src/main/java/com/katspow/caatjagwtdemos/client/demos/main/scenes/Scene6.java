package com.katspow.caatjagwtdemos.client.demos.main.scenes;

import java.util.Arrays;

import com.katspow.caatja.behavior.Interpolator;
import com.katspow.caatja.behavior.RotateBehavior;
import com.katspow.caatja.core.canvas.CaatjaContext2d;
import com.katspow.caatja.core.canvas.CaatjaFillStrokeStyle;
import com.katspow.caatja.core.canvas.CaatjaGradient;
import com.katspow.caatja.event.CAATMouseEvent;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.ActorContainer;
import com.katspow.caatja.foundation.actor.SpriteActor;
import com.katspow.caatja.foundation.image.CompoundImage;
import com.katspow.caatja.foundation.ui.TextActor;
import com.katspow.caatja.math.Pt;
import com.katspow.caatjagwtdemos.client.demos.main.actor.Circle;

public class Scene6 {
    
    public static Scene init(final Director director) throws Exception {
        SpecialScene scene = new SpecialScene() {
            public void mouseMove(CAATMouseEvent mouseEvent) {
             // 1 el circulo
                ((ActorContainer) mouseEvent.source).childrenList.get(1).setLocation( mouseEvent.point.x, mouseEvent.point.y );
                // 0 el contenedor de peces
                this.magneticField(
                        mouseEvent.point.x, 
                        mouseEvent.point.y,
                        (ActorContainer)((ActorContainer) mouseEvent.source).childrenList.get(0));
            }
        };
        
        CompoundImage conpoundimage = new CompoundImage();
        conpoundimage.initialize(director.getImage("fish"), 1, 3);
        
        double w= (int)(director.canvas.getCoordinateSpaceWidth()/conpoundimage.singleWidth)>>0;
        final double h= (int)(director.canvas.getCoordinateSpaceHeight()/conpoundimage.singleWidth)>>0;
        double x= (director.canvas.getCoordinateSpaceWidth()-w*conpoundimage.singleWidth)/2;
        double y= (director.canvas.getCoordinateSpaceHeight()-h*conpoundimage.singleWidth)/2;
        
        ActorContainer pezContainer= new ActorContainer();
        pezContainer.setBounds(0,0,director.canvas.getCoordinateSpaceWidth(),director.canvas.getCoordinateSpaceHeight());
        pezContainer.mouseEnabled= false;
        scene.addChild(pezContainer);
        
        
        for( int i=0; i<h; i++ ) {
            for( int j=0; j<w; j++ ) {
                SpriteActor p2 = new SpriteActor();
                p2.setAnimationImageIndex(Arrays.asList(0,1,2,1));
                p2.setSpriteImage(conpoundimage);
                p2.changeFPS= (int) (250+250*Math.random());        
                p2.setLocation(
                        x + j*conpoundimage.singleWidth, 
                        y + i*conpoundimage.singleWidth );
                
                pezContainer.addChild(p2);
            }
        }
        
        Circle circle= new Circle() {
            @Override
            public void paint(Director director, double time) {
                CaatjaContext2d canvas= director.ctx;
                
                canvas.beginPath();
                canvas.setGlobalAlpha(.75);
                canvas.setFillStyle(this.fillStyle);
                canvas.arc(0,0,this.width,0,Math.PI*2,false);
                canvas.fill();
            }
            
            @Override
            public boolean animate(Director director, double time) throws Exception {
                if ( false==this.parent.pointed ) {     
                    
                    // TODO  h or i ???
                    double angle= Math.PI*2*Math.sin(time*3E-4) + h*Math.PI/50;
                    double radius= this.parent.width/8*Math.cos(time*3E-4);
                    this.setLocation( 
                                this.__orgX + 
                                this.parent.width/4*Math.cos(time*3E-4) +   // move horizontally with time 
                                radius*Math.cos(angle)/2,
                                
                                this.__orgY +  
                                this.parent.height/4*Math.sin(time*3E-4) +  // move vertically with time 
                                radius*Math.sin(angle)/2
                    );
                    
                
                    ((SpecialScene) this.parent).magneticField( this.x, this.y, (ActorContainer) this.parent.childrenList.get(0) );
                }
                
                return super.animate(director, time);
            }
            
        };
        circle.setBounds( director.canvas.getCoordinateSpaceWidth()/2, director.canvas.getCoordinateSpaceHeight()/2, 10, 10 );
        circle.__orgX= circle.x;
        circle.__orgY= circle.y;
        circle.fillStyle= new CaatjaFillStrokeStyle("blue");
        circle.mouseEnabled= false;

        scene.addChild(circle);
        

        
        __scene6_text(director,scene);
        
        return scene;
    }
    
    private static void __scene6_text(Director director, Scene scene) throws Exception {
    	CaatjaGradient gradient= director.ctx.createLinearGradient(0,0,0,50);
        gradient.addColorStop(0,"black");
        gradient.addColorStop(0.5,"gray");
        gradient.addColorStop(1,"#c0c0c0");
        
        ActorContainer cc= new ActorContainer();
        cc.setBounds( 380,30, 150, 100 );
        cc.mouseEnabled= false;
        
        RotateBehavior rb= new RotateBehavior();
        rb.cycleBehavior= true;
        rb.setFrameTime( 0, 4000 );
        rb.setValues( -Math.PI/8, Math.PI/8, .5, 0d );
        rb.setInterpolator( new Interpolator().createCubicBezierInterpolator(new Pt().set(0, 0), new Pt().set(1, 0), new Pt().set(0, 1), new Pt().set(1, 1), true ) );
        cc.addBehavior(rb);    
        
        TextActor text= new TextActor();
        text.setFont("50px sans-serif");
        text.setText("Fish");
        text.calcTextSize(director);
        text.setLocation((cc.width-text.width),0);
        text.setTextFillStyle(gradient);
        text.outline= true;
        text.cacheAsBitmap();

        cc.addChild(text.setLocation((cc.width-text.textWidth)/2,0));
        
        TextActor text2= new TextActor();
        text2.setFont("50px sans-serif");
        text2.setText("Field");
        text2.calcTextSize(director);
        text2.setLocation((cc.width-text2.width)/2,50);
        text2.setTextFillStyle(gradient);
        text2.outline= true;
        text2.cacheAsBitmap();
        cc.addChild(text2.setLocation((cc.width-text2.textWidth)/2,50));

        scene.addChild(cc);
    }

}
