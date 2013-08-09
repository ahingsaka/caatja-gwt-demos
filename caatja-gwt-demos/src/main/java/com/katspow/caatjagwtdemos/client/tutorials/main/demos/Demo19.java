package com.katspow.caatjagwtdemos.client.tutorials.main.demos;

import java.util.ArrayList;
import java.util.List;

import com.katspow.caatja.CAATKeyListener;
import com.katspow.caatja.behavior.Interpolator;
import com.katspow.caatja.behavior.PathBehavior;
import com.katspow.caatja.behavior.RotateBehavior;
import com.katspow.caatja.core.CAAT;
import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.core.canvas.CaatjaContext2d;
import com.katspow.caatja.event.CAATKeyEvent;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;
import com.katspow.caatja.foundation.image.SpriteImage;
import com.katspow.caatja.foundation.timer.Callback;
import com.katspow.caatja.foundation.timer.TimerTask;
import com.katspow.caatja.foundation.ui.StarActor;
import com.katspow.caatja.math.AABB;
import com.katspow.caatja.math.Rectangle;
import com.katspow.caatja.modules.collision.QuadTree;
import com.katspow.caatja.modules.collision.SpatialHash;
import com.katspow.caatja.modules.collision.SpatialHashCallback;
import com.katspow.caatja.modules.collision.SpatialHashObject;
import com.katspow.caatja.pathutil.Path;

public class Demo19 {
    
    int[] keys=               new int[]{0,0,0,0};
    double prevTime=           -1;
    int pixelsPerSecond=    85;
    QuadTree collision=          null;
    QuadTree entitiesCollision=  null;
    Actor hero=               null;
    Rectangle r0=                 new Rectangle();
    Rectangle r1=                 new Rectangle();
    
    List<AABB> enemies = null;
    SpatialHash hash;

    int levelW= 24;
    int levelH= 15;
    int TW=32;
    int TH=32;

    public void start(CaatjaCanvas canvas) throws Exception {
        Director director= new Director().
                initialize( 800,500, canvas).
                setClear( Director.CLEAR_DIRTY_RECTS );    //  enable dirty rects cleaning
        
        hash= new SpatialHash().initialize((int) director.width, (int) director.height, 10, 16 );

        Scene scene= director.createScene();
        scene.setFillStyle( "#fff" );

        __createLevel( director, scene );
        __createHero( scene );
        __createEnemies( director, scene );
        __setKeys( scene, hero );

        Caatja.loop(60);
        
    }
    
    public void __setKeys(Scene scene, final Actor selected ) {
        /**
         * This timer makes the process to increment actor position based on elapsed time.
         * it will move pixelsPerSecond pixels on any direction.
         */
        scene.createTimer(scene.time, Double.MAX_VALUE, null, new Callback() {
            @Override
            public void call(double time, double ttime, TimerTask timerTask) {
                double ottime= ttime;
                if ( -1!=prevTime ) {
                    ttime-= prevTime;

                    int xinc= (keys[1]-keys[0]);
                    int yinc= (keys[3]-keys[2]);

                    if ( xinc != 0 || yinc != 0) {
                        double nx= selected.x + (ttime/1000)*pixelsPerSecond * xinc;
                        double ny= selected.y + (ttime/1000)*pixelsPerSecond * yinc;

                        /**
                         * Test map collision: hero vs map.
                         */
                        List<AABB> collides= collision.getOverlappingActors(
                                r0.setBounds( nx, ny, selected.width, selected.height ) );

                        if ( collides.size() == 0) {
                            // no collision, move to new location
                            selected.setLocation( nx, ny );
                        } else {

                            // adjust colliding position.
                            // collides is an array of objects which at least contain a Rectangle.
                            for( int i=0; i<collides.size(); i++ ) {
                                AABB coll= collides.get(i);

                                r0.intersect( coll.AABB, r1 );

                                // if width is greater than height, we assume vertical collision.
                                if ( r1.width>r1.height ) {
                                    if ( r0.y==r1.y )  {   // intersection on top
                                        ny= r1.y1;
                                    } else if ( r0.y1==r1.y1 ) {   // intersection on bottom
                                        ny= r1.y-selected.height;
                                    }
                                // else elements collide horizontally
                                } else {
                                    if ( r0.x==r1.x )  {   // intersection on left
                                        nx= r1.x1;
                                    } else if ( r0.x1==r1.x1 ) {   // intersection on right
                                        nx= r1.x-selected.width;
                                    }
                                }

                                if ( nx!=selected.x || ny!=selected.y ) {
                                    selected.setLocation( nx, ny );
                                }
                            }
                        }
                    }
                }

                prevTime= ottime;                
            }
        }, null);
        
        /**
         * Register a CAAT key listener function
         */
        CAAT.registerKeyListener(new CAATKeyListener() {
            @Override
            public void call(CAATKeyEvent keyEvent) {
                if ( keyEvent.getKeyCode()==CAAT.Keys.UP.getValue() ) {
//                    keyEvent.preventDefault();
                    keys[2]= ( keyEvent.getAction().equals("up" )) ? 0 : 1;
                }
                if ( keyEvent.getKeyCode()==CAAT.Keys.DOWN.getValue() ) {
//                    keyEvent.preventDefault();
                    keys[3]= ( keyEvent.getAction().equals("up" )) ? 0 : 1;
                }
                if ( keyEvent.getKeyCode()==CAAT.Keys.LEFT.getValue() ) {
//                    keyEvent.preventDefault();
                    keys[0]= ( keyEvent.getAction().equals("up" )) ? 0 : 1;

                    /**
                     * whilst this code is not needed here, if you change the hero (StarActor) by an actor with bg image,
                     * this code will appropriately flip horizontally that background image.
                     */
                    if ( (keys[0] ^ keys[1]) != 0 ) {
                            selected.setImageTransformation( keys[0] != 0 ? SpriteImage.Tr.FLIP_HORIZONTAL : SpriteImage.Tr.NONE );
                    }
                }
                if ( keyEvent.getKeyCode()==CAAT.Keys.RIGHT.getValue() ) {
//                    keyEvent.preventDefault();
                    keys[1]= ( keyEvent.getAction().equals("up") ) ? 0 : 1;
                    if ( (keys[0] ^ keys[1]) != 0 ) {
                        selected.setImageTransformation( keys[1] != 0 ? SpriteImage.Tr.NONE : SpriteImage.Tr.FLIP_HORIZONTAL );
                    }
                }
            }
        });
        
    }

