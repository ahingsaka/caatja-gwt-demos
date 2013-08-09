package com.katspow.caatjagwtdemos.client.demos.hypernumber;

import java.util.ArrayList;
import java.util.List;

public class Context {
    
    List<ContextListener> eventListener=  null;   // context listeners

    int rows=           0;      // model size in
    int columns=        0;      //  rows x columns
    int numNumberColors=0;

    Brick[][] data;   // context model. Bricks.

    int guessNumber=    0;      // number to sum up with bricks.
    double time=           0;      // maximum time to take to guess an adding number sequence.

    List<Brick> selectedList=   null;   // selected bricks.

    int status=         0;      // <-- control logic -->
    int level = 0;
    
    int score = 0; // game points.

    double turnTime=       15000;

    double[] turnTimes=   new double[]{20000, 15000, 10000};
    int difficulty=     0;    // 0= easy; 1= hard; 2= hardcore.

    static final int ST_STARTGAME=       5;
    static final int ST_INITIALIZING=    0;
    static final int ST_START_LEVEL=    2;
    static final int ST_RUNNNING=        1;
    static final int ST_LEVEL_RESULT=    3;
    static final int ST_ENDGAME=    4;
    
    // Add by me
    int multiplier = 1;
    
    public Context() {
        this.eventListener= new ArrayList<ContextListener>();
    }
    
    /**
     * Called once on game startup.
     * @param rows an integer indicating game model rows.
     * @param columns an integer indicating game model columns.
     *
     * @return nothing.
     */
    public Context create (int rows, int columns, int numNumberColors  ) {
        this.rows=              rows;
        this.columns=           columns;
        this.numNumberColors=   numNumberColors;
        this.data=              new Brick[rows][columns];

        int i,j;

        for( i=0; i<rows; i++ ) {
            //this.data.push( [] );
            for( j=0; j<columns; j++ ) {
                this.data[i][j] = new Brick();
            }
        }

        return this;
    }
    int getNumberColors ()  {
        return this.numNumberColors;
    }
    
    Context initialize () {
        
        this.setStatus( this.ST_STARTGAME );
        this.turnTime= this.turnTimes[this.difficulty];
        this.score=0;
        this.level=0;
        this.nextLevel();
        
        return this;
    }
    
    Context nextLevel() {

        this.level++;
        this.fireEvent("context","levelchange",null, null, null, null, null, null);

        this.selectedList=  new ArrayList<Brick>();

        for(int i=0; i<this.rows; i++ ) {
            for(int j=0; j<this.columns; j++ ) {
                this.data[i][j].initialize(i,j,this);
            }
        }

        this.setStatus( this.ST_INITIALIZING );

        if ( this.level>1 ) {
            // 1 seconds less each level.
            this.turnTime-=1000;
        }

        return this;
    }
    
    /**
     * Notify listeners of a context event
     * @param sSource event source object
     * @param sEvent an string indicating the event type
     * @param params an object with event parameters. Each event type will have its own parameter set.
     * @param guessNumber TODO
     * @param score TODO
     * @param multiplier TODO
     */
    void fireEvent(String sSource, String sEvent, Integer params, Brick brick, Integer guessNumber, List<Brick> bricks,
            Integer score, Integer multiplier) {
        
        int i;
        for( i=0; i<this.eventListener.size(); i++ ) {
            
            Event evt = new Event();
            evt.source = sSource;
            evt.event = sEvent;
            evt.params = params;
            evt.brick = brick;
            evt.bricks = bricks;
            evt.guessNumber = guessNumber;
            
            evt.score = score;
            evt.multiplier = multiplier;
            
            this.eventListener.get(i).contextEvent(evt);
        }
    }
    
    Context addContextListener (ContextListener listener ) {
        this.eventListener.add(listener);
        return this;
    }
    
    Brick getBrick (int row,int column ) {
        return this.data[row][column];
    }
    
    void setStatus (int status ) {
        this.status= status;
        this.fireEvent( "context", "status", this.status, null, null, null, null, null );
        
        if ( this.status==ST_RUNNNING ) {
            this.setGuessNumber();
        }
    }
    
