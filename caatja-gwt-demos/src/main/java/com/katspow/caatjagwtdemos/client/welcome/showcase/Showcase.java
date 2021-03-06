package com.katspow.caatjagwtdemos.client.welcome.showcase;

import java.util.ArrayList;
import java.util.List;

import com.katspow.caatja.behavior.AlphaBehavior;
import com.katspow.caatja.behavior.BaseBehavior;
import com.katspow.caatja.behavior.BaseBehavior.Status;
import com.katspow.caatja.behavior.ScaleBehavior;
import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaColor;
import com.katspow.caatja.core.canvas.CaatjaContext2d;
import com.katspow.caatja.core.image.CaatjaImageLoader;
import com.katspow.caatja.core.image.CaatjaImageLoaderCallback;
import com.katspow.caatja.core.image.CaatjaPreloader;
import com.katspow.caatja.event.CAATMouseEvent;
import com.katspow.caatja.event.MouseListener;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;
import com.katspow.caatja.foundation.ui.TextFont;
import com.katspow.caatjagwtdemos.client.welcome.showcase.actor.SpecialActor;
import com.katspow.caatjagwtdemos.client.welcome.showcase.scenes.Scene1;
import com.katspow.caatjagwtdemos.client.welcome.showcase.scenes.Scene10;
import com.katspow.caatjagwtdemos.client.welcome.showcase.scenes.Scene11;
import com.katspow.caatjagwtdemos.client.welcome.showcase.scenes.Scene12;
import com.katspow.caatjagwtdemos.client.welcome.showcase.scenes.Scene2;
import com.katspow.caatjagwtdemos.client.welcome.showcase.scenes.Scene3;
import com.katspow.caatjagwtdemos.client.welcome.showcase.scenes.Scene4;
import com.katspow.caatjagwtdemos.client.welcome.showcase.scenes.Scene5;
import com.katspow.caatjagwtdemos.client.welcome.showcase.scenes.Scene6;
import com.katspow.caatjagwtdemos.client.welcome.showcase.scenes.Scene7;
import com.katspow.caatjagwtdemos.client.welcome.showcase.scenes.Scene8;
import com.katspow.caatjagwtdemos.client.welcome.showcase.scenes.ShowcaseScene;

/**
 * 
 *
 */
public class Showcase {

    private static  Director director;
    private static  ShowcaseScene showcaseScene;
    private static  List<Scene> showcaseScenes;
    private static  boolean started;

    /**
     * Entry point.
     * @param director 
     * 
     * @throws Exception
     */
    public static void start(Director director) throws Exception {
        Showcase.director = director;
        setup();
    }

    /**
     * Prepare all CAATJA stuff here.
     * 
     * @throws Exception
     */
    private static void setup() throws Exception {
    	
    	// TODO Since hypernumber setClear to false, I have to reset those values
    	director.dirtyRectsEnabled = false;
    	director.clear = true;
        
        if (!started) {
            showcaseScene = new ShowcaseScene();
            director.addScene(showcaseScene);
            showcaseScene.load(director);
            
            loadImages();
            
            started = true;
            director.setScene(showcaseScene);
        } else {
        	director.setScene(director.getSceneIndex(showcaseScenes.get(0)));
        }
        
    }

    /**
     * Add a loading message before launching showcase.
     * 
     * @throws Exception
     */
//    private void createLoadingText() throws Exception {
//        TextActor loading = new TextActor();
//        loading.setFont("30px sans-serif").
//            setTextBaseline("top").
//            setText("Loading ...").
//            calcTextSize(director).
//            setTextFillStyle("white");
//        
//        loading.setLocation((director.canvas.getCoordinateSpaceWidth() - loading.width) / 2,
//                (director.canvas.getCoordinateSpaceHeight()) / 2);
//
//        showcaseScene.addChild(loading);
//    }

