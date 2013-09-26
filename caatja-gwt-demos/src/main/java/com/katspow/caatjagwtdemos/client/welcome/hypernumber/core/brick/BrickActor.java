package com.katspow.caatjagwtdemos.client.welcome.hypernumber.core.brick;

import com.katspow.caatja.behavior.ScaleBehavior;
import com.katspow.caatja.event.CAATMouseEvent;
import com.katspow.caatja.foundation.Director;
import com.katspow.caatja.foundation.actor.Actor;
import com.katspow.caatja.foundation.image.CompoundImage;

public class BrickActor extends Actor {

    public Brick brick = null;
    CompoundImage compoundImage = null;

    public BrickActor() {
        super();
    }

    /**
     * 
     * @param compoundImage
     * @param brick
     *            a HN.Brick instance.
     */
    public BrickActor initialize(CompoundImage compoundImage, Brick brick) {
        this.compoundImage = compoundImage;
        this.brick = brick;
        this.setSize(compoundImage.singleWidth, compoundImage.singleHeight);

        return this;
    }

    public void paint(Director director, double time) {
        if ( this.brick.value<=0 ) {
            return;
        }
        this.compoundImage.paint(director.ctx, (this.brick.value - 1) + 9 * this.brick.color, 0, 0);
    }

    public void mouseEnter(CAATMouseEvent mouseEvent) {

        if (this.brick.selected) {
            return;
        }

        this.emptyBehaviorList();

        this.parent.setZOrder(this, Integer.MAX_VALUE);

        ScaleBehavior sb = (ScaleBehavior) new ScaleBehavior().setFrameTime(mouseEvent.source.time, 250);
        sb.setValues(1, 1.2, 1, 1.2).setPingPong();

        this.addBehavior(sb);
    }

    public void mouseDown(CAATMouseEvent mouseEvent) {
        this.brick.changeSelection();
    }

    public void reset() {
        this.resetTransform();
        this.emptyBehaviorList();
        this.alpha = 1;
    }

    @Override
    public String toString() {
        return "HN.Brick "+this.brick.row+","+this.brick.column;
    }
    
    

}
