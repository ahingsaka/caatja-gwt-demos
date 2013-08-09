package com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut02;

import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.core.canvas.CaatjaContext2d;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;
import com.katspow.caatja.foundation.actor.ActorContainer;

public class Tut21 {
    
    public void start(CaatjaCanvas canvas) throws Exception {
        Director director = new Director().initialize(600, 400, canvas);
//        Scene scene_1 = director.createScene().setFillStyle("#fff");
        
        Scene scene_1 = new Scene() {
            @Override
            public void paint(Director director, double time) {
                // make the scene clear background and draw a black rectangle
                CaatjaContext2d ctx = director.ctx;
              ctx.setFillStyle(this.fillStyle);
              ctx.fillRect(0, 0, this.width, this.height);
              ctx.setStrokeStyle("#000");
              ctx.strokeRect(0, 0, this.width - 1, this.height - 1);
            }
            
        }.setFillStyle("#fff");
        
        director.addScene(scene_1);
        
        // create six actors for this scene.
        for (int i = 0; i < 6; i++) {

            // rectangle shaped actors of 80x80 pixels.
            int s = 80;

            // containers can contain other actors or containers.
            ActorContainer _c1_container = new ActorContainer() {

                @Override
                public void paint(Director director, double time) {
                    // set container paint routine to draw an arrow
                    CaatjaContext2d crx = director.ctx;

                    // fill actor
                    crx.setFillStyle(this.fillStyle);
                    crx.fillRect(0, 0, this.width, this.height);

                    // outline it.
                    crx.setStrokeStyle("black");
                    crx.strokeRect(0, 0, this.width, this.height);

                    // draw a white arrow. just to point where position 0,0 is.
                    crx.setStrokeStyle("white");
                    crx.beginPath();
                    crx.moveTo(5, 10);
                    crx.lineTo(20, 10);
                    crx.lineTo(15, 5);

                    crx.moveTo(20, 10);
                    crx.lineTo(15, 15);

                    crx.setLineWidth(2);
                    crx.setLineJoin("round");
                    crx.setLineCap("round");

                    crx.stroke();
                }
                
            }.
                    setBounds(i * 100 + 10, 20, s, s).
                    setRotation(Math.PI * 2 * Math.random()).
                    setFillStyle("#ff3fff");
            _c1_container.enableDrag();

            _c1_container.name = "rectangle" + i;
            


            // add actor to scene.
            scene_1.addChild(_c1_container);

            // create a container.
            Actor _c1_container_child = new Actor() {
                @Override
                public void paint(Director director, double time) {
                    // set a custom paint function for children inside
                    // containers.
                    super.paint(director, time);

                    CaatjaContext2d ctx = director.ctx;

                    // fill a white circle of 10x10 pixels at position 2,2
                    // just to show where 0,0 is positioned on screen.
                    ctx.setFillStyle("white");
                    ctx.beginPath();
                    ctx.arc(7, 7, 5, 0, 2 * Math.PI, false);
                    ctx.fill();
                }

            }.
                    setBounds(s / 2, s / 2, s / 4, s / 4).
                    setRotation(Math.PI * 2 * Math.random()).
                    setFillStyle("#00ff00");
                    _c1_container_child.enableDrag();

            


            // add this container as a child of the previous created container.
            _c1_container.addChild(_c1_container_child);
        }

        // set animation to 10fps.
        Caatja.loop(30);
    }

}
