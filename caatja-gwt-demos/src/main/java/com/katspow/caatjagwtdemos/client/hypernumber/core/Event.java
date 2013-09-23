package com.katspow.caatjagwtdemos.client.hypernumber.core;

import java.util.List;

import com.katspow.caatjagwtdemos.client.hypernumber.core.brick.Brick;

public class Event {
    
    public String source;
    public String event;
    public Integer params;
    public Brick brick;
    public Integer guessNumber;
    public List<Brick> bricks;
    
    public Integer multiplier;
    public Integer score;

}
