package com.katspow.caatjagwtdemos.client.hypernumber.effects;

import com.katspow.caatja.core.canvas.CaatjaImage;
import com.katspow.caatja.core.canvas.CaatjaPattern;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.actor.Actor;

public class RotoZoomActor extends Actor {
    
    Rotozoom rotozoomer = null;

    public RotoZoomActor initialize(Director director, CaatjaImage image) {
        CaatjaPattern pattern= director.ctx.createPattern(image,"repeat");
        this.rotozoomer= new Rotozoom().
                setDimension(this.width,this.height).
                setPattern( pattern );
        return this;
    }
    
    public void paint(Director director, double time) {
        this.rotozoomer.apply(director.ctx);
    }

}
