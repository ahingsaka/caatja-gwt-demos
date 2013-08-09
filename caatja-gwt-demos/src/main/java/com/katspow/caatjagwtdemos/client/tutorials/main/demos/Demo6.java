package com.katspow.caatjagwtdemos.client.tutorials.main.demos;

import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;

// TODO
public class Demo6 {
    
    int nStars = 1000;
    String[] m_starData;
    int speed = 5;
    int STAR_DENSITY = 256;
    int dist = 256;
    double[] m_matrix;
    int xy = 0, xz = 0, yz = 0;
    
    int CW= 800;
    int CH= 500;
    
    public void start(CaatjaCanvas canvas) throws Exception {
        final Director director = new Director().initialize(800, 500, canvas).setClear(false);
        Scene scene = director.createScene();
        
//        createStars();
        int i;

//        scene.activated= function() {
//            director.setClear(false);
//        };
        
        // TODO
//      scene.onRenderEnd = function(director, time) {
//      var rx = rotationRate.gamma;
//
//      //rx/=10; // 9.8 m/s^2
//
//      var ixy= -rx * Math.PI / 180;
//      text.setRotation(ixy);
//
//      xy+= -ixy/10;
//      yz+= -rotationRate.beta * Math.PI/ 180 / 10;
//
//  };

//        ActorContainer root= new ActorContainer() {
//            @Override
//            public void paint(Director director, double time) {
//                Context2d ctx= director.ctx;
//              ctx.setFillStyle("black");
//              ctx.fillRect(0,0,director.width,director.height);
//              drawStarfield(ctx);
//            }
//            
//        }.setBounds(0,0,director.width,director.height);
//        
//        scene.addChild(root);
//        
//        __scene11_text(director, scene);
//
//        TextActor text = new TextActor().
//                setFont("100px sans-serif").
//                setText("Rotate Device").
//                create().
//                setTextFillStyle("red").
//                setOutlineColor("#ffff00").
//                setOutline(true).
//                calcTextSize(director);
//                text.cacheAsBitmap();
//        scene.addChild(text.setLocation((director.canvas.getCoordinateSpaceWidth() - text.width) / 2,
//                (director.canvas.getCoordinateSpaceHeight() - text.height) / 2) );

    }
    
//        private void drawStarfield(Context2d ctx) {
//            drawStars(ctx);
//
//            for( int i=0; i<nStars; i++ ) {
//                m_starData[i*4+2] -= speed;
//                if (m_starData[i*4+2] < -dist) {
//                    m_starData[i*4+2] = dist;
//                    m_starData[i*4+0] = (((Math.random() > .5) ? 1 : -1) * (Math.random() * dist))>>0;
//                    m_starData[i*4+1] = (((Math.random() > .5) ? 1 : -1) * (Math.random() * dist))>>0;
//                }
//            }
//
//            rotate();
//        }
//
//
//        private void createStars() {
//            rotate();
//
//            int i;
//            for( i=0; i<nStars*4; i++ ) {
//                m_starData.push(0);
//            }
//
//            for ( i = 0; i < nStars; i++ ) {
//                m_starData[i*4+0] = ((Math.random() > .5) ? 1 : -1) * (Math.random() * dist)>>0;
//                m_starData[i*4+1] = ((Math.random() > .5) ? 1 : -1) * (Math.random() * dist)>>0;
//                m_starData[i*4+2] = ((Math.random() > .5) ? 1 : -1) * (Math.random() * dist)>>0;
//
//                double d = Math.random();
//                if (d < .25) {
//                    m_starData[i*4+3] = "#ff0000";
//                } else if (d < .50) {
//                    m_starData[i*4+3] = "#00ff00";
//                } else if (d < .75) {
//                    m_starData[i*4+3] = "#0000ff";
//                } else {
//                    m_starData[i*4+3] = "#ffffff";
//                }
//            }
//        }
//
//        private void drawStars(Context2d ctx) {
//            int i,l;
//
//            for ( i = 0, l=nStars; i < l; i++ ) {
//
//                var xx = m_starData[i*4+0];
//                var yy = m_starData[i*4+1];
//                var zz = m_starData[i*4+2];
//
//                var x = xx * m_matrix[0] + yy * m_matrix[1] + zz * m_matrix[2] + m_matrix[3];
//                var y = xx * m_matrix[4] + yy * m_matrix[5] + zz * m_matrix[6] + m_matrix[7];
//                var z = xx * m_matrix[8] + yy * m_matrix[9] + zz * m_matrix[10] + m_matrix[11];
//
//                if (z > 0) {
//
//                    double xp = ( CW >> 1 ) + (x * 256) / z;
//                    double yp = ( CH >> 1 ) + (y * 256) / z;
//
//                    int s=0;
//                    if (z < 128) {
//                        s = 4;
//                    } else if (z < 256) {
//                        s = 2;
//                    } else {
//                        s = 1;
//                    }
//
//                    ctx.setFillStyle(m_starData[i*4+3]);
//                    ctx.fillRect( xp, yp, s, s );
//                }
//            }
//        }
//
//        private void rotate() {
//
//            double sxy = Math.sin(xy);
//            double sxz = Math.sin(xz);
//            double syz = Math.sin(yz);
//            double cxy = Math.cos(xy);
//            double cxz = Math.cos(xz);
//            double cyz = Math.cos(yz);
//
//            m_matrix[  0 ] = cxz * cxy;
//            m_matrix[  1 ] = -cxz * sxy;
//            m_matrix[  2 ] = sxz;
//            m_matrix[  3 ] = 0;
//            m_matrix[  4 ] = syz * sxz * cxy + sxy * cyz;
//            m_matrix[  5 ] = cyz * cxy - syz * sxz * sxy;
//            m_matrix[  6 ] = -syz * cxz;
//            m_matrix[  7 ] = 0;
//            m_matrix[  8 ] = syz * sxy - cyz * sxz * cxy;
//            m_matrix[  9 ] = cyz * sxz * sxy + syz * cxy;
//            m_matrix[  10] = cyz * cxz;
//            m_matrix[ 11 ] = dist;
//            m_matrix[ 12 ] = 0;
//            m_matrix[ 13 ] = 0;
//            m_matrix[ 14 ] = 0;
//            m_matrix[ 15 ] = 1;
//        }
//        
//        private void __scene11_text(Director director, Scene scene) {
//            CanvasGradient gradient = director.crc.createLinearGradient(0, 0, 0, 50);
//            gradient.addColorStop(0, "orange");
//            gradient.addColorStop(0.5, "yellow");
//            gradient.addColorStop(1, "#7f00ff");
//
//            ActorContainer cc = new ActorContainer().
//                    setBounds(550, 50, 150, 100).
//                    create().
//                    enableEvents(false).
//                    addBehavior(
//                    new RotateBehavior().
//                            setCycle(true).
//                            setFrameTime(0, 4000).
//                            setValues(-Math.PI / 8, Math.PI / 8, 50d, 0d).
//                            setInterpolator(
//                            new Interpolator().createExponentialInOutInterpolator(3, true)
//                            )
//                    );
//            scene.addChild(cc);
//
//            TextActor text = new TextActor().
//                    setFont("50px sans-serif").
//                    setText("Accelerometer").
//                    create().
//                    setTextFillStyle(gradient).
//                    setOutline(true).
//                    calcTextSize(director);
//            text.cacheAsBitmap();
//            cc.addChild(text.setLocation((cc.width - text.width) / 2, 0));
//
//            TextActor text2 = new TextActor().
//                    setFont("50px sans-serif").
//                    setText("Enabled").
//                    calcTextSize(director).
//                    create().
//                    setTextFillStyle(gradient).
//                    setOutline(true);
//                    text.cacheAsBitmap();
//            cc.addChild(text2.setLocation((cc.width - text2.width) / 2, 50));
//        }
//    }

}
