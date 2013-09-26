package com.katspow.caatjagwtdemos.client.welcome.hypernumber.effects;

import com.katspow.caatja.core.canvas.CaatjaContext2d;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.actor.Actor;

public class PlasmaActor extends Actor {

    public PlasmaActor() {
        super();
    }

    Plasma plasma = null;

    public PlasmaActor initialize(double plasmaWidth, double plasmaHeight) {
        this.plasma = new Plasma().initialize(plasmaWidth, plasmaHeight);
        return this;
    }

    public void paint(Director director, double time) {
        CaatjaContext2d ctx = director.ctx;
        ctx.drawImage(this.plasma.canvas, 0, 0, this.width, this.height);
    }

    public boolean animate(Director director, double time) throws Exception {
        this.plasma.plasmaLoop(time);
        return super.animate(director, time);
    }

}
