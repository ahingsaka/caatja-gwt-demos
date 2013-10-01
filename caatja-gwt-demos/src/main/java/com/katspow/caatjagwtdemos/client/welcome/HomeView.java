package com.katspow.caatjagwtdemos.client.welcome;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.core.image.CaatjaImageLoader;
import com.katspow.caatja.core.image.CaatjaImageLoaderCallback;
import com.katspow.caatja.core.image.CaatjaPreloader;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.ui.TextActor;
import com.katspow.caatjagwtdemos.client.welcome.hypernumber.HyperNumber;
import com.katspow.caatjagwtdemos.client.welcome.showcase.Showcase;
import com.katspow.caatjagwtdemos.client.welcome.tutorials.Tutorials;

/**
 * This defines the first view of the application.<br>
 * User can choose between different demos in this view.<br>
 * When he chooses one of them, the canvas part of the view is updated and the
 * content replaced.
 * 
 */
public class HomeView extends Composite {

    // MESSAGES
    private static final String NOT_YET_IMPLEMENTED = "Not yet implemented !";
    
    // CANVAS DIMENSIONS
    private static final int CANVAS_HEIGHT = 500;
    private static final int CANVAS_WIDTH = 680;
    
    // MENU (widget on the right side) DIMENSIONS
    private static final String STACK_MENU_HEIGHT = CANVAS_HEIGHT + "px";
    private static final String STACK_MENU_WIDTH = "250px";
    private static final int STACK_PANEL_X = CANVAS_WIDTH;
    
    private StackPanel demosStackPanel;

    // EACH STACK ENTRY HAS A LABEL AND A DESCRIPTION GIVEN BY THIS ENUM
    private enum Demo {
        HOME("Home", "Welcome to the CAATJA demos ! <br>Please select an entry below to watch them in action !<p>This is a BETA version, some features are missing"),
        SHOWCASE("Showcase", "With the showcase, you'll have an overview of the features of Caatja"),
        DEMOS("Demos", "A more precise demo for each feature (NOT AVAILABLE)"),
        DEMOS_WITH_SOURCE("Tutorials", "With the tutorials, you'll learn to use caatja (NOT AVAILABLE)"),
        HYPERNUMBER("Hypernumber", "Hypernumber is a small game where you have to choose digits to do sums");
       
        private String label;
        private String desc;
       
        private Demo(String label, String desc) {
            this.label = label;
            this.desc = desc;
        }
       
        public String getLabel() {
            return label;
        }
       
        public String getDesc() {
            return desc;
        }
    }

    // Only ONE director for the WHOLE application
    private final Director director = new Director();

    // This view is associated with ONE scene for loading images and the intro
    private final HomeScene homeScene = new HomeScene();
    
    // The canvas, all animations will be shown here
    private final CaatjaCanvas canvas = Caatja.createCanvas();
    
    // A text is displayed when images are being loaded
    private TextActor loadingText;

    public HomeView() throws Exception {
        createStackMenu();
        setupCaatja();
        loadImages();
       
        initWidget(new SimplePanel());
        RootPanel.get().add(demosStackPanel, STACK_PANEL_X, 0);
    }

    /**
     * Director is initialized with the homescene and the loading text.<br>
     * The animation loop starts here.
     * 
     * @throws Exception
     */
    private void setupCaatja() throws Exception {
       
        director.initialize(CANVAS_WIDTH, CANVAS_HEIGHT, canvas);
        director.addScene(homeScene);
        homeScene.load(director);

        loadingText = createLoadingText();

        Caatja.addCanvas(canvas);
        Caatja.loop(60);
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
       
        // Hypernumber images
        preloader.addImage("bricks", "nums.png");
        preloader.addImage("buttons", "buttons.png");
        preloader.addImage("madewith", "madewith.png");
        preloader.addImage("space", "space2.jpg");
        preloader.addImage("background", "fondo.jpg");
        preloader.addImage("background_op", "fondo_opciones.jpg");
        preloader.addImage("cloud1", "nube1.png");
        preloader.addImage("cloud2", "nube2.png");
        preloader.addImage("cloud3", "nube3.png");
        preloader.addImage("cloud4", "nube4.png");

        preloader.addImage("cloudb1", "nubefondo1.png");
        preloader.addImage("cloudb2", "nubefondo2.png");
        preloader.addImage("cloudb3", "nubefondo3.png");
        preloader.addImage("cloudb4", "nubefondo4.png");
       
    }
   
