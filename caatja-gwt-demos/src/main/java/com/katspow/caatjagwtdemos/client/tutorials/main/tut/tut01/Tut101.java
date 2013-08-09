package com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut01;

import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;

public class Tut101 {

    public void start(CaatjaCanvas canvas) throws Exception {

        Director director = new Director().initialize(400, 100, canvas);
        Scene scene = director.createScene();

        Caatja.loop(20);
    }

}
