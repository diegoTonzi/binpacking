package br.com.diegotonzi.binpacking.restrictions;

import br.com.diegotonzi.binpacking.model.Container;
import br.com.diegotonzi.binpacking.model.Item;

/**
 * Represents the size restrictions of the container. <br/> 
�* When items are packaged, one of the criteria used to decide if is necessary create a new package to accommodate the items is when the maximum and minimum sizes are reached. <br/> 
�* So, this interface allows the algorithm use different packaging rules in relation to the size of the containers
 */
public interface Restrictions {

    /**
     * Method used to verify if the container is out of the minimum restrictions allowed.
     * If is not necessary to verify the minimums restrictions, only return false.
     * @param container used to verify the restrictions
     * @returns true if the minimum restrictions were violated, false otherwise.
     */
    boolean isMinRestrictionsViolated(Container container);
    
    /**
     * Method used to apply the rules when the minimum restrictions are violated
     * @param container to be applied the rules
     */
    void minRestrictionsViolated(Container container);
    
    /**
     * Method used to verify if the container is out of the maximum restriction allowed.
     * If is not necessary to verify the maximum restrictions, only return false.
     * @param container used to verify the restrictions
     * @returns true if the maximum restrictions were violated, false otherwise.
     */
    boolean isMaxRestrictionsViolated(Container container);
    
    /**
     * Method used to verify if one Item is out of the maximum restriction allowed.
     * This verification is necessary, because when a item is greater than the maximum allowed, means that is not possible to pack the item
     * If is not necessary to verify the maximum restrictions, only return false.
     * @param item used to verify the restrictions
     * @returns true if the maximum restrictions were violated, false otherwise.
     */
    boolean isMaxRestrictionsViolated(Item item);
    
    /**
     * Method used to apply the rules when the maximum restrictions are violated
     * @param container to be applied the rules
     */
    void maxRestrictionsViolated(Container container);
    
    Double getMinHeight();
    
    Double getMaxHeight();
    
    Double getMinWidth();
    
    Double getMaxWidth();
    
    Double getMinLength();
    
    Double getMaxLength();
    
    Double getMinWeight();
    
    Double getMaxWeight();
    
    /**
     * Maximum value allowed for the sum of sides of a container
     */
    Double getMaxTotalSize();
    
}
