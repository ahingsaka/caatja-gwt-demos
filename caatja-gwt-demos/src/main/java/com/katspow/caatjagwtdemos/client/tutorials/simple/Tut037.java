package com.katspow.caatjagwtdemos.client.tutorials.simple;

import com.katspow.caatja.behavior.Interpolator;
import com.katspow.caatja.behavior.RotateBehavior;
import com.katspow.caatja.behavior.ScaleBehavior;
import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaGradient;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.ui.PathActor;
import com.katspow.caatja.foundation.ui.TextActor;
import com.katspow.caatja.pathutil.Path;

/**
 * FIXME Colors are not correct.
 * 
 * @author ahingsaka
 *
 */
public class Tut037 {
    
    public void init() throws Exception {
        Director _director_7= new Director().initialize(
                700,
                200,
                null
                );

        Scene _scene_7= _director_7.createScene();

        // create a vertical gradient to apply to text.
        CaatjaGradient gradient_top= _director_7.ctx.createLinearGradient(0,0,0,40);
        gradient_top.addColorStop(0,"#ffff00");
        gradient_top.addColorStop(0.5,"#00ffff");
        gradient_top.addColorStop(1,"blue");

        // outline text. no fillStyle since the text will just be outlined.
        TextActor _text_00= (TextActor) new TextActor();
        _text_00.setFont("40px sans-serif").
                setText("CAAT").
                setTextBaseline("top").
                setFill(false).
                setLocation( 0, 30 ).
                calcTextSize(_director_7).
                cacheAsBitmap();

        // gradient text.
        TextActor _text_01= (TextActor) new TextActor();
                _text_01.setFont("40px sans-serif").
                setText("CAAT").
                setFillStrokeStyle(gradient_top);
                
                _text_01.setTextBaseline("top").
                setLocation( 0, 80 );

        // gradient + outlined.
        TextActor _text_02= (TextActor) new TextActor().
                setFont("40px sans-serif").
                setText("CAAT").
                setFillStrokeStyle(gradient_top);
                
                _text_02.setTextBaseline("top").
                setOutline(true).
                setOutlineColor("blue").
                setLocation( 0, 130 ).
                calcTextSize(_director_7).
                cacheAsBitmap();

        _scene_7.addChild(_text_00);
        _scene_7.addChild(_text_01);
        _scene_7.addChild(_text_02);

        // a simple TextActor
        TextActor _text_10= (TextActor) new TextActor().
                setFont("40px sans-serif").
                setText("CAAT").
                setFillStrokeStyle(gradient_top);
                _text_10.setTextBaseline("top").
                setOutline(true).
                setOutlineColor("yellow").
                setLocation( 200,80 ).
                calcTextSize(_director_7).
                cacheAsBitmap();

        // rotate in 3 seconds
        RotateBehavior _text_00_rb= (RotateBehavior) new RotateBehavior().
                setAngles(0, 2*Math.PI).
                setFrameTime( 0, 3000 ).
                setCycle(true);

        // scale in 6 seconds doing pingpong
        ScaleBehavior _text_00_sb= (ScaleBehavior) new ScaleBehavior().
                setValues( .5, 2, .5, 2 ).
                setFrameTime( 0, 6000 ).
                setCycle(true).
                setPingPong();

        _text_10.addBehavior( _text_00_rb );
        _text_10.addBehavior( _text_00_sb );
        _scene_7.addChild( _text_10 );


        // create a circular path.
        Path _path_c7= (Path) new Path().
            beginPath(425,100).
            addCubicTo( 425,0,   625,0,   625,100, null ).
            addCubicTo( 625,200,  425,200,  425,100, null ).
            endPath();

        // set an actor to be able to handle path.
        PathActor _pathactor_c7= (PathActor) new PathActor().
                setBounds(0,0,_director_7.width,_director_7.height);
                _pathactor_c7.setPath(_path_c7);
        _scene_7.addChild( _pathactor_c7 );

        // make text traverse over the path
        TextActor _text_20= (TextActor) new TextActor().
                setFont("30px sans-serif").
                setText("CAAT").
                setFillStyle("green");
                _text_20.setTextBaseline("bottom").      // over the path
                setOutline(true).
                setOutlineColor("red").
                calcTextSize(_director_7);
        _text_20.setPath(_path_c7, null, null);          // traverse the path with defaults.
        _scene_7.addChild( _text_20 );

//        // make text traverse under the path
        TextActor _text_21= new TextActor().
                setFont("40px sans-serif").
                setText("CAAT").
                setFillStrokeStyle(gradient_top).
                setTextBaseline("top").         // under path
                setOutline(true).
                setOutlineColor("red").
                calcTextSize(_director_7).
                setPath( _path_c7,
                         new Interpolator().createBounceInOutInterpolator(true),
                         20000d );
        _scene_7.addChild( _text_21 );

        Caatja.loop(20);
    }

}
