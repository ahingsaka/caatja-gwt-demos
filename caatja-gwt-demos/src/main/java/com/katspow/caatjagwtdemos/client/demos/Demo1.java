package com.katspow.caatjagwtdemos.client.demos;

import java.util.Map;

import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.core.canvas.CaatjaImage;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;

public class Demo1 {
    
    public void start(CaatjaCanvas canvas, Map<String, CaatjaImage> images) throws Exception {
        final Director director = new Director().initialize(800, 500, canvas);
        director.imagesCache = images;
            
        final Scene scene = director.createScene();
    }

}
