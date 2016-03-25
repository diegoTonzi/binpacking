package br.com.diegotonzi.binpacking.restrictions;

import br.com.diegotonzi.binpacking.model.Container;
import br.com.diegotonzi.binpacking.model.Item;

/**
 * Represents the package restrictions used by Correios 
 */
public class CorreiosPac implements Restrictions {

    private final Double MIN_HEIGHT_BIN = 2.0;
    private final Double MIN_WIDTH_BIN = 11.0;
    private final Double MIN_LENGTH_BIN = 16.0;
    private final Double MAX_TOTAL_SIZE_BIN = 200.0;
    private final Double MAX_SIDE = 105.0;
    
    /**
     * Checks if the bin is out of the minimum restrictions allowed by the Correios.
     * @param container to be checked
     * @return True when the bin exceeds the minimums restrictions. False otherwise
     */
    @Override
    public boolean isMinRestrictionsViolated(Container container) {
        if(container.getWidth() < MIN_WIDTH_BIN) return true;
        if(container.getLength() < MIN_LENGTH_BIN) return true;
        if(container.getHeight() < MIN_HEIGHT_BIN) return true;
        return false;
    }

    /**
     * If the bin is out of the minimum restrictions, then, the default values of minimum restrictions are applied.
     */
    @Override
    public void minRestrictionsViolated(Container container) {
        if(container.getWidth() < MIN_WIDTH_BIN) container.setWidth(MIN_WIDTH_BIN);
        if(container.getLength() < MIN_LENGTH_BIN) container.setLength(MIN_LENGTH_BIN);
        if(container.getHeight() < MIN_HEIGHT_BIN) container.setHeight(MIN_HEIGHT_BIN);
    }

    /**
     * Checks if the bin is out of the maximum restrictions allowed by the Correios.
     * This method do not use the real measures of the bin. Is used the Updated Measures after that an item is added
     * @param container to be checked
     * @return True when the bin exceeds the maximums restrictions. False otherwise
     */
    @Override
    public boolean isMaxRestrictionsViolated(Container container) {
        if((container.getUpdatedWidth() + container.getUpdatedLength() + container.getUpdatedHeight()) > MAX_TOTAL_SIZE_BIN) return true;
        if(container.getUpdatedWidth() > MAX_SIDE || container.getUpdatedLength() > MAX_SIDE || container.getUpdatedHeight() > MAX_SIDE) return true;
        return false;
    }

    /**
     * Checks if one Item is out of the maximum restriction allowed by the Correios.
     * @param item to be checked
     * @return True when the item exceeds the maximum size allowed. False otherwise
     */
    public boolean isMaxRestrictionsViolated(Item item){
        if((item.getWidth() + item.getLength() + item.getHeight()) > MAX_TOTAL_SIZE_BIN) return true;
        if(item.getWidth() > MAX_SIDE || item.getLength() > MAX_SIDE || item.getHeight() > MAX_SIDE) return true;
        return false;
    }
    
    @Override
    public void maxRestrictionsViolated(Container container) {
    }
    
    
    @Override
    public Double getMinHeight() {
        return MIN_HEIGHT_BIN;
    }

    @Override
    public Double getMinWidth() {
        return MIN_WIDTH_BIN;
    }

    @Override
    public Double getMinLength() {
        return MIN_LENGTH_BIN;
    }

    @Override
    public Double getMaxTotalSize() {
        return MAX_TOTAL_SIZE_BIN;
    }

    @Override
    public Double getMaxSide() {
        return MAX_SIDE;
    }

}//Class

