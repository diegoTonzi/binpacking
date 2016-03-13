package br.com.diegotonzi.binpacking.model;

/**
 * This class represents a line of a Point <br/><br/>
 * A Line have two attributes, the point where the line begins and the point where the line ends <br/>
 * When the line is in a Point object, its represents one of coordinates of this point, x(width), y(length) and z(height). <br/><br/>
 * If, for example, in a point, this line is the coordinate x(width), then, the values of begin and end of this line represents the two points in a coordinate x(width), for example, in a point this line is the coordinate x(width), then, the values of begin and end of this line represents the two points in a coordinate x(width)
 */
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
        return "{" + begin + ", " + end + "}";
    }

}