    void selectionChanged(Brick brick) {

        // si ya estaba en la lista de seleccionados, quitarlo.
        if (selectedList.contains(brick)) {
            selectedList.remove(brick);
            this.fireEvent("brick", "selection", null, brick, null, null, null, null);
            return;
        }

        /// chequear que la suma de los elementos seleccionados es igual al numero magico.
        int sum=0;
        for(int i=0; i<this.selectedList.size(); i++ ) {
            sum+= this.selectedList.get(i).value;
        }

        sum+= brick.value;

        if ( sum>this.guessNumber ) {

            brick.selected= false;
            
            List<Brick> selected= new ArrayList<Brick>();

            // TODO Check if it really works !!!
            for (Brick selectedBrick : selectedList) {
                selected.add(selectedBrick);
            }
            
            for(int i=0; i<this.selectedList.size(); i++ ) {
                this.selectedList.get(i).selected= false;
            }
            this.selectedList.clear();
            
            // quitar marca de seleccion al ladrillo.
            this.fireEvent("brick","selectionoverflow", null, null, null, selected, null, null );


        } else if ( sum==this.guessNumber ) {
            this.selectedList.add(brick);
            
            List<Brick> selected= new ArrayList<Brick>();
            
            // TODO Check if it really works !!!
            for (Brick selectedBrick : selectedList) {
                selected.add(selectedBrick);
            }
            
            for(int i=0; i<this.selectedList.size(); i++ ) {
                this.selectedList.get(i).selected= false;
                this.selectedList.get(i).removed= true;
            }
            this.selectedList.clear();

            this.fireEvent("brick","selection-cleared", null, null, null, selected, null, null );

            this.score+= this.multiplier * ((selected.size()+1)*10)*selected.size();

            this.fireEvent("context","score", null, null, null, null, score, null);

            for(int i=0; i<this.rows; i++ ) {
                for(int j=0; j<this.columns; j++ ) {
                    if ( !this.data[i][j].removed ) {
                        this.setGuessNumber();
                        return;
                    }
                }
            }

            this.setStatus( this.ST_LEVEL_RESULT );
            
            
        } else {
            // todavia podemos sumar numeros.
            this.selectedList.add(brick);
            this.fireEvent("brick","selection", null, brick, null, null, null, null);
            this.setMultipliers();
        }
    }
    
    void setGuessNumber() {

        // first get all available board numbers.
        List<Brick> activeBricks= new ArrayList<Brick>();
        int i,j;
        for( i=0; i<this.rows; i++ ) {
            for( j=0; j<this.columns; j++ ) {
                if ( !this.data[i][j].removed ) {
                    activeBricks.add(this.data[i][j]);
                }
            }
        }

        // scramble elements.
        if ( activeBricks.size() >1 ) {
            for( i=0; i<activeBricks.size(); i++ ) {
                int rpos0 = (int) (Math.random() * activeBricks.size()) >> 0;
                Brick tmp = activeBricks.get(i);
                activeBricks.set(i, activeBricks.get(rpos0));
                activeBricks.set(rpos0, tmp);
            }
        }

        int sum = 0;
        
        for (i = 0; i < activeBricks.size(); i++) {
            if (sum + activeBricks.get(i).value >= (this.level + 1) * 10) {
                break;
            }
            sum += activeBricks.get(i).value;
        }

        this.guessNumber= sum;
        this.fireEvent( "context","guessnumber", null, null, guessNumber, null, null, null);
    }
    
    public void timeUp () {
        this.setStatus( this.ST_ENDGAME );
    }
    /**
     * establece multiplicadores de puntos en funcion de:
     *  + numero de ladridllos
     *  + distancia total entre ladrillos
     */
    public void setMultipliers () {

        int x0= this.selectedList.get(0).column;
        int y0= this.selectedList.get(0).row;
        int d=  0;

        for(int i=1; i<this.selectedList.size(); i++ ) {
            int x1= this.selectedList.get(i).column;
            int y1= this.selectedList.get(i).row;

            d+= Math.sqrt( (x1-x0)*(x1-x0) + (y1-y0)*(y1-y0) );

            x0= x1;
            y0= y1;
        }

        d= d>>0;
        d= 1+ (d/10)>>0;
        if ( d<1 ) {
            d=1;
        } else if ( d>5 ) {
            d=5;
        }

        this.multiplier= d;

        this.fireEvent("context","multiplier", null, null, null, null, null, multiplier);
    }
    
    

}
