package br.com.diegotonzi.binpacking.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.diegotonzi.binpacking.restrictions.Restrictions;
/**
 * This class is the box where the items will be packed. 
 * When a Container is created, the limits associated to the minimum sizes and maximum sizes should be informed by the {@link Restrictions} interface.
 */
public class Container {
    
    private Restrictions restrictions;
    private Double width;
    private Double length;
    private Double height;
    private Double volume;
    private List<Item> items;
    private List<Point> entryPoints;
    private Point reference;
    
    private Double widthFake = null;
    private Double lengthFake = null;
    private Double heightFake = null;
    
    
    /**
     * Constructor to create a Container with its restrictions
     */
    public Container(Restrictions restrictions) {
        this.restrictions = restrictions;
        this.width = 0.0;
        this.length = 0.0;
        this.height = 0.0;
        this.volume = 0.0;
        this.items = new ArrayList<Item>();
        this.entryPoints = new ArrayList<Point>();
        entryPoints.add(new Point(new Line(0D, restrictions.getMaxSide()), new Line(0D, restrictions.getMaxSide()), new Line(0D, restrictions.getMaxSide())));
    }

    /**
     * Try to add the item inside of bin.
     * @param item
     * @return True if the item was added. False otherwise
     */
    public boolean add(Item item){
        
        // Select the best point to insert item
        Integer position = getBestEntryPoint(item);
        
        // If the position is null, then, the item can not to be inserted
        if(position == null) return false;
        
        // Add item at the selected point
        try {
            addItem(item, (Point) entryPoints.get(position).clone());
            reference = (Point) entryPoints.get(position).clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return false;
        }
        
        // remove from the entryPoints the point where the item was added
        entryPoints.remove(position.intValue());
        
        // creates new entryPoins from the added item
        createEntryPoints(item, reference);
        
        // sort the entryPoints
        Collections.sort(entryPoints);
                
        // Update the entryPoints
        updateEntryPoints();
        
        return true;
    }
    
    /**
     * Return the best entry point to place the item
     * @param item
     * @return A index of best point to place the item
     */
    private Integer getBestEntryPoint(Item item){
        double itemWidth = item.getWidth();
        double itemLength = item.getLength();
        double itemHeight = item.getHeight();
        Integer index = null;
        this.volume = 0.0;
        
        for (int i = 0; i < entryPoints.size(); i++) {
                
            // Checks if the item fits in the length and width measurements of point
            if(item.getWidth() <= entryPoints.get(i).getWidth().getEnd() - entryPoints.get(i).getWidth().getBegin()) { 
                if (item.getLength() <= entryPoints.get(i).getLength().getEnd() - entryPoints.get(i).getLength().getBegin()) {
                    
                	widthFake = entryPoints.get(i).getWidth().getBegin() + item.getWidth() > this.width? entryPoints.get(i).getWidth().getBegin() + item.getWidth() : this.width;
                	lengthFake = entryPoints.get(i).getLength().getBegin() + item.getLength() > this.length? entryPoints.get(i).getLength().getBegin() + item.getLength() : this.length;
                	heightFake = entryPoints.get(i).getHeight().getBegin() + item.getHeight() > this.height? entryPoints.get(i).getHeight().getBegin() + item.getHeight() : this.height;
                    
                    // Checks if the new measurements of the bin exceed the maximum allowed
                    if(!restrictions.isMaxRestrictionsViolated(this)){
                        
                        // If the item is placed in this point, the volume of the bin will be lower than that other points?
                        if (widthFake * lengthFake * heightFake < this.volume || this.volume == 0) {
                        	this.volume = widthFake * lengthFake * heightFake;
                        	itemWidth = item.getWidth();
                        	itemLength = item.getLength();
                        	itemHeight = item.getHeight();
                            index = i;
                        } 
                        
                    }
                    
                }
            }

        }
        
        item.switchSides();

        for (int i = 0; i < entryPoints.size(); i++) {
            
            // Checks if the item fits in the length and width measurements of point
            if(item.getWidth() <= entryPoints.get(i).getWidth().getEnd() - entryPoints.get(i).getWidth().getBegin()) { 
                if (item.getLength() <= entryPoints.get(i).getLength().getEnd() - entryPoints.get(i).getLength().getBegin()) {
                    
                	widthFake = entryPoints.get(i).getWidth().getBegin() + item.getWidth() > this.width? entryPoints.get(i).getWidth().getBegin() + item.getWidth() : this.width;
                	lengthFake = entryPoints.get(i).getLength().getBegin() + item.getLength() > this.length? entryPoints.get(i).getLength().getBegin() + item.getLength() : this.length;
                	heightFake = entryPoints.get(i).getHeight().getBegin() + item.getHeight() > this.height? entryPoints.get(i).getHeight().getBegin() + item.getHeight() : this.height;
                    
                    // Checks if the new measurements of the bin exceed the maximum allowed
                    if(!restrictions.isMaxRestrictionsViolated(this)){
                        
                        // Se o item for colocado neste ponto, o volume da caixa ficar� menor do que nos outros pontos?
                        if (widthFake * lengthFake * heightFake < this.volume || this.volume == 0) {
                        	this.volume = widthFake * lengthFake * heightFake;
                        	itemWidth = item.getWidth();
                        	itemLength = item.getLength();
                        	itemHeight = item.getHeight();
                            index = i;
                        } 
                        
                    }
                    
                }
            }
            
        }
        
        item.setWidth(itemWidth);
        item.setLength(itemLength);
        item.setHeight(itemHeight);
        
        return index;
    } 
        
