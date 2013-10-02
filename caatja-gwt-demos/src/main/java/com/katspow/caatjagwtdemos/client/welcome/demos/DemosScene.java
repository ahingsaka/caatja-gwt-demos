package com.katspow.caatjagwtdemos.client.welcome.demos;

import com.katspow.caatja.core.canvas.CaatjaColor;
import com.katspow.caatja.core.canvas.CaatjaImage;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.ActorContainer;

public class DemosScene extends Scene {
    
    private ActorContainer root;
    
    private CaatjaImage image;

    public DemosScene(Director director) throws Exception {
        
        this.root = new ActorContainer() {
            @Override
            public void paint(Director director, double time) {
                if (image != null) {
                    director.ctx.drawImage(image, 0d, 0d, this.width, this.height );
                }
            }
        };
        
        root.setBounds(0, 0, director.canvas.getCoordinateSpaceWidth(), director.canvas.getCoordinateSpaceHeight());
        root.setFillStrokeStyle(CaatjaColor.valueOf("#000000"));

        addChild(root);
        
    }
    
    public void setBackgroundImage(CaatjaImage img) {
        image = img;
    }

}
