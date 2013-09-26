package com.katspow.caatjagwtdemos.client.welcome.demos;

import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatjagwtdemos.client.welcome.hypernumber.startmenu.Garden;

public class Demo10 {

    public void start(CaatjaCanvas canvas) throws Exception {
        final Director director = new Director().initialize(800, 500, canvas);
        final Scene scene = director.createScene();

        Garden garden = new Garden();
        garden.setBounds(0, 0, director.width, director.height);
        garden.initialize(director.ctx, 200, (int) (director.height * .7));
        scene.addChild(garden);

        Caatja.loop(30);
    }

}