    /**
     * Once images are "preloaded", we call the image service from the server to get them
     */
    private static  void loadImages() {
        preloadImages();

        CaatjaImageLoader caatjaImageLoader = Caatja.getCaatjaImageLoader();
        caatjaImageLoader.loadImages(Caatja.getCaatjaImagePreloader(), new CaatjaImageLoaderCallback() {
            @Override
            public void onFinishedLoading() throws Exception {
                finishShowcaseLoading();
            }
        });

    }

    /**
     * We add requested images to the preloader
     */
    private static  void preloadImages() {
        final CaatjaPreloader preloader = Caatja.getCaatjaImagePreloader();
        preloader.addImage("fish", "anim1.png");
        preloader.addImage("fish2", "anim2.png");
        preloader.addImage("fish3", "anim3.png");
        preloader.addImage("fish4", "anim4.png");
        preloader.addImage("chapas", "chapas.jpg");
        preloader.addImage("buble1", "burbu1.png");
        preloader.addImage("buble2", "burbu2.png");
        preloader.addImage("buble3", "burbu3.png");
        preloader.addImage("buble4", "burbu4.png");
        preloader.addImage("plants", "plants.jpg");
        preloader.addImage("bumps", "3.jpg");
    }

    /**
     * When images are loaded, we store them and we can load all the showcase
     * scenes.
     * 
     * @throws Exception
     */
    private static  void finishShowcaseLoading() throws Exception {
        director.imagesCache = Caatja.getCaatjaImagePreloader().getCaatjaImages();
        
        //director.emptyScenes();
        
        loadShowcaseScenes();
        // scene_loading.finishedLoading();
    }

    /**
     * Load all showcase scenes
     * @throws Exception
     */
    private static  void loadShowcaseScenes() throws Exception {
        
        showcaseScenes = new ArrayList<Scene>();
        showcaseScenes.add(Scene1.init(director));
        showcaseScenes.add(Scene2.init(director));
        showcaseScenes.add(Scene3.init(director));
        showcaseScenes.add(Scene4.init(director));
        showcaseScenes.add(Scene5.init(director));
        showcaseScenes.add(Scene6.init(director));
        showcaseScenes.add(Scene12.init(director));
        showcaseScenes.add(Scene7.init(director));
        showcaseScenes.add(Scene8.init(director));
        showcaseScenes.add(Scene10.init(director));
        
        // TODO Add touch/acceleration scene when it is finished
        //showcaseScenes.add(Scene11.init(director));
        
        for (Scene scene : showcaseScenes) {
            director.addScene(scene);
        }
        
        int firstSceneIndex = director.getSceneIndex(showcaseScenes.get(0));
//        director.setScene(firstSceneIndex);
        
        createSceneSwitchingButtons();
        
        Effects.welcome(firstSceneIndex, director);
    }

    /**
     * Creates all buttons for scene navigation
     * @throws Exception 
     */
    private static  void createSceneSwitchingButtons() throws Exception {
        
        int numScenes = showcaseScenes.size();
        double buttonW = 22.5;
        double buttonX = (director.width - buttonW * numScenes) / 2;

        for (int i = 0; i < numScenes; i++) {
            
            Scene scene = showcaseScenes.get(i);

            if (i != 0) {
                createPreviousButton(i, scene);
            }

            if (i != numScenes - 1) {
                createNextButton(i, scene);
            }

            for (int j = 0; j < numScenes; j++) {
                createAllSceneButtons(i, j, scene, buttonX, buttonW);
            }

        }
    }

