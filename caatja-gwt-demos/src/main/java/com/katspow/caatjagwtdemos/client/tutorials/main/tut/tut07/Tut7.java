package com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut07;

import com.katspow.caatja.behavior.Interpolator;
import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.ui.InterpolatorActor;

public class Tut7 {

    public void start(CaatjaCanvas canvas) throws Exception {

        Interpolator[] lerps = new Interpolator[] { Interpolator.createLinearInterpolator(false, false),
                Interpolator.createLinearInterpolator(true, false) };
        
        Interpolator[] lerps2 = new Interpolator[] { Interpolator.createLinearInterpolator(false, true),
                Interpolator.createLinearInterpolator(true, true) };
        
        Interpolator[][] lerps3 = new Interpolator[][] {
                { Interpolator.createExponentialInInterpolator(2, false),
                        Interpolator.createExponentialOutInterpolator(2, false),
                        Interpolator.createExponentialInOutInterpolator(2, false),

                        Interpolator.createExponentialInInterpolator(2, true),
                        Interpolator.createExponentialOutInterpolator(2, true),
                        Interpolator.createExponentialInOutInterpolator(2, true) },
                { Interpolator.createExponentialInInterpolator(4, false),
                        Interpolator.createExponentialOutInterpolator(4, false),
                        Interpolator.createExponentialInOutInterpolator(4, false),

                        Interpolator.createExponentialInInterpolator(4, true),
                        Interpolator.createExponentialOutInterpolator(4, true),
                        Interpolator.createExponentialInOutInterpolator(4, true) },
                { Interpolator.createExponentialInInterpolator(6, false),
                        Interpolator.createExponentialOutInterpolator(6, false),
                        Interpolator.createExponentialInOutInterpolator(6, false),

                        Interpolator.createExponentialInInterpolator(6, true),
                        Interpolator.createExponentialOutInterpolator(6, true),
                        Interpolator.createExponentialInOutInterpolator(6, true) } };
        
        Interpolator[][] lerps4 = new Interpolator[][] { { Interpolator.createBounceInInterpolator(false),
                Interpolator.createBounceOutInterpolator(false),
                Interpolator.createBounceInOutInterpolator(false),

                Interpolator.createBounceInInterpolator(true),
                Interpolator.createBounceOutInterpolator(true),
                Interpolator.createBounceInOutInterpolator(true) } };
        
        Interpolator[][] lerps5= new Interpolator[][] {
                {
                       Interpolator.createElasticInInterpolator(    1.1, .4, false),
                       Interpolator.createElasticOutInterpolator(   1.1, .4, false),
                       Interpolator.createElasticInOutInterpolator( 1.1, .4, false),

                       Interpolator.createElasticInInterpolator(    1.1, .4, true),
                       Interpolator.createElasticOutInterpolator(   1.1, .4, true),
                       Interpolator.createElasticInOutInterpolator( 1.1, .4, true)
                },
                {
                       Interpolator.createElasticInInterpolator(    1.0, .2, false),
                       Interpolator.createElasticOutInterpolator(   1.0, .2, false),
                       Interpolator.createElasticInOutInterpolator( 1.0, .2, false),

                       Interpolator.createElasticInInterpolator(    1.0, .2, true),
                       Interpolator.createElasticOutInterpolator(   1.0, .2, true),
                       Interpolator.createElasticInOutInterpolator( 1.0, .2, true)
                }
        };

        Director _director_1 = new Director().initialize(500, 700, canvas);
        Scene _scene_1 = _director_1.createScene();
        
        for (int i = 0; i < lerps.length; i++) {

            _scene_1.addChild(new InterpolatorActor().setInterpolator(lerps[i]).setBounds(i*70 + 20, 10, 80, 80)
                    .setFillStyle("#77f").setStringStrokeStyle("#fff"));

        }
        
        for (int i = 0; i < lerps2.length; i++) {
            _scene_1.addChild(
                    new InterpolatorActor().
                        setInterpolator( lerps2[i] ).
                        setBounds(i*70 + 20, 100, 80, 80 ).
                        setFillStyle("#77f").
                        setStringStrokeStyle( "#fff") );
        }
        
        int i, j;
        for( j=0; j<lerps3.length; j++) {
            for( i=0; i<lerps3[j].length; i++) {

                _scene_1.addChild(
                        new InterpolatorActor().
                            setInterpolator( lerps3[j][i] ).
                            setBounds(i * 70 + 20, j*80 + 200, 80, 80 ).
                            setFillStyle("#77f").
                            setStringStrokeStyle( "#fff" ) );

            }
        }
        
        for( j=0; j<lerps4.length; j++) {
            for( i=0; i<lerps4[j].length; i++) {

                _scene_1.addChild(
                        new InterpolatorActor().
                            setInterpolator( lerps4[j][i] ).
                            setBounds(i * 70 + 20, j*80 + 450, 80, 80 ).
                            setFillStyle("#77f").
                            setStringStrokeStyle( "#fff" ) );

            }
        }
        
        for( j=0; j<lerps5.length; j++) {
            for( i=0; i<lerps5[j].length; i++) {

                _scene_1.addChild(
                        new InterpolatorActor().
                            setInterpolator( lerps5[j][i] ).
                            setBounds(i * 70 + 20, j*80 + 550, 80, 80 ).
                            setFillStyle("#77f").
                            setStringStrokeStyle( "#fff" ) );

            }
        }

        Caatja.loop(30);
    }

}
