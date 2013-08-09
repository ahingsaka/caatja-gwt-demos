package com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut07;

import com.katspow.caatja.behavior.Interpolator;
import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.ui.InterpolatorActor;

public class Tut7 {

    public void start(CaatjaCanvas canvas) throws Exception {

        Interpolator[] lerps = new Interpolator[] { new Interpolator().createLinearInterpolator(false, false),
                new Interpolator().createLinearInterpolator(true, false) };
        
        Interpolator[] lerps2 = new Interpolator[] { new Interpolator().createLinearInterpolator(false, true),
                new Interpolator().createLinearInterpolator(true, true) };
        
        Interpolator[][] lerps3 = new Interpolator[][] {
                { new Interpolator().createExponentialInInterpolator(2, false),
                        new Interpolator().createExponentialOutInterpolator(2, false),
                        new Interpolator().createExponentialInOutInterpolator(2, false),

                        new Interpolator().createExponentialInInterpolator(2, true),
                        new Interpolator().createExponentialOutInterpolator(2, true),
                        new Interpolator().createExponentialInOutInterpolator(2, true) },
                { new Interpolator().createExponentialInInterpolator(4, false),
                        new Interpolator().createExponentialOutInterpolator(4, false),
                        new Interpolator().createExponentialInOutInterpolator(4, false),

                        new Interpolator().createExponentialInInterpolator(4, true),
                        new Interpolator().createExponentialOutInterpolator(4, true),
                        new Interpolator().createExponentialInOutInterpolator(4, true) },
                { new Interpolator().createExponentialInInterpolator(6, false),
                        new Interpolator().createExponentialOutInterpolator(6, false),
                        new Interpolator().createExponentialInOutInterpolator(6, false),

                        new Interpolator().createExponentialInInterpolator(6, true),
                        new Interpolator().createExponentialOutInterpolator(6, true),
                        new Interpolator().createExponentialInOutInterpolator(6, true) } };
        
        Interpolator[][] lerps4 = new Interpolator[][] { { new Interpolator().createBounceInInterpolator(false),
                new Interpolator().createBounceOutInterpolator(false),
                new Interpolator().createBounceInOutInterpolator(false),

                new Interpolator().createBounceInInterpolator(true),
                new Interpolator().createBounceOutInterpolator(true),
                new Interpolator().createBounceInOutInterpolator(true) } };
        
        Interpolator[][] lerps5= new Interpolator[][] {
                {
                       new Interpolator().createElasticInInterpolator(    1.1, .4, false),
                       new Interpolator().createElasticOutInterpolator(   1.1, .4, false),
                       new Interpolator().createElasticInOutInterpolator( 1.1, .4, false),

                       new Interpolator().createElasticInInterpolator(    1.1, .4, true),
                       new Interpolator().createElasticOutInterpolator(   1.1, .4, true),
                       new Interpolator().createElasticInOutInterpolator( 1.1, .4, true)
                },
                {
                       new Interpolator().createElasticInInterpolator(    1.0, .2, false),
                       new Interpolator().createElasticOutInterpolator(   1.0, .2, false),
                       new Interpolator().createElasticInOutInterpolator( 1.0, .2, false),

                       new Interpolator().createElasticInInterpolator(    1.0, .2, true),
                       new Interpolator().createElasticOutInterpolator(   1.0, .2, true),
                       new Interpolator().createElasticInOutInterpolator( 1.0, .2, true)
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