    /**
     * Creates all the buttons on the bottom for scene navigation
     * @param scene 
     * @throws Exception 
     */
    private static  void createAllSceneButtons(int i, int j, Scene scene, double buttonX, double buttonW) throws Exception {

        SpecialActor idx = new SpecialActor() {
            
            // TODO Remove
//            @Override
//            public void mouseClick(CAATMouseEvent mouseEvent) throws Exception {
//                
//                int sceneIndex = ((SpecialActor)mouseEvent.source).__sceneIndex;
//                director.switchToScene(director.getSceneIndex(showcaseScenes.get(sceneIndex)),1000,false,true);
////                director.switchToScene(sceneIndex,1000,false,true);
//            }

            @Override
            public void paint(Director director, double time) {
                CaatjaContext2d canvas= director.ctx;
                
                if ( null!=this.parent && null!=this.fillStyle ) {
                    canvas.beginPath();
                    canvas.setFillStyle(this.pointed ? CaatjaColor.valueOf("orange") : (this.fillStyle!=null ? this.fillStyle : CaatjaColor.valueOf("white"))); //'white';
                    canvas.arc(10,10,10,0,Math.PI*2,false );
                    canvas.fill();
                }

                if ( this.clip ) {
                    canvas.beginPath();
                    canvas.rect(0,0,this.width,this.height);
                    canvas.clip();
                }
                    
                canvas.setFillStyle("#ffff00");
                canvas.setTextBaseline("top");
                canvas.setFont(new TextFont(18, "px", "sans-serif"));
                String str= ""+(this.__sceneIndex+1);
                double w= canvas.measureTextWidth(str);
                canvas.fillText( str, (this.width-w)/2, 0 );

            }
            
        };
        
        idx.name = "idx";
        idx.__sceneIndex = j;
        idx.setBounds(buttonX + j * buttonW, 470, 20, 20);
        
        idx.setMouseClickListener(new MouseListener() {
            public void call(CAATMouseEvent mouseEvent) throws Exception {
                int sceneIndex = ((SpecialActor)mouseEvent.source).__sceneIndex;
                director.switchToScene(director.getSceneIndex(showcaseScenes.get(sceneIndex)),1000,false,true);
            }
        });
        
        if (j != i) {
            // TODO ???
            // idx.mouseClick = function(mouseEvent) {
            // director.switchToScene(mouseEvent.source.__sceneIndex,1000,false,true);
            // };
            setupTRButton(idx);
            idx.fillStyle = CaatjaColor.valueOf("#0000ff");
        } else {
            idx.mouseEnabled = false;
            idx.fillStyle = CaatjaColor.valueOf("#c07f00");
        }
        
        director.scenes.get(director.getSceneIndex(showcaseScenes.get(i))).addChild(idx);

        setupTRButtonPaintIndex(idx, j + 1);
        
    }

