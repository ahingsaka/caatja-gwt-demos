package com.katspow.caatjagwtdemos.client.tutorials.simple;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.katspow.caatja.behavior.ContainerBehavior;
import com.katspow.caatja.behavior.RotateBehavior;
import com.katspow.caatja.behavior.ScaleBehavior;
import com.katspow.caatja.core.CAAT;
import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaImage;
import com.katspow.caatja.core.image.CaatjaImageLoader;
import com.katspow.caatja.core.image.CaatjaImageLoaderCallback;
import com.katspow.caatja.core.image.CaatjaPreloader;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.SpriteActor;
import com.katspow.caatja.foundation.actor.SpriteActor.Tr;
import com.katspow.caatja.foundation.image.SpriteImage;

// FIXME Null pointer exception
public class Tut036 {
    
    private void start(Director _director_6, CaatjaPreloader preloader) throws Exception {
        _director_6.imagesCache = preloader.getCaatjaImages();
        
        Scene _scene_6= _director_6.createScene();
        
        _director_6.addScene( _scene_6 );
        
     // CompoundImage, basically treates a single image as an Array
        // of equals sized subimages. We want in this case, one row, 3 columns.
        SpriteImage cimage = new SpriteImage().
                initialize(_director_6.getImage("fish"), 1, 3);

        // create 4 fish positioned in a 2x2 array.
        // set sprite transformation to show how fish is transformed..
        for(int i=0; i<4; i++ ) {
            
            int multiply = 0;
            if (i > 1) {
                multiply = 1;
            }
            
            SpriteActor fish = (SpriteActor) new SpriteActor();
            
                    System.out.println("i" + i + ", y: " + 20+(15+cimage.singleWidth)*(multiply));
                    fish.setAnimationImageIndex( Arrays.asList(0,1,2,1) ).
                    setBackgroundImage(cimage.getRef(), false).
                    setChangeFPS(350).
                    setLocation(20+(i%2)*(15+cimage.singleWidth), 20+(15+cimage.singleWidth)*(multiply));
                    
                    Tr transformation = Tr.NONE;
                    switch (i) {
                        case 0:
                            transformation = Tr.NONE;
                            break;
                        case 1:
                            transformation = Tr.FLIP_HORIZONTAL;
                            break;
                        case 2:
                            transformation = Tr.FLIP_VERTICAL;
                            break;
                        case 3:
                            transformation = Tr.FLIP_ALL;
                            break;
                        
                    }
                    
                    fish.setSpriteTransformation(transformation);
                    
            _scene_6.addChild(fish);
        }

        // Sprite objects are Actors, and hence can contain behaviors.
        SpriteActor fish_scaling = (SpriteActor) new SpriteActor();
                fish_scaling.setAnimationImageIndex(Arrays.asList(0,1,2,1)).
                setBackgroundImage(cimage.getRef(), false).
                setChangeFPS(500).
                setLocation(200,70).
                setAlpha(.8);

        RotateBehavior rb_6= (RotateBehavior) new RotateBehavior().
                setAngles(0, 2*Math.PI).
                setFrameTime( 0, 20000 ).
                setCycle(true);

        ContainerBehavior cb_6= (ContainerBehavior) new ContainerBehavior().
                setFrameTime(0,2000).
                setCycle(true);
        
        ScaleBehavior sb_6_x= (ScaleBehavior) new ScaleBehavior().
                    setValues( 2, 4, 2, 2 ).
                    setFrameTime( 0, 1000 ).
                    setPingPong();
        
        ScaleBehavior sb_6_y= (ScaleBehavior) new ScaleBehavior().
                    setValues( 2, 2, 2, 4 ).
                    setFrameTime( 1000, 1000 ).
                    setPingPong();
        cb_6.addBehavior( sb_6_x );
        cb_6.addBehavior( sb_6_y );

        fish_scaling.addBehavior( rb_6 );
        fish_scaling.addBehavior( cb_6 );

        _scene_6.addChild(fish_scaling);

        

        Caatja.loop(20);
        
    }
    
