package com.example.lr1_kg.models;

public class Lines {

    private Coordinates start;
    private  Coordinates end;
    public Lines(Coordinates start, Coordinates end)
    {
        this.start=start;
        this.end=end;
    }

    public Coordinates getEnd() {
        return end;
    }

    public Coordinates getStart() {
        return start;
    }

    public void setEnd(Coordinates end) {
        this.end = end;
    }

    public void setStart(Coordinates start) {
        this.start = start;
    }
}