    /**
     * Add the item in the point of the bin
     * @param item to add
     * @param point to place the item
     */
    private void addItem(Item item, Point point){

        // Update the point used and insert it into the item
        point.getWidth().setEnd(point.getWidth().getBegin() + item.getWidth());
        point.getLength().setEnd(point.getLength().getBegin() + item.getLength());
        point.getHeight().setEnd(point.getHeight().getBegin() + item.getHeight());
        
        // add item in a list items
        item.setPoint(point);
        items.add(item);

        // update the bin sizes
        this.width = point.getWidth().getEnd() > this.width? point.getWidth().getEnd() : this.width;
        this.length = point.getLength().getEnd() > this.length? point.getLength().getEnd() : this.length;
        this.height = point.getHeight().getEnd() > this.height? point.getHeight().getEnd() : this.height;
        this.volume = this.width * this.length * this.height;
        
    }
    
    /**
     * Through the added item, create the new entry points
     * @param item
     */
    private void createEntryPoints(Item item, Point reference){

    	// Checks if the item was placed in the base of bin
    	if(reference.getHeight().getBegin() == 0){
    		
    		// Create the width point when item was placed in the base of bin
            Line w = new Line(item.getPoint().getWidth().getEnd(), restrictions.getMaxSide());
            Line l = new Line(item.getPoint().getLength().getBegin(), restrictions.getMaxSide());
            Line h = new Line(item.getPoint().getHeight().getBegin(), restrictions.getMaxSide());
            Point point = new Point(w, l, h);
            entryPoints.add(point);
        
            // Create the length point when item was placed in the base of bin
            w = new Line(item.getPoint().getWidth().getBegin(), restrictions.getMaxSide());
            l = new Line(item.getPoint().getLength().getEnd(), restrictions.getMaxSide());
            h = new Line(item.getPoint().getHeight().getBegin(), restrictions.getMaxSide());
            point = new Point(w, l, h);
            entryPoints.add(point);
            
    	} else {
    		
    		// Create the width point when item was placed above the base of bin
            Line w = new Line(item.getPoint().getWidth().getEnd(), reference.getWidth().getEnd());
            Line l = new Line(item.getPoint().getLength().getBegin(), reference.getLength().getEnd());
            Line h = new Line(item.getPoint().getHeight().getBegin(), restrictions.getMaxSide());
            Point point = new Point(w, l, h);
            entryPoints.add(point);
        
            // Create the length point when item was placed above the base of bin
            w = new Line(item.getPoint().getWidth().getBegin(), reference.getWidth().getEnd());
            l = new Line(item.getPoint().getLength().getEnd(), reference.getLength().getEnd());
            h = new Line(item.getPoint().getHeight().getBegin(), restrictions.getMaxSide());
            point = new Point(w, l, h);
            entryPoints.add(point);
    		
    	}
    	
    	// Create the top point. Top point is always the same when the item is placed above or in the base of bin
    	Line w = new Line(item.getPoint().getWidth().getBegin(), item.getPoint().getWidth().getEnd());
    	Line l = new Line(item.getPoint().getLength().getBegin(), item.getPoint().getLength().getEnd());
    	Line h = new Line(item.getPoint().getHeight().getEnd(), restrictions.getMaxSide());
    	Point point = new Point(w, l, h);
        entryPoints.add(point);

    }

