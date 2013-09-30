package com.katspow.caatjagwtdemos.client.welcome.hypernumber;

import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatjagwtdemos.client.welcome.hypernumber.core.GameScene;
import com.katspow.caatjagwtdemos.client.welcome.hypernumber.core.context.Context;
import com.katspow.caatjagwtdemos.client.welcome.hypernumber.startmenu.GardenScene;

/**
 * Entry point for hypernumber demo 
 *
 */
public class HyperNumber {
    
    private  Director director;
    
    private static  Scene menuScene;
    private static  Scene playScene;
    private  boolean started;
    
    public void start(Director director) throws Exception {
        
        if (!started) {
            
        this.director = director;
        
//        final CaatjaPreloader preloader = Caatja.getCaatjaImagePreloader();
//
//        preloader.addImage("bricks", "nums.png");
//        preloader.addImage("buttons", "buttons.png");
//        preloader.addImage("madewith", "madewith.png");
//        preloader.addImage("space", "space2.jpg");
//        preloader.addImage("background", "fondo.jpg");
//        preloader.addImage("background_op", "fondo_opciones.jpg");
//        preloader.addImage("cloud1", "nube1.png");
//        preloader.addImage("cloud2", "nube2.png");
//        preloader.addImage("cloud3", "nube3.png");
//        preloader.addImage("cloud4", "nube4.png");
//
//        preloader.addImage("cloudb1", "nubefondo1.png");
//        preloader.addImage("cloudb2", "nubefondo2.png");
//        preloader.addImage("cloudb3", "nubefondo3.png");
//        preloader.addImage("cloudb4", "nubefondo4.png");

//        CaatjaImageLoader caatjaImageLoader = Caatja.getCaatjaImageLoader();
//        caatjaImageLoader.loadImages(preloader, new CaatjaImageLoaderCallback() {
//            public void onFinishedLoading() throws Exception {

//                director.imagesCache = preloader.getCaatjaImages();

                GardenScene gardenScene = new GardenScene().create(director, 120);

                Context context = new Context().create(8, 12, GameScene.imageBricksH);

                GameScene gameScene = new GameScene().create(director, 8, 8, context);
                gardenScene.gameScene = gameScene;
                
                menuScene = gardenScene.directorScene;
//                int menuSceneIndex = director.addScene(menuScene);
//                int playSceneIndex = director.addScene(gameScene.directorScene);
                playScene = gameScene.directorScene;
                
                started = true;
        }
                
        setup();
                director.setScene(director.getSceneIndex(menuScene));
                

//                Caatja.loop(60);

//            }
//        });

    }
    
    private  void setup() throws Exception {
        //director = new Director().initialize(640, 480).setClear(false);
        director.setClear(false);
    }
    
    public static Scene getPlayScene() {
        return playScene;
    }
    
    public static Scene getMenuScene() {
        return menuScene;
    }

}
