package br.com.diegotonzi.binpacking.controller;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.diegotonzi.binpacking.model.Container;
import br.com.diegotonzi.binpacking.model.Item;
import br.com.diegotonzi.binpacking.restrictions.Restrictions;
import br.com.diegotonzi.binpacking.util.DrawBin;

/**
 * Class responsible for organizing items in containers. 
 * When a bin can't include another item, then, another bin is created
 */
public class PackingController {

    private List<Container> containers;
    private List<Item> itens;
    private Restrictions binRestrictions;
    private boolean viewMode = false;
    private List<DrawBin> drawList;
    
    public PackingController(List<Item> itens, Restrictions binRestrictions, boolean viewMode) {
        this.binRestrictions = binRestrictions;
        containers = new ArrayList<Container>();
        containers.add(new Container(this.binRestrictions));
        
        this.viewMode = viewMode;
        if(this.viewMode){
        	drawList = new ArrayList<DrawBin>();
        	drawList.add(new DrawBin());
        }
        
        this.itens = itens;
        for (Item item : itens) item.sortSides();
        Collections.sort(this.itens);
    }

    public void arrangeItens() throws InvalidParameterException{
        for (Item item : itens) {
            if(containers.get(0).getRestrictions().isMaxRestrictionsViolated(item))
                throw new InvalidParameterException("There are products that exceed the maximum size allowed for Correios");
        }
        
        boolean added;
        for (Item item : itens) {
            added = false;
            for (int i = 0; i < containers.size(); i++) {
                added = containers.get(i).add(item);
                if(added) break;
            }
            if(!added){
                createBin();
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
            if(binRestrictions.isMinRestrictionsViolated(container)) {
                binRestrictions.minRestrictionsViolated(container);
            }
        }

    }

    private void createBin(){
        containers.add(new Container(this.binRestrictions));
        if(this.viewMode){
        	drawList.add(new DrawBin());
        }
    }
    
    public List<Container> getBins(){
        return containers;
    }

    public void printResult(){
        int count = 0;
        for (Container container : containers) {
            count++;
            System.out.println("------------------------------------------------------------------------------------------------------------\n");
            System.out.println("Container " + count);
            System.out.println(container.toString());
            String itemsStr = "Items:\n";
            for (Item item : container.getItens()) {
                itemsStr += item.toString() + "\n";
            }
            System.out.println(itemsStr);   
        }
    }
    
}