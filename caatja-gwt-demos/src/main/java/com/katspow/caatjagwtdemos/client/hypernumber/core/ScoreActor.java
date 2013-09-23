package com.katspow.caatjagwtdemos.client.hypernumber.core;

import com.katspow.caatja.behavior.Interpolator;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.ui.TextActor;
import com.katspow.caatjagwtdemos.client.hypernumber.core.context.Context;
import com.katspow.caatjagwtdemos.client.hypernumber.core.context.ContextListener;

public class ScoreActor extends TextActor implements ContextListener {
    
    public ScoreActor() {
        super();
        this.interpolator= new Interpolator().createExponentialInOutInterpolator(2,false);
    }
    
    int incrementScore= 0;
    int maxScore=       0;
    int minScore=       0;
    int currentScore=   0;

    double startTime=      0;
    Interpolator interpolator=   null;
    double scoreDuration=  2000;


    public void reset () {
        this.currentScore= 0;
        this.maxScore= 0;
        this.minScore= 0;
        this.setText("000000");
    }
    
    public void contextEvent (Event event ) {
        if ( event.source.equals("context")) {
            if ( event.event.equals("score")) {
                this.maxScore= event.score;
                this.minScore= this.currentScore;
                this.incrementScore= this.maxScore- this.minScore;
                this.startTime= this.time;
            } else if ( event.event.equals("status")) {
                if ( event.params==Context.ST_STARTGAME ) {
                    this.reset();
                }
            }
        }
    }
    
    public void setScore(Director director) {
        this.currentScore>>=0;
        String str= "" +this.currentScore;
        while( str.length()<6 ) {
            str="0"+str;
        }
        this.setText( ""+this.currentScore );
        this.calcTextSize(director);
        this.x= this.parent.width-this.textWidth;
    }
    
    public boolean animate(Director director, double time) throws Exception {
        if ( time>= this.startTime && time<this.startTime+this.scoreDuration ) {
            this.currentScore= 
                    this.minScore +
                        this.incrementScore *
                        (int) this.interpolator.getPosition( (time-this.startTime)/this.scoreDuration ).y;
            this.setScore(director);
            
        } else {
            if ( this.currentScore!=this.maxScore ) {
                this.currentScore= this.maxScore;
                this.setScore(director);
            }
        }

        return super.animate(director, time);
    }

}
