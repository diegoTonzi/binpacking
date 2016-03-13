package br.com.diegotonzi.binpacking.restrictions;

import br.com.diegotonzi.binpacking.model.Bin;
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
     * @param bin to be checked
     * @return True when the bin exceeds the minimums restrictions. False otherwise
     */
    @Override
    public boolean isMinRestrictionsViolated(Bin bin) {
        if(bin.getWidth() < MIN_WIDTH_BIN) return true;
        if(bin.getLength() < MIN_LENGTH_BIN) return true;
        if(bin.getHeight() < MIN_HEIGHT_BIN) return true;
        return false;
    }

    /**
     * If the bin is out of the minimum restrictions, then, the default values of minimum restrictions are applied.
     */
    @Override
    public void minRestrictionsViolated(Bin bin) {
        if(bin.getWidth() < MIN_WIDTH_BIN) bin.setWidth(MIN_WIDTH_BIN);
        if(bin.getLength() < MIN_LENGTH_BIN) bin.setLength(MIN_LENGTH_BIN);
        if(bin.getHeight() < MIN_HEIGHT_BIN) bin.setHeight(MIN_HEIGHT_BIN);
    }

    /**
     * Checks if the bin is out of the maximum restrictions allowed by the Correios.
     * This method do not use the real measures of the bin. Is used the Updated Measures after that an item is added
     * @param bin to be checked
     * @return True when the bin exceeds the maximums restrictions. False otherwise
     */
    @Override
    public boolean isMaxRestrictionsViolated(Bin bin) {
        if((bin.getUpdatedWidth() + bin.getUpdatedLength() + bin.getUpdatedHeight()) > MAX_TOTAL_SIZE_BIN) return true;
        if(bin.getUpdatedWidth() > MAX_SIDE || bin.getUpdatedLength() > MAX_SIDE || bin.getUpdatedHeight() > MAX_SIDE) return true;
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
    public void maxRestrictionsViolated(Bin bin) {
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

