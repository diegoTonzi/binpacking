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
        if(container.getMeasures().getWidth() < MIN_WIDTH_BIN) return true;
        if(container.getMeasures().getLength() < MIN_LENGTH_BIN) return true;
        if(container.getMeasures().getHeight() < MIN_HEIGHT_BIN) return true;
        return false;
    }

    /**
     * If the bin is out of the minimum restrictions, then, the default values of minimum restrictions are applied.
     */
    @Override
    public void minRestrictionsViolated(Container container) {
        if(container.getMeasures().getWidth() < MIN_WIDTH_BIN) container.getMeasures().setWidth(MIN_WIDTH_BIN);
        if(container.getMeasures().getLength() < MIN_LENGTH_BIN) container.getMeasures().setLength(MIN_LENGTH_BIN);
        if(container.getMeasures().getHeight() < MIN_HEIGHT_BIN) container.getMeasures().setHeight(MIN_HEIGHT_BIN);
    }

    /**
     * Checks if the bin is out of the maximum restrictions allowed by the Correios.
     * This method do not use the real measures of the bin. Is used the Updated Measures after that an item is added
     * @param container to be checked
     * @return True when the bin exceeds the maximums restrictions. False otherwise
     */
    @Override
    public boolean isMaxRestrictionsViolated(Container container) {
        if((container.getUpdatedMeasures().getWidth() + container.getUpdatedMeasures().getLength() + container.getUpdatedMeasures().getHeight()) > MAX_TOTAL_SIZE_BIN) return true;
        if(container.getUpdatedMeasures().getWidth() > MAX_SIDE || container.getUpdatedMeasures().getLength() > MAX_SIDE || container.getUpdatedMeasures().getHeight() > MAX_SIDE) return true;
        return false;
    }

    /**
     * Checks if one Item is out of the maximum restriction allowed by the Correios.
     * @param item to be checked
     * @return True when the item exceeds the maximum size allowed. False otherwise
     */
    public boolean isMaxRestrictionsViolated(Item item){
        if((item.getMeasures().getWidth() + item.getMeasures().getLength() + item.getMeasures().getHeight()) > MAX_TOTAL_SIZE_BIN) return true;
        if(item.getMeasures().getWidth() > MAX_SIDE || item.getMeasures().getLength() > MAX_SIDE || item.getMeasures().getHeight() > MAX_SIDE) return true;
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

