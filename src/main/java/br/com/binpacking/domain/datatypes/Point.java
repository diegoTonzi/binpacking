package br.com.binpacking.domain.datatypes;

import br.com.binpacking.domain.Item;

public class Point implements Comparable<Point> {
    
    private final Line width;
    private final Line length;
    private final Line height;
    
    public Point(Line width, Line length, Line height) {
        this.width = width;
        this.length = length;
        this.height = height;
    }

    public Line width() {
        return width;
    }

    public Line length() {
        return length;
    }

    public Line height() {
        return height;
    }
    
    public boolean isIntersectLineWidth(Point point){
    	if(this.width.begin() >= point.width.begin()){
		    if(this.length.begin() < point.length.end()){
		        if(this.length.end() > point.length.begin()){   
		            if(point.width.end() > this.width.begin()){ 
		                return true;
		            }
		        }
		    }
		}
    	return false;
    }
    
    public boolean isIntersectLineLength(Point point){
    	if(this.length.begin() >= point.length.begin()){
		    if(this.width.begin() < point.width.end()){
		        if(this.width.end() > point.width.begin()){
		            if(point.length.end() > this.length.begin()){
		                return true;
		            }
		        }
		    }
		}
    	return false;
    }
    
    public boolean isInBaseOfContainer(){
    	return this.height.begin() == 0 ? true : false;
    }
    
    public boolean isSameHeight(Item item){
    	return item.getPoint().height.begin() == this.height.begin();
    }
 
    @Override
    public int compareTo(Point other) {
        double thisBegin = width.begin() + length.begin() + height.begin();
        double otherBegin = other.width().begin() + other.length().begin() + other.height().begin();
        if(thisBegin > otherBegin){
            return 1;
        } else if (thisBegin < otherBegin) {
            return -1;
        } else {
            return 0;
        }
    }
    
    @Override
    public Point clone() {
        return new Point(new Line(this.width.begin(), this.width.end()), new Line(this.length.begin(), this.length.end()), new Line(this.height.begin(), this.height.end()));
    }
    
    @Override
    public String toString() {
        return "Point [W: " + this.width + ", L: " + this.length + ", H: " + this.height + "] ";
    }

}
