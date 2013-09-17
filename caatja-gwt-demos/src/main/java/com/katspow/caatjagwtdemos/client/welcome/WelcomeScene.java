package com.katspow.caatjagwtdemos.client.welcome;

import com.katspow.caatja.core.canvas.CaatjaFillStrokeStyle;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.ActorContainer;

public class WelcomeScene extends Scene {

	public void load(Director director) throws Exception {

		ActorContainer root = new ActorContainer();
		root.setBounds(0, 0, director.canvas.getCoordinateSpaceWidth(),
				director.canvas.getCoordinateSpaceHeight());
		root.setFillStrokeStyle(new CaatjaFillStrokeStyle("#000000"));
		
		addChild(root);

	}

}
