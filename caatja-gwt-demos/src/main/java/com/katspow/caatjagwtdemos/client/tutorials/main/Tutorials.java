package com.katspow.caatjagwtdemos.client.tutorials.main;

import java.util.Map;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.core.canvas.CaatjaImage;
import com.katspow.caatja.core.image.CaatjaImageLoader;
import com.katspow.caatja.core.image.CaatjaImageLoaderCallback;
import com.katspow.caatja.core.image.CaatjaPreloader;
import com.katspow.caatjagwt.client.CaatjaGwtCanvas;
import com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut01.Tut101;
import com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut01.Tut102;
import com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut02.Tut21;
import com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut02.Tut22;
import com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut02.Tut23;
import com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut02.Tut24;
import com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut02.Tut25;
import com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut02.Tut26;
import com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut02.Tut27;
import com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut02.Tut28;
import com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut02.Tut29;
import com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut03.Tut3;
import com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut03.Tut31;
import com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut04.Tut44;
import com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut04.Tut46p;
import com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut04.Tut48;
import com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut05.Tut52;
import com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut06.Tut6;
import com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut06.Tut61;
import com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut06.Tut62;
import com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut06.Tut63;
import com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut07.Tut7;
import com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut07.Tut71;
import com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut08.Tut83;
import com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut08.Tut84;
import com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut08.Tut85;
import com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut09.Tut91;
import com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut09.Tut92;
import com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut09.Tut94;
import com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut09.Tut95;
import com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut09.Tut96;
import com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut09.Tut98;
import com.katspow.caatjagwtdemos.client.tutorials.main.tut.tut09.Tut99;
import com.katspow.caatjagwtdemos.client.welcome.demos.Demo1;
import com.katspow.caatjagwtdemos.client.welcome.demos.Demo10;
import com.katspow.caatjagwtdemos.client.welcome.demos.Demo11;
import com.katspow.caatjagwtdemos.client.welcome.demos.Demo12;
import com.katspow.caatjagwtdemos.client.welcome.demos.Demo121;
import com.katspow.caatjagwtdemos.client.welcome.demos.Demo14;
import com.katspow.caatjagwtdemos.client.welcome.demos.Demo15;
import com.katspow.caatjagwtdemos.client.welcome.demos.Demo16;
import com.katspow.caatjagwtdemos.client.welcome.demos.Demo17;
import com.katspow.caatjagwtdemos.client.welcome.demos.Demo18;
import com.katspow.caatjagwtdemos.client.welcome.demos.Demo19;
import com.katspow.caatjagwtdemos.client.welcome.demos.Demo2;
import com.katspow.caatjagwtdemos.client.welcome.demos.Demo3;
import com.katspow.caatjagwtdemos.client.welcome.demos.Demo4;
import com.katspow.caatjagwtdemos.client.welcome.demos.Demo5;
import com.katspow.caatjagwtdemos.client.welcome.demos.Demo6;
import com.katspow.caatjagwtdemos.client.welcome.demos.Demo7;
import com.katspow.caatjagwtdemos.client.welcome.demos.Demo8;
import com.katspow.caatjagwtdemos.client.welcome.demos.Demo9;

public class Tutorials {
	
