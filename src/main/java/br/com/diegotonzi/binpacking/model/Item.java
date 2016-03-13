package br.com.diegotonzi.binpacking.model;

/**
 * Class that represents the product of the basket. 
 * Composed by measurements of the width, length and height of the product and the point where the item was inserted into the bin
 */
public class Item implements Comparable<Item> {
    
    private Double width;
    private Double length;
    private Double height;
    private Point point;
    
    public Item(Double width, Double length, Double height) {
        this.width = width;
        this.length = length;
        this.height = height;
        this.point = new Point(new Line(0.0, 0.0), new Line(0.0, 0.0), new Line(0.0, 0.0));
    }

    public Double getWidth() {
        return width;
    }
    
    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getLength() {
        return length;
    }
    
    public void setLength(Double length) {
        this.length = length;
    }

    public Double getHeight() {
        return height;
    }
    
    public void setHeight(Double height) {
        this.height = height;
    }
    
    public Point getPoint(){
        return this.point;
    }
    
    public void setPoint(Point point){
        this.point = point;
    }
    
    /**
     * Rotate the base of item
     */
    public void switchSides(){
        Double aux = width;
        width = length;
        length = aux;
    }
    
    /**
     * Rotate the base of item
     */
    public void switchHeightWidth(){
        Double aux = height;
        height = width;
        width = aux;
    }
    
    /**
     * Rotate the base of item
     */
    public void switchHeightLength(){
        Double aux = height;
        height = length;
        length = aux;
    }
        
    /**
     * Sort the sides of the item using as criteria, in order: height, width and length
     */
    public void sortSide(){
        Double aux = null;
        if(width > height){
            aux = height;
            height = width;
            width = aux;
        } 
        if(length > height){
            aux = height;
            height = length;
            length = aux;
        } 
        if(length > width){
            aux = width;
            width = length;
            length = aux;
        } 
    }

    /**
     * This method do not consider the measurements of the item, the rule to compare two items 
     * is to compare their volumes
     */
    @Override
    public int compareTo(Item item) {   
        double thisVolume = width * length * height;
        double otherVolume = item.getWidth() * item.getLength() * item.getHeight();
        if(thisVolume > otherVolume){
            return -1;
        } else if (thisVolume < otherVolume) {
            return 1;
        } else {
            return 0;
        }
    }
    
    @Override
    public String toString() {
        return "Item Sizes {width: " + this.width + ", length: " + this.length + ", height: " + this.height + "} - " + this.point.toString();
    }
    
}
