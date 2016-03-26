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
    private Measures measures;
    private Measures measuresFake;
    private List<Item> items;
    private List<Point> entryPoints;
    private Point reference;

    
    public Container(Restrictions restrictions) {
        this.restrictions = restrictions;
        this.measures = new Measures(0D, 0D, 0D, 0D);
        this.measuresFake = new Measures(0D, 0D, 0D, 0D);
        this.items = new ArrayList<Item>();
        this.entryPoints = new ArrayList<Point>();
        entryPoints.add(new Point(new Line(0D, restrictions.getMaxSide()), new Line(0D, restrictions.getMaxSide()), new Line(0D, restrictions.getMaxSide())));
    }

    public boolean add(Item item){
        
        Integer position = getBestEntryPoint(item);
        if(position == null) return false;
        
        try {
            addItem(item, (Point) entryPoints.get(position).clone());
            reference = (Point) entryPoints.get(position).clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return false;
        }
        
        entryPoints.remove(position.intValue());
        createEntryPoints(item, reference);
        Collections.sort(entryPoints);
        updateEntryPoints();
        return true;
    }

    private Integer getBestEntryPoint(Item item){
        double itemWidth = item.getWidth();
        double itemLength = item.getLength();
        double itemHeight = item.getHeight();
        Integer index = null;
        Double volume = 0D;
        
        for (int j = 0; j < 2; j++) {
	        for (int i = 0; i < entryPoints.size(); i++) {
	                
	            // Checks if the item fits in the length and width measurements of point
	            if(item.getWidth() <= entryPoints.get(i).getWidth().getEnd() - entryPoints.get(i).getWidth().getBegin()) { 
	                if (item.getLength() <= entryPoints.get(i).getLength().getEnd() - entryPoints.get(i).getLength().getBegin()) {
	                    	
	                	if(entryPoints.get(i).getWidth().getBegin() + item.getWidth() > measures.getWidth()){
	                		measuresFake.setWidth(entryPoints.get(i).getWidth().getBegin() + item.getWidth());
	                	} else {
	                		measuresFake.setWidth(measures.getWidth());
	                	}
	                	
	                	if(entryPoints.get(i).getLength().getBegin() + item.getLength() > measures.getLength()){
	                		measuresFake.setLength(entryPoints.get(i).getLength().getBegin() + item.getLength());
	                	} else {
	                		measuresFake.setLength(measures.getLength());
	                	}
	                			
	                	if(entryPoints.get(i).getHeight().getBegin() + item.getHeight() > measures.getHeight()){
	                		measuresFake.setHeight(entryPoints.get(i).getHeight().getBegin() + item.getHeight());
	                	} else {
	                		measuresFake.setHeight(measures.getHeight());
	                	}
	                	
	                    // Checks if the new measurements of the bin exceed the maximum allowed
	                    if(!restrictions.isMaxRestrictionsViolated(this)){
	                        
	                        // If the item is placed in this point, the volume of the bin will be lower than that other points?
	                        if (measuresFake.getVolume() < volume || volume == 0) {
	                        	volume = measuresFake.getVolume();
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
    	}
        
        item.setWidth(itemWidth);
        item.setLength(itemLength);
        item.setHeight(itemHeight);
        
        return index;
    } 

    private void addItem(Item item, Point point){

        // Update the point used and insert it into the item
        point.getWidth().setEnd(point.getWidth().getBegin() + item.getWidth());
        point.getLength().setEnd(point.getLength().getBegin() + item.getLength());
        point.getHeight().setEnd(point.getHeight().getBegin() + item.getHeight());
        
        // add item in a list items
        item.setPoint(point);
        items.add(item);

        // update the bin sizes
        if(point.getWidth().getEnd() > measures.getWidth()){
        	measures.setWidth(point.getWidth().getEnd());
        }

		if(point.getLength().getEnd() > measures.getLength()){
			measures.setLength(point.getLength().getEnd());
        }
        		
		if(point.getHeight().getEnd() > measures.getHeight()){
			measures.setHeight(point.getHeight().getEnd());
        }
        
    }

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
    
    public Measures getMeasures(){
    	return this.measures;
    }
    
    public Measures getUpdatedMeasures(){
    	return this.measuresFake;
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
        result += "Container Volume: " + measures.getVolume();
        result += "Container sizes {width: " + measures.getWidth() + ", length: " + measures.getLength() + ", height: " + measures.getHeight() + "}";
        
        return result;
    }
    
}
