package com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut02;

import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.core.canvas.CaatjaContext2d;
import com.katspow.caatja.event.CAATMouseEvent;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;
import com.katspow.caatja.foundation.actor.ActorContainer;

public class Tut26 {
    
    ActorContainer bg;
    Actor arrow;
    
    public void start(CaatjaCanvas canvas) throws Exception {
        
        Director director = new Director().initialize(500, 500, canvas);
        Scene scene = director.createScene();
        
        bg= new ActorContainer() {
            @Override
            public void paint(Director director, double time) {
                // custom paint: stroke a bounding rectangle
                CaatjaContext2d ctx= director.ctx;
              ctx.setStrokeStyle("black");
              ctx.strokeRect(0,0,this.width,this.height);
            }

            @Override
            public void mouseMove(CAATMouseEvent e) throws Exception {
                double angle= Math.atan2(
                      e.y - arrow.height / 2,
                      e.x - arrow.width / 2 );
              arrow.setRotation(angle);
            }
            
        }.
                setBounds(0,0,director.width,director.height);


        scene.addChild(bg);

        arrow= new Actor() {
            @Override
            public void paint(Director director, double time) {
                // custom paint: draw a proportional arrow
                CaatjaContext2d ctx= director.ctx;
              int gap= 80;
  
              // build a random color
              String color= "rgb(";
              color+= (int) time%255;
              color+=",";
              color+= ((int)time>>8)&255;
              color+=",";
              color+= 0xa0;
              color+=")";
              
              ctx.setStrokeStyle(color);
              ctx.beginPath();
              ctx.moveTo(gap, bg.height / 2);
              ctx.lineTo(bg.width - gap, bg.height/2);
              ctx.lineTo( bg.width - gap - (bg.height / 4), bg.height / 4);
  
              ctx.moveTo(bg.width - gap, bg.height/2);
              ctx.lineTo(bg.width - gap - (bg.height / 4), bg.height / 2 + bg.height / 4);
  
              ctx.setLineWidth(15);
              ctx.setLineJoin("round");
              ctx.setLineCap("round");
  
              ctx.stroke();
            }
            
        }.
                setBounds(0,0,director.width,director.height).
                enableEvents(false);
        bg.addChild(arrow);

        Caatja.loop(30);
    }

}
