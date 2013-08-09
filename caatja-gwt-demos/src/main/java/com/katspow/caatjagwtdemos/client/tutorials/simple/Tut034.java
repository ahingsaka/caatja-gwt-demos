package com.katspow.caatjagwtdemos.client.tutorials.simple;

import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaContext2d;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.ActorContainer;

/**
 * Shows squares that can be moved via mouse click.
 * 
 * Bug on hover background ???
 * @author Senizaka
 *
 */
public class Tut034 {
    
    public void init() throws Exception {
        Director _director_5= new Director().initialize(
                600,
                200,
                null);

        Scene _scene_5= _director_5.createScene().setFillStyle("#c0c0c0");
        _director_5.addScene( _scene_5 );

        for(int i=0; i<2; i++ ) {

            // rectangle shaped actors of 80x80 pixels.
            int s = 80;

            // containers can contain other actors or containers.
            ActorContainer _c5_container = (ActorContainer) new ActorContainer() {
                @Override
                public void paint(Director director, double time) {
                    CaatjaContext2d crx= director.ctx;

                    // fill actor
                    crx.setFillStyle(this.fillStyle);
                    crx.fillRect(0,0,this.width,this.height );

                    // outline it.
                    crx.setStrokeStyle("black");
                    crx.strokeRect(0,0,this.width,this.height );

                    // draw a white arrow. just to point where position 0,0 is.
                    crx.setStrokeStyle("white");
                    crx.beginPath();
                    crx.moveTo(5,10);
                    crx.lineTo(20,10);
                    crx.lineTo(15,5);

                    crx.moveTo(20,10);
                    crx.lineTo(15,15);

                    crx.setLineWidth(2);
                    crx.setLineJoin("round");
                    crx.setLineCap("round");

                    crx.stroke();
                }
            }.
                    setBounds(i*400+10, 20, s, s).
                    setRotation( Math.PI*2*Math.random() ).
                    setFillStyle("#ff3fff");
            
                    _c5_container.enableDrag();
            
            if (i==0) {
                _c5_container.setClip(true);
            } else {
                _c5_container.setClip(false);
            }

            // set container paint routine to draw an arrow

            // add actor to scene.
            _scene_5.addChild(_c5_container);

            // create a container.
            ActorContainer _c5_container_child= (ActorContainer) new ActorContainer() {
                @Override
                public void paint(Director director, double time) {
                 // call default container paint method.
                    super.paint(director,time);
                    CaatjaContext2d ctx= director.ctx;

                    // fill a white circle of 10x10 pixels at position 2,2
                    // just to show where 0,0 is positioned on screen.
                    ctx.setFillStyle("white");
                    ctx.beginPath();
                    ctx.arc(7,7,5,0,2*Math.PI,false);
                    ctx.fill();
                }
            }.
                    setBounds(s/2,s/2,s/4,s/4).
                    setRotation( Math.PI*2*Math.random() ).
                    setFillStyle("#00ff00");
            _c5_container_child.enableDrag();


            // add this container as a child of the previous created container.
            _c5_container.addChild(_c5_container_child);
        }

        Caatja.loop(20);
    }

}