    /**
     * Once images are "preloaded", we call the image service from the server to
     * get them
     */
    private void loadImages() {
        preloadImages();

        CaatjaImageLoader caatjaImageLoader = Caatja.getCaatjaImageLoader();
        caatjaImageLoader.loadImages(Caatja.getCaatjaImagePreloader(), new CaatjaImageLoaderCallback() {
            @Override
            public void onFinishedLoading() throws Exception {
                finishImageLoading();
            }
        });

    }
   
    /**
     * When images are loaded, we store them and we can load all the showcase
     * scenes.
     *
     * @throws Exception
     */
    private void finishImageLoading() throws Exception {
        director.imagesCache = Caatja.getCaatjaImagePreloader().getCaatjaImages();
       
        demosStackPanel.setVisible(true);
        
        homeScene.removeChild(loadingText);
        homeScene.showIntroduction();
        
    }

    /**
     * The "Loading ..." text to be displayed when the images are being loaded,
     * is created here.<br>
     * The text is centered in the canvas.
     * 
     * @return the text "Loading ..."
     * @throws Exception
     */
    private TextActor createLoadingText() throws Exception {
        TextActor loading = new TextActor();
        loading.setFont("30px sans-serif").
            setTextBaseline("top").
            setText("Loading ...").
            calcTextSize(director).
            setTextFillStyle("white");

        loading.setLocation((director.canvas.getCoordinateSpaceWidth() - loading.width) / 2,
                (director.canvas.getCoordinateSpaceHeight()) / 2);

        homeScene.addChild(loading);
        
        return loading;
    }

    /**
     * Creates a menu with redirection entries to the demos.
     */
    private void createStackMenu() {
        demosStackPanel = new StackPanel();
        demosStackPanel.setHeight(STACK_MENU_HEIGHT);
        demosStackPanel.setWidth(STACK_MENU_WIDTH);
        demosStackPanel.setVisible(false);
       
        createStackEntryWidget(Demo.HOME);
        createStackEntryWidget(Demo.SHOWCASE);
        createStackEntryWidget(Demo.DEMOS);
        createStackEntryWidget(Demo.DEMOS_WITH_SOURCE);
        createStackEntryWidget(Demo.HYPERNUMBER);
    }
   
    /**
     * Returns a button that redirects to a demo when clicked.
     * 
     * @param demo
     *            the demo to be launched
     * @return a redirection button
     */
    private Button createLaunchLink(final Demo demo) {
        Button launch = new Button("Launch");
        
        launch.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                loadDemo(demo);
            }
        });
        
        return launch;
    }
    
    /**
     * Returns a vertical panel with 2 widgets : a label and a redirection
     * button.<br>
     * The redirection button appears only for the showcase et hypernumber
     * demos.<br>
     * 
     * @param demo
     *            the demo to be launched
     * @return a vertical panel with 2 widgets at most
     */
    private VerticalPanel createStackEntryWidget(final Demo demo) {
        VerticalPanel vp = new VerticalPanel();
        vp.add(new HTML(demo.getDesc()));
        
        if (demo == Demo.SHOWCASE || demo == Demo.HYPERNUMBER) {
            vp.add(new HTML("<br>"));
            vp.add(createLaunchLink(demo));
        }

        // FIXME Move this !
        demosStackPanel.add(vp);
        demosStackPanel.setStackText(demo.ordinal(), demo.getLabel());
        
        return vp;
    }
   
    /**
     * Called when the user clicks on one of the "Launch" button.
     * 
     * @param demo
     *            the demo to be launched
     */
    private void loadDemo(Demo demo) {

        try {

            switch (demo) {
            case SHOWCASE:
                Showcase.start(director);
                break;

            case HYPERNUMBER:
                HyperNumber.start(director);
                break;

            case DEMOS_WITH_SOURCE:
                new Tutorials().start();
                break;
               
            case DEMOS:
                Window.alert(NOT_YET_IMPLEMENTED);
                break;
               
            case HOME:
                director.setScene(director.getSceneIndex(homeScene));
                break;

            default:
                break;
            }
        } catch (Exception e) {
            Window.alert("Could not load " + demo.name());
            e.printStackTrace();
        }
    }
}
