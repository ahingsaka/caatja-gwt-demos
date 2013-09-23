package com.katspow.caatjagwtdemos.client.showcase.fish;

import com.katspow.caatja.core.canvas.CaatjaContext2d;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.actor.Actor;

public class FishScene12 extends Actor {

    private String bodyColor = "red";
    private double timeOffset = 0;
    private boolean antihead = false;
    private double headProportion = 0;
    private double maxAngle = 45;
    private int tailSize = 0;
    private double headpos;
    private double f1 = 0;
    private double f2 = 0;
    private int f3 = 0;
    private int f4 = 0;
    private double[] tail;
    private double[] tail1;
    private double[] head1;
    private double[] head2;
    private double[] tail2;

    public FishScene12() {
        this.tail = new double[2];
        this.tail1 = new double[6];
        this.tail2 = new double[4];
        this.head1 = new double[4];
        this.head2 = new double[4];
    }

    public FishScene12 setBodyColor(String color) {
        this.bodyColor = color;
        return this;
    }

    /**
     * born method must be called after Actor complete definition.
     */
    public FishScene12 born() {
        this.f1 = ((int) (Math.random() * 2) >> 0) / 1000 + .002;
        this.f2 = ((int) (Math.random() * 2) >> 0) / 1000 + .002;
        this.f3 = ((int) (Math.random() * 5) >> 0) / 1000;
        this.f4 = ((int) (Math.random() * 5) >> 0) / 1000;

        this.tailSize = (int) (Math.random() * 3 + 2) >> 0;
        this.timeOffset = (Math.random() * 10000);

        this.antihead = Math.random() < .25;
        if (this.antihead) {
            this.headProportion = Math.random() * .25 + .65;
            if (this.headProportion < .8) {
                this.headProportion = .8;
            }
        } else {
            this.headProportion = Math.random() * .25 + .6;
        }

        this.maxAngle = 40 + 25 * Math.random();

        double w = this.width;
        double h = this.height;

         double w2 = w / 2;
        double h2 = h / 2;

        double headStart = this.antihead ? w : w * this.headProportion;

        this.tail[0] = 0;
        this.tail[1] = h2;

        // ///////////---------------

        // tail son 1 y 2 punto de control, y el ultimo punto de la cubica.
        // ek primer punto fijo sera tail.
        this.tail1[0] = w2;
        this.tail1[1] = h2;

        this.tail1[2] = w2;
        this.tail1[3] = 0;

        this.tail1[4] = headStart;
        this.tail1[5] = 0;

        this.headpos = w;
        if (this.antihead) {
            this.headpos = w * this.headProportion;
        }

        this.head1[0] = this.headpos;
        this.head1[1] = 0;
        this.head1[2] = this.headpos;
        this.head1[3] = h2;

        this.head2[0] = this.headpos;
        this.head2[1] = h;
        this.head2[2] = headStart;
        this.head2[3] = h;

        this.tail2[0] = w2;
        this.tail2[1] = h;

        this.tail2[2] = w2;
        this.tail2[3] = h2;

        return this;
    }

    @Override
    public void paint(Director director, double time) {
        CaatjaContext2d ctx= director.ctx;

        time+= this.timeOffset;

        double w= this.width;
        double h= this.height;
        double w2= w/2;
        double w4 = w2 / 2;
        double h2= h/2;


        // .002 .003
        double ang= Math.cos(time*this.f1)*this.maxAngle*Math.sin(time*this.f2)*Math.PI/180;
        double px= w4-w4*Math.cos(ang);
        double py= w4*Math.sin(ang);

        this.tail[0]= px;
        this.tail[1]= h2+ py;


        double inc= -3*(Math.sin(time*.005)+Math.cos(time*.001))/2;

        this.tail1[0]= w4;
        this.tail1[1]= h2+inc;
        this.tail1[2]= w2;
        this.tail1[3]= inc;

        this.tail2[0]= w2;
        this.tail2[1]= h+inc;
        this.tail2[2]= w4;
        this.tail2[3]= h2+inc;


        ctx.beginPath();
        ctx.setFillStyle("orange");

        //////////////////////////////////////////////// START ALETAS
        double aletaSize = h2; // ancho
        int aletaHeight = 7; // alto aleta
        int aletaWidth = 5;
        double aletaPos = w * 2 / 3 + aletaWidth;

        ctx.beginPath();
            ctx.moveTo( aletaPos, 2 );
            ctx.quadraticCurveTo(
                    aletaPos, -aletaHeight,
                    aletaPos-aletaSize, -aletaHeight-3*Math.sin(time*.002) );
            ctx.lineTo( aletaPos-aletaWidth, 2);
            ctx.closePath();
        ctx.fill();
        ctx.beginPath();
            ctx.moveTo( aletaPos, h-2 );
            ctx.quadraticCurveTo(
                    aletaPos, h+aletaHeight,
                    aletaPos-aletaSize, h+aletaHeight-3*Math.cos(time*.002) );
            ctx.lineTo( aletaPos-aletaWidth, h-2 );
            ctx.closePath();
        ctx.fill();
        //////////////////////////////////////////////// END ALETAS

        //////////////////////////////////////////////// START BODY
        ctx.beginPath();
            ctx.moveTo( this.tail[0], this.tail[1] + this.tailSize );
            ctx.bezierCurveTo(
                    this.tail1[0], this.tail1[1],
                    this.tail1[2], this.tail1[3],
                    this.tail1[4], this.tail1[5] );
            ctx.quadraticCurveTo(
                    this.head1[0], this.head1[1],
                    this.head1[2], this.head1[3]
                    );
            ctx.quadraticCurveTo(
                    this.head2[0], this.head2[1],
                    this.head2[2], this.head2[3]
                    );
            ctx.bezierCurveTo(
                    this.tail2[0], this.tail2[1],
                    this.tail2[2], this.tail2[3],
                    this.tail[0], this.tail[1] - this.tailSize );
        ctx.closePath();
        ctx.setFillStyle(this.bodyColor);
        ctx.fill();
        ctx.setStrokeStyle(this.bodyColor);
        ctx.stroke();
        //////////////////////////////////////////////// END BODY

        //////////////////////////////////////////////// START EYES
        ctx.beginPath();
        ctx.setFillStyle("black");
        double eyeradius = h2 / 6;

        if ( this.antihead ) {
            ctx.arc(this.headpos-eyeradius*2, h2-h2/3, eyeradius, 0, 2*Math.PI, false );
            ctx.arc(this.headpos-eyeradius*2, h2+h2/3, eyeradius, 0, 2*Math.PI, false );

        } else {
            ctx.arc(w-w2/4, h2-h2/3, eyeradius, 0, 2*Math.PI, false );
            ctx.arc(w-w2/4, h2+h2/3, eyeradius, 0, 2*Math.PI, false );
        }
        ctx.fill();
        //////////////////////////////////////////////// END EYES
        
    }

}