    private static  void createNextButton(int index, Scene scene) throws Exception {
        
        Actor next = new Actor() {
            
            // TODO Remove
//            @Override
//            public void mouseClick(CAATMouseEvent mouseEvent) throws Exception {
//                director.switchToNextScene(1000,false,true);
//            }
            
            // TODO Remove
//            @Override
//            public void mouseEnter(CAATMouseEvent mouseEvent) {
//                Actor actor = mouseEvent.source;
//                if (null == actor) {
//                    return;
//                }
//
//                BaseBehavior behaviour = actor.behaviorList.get(0);
//                if (null == behaviour) {
//                    return;
//                }
//
//                actor.pointed = true;
//
//                if (behaviour.status == Status.EXPIRED) {
//                    actor.behaviorList.get(0).setFrameTime(mouseEvent.source.time, 1000);
//                    actor.behaviorList.get(0).setCycle(true);
//                    actor.behaviorList.get(1).setFrameTime(mouseEvent.source.time, 1000);
//                    actor.behaviorList.get(1).setCycle(true);
//                }
//            }

            // TODO Remove
//            @Override
//            public void mouseExit(CAATMouseEvent mouseEvent) {
//                Actor actor = mouseEvent.source;
//                if (null == actor) {
//                    return;
//                }
//                
//                BaseBehavior behaviour = actor.behaviorList.get(0);
//                if (null == behaviour) {
//                    return;
//                }
//
//                actor.pointed = false;
//                actor.behaviorList.get(0).setExpired(actor, mouseEvent.source.time);
//                actor.behaviorList.get(1).setExpired(actor, mouseEvent.source.time);
//            }

            @Override
            public void paint(Director director, double time) {
                CaatjaContext2d canvas= director.ctx;
                
                if (null != this.parent && null != this.fillStyle) {
                    canvas.beginPath();
                    canvas.setFillStyle(this.pointed ? CaatjaColor.valueOf("orange") : (this.fillStyle!=null ? this.fillStyle : CaatjaColor.valueOf("white"))); //'white';
                    canvas.arc(10,10,10,0,Math.PI*2,false );
                    canvas.fill();
                }

                if (this.clip) {
                    canvas.beginPath();
                    canvas.rect(0, 0, this.width, this.height);
                    canvas.clip();
                }
                    
                canvas.setStrokeStyle(this.pointed ?  CaatjaColor.valueOf("green") :  CaatjaColor.valueOf("#ffff00"));
                canvas.beginPath();

                canvas.moveTo(3,10);
                canvas.lineTo(17,10);
                canvas.lineTo(13,5);
                
                canvas.moveTo(17,10);
                canvas.lineTo(13,15);
                
                canvas.setLineWidth(2);
                canvas.setLineJoin("round");
                canvas.setLineCap("round");

                canvas.stroke();
            }
        };
        
        next.setMouseExitListener(new MouseListener() {
            public void call(CAATMouseEvent e) throws Exception {
                Actor actor = e.source;
                if (null == actor) {
                    return;
                }
                
                BaseBehavior behaviour = actor.behaviorList.get(0);
                if (null == behaviour) {
                    return;
                }

                actor.pointed = false;
                actor.behaviorList.get(0).setExpired(actor, e.source.time);
                actor.behaviorList.get(1).setExpired(actor, e.source.time);
            }
        });
        
        next.setMouseEnterListener(new MouseListener() {
            public void call(CAATMouseEvent e) throws Exception {
                Actor actor = e.source;
                if (null == actor) {
                    return;
                }

                BaseBehavior behaviour = actor.behaviorList.get(0);
                if (null == behaviour) {
                    return;
                }

                actor.pointed = true;

                if (behaviour.status == Status.EXPIRED) {
                    actor.behaviorList.get(0).setFrameTime(e.source.time, 1000);
                    actor.behaviorList.get(0).setCycle(true);
                    actor.behaviorList.get(1).setFrameTime(e.source.time, 1000);
                    actor.behaviorList.get(1).setCycle(true);
                }
            }
        });
        
        next.setMouseClickListener(new MouseListener() {
            public void call(CAATMouseEvent e) throws Exception {
                director.switchToNextScene(1000,false,true);
            }
        });
        
        next.setBounds(director.width - 20 - 5, 470, 20, 20);
        next.fillStyle = CaatjaColor.valueOf("#0000ff");

        director.scenes.get(director.getSceneIndex(scene)).addChild(next);
        //director.scenes.get(index).addChild(next);

        setupTRButton(next);
        setupTRButtonPaint(next);
    }

