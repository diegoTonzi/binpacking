package br.com.diegotonzi.binpacking.restrictions;

import br.com.diegotonzi.binpacking.model.Container;
import br.com.diegotonzi.binpacking.model.Item;

public class UnlimitedPac implements Restrictions {

    private final Double MIN_HEIGHT_CONTAINER = 0D;
    private final Double MIN_WIDTH_CONTAINER = 0D;
    private final Double MIN_LENGTH_CONTAINER = 0D;
    private final Double MAX_TOTAL_SIZE_CONTAINER = Double.MAX_VALUE;
    private final Double MAX_SIDE = Double.MAX_VALUE;
    
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
    public Double getMinWidth() {
        return MIN_WIDTH_CONTAINER;
    }

    @Override
    public Double getMinLength() {
        return MIN_LENGTH_CONTAINER;
    }

    @Override
    public Double getMaxTotalSize() {
        return MAX_TOTAL_SIZE_CONTAINER;
    }

    @Override
    public Double getMaxSide() {
        return MAX_SIDE;
    }

}