	private void start(final CaatjaCanvas canvas, final Map<String, CaatjaImage> images) {
		
//      Image numsImage = new Image(INSTANCE.nums().getSafeUri());
//      Image botonesImage = new Image(INSTANCE.botones().getSafeUri());
//      Image fish1Image = new Image(INSTANCE.fish().getSafeUri());
//      Image fish2Image = new Image(INSTANCE.fish2().getSafeUri());
//      Image fish3Image = new Image(INSTANCE.fish3().getSafeUri());
//      Image fish4Image = new Image(INSTANCE.fish4().getSafeUri());
//      Image fish5Image = new Image(INSTANCE.fish5().getSafeUri());
//      Image fish6Image = new Image(INSTANCE.fish6().getSafeUri());
//      Image logoImage = new Image(INSTANCE.logo().getSafeUri());
//      Image starsImages = new Image(INSTANCE.stars().getSafeUri());
//      Image normalModeImage = new Image(INSTANCE.normalMode().getSafeUri());
//      Image numbersImage = new Image(INSTANCE.numbers().getSafeUri());
//      Image numbers5Image = new Image(INSTANCE.numbers5().getSafeUri());

//      images.put("nums", ImageElement.as(numsImage.getElement()));
//      images.put("botones", ImageElement.as(botonesImage.getElement()));
//      images.put("fish0", ImageElement.as(fish1Image.getElement()));
//      images.put("fish1", ImageElement.as(fish2Image.getElement()));
//      images.put("fish2", ImageElement.as(fish3Image.getElement()));
//      images.put("fish3", ImageElement.as(fish4Image.getElement()));
//      images.put("fish4", ImageElement.as(fish5Image.getElement()));
//      images.put("fish5", ImageElement.as(fish6Image.getElement()));
//      images.put("logo", ImageElement.as(logoImage.getElement()));
//      images.put("stars", ImageElement.as(starsImages.getElement()));
//      images.put("b", ImageElement.as(normalModeImage.getElement()));
//      images.put("numbers", ImageElement.as(numbersImage.getElement()));
//      images.put("numbers5", ImageElement.as(numbers5Image.getElement()));

      Tree menuTree = new Tree();
      
      TreeItem demosItem = new TreeItem();
      demosItem.setText("Demos");
      
      final TreeItem pathManagementItem = new TreeItem();
      pathManagementItem.setText("Path management");
      final TreeItem fishPondItem = new TreeItem();
      fishPondItem.setText("Fish pond");
      final TreeItem spritesOnPathItem = new TreeItem();
      spritesOnPathItem.setText("Sprites on path");
      final TreeItem dragAndDropItem = new TreeItem();
      dragAndDropItem.setText("Drag and Drop");
      final TreeItem textOnPathItem = new TreeItem();
      textOnPathItem.setText("Text on path");
      final TreeItem accelerometerDemoItem = new TreeItem();
      accelerometerDemoItem.setText("Accelerometer");
      final TreeItem transformAnchorItem = new TreeItem();
      transformAnchorItem.setText("Transform anchors");
      final TreeItem transformHierarchyItem = new TreeItem();
      transformHierarchyItem.setText("Transform hierarchy");
      final TreeItem interactivityItem = new TreeItem();
      interactivityItem.setText("Interactivity");
      final TreeItem proceduralGrassItem = new TreeItem();
      proceduralGrassItem.setText("Procedural Grass");
      final TreeItem collisionDetectionItem = new TreeItem();
      collisionDetectionItem.setText("Collision detection");
      final TreeItem box2dItem = new TreeItem();
      box2dItem.setText("Box 2d");
      final TreeItem sceneTransitionItem = new TreeItem();
      sceneTransitionItem.setText("Scene transition");
      final TreeItem timersDemoItem = new TreeItem();
      timersDemoItem.setText("Timers");
      final TreeItem spriteImageItem = new TreeItem();
      spriteImageItem.setText("Sprite Image");
      final TreeItem maskItem = new TreeItem();
      maskItem.setText("Masking");
      final TreeItem videoItem = new TreeItem();
      videoItem.setText("Video");
      final TreeItem keyItem = new TreeItem();
      keyItem.setText("Keyboard management");
      final TreeItem quadtreeCollisionItem = new TreeItem();
      quadtreeCollisionItem.setText("Quadtree based collision detection");
      
      demosItem.addItem(pathManagementItem);
      demosItem.addItem(fishPondItem);
      demosItem.addItem(spritesOnPathItem);
      demosItem.addItem(dragAndDropItem);
      demosItem.addItem(textOnPathItem);
      demosItem.addItem(accelerometerDemoItem);
      demosItem.addItem(transformAnchorItem);
      demosItem.addItem(transformHierarchyItem);
      demosItem.addItem(interactivityItem);
      demosItem.addItem(proceduralGrassItem);
      demosItem.addItem(collisionDetectionItem);
      demosItem.addItem(box2dItem);
      demosItem.addItem(sceneTransitionItem);
      demosItem.addItem(timersDemoItem);
      demosItem.addItem(spriteImageItem);
      demosItem.addItem(maskItem);
      demosItem.addItem(videoItem);
      demosItem.addItem(keyItem);
      demosItem.addItem(quadtreeCollisionItem);
      
      TreeItem tutorialsItem = new TreeItem();
      tutorialsItem.setText("Tutorials");
      
      TreeItem tut2Tree = new TreeItem();
      tut2Tree.setText("Tutorial 2");
      
      final TreeItem actorEventsItem = new TreeItem();
      actorEventsItem.setText("Actor events");
      final TreeItem actorLifecycleItem = new TreeItem();
      actorLifecycleItem.setText("Actor lifecycle");
      final TreeItem actorTransformationsItem = new TreeItem();
      actorTransformationsItem.setText("Actor transformations");
      final TreeItem actorBehaviorsItem = new TreeItem();
      actorBehaviorsItem.setText("Actor behaviors");
      final TreeItem actorBackgroundItem = new TreeItem();
      actorBackgroundItem.setText("Actor background");
      final TreeItem actorPaintItem = new TreeItem();
      actorPaintItem.setText("Actor paint method");
      final TreeItem gettingStartedItem = new TreeItem();
      gettingStartedItem.setText("Getting started");
      final TreeItem clipItem = new TreeItem();
      clipItem.setText("Clip");
      final TreeItem actorAsButtonItem = new TreeItem();
      actorAsButtonItem.setText("Actor as button");
      
      tut2Tree.addItem(actorEventsItem);
      tut2Tree.addItem(actorLifecycleItem);
      tut2Tree.addItem(actorTransformationsItem);
      tut2Tree.addItem(actorBehaviorsItem);
      tut2Tree.addItem(actorBackgroundItem);
      tut2Tree.addItem(actorPaintItem);
      tut2Tree.addItem(gettingStartedItem);
      tut2Tree.addItem(clipItem);
      tut2Tree.addItem(actorAsButtonItem);
      
      TreeItem tut3Tree = new TreeItem();
      tut3Tree.setText("Tutorial 3");
      
      final TreeItem addingActorsToActorContainerItem = new TreeItem();
      addingActorsToActorContainerItem.setText("Adding actors to Actor Container");
      final TreeItem actorContainerItem = new TreeItem();
      actorContainerItem.setText("Actor Container");
      
      tut3Tree.addItem(addingActorsToActorContainerItem);
      tut3Tree.addItem(actorContainerItem);
      
      TreeItem tut4Tree = new TreeItem();
      tut4Tree.setText("Tutorial 4");
      
      final TreeItem switchingItem = new TreeItem();
      switchingItem.setText("Director Switching scene");
      final TreeItem accelerometerItem = new TreeItem();
      accelerometerItem.setText("Director accelerometer events");
      final TreeItem resizeItem = new TreeItem();
      resizeItem.setText("Resize event example");
      
      tut4Tree.addItem(switchingItem);
      tut4Tree.addItem(resizeItem);
      tut4Tree.addItem(accelerometerItem);
      
      TreeItem tut5Tree = new TreeItem();
      tut5Tree.setText("Tutorial 5");
      
      final TreeItem timersItem = new TreeItem();
      timersItem.setText("Timers");
      
      tut5Tree.addItem(timersItem);
      
      TreeItem tut6Tree = new TreeItem();
      tut6Tree.setText("Tutorial 6");
      
      final TreeItem behaviorLifeCycleItem = new TreeItem();
      behaviorLifeCycleItem.setText("Behavior life cycle");
      final TreeItem outOfTheBoxBehaviorsItem = new TreeItem();
      outOfTheBoxBehaviorsItem.setText("Out of the box behaviors");
      final TreeItem containerBehaviorsItem = new TreeItem();
      containerBehaviorsItem.setText("Container Behaviors");
      final TreeItem behaviorsItem = new TreeItem();
      behaviorsItem.setText("Behaviors");
      
      tut6Tree.addItem(behaviorLifeCycleItem);
      tut6Tree.addItem(outOfTheBoxBehaviorsItem);
      tut6Tree.addItem(containerBehaviorsItem);
      tut6Tree.addItem(behaviorsItem);
      
      TreeItem tut7Tree = new TreeItem();
      tut7Tree.setText("Tutorial 7");
      
      final TreeItem interpolatorsItem = new TreeItem();
      interpolatorsItem.setText("Interpolators");
      final TreeItem interpolatorItem = new TreeItem();
      interpolatorItem.setText("Interpolator");
      
      tut7Tree.addItem(interpolatorsItem);
      tut7Tree.addItem(interpolatorItem);
      
      TreeItem tut8Tree = new TreeItem();
      tut8Tree.setText("Tutorial 8");
      
      final TreeItem complexPathItem = new TreeItem();
      complexPathItem.setText("Path - complex paths");
      final TreeItem traversingPathItem = new TreeItem();
      traversingPathItem.setText("Path - traversing");
      final TreeItem pathItem = new TreeItem();
      pathItem.setText("Path - as an Interpolator instance");
      
      tut8Tree.addItem(complexPathItem);
      tut8Tree.addItem(traversingPathItem);
      tut8Tree.addItem(pathItem);
      
      
      TreeItem tut9Tree = new TreeItem();
      tut9Tree.setText("Tutorial 9");

      final TreeItem shapeItem = new TreeItem();
      shapeItem.setText("Shape Actor");
      final TreeItem starItem = new TreeItem();
      starItem.setText("Star Actor");
      final TreeItem imageItem = new TreeItem();
      imageItem.setText("Image Actor");
      final TreeItem spriteActorItem = new TreeItem();
      spriteActorItem.setText("Sprite Actor");
      final TreeItem buttonItem = new TreeItem();
      buttonItem.setText("Button");
      final TreeItem pathActorItem = new TreeItem();
      pathActorItem.setText("PathActor");
      final TreeItem dockItem = new TreeItem();
      dockItem.setText("Dock");
      
      tut9Tree.addItem(shapeItem);
      tut9Tree.addItem(starItem);
      tut9Tree.addItem(imageItem);
      tut9Tree.addItem(spriteActorItem);
      tut9Tree.addItem(buttonItem);
      tut9Tree.addItem(pathActorItem);
      tut9Tree.addItem(dockItem);
      
      TreeItem tut10Tree = new TreeItem();
      tut10Tree.setText("Tutorial 10");
      
      final TreeItem audioItem = new TreeItem();
      audioItem.setText("Helpers Image Utils");
      final TreeItem imageUtilsItem = new TreeItem();
      imageUtilsItem.setText("Helpers Image Utils");
      
      tut10Tree.addItem(audioItem);
      tut10Tree.addItem(imageUtilsItem);
      
      tutorialsItem.addItem(tut2Tree);
      tutorialsItem.addItem(tut3Tree);
      tutorialsItem.addItem(tut4Tree);
      tutorialsItem.addItem(tut5Tree);
      tutorialsItem.addItem(tut6Tree);
      tutorialsItem.addItem(tut7Tree);
      tutorialsItem.addItem(tut8Tree);
      tutorialsItem.addItem(tut9Tree);
      tutorialsItem.addItem(tut10Tree);
      
      menuTree.addItem(demosItem);
      menuTree.addItem(tutorialsItem);

      menuTree.addSelectionHandler(new SelectionHandler<TreeItem>() {
          @Override
          public void onSelection(SelectionEvent<TreeItem> event) {
              try {

                  TreeItem selectedItem = event.getSelectedItem();
                  
				if (selectedItem.equals(dockItem)) {
                      new Tut99().start(canvas, images);
                  } else if (selectedItem.equals(pathActorItem)) {
                      new Tut98().start(canvas, images);
                  } else if (selectedItem.equals(buttonItem)) {
                      new Tut96().start(canvas, images);
                  } else if (selectedItem.equals(spriteActorItem)) {
                      new Tut95().start(canvas, images);
                  } else if (selectedItem.equals(imageItem)) {
                      new Tut94().start(canvas, images);
                  } else if (selectedItem.equals(starItem)) {
                      new Tut92().start(canvas, images);
                  } else if (selectedItem.equals(shapeItem)) {
                      new Tut91().start(canvas, images);
                  } else if (selectedItem.equals(pathItem)) {
                      new Tut85().start(canvas, images);
                  } else if (selectedItem.equals(traversingPathItem)) {
                      new Tut84().start(canvas);
                  } else if (selectedItem.equals(complexPathItem)) {
                      new Tut83().start(canvas);
                  } else if (selectedItem.equals(interpolatorItem)) {
                      new Tut7().start(canvas);
                  } else if (selectedItem.equals(interpolatorsItem)) {
                      new Tut71().start(canvas);
                  } else if (selectedItem.equals(behaviorsItem)) {
                      new Tut6().start(canvas);
                  } else if (selectedItem.equals(containerBehaviorsItem)) {
                      new Tut63().start(canvas);
                  } else if (selectedItem.equals(outOfTheBoxBehaviorsItem)) {
                      new Tut62().start(canvas);
                  } else if (selectedItem.equals(behaviorLifeCycleItem)) {
                      new Tut61().start(canvas);
                  } else if (selectedItem.equals(timersItem)) {
                      new Tut52().start(canvas);
                  } else if (selectedItem.equals(accelerometerItem)) {
                      new Tut48().start(canvas);
                  } else if (selectedItem.equals(resizeItem)) {
                      new Tut46p().start(canvas, images);
                  } else if (selectedItem.equals(switchingItem)) {
                      new Tut44().start(canvas);
                  } else if (selectedItem.equals(actorContainerItem)) {
                      new Tut3().start(canvas);
                  } else if (selectedItem.equals(addingActorsToActorContainerItem)) {
                      new Tut31().start(canvas);
                  } else if (selectedItem.equals(actorAsButtonItem)) {
                      new Tut29().start(canvas, images);
                  } else if (selectedItem.equals(clipItem)) {
                      new Tut28().start(canvas);
                  } else if (selectedItem.equals(gettingStartedItem)) {
                      new Tut27().start(canvas);
                  } else if (selectedItem.equals(actorPaintItem)) {
                      new Tut26().start(canvas);
                  } else if (selectedItem.equals(actorBackgroundItem)) {
                      new Tut25().start(canvas, images);
                  } else if (selectedItem.equals(actorBehaviorsItem)) {
                      new Tut24().start(canvas);
                  } else if (selectedItem.equals(actorTransformationsItem)) {
                      new Tut23().start(canvas);
                  } else if (selectedItem.equals(actorLifecycleItem)) {
                      new Tut22().start(canvas);
                  } else if (selectedItem.equals(actorEventsItem)) {
                      new Tut21().start(canvas);
                  } else if (selectedItem.equals(imageUtilsItem)) {
                      new Tut102().start(canvas, images);
                  } else if (selectedItem.equals(audioItem)) {
                      new Tut101().start(canvas);
                  } else if (selectedItem.equals(interactivityItem)) {
                      new Demo9().start(canvas, images);
                  } else if (selectedItem.equals(transformHierarchyItem)) {
                      new Demo8().start(canvas);
                  } else if (selectedItem.equals(transformAnchorItem)) {
                      new Demo7().start(canvas, images);
                  } else if (selectedItem.equals(accelerometerDemoItem)) {
                      new Demo6().start(canvas);
                  } else if (selectedItem.equals(textOnPathItem)) {
                      new Demo5().start(canvas, images);
                  } else if (selectedItem.equals(dragAndDropItem)) {
                      new Demo4().start(canvas);
                  } else if (selectedItem.equals(spritesOnPathItem)) {
                      new Demo3().start(canvas, images);
                  } else if (selectedItem.equals(fishPondItem)) {
                      new Demo2().start(canvas, images);
                  } else if (selectedItem.equals(maskItem)) {
                      new Demo16().start(canvas, images);
                  } else if (selectedItem.equals(spriteImageItem)) {
                      new Demo15().start(canvas, images);
                  } else if (selectedItem.equals(timersDemoItem)) {
                      new Demo14().start(canvas);
                  } else if (selectedItem.equals(box2dItem)) {
                      new Demo12().start(canvas);
                  } else if (selectedItem.equals(sceneTransitionItem)) {
                      new Demo121().start(canvas);
                  } else if (selectedItem.equals(collisionDetectionItem)) {
                      new Demo11().start(canvas);
                  } else if (selectedItem.equals(proceduralGrassItem)) {
                      new Demo10().start(canvas);
                  } else if (selectedItem.equals(pathManagementItem)) {
                      new Demo1().start(canvas, images);
                  } else if (selectedItem.equals(videoItem)) {
                      new Demo17().start(canvas);
                  } else if (selectedItem.equals(keyItem)) {
                      new Demo18().start(canvas);
                  } else if (selectedItem.equals(quadtreeCollisionItem)) {
                      new Demo19().start(canvas);
                  }

              } catch (Exception e) {
                  e.printStackTrace();
              }
          }
      });

      HorizontalPanel hp = new HorizontalPanel();
      hp.add(menuTree);
      hp.add(((CaatjaGwtCanvas)canvas).canvas);

      RootPanel.get().add(hp);
		
	}

