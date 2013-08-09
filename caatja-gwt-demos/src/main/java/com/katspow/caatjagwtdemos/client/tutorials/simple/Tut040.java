package com.katspow.caatjagwtdemos.client.tutorials.simple;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.katspow.caatja.behavior.Interpolator;
import com.katspow.caatja.core.CAAT;
import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.ui.InterpolatorActor;
import com.katspow.caatjagwt.client.CaatjaGwtCanvas;

/**
 * Some interpolator lines are displayed.
 * 
 * @author ahingsaka
 *
 */
public class Tut040 {
    
    public void init() throws Exception {
        
        VerticalPanel vp = new VerticalPanel();
        
        CaatjaCanvas canvas1 = Caatja.createCanvas();
        CaatjaCanvas canvas2 = Caatja.createCanvas();
        CaatjaCanvas canvas3 = Caatja.createCanvas();
        CaatjaCanvas canvas4 = Caatja.createCanvas();
        
        vp.add(((CaatjaGwtCanvas)canvas1).canvas);
        vp.add(((CaatjaGwtCanvas)canvas2).canvas);
        vp.add(((CaatjaGwtCanvas)canvas3).canvas);
        vp.add(((CaatjaGwtCanvas)canvas4).canvas);
        
        RootPanel.get().add(vp);
        
        
        Interpolator[] lerps= new Interpolator [] {
                    new Interpolator().createLinearInterpolator(false, false),
                    new Interpolator().createLinearInterpolator(true,  false)
        };

            int i;
            for( i=0; i<lerps.length; i++) {
                
                CaatjaCanvas selectedCanvas;
                
                if (i == 0) {
                    selectedCanvas = canvas1;
                } else {
                    selectedCanvas = canvas2;
                }
                
                Director _director_1= new Director().initialize(
                        100,
                        100,
                        selectedCanvas);

                Scene _scene_1= _director_1.createScene();

                _scene_1.addChild(
                        new InterpolatorActor().
                            setInterpolator( lerps[i], null).
                            setBounds( 10, 10, 80, 80 ).
                            setFillStyle("#d0d0d0") );

                Caatja.loop(30);
            }
            
            
            Interpolator[] lerps2 = new Interpolator [] {
                        new Interpolator().createLinearInterpolator(false, true),
                        new Interpolator().createLinearInterpolator(true,  true)
            };

                for( i=0; i<lerps2.length; i++) {
                    
                	CaatjaCanvas selectedCanvas;
                    
                    if (i == 0) {
                        selectedCanvas = canvas3;
                    } else {
                        selectedCanvas = canvas4;
                    }
                    
                    Director _director_1= new Director().initialize(
                            100,
                            100,
                            selectedCanvas);

                    Scene _scene_1= new Scene();

                    _director_1.addScene( _scene_1 );

                    _scene_1.addChild(
                            new InterpolatorActor().
                                setInterpolator( lerps2[i], null ).
                                setBounds( 10, 10, 80, 80 ).
                                setFillStyle("#d0d0d0") );

                    Caatja.loop(30);
                }
         
        
//        Canvas canvas2 = Canvas.createIfSupported();
//        Canvas canvas3 = Canvas.createIfSupported();
//        Canvas canvas4 = Canvas.createIfSupported();
//        
//        vp.add(canvas1);
//        vp.add(canvas2);
//        vp.add(canvas3);
//        vp.add(canvas4);
//       
//        RootPanel.get().add(vp);
//        
//        Director _director_1= new Director().initialize(
//                500,
//                100,
//                canvas1);
//
//        Scene _scene_1= new Scene().create();
//
//        _director_1.addScene( _scene_1 );
//
//        List<Interpolator> lerps= Arrays.asList(
//                new Interpolator().createLinearInterpolator(false, false),
//                new Interpolator().createLinearInterpolator(true,  false),
//                new Interpolator().createLinearInterpolator(false, true),
//                new Interpolator().createLinearInterpolator(true,  true)
//        );
//
//        for(int i=0; i<lerps.size(); i++) {
//            _scene_1.addChild(
//                    new InterpolatorActor().
//                        create().
//                        setInterpolator( lerps.get(i), null ).
//                        setBounds( 10+i*90, 10, 80, 80 ).
//                        setFillStyle("#d0d0d0") );
//        }
//        
//        Director _director_2= new Director().initialize(
//                800,
//                300,
//                canvas2);
//
//        Scene _scene_2= new Scene().create();
//
//        _director_2.addScene( _scene_2 );
//        
//
//        Interpolator[][] lerps2 = new Interpolator[][] {
//                {
//                new Interpolator().createExponentialInInterpolator(    2, false),
//                new Interpolator().createExponentialOutInterpolator(   2, false),
//                new Interpolator().createExponentialInOutInterpolator( 2, false),
//
//                new Interpolator().createExponentialInInterpolator(    2, true),
//                new Interpolator().createExponentialOutInterpolator(   2, true),
//                new Interpolator().createExponentialInOutInterpolator( 2, true),
//                },
//            {
//                new Interpolator().createExponentialInInterpolator(    4, false),
//                new Interpolator().createExponentialOutInterpolator(   4, false),
//                new Interpolator().createExponentialInOutInterpolator( 4, false),
//
//                new Interpolator().createExponentialInInterpolator(    4, true),
//                new Interpolator().createExponentialOutInterpolator(   4, true),
//                new Interpolator().createExponentialInOutInterpolator( 4, true),
//            },
//            {
//                new Interpolator().createExponentialInInterpolator(    6, false),
//                new Interpolator().createExponentialOutInterpolator(   6, false),
//                new Interpolator().createExponentialInOutInterpolator( 6, false),
//
//                new Interpolator().createExponentialInInterpolator(    6, true),
//                new Interpolator().createExponentialOutInterpolator(   6, true),
//                new Interpolator().createExponentialInOutInterpolator( 6, true),
//            }
//        };
//
//        int i, j;
//        for( j=0; j<lerps2.length ; j++) {
//            for( i=0; i<lerps2[j].length; i++) {
//                _scene_2.addChild(
//                        new InterpolatorActor().
//                            create().
//                            setInterpolator( lerps2[j][i], null ).
//                            setBounds( (i>=3 ? 30 : 0) + 10+i*90, 10+90*j, 80, 80 ).
//                            setFillStyle("#d0d0d0") );
//            }
//        }
//        
//        Director _director_3 = new Director().initialize(
//                800,
//                100,
//                canvas3 );
//
//        Scene _scene_3 = new Scene().create();
//
//        _director_3.addScene( _scene_3 );
//
//        List<Interpolator> lerps3= Arrays.asList(
//                new Interpolator().createBounceInInterpolator(false),
//                new Interpolator().createBounceOutInterpolator(false),
//                new Interpolator().createBounceInOutInterpolator(false),
//
//                new Interpolator().createBounceInInterpolator(true),
//                new Interpolator().createBounceOutInterpolator(true),
//                new Interpolator().createBounceInOutInterpolator(true)
//        );
//
//        for( i=0; i<lerps3.size(); i++) {
//            _scene_3.addChild(
//                    new InterpolatorActor().
//                        create().
//                        setInterpolator( lerps3.get(i), null ).
//                        setBounds( (i>=3 ? 30 : 0) + 10+i*90, 10, 80, 80 ).
//                        setFillStyle("#d0d0d0") );
//        }
//        
//        Director _director_4= new Director().initialize(
//                800,
//                320,
//                canvas4 );
//
//        Scene _scene_4= new Scene().create();
//
//        _director_4.addScene( _scene_4 );
//
//        Interpolator[][] lerps4= new Interpolator[][] {
//                {new Interpolator().createElasticInInterpolator(1.1, .4, false),
//                new Interpolator().createElasticOutInterpolator(1.1, .4, false),
//                new Interpolator().createElasticInOutInterpolator(1.1, .4, false),
//
//            new Interpolator().createElasticInInterpolator(1.1, .4, true),
//            new Interpolator().createElasticOutInterpolator(1.1, .4, true),
//            new Interpolator().createElasticInOutInterpolator(1.1, .4, true)
//                }
//        ,
//        {
//            new Interpolator().createElasticInInterpolator(    1.0, .2, false),
//            new Interpolator().createElasticOutInterpolator(   1.0, .2, false),
//            new Interpolator().createElasticInOutInterpolator( 1.0, .2, false),
//
//            new Interpolator().createElasticInInterpolator(    1.0, .2, true),
//            new Interpolator().createElasticOutInterpolator(   1.0, .2, true),
//            new Interpolator().createElasticInOutInterpolator( 1.0, .2, true)
//        }
//        
//        };
//
//        for( j=0; j<lerps4.length; j++) {
//            for( i=0; i<lerps4[j].length; i++) {
//            _scene_4.addChild(
//                    new InterpolatorActor().
//                        create().
//                        setInterpolator( lerps4[j][i], null ).
//                        setBounds( (i>=3 ? 30 : 0) + 10+i*90, 30+130*j, 80, 80 ).
//                        setFillStyle("#d0d0d0") );
//            }
//        }
//
//        _director_4.loop(1);
//
//        _director_3.loop(1);
//
//        _director_2.loop(1);
//
//        _director_1.loop(1);
        
    }

}