    public void  __createEnemies(Director director, Scene scene ) throws Exception {
        int NUM_ENEMIES= 4;
        double x,y;

        Interpolator[] lerp= new Interpolator[] {
                new Interpolator().createBounceOutInterpolator(false),
                new Interpolator().createExponentialInInterpolator(3,true)
        };

        enemies= new ArrayList<AABB>();
        for( int i=0; i<NUM_ENEMIES; i++ ) {

            /**
             * create vertically moving enemies.
             */
            x= 50 + (director.width-100)/NUM_ENEMIES * i;
            Actor enemy=
                new Actor().
                    setFillStyle( "#bb0" ).
                    setBounds(x,0,7,21).
                    addBehavior(
                        new PathBehavior().
                                setValues(
                                    new Path().
                                            setLinear( x, 20, x, director.height-20 )
                                ).
                                setPingPong().
                                setFrameTime( 0, 2000 + i*250 ).
                                setCycle( true ).
                                setInterpolator( lerp[i%lerp.length] )
                    );
            scene.addChild(enemy);
            enemies.add( enemy );

            /**
             * create horizontally moving elemies.
             */
            y= 50 + (director.height-100)/NUM_ENEMIES * i;
            enemy=
                new Actor().
                    setFillStyle( "#bb0" ).
                    setBounds(x,0,21,7).
                    addBehavior(
                        new PathBehavior().
                                setValues(
                                    new Path().
                                            setLinear( 50, y, director.width-50, y )
                                ).
                                setPingPong().
                                setFrameTime( 0, 6000 + i*250 ).
                                setCycle( true ).
                                setInterpolator( lerp[i%lerp.length] )
                    );
            scene.addChild(enemy);
            enemies.add( enemy );
        }

        /**
         * Check on every frame, whether our hero collides with any enemy.
         */
        scene.createTimer(scene.time, Double.MAX_VALUE, null, new Callback() {
            @Override
            public void call(double time, double ttime, TimerTask timerTask) {
                int max= Math.max( levelW, levelH );
                
                hash.clearObject();
                for( int i=0; i<enemies.size(); i++ ) {
                     Rectangle enemy = enemies.get(i).AABB;
                    
                    hash.addObject(new SpatialHashObject(String.valueOf(i), (int) enemy.x, (int) enemy.y,
                            (int) enemy.width, (int) enemy.height, true));

                }
                
                hash.collide((int) hero.x, (int) hero.y, (int) hero.width, (int) hero.height, new SpatialHashCallback() {
                    @Override
                    public boolean call(SpatialHashObject collide_width) {
                        hero.setLocation(112,112);
                        return true;
                    }
                });

            }
        }, null);
        
    }

    public void __createHero(Scene scene ) throws Exception {
        /**
         * create a 32x32 star actor.
         */
        int size= 16;
        hero= new StarActor().
                setBounds( 112, 112, size, size ).
                setStringStrokeStyle( "#f0f" ).
                setFilled( false ).
                setOutlined( true ).
                initialize( 12, size, size/2 ).
                addBehavior(
                    new RotateBehavior().
                            setValues( 0, 2*Math.PI ).
                            setCycle( true ).
                            setFrameTime( 0, 2000 )
                );
        
        hero.cacheAsBitmap();

        scene.addChild( hero );
    }

    public void __createLevel(Director director, Scene scene) {
       String level=
                       //012345678901234567890123
                        "111111111111111111111111"+
                        "1  11     11111      111"+
                        "1  11  1  1111        11"+
                        "1      1   11   1111  11"+
                        "1      11  11  1111   11"+
                        "1  11  11      111   111"+
                        "1  11  111   1111   1111"+
                        "1111111111111111   11  1"+
                        "1111111    11    111  11"+
                        "111             1111   1"+
                        "111    11  11111 1    11"+
                        "11    111   1111 1  1111"+
                        "11   111     11     1111"+
                        "111       11      111111"+
                        "111111111111111111111111";

       CaatjaCanvas canvas = Caatja.createCanvas();;
        canvas.setCoordinateSpaceWidth(levelW*TW);
        canvas.setCoordinateSpaceHeight(levelH*TH);
        CaatjaContext2d ctx=  canvas.getContext2d();
        int i,j;
        List<AABB> entities= new ArrayList<AABB>();

        ctx.setFillStyle("#77f");
        for( i=0; i<levelH; i++ ) {
            for( j=0; j<levelW; j++ ) {
                if ( level.charAt(j + i*levelW)=='1' ) {
                    ctx.fillRect( j*TW, i*TH, TW, TH );

                    // different from source
                    AABB aabb = new AABB();
                    aabb.AABB = new Rectangle();
                    entities.add(aabb);
                }
            }
        }

        // FIXME
//        scene.addChild( new Actor().setBackgroundImage( canvas ) );

        int max= Math.max( levelW, levelH );

        /**
         * Create a map collision spatial structure.
         */
        collision= new QuadTree().create((double) 0,(double)0,(double)max*TW,(double)max*TH, entities );
    }

}
