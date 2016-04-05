package br.com.diegotonzi.binpacking.model;

public class Item implements Comparable<Item> {
    
	private String id;
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
    	if(measures.getWidth() <= point.getWidth().getEnd() - point.getWidth().getBegin()) { 
            if (measures.getLength() <= point.getLength().getEnd() - point.getLength().getBegin()) {
            	return true;
            }
    	}    
    	return false;
    }

    public boolean canRotateVerticaly(){
    	return this.rotateVerticaly;
    }
    
    public void switchWidthLength(){
        this.measures = new Measures(measures.getLength(), measures.getWidth(), measures.getHeight(), measures.getWeight());
    }

    public void switchHeightWidth(){
        this.measures = new Measures(measures.getHeight(), measures.getLength(), measures.getWidth(), measures.getWeight());
    }
    
    public void switchHeightLength(){
        this.measures = new Measures(measures.getWidth(), measures.getHeight(), measures.getLength(), measures.getWeight());
    }

    public void sortSides(){
        if(measures.getWidth() > measures.getHeight()){
        	if(rotateVerticaly){
        		switchHeightWidth();
        	}
        } 
        if(measures.getLength() > measures.getHeight()){
        	if(rotateVerticaly){
        		switchHeightLength();
        	}
        } 
        if(measures.getLength() > measures.getWidth()){
            switchWidthLength();
        } 
    }

    @Override
    public int compareTo(Item item) {   
        return this.measures.compareTo(item.measures);
    }
    
    @Override
    protected Item clone() throws CloneNotSupportedException {
    	Measures meas = new Measures(measures.getWidth(), measures.getLength(), measures.getHeight(), measures.getWeight());
    	return new Item(meas, this.id, this.rotateVerticaly);
    }
    
    @Override
    public String toString() {
        return id + ": " + measures.toString() + " - " + point.toString();
    }
    
    public String getId(){
    	return this.id;
    }
    
    public Double getWidth(){
    	return measures.getWidth();
    }
    
    public Double getLength(){
    	return measures.getLength();
    }

	public Double getHeight(){
    	return measures.getHeight();
    }
	
	public Double getWeight(){
    	return measures.getWeight();
    }
	
	public Point getPoint(){
        return this.point;
    }
    
    public void setPoint(Point point){
        this.point = point;
    }
    
}
