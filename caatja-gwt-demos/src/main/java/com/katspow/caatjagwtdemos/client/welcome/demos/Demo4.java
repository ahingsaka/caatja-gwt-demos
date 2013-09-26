package com.katspow.caatjagwtdemos.client.welcome.demos;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.katspow.caatja.behavior.Interpolator;
import com.katspow.caatja.behavior.RotateBehavior;
import com.katspow.caatja.core.Caatja;
import com.katspow.caatja.core.canvas.CaatjaCanvas;
import com.katspow.caatja.core.canvas.CaatjaContext2d;
import com.katspow.caatja.core.canvas.CaatjaColor;
import com.katspow.caatja.core.canvas.CaatjaGradient;
import com.katspow.caatja.event.CAATMouseEvent;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;
import com.katspow.caatja.foundation.actor.ActorContainer;
import com.katspow.caatja.foundation.ui.TextActor;
import com.katspow.caatja.math.Pt;

public class Demo4 {
    
    private Label coords = new Label();
    private Label coords2 = new Label();
    private Label coords3 = new Label();
    
    private Actor p;
    private Actor p0;
    private Actor p1;
    
    private int i;

    private void dblclick(CAATMouseEvent mouseEvent) {
        Actor actor= mouseEvent.source;
        if( null==actor ) {
            return;
        }

        ActorContainer parent= actor.parent;
        parent.setZOrder(actor,Integer.MAX_VALUE);
    }
    
    private void fpaint(Director director, double time, ActorContainer parent, CaatjaColor fillStyle, boolean pointed, double width, double height) {
        CaatjaContext2d canvas= director.ctx;
        
                            if ( null!=parent && null!=fillStyle ) {
                                
                                if (fillStyle != null) {
                                    canvas.setFillStyle(fillStyle);
                                } else {
                                    canvas.setFillStyle(pointed ? "orange" : "white");
                                }
                                
                                canvas.fillRect(0,0,width,height );
                            }
        
                            canvas.setFillStyle("black");
                            canvas.fillRect(1,1,5,5);
    }
    
    private void oldMouseMove(CAATMouseEvent mouseEvent, Actor actor) {
        if (actor.enableDrag) {
            actor.mx = mouseEvent.point.x;
            actor.my = mouseEvent.point.y;
        }
    }
    
    private void mouseMoveHandler(int i, Scene scene, CAATMouseEvent mouseEvent) {
        Actor actor= mouseEvent.source;
        
//      actor.__mouseMove(mouseEvent);
        oldMouseMove(mouseEvent, actor);
        
        
      coords.setText("Local Coord: ("+
              ((int)(mouseEvent.point.x*100)>>0)/100+","+
              ((int)(mouseEvent.point.y*100)>>0)/100+")");
      coords2.setText("Screen Coord: ("+
              mouseEvent.screenPoint.x+","+
              mouseEvent.screenPoint.y+")");
      coords3.setText(
              "Parent Pos: ("+((int)(actor.x*100)>>0)/100+","+((int)(actor.y*100)>>0)/100+")" );
      
      if (i==0) {
          scene.addActorToInputList(p,1);
          scene.addActorToInputList(p0,0);
          scene.addActorToInputList(p1,0);
      }
    }
    
    public void start(CaatjaCanvas canvas) throws Exception {
        
        RootPanel.get().add(coords);
        RootPanel.get().add(coords2);
        RootPanel.get().add(coords3);
        
        final Director director = new Director().initialize(800, 500, canvas);
        final Scene scene = director.createScene();
        
        ActorContainer cc= new ActorContainer() {
            @Override
            public void mouseMove(CAATMouseEvent mouseEvent) throws Exception {
                
                oldMouseMove(mouseEvent, mouseEvent.source);
                
                coords.setText("Local Coord: (" + mouseEvent.point.x + "," + mouseEvent.point.y + ")");
                coords2.setText("");
                coords3.setText("");

            }
            
        }.
                setBounds( 0,0,director.width,director.height );
            cc.setGestureEnabled(true);
            scene.addChild(cc);

            TextActor coords= new TextActor().
                    setFont("20px sans-serif").
                    setTextAlign("left").
                    setTextBaseline("top").
                    setText("").
                    setLocation(15,20).
                    setTextFillStyle("black").
                    setOutlineColor("#aaa").
                    setOutline(true);
            scene.addChild(coords);

            TextActor coords2= new TextActor().
                setFont("20px sans-serif").
                setTextAlign("left").
                setTextBaseline("top").
                setText("").
                setLocation(15,42).
                setTextFillStyle("black").
                setOutlineColor("#aaa").
                setOutline(true);
            scene.addChild(coords2);

            TextActor coords3= new TextActor().
                setFont("20px sans-serif").
                setTextAlign("left").
                setTextBaseline("top").
                setText("").
                setLocation(15,64).
                setTextFillStyle("black").
                setOutlineColor("#aaa").
                setOutline(true);
            scene.addChild(coords3);
            
            scene.enableInputList(2);

            int np = 20;
            int s = 80;
            for ( i = 0; i < np; i++) {
                double sc= 1+Math.random()*.25;

                ActorContainer p = new ActorContainer() {

                    @Override
                    public void paint(Director director, double time) {
                        CaatjaContext2d canvas= director.ctx;
                        
                                            if ( null!=this.parent && null!=this.fillStyle ) {
                                                
                                                if (this.fillStyle != null) {
                                                    canvas.setFillStyle(fillStyle);
                                                } else {
                                                    canvas.setFillStyle(this.pointed ? "orange" : "white");
                                                }
                                                
                                                canvas.fillRect(0,0,this.width,this.height );
                                            }
                        
                                            canvas.setStrokeStyle(this.pointed ? "red" : "black");
                                            canvas.strokeRect(0,0,this.width,this.height );
                        
                                            canvas.setStrokeStyle("white");
                                            canvas.beginPath();
                                            canvas.moveTo(5,10);
                                            canvas.lineTo(20,10);
                                            canvas.lineTo(15,5);
                        
                                            canvas.moveTo(20,10);
                                            canvas.lineTo(15,15);
                        
                                            canvas.setLineWidth(2);
                                            canvas.setLineJoin("round");
                                            canvas.setLineCap("round");
                        
                                            canvas.stroke();
                    }

                    @Override
                    public void mouseEnter(CAATMouseEvent mouseEvent) {
                        this.pointed= true;
                    }

                    @Override
                    public void mouseExit(CAATMouseEvent mouseEvent) {
                        this.pointed= false;
                    }

                    @Override
                    public void mouseDblClick(CAATMouseEvent mouseEvent) throws Exception {
                        dblclick(mouseEvent);
                    }

                    @Override
                    public void mouseMove(CAATMouseEvent mouseEvent) throws Exception {
                        mouseMoveHandler(i, scene, mouseEvent);
                    }
                    
                    
                }.
                    setBounds(
                        Math.random() * director.width,
                        Math.random()* director.height,
                        s,
                        s).
                    setRotation( Math.PI*2*Math.random() ).
                    setScale( sc, sc ).
                    setFillStyle( i==0 ? "#00f" : "#ff3fff");
                
                p.setGestureEnabled(true);

                cc.addChild(p);

                p0= new Actor() {

                    @Override
                    public void paint(Director director, double time) {
                        fpaint(director, time, this.parent, this.fillStyle, this.pointed, this.width, this.height);
                    }

                    @Override
                    public void mouseDblClick(CAATMouseEvent mouseEvent) throws Exception {
                        dblclick(mouseEvent);
                    }
                    
                    @Override
                    public void mouseMove(CAATMouseEvent mouseEvent) throws Exception {
                        mouseMoveHandler(i, scene, mouseEvent);
                    }
                    
                }.
                    setBounds( s/4, s/4, s/4, s/4 ).
                    setRotation( Math.PI*2*Math.random() ).
                    setFillStyle("#a03f00");
                
                p.addChild(p0);

                p1= new Actor() {
                    @Override
                    public void paint(Director director, double time) {
                        fpaint(director, time, this.parent, this.fillStyle, this.pointed, this.width, this.height);
                    }

                    @Override
                    public void mouseDblClick(CAATMouseEvent mouseEvent) throws Exception {
                        dblclick(mouseEvent);
                    }
                    
                    @Override
                    public void mouseMove(CAATMouseEvent mouseEvent) throws Exception {
                        mouseMoveHandler(i, scene, mouseEvent);
                    }
                }.
                    setBounds( s/2, s/2, s/4, s/4 ).
                    setRotation( Math.PI*2*Math.random() ).
                    setFillStyle("#ffff3f");

                p.addChild(p1);
                p1.enableDrag();
                p0.enableDrag();
                p.enableDrag();
//
//                p1.__mouseMove= p1.mouseMove;
//                p0.__mouseMove= p0.mouseMove;
//                p.__mouseMove= p.mouseMove;
//
//                p.mouseMove= mouseMoveHandler;
//                p0.mouseMove= mouseMoveHandler;
//                p1.mouseMove= mouseMoveHandler;
//
//            cc.__mouseMove= scene.mouseMove;
                
//            cc.mouseMove= function(mouseEvent) {
//                mouseEvent.source.__mouseMove(mouseEvent);
//                coords.setText("Local Coord: ("+mouseEvent.point.x+","+mouseEvent.point.y+")");
//                coords2.setText("");
//                coords3.setText("");
//            };

            CaatjaGradient gradient= director.ctx.createLinearGradient(0,0,0,50);
            gradient.addColorStop(0,"green");
            gradient.addColorStop(0.5,"red");
            gradient.addColorStop(1,"yellow");

            // texts
            ActorContainer cc1= new ActorContainer().
                    setBounds( 480,30, 300, 140 ).
                    enableEvents(false).
                    addBehavior(
                        new RotateBehavior().
                                setCycle(true).
                                setFrameTime( 0, 4000 ).
                                setValues( -Math.PI/8, Math.PI/8, .50, 0d ).  // anchor at 50%, 0%
                                setInterpolator(
                                    new Interpolator().createCubicBezierInterpolator(
                                        new Pt(0,0),
                                        new Pt(1,0),
                                        new Pt(0,1),
                                        new Pt(1,1),
                                        true ) )
                    );
            scene.addChild(cc1);

            TextActor text= new TextActor().
                    setFont("30px sans-serif").
                    setText("Perfect Pixel").
                    calcTextSize(director).
                    setTextAlign("center").
                    setTextFillStyle(gradient).
                    setOutline(true);
                    text.cacheAsBitmap();
            cc1.addChild(text.setLocation((cc1.width-text.width)/2,0));

            TextActor text2= new TextActor().
                    setFont("30px sans-serif").
                    setTextAlign("center").
                    setText("Collision detection").
                    calcTextSize(director).
                    setTextFillStyle(gradient).
                    setOutline(true);
                    text2.cacheAsBitmap();
            cc1.addChild(text2.setLocation((cc1.width-text2.width)/2,30));

            TextActor text3= new TextActor().
                    setFont("30px sans-serif").
                    setTextAlign("center").
                    setText("Drag Enabled").
                    calcTextSize(director).
                    setTextFillStyle(gradient).
                    setOutline(true);
                    text3.cacheAsBitmap();
            cc1.addChild(text3.setLocation((cc1.width-text3.width)/2,60));

            TextActor text4= new TextActor().
                    setFont("20px sans-serif").
                    setTextAlign("center").
                    setText("Drag + [Control,Shift,Alt]").
                    calcTextSize(director).
                    setTextFillStyle("black").
                    setOutline(true);
            text4.cacheAsBitmap();
            cc1.addChild(text4.setLocation((cc1.width-text4.width)/2,100));

            TextActor text5= new TextActor().
                    setFont("20px sans-serif").
                    setTextAlign("center").
                    setText("Double Click").
                    calcTextSize(director).
                    setTextFillStyle("black").
                    setOutline(true);
            text5.cacheAsBitmap();
            cc1.addChild(text5.setLocation((cc1.width-text5.width)/2,120));
        
            Caatja.loop(30);
    }
    }

}
