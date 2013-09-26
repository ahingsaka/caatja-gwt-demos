package com.katspow.caatjagwtdemos.client.welcome.demos;

import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;

public class Demo12 {
    
    public void start(CaatjaCanvas canvas) throws Exception {
        final Director director = new Director().initialize(800, 500, canvas);
        final Scene slide = director.createScene();
    }
    

}
