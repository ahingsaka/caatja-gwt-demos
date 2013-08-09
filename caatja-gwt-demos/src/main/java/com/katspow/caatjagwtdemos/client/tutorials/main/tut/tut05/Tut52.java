package com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut05;

import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.core.canvas.CaatjaContext2d;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;
import com.katspow.caatja.foundation.timer.Callback;
import com.katspow.caatja.foundation.timer.TimerTask;
import com.katspow.caatja.foundation.ui.TextActor;

public class Tut52 {
    
    public void start(CaatjaCanvas canvas) throws Exception {
        
        Director director = new Director().initialize(700, 300, canvas);
        
     // create 2 scenes to show timers are per scene.
        createScene(director, false, "1");
        createScene(director, true, "2");

        Caatja.loop(60);
        
    }
    
    private TextActor createText(Director director, String color) {
        TextActor actor= new TextActor().
                setFont("100px Lucida sans").
                calcTextSize(director).
                setFillStyle(color).
                setOutline(true).
                enableEvents(false);

        return actor;
    }

    private Actor createButton(Director director, final boolean rotated) {
        Actor actor= new Actor() {
            @Override
            public void paint(Director director, double time) {
                
                CaatjaContext2d ctx= director.ctx;
              ctx.save();
              if ( rotated ) {
                  ctx.translate( this.width, 0 );
                  ctx.scale(-1,1);
              }
  
              ctx.setFillStyle(this.pointed ? "orange" : "#f3f");
              ctx.fillRect(0,0,this.width,this.height );
  
              ctx.setStrokeStyle(this.pointed ? "red" : "black");
              ctx.strokeRect(0,0,this.width,this.height );
  
              ctx.setStrokeStyle("white");
              ctx.beginPath();
              ctx.moveTo(5,10);
              ctx.lineTo(20,10);
              ctx.lineTo(15,5);
  
              ctx.moveTo(20,10);
              ctx.lineTo(15,15);
  
              ctx.setLineWidth(2);
              ctx.setLineJoin("round");
              ctx.setLineCap("round");
              ctx.stroke();
              ctx.restore();
  
              ctx.setFont("10px sans-serif");
              ctx.setFillStyle("black");
              ctx.fillText(
                  rotated ? "Prev Scene" : "Next Scene",
                  3,
                  45);
                
            }
            
        }.
                setSize( 60, 60 ).
                centerAt( director.width - 40, director.height - 40 );

        return actor;
    }

    private String format(double time ) {
        
        int t = (int) time;
        
        int millis= (int) (t%1000);
        t/=1000;
        t>>=0;

        int seconds= (int) (t%60);
        t/=60;
        t>>=0;

        int mins= (int) (t%60);
        t/=60;
        t>>=0;

        int hours= t;

        return ""+
                (hours>9 ? hours : "0"+hours)+
                " : "+
                (mins>9 ? mins : "0"+mins)+
                " : "+
                (seconds>9 ? seconds : "0"+seconds)+
                "."+
                millis;
        
    }
    
    int seconds1= 0;
    int seconds2 = 0;

    private void createScene(Director director, boolean rotated, final String name) throws Exception {

        Scene scene1 = director.createScene();
        final TextActor s1t1= createText(director, "red" );
        final TextActor s1t2= createText(director, "green" );
        scene1.addChild(s1t1.setLocation( 20,20 ));
        scene1.addChild(s1t2.setLocation( 20, 150 ));

        // create a timer which runs for ever
        // on every timer tick, update counter.
        scene1.createTimer(0, Double.MAX_VALUE, new Callback() {
            @Override
            public void call(double time, double ttime, TimerTask timerTask) {
            }
        }, new Callback() {

            @Override
            public void call(double time, double ttime, TimerTask timerTask) {
                s1t1.setText( format(ttime) );
            }
            
        }, new Callback() {
            @Override
            public void call(double time, double ttime, TimerTask timerTask) {
            }
        });
        
        
        scene1.createTimer(0, 1000, new Callback() {
            @Override
            public void call(double time, double ttime, TimerTask timerTask) {
                if (name.equals("1")) {
                    seconds1 += 1000;
                    timerTask.reset(time);
                    s1t2.setText(format(seconds1));
                } else if (name.equals("2")) {
                    seconds2 += 1000;
                    timerTask.reset(time);
                    s1t2.setText(format(seconds2));
                }
            }
        }, new Callback() {

            @Override
            public void call(double time, double ttime, TimerTask timerTask) {
            }

        }, new Callback() {
            @Override
            public void call(double time, double ttime, TimerTask timerTask) {
            }
        });

        // this timer increases timer count on timeout, and then, resets itself.
        Actor button= createButton(director, rotated);
//        button.mouseClick= function(e) {
//            if ( !rotated ) {
//                director.switchToNextScene(
//                    2000,
//                    false,
//                    true);
//            } else {
//                director.switchToPrevScene(
//                    2000,
//                    false,
//                    true);
//            }
//
//        }
        scene1.addChild(button);

    }

}
