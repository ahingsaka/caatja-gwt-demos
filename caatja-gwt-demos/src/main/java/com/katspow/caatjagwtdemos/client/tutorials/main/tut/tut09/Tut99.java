package com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut09;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.core.canvas.CaatjaImage;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;
import com.katspow.caatja.foundation.image.SpriteImage;
import com.katspow.caatja.foundation.ui.Dock;

public class Tut99 {

    public void start(CaatjaCanvas canvas, Map<String, CaatjaImage> images) throws Exception {
        Director director = new Director();
        director.initialize(800, 500, canvas);
        director.imagesCache = images;
        __scene(director);
    }

    private void __scene(Director director) throws Exception {

        Scene scene = director.createScene();

        // min docked actor size.
        int min = 20;
        // max docked actor size.
        int max = 100;
        int width = 500;
        int height = 100;
        int insets = 50;

        // reuse a sprite image
        SpriteImage ic = new SpriteImage().initialize(director.getImage("nums"), 9, 9);

        int i, j;
        List<Dock> dock = new ArrayList<Dock>();
        // build 4 dock actors with the different layout ops available.
        dock.add(new Dock().initialize(scene).setBounds((director.width - width) / 2, insets, width, height)
                .setSizes(min, max).setApplicationRange(3).setLayoutOp(Dock.OpLayout.TOP));

        dock.add(new Dock().initialize(scene)
                .setBounds((director.width - width) / 2, director.height - height - insets, width, height)
                .setSizes(min, max).setApplicationRange(5).setLayoutOp(Dock.OpLayout.BOTTOM));
        
        dock.add(new Dock().initialize(scene).setBounds(insets, insets, height, 400).setSizes(min, max)
                .setApplicationRange(3).setLayoutOp(Dock.OpLayout.LEFT));
        
        dock.add(new Dock().initialize(scene).setBounds(director.width - height - insets, insets, height, 400)
                .setSizes(min, max).setApplicationRange(6).setLayoutOp(Dock.OpLayout.RIGHT));

        for (j = 0; j < dock.size(); j++) {
            // add 10 elements for each docking actor.
            for (i = 0; i < 10; i++) {

                // create an actor
                Actor img = new Actor().setBackgroundImage(ic.getRef(), true)
                        .setSpriteIndex((int) (Math.random() * ic.rows * ic.columns) >> 0).
                        // and make its image conform to all the available
                        // space.
                        setImageTransformation(SpriteImage.Tr.FIXED_TO_SIZE);

                dock.get(j).addChild(img);
            }

            dock.get(j).layout();
            scene.addChild(dock.get(j));
        }

        Caatja.loop(33);

    }

}
