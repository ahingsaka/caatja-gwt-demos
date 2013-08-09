package com.katspow.caatjagwtdemos.client.demos.hypernumber;

import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.image.CaatjaImageLoader;
import com.katspow.caatja.core.image.CaatjaImageLoaderCallback;
import com.katspow.caatja.core.image.CaatjaPreloader;
import com.katspow.caatja.foundation.Director;

public class HyperNumber {

    public void init() throws Exception {
        final Director director = new Director().initialize(640, 480).setClear(false);

//        final ImageResources INSTANCE = GWT.create(ImageResources.class);
//        
//        Image numsImage = new Image(INSTANCE.nums().getSafeUri());
//        Image buttonsImage = new Image(INSTANCE.buttons().getSafeUri());
//        Image madeWithImage = new Image(INSTANCE.madewith().getSafeUri());
//        Image spaceImage = new Image(INSTANCE.space().getSafeUri());
//        Image backgroundImage = new Image(INSTANCE.background().getSafeUri());
//        Image backgroundOptImage = new Image(INSTANCE.background_op().getSafeUri());
//        
//        Image cloud1Image = new Image(INSTANCE.cloud1().getSafeUri());
//        Image cloud2Image = new Image(INSTANCE.cloud2().getSafeUri());
//        Image cloud3Image = new Image(INSTANCE.cloud3().getSafeUri());
//        Image cloud4Image = new Image(INSTANCE.cloud4().getSafeUri());
//        
//        Image cloudb1Image = new Image(INSTANCE.cloudb1().getSafeUri());
//        Image cloudb2Image = new Image(INSTANCE.cloudb2().getSafeUri());
//        Image cloudb3Image = new Image(INSTANCE.cloudb3().getSafeUri());
//        Image cloudb4Image = new Image(INSTANCE.cloudb4().getSafeUri());
        
        final CaatjaPreloader preloader = Caatja.getCaatjaImagePreloader();
        
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
        
        CaatjaImageLoader caatjaImageLoader = Caatja.getCaatjaImageLoader();
        caatjaImageLoader.loadImages(preloader, new CaatjaImageLoaderCallback() {
            @Override
            public void onFinishedLoading() throws Exception {
                
                director.imagesCache = preloader.getCaatjaImages();
                
//              images.put("bricks", ImageElement.as(numsImage.getElement()));
//              images.put("buttons", ImageElement.as(buttonsImage.getElement()));
//              images.put("madewith", ImageElement.as(madeWithImage.getElement()));
//              images.put("space", ImageElement.as(spaceImage.getElement()));
//              images.put("background", ImageElement.as(backgroundImage.getElement()));
//              images.put("background_op", ImageElement.as(backgroundOptImage.getElement()));
              
//              images.put("cloud1", ImageElement.as(cloud1Image.getElement()));
//              images.put("cloud2", ImageElement.as(cloud2Image.getElement()));
//              images.put("cloud3", ImageElement.as(cloud3Image.getElement()));
//              images.put("cloud4", ImageElement.as(cloud4Image.getElement()));
              
//              images.put("cloudb1", ImageElement.as(cloudb1Image.getElement()));
//              images.put("cloudb2", ImageElement.as(cloudb2Image.getElement()));
//              images.put("cloudb3", ImageElement.as(cloudb3Image.getElement()));
//              images.put("cloudb4", ImageElement.as(cloudb4Image.getElement()));

//              director.imagesCache = images;

              GardenScene gardenScene= new GardenScene().create(director, 120);
              
              Context context= new Context().
              create(8, 12, GameScene.imageBricksH );
              
              GameScene gameScene = new GameScene().create(director, 8, 8, context);
              gardenScene.gameScene = gameScene;

              director.loop(60);
                
            }
            
        });
        
        
    }

}
