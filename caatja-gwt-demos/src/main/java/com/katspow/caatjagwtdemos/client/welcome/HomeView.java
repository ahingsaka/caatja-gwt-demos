package com.katspow.caatjagwtdemos.client.welcome;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.StackPanel;
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

public class HomeView extends Composite {

    private static final String NOT_YET_IMPLEMENTED = "Not yet implemented !";
    private static final int CANVAS_HEIGHT = 500;
    private static final int CANVAS_WIDTH = 680;
    private static final int STACK_PANEL_X = CANVAS_WIDTH;
   
    private static final String HEIGHT = "500px";
    private static final String WIDTH = "680px";
   
    private static final String WELCOME_TEXT = "Welcome to the CAATJA-GWT demos !<br>"
            + "Choose one of the examples on the left<br> " + "by clicking on the icon";

    private enum Demo {
        HOME("Home", "Welcome to the CAATJA demos ! <br>Please select an entry below to watch them in action !<br>(This is a BETA version)"),
        SHOWCASE("Showcase", "With the showcase, you'll have an overview of the features of Caatja"),
        DEMOS("Demos", "A more precise demo for each feature"),
        DEMOS_WITH_SOURCE("Tutorials", "With the tutorials, you'll learn to use caatja"),
        HYPERNUMBER("Hypernumber", "Hypernumber is a small game, it is in beta but playable<p>Click to Launch");
       
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


    private Director director;
    private HomeScene welcomeScene;
   
    private StackPanel demosStackPanel;
    private CaatjaCanvas canvas;

    public HomeView() throws Exception {
       
        createStackMenu();
        setupCaatja();
        loadImages();
       
        initWidget(new SimplePanel());
        RootPanel.get().add(demosStackPanel, STACK_PANEL_X, 0);

    }

    private void setupCaatja() throws Exception {
       
        canvas = Caatja.createCanvas();
        welcomeScene = new HomeScene();
        director = new Director();

        director.initialize(CANVAS_WIDTH, CANVAS_HEIGHT, canvas);
        director.addScene(welcomeScene);
//        director.setScene(0);

        welcomeScene.load(director);

        createLoadingText();

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
       
        //director.emptyScenes();
       
        demosStackPanel.setVisible(true);
//        loadShowcaseScenes();
    }

    private void createLoadingText() throws Exception {
        TextActor loading = new TextActor();
        loading.setFont("30px sans-serif").setTextBaseline("top").setText("Loading ...").calcTextSize(director)
                .setTextFillStyle("white");

        loading.setLocation((director.canvas.getCoordinateSpaceWidth() - loading.width) / 2,
                (director.canvas.getCoordinateSpaceHeight()) / 2);

        welcomeScene.addChild(loading);
    }

    private void createGridMenu() {
        FlexTable grid = new FlexTable();
        grid.setSize(WIDTH, HEIGHT);

        grid.setText(0, 0, "CAATJA-GWT demos");
        grid.getFlexCellFormatter().setColSpan(0, 0, 3);
        grid.getFlexCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

        HTML showcase = createHTMLEntry(Demo.SHOWCASE);
        HTML demos = createHTMLEntry(Demo.DEMOS);
        HTML demosWithSourceCode = createHTMLEntry(Demo.DEMOS_WITH_SOURCE);
        HTML hypernumber = createHTMLEntry(Demo.HYPERNUMBER);

        grid.setWidget(1, 0, showcase);
        grid.setWidget(2, 0, demos);
        grid.setWidget(3, 0, demosWithSourceCode);
        grid.setWidget(4, 0, hypernumber);

        grid.setWidget(1, 2, new HTML(WELCOME_TEXT));
        grid.getFlexCellFormatter().setColSpan(1, 2, 2);
        grid.getFlexCellFormatter().setRowSpan(1, 2, 4);
        grid.getFlexCellFormatter().setHorizontalAlignment(1, 2, HasHorizontalAlignment.ALIGN_CENTER);
        grid.getFlexCellFormatter().setVerticalAlignment(1, 2, HasVerticalAlignment.ALIGN_MIDDLE);
    }

    private void createStackMenu() {
        demosStackPanel = new StackPanel();
       
        createHTMLStackEntry(Demo.HOME);
        createHTMLStackEntry(Demo.SHOWCASE);
        createHTMLStackEntry(Demo.DEMOS);
        createHTMLStackEntry(Demo.DEMOS_WITH_SOURCE);
        createHTMLStackEntry(Demo.HYPERNUMBER);
       
//        demosStackPanel.add(new HTML("Welcome to the CAATJA demos ! <br>Please select an entry below to watch them in action !"), "Home");
//        demosStackPanel.add(new HTML("With the showcase, you'll have an overview of the features of Caatja"), "Showcase");
//        demosStackPanel.add(new HTML("A more precise demo for each feature"), "Demos");
//        demosStackPanel.add(new HTML("With the tutorials, you'll learn to use caatja"), "Tutorials");
//        demosStackPanel.add(new HTML("Hypernumber is a small game, it is in beta but playable"), "Hypernumber (BETA)");
       
        demosStackPanel.setHeight("500px");
        demosStackPanel.setWidth("250px");
        demosStackPanel.setVisible(false);
       
    }
   
    private HTML createHTMLEntry(final Demo demo) {
        HTML htmlEntry = new HTML(demo.getLabel());
        htmlEntry.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                loadDemo(demo);
            }
        });
       
        return htmlEntry;
    }
   
    private HTML createHTMLStackEntry(final Demo demo) {
        HTML htmlEntry = new HTML(demo.getDesc());
        demosStackPanel.add(htmlEntry);
        demosStackPanel.setStackText(demo.ordinal(), demo.getLabel());
       
        htmlEntry.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                loadDemo(demo);
            }
        });
       
        return htmlEntry;
    }
   

    private void loadDemo(Demo demo) {

        try {
            //WelcomeView.this.setVisible(false);

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
                director.setScene(director.getSceneIndex(welcomeScene));
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
