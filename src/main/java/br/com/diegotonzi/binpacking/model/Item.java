package br.com.diegotonzi.binpacking.model;

public class Item implements Comparable<Item> {
    
    private Measures measures;
    private Point point;
    
    public Item(Measures measures) {
        this.measures = measures;
        this.point = new Point(new Line(0.0, 0.0), new Line(0.0, 0.0), new Line(0.0, 0.0));
    }

    public Measures getMeasures(){
    	return this.measures;
    }
    
    public Point getPoint(){
        return this.point;
    }
    
    public void setPoint(Point point){
        this.point = point;
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
            switchHeightWidth();
        } 
        if(measures.getLength() > measures.getHeight()){
            switchHeightLength();
        } 
        if(measures.getLength() > measures.getWidth()){
            switchWidthLength();
        } 
    }

    @Override
    public int compareTo(Item item) {   
        return this.measures.compareTo(item.getMeasures());
    }
    
    @Override
    public String toString() {
        return "Item Sizes {width: " + this.measures.getWidth() + ", length: " + this.measures.getLength() + ", height: " + this.measures.getHeight() + "} - " + this.point.toString();
    }
    
}
