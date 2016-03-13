package br.com.diegotonzi.binpacking.controller;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.diegotonzi.binpacking.model.Bin;
import br.com.diegotonzi.binpacking.model.Item;
import br.com.diegotonzi.binpacking.restrictions.Restrictions;
import br.com.diegotonzi.binpacking.util.DrawBin;

/**
 * Class responsible for organizing items in bins. 
 * When a bin can't include another item, then, another bin is created
 */
public class PackingController {
    
	//TODO: Alterar o método Bin.updateEntryPoints() para que pontos adjacentes sejam unificados
	//TODO: Adicionar restrição quanto ao peso do pacote. Cria um método com essa finalidade na interface BinRestrictions
	//TODO: Melhorar a implementação do método Bin.getBestEntryPoints() para que não haja repetição de código
	
    private List<Bin> bins;
    private List<Item> itens;
    private Restrictions binRestrictions;
    private boolean viewMode = false;
    private List<DrawBin> drawList;
    
    /**
     * Create a new pack controller with a list of items to organize and the bin restrictions
     * @param itens List items to organize in bins
     * @param binRestrictions The bin restrictions
     */
    public PackingController(List<Item> itens, Restrictions binRestrictions, boolean viewMode) {
        this.binRestrictions = binRestrictions;
        bins = new ArrayList<Bin>();
        bins.add(new Bin(this.binRestrictions));
        
        this.viewMode = viewMode;
        if(this.viewMode){
        	drawList = new ArrayList<DrawBin>();
        	drawList.add(new DrawBin());
        }
        
        this.itens = itens;
        for (Item item : itens) item.sortSide();
        Collections.sort(this.itens);
    }

    /**
     * Organize the list of items in bins.
     * The items are arranged in a box, when not fit items inside the bin, another bin is created and this item is placed inside
     */
    public void arrangeItens() throws InvalidParameterException{
        
        // Checks if any item of the list exceed the maximum size allowed
        for (Item item : itens) {
            if(bins.get(0).getRestrictions().isMaxRestrictionsViolated(item))
                throw new InvalidParameterException("There are products that exceed the maximum size allowed for Correios");
        }
        
        // Add item into the bin
        boolean added;
        for (Item item : itens) {
            added = false;
            for (int i = 0; i < bins.size(); i++) {
                added = bins.get(i).add(item);
                if(added) break;
            }
            if(!added){
                createBin();
                bins.get(bins.size() - 1).add(item);
            }

            if(this.viewMode){
	    		for (int i = 0; i < bins.size(); i++) {
					if(drawList.get(i) != null){
						drawList.get(i).drawing(bins.get(i));
					}
				}
            }
            
        }
        
        // Checks if the bin have the minimum sizes allowed
        for (Bin bin : bins) {
            if(binRestrictions.isMinRestrictionsViolated(bin)) {
                binRestrictions.minRestrictionsViolated(bin);
            }
        }

    }
    
    /**
     * Create a new bin
     */
    private void createBin(){
        bins.add(new Bin(this.binRestrictions));
        if(this.viewMode){
        	drawList.add(new DrawBin());
        }
    }
    
    public List<Bin> getBins(){
        return bins;
    }

    public void printResult(){
        int count = 0;
        for (Bin bin : bins) {
            count++;
            System.out.println("------------------------------------------------------------------------------------------------------------\n");
            System.out.println("Bin " + count);
            System.out.println(bin.toString());
            String itemsStr = "Items:\n";
            for (Item item : bin.getItens()) {
                itemsStr += item.toString() + "\n";
            }
            System.out.println(itemsStr);   
        }
    }
    
}//Class