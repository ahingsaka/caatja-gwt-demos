package com.katspow.caatjagwtdemos.client.demos.hypernumber;

import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.ImageActor;
import com.katspow.caatja.foundation.timer.Callback;
import com.katspow.caatja.foundation.timer.TimerTask;

public class AnimatedBackground extends ImageActor implements ContextListener {
    
    public AnimatedBackground() {
        super();
    }
    
    TimerTask timer =   null;
    Scene scene =  null;

    public AnimatedBackground setScene (Scene scene) {
        this.scene= scene;
        return this;
    }
    
    public void contextEvent (Event event ) {

        if ( event.source.equals("context") ) {
            if ( event.event.equals("status")) {
                if ( event.params==Context.ST_STARTGAME ) {
                    this.timer= this.scene.createTimer(
                        scene.time,
                        100,
                        new Callback() {
                            @Override
                            public void call(double time, double ttime, TimerTask timerTask) {
                                offsetY+= .2;
                                if ( offsetY>0 ) {
                                    offsetY=0;
                                }
                                timerTask.reset( scene.time );
                            }
                        },
                        null,
                        null );
                    
                } else if ( event.params==Context.ST_ENDGAME ) {
                    if ( this.timer!=null ) {
                        this.timer.cancel();
                        this.timer= null;
                    }
                }
            }
        }
    }
    

}
