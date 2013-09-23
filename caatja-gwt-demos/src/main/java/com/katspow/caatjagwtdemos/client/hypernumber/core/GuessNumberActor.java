package com.katspow.caatjagwtdemos.client.hypernumber.core;

import com.katspow.caatja.foundation.ui.TextActor;
import com.katspow.caatjagwtdemos.client.hypernumber.core.context.ContextListener;

public class GuessNumberActor extends TextActor implements ContextListener {
    
    int guessNumber = 0;
    
    public GuessNumberActor() {
        super();
    }
    
    public void contextEvent(Event event ) {
        if ( event.source=="context" && event.event=="guessnumber" ) {
            this.guessNumber=   event.guessNumber;
            this.setText( ""+this.guessNumber );
        }
    }
    
    

}
