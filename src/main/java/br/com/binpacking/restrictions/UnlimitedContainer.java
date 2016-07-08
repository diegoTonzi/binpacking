package br.com.binpacking.restrictions;

import br.com.binpacking.model.Container;
import br.com.binpacking.model.Item;

public class UnlimitedContainer implements Restrictions {

    private final Double MIN_HEIGHT_CONTAINER = 0D;
    private final Double MAX_HEIGHT_CONTAINER = 100D;
    
    private final Double MIN_WIDTH_CONTAINER = 0D;
    private final Double MAX_WIDTH_CONTAINER = 100D;
    
    private final Double MIN_LENGTH_CONTAINER = 0D;
    private final Double MAX_LENGTH_CONTAINER = 100D;
    
    private final Double MIN_WEIGHT_CONTAINER = 0D;
    private final Double MAX_WEIGHT_CONTAINER = Double.MAX_VALUE;
    
    private final Double MAX_TOTAL_SIZE_CONTAINER = Double.MAX_VALUE;
    
    @Override
    public boolean isMinRestrictionsViolated(Container container) {
        return false;
    }

    @Override
    public void minRestrictionsViolated(Container container) {
    }

    @Override
    public boolean isMaxRestrictionsViolated(Container container) {
        return false;
    }

    public boolean isMaxRestrictionsViolated(Item item){
        return false;
    }
    
    @Override
    public void maxRestrictionsViolated(Container container) {
    }
    
    
    @Override
    public Double getMinHeight() {
        return MIN_HEIGHT_CONTAINER;
    }
    
    @Override
    public Double getMaxHeight() {
    	return MAX_HEIGHT_CONTAINER;
    }

    @Override
    public Double getMinWidth() {
        return MIN_WIDTH_CONTAINER;
    }
    
    @Override
    public Double getMaxWidth() {
    	return MAX_WIDTH_CONTAINER;
    }

    @Override
    public Double getMinLength() {
        return MIN_LENGTH_CONTAINER;
    }

    @Override
    public Double getMaxLength() {
    	return MAX_LENGTH_CONTAINER;
    }
    
    @Override
    public Double getMinWeight() {
    	return MIN_WEIGHT_CONTAINER;
    }
    
    @Override
    public Double getMaxWeight() {
    	return MAX_WEIGHT_CONTAINER;
    }
    
    @Override
    public Double getMaxTotalSize() {
        return MAX_TOTAL_SIZE_CONTAINER;
    }

}

