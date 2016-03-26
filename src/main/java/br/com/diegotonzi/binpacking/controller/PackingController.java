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
    
	//TODO: Alterar o m�todo Container.updateEntryPoints() para que pontos adjacentes sejam unificados
	//TODO: Adicionar restri��o quanto ao peso do pacote. Cria um m�todo com essa finalidade na interface BinRestrictions
	//TODO: Melhorar a implementa��o do m�todo Container.getBestEntryPoints() para que n�o haja repeti��o de c�digo
	
    private List<Container> containers;
    private List<Item> itens;
    private Restrictions binRestrictions;
    private boolean viewMode = false;
    private List<DrawBin> drawList;
    
    /**
     * Create a new pack controller with a list of items to organize and the bin restrictions
     * @param itens List items to organize in containers
     * @param binRestrictions The bin restrictions
     */
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

    /**
     * Organize the list of items in containers.
     * The items are arranged in a box, when not fit items inside the bin, another bin is created and this item is placed inside
     */
    public void arrangeItens() throws InvalidParameterException{
        
        // Checks if any item of the list exceed the maximum size allowed
        for (Item item : itens) {
            if(containers.get(0).getRestrictions().isMaxRestrictionsViolated(item))
                throw new InvalidParameterException("There are products that exceed the maximum size allowed for Correios");
        }
        
        // Add item into the bin
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
        
        // Checks if the bin have the minimum sizes allowed
        for (Container container : containers) {
            if(binRestrictions.isMinRestrictionsViolated(container)) {
                binRestrictions.minRestrictionsViolated(container);
            }
        }

    }
    
    /**
     * Create a new bin
     */
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
    
}//Class