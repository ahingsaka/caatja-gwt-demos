package com.katspow.caatjagwtdemos.client.welcome.showcase.scenes;

import com.katspow.caatja.behavior.BaseBehavior;
import com.katspow.caatja.behavior.BehaviorListener;
import com.katspow.caatja.behavior.Interpolator;
import com.katspow.caatja.behavior.PathBehavior;
import com.katspow.caatja.behavior.RotateBehavior;
import com.katspow.caatja.behavior.ScaleBehavior;
import com.katspow.caatja.behavior.SetForTimeReturnValue;
import com.katspow.caatja.behavior.listener.BehaviorAppliedListener;
import com.katspow.caatja.behavior.listener.BehaviorExpiredListener;
import com.katspow.caatja.core.canvas.CaatjaGradient;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.Scene;
import com.katspow.caatja.foundation.actor.Actor;
import com.katspow.caatja.foundation.actor.ActorContainer;
import com.katspow.caatja.pathutil.Path;
import com.katspow.caatjagwtdemos.client.welcome.showcase.fish.FishScene12;

public class Scene12 {

    public static Scene init(final Director director) throws Exception {

        final Scene scene = new Scene();

        int NP = 20;

        String[] colors = new String[] { "red", "blue", "white", "rgb(0,255,255)", "yellow" };

        CaatjaGradient gradient = director.ctx.createLinearGradient(0, 0, director.width, director.height);
        gradient.addColorStop(0, "#000000");
        gradient.addColorStop(1, "#00007f");
        
        Path path2= new Path().
        beginPath(100,director.height/2).
        addCubicTo(
            100,10,
            director.width-200,10,
            director.width-200,director.height/2 ).
        addCubicTo(
            director.width-200,director.height-10,
            100, director.height-10,
            100, director.height/2 ).
        closePath();

        Path path = new Path()
                .
                // beginPath(100,100).
                // addRectangleTo( director.width-100, director.height-100 ).

                beginPath(100, director.height / 2)
                .addCubicTo(100, 10, director.width - 100, 10, director.width - 100, director.height / 2)
                .addCubicTo(director.width - 100, director.height - 10, 100, director.height - 10, 100,
                        director.height / 2).closePath();

        path.addBehavior(
                new PathBehavior().setValues(path2).setFrameTime(0, 15000).setCycle(true)
                        .setTranslation(path2.width / 2, path2.height / 2))
                .addBehavior(new RotateBehavior().setValues(0, Math.PI * 2).setFrameTime(0, 5000).setCycle(true))
                .addBehavior(
                        new ScaleBehavior().setValues(.2, 1, .2, 1).setFrameTime(0, 10000).setCycle(true)
                                .setInterpolator(new Interpolator().createLinearInterpolator(true, false)));

        ActorContainer ac = new ActorContainer().setBounds(0, 0, director.width, director.height)
                .setFillStrokeStyle(gradient).enableEvents(false);

        for (int i = 0; i < NP; i++) {

            final double fw = (int) (100 + Math.random() * 40 * (Math.random() < .5 ? 1 : -1)) >> 0;
            double fh = (int) (20 + Math.random() * 5 * (Math.random() < .5 ? 1 : -1)) >> 0;

            int inTime = i * 1000;

            PathBehavior pb = (PathBehavior) new PathBehavior()
                    .setPath(
                            new Path().setCubic(-fw - Math.random() * 300, Math.random() * director.height,

                            director.width * Math.random(), Math.random() * director.height,

                            director.width * Math.random(), Math.random() * director.height,

                            Math.random() < .5 ? director.width + fw + Math.random() * 150 : Math.random()
                                    * director.width, Math.random() < .5 ? -director.height * Math.random() - 300
                                    : director.height + Math.random() * director.height))
                    .setFrameTime(scene.time + inTime, (int) (20000 + 5000 * Math.random()) >> 0).setCycle(true)
                    .setAutoRotate(true).addListener(
                            
                            BehaviorListener.valueOfExpired(new BehaviorExpiredListener() {
                                public void call(BaseBehavior behavior, double time, Actor actor) {
                                    ((PathBehavior) behavior).path.setCubic(-fw - Math.random() * 300, Math.random()
                                            * director.height,

                                    director.width * Math.random(), -Math.random() * director.height / 2 + Math.random()
                                            * director.height,

                                    director.width * Math.random(), -Math.random() * director.height / 2 + Math.random()
                                            * director.height,

                                    Math.random() < .5 ? director.width + fw + Math.random() * 150 : Math.random()
                                            * director.width, Math.random() < .5 ? -director.height * Math.random() - 300
                                            : director.height + Math.random() * director.height);
                                    behavior.setFrameTime(scene.time, (int) (20000 + 5000 * Math.random()) >> 0);
                                    ((FishScene12) actor).born();
                                }
                            })
                            
//                            new BehaviorListener() {
//
//                        @Override
//                        public void behaviorExpired(BaseBehavior behavior, double time, Actor actor) {
//                            ((PathBehavior) behavior).path.setCubic(-fw - Math.random() * 300, Math.random()
//                                    * director.height,
//
//                            director.width * Math.random(), -Math.random() * director.height / 2 + Math.random()
//                                    * director.height,
//
//                            director.width * Math.random(), -Math.random() * director.height / 2 + Math.random()
//                                    * director.height,
//
//                            Math.random() < .5 ? director.width + fw + Math.random() * 150 : Math.random()
//                                    * director.width, Math.random() < .5 ? -director.height * Math.random() - 300
//                                    : director.height + Math.random() * director.height);
//                            behavior.setFrameTime(scene.time, (int) (20000 + 5000 * Math.random()) >> 0);
//                            ((FishScene12) actor).born();
//
//                        }
//
//                        @Override
//                        public void behaviorApplied(BaseBehavior behavior, double time, double normalizeTime, Actor actor,
//                                SetForTimeReturnValue value) {
//
//                        }
//
//                        @Override
//                        public void behaviorStarted(BaseBehavior behavior, double time, Actor actor) {
//                            
//                        }
//                    }
                            
                            );

            FishScene12 f = (FishScene12) new FishScene12().setBounds(300, 400, fw, fh);
            ((FishScene12) f.born().setFrameTime(scene.time + inTime, Double.MAX_VALUE)).setBodyColor(colors[i
                    % colors.length]);

            f.addBehavior(pb);
            ac.addChild(f);
        }

        scene.addChild(ac);

        return scene;

    }

}
