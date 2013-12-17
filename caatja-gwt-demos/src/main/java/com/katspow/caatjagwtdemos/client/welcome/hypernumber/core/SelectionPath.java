package com.katspow.caatjagwtdemos.client.welcome.hypernumber.core;

import java.util.ArrayList;
import java.util.List;

import com.katspow.caatja.behavior.PathBehavior;
import com.katspow.caatja.core.canvas.CaatjaContext2d;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.actor.Actor;
import com.katspow.caatja.foundation.ui.TextFont;
import com.katspow.caatja.math.Pt;
import com.katspow.caatja.pathutil.Path;
import com.katspow.caatjagwtdemos.client.welcome.hypernumber.core.brick.Brick;
import com.katspow.caatjagwtdemos.client.welcome.hypernumber.core.context.Context;
import com.katspow.caatjagwtdemos.client.welcome.hypernumber.core.context.ContextListener;

public class SelectionPath extends Actor implements ContextListener {
    
    List<Pt> coords=                 null;   // an array of 2D positions on screen.
    Path path=                   null;
    PathBehavior pathMeasure=            null;
    List<Double> particles=              null;   // an array of random time to position on path.
    int particlesPerSegment=    10;
    double traversingPathTime=     3000;
    int multiplier =             1;
    
    public SelectionPath() {
        super();
        this.coords= new ArrayList<Pt>();
        this.particles= new ArrayList<Double>();
        this.fillStyle= null;
    }
    
    public void initialize () {
        this.coords= new ArrayList<Pt>();
        this.path=           null;
        this.pathMeasure=    null;
    }
    
    public void setup (Context context, int numberWidth, int numberHeight ) {

        this.coords= new ArrayList<Pt>();

        // no bricks, no path
        if ( 0==context.selectedList.size() ) {
            this.initialize();
            return;
        }

        int i;

        // get selected bricks screen coords.
        for( i=0; i<context.selectedList.size(); i++ )  {
            Brick brick= context.selectedList.get(i);
            this.coords.add(
                    new Pt().set(brick.column*numberWidth + numberWidth/2,
                    brick.row*numberHeight + numberHeight/2)
                );
        }

        // setup a path for the coordinates.
        this.path= new Path();
        this.path.beginPath( this.coords.get(0).x, this.coords.get(0).y );
        for( i=1; i<context.selectedList.size(); i++ ) {
            this.path.addLineTo( this.coords.get(i).x, this.coords.get(i).y, null );
        }
        this.path.endPath();


        this.pathMeasure= (PathBehavior) new PathBehavior().
                setPath(this.path).
                setFrameTime(0, this.traversingPathTime*context.selectedList.size()).
                setCycle(true);

        int expectedParticleCount= this.particlesPerSegment*(context.selectedList.size()-1);
        if ( this.particles.size()> expectedParticleCount ) {
//            this.particles.splice( expectedParticleCount, this.particles.size()-expectedParticleCount );
            this.particles = this.particles.subList(expectedParticleCount, particles.size() - 1);
            
            
        } else {
            while( this.particles.size()<expectedParticleCount ) {
                this.particles.add( (context.selectedList.size())*this.traversingPathTime + this.traversingPathTime*Math.random() );
            }
        }
    }
    public void paint (Director director, double time)    {
        if ( this.coords.size()>0 ) {
            CaatjaContext2d ctx= director.ctx;

            ctx.beginPath();
            for(int i=0; i<this.coords.size(); i++ ) {
                ctx.lineTo( this.coords.get(i).x, this.coords.get(i).y );
            }

            ctx.setStrokeStyle(    "#ffff00");
            ctx.setLineCap(        "round");
            ctx.setLineJoin(       "round");
            
            for(int i=2; i<=8; i+=2 ) {
                ctx.setLineWidth(      i);
                ctx.setGlobalAlpha(.5 - i/8/3);
                ctx.stroke();
            }
            
            // draw particles.
            ctx.setFillStyle("#ffffff");
            double s = 8;
            Pt pos;
            
            for(int i=0; i<this.particles.size(); i++) {
                pos= this.pathMeasure.positionOnTime( (this.particles.get(i)+time)*(1+(i%3)*.33) );
                ctx.beginPath();
                ctx.arc( pos.x, pos.y, s/2, 0, Math.PI*2, false );
                ctx.fill();
//                ctx.fillRect( pos.x-s/2, pos.y-s/2, s, s );
            }
            
            if ( this.multiplier>1 ) {
                ctx.setGlobalAlpha(1);
                pos= this.pathMeasure.positionOnTime( 0 );

                ctx.setFillStyle("#ffff00");
                ctx.beginPath();
                ctx.setFont(new TextFont(40, "px", "sans-serif"));
                ctx.fillText( "x"+this.multiplier, pos.x, pos.y );

                ctx.setLineWidth(2);
                ctx.setStrokeStyle("7f7f00");
                ctx.strokeText("x"+this.multiplier, pos.x, pos.y );
            }
            
        }
    }
    
    public void contextEvent(Event event ) {
        if ( event.source.equals("context") && event.event.equals("multiplier")) {
            this.multiplier=   event.multiplier;
        }
    }

}
