package com.katspow.caatjagwtdemos.client.hypernumber.core;

import com.katspow.caatja.core.canvas.CaatjaContext2d;
import com.katspow.caatja.core.canvas.CaatjaGradient;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.actor.Actor;
import com.katspow.caatjagwtdemos.client.hypernumber.core.context.Context;
import com.katspow.caatjagwtdemos.client.hypernumber.core.context.ContextListener;

public class Chrono extends Actor implements ContextListener {
    
    public Chrono() {
        super();
    }
    
    CaatjaGradient gradient=   null;
    double maxTime=    0;
    double elapsedTime=0;
    String method= "_paint";

    public void paint (Director director, double time ) {
        CaatjaContext2d ctx= director.ctx;

        if ( null==this.gradient ) {
            this.gradient= ctx.createLinearGradient(0,0,this.width,0);
            this.gradient.addColorStop(0,"red");
            this.gradient.addColorStop(0.3,"yellow");
            this.gradient.addColorStop(1,"green");
        }

        ctx.setFillStyle(this.gradient);
        double size=
                this.maxTime!=0 ?
                        this.width - this.elapsedTime/this.maxTime * this.width :
                        this.width;
        
        ctx.fillRect(0,0,size,this.height);

        ctx.setStrokeStyle("black");
        ctx.strokeRect(0,0,this.width,this.height);
    }
    
    public void tick (double iElapsedTime, double maxTime ) {
        this.maxTime= maxTime;
        this.elapsedTime= iElapsedTime;
    }
    
    @Override
    public void contextEvent (Event event) {
        if ( event.source=="context" && event.event=="status") {
            if ( event.params==Context.ST_ENDGAME ) {
                this.maxTime=0;
                this.elapsedTime= 1000;
            }
        }
    }

}
