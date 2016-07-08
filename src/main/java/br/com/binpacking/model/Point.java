package br.com.binpacking.model;

public class Point implements Comparable<Point> {
    
    private final Line width;
    private final Line length;
    private final Line height;
    
    public Point(Line width, Line length, Line height) {
        this.width = width;
        this.length = length;
        this.height = height;
    }

    public Line getWidth() {
        return width;
    }

    public Line getLength() {
        return length;
    }

    public Line getHeight() {
        return height;
    }
    
    public boolean isIntersectLineWidth(Point point){
    	if(this.width.getBegin() >= point.width.getBegin()){
		    if(this.length.getBegin() < point.length.getEnd()){
		        if(this.length.getEnd() > point.length.getBegin()){   
		            if(point.width.getEnd() > this.width.getBegin()){ 
		                return true;
		            }
		        }
		    }
		}
    	return false;
    }
    
    public boolean isIntersectLineLength(Point point){
    	if(this.length.getBegin() >= point.length.getBegin()){
		    if(this.width.getBegin() < point.width.getEnd()){
		        if(this.width.getEnd() > point.width.getBegin()){
		            if(point.length.getEnd() > this.length.getBegin()){
		                return true;
		            }
		        }
		    }
		}
    	return false;
    }
    
    public boolean isInBaseOfContainer(){
    	return this.height.getBegin() == 0 ? true : false;
    }
    
    public boolean isSameHeight(Item item){
    	return item.getPoint().height.getBegin() == this.height.getBegin();
    }
 
    @Override
    public int compareTo(Point point) {
        double thisBegin = width.getBegin() + length.getBegin() + height.getBegin();
        double otherBegin = point.getWidth().getBegin() + point.getLength().getBegin() + point.getHeight().getBegin();
        if(thisBegin > otherBegin){
            return 1;
        } else if (thisBegin < otherBegin) {
            return -1;
        } else {
            return 0;
        }
    }
    
    @Override
    protected Point clone() {
        return new Point(new Line(this.width.getBegin(), this.width.getEnd()), new Line(this.length.getBegin(), this.length.getEnd()), new Line(this.height.getBegin(), this.height.getEnd()));
    }
    
    @Override
    public String toString() {
        return "Point [W: " + this.width + ", L: " + this.length + ", H: " + this.height + "]";
    }

}
