package com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut09;

import java.util.Map;

import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.core.canvas.CaatjaImage;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;
import com.katspow.caatja.foundation.actor.ActorCallback;
import com.katspow.caatja.foundation.actor.Button;
import com.katspow.caatja.foundation.image.CompoundImage;

public class Tut96 {

    public void start(CaatjaCanvas canvas, Map<String, CaatjaImage> images) throws Exception {
        Director director = new Director();
        director.initialize(700, 100, canvas);
        director.imagesCache = images;

        Scene scene = director.createScene();

        // an image of 7 rows by 3 columns
        CompoundImage ci = new CompoundImage().initialize((director.getImage("botones")), 7, 3);

        Button b1 = new Button().initialize(ci, 0, 1, 2, 0, new ActorCallback() {
            @Override
            public void call(Actor actor) {
                Caatja.alert("easy pressed");
            }
        }).setLocation(0, 30);

        Button b2 = new Button().initialize(ci, 6, 7, 8, 6, new ActorCallback() {
            @Override
            public void call(Actor actor) {
            	Caatja.alert("start pressed");
            }
        }).setLocation(1.5 * ci.singleWidth, 30);

        scene.addChild(b1);
        scene.addChild(b2);

        Caatja.loop(10);

    }
}