    public void init() throws Exception {
        
        final Director _director_6= new Director().initialize(
                400,
                150,
                null );
        
        Map<String, CaatjaImage> images = new HashMap<String, CaatjaImage>();
//        final ImageResources INSTANCE = GWT.create(ImageResources.class);
//        Image fishImage = new Image(INSTANCE.fish().getSafeUri());
//        images.put("fish", ImageElement.as(fishImage.getElement()));
        
        final CaatjaPreloader preloader = Caatja.getCaatjaImagePreloader();
        preloader.addImage("fish", "anim1.png");
        
        CaatjaImageLoader caatjaImageLoader = Caatja.getCaatjaImageLoader();
        
        caatjaImageLoader.loadImages(preloader, new CaatjaImageLoaderCallback() {
            @Override
            public void onFinishedLoading() throws Exception {
                start(_director_6, preloader);
            }
            
        });
        
//        _director_6.imagesCache = images;

//        Scene _scene_6= _director_6.createScene();
//        
//        _director_6.addScene( _scene_6 );
//        
//     // CompoundImage, basically treates a single image as an Array
//        // of equals sized subimages. We want in this case, one row, 3 columns.
//        SpriteImage cimage = new SpriteImage().
//                initialize(_director_6.getImage("fish"), 1, 3);
//
//        // create 4 fish positioned in a 2x2 array.
//        // set sprite transformation to show how fish is transformed..
//        for(int i=0; i<4; i++ ) {
//            
//            int multiply = 0;
//            if (i > 1) {
//                multiply = 1;
//            }
//            
//            SpriteActor fish = (SpriteActor) new SpriteActor();
//            
//                    System.out.println("i" + i + ", y: " + 20+(15+cimage.singleWidth)*(multiply));
//                    fish.setAnimationImageIndex( Arrays.asList(0,1,2,1) ).
//                    setBackgroundImage(cimage.getRef(), false).
//                    setChangeFPS(350).
//                    setLocation(20+(i%2)*(15+cimage.singleWidth), 20+(15+cimage.singleWidth)*(multiply));
//                    
//                    Tr transformation = Tr.NONE;
//                    switch (i) {
//                        case 0:
//                            transformation = Tr.NONE;
//                            break;
//                        case 1:
//                            transformation = Tr.FLIP_HORIZONTAL;
//                            break;
//                        case 2:
//                            transformation = Tr.FLIP_VERTICAL;
//                            break;
//                        case 3:
//                            transformation = Tr.FLIP_ALL;
//                            break;
//                        
//                    }
//                    
//                    fish.setSpriteTransformation(transformation);
//                    
//            _scene_6.addChild(fish);
//        }
//
//        // Sprite objects are Actors, and hence can contain behaviors.
//        SpriteActor fish_scaling = (SpriteActor) new SpriteActor();
//                fish_scaling.setAnimationImageIndex(Arrays.asList(0,1,2,1)).
//                setBackgroundImage(cimage.getRef(), false).
//                setChangeFPS(500).
//                setLocation(200,70).
//                setAlpha(.8);
//
//        RotateBehavior rb_6= (RotateBehavior) new RotateBehavior().
//                setAngles(0, 2*Math.PI).
//                setFrameTime( 0, 20000 ).
//                setCycle(true);
//
//        ContainerBehavior cb_6= (ContainerBehavior) new ContainerBehavior().
//                setFrameTime(0,2000).
//                setCycle(true);
//        
//        ScaleBehavior sb_6_x= (ScaleBehavior) new ScaleBehavior().
//                    setValues( 2, 4, 2, 2 ).
//                    setFrameTime( 0, 1000 ).
//                    setPingPong();
//        
//        ScaleBehavior sb_6_y= (ScaleBehavior) new ScaleBehavior().
//                    setValues( 2, 2, 2, 4 ).
//                    setFrameTime( 1000, 1000 ).
//                    setPingPong();
//        cb_6.addBehavior( sb_6_x );
//        cb_6.addBehavior( sb_6_y );
//
//        fish_scaling.addBehavior( rb_6 );
//        fish_scaling.addBehavior( cb_6 );
//
//        _scene_6.addChild(fish_scaling);
//
//        
//
//        CAAT.loop(20);
    }

}
