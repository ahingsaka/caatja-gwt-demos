package com.katspow.caatjagwtdemos.client.welcome.hypernumber.startmenu;

import java.util.ArrayList;
import java.util.List;

import com.katspow.caatja.core.canvas.CaatjaContext2d;
import com.katspow.caatja.core.canvas.CaatjaGradient;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.actor.Actor;

public class Garden extends Actor {
    
    public Garden() {
        super();
    }
    
    List<Grass> grass=          null;
    double ambient=        1;
    List<Double> stars=          null;
    int firefly_radius= 10;
    int num_fireflyes=  40;
    int num_stars=      512;
    String[] fireflyColor = new String[]{ "#ffff00", "#7fff00", "#c0c000"};

    public Garden initialize (CaatjaContext2d ctx, int size, int maxGrassHeight) {
        this.grass= new ArrayList<Grass>();

        for(int i=0; i<size; i++ ) {
            Grass g= new Grass();
            g.initialize(
                    this.width,
                    this.height,
                    50,         // min grass height
                    maxGrassHeight, // max grass height
                    20,         // grass max initial random angle
                    40          // max random angle for animation
                    );
            this.grass.add(g);
        }

        this.stars= new ArrayList<Double>();
        for(int i=0; i<this.num_stars; i++ )   {
            this.stars.add( Math.floor( Math.random()*(this.width-10)+5  ) );
            this.stars.add( Math.floor( Math.random()*(this.height-10)+5 ) );
        }

        this.lerp(ctx,0,2000);

        return this;
    }

    public void paint (Director director, double time){


        CaatjaContext2d ctx= director.ctx;

//        ctx.setFillStyle(this.gradient);
//        ctx.fillRect(0,0,this.width,this.height);

        // draw stars if ambient below .3 -> night
        if ( this.ambient<.3 )  {

            // modify stars translucency by ambient (as transitioning to day, make them dissapear).
            ctx.setGlobalAlpha(1-((this.ambient-.05)/.25));

            // as well as making them dimmer
            double intensity= 1 - (this.ambient/2-.05)/.25;

            // how white do you want the stars to be ??
            int c= (int) Math.floor( 192*intensity );
            String strc= "rgb("+c+","+c+","+c+")";
            ctx.setStrokeStyle(strc);

            // first num_fireflyes coordinates are fireflyes themshelves.
            for( int j=this.num_fireflyes*2; j<this.stars.size(); j+=2 )    {
                double inc=1;
                if ( j%3==0 ) {
                    inc=1.5;
                } else if ( j%11==0 ) {
                    inc=2.5;
                }
                this.stars.set(j, (this.stars.get(j)+.1*inc)%this.width);

                double y= this.stars.get(j+1);
                ctx.strokeRect(this.stars.get(j),this.stars.get(j+1),1,1);

            }
        }

        ctx.setGlobalAlpha(1);

        // draw fireflyes
        
        for(int i=0; i<this.num_fireflyes*2; i+=2) {
            ctx.setFillStyle(this.fireflyColor[i%3]);
            double angle= Math.PI*2*Math.sin(time*3E-4) + i*Math.PI/50;
            double radius= this.firefly_radius*Math.cos(time*3E-4);
            ctx.beginPath();
            ctx.arc(
                    this.width/2 +
                    .5*this.stars.get(i) +
                    150*Math.cos(time*3E-4) +   // move horizontally with time
                    (radius+20*Math.cos((i%5)*Math.PI/3600))*Math.cos(angle),

                    this.height/2 +
                    .5*this.stars.get(i+1) +
                    20*Math.sin(time*3E-4) +    // move vertically with time
                    radius*Math.sin(angle),

                    2,
                    0,
                    Math.PI*2,
                    false );
            ctx.fill();
        }


        for( int i=0; i<this.grass.size(); i++ ) {
            this.grass.get(i).paint(ctx,time,this.ambient);
        }

        // lerp.
        if ( time>this.nextLerpTime ) {
            this.lerpindex= (int) Math.floor((time-this.nextLerpTime)/this.nextLerpTime);
            if ( (time-this.nextLerpTime)%this.nextLerpTime<this.lerpTime ) {
                this.lerp( ctx, (time-this.nextLerpTime)%this.nextLerpTime, this.lerpTime );
            }
        }

    }

    CaatjaGradient gradient=       null;
    double lerpTime=       10000;      // time taken to fade sky colors
    double nextLerpTime=   15000;  // after fading; how much time to wait to fade colors again.
    int[][] colors= new int[][]        {
            {   0x00, 0x3f, 0x7f, //0x00, 0x00, 0x3f,
                            0x00, 0x3f, 0x7f,
                            0x1f, 0x5f, 0xc0,
                            0x3f, 0xa0, 0xff },

                        {   0x00, 0x3f, 0x7f,
                          0xa0, 0x5f, 0x7f,
                          0xff, 0x90, 0xe0,
                          0xff, 0x90, 0x00 },

                        {     0x00, 0x3f, 0x7f, //0x00, 0x00, 0x00,
                        0x00, 0x2f, 0x7f,
                        0x00, 0x28, 0x50,
                        0x00, 0x1f, 0x3f },

                        { 0x00, 0x3f, 0x7f, //0x1f, 0x00, 0x5f,
                          0x3f, 0x2f, 0xa0,
                          0xa0, 0x1f, 0x1f,
                          0xff, 0x7f, 0x00 } };

    double [] ambients= new double []{ 1, .35, .05, .5 };    // ambient intensities for each sky color
    int lerpindex=      0;                      // start with this sky index.

    /**
     * fade sky colors
     * @param time current time
     * @param last how much time to take fading colors
     */
    public void lerp(CaatjaContext2d ctx, double time, double last ) {
        this.gradient= ctx.createLinearGradient(0,0,0,this.height);

        int i0= this.lerpindex%this.colors.length;
        int i1= (this.lerpindex+1)%this.colors.length;

        for( int i=0; i<4; i++ )    {
            String rgb="rgb(";
            for( int j=0; j<3; j++ ) {
                rgb+= (int) Math.floor( (this.colors[i1][i*3+j]-this.colors[i0][i*3+j])*time/last + this.colors[i0][i*3+j]);
                if ( j<2 ) rgb+=",";
            }
            rgb+=")";
            this.gradient.addColorStop( i/3, rgb );
        }

        this.ambient= (this.ambients[i1]-this.ambients[i0])*time/last + this.ambients[i0];
    }

}
