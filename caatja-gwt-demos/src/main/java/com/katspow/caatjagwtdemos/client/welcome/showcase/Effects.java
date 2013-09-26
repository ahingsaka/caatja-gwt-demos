package com.katspow.caatjagwtdemos.client.welcome.showcase;

import com.katspow.caatja.behavior.Interpolator;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;

public final class Effects {
    
    public static void welcome(final Director director) {
        director.easeIn(
                0,
                Scene.Ease.SCALE,
                2000,
                false,
                Actor.Anchor.CENTER,
                new Interpolator().createElasticOutInterpolator(2.5, .4, false) );
    }
    

}
