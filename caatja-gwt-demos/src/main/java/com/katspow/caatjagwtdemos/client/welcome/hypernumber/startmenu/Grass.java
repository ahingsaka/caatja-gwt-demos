package com.katspow.caatjagwtdemos.client.welcome.hypernumber.startmenu;

import com.katspow.caatja.core.canvas.CaatjaContext2d;

public class Grass {
    
    double alto_hierba=            0;      // grass height
    double maxAngle=               0;      // maximum grass rotation angle (wind movement)
    double angle=                  0;      // construction angle. thus; every grass is different to others
    double[] coords=                 null;   // quadric bezier curves coordinates
    int[] color=                  null;   // grass color. modified by ambient component.
    int offset_control_point=   3;      // grass base size. greater values; wider at the basement.

    void initialize (double canvasWidth,double canvasHeight,double minHeight,double maxHeight,double angleMax,double initialMaxAngle)   {

        // grass start position
        double sx= Math.floor( Math.random()*canvasWidth );
        double sy= canvasHeight;

        int offset_control_x=2;


        this.alto_hierba= minHeight+Math.random()*maxHeight;
        this.maxAngle= 10+Math.random()*angleMax;
        this.angle= Math.random()*initialMaxAngle*(Math.random()<.5?1:-1)*Math.PI/180;

        // hand crafted value. modify offset_control_x to play with grass curvature slope.
        double csx= sx-offset_control_x ;

        // curvatura de la hierba. - menor, curva mas tiesa. +valor, hierba lacia.
        // grass curvature. greater values make grass bender.
        double csy= sy-this.alto_hierba/2;

        double psx= csx;
        double psy= csy;

        // the bigger offset_control_point, the wider on its basement.
        this.offset_control_point=10;
        double dx= sx+this.offset_control_point;
        double dy= sy;

        this.coords= new double[]{sx,sy,csx,csy,psx,psy,dx,dy};

        // grass color.
        this.color= new int[] {16+(int)Math.floor(Math.random()*32),
                     100+(int)Math.floor(Math.random()*155),
                     16+(int)Math.floor(Math.random()*32) };

    }

    /**
     * paint every grass.
     * @param ctx is the canvas2drendering context
     * @param time for grass animation.
     * @param ambient parameter to dim or brighten every grass.
     * @returns nothing
     */
    void paint (CaatjaContext2d ctx, double time, double ambient) {

//      ctx.save();

        // grass peak position. how much to rotate the peak.
        // less values (ie the .0005), will make as if there were a softer wind.
        double inc_punta_hierba= Math.sin(time*.0005);

        // rotate the point, so grass curves are modified accordingly. If just moved horizontally, the curbe would
        // end by being unstable with undesired visuals.
        double ang= this.angle + Math.PI/2 + inc_punta_hierba * Math.PI/180*(this.maxAngle*Math.cos(time*.0002));
        double px= this.coords[0]+ this.offset_control_point + this.alto_hierba*Math.cos(ang);
        double py= this.coords[1]                           - this.alto_hierba*Math.sin(ang);

        ctx.beginPath();
        ctx.moveTo( this.coords[0], this.coords[1] );
        ctx.bezierCurveTo(this.coords[0], this.coords[1], this.coords[2], this.coords[3], px, py);

        ctx.bezierCurveTo(px, py, this.coords[4], this.coords[5],  this.coords[6], this.coords[7]);
        ctx.closePath();
        ctx.setFillStyle("rgb("+
                (int) Math.floor(this.color[0]*ambient)+","+
                (int) Math.floor(this.color[1]*ambient)+","+
                (int) Math.floor(this.color[2]*ambient)+")");
        ctx.fill();
/*
        ctx.strokeStyle='rgb('+
                Math.floor(this.color[0]*ambient)+','+
                Math.floor(this.color[1]*ambient)+','+
                Math.floor(this.color[2]*ambient)+')';
        ctx.stroke();
*/
//      ctx.restore();

    }

}
