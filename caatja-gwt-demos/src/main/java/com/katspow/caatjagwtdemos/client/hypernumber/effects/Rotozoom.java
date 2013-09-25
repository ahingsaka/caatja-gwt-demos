package com.katspow.caatjagwtdemos.client.hypernumber.effects;

import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaContext2d;
import com.katspow.caatja.core.canvas.CaatjaColor;

public class Rotozoom {
    
	CaatjaColor pattern=    null;
    double width=      0;
    double height=     0;
    int size=       3000;
    
    public Rotozoom setDimension (double w, double h) {
        this.width= w;
        this.height= h;
        return this;
    }
    
    public Rotozoom setPattern (CaatjaColor pattern) {
        this.pattern= pattern;
        return this;
    }
    
    public void apply (CaatjaContext2d ctx) {

        //ctx.clearRect(0,0,this.width,this.height);

        ctx.beginPath();
        ctx.rect(0,0,this.width,this.height);
        ctx.clip();

        double scaleX, scaleY, tx, ty;
        double timer = Caatja.getTime();

        tx = this.width/2 + Math.sin(timer * 0.0001) * 256;
        ty = this.height/2 + Math.sin(timer * 0.0001) * 256;
        scaleX = (Math.sin(timer*0.00005) + 1.1) * 1.5;
        scaleY = scaleX;
        double angle=Math.PI*2 * Math.cos(timer * 0.00005);

        ctx.save();

            ctx.translate(tx, ty);
            ctx.rotate(angle);
            ctx.translate(-tx, -ty);

            ctx.translate(tx - this.size, ty - this.size);
            ctx.scale(scaleX, scaleY);
            ctx.translate(
                -(tx - this.size / scaleX),
                -(ty - this.size / scaleY));
            ctx.setFillStyle(this.pattern);
            ctx.fillRect(
                this.width/2-this.size,
                this.height/2-this.size,
                2*this.size,
                2*this.size);
        ctx.restore();

    }

}
