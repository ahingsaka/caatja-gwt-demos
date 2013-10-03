package com.katspow.caatjagwtdemos.client.welcome.tutorials;

import com.google.gwt.user.client.Window;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatjagwtdemos.client.welcome.HomeView.SimpleTutorial;
import com.katspow.caatjagwtdemos.client.welcome.tutorials.tut.GettingStarted;

/**
 * Shows some examples with source code.
 * 
 */
public class Tutorials {

    private static Director director;
    private static boolean started;

    /**
     * Entry point.
     * 
     * @param director
     * 
     * @throws Exception
     */
    public static void start(Director director) throws Exception {
        if (!started) {
            Tutorials.director = director;
        }

    }

    public static void launch(SimpleTutorial simpleTutorial) {
        try {
            
            switch (simpleTutorial) {
            case GETTING_STARTED:
                GettingStarted.launch(director);
                break;

            default:
                break;
            }
            
        } catch (Exception e) {
            Window.alert("Cannot load " + simpleTutorial.getLabel());
        }
    }

}
