package com.katspow.caatjagwtdemos.client.tutorials.simple;

import java.util.Map;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.katspow.caatja.behavior.AlphaBehavior;
import com.katspow.caatja.behavior.Interpolator;
import com.katspow.caatja.behavior.RotateBehavior;
import com.katspow.caatja.behavior.ScaleBehavior;
import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.event.CAATMouseEvent;
import com.katspow.caatja.event.MouseListener;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.ui.Dock;
import com.katspow.caatja.foundation.ui.InterpolatorActor;
import com.katspow.caatja.foundation.ui.ShapeActor;
import com.katspow.caatja.foundation.ui.TextActor;

/**
 * FIXME strokeStyle null ????
 *
 */
public class Tut041 {
    
    InterpolatorActor selectedInterpolatorActor = null;

    public void init() throws Exception {
        
        final HTML html = new HTML();
        RootPanel.get().add(html);

        Director director = new Director().initialize(680, 600, null);

        Scene scene = director.createScene();

//        Object[] lerps = new Object[] {
//                new Interpolator().createLinearInterpolator(false, false), "Linear pingpong=false, inverse=false",
//                new Interpolator().createLinearInterpolator(true,  false), "Linear pingpong=true, inverse=false",
//
//                new Interpolator().createLinearInterpolator(false, true), "Linear pingpong=false, inverse=true",
//                new Interpolator().createLinearInterpolator(true,  true), "Linear pingpong=true, inverse=true",
//
//                    new Interpolator().createExponentialInInterpolator(    2, false), "ExponentialIn pingpong=false, exponent=2",
//                    new Interpolator().createExponentialOutInterpolator(   2, false), "ExponentialOut pingpong=false, exponent=2",
//                    new Interpolator().createExponentialInOutInterpolator( 2, false), "ExponentialInOut pingpong=false, exponent=2",
//                    new Interpolator().createExponentialInInterpolator(    2, true), "ExponentialIn pingpong=true, exponent=2",
//                    new Interpolator().createExponentialOutInterpolator(   2, true), "ExponentialOut pingpong=true, exponent=2",
//                    new Interpolator().createExponentialInOutInterpolator( 2, true), "ExponentialInOut pingpong=true, exponent=2",
//
//                    new Interpolator().createExponentialInInterpolator(    4, false), "ExponentialIn pingpong=false, exponent=4",
//                    new Interpolator().createExponentialOutInterpolator(   4, false), "ExponentialOut pingpong=false, exponent=4",
//                    new Interpolator().createExponentialInOutInterpolator( 4, false), "ExponentialInOut pingpong=false, exponent=4",
//                    new Interpolator().createExponentialInInterpolator(    4, true), "ExponentialIn pingpong=true, exponent=4",
//                    new Interpolator().createExponentialOutInterpolator(   4, true), "ExponentialOut pingpong=true, exponent=4",
//                    new Interpolator().createExponentialInOutInterpolator( 4, true), "ExponentialInOut pingpong=true, exponent=4",
//
//                    new Interpolator().createExponentialInInterpolator(    6, false), "ExponentialIn pingpong=false, exponent=6",
//                    new Interpolator().createExponentialOutInterpolator(   6, false), "ExponentialOut pingpong=false, exponent=6",
//                    new Interpolator().createExponentialInOutInterpolator( 6, false), "ExponentialInOut pingpong=false, exponent=6",
//                    new Interpolator().createExponentialInInterpolator(    6, true), "ExponentialIn pingpong=true, exponent=6",
//                    new Interpolator().createExponentialOutInterpolator(   6, true), "ExponentialOut pingpong=true, exponent=6",
//                    new Interpolator().createExponentialInOutInterpolator( 6, true), "ExponentialInOut pingpong=true, exponent=6",
//
//                new Interpolator().createBounceInInterpolator(false), "BounceIn pingpong=false",
//                new Interpolator().createBounceOutInterpolator(false), "BounceOut pingpong=false",
//                new Interpolator().createBounceInOutInterpolator(false), "BounceInOut pingpong=false",
//                new Interpolator().createBounceInInterpolator(true), "BounceIn pingpong=true",
//                new Interpolator().createBounceOutInterpolator(true), "BounceOut pingpong=true",
//                new Interpolator().createBounceInOutInterpolator(true), "BounceInOut pingpong=true",
//
//                    new Interpolator().createElasticInInterpolator(    1.1, .4, false), "ElasticIn pingpong=false, amp=1.1, d=.4",
//                    new Interpolator().createElasticOutInterpolator(   1.1, .4, false), "ElasticOut pingpong=false, amp=1.1, d=.4",
//                    new Interpolator().createElasticInOutInterpolator( 1.1, .4, false), "ElasticInOut pingpong=false, amp=1.1, d=.4",
//                    new Interpolator().createElasticInInterpolator(    1.1, .4, true), "ElasticIn pingpong=true, amp=1.1, d=.4",
//                    new Interpolator().createElasticOutInterpolator(   1.1, .4, true), "ElasticOut pingpong=true, amp=1.1, d=.4",
//                    new Interpolator().createElasticInOutInterpolator( 1.1, .4, true), "ElasticInOut pingpong=true, amp=1.1, d=.4",
//
//                    new Interpolator().createElasticInInterpolator(    1.0, .2, false), "ElasticIn pingpong=false, amp=1.0, d=.2",
//                    new Interpolator().createElasticOutInterpolator(   1.0, .2, false), "ElasticOut pingpong=false, amp=1.0, d=.2",
//                    new Interpolator().createElasticInOutInterpolator( 1.0, .2, false), "ElasticInOut pingpong=false, amp=1.0, d=.2",
//                    new Interpolator().createElasticInInterpolator(    1.0, .2, true), "ElasticIn pingpong=true, amp=1.0, d=.2",
//                    new Interpolator().createElasticOutInterpolator(   1.0, .2, true), "ElasticOut pingpong=true, amp=1.0, d=.2",
//                    new Interpolator().createElasticInOutInterpolator( 1.0, .2, true), "ElasticInOut pingpong=true, amp=1.0, d=.2",
//        };
        
        Map<String, Interpolator> lerps = Interpolator.enumerateInterpolators();

        int cols = 21;
        int j = 0, i = 0;
        int rows = lerps.size() / cols;
        int min = 25;
        int max = 80;

        // generate interpolator actors.
        for (j = 0; j < rows; j++) {

            Dock root = (Dock) new Dock().setBounds(0,
                    director.canvas.getCoordinateSpaceHeight() - (j + 1) * max,
                    director.canvas.getCoordinateSpaceWidth(), max);
            root.setSizes(min, max);
            root.setLayoutOp(Dock.OpLayout.BOTTOM);
            root.scene = scene;

            scene.addChildImmediately(root);

            for (i = 0; i < cols; i++) {

                if (j * cols + i >= lerps.size()) {
                    break;
                }

                InterpolatorActor actor = (InterpolatorActor) new InterpolatorActor() {
                    
                    // TODO Remove
//                    @Override
//                    public void mouseMove(CAATMouseEvent mouseEvent) {
//                        
//                        html.setHTML("<code>" + mouseEvent.source.name
//                                + "</code>");
//
//                        ((Dock) mouseEvent.source.parent).actorPointed(mouseEvent.point.x, mouseEvent.point.y, mouseEvent.source);
//                    }

                    // TODO Remove
//                    @Override
//                    public void mouseExit(CAATMouseEvent mouseEvent) {
//                        if (mouseEvent.source != selectedInterpolatorActor) {
//                            mouseEvent.source.setFillStrokeStyle(null);
//                        }
//                        
//                        ((Dock) mouseEvent.source.parent).actorMouseExit(mouseEvent);
//                    }

                    // TODO Remove
//                    @Override
//                    public void mouseEnter(CAATMouseEvent mouseEvent) {
//                        
//                        ((Dock) mouseEvent.source.parent).actorMouseEnter(mouseEvent);
//                        
//                        if (mouseEvent.source != selectedInterpolatorActor) {
//                            mouseEvent.source.setFillStyle("#f0f0f0");
//                        }
//                    }

                    // TODO Remove
//                    @Override
//                    public void mouseClick(CAATMouseEvent mouseEvent) {
//                        if (null != selectedInterpolatorActor) {
//                            selectedInterpolatorActor.setFillStrokeStyle(null);
//                        }
//                        selectedInterpolatorActor = (InterpolatorActor) mouseEvent.source;
//                        mouseEvent.source.setFillStyle("#00ff00");
//                        selectedInterpolatorActor = (InterpolatorActor) mouseEvent.source;
//                    }
                }.setInterpolator((Interpolator) lerps.values().toArray()[(j * cols + i)], null).setBounds(0, 0, min, min);
                
                actor.setMouseClickListener(new MouseListener() {
                    public void call(CAATMouseEvent e) throws Exception {
                        if (null != selectedInterpolatorActor) {
                            selectedInterpolatorActor.setFillStrokeStyle(null);
                        }
                        selectedInterpolatorActor = (InterpolatorActor) e.source;
                        e.source.setFillStyle("#00ff00");
                        selectedInterpolatorActor = (InterpolatorActor) e.source;
                    }
                });
                
                actor.setMouseMoveListener(new MouseListener() {
                    public void call(CAATMouseEvent e) throws Exception {
                        html.setHTML("<code>" + e.source.name
                                + "</code>");

                        ((Dock) e.source.parent).actorPointed(e.point.x, e.point.y, e.source);
                    }
                });
                
                
                actor.setMouseEnterListener(new MouseListener() {
                    public void call(CAATMouseEvent e) throws Exception {
                        ((Dock) e.source.parent).actorMouseEnter(e);
                        
                        if (e.source != selectedInterpolatorActor) {
                            e.source.setFillStyle("#f0f0f0");
                        }
                    }
                });
                
                actor.setMouseExitListener(new MouseListener() {
                    public void call(CAATMouseEvent e) throws Exception {
                        if (e.source != selectedInterpolatorActor) {
                            e.source.setFillStrokeStyle(null);
                        }
                        
                        ((Dock) e.source.parent).actorMouseExit(e);
                    }
                });
                
                actor.name = (String) lerps.keySet().toArray()[i];

                root.addChildImmediately(actor);
            }

            root.layout();
        }

        // generate actors to apply interpolators to
        for (i = 0; i < 3; i++) {
            TextActor _text_r0 = new TextActor() {
                @Override
                public void mouseClick(CAATMouseEvent mouseEvent) {
                    if (null != selectedInterpolatorActor) {
                        mouseEvent.source.behaviorList.get(0).setInterpolator(
                                selectedInterpolatorActor.getInterpolator());
                    }
                }
            }.setFont("50px sans-serif").setText("CAAT").setFillStyle("green").setOutline(true)
                    .setOutlineColor("red").setLocation(30, 60 + 140 * i);

            RotateBehavior text_r0_rb = (RotateBehavior) new RotateBehavior().setFrameTime(0, 5000);
            text_r0_rb.setAngles(0, Math.PI * 2).setCycle(true);

            _text_r0.addBehavior(text_r0_rb);

            scene.addChild(_text_r0);
        }

        for (i = 0; i < 3; i++) {
            TextActor _text_r0 = new TextActor() {
                @Override
                public void mouseClick(CAATMouseEvent mouseEvent) {
                    if (null != selectedInterpolatorActor) {
                        mouseEvent.source.behaviorList.get(0).setInterpolator(
                                selectedInterpolatorActor.getInterpolator());
                    }
                }
            }.setFont("50px sans-serif").setText("CAAT").setFillStyle("green").setOutline(true)
                    .setOutlineColor("red").setLocation(250, 60 + 140 * i);
            ScaleBehavior text_r0_rb = (ScaleBehavior) new ScaleBehavior().setFrameTime(0, 5000);
            text_r0_rb.setValues(1, 2, 1, 2).setCycle(true);
            _text_r0.addBehavior(text_r0_rb);

            scene.addChild(_text_r0);
        }

        for (i = 0; i < 3; i++) {
            ShapeActor _text_r0 = (ShapeActor) new ShapeActor() {
                @Override
                public void mouseClick(CAATMouseEvent mouseEvent) {
                    if (null != selectedInterpolatorActor) {
                        mouseEvent.source.behaviorList.get(0).setInterpolator(
                                selectedInterpolatorActor.getInterpolator());
                    }
                }
            }.setShape(ShapeActor.Shape.CIRCLE).setFillStyle("pink").setStringStrokeStyle("orange")
                    .setBounds(500, 60 + 130 * i, 80, 80);

            AlphaBehavior text_r0_rb = (AlphaBehavior) new AlphaBehavior().setFrameTime(0, 5000);

            text_r0_rb.setValues(0, 1).setCycle(true);
            _text_r0.addBehavior(text_r0_rb);

            scene.addChild(_text_r0);
        }

        Caatja.loop(60);

    }

}
