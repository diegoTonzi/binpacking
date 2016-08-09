package br.com.binpacking.domain;

import br.com.binpacking.domain.datatypes.Line;
import br.com.binpacking.domain.datatypes.Measures;
import br.com.binpacking.domain.datatypes.Point;

public class Item implements Comparable<Item> {
    
	private String id;
	private String description;
    private Measures measures;
    private boolean rotateVerticaly;
    private Point point;
    
    
    public Item(Measures measures, String id, boolean rotateVerticaly) {
    	this.id = id;
        this.measures = measures;
        this.rotateVerticaly = rotateVerticaly;
        this.point = new Point(new Line(0.0, 0.0), new Line(0.0, 0.0), new Line(0.0, 0.0));
    }
    
    public boolean fits(Point point){
    	if(measures.width() <= point.width().end() - point.width().begin()) { 
            if (measures.length() <= point.length().end() - point.length().begin()) {
            	return true;
            }
    	}    
    	return false;
    }

    public boolean canRotateVerticaly(){
    	return this.rotateVerticaly;
    }
    
    public void switchWidthLength(){
        this.measures = new Measures(measures.length(), measures.width(), measures.height(), measures.weight());
    }

    public void switchHeightWidth(){
        this.measures = new Measures(measures.height(), measures.length(), measures.width(), measures.weight());
    }
    
    public void switchHeightLength(){
        this.measures = new Measures(measures.width(), measures.height(), measures.length(), measures.weight());
    }

    public void sortSides(){
        if(measures.width() < measures.height()){
        	if(rotateVerticaly){
        		switchHeightWidth();
        	}
        } 
        if(measures.length() > measures.height()){
        	if(rotateVerticaly){
        		switchHeightLength();
        	}
        } 
        if(measures.length() > measures.width()){
            switchWidthLength();
        } 
    }

    @Override
    public int compareTo(Item other) {   
    	int widthResult = this.getWidth().compareTo(other.getWidth());
    	if(widthResult != 0){
    		return widthResult;
    	}
    	
    	int lengthResult = this.getLength().compareTo(other.getLength());
    	if(lengthResult != 0){
    		return lengthResult;
    	}
    	
    	int heightResult = this.getHeight().compareTo(other.getHeight());
    	if(heightResult != 0){
    		return heightResult;
    	}

    	return 0;
//        return this.measures.compareTo(item.measures);
    }
    
    @Override
    protected Item clone() throws CloneNotSupportedException {
    	Measures size = new Measures(measures.width(), measures.length(), measures.height(), measures.weight());
    	return new Item(size, this.id, this.rotateVerticaly);
    }
    
    @Override
    public String toString() {
        return id + ": " + measures.toString() + " - " + point.toString();
    }
    
    public String getId(){
    	return this.id;
    }
    
    public Measures getMeasures(){
    	return this.measures;
    }
    
    public Double getWidth(){
    	return measures.width();
    }
    
    public Double getLength(){
    	return measures.length();
    }

	public Double getHeight(){
    	return measures.height();
    }
	
	public Double getWeight(){
    	return measures.weight();
    }
	
	public Point getPoint(){
        return this.point;
    }
	
	public boolean isRotateVerticaly(){
		return this.rotateVerticaly;
	}
	
	public String getDescription(){
		return this.description;
	}
    
	public void setDescription(String description){
		this.description = description;
	}
	
    public void setPoint(Point point){
        this.point = point;
    }
    
}
