package com.katspow.caatjagwtdemos.client.hypernumber.core.brick;

import com.katspow.caatjagwtdemos.client.hypernumber.core.context.Context;

public class Brick {

    public boolean selected;
    public int value;
    public int color;
    public int row;
    public int column;
    public Context context;
    public boolean removed;

    public Brick() {
        value = 0;
        color = 0;
        selected = false;
        removed = false;
        
        row = 0;
        column = 0;

        context = null;
    }

    /**
     * 
     * @param row
     * @param column
     * @param context
     *            the HN.Context instance
     */
    public void initialize(int row, int column, Context context) {
        this.row = row;
        this.column = column;
        this.selected = false;
        this.color = (int) (Math.random() * context.getNumberColors()) >> 0;
        this.context = context;

        // favorecer los numeros 3..9
        if (Math.random() > .3) {
            this.value = 4 + (int) (Math.random() * 6) >> 0;
        } else {
            this.value = 1 + (int) (Math.random() * 3) >> 0;
        }

        if (this.value < 1) {
            this.value = 1;
        } else if (this.value > 9) {
            this.value = 9;
        }
    }

    public void changeSelection() {
        this.selected = !this.selected;
        this.context.selectionChanged(this);
    }

}
