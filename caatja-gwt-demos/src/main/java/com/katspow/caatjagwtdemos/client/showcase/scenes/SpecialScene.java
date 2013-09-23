package com.katspow.caatjagwtdemos.client.showcase.scenes;

import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;
import com.katspow.caatja.foundation.actor.ActorContainer;

public class SpecialScene extends Scene {
    
    public void  magneticField(double x, double y, ActorContainer pezContainer) {
        for(int i=0; i<pezContainer.childrenList.size(); i++ ) {
            Actor actor= pezContainer.childrenList.get(i);
            double angle=  Math.atan2( 
                    y - (actor.y + actor.height/2), 
                    x - (actor.x + actor.width/2) );
            actor.setRotation( angle );
        }       
    };

}
