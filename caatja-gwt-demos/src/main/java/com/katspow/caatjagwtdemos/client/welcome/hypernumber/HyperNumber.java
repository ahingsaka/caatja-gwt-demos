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

    private static Director director;
    private static Scene menuScene;
    private static Scene playScene;
    private static boolean started;

    public static void start(Director director) throws Exception {

        if (!started) {

            HyperNumber.director = director;

            GardenScene gardenScene = new GardenScene().create(director, 120);

            Context context = new Context().create(8, 12, GameScene.imageBricksH);

            GameScene gameScene = new GameScene().create(director, 8, 8, context);
            gardenScene.gameScene = gameScene;

            menuScene = gardenScene.directorScene;
            playScene = gameScene.directorScene;

            started = true;
        }

        setup();
        director.setScene(director.getSceneIndex(menuScene));

    }

    private static void setup() throws Exception {
        // director = new Director().initialize(640, 480).setClear(false);
        director.setClear(false);
    }

    public static Scene getPlayScene() {
        return playScene;
    }

    public static Scene getMenuScene() {
        return menuScene;
    }

}
