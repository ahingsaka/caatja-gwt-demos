package com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut02;

import java.util.Map;

import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.core.canvas.CaatjaImage;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.image.SpriteImage;

public class Tut29 {

    public void start(CaatjaCanvas canvas, Map<String, CaatjaImage> images) throws Exception {

        Director director = new Director().initialize(200, 200, canvas);
        director.imagesCache = images;
        
        Scene scene = director.createScene();
        
     // an image of 7 rows by 3 columns
        SpriteImage ci= new SpriteImage().initialize(
                (director.getImage("botones")), 7, 3 );

        // TODO 
//        Actor b1= new Actor().setAsButton(
//                    ci.getRef(), 0, 1, 2, 0, function(button) {
//                        Window.alert("easy pressed");
//                    }
//                ).
//                setLocation(0,30);
//
//        Actor b2= new Actor().setAsButton(
//                    ci.getRef(), 6, 7, 8, 6, function(button) {
//                        Window.alert("start pressed");
//                    }
//                ).
//                setLocation(1.5*ci.singleWidth, 30);
//
//        scene.addChild( b1 );
//        scene.addChild( b2 );
    }

}
