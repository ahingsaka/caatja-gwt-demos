package com.katspow.caatjagwtdemos.client.welcome;

import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;

public class WelcomeState {
	
	public void start() throws Exception {
		final CaatjaCanvas canvas = Caatja.createCanvas();
		
		final WelcomeScene welcomeScene = new WelcomeScene();
		final Director director = new Director();
		
        director.initialize(800, 480,canvas);
		director.addScene(welcomeScene);
		director.setScene(0);
        
		welcomeScene.load(director);
		
        Caatja.addCanvas(canvas);
        Caatja.loop(60);
	}

}