package com.katspow.caatjagwtdemos.client.welcome.showcase.scenes;

import com.katspow.caatja.behavior.ScaleBehavior;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.ui.TextActor;

public class AnotherSpecialScene extends Scene {
    
    public TextActor loading;
    
    public void finishedLoading() {
        this.loading.setText("Start");
        this.loading.emptyBehaviorList();

        ScaleBehavior sb= new ScaleBehavior().
            setPingPong().
            setValues(1,4,1,4).
            setCycle(true).
            setFrameTime(time, 1000 );

        this.loading.addBehavior(sb);

//        // after changing the from 'loading' to 'start', set mouseclick function to initialize demo.
//        this.loading.mouseClick= function(event) {
//            __CAAT_director_initialize(director);
//        }
    }

}
