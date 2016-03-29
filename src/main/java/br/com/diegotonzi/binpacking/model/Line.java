package br.com.diegotonzi.binpacking.model;

public class Line {
    
    private Double begin;
    private Double end;
    
    public Line(Double begin, Double end) {
        this.begin = begin;
        this.end = end;
    }

    public Double getBegin() {
        return begin;
    }

    public void setBegin(Double begin) {
        this.begin = begin;
    }

    public Double getEnd() {
        return end;
    }

    public void setEnd(Double end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "(" + begin + ", " + end + ")";
    }

}
