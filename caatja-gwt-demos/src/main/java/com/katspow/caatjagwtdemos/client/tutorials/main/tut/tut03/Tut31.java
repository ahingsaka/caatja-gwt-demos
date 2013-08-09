package com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut03;

import com.katspow.caatja.behavior.RotateBehavior;
import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.ActorContainer;
import com.katspow.caatja.foundation.ui.ShapeActor;
import com.katspow.caatja.modules.colorutil.Color;

public class Tut31 {
    
    ActorContainer segment;
    
    private void __createTentacle(final ShapeActor root, double angle, final int segments, final double armSegmentSizeW, final double armSegmentSizeH, final int armIndex, final double maxAngle) throws Exception {

        int i;
        segment = root;

        for (i = 0; i< segments; i++) {

            // calculate a color which lies somewhere between the rgb colors
            // (255,255,0) yellow and (255,128,0) orange.
            int[] color = Color.interpolate(
                    255, 255, 0,
                    255, 128, 0,
                    segments,
                    i
            );

            // create a tentacle"s segment
            Tut31ActorContainer newSegment = (Tut31ActorContainer) new Tut31ActorContainer() {
                @Override
                public boolean animate(Director director, double time) throws Exception {
                    if (segment != root) {
                        this.setRotationAnchored(
                              this.oldAngle +
                                      maxAngle * Math.sin(Caatja.getTime() * .0005 + armIndex * Math.PI / segments / 2
                                      ),
                              .5,
                              1d
                      );
                    }
                    return super.animate(director, time);
                }
            }.
                    setSize(armSegmentSizeH - 4, armSegmentSizeH - 4).
                    setFillStyle( "rgb("+color[0]+","+color[1]+","+color[2]+")" ).
                    setLocation(0, -armSegmentSizeH);

            // and link it to the previos tentacle segment or root element
            if (segment == root) {
                newSegment.setRotationAnchored(angle, .5, 1d);
                newSegment.oldAngle = angle;
            }

            segment.addChild(newSegment);
            
            // TODO Check ?
            segment = newSegment;
        }
    }
    
    public void start(CaatjaCanvas canvas) throws Exception {
        
        Director _director_9 = new Director().initialize(200, 200, canvas);
        Scene _scene_9 = _director_9.createScene();
        
        _director_9.addScene(_scene_9);

        // make this actor each tentacle"s parent.
        // rotating this actor will make every tentacle to rotate around it.
        ShapeActor root = new ShapeActor().
                setShape(ShapeActor.Shape.CIRCLE).
                setBounds(_director_9.width / 2, _director_9.height / 2, 1, 1).
                setFillStyle("yellow").
                addBehavior(
                new RotateBehavior().
                        setFrameTime(0, 20000).
                        setValues(0, 2 * Math.PI).
                        setCycle(true)
        );

        // create 10 tentacles
        int arms = 10;
        // with 10 segments each
        int segments = 10;

        int i;

        for (i = 0; i < arms; i++) {
            __createTentacle(root, 2 * Math.PI / arms * i, segments, 2, 10, i, Math.PI / segments / 2);
        }

        _scene_9.addChild(root);
        Caatja.loop(15);
        
    }

}
