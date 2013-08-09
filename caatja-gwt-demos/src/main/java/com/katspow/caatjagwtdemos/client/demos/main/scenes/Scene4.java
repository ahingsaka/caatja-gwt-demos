package com.katspow.caatjagwtdemos.client.demos.main.scenes;

import com.katspow.caatja.behavior.Interpolator;
import com.katspow.caatja.behavior.RotateBehavior;
import com.katspow.caatja.core.canvas.CaatjaContext2d;
import com.katspow.caatja.core.canvas.CaatjaFillStrokeStyle;
import com.katspow.caatja.core.canvas.CaatjaGradient;
import com.katspow.caatja.event.CAATMouseEvent;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;
import com.katspow.caatja.foundation.actor.ActorContainer;
import com.katspow.caatja.foundation.ui.TextActor;
import com.katspow.caatja.math.Pt;

public class Scene4 {

    public static Scene init(Director director) throws Exception {
        Scene scene = new Scene() {
            @Override
            public void mouseMove(CAATMouseEvent mouseEvent) throws Exception {
                mouseEvent.source.mouseMove(mouseEvent);
                ((TextActor) ((ActorContainer) mouseEvent.source).childrenList.get(1)).setText("");
                ((TextActor) ((ActorContainer) mouseEvent.source).childrenList.get(2)).setText("");
            }
        };
        
        ActorContainer cc= new ActorContainer();
        cc.setBounds( 0,0,director.canvas.getCoordinateSpaceWidth(),director.canvas.getCoordinateSpaceHeight() );
        scene.addChild(cc); 
        
        TextActor coords= new TextActor();
        coords.setFont("20px sans-serif");
        coords.textAlign="left";
        coords.textBaseline="top";
        coords.setText("");
        coords.setLocation(cc.width/2,20);
        coords.setTextFillStyle(new CaatjaFillStrokeStyle("black"));
        coords.outlineColor="white";
        coords.outline= true;
        scene.addChild(coords);

        TextActor coords2= new TextActor();
        coords2.setFont("20px sans-serif");
        coords2.textAlign="left";
        coords2.textBaseline="top";
        coords2.setText("");
        coords2.setLocation(15,42);
        coords2.setTextFillStyle(new CaatjaFillStrokeStyle("black"));
        coords2.outlineColor=  "white";
        coords2.outline= true;
        scene.addChild(coords2);

        TextActor coords3= new TextActor();
        coords3.setFont("20px sans-serif");
        coords3.textAlign="left";
        coords3.textBaseline="top";
        coords3.setText("");
        coords3.setLocation(15,64);
        coords3.setTextFillStyle(new CaatjaFillStrokeStyle("black"));
        coords3.outlineColor=  "white";
        coords3.outline= true;
        scene.addChild(coords3);
        
        int np = 20;
        for ( int i = 0; i < np; i++) {
            ActorContainer p = new ActorContainer() {
                @Override
                public void paint(Director director, double time) {
                    CaatjaContext2d canvas = director.crc;
                    
                    if ( null!=this.parent && null!=this.fillStyle ) {
                        canvas.setFillStyle(this.pointed ? new CaatjaFillStrokeStyle("orange") : (this.fillStyle!=null ? this.fillStyle : new CaatjaFillStrokeStyle("white"))); //"white";
                        canvas.fillRect(0,0,this.width,this.height );
                    }
                    
                    canvas.setStrokeStyle(this.pointed ? "red" : "black");
                    canvas.strokeRect(0,0,this.width,this.height );
                    
                    canvas.setStrokeStyle("white");
                    canvas.beginPath();
                    canvas.moveTo(5, 10);
                    canvas.lineTo(20, 10);
                    canvas.lineTo(15, 5);

                    canvas.moveTo(20, 10);
                    canvas.lineTo(15, 15);

                    canvas.setLineWidth(2);
                    canvas.setLineJoin("round");
                    canvas.setLineCap("round");

                    canvas.stroke();
                }
                
                @Override
                public void mouseDblClick(CAATMouseEvent mouseEvent) {
                    Actor actor= mouseEvent.source;
                    if( null==actor ) {
                        return;
                    }

                    ActorContainer parent= actor.parent;
                    parent.setZOrder(actor,Integer.MAX_VALUE);
                }
                
                @Override
                public void mouseMove(CAATMouseEvent mouseEvent) throws Exception {
                    Actor actor= mouseEvent.source;
                    super.mouseMove(mouseEvent);
                    
                    // bugbug, subiendo hasta scena
                    ((TextActor) actor.parent.parent.childrenList.get(1)).setText("Local Coord: ("+
                            ((int)(mouseEvent.point.x*100)>>0)/100+","+
                            ((int)(mouseEvent.point.y*100)>>0)/100+")");
                    ((TextActor) actor.parent.parent.childrenList.get(2)).setText("Screen Coord: ("+
                            mouseEvent.screenPoint.x+","+
                            mouseEvent.screenPoint.y+")");
                    ((TextActor) actor.parent.parent.childrenList.get(3)).setText(
                            "Parent Pos: ("+((int)(actor.x*100)>>0)/100+","+((int)(actor.y*100)>>0)/100+")" );           
                }
                
            };
            
            int s = 80;
            p.setBounds(Math.random() * director.canvas.getCoordinateSpaceWidth(), Math.random()* director.canvas.getCoordinateSpaceHeight(), s, s);
            p.setRotation( Math.PI*2*Math.random() );
            double sc= 1+Math.random()*.25;
            p.setScale( sc, sc );
            p.fillStyle= new CaatjaFillStrokeStyle("#ff3fff");
           
            cc.addChild(p);

            Actor p0 = new Actor() {
                @Override
                public void paint(Director director, double time) {
                    CaatjaContext2d canvas= director.crc;
                    super.paint(director, time);
                    canvas.setFillStyle("black");
                    canvas.fillRect(1,1,5,5);
                }
                
                @Override
                public void mouseMove(CAATMouseEvent mouseEvent) throws Exception {
                    Actor actor= mouseEvent.source;
                    super.mouseMove(mouseEvent);
                    
                    // bugbug, subiendo hasta scena
                    ((TextActor) actor.parent.parent.parent.childrenList.get(1)).setText("Local Coord: ("+
                            ((int)(mouseEvent.point.x*100)>>0)/100+","+
                            ((int)(mouseEvent.point.y*100)>>0)/100+")");
                    ((TextActor) actor.parent.parent.parent.childrenList.get(2)).setText("Screen Coord: ("+
                            mouseEvent.screenPoint.x+","+
                            mouseEvent.screenPoint.y+")");
                    ((TextActor) actor.parent.parent.parent.childrenList.get(3)).setText(
                            "Parent Pos: ("+((int)(actor.x*100)>>0)/100+","+((int)(actor.y*100)>>0)/100+")" );            
                    
                }

                @Override
                public void mouseDblClick(CAATMouseEvent mouseEvent) throws Exception {
                    Actor actor= mouseEvent.source;
                    if( null==actor ) {
                        return;
                    }

                    ActorContainer parent= actor.parent;
                    parent.removeChild(actor);
                    parent.addChild(actor);
                }
                
            };
            
            p0.setBounds((double) s/4,(double) s/4,(double) s/4,(double) s/4 );
            p0.setRotation( Math.PI*2*Math.random() );
            p0.fillStyle=new CaatjaFillStrokeStyle("#a03f00");
            p.addChild(p0);

            Actor p1= new Actor() {
                
                @Override
                public void paint(Director director, double time) {
                    CaatjaContext2d canvas= director.crc;
                    super.paint(director, time);
                    canvas.setFillStyle("black");
                    canvas.fillRect(1,1,5,5);
                }

                @Override
                public void mouseMove(CAATMouseEvent mouseEvent) throws Exception {
                    Actor actor= mouseEvent.source;
                    super.mouseMove(mouseEvent);
                    
                    // bugbug, subiendo hasta scena
                    ((TextActor) actor.parent.parent.parent.childrenList.get(1)).setText("Local Coord: ("+
                            ((int)(mouseEvent.point.x*100)>>0)/100+","+
                            ((int)(mouseEvent.point.y*100)>>0)/100+")");
                    ((TextActor) actor.parent.parent.parent.childrenList.get(2)).setText("Screen Coord: ("+
                            mouseEvent.screenPoint.x+","+
                            mouseEvent.screenPoint.y+")");
                    ((TextActor) actor.parent.parent.parent.childrenList.get(3)).setText(
                            "Parent Pos: ("+((int)(actor.x*100)>>0)/100+","+((int)(actor.y*100)>>0)/100+")" );
                }

                @Override
                public void mouseDblClick(CAATMouseEvent mouseEvent) throws Exception {
                    Actor actor= mouseEvent.source;
                    if( null==actor ) {
                        return;
                    }

                    ActorContainer parent= actor.parent;
                    parent.removeChild(actor);
                    parent.addChild(actor);
                }
                
                
                
            };
            p1.setBounds((double) s/2,(double) s/2,(double) s/4,(double) s/4 );
            p1.setRotation( Math.PI*2*Math.random() );
            p1.fillStyle=new CaatjaFillStrokeStyle("#ffff3f");
            p.addChild(p1);
        
            p1.enableDrag();
            p0.enableDrag();
            p.enableDrag();
            
//            p1.__mouseMove= p1.mouseMove;
//            p0.__mouseMove= p0.mouseMove;
//            p.__mouseMove= p.mouseMove;
            

            // ImageActor mouseMoveHandler= function(mouseEvent) {
            // ImageActor actor= mouseEvent.source;
//                actor.__mouseMove.call(this,mouseEvent);
//                // bugbug, subiendo hasta scena
//                actor.parent.parent.childList[1].setText("Local Coord: ("+
//                        ((mouseEvent.point.x*100)>>0)/100+","+
//                        ((mouseEvent.point.y*100)>>0)/100+")");
//                actor.parent.parent.childList[2].setText("Screen Coord: ("+
//                        mouseEvent.screenPoint.x+","+
//                        mouseEvent.screenPoint.y+")");
//                actor.parent.parent.childList[3].setText(
//                        "Parent Pos: ("+((actor.x*100)>>0)/100+","+((actor.y*100)>>0)/100+")" );            
//            };
            // ImageActor mouseMoveHandler2= function(mouseEvent) {
            // ImageActor actor= mouseEvent.source;
//                actor.__mouseMove.call(this,mouseEvent);
//                // bugbug, subiendo hasta scena
//                actor.parent.parent.parent.childList[1].setText("Local Coord: ("+
//                        ((mouseEvent.point.x*100)>>0)/100+","+
//                        ((mouseEvent.point.y*100)>>0)/100+")");
//                actor.parent.parent.parent.childList[2].setText("Screen Coord: ("+
//                        mouseEvent.screenPoint.x+","+
//                        mouseEvent.screenPoint.y+")");
//                actor.parent.parent.parent.childList[3].setText(
//                        "Parent Pos: ("+((actor.x*100)>>0)/100+","+((actor.y*100)>>0)/100+")" );            
//                
//            };
            
            
//            p.mouseMove= mouseMoveHandler;
//            p0.mouseMove= mouseMoveHandler2;
//            p1.mouseMove= mouseMoveHandler2;
        }       

//        scene.__mouseMove= scene.mouseMove;
//        scene.mouseMove= function(mouseEvent) {
//            mouseEvent.source.__mouseMove.call(this,mouseEvent);
//            mouseEvent.source.childList[1].setText("");
//            mouseEvent.source.childList[2].setText("");
//        };
        
        CaatjaGradient gradient= director.crc.createLinearGradient(0,0,0,50);
        gradient.addColorStop(0,"green");
        gradient.addColorStop(0.5,"red");
        gradient.addColorStop(1,"yellow");  
        
        // texts
        ActorContainer cc1= new ActorContainer();
        cc1.setBounds( 380,30, 300, 140 );
        scene.addChild(cc1);
        cc1.mouseEnabled= false;

        
        RotateBehavior rb= new RotateBehavior();
        rb.cycleBehavior= true;
        rb.setFrameTime( 0, 4000 );
        rb.setValues( -Math.PI/8, Math.PI/8, .5, 0d );
        rb.setInterpolator( new Interpolator().createCubicBezierInterpolator(new Pt().set(0, 0), new Pt().set(1, 0), new Pt().set(0, 1), new Pt().set(1, 1), true ) );
        cc1.addBehavior(rb);
        
        TextActor text= new TextActor();
        text.setFont("30px sans-serif");
        text.setText("Perfect Pixel");
        text.calcTextSize(director);
        text.textAlign="center";
        text.setLocation(cc1.width/2,0);
        text.setTextFillStyle(gradient);
        text.outline= true;
        text.cacheAsBitmap();
        cc1.addChild(text.setLocation((cc1.width-text.textWidth)/2,0));

        TextActor text2= new TextActor();
        text2.setFont("30px sans-serif");
        text2.textAlign="center";
        text2.setText("Collision detection");
        text2.calcTextSize(director);
        text2.setLocation(cc1.width/2,30);
        text2.setTextFillStyle(gradient);
        text2.outline= true;
        text2.cacheAsBitmap();
        cc1.addChild(text2.setLocation((cc1.width-text2.textWidth)/2,30));

        TextActor text3= new TextActor();
        text3.setFont("30px sans-serif");
        text3.textAlign="center";
        text3.setText("Drag Enabled");
        text3.calcTextSize(director);
        text3.setLocation(cc1.width/2,60);
        text3.setTextFillStyle(gradient);
        text3.outline= true;
        text3.cacheAsBitmap();
        cc1.addChild(text3.setLocation((cc1.width-text3.textWidth)/2,60));

        TextActor text4= new TextActor();
        text4.setFont("20px sans-serif");
        text4.textAlign="center";
        text4.setText("Drag + [Control,Shift,Alt]");
        text4.calcTextSize(director);
        text4.setLocation(cc1.width/2,100);
        text4.setTextFillStyle(new CaatjaFillStrokeStyle("black"));
        text4.cacheAsBitmap();
        cc1.addChild(text4.setLocation((cc1.width-text4.textWidth)/2,100));

        TextActor text5= new TextActor();
        text5.setFont("20px sans-serif");
        text5.textAlign="center";
        text5.setText("Double Click");
        text5.calcTextSize(director);
        text5.setLocation(cc1.width/2,120);
        text5.setTextFillStyle(new CaatjaFillStrokeStyle("black"));
        text5.cacheAsBitmap();
        cc1.addChild(text5.setLocation((cc1.width-text5.textWidth)/2,120));

        cc1.mouseEnabled= false;    

        return scene;
    }

}
