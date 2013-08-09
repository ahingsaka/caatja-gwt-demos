package com.katspow.caatjagwtdemos.client.demos.hypernumber;

import com.katspow.caatja.foundation.ui.TextActor;

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
