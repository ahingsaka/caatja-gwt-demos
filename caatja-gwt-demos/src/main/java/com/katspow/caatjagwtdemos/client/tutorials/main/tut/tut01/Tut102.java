package com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut01;

import java.util.Map;

import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.core.canvas.CaatjaImage;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;

public class Tut102 {
    
    public void start(CaatjaCanvas canvas, Map<String, CaatjaImage> images) throws Exception {
        Director director = new Director().initialize(440, 230, canvas);
        director.imagesCache = images;
        Scene scene = director.createScene();
        
        CaatjaImage img= director.getImage("b");

        scene.addChild(
                new Actor().
                        setBounds((director.width-img.getWidth())/2, 10, img.getWidth(), img.getHeight()).
                        setFillStyle("blue")
        );
        scene.addChild(
                new Actor().
                        setBackgroundImage((img), true ).
                        setLocation((director.width-img.getWidth())/2, 10).
                        setFillStyle("blue")
        );


        // TODO

//        Canvas img2= ImageUtil.createThumb(
//                (img), img.getWidth()/4, img.getHeight()/4, false );
//        scene.addChild(
//                new Actor().
//                        setBounds(50, 10+10+img.getHeight(), img2.width, img2.height).
//                        setFillStyle("blue")
//        );
//        scene.addChild(
//                new Actor().
//                        setBackgroundImage( img2, true ).
//                        setLocation(50, 10+img.getHeight()+10 ).
//                        setFillStyle("blue")
//        );
//
//
//
//        Canvas img3= ImageUtil.createThumb(
//                img, 64, 64, false );
//        scene.addChild(
//                new Actor().
//                        setBounds(220, 10+10+img.getHeight(), img3.width, img3.getHeight()).
//                        setFillStyle("blue")
//        );
//        scene.addChild(
//                new Actor().
//                        setBackgroundImage( img3, true ).
//                        setLocation(220, 10+img.getHeight()+10 ).
//                        setFillStyle("blue")
//        );
//
//
//
//        Canvas img4= ImageUtil.createThumb(
//                (img), 64, 64, true );
//        scene.addChild(
//                new Actor().
//                        setBounds(300, 10+10+img.getHeight(), img4.width, img4.height).
//                        setFillStyle("blue")
//        );
//        scene.addChild(
//                new Actor().
//                        setBackgroundImage( img4, true ).
//                        setLocation(300, 10+img.getHeight()+10 ).
//                        setFillStyle("blue")
//        );

        Caatja.loop(1);
    }

}
