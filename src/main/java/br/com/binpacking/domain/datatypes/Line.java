package br.com.binpacking.domain.datatypes;

public class Line implements Comparable<Line> {
    
    private Double begin;
    private Double end;
    
    public Line(Double begin, Double end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    public int compareTo(Line other){
    	if(this.begin < other.begin){
    		return -1;
    	} else if(this.begin > other.begin){
    		return 1;
    	}
    	return 0;
    }
    
    public Double size(){
    	return end - begin;
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
