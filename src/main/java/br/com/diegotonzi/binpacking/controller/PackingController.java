package br.com.diegotonzi.binpacking.controller;

import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import br.com.diegotonzi.binpacking.model.Container;
import br.com.diegotonzi.binpacking.model.Item;
import br.com.diegotonzi.binpacking.restrictions.Restrictions;
import br.com.diegotonzi.binpacking.util.DrawContainer;

/**
 * Class responsible for organizing items in containers. 
 * When a Container can't include another item, then, another Container is created
 */
public class PackingController {

    private List<Container> containers;
    private List<Item> itens;
    private Restrictions restrictions;
    private boolean viewMode = false;
    private List<DrawContainer> drawList;
    private Calendar startPacking;
    private Calendar endPacking;
    
    public PackingController(List<Item> itens, Restrictions containerRestrictions) {
        this.restrictions = containerRestrictions;
        containers = new ArrayList<Container>();
        containers.add(new Container(this.restrictions));
        
        this.itens = itens;
        for (Item item : itens) {
        	item.sortSides();
        }
        Collections.sort(this.itens);
    }

    public void arrangeItens() throws InvalidParameterException{
    	this.startPacking = Calendar.getInstance();
    	
        for (Item item : itens) {
            if(containers.get(0).getRestrictions().isMaxRestrictionsViolated(item))
                throw new InvalidParameterException("There are products that exceed the maximum size allowed for Correios");
        }
        
        boolean added;
        for (Item item : itens) {
            added = false;
            for (int i = 0; i < containers.size(); i++) {
                added = containers.get(i).add(item);
                if(added) {
                	break;
                }
            }
            if(!added){
                createContainer();
                containers.get(containers.size() - 1).add(item);
            }

            if(this.viewMode){
	    		for (int i = 0; i < containers.size(); i++) {
					if(drawList.get(i) != null){
						drawList.get(i).drawing(containers.get(i));
					}
				}
            }
            
        }
        
        for (Container container : containers) {
            if(restrictions.isMinRestrictionsViolated(container)) {
                restrictions.minRestrictionsViolated(container);
            }
        }

        this.endPacking = Calendar.getInstance();
    }

    private void createContainer(){
        containers.add(new Container(this.restrictions));
        if(this.viewMode){
        	drawList.add(new DrawContainer());
        }
    }
    
    public List<Container> getContainers(){
        return containers;
    }
    
    public void setViewMode(boolean viewMode){
    	this.viewMode = viewMode;
    	
    	if(this.viewMode){
        	drawList = new ArrayList<DrawContainer>();
        	drawList.add(new DrawContainer());
        }
    }

    public Calendar getStartPacking() {
		return startPacking;
	}

	public Calendar getEndPacking() {
		return endPacking;
	}

	public void printResult(){
        int count = 0;
        for (Container container : containers) {
            count++;
            System.out.println("------------------------------------------------------------------------------------------------------------\n");
            System.out.println("Container " + count);
            System.out.println(container.toString());
            String itemsStr = "Items:\n";
            for (Item item : container.getItems()) {
                itemsStr += item.toString() + "\n";
            }
            System.out.println(itemsStr);   
        }
        
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyy - HH:mm:ss:SSS");
        System.out.println("------------------------------------------------------------------------------------------------------------\n");
        System.out.println("Start Packing: " + fmt.format(startPacking.getTime()));
        System.out.println("End Packing: " + fmt.format(endPacking.getTime()));
        System.out.println("Total time: " + (endPacking.getTimeInMillis() - startPacking.getTimeInMillis()) + " millis");
        
    }
    
}