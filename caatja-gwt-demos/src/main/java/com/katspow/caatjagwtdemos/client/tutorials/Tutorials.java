package com.katspow.caatjagwtdemos.client.tutorials;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Tree;
import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.core.image.CaatjaImageLoader;
import com.katspow.caatja.core.image.CaatjaImageLoaderCallback;
import com.katspow.caatja.core.image.CaatjaPreloader;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatjagwtdemos.client.tutorials.simple.Tut001;
import com.katspow.caatjagwtdemos.client.tutorials.simple.Tut021;
import com.katspow.caatjagwtdemos.client.tutorials.simple.Tut022;

/**
 * A set of small demos with their source code displayed.
 * 
 */
public class Tutorials {

    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 600;
    private static final int SOURCE_DISPLAY_Y = CANVAS_HEIGHT + 20;
    private static final int TREE_X = CANVAS_WIDTH + 20;
    
    private Director director;
    private ScrollPanel sourceCodeDisplay;
    private CaatjaCanvas canvas;

    /**
     * Entry point.
     * 
     * @throws Exception
     */
    public void start() throws Exception {
        setup();
        loadImages();
    }

    /**
     * Create CAATJA stuff, prepare tutorials tree tree and source code display
     * 
     * @throws Exception
     */
    private void setup() throws Exception {
        setupCaatja();
        createTutorialsTree();
        sourceCodeDisplay = createSourceCodeDisplay();
    }

    private void setupCaatja() throws Exception {
        canvas = Caatja.createCanvas();
        director = new Director();
        director.initialize(CANVAS_WIDTH, CANVAS_HEIGHT, canvas);

        Caatja.addCanvas(canvas);
    }

    /**
     * Create a panel with the source code of the tutorial
     * 
     * @return
     */
    private ScrollPanel createSourceCodeDisplay() {
        ScrollPanel sourceCodeScrollPanel = new ScrollPanel();

        HTMLPanel sourceCodePanel = new HTMLPanel("- Source code -");
        sourceCodeScrollPanel.setWidget(sourceCodePanel);

        RootPanel.get().add(sourceCodeScrollPanel, 0, SOURCE_DISPLAY_Y);

        return sourceCodeScrollPanel;
    }

    /**
     * Create a tree with the names of the tutorials
     */
    private Tree createTutorialsTree() {
        Tree tutorialsTree = new Tree();

        HTML tut001 = createTutorialEntry(Tut001.class, "A", "Source code of A");
        tutorialsTree.addItem(tut001);

        HTML tut002 = createTutorialEntry(Tut021.class, "B", "Source code of B");
        tutorialsTree.addItem(tut002);
        
        HTML tut003 = createTutorialEntry(Tut022.class, "C", "Source code of C");
        tutorialsTree.addItem(tut003);

        ScrollPanel tutorialsScrollPanel = new ScrollPanel();
        tutorialsScrollPanel.setWidget(tutorialsTree);
        RootPanel.get().add(tutorialsScrollPanel, TREE_X, 0);

        return tutorialsTree;
    }

    /**
     * Create a tree entry by adding a label and a click handler on it.<br>
     * The click handler launch a tutorial.
     * 
     * @param clazz
     * @param label
     * @param source
     * @return
     */
    private HTML createTutorialEntry(final Class<?> clazz, String label, final String source) {
        HTML tutEntry = new HTML(label);
        tutEntry.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                launchTutorial(clazz, source);
            }
        });
        
        return tutEntry;
    }

    private void loadImages() {
        preloadImages();

        CaatjaImageLoader caatjaImageLoader = Caatja.getCaatjaImageLoader();
        caatjaImageLoader.loadImages(Caatja.getCaatjaImagePreloader(), new CaatjaImageLoaderCallback() {
            public void onFinishedLoading() throws Exception {
                finishShowcaseLoading();
            }
        });
    }

    /**
     * We add requested images to the preloader
     */
    private void preloadImages() {
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
     * When images are loaded, we store them
     * 
     * @throws Exception
     */
    private void finishShowcaseLoading() throws Exception {
        director.imagesCache = Caatja.getCaatjaImagePreloader().getCaatjaImages();
        director.emptyScenes();

        Caatja.loop(60);
    }

    private void clearDirector() {
        director.emptyScenes();
        director.childrenList.clear();
        director.currentScene = null;
    }

    private void setSourceDisplay(String source) {
        sourceCodeDisplay.clear();
        sourceCodeDisplay.add(new HTML(source));
    }

    /**
     * Will call the init method of the tut class given in parameter.
     * 
     * @param tut
     * @param source
     */
    private void launchTutorial(Class<?> tut, String source) {
        clearDirector();
        setSourceDisplay(source);
        try {

            if (tut == Tut001.class) {
                Tut001.init(canvas, director);
            } else if (tut == Tut021.class) {
                Tut021.init(director);
            } else if (tut == Tut022.class) {
                Tut022.init(director);
            }

        } catch (Exception e) {
            Window.alert("Cannot launch tutorial " + tut.getName());
            e.printStackTrace();
        }
    }

}
