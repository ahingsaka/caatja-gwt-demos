package com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut07;

import java.util.Map;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.katspow.caatja.behavior.AlphaBehavior;
import com.katspow.caatja.behavior.Interpolator;
import com.katspow.caatja.behavior.PathBehavior;
import com.katspow.caatja.behavior.RotateBehavior;
import com.katspow.caatja.behavior.ScaleBehavior;
import com.katspow.caatja.core.CAAT;
import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.event.CAATMouseEvent;
import com.katspow.caatja.event.MouseListener;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.ui.Dock;
import com.katspow.caatja.foundation.ui.InterpolatorActor;
import com.katspow.caatja.foundation.ui.PathActor;
import com.katspow.caatja.foundation.ui.ShapeActor;
import com.katspow.caatja.foundation.ui.TextActor;
import com.katspow.caatja.foundation.ui.TextFont;
import com.katspow.caatja.pathutil.Path;

public class Tut71 {
    
    InterpolatorActor selectedInterpolatorActor= null;
    Label label = new Label();
    
    public void start(CaatjaCanvas canvas) throws Exception {
        
        RootPanel.get().add(label);
        
        Director director = new Director().initialize(800, 600, canvas);
        Scene scene = director.createScene().setFillStyle("#fff");
        
        Map<String, Interpolator> lerps = Interpolator.enumerateInterpolators();

        int cols= 21;
        int j=0, i=0;
        int rows= lerps.size()/cols;
        int min= 25;
        int max= 80;

        // generate interpolator actors.
        for( j=0; j<rows; j++ ) {

            Dock root= new Dock().
                    setBounds(0,director.canvas.getCoordinateSpaceHeight()-(j+1)*max, director.canvas.getCoordinateSpaceWidth(), max).
                    setSizes(min, max).
                    setLayoutOp( Dock.OpLayout.BOTTOM );
            root.scene= scene;

            scene.addChild(root);

            for( i=0; i<cols; i++ ) {

                if ( j*cols+i>=lerps.size() ) {
                    break;
                }

                InterpolatorActor actor= new InterpolatorActor() {
                    @Override
                    public void mouseClick(CAATMouseEvent mouseEvent) throws Exception {
                        if (null != selectedInterpolatorActor) {
                            selectedInterpolatorActor.setFillStyle(null);
                        }
                        selectedInterpolatorActor = (InterpolatorActor) mouseEvent.source;
                        mouseEvent.source.setFillStyle("#373");
                        selectedInterpolatorActor = (InterpolatorActor) mouseEvent.source;
                    }

                    // TODO Remove
//                    @Override
//                    public void mouseEnter(CAATMouseEvent mouseEvent) {
//                        if (mouseEvent.source != selectedInterpolatorActor) {
//                            mouseEvent.source.setFillStyle("#bbb");
//                        }
//                    }

                    // TODO Remove
//                    @Override
//                    public void mouseExit(CAATMouseEvent mouseEvent) {
//                        if ( mouseEvent.source!=selectedInterpolatorActor ) {
//                          mouseEvent.source.setFillStyle(null);
//                      }
//                    }

                    // TODO Remove
//                    @Override
//                    public void mouseMove(CAATMouseEvent mouseEvent) throws Exception {
//                        label.setText(mouseEvent.source.name);
//                    }
                    
                    
                    
                }.
                     setInterpolator((Interpolator) lerps.values().toArray()[(j * cols + i)]).
                     setBounds( 0, 0, min, min ).
                     setFillStyle("#fff").
                     setStringStrokeStyle( "#000" );
                
                
                if ( i==0 && j == 0 ) {
                    actor.setFillStyle("#373");
                    selectedInterpolatorActor= actor;
                }
                
                actor.setMouseExitListener(new MouseListener() {
                    public void call(CAATMouseEvent e) throws Exception {
                        if (e.source != selectedInterpolatorActor) {
                            e.source.setFillStyle(null);
                        }
                    }
                });
                
                actor.setMouseEnterListener(new MouseListener() {
                    public void call(CAATMouseEvent e) throws Exception {
                        if (e.source != selectedInterpolatorActor) {
                            e.source.setFillStyle("#bbb");
                        }
                    }
                });
                
                actor.setMouseMoveListener(new MouseListener() {
                    public void call(CAATMouseEvent e) throws Exception {
                        label.setText(e.source.name);
                    }
                });
                
                actor.name= (String) lerps.keySet().toArray()[i];

                root.addChild( actor );
            }

            root.layout();
        }

        // generate actors to apply interpolators to
        for( i=0; i<3; i++ ) {
            TextActor _text_r0= new TextActor() {

                // TODO Remove
//                @Override
//                public void mouseClick(CAATMouseEvent mouseEvent) throws Exception {
//                    if (null != selectedInterpolatorActor) {
//                        mouseEvent.source.behaviorList.get(0).setInterpolator(
//                                selectedInterpolatorActor.getInterpolator());
//                    }
//                }

                // TODO Remove
//                @Override
//                public void mouseEnter(CAATMouseEvent mouseEvent) {
//                    Caatja.setCursor("pointer");
//                }

                // TODO Remove
//                @Override
//                public void mouseExit(CAATMouseEvent mouseEvent) {
//                	Caatja.setCursor("default");
//                }
                
            }.
                    setFont(new TextFont(50, "px", "sans-serif")).
                    setText("CAAT").
                    setFillStyle("yellow").
                    setOutline(true).
                    setOutlineColor("gray").
                    setLocation( 30,60+140*i ).
                    calcTextSize(director);
            
            _text_r0.setMouseEnterListener(new MouseListener() {
                public void call(CAATMouseEvent e) throws Exception {
                    Caatja.setCursor("pointer");
                }
            });
            
            _text_r0.setMouseExitListener(new MouseListener() {
                public void call(CAATMouseEvent e) throws Exception {
                    Caatja.setCursor("default");
                }
            });
            
            _text_r0.setMouseClickListener(new MouseListener() {
                public void call(CAATMouseEvent e) throws Exception {
                    if (null != selectedInterpolatorActor) {
                        e.source.behaviorList.get(0).setInterpolator(
                                selectedInterpolatorActor.getInterpolator());
                    }
                }
            });
            
            _text_r0.cacheAsBitmap();
            
            
            
            RotateBehavior text_r0_rb= new RotateBehavior().
                    setFrameTime(0,5000).
                    setAngles(0,Math.PI*2).
                    setCycle(true);
            _text_r0.addBehavior( text_r0_rb );
            scene.addChild( _text_r0 );
        }

        for( i=0; i<3; i++ ) {
            TextActor _text_r0= new TextActor() {
                
                // TODO Remove
//                @Override
//                public void mouseClick(CAATMouseEvent mouseEvent) throws Exception {
//                    if (null != selectedInterpolatorActor) {
//                        mouseEvent.source.behaviorList.get(0).setInterpolator(
//                                selectedInterpolatorActor.getInterpolator());
//                    }
//                }

                // TODO Remove
//                @Override
//                public void mouseEnter(CAATMouseEvent mouseEvent) {
//                	Caatja.setCursor("pointer");
//                }

                // TODO Remove
//                @Override
//                public void mouseExit(CAATMouseEvent mouseEvent) {
//                	Caatja.setCursor("default");
//                }
                
            }.
                    setFont(new TextFont(50, "px", "sans-serif")).
                    setText("CAAT").
                    setFillStyle("blue").
                    setOutline(true).
                    setOutlineColor("gray").
                    setLocation( 250,60+140*i ).
                    calcTextSize(director);
            
            _text_r0.setMouseExitListener(new MouseListener() {
                public void call(CAATMouseEvent e) throws Exception {
                    Caatja.setCursor("default");
                }
            });
            
            _text_r0.setMouseClickListener(new MouseListener() {
                public void call(CAATMouseEvent e) throws Exception {
                    if (null != selectedInterpolatorActor) {
                        e.source.behaviorList.get(0).setInterpolator(
                                selectedInterpolatorActor.getInterpolator());
                    }
                }
            });
            
            _text_r0.setMouseEnterListener(new MouseListener() {
                public void call(CAATMouseEvent e) throws Exception {
                    Caatja.setCursor("pointer");
                }
            });
            
            _text_r0.cacheAsBitmap();
            
            
            ScaleBehavior text_r0_rb= new ScaleBehavior().
                    setFrameTime(0,5000).
                    setValues(1, 2, 1, 2).
                    setCycle(true);
            _text_r0.addBehavior( text_r0_rb );

            scene.addChild( _text_r0 );
        }

        for( i=0; i<3; i++ ) {
            ShapeActor _text_r0= new ShapeActor() {
                
                // TODO Remove
//                @Override
//                public void mouseClick(CAATMouseEvent mouseEvent) throws Exception {
//                    if (null != selectedInterpolatorActor) {
//                        mouseEvent.source.behaviorList.get(0).setInterpolator(
//                                selectedInterpolatorActor.getInterpolator());
//                    }
//                }

                // TODO Remove
//                @Override
//                public void mouseEnter(CAATMouseEvent mouseEvent) {
//                	Caatja.setCursor("pointer");
//                }

                // TODO Remove
//                @Override
//                public void mouseExit(CAATMouseEvent mouseEvent) {
//                	Caatja.setCursor("default");
//                }
            }.
                    setShape( ShapeActor.Shape.CIRCLE ).
                    setFillStyle("red").
                    setStringStrokeStyle("orange").
                    setBounds( 470,60+130*i, 80, 80 );
            
            _text_r0.setMouseEnterListener(new MouseListener() {
                public void call(CAATMouseEvent e) throws Exception {
                    Caatja.setCursor("pointer");
                }
            });
            
            _text_r0.setMouseExitListener(new MouseListener() {
                public void call(CAATMouseEvent e) throws Exception {
                    Caatja.setCursor("default");
                }
            });
            
            _text_r0.setMouseClickListener(new MouseListener() {
                public void call(CAATMouseEvent e) throws Exception {
                    if (null != selectedInterpolatorActor) {
                        e.source.behaviorList.get(0).setInterpolator(
                                selectedInterpolatorActor.getInterpolator());
                    }
                }
            });
            
            
            AlphaBehavior text_r0_rb= new AlphaBehavior().
                    setFrameTime(0,5000).
                    setValues(0, 1).
                    setCycle(true);
            _text_r0.addBehavior( text_r0_rb );

            scene.addChild( _text_r0 );
        }

        for( i=0; i<3; i++ ) {

            ShapeActor shape= new ShapeActor() {
                
                // TODO Remove
//                @Override
//                public void mouseClick(CAATMouseEvent mouseEvent) throws Exception {
//                    if (null != selectedInterpolatorActor) {
//                        mouseEvent.source.behaviorList.get(0).setInterpolator(
//                                selectedInterpolatorActor.getInterpolator());
//                    }
//                }

                // TODO Remove
//                @Override
//                public void mouseEnter(CAATMouseEvent mouseEvent) {
//                	Caatja.setCursor("pointer");
//                }

                // TODO Remove
//                @Override
//                public void mouseExit(CAATMouseEvent mouseEvent) {
//                	Caatja.setCursor("default");
//                }
            }.
                    setShape(ShapeActor.Shape.CIRCLE ).
                    setFillStyle("yellow").
                    setStringStrokeStyle("blue").
                    setBounds(0,0,40,40);
            
            shape.setMouseExitListener(new MouseListener() {
                public void call(CAATMouseEvent e) throws Exception {
                    Caatja.setCursor("default");
                }
            });
            
            shape.setMouseEnterListener(new MouseListener() {
                public void call(CAATMouseEvent e) throws Exception {
                    Caatja.setCursor("pointer");
                }
            });
            
            shape.setMouseClickListener(new MouseListener() {
                public void call(CAATMouseEvent e) throws Exception {
                    if (null != selectedInterpolatorActor) {
                        e.source.behaviorList.get(0).setInterpolator(
                                selectedInterpolatorActor.getInterpolator());
                    }
                }
            });
            

            Path path= new Path().
                    setInteractive(false).
                    setLinear(590+ i*70, 90, 590+ i*70, 370);

            PathActor pa= new PathActor().
                    setPath(path);
            PathBehavior pb= new PathBehavior().
                    setValues( path ).
                    setFrameTime( 0,10000 ).
                    setTranslation(shape.width/2, shape.height/2 ).
                    setCycle( true );

            shape.addBehavior(pb);

            scene.addChild( pa );
            scene.addChild( shape );
        }


        Caatja.loop(60);
        
    }

}
