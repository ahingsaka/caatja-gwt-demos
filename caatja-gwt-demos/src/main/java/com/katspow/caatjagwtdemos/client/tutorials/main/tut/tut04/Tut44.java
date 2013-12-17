package com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut04;

import com.katspow.caatja.behavior.RotateBehavior;
import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.core.canvas.CaatjaContext2d;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;
import com.katspow.caatja.foundation.ui.TextActor;
import com.katspow.caatja.foundation.ui.TextFont;

public class Tut44 {
    
    public void start(CaatjaCanvas canvas) throws Exception {

        Director director = new Director().initialize(800, 400, canvas);
        Scene scene1 = director.createScene();
        
        scene1.addChild( createPattern(director, "#33f") );
        scene1.addChild( createNumber(director, 1, "#33f") );
        Actor button= createButton(director, false);
        // TODO
//        button.mouseClick= function(e) {
//            director.switchToNextScene(
//                    2000,
//                    false,
//                    true
//            )
//        };
        scene1.addChild(button);

        Scene scene2 = director.createScene();
        scene2.addChild( createPattern(director, "#f33") );
        scene2.addChild( createNumber(director, 2, "#f33") );
        Actor button2= createButton(director, true);
        // TODO
//        button2.mouseClick= function(e) {
//            director.switchToPrevScene(
//                    2000,
//                    false,
//                    true
//            )
//        };
        scene2.addChild(button2);

        Caatja.loop(50);
    }
    
 // screat an actor with the text Scene1 or Scene2
    public Actor createNumber(Director director, int n, String color) {
        TextActor actor= new TextActor().
                setFont(new TextFont(200, "px", "Lucida-sans")).
                setText("Scene "+n).
                calcTextSize(director).
                setAlign("center").
                setFillStyle(color).
                setOutline(true);
                actor.cacheAsBitmap();
                actor.enableEvents(false).
                addBehavior(
                    new RotateBehavior().
                            setFrameTime( 0, 20000 ).
                            setValues( 0, 2 * Math.PI ).
                            setCycle( true ) );

        actor.centerAt( director.width/2, director.height/2 );
        return actor;
    }

// create an actor with a custom paint method. its behavior resembles that of
// a button.
    public Actor createButton(Director director, final boolean rotated) {
        Actor actor= new Actor() {
            @Override
            public void paint(Director director, double time) {
                CaatjaContext2d ctx= director.ctx;
              ctx.save();
              if ( rotated ) {
                  ctx.translate( this.width, 0 );
                  ctx.scale(-1,1);
              }
  
              ctx.setFillStyle(this.pointed ? "orange" : "#f3f");
              ctx.fillRect(0,0,this.width,this.height );
  
              ctx.setStrokeStyle(this.pointed ? "red" : "black");
              ctx.strokeRect(0,0,this.width,this.height );
  
              ctx.setStrokeStyle("white");
              ctx.beginPath();
              ctx.moveTo(5,10);
              ctx.lineTo(20,10);
              ctx.lineTo(15,5);
  
              ctx.moveTo(20,10);
              ctx.lineTo(15,15);
  
              ctx.setLineWidth(2);
              ctx.setLineJoin("round");
              ctx.setLineCap("round");
              ctx.stroke();
              ctx.restore();
  
              ctx.setFont(new TextFont(10, "px", "sans-serif"));
              ctx.setFillStyle("black");
              ctx.fillText(
                  rotated ? "Prev Scene" : "Next Scene",
                  3,
                  45);
            }
            
        }.
                setSize( 60, 60 ).
                centerAt( director.width - 40, director.height - 40 );

        return actor;
    }

// create a background pattern of horizontal and vertical lines
    public Actor createPattern(Director director, final String color) {
        Actor actor= new Actor() {
            @Override
            public void paint(Director director, double time) {
              double i,j;
              CaatjaContext2d ctx;
                
                            if ( this.backgroundImage != null ) {
                                this.backgroundImage.paint(director,0,0,0);
                                return;
                            }
                
                            ctx= director.ctx;
                
                            for( j=0.5; j<director.width; j+=20 ) {
                                ctx.moveTo( j, 0 );
                                ctx.lineTo( j, director.height );
                            }
                
                            for( i=0.5; i<director.height; i+=20 ) {
                                ctx.moveTo( 0, i );
                                ctx.lineTo( director.width, i );
                            }
                
                            ctx.setStrokeStyle(color);
                            ctx.stroke();
            }
        }.
                setSize(director.width,director.height).
                enableEvents(false);

        return actor;
    }

}