    public void init() {

    	
    	final CaatjaPreloader preloader = Caatja.getCaatjaImagePreloader();
    	preloader.addImage("nums", "nums.png");
        preloader.addImage("botones", "botones.png");
        preloader.addImage("fish0", "anim1.png");
        preloader.addImage("fish1", "anim2.png");
        preloader.addImage("fish2", "anim3.jpg");
        preloader.addImage("fish3", "anim4.png");
        preloader.addImage("fish4", "anim5.png");
        preloader.addImage("fish5", "anim6.png");
        preloader.addImage("logo", "logo.png");
        preloader.addImage("stars", "stars.png");
        preloader.addImage("b", "normal_mode.png");
        preloader.addImage("numbers", "numbers.png");
        preloader.addImage("numbers5", "numbers-demo5.png");
        
        final CaatjaCanvas canvas = Caatja.createCanvas();;
        canvas.setCoordinateSpaceWidth(1024);
        canvas.setCoordinateSpaceHeight(768);
        
        CaatjaImageLoader caatjaImageLoader = Caatja.getCaatjaImageLoader();
        caatjaImageLoader.loadImages(preloader, new CaatjaImageLoaderCallback() {
            @Override
            public void onFinishedLoading() throws Exception {
                Caatja.addCanvas(canvas);
                start(canvas, preloader.getCaatjaImages());
            }
        });


    }

}