    /**
     * Update the entry points when a new item is added.
     * When a item is added in a bin, its can limit any line of the entry points.
     * If this occurs, then, the lines of the point is updated 
     */
    private void updateEntryPoints(){

    	for (Point entryPoint : entryPoints) {
            for (Item item : items) {
                
                // Update the entry points that are in the base of bin.
                if(entryPoint.getHeight().getBegin() == 0){
                    
                    // Checks if an item intersects the line width of the point
                    if(item.getPoint().getWidth().getBegin() >= entryPoint.getWidth().getBegin()){
                        if(item.getPoint().getLength().getBegin() < entryPoint.getLength().getEnd()){
                            if(item.getPoint().getLength().getEnd() > entryPoint.getLength().getBegin()){   
                                if(entryPoint.getWidth().getEnd() > item.getPoint().getWidth().getBegin()){ 
                                    entryPoint.getWidth().setEnd(item.getPoint().getWidth().getBegin());
                                }
                            }
                        }
                    }
                    
                    // Checks if an item intersects the line length of the point
                    if(item.getPoint().getLength().getBegin() >= entryPoint.getLength().getBegin()){
                        if(item.getPoint().getWidth().getBegin() < entryPoint.getWidth().getEnd()){
                            if(item.getPoint().getWidth().getEnd() > entryPoint.getWidth().getBegin()){
                                if(entryPoint.getLength().getEnd() > item.getPoint().getLength().getBegin()){
                                    entryPoint.getLength().setEnd(item.getPoint().getLength().getBegin());
                                }
                            }
                        }
                    }
                  
                } else {
                	
                	// Checks if the item and the entryPoint are in the same Height
                	if(item.getPoint().getHeight().getBegin() == entryPoint.getHeight().getBegin()){
                		
                		// Checks if an item intersects the line width of the point
	                    if(item.getPoint().getWidth().getBegin() >= entryPoint.getWidth().getBegin()){
	                        if(item.getPoint().getLength().getBegin() < entryPoint.getLength().getEnd()){
	                            if(item.getPoint().getLength().getEnd() > entryPoint.getLength().getBegin()){   
	                                if(entryPoint.getWidth().getEnd() > item.getPoint().getWidth().getBegin()){ 
	                                    entryPoint.getWidth().setEnd(item.getPoint().getWidth().getBegin());
	                                }
	                            }
	                        }
	                    }
                	
	                    // Checks if an item intersects the line length of the point
	                    if(item.getPoint().getLength().getBegin() >= entryPoint.getLength().getBegin()){
	                        if(item.getPoint().getWidth().getBegin() < entryPoint.getWidth().getEnd()){
	                            if(item.getPoint().getWidth().getEnd() > entryPoint.getWidth().getBegin()){
	                                if(entryPoint.getLength().getEnd() > item.getPoint().getLength().getBegin()){
	                                    entryPoint.getLength().setEnd(item.getPoint().getLength().getBegin());
	                                }
	                            }
	                        }
	                    }
	                    
                	}          
                	
                }
                
            }
        }
    }
    
    /**
     * Returns the value of the width of the box after/if an item is added.
     * This measure does not represent the actual width of the box, its only the possible width of the box if certain item is added.
     * After an item is added, the width is updated and is possible to get the actual width using {@link #getWidth}
     */
    public Double getUpdatedWidth() {
        return widthFake;
    }
    
    /**
     * Returns the value of the length of the box after/if an item is added.
     * This measure does not represent the actual length of the box, its only the possible length of the box if certain item is added.
     * After an item is added, the length is updated and is possible to get the actual width using {@link #getLength()}
     */
    public Double getUpdatedLength() {
        return lengthFake;
    }
    
    /**
     * Returns the value of the height of the box after/if an item is added.
     * This measure does not represent the actual height of the box, its only the possible height of the box if certain item is added.
     * After an item is added, the height is updated and is possible to get the actual height using {@link #getHeight()}
     */
    public Double getUpdatedHeight() {
        return heightFake;
    }
    
    
    public Double getWidth() {
        return width;
    }
    
    public void setWidth(Double width){
        this.width = width;
    }

    public Double getLength() {
        return length;
    }
    
    public void setLength(Double length){
        this.length = length;
    }

    public Double getHeight() {
        return height;
    }
    
    public void setHeight(Double height){
        this.height = height;
    }

    public List<Item> getItens(){
        return this.items;
    }
    
    public List<Point> getEntryPoints(){
        return this.entryPoints;
    }
    
    public Restrictions getRestrictions(){
        return this.restrictions;
    }
    
    @Override
    public String toString() {
        String result = "Container items: " +  items.size() + "\n";
        result += "Container Volume: " + (this.width * this.length * this.height + "\n");
        result += "Container sizes {width: " + this.width + ", length: " + this.length + ", height: " + this.height + "}";
        
        return result;
    }
    
}