    private static  void createPreviousButton(int index, Scene scene) throws Exception {
        
        Actor prev = new Actor() {
            
            // TODO Remove
//            @Override
//            public void mouseClick(CAATMouseEvent mouseEvent) throws Exception {
//                director.switchToPrevScene(1000,false,true);
//            }

            // TODO Remove
//            @Override
//            public void mouseEnter(CAATMouseEvent mouseEvent) {
//                Actor actor = mouseEvent.source;
//                if (null == actor) {
//                    return;
//                }
//
//                BaseBehavior behaviour = actor.behaviorList.get(0);
//                if (null == behaviour) {
//                    return;
//                }
//              
//                actor.pointed = true;
//              
//                if (behaviour.status == Status.EXPIRED) {
//                  actor.behaviorList.get(0).
//                      setFrameTime( mouseEvent.source.time, 1000 ).
//                      setCycle(true);
//                  actor.behaviorList.get(1).
//                      setFrameTime( mouseEvent.source.time, 1000 ).
//                      setCycle(true);
//                }
//            }

            // TODO Remove
//            @Override
//            public void mouseExit(CAATMouseEvent mouseEvent) {
//                Actor actor = mouseEvent.source;
//                if (null == actor) {
//                    return;
//                }
//                BaseBehavior behaviour = actor.behaviorList.get(0);
//                if (null == behaviour) {
//                    return;
//                }
//
//                actor.pointed = false;
//                actor.behaviorList.get(0).setExpired(actor, mouseEvent.source.time);
//                actor.behaviorList.get(1).setExpired(actor, mouseEvent.source.time);
//            }

            @Override
            public void paint(Director director, double time) {
                CaatjaContext2d canvas= director.ctx;
                
                if ( null!=this.parent && null!=this.fillStyle ) {
                    canvas.beginPath();
                    canvas.setFillStyle(this.pointed ? CaatjaColor.valueOf("orange") : (this.fillStyle!=null ? this.fillStyle : CaatjaColor.valueOf("white"))); //'white';
                    canvas.arc(10,10,10,0,Math.PI*2,false );
                    canvas.fill();
                }

                if ( this.clip ) {
                    canvas.beginPath();
                    canvas.rect(0,0,this.width,this.height);
                    canvas.clip();
                }
                    
                canvas.setStrokeStyle(this.pointed ?  CaatjaColor.valueOf("green") :  CaatjaColor.valueOf("#ffff00"));
                canvas.beginPath();

                canvas.moveTo(3,10);
                canvas.lineTo(17,10);
                canvas.lineTo(13,5);
                
                canvas.moveTo(17,10);
                canvas.lineTo(13,15);
                
                canvas.setLineWidth(2);
                canvas.setLineJoin("round");
                canvas.setLineCap("round");

                canvas.stroke();
            }
            
            
        };
        
        prev.setMouseExitListener(new MouseListener() {
            public void call(CAATMouseEvent e) throws Exception {
                Actor actor = e.source;
                if (null == actor) {
                    return;
                }
                BaseBehavior behaviour = actor.behaviorList.get(0);
                if (null == behaviour) {
                    return;
                }

                actor.pointed = false;
                actor.behaviorList.get(0).setExpired(actor, e.source.time);
                actor.behaviorList.get(1).setExpired(actor, e.source.time);
            }
        });
        
        prev.setMouseEnterListener(new MouseListener() {
            public void call(CAATMouseEvent e) throws Exception {
                Actor actor = e.source;
                if (null == actor) {
                    return;
                }

                BaseBehavior behaviour = actor.behaviorList.get(0);
                if (null == behaviour) {
                    return;
                }
              
                actor.pointed = true;
              
                if (behaviour.status == Status.EXPIRED) {
                  actor.behaviorList.get(0).
                      setFrameTime( e.source.time, 1000 ).
                      setCycle(true);
                  actor.behaviorList.get(1).
                      setFrameTime( e.source.time, 1000 ).
                      setCycle(true);
                }
            }
        });
        
        prev.setMouseClickListener(new MouseListener() {
            public void call(CAATMouseEvent e) throws Exception {
                director.switchToPrevScene(1000,false,true);
            }
        });
        
        prev.setBounds(5, 470, 20, 20);
        prev.setRotation(Math.PI);
        prev.fillStyle = CaatjaColor.valueOf("#0000ff");

        director.scenes.get(director.getSceneIndex(scene)).addChild(prev);
//        director.scenes.get(index).addChild(prev);

        setupTRButton(prev);
        setupTRButtonPaint(prev);
    }
    
    private static  void setupTRButton(Actor prev) {
        
        ScaleBehavior sb = new ScaleBehavior().
                setPingPong().
                setValues(1, 1.5, 1, 1.5);
        prev.addBehavior(sb);
        
        AlphaBehavior ab = new AlphaBehavior().
                setPingPong().
                setValues(1,.5).
                setCycle(true);
        prev.addBehavior(ab);
        
    }
    
    private static  void setupTRButtonPaint(Actor prev) {
        prev.fillStyle = CaatjaColor.valueOf("#0000ff");
    }

    private static  void setupTRButtonPaintIndex(Actor idx, int i) {
        // DO nothing
    }

}
