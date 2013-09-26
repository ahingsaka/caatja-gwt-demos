package com.katspow.caatjagwtdemos.client.welcome.showcase.scenes;

import com.katspow.caatja.behavior.BaseBehavior;
import com.katspow.caatja.behavior.BehaviorListener;
import com.katspow.caatja.behavior.Interpolator;
import com.katspow.caatja.behavior.PathBehavior;
import com.katspow.caatja.behavior.RotateBehavior;
import com.katspow.caatja.behavior.SetForTimeReturnValue;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;
import com.katspow.caatja.foundation.actor.CSSActor;
import com.katspow.caatja.math.Pt;
import com.katspow.caatja.pathutil.Path;

/**
 * 
 * Scene 11-experimental.
 * Shows some css actors.
 *
 */
public class SceneExperimental {
    
    public static Scene init(Director director) throws Exception {
        
        int i, j;
        final Scene scene= new Scene();

        for( j=0; j<2; j++ ) {

            final int ww= 400;
            final int hh= 600;

            CSSActor container= new CSSActor().
                    create().
                    setBounds( j*ww, 0, ww, hh )/*.
                    addBehavior(
                        new RotateBehavior().
                                setFrameTime(0,10000).
                                setCycle(true).
                                setValues(0,Math.PI*2)
                    ).
                    addBehavior(
                        new ScaleBehavior().
                                setFrameTime(0,5000).
                                setCycle(true).
                                setPingPong().
                                setValues(.5,1, .5,1)
                    )*/;
            
            scene.addChild(container);

            for( i=0; i<49; i++ ) {
                double x,y;
                x= (i%7)*(container.width/5);
                y= ((i/7)>>0)*(container.height/5);
                CSSActor css= new CSSActor().
                        create().
                        setBounds( x, y, 50, 26 ).
                        setBackground("res/img/anim1.png").
                        addBehavior(
                            new RotateBehavior().
                                    setFrameTime(0,2000).
                                    setCycle(true).
                                    setValues(0,Math.PI*2)
                        ).
                        addBehavior(
                            new PathBehavior().
                                setAutoRotate(true).
                                setPath(
                                    new Path().setCubic(
                                        x,
                                        y,
                                        Math.random()*ww,
                                        Math.random()*hh,
                                        Math.random()*ww,
                                        Math.random()*hh,
                                        Math.random()*ww,
                                        Math.random()*hh) ).
                                setFrameTime( scene.time, 3000+Math.random()*3000 ).
                                addListener(new BehaviorListener() {
                                    
                                    @Override
                                    public void behaviorExpired(BaseBehavior behaviour, double time, Actor actor) {
                                        PathBehavior pathBehavior = (PathBehavior) behaviour;
                                        Pt endCoord = pathBehavior.path.endCurvePosition();
                                        pathBehavior.setPath(
                                                new Path().setCubic(
                                                    endCoord.x,
                                                    endCoord.y,
                                                    Math.random()*ww,
                                                    Math.random()*hh,
                                                    Math.random()*ww,
                                                    Math.random()*hh,
                                                    Math.random()*ww,
                                                    Math.random()*hh) );
                                        behaviour.setFrameTime( scene.time, 3000+Math.random()*3000 );            
                                        
                                    }
                                    
                                    @Override
                                    public void behaviorApplied(BaseBehavior behavior, double time, double normalizeTime, Actor actor, SetForTimeReturnValue value) {
                                        
                                    }

                                    @Override
                                    public void behaviorStarted(BaseBehavior behavior, double time, Actor actor) {
                                        
                                    }
                                }));
                container.addChild(css);
            }
        }

        __scene12_text(director,scene);

        return scene;
        
    }
    
    public static void __scene12_text(Director director, Scene scene) throws Exception {

        CSSActor cc= new CSSActor().
                create().
                setBounds( 450,30, 150, 100 ).
                enableEvents(false).
                addBehavior(
                    new RotateBehavior().
                            setCycle(true).
                            setFrameTime( 0, 4000 ).
                            setValues( -Math.PI/8, Math.PI/8 ).
                            setInterpolator(
                                new Interpolator().createExponentialInOutInterpolator(3,true)
                            )//.
                            //setAnchor( Actor.Anchor.BOTTOM)
                );
        scene.addChild(cc);

        CSSActor text= new CSSActor().
                create().
                setInnerHTML("<b>Just CSS").
                setSize(80,40);
        text.setLocation( (cc.width-80)/2, 0 );
        cc.addChild(text);

        CSSActor text2= new CSSActor().
                create().
                setInnerHTML("<b>Actors").
                setSize(80,40);
        text2.setLocation( (cc.width-80)/2, 40 );
        cc.addChild(text2);

        scene.addChild(cc);
    }

}
