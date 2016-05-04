package br.com.diegotonzi.binpacking.restrictions;

import br.com.diegotonzi.binpacking.model.Container;
import br.com.diegotonzi.binpacking.model.Item;

public class LimitedContainer implements Restrictions {

    private Double minHeight;
    private Double maxHeight;
    
    private Double minWidth;
    private Double maxWidth;
    
    private Double minLength;
    private Double maxLength;
    
    private Double minWeight;
    private Double maxWeight;
    
    private Double maxTotalSize;
    
    
    public LimitedContainer(Double maxWidth, Double maxLength, Double maxHeight, Double maxWeight) {
		this.maxHeight = maxHeight;
		this.maxWidth = maxWidth;
		this.maxLength = maxLength;
		this.maxWeight = maxWeight;
	}

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
        return minHeight;
    }
    
    @Override
    public Double getMaxHeight() {
    	return maxHeight;
    }

    @Override
    public Double getMinWidth() {
        return minWidth;
    }
    
    @Override
    public Double getMaxWidth() {
    	return maxWidth;
    }

    @Override
    public Double getMinLength() {
        return minLength;
    }

    @Override
    public Double getMaxLength() {
    	return maxLength;
    }
    
    @Override
    public Double getMinWeight() {
    	return minWeight;
    }
    
    @Override
    public Double getMaxWeight() {
    	return maxWeight;
    }
    
    @Override
    public Double getMaxTotalSize() {
        return maxTotalSize;
    }

	public void setMinHeight(Double minHeight) {
		this.minHeight = minHeight;
	}

	public void setMinWidth(Double minWidth) {
		this.minWidth = minWidth;
	}

	public void setMinLength(Double minLength) {
		this.minLength = minLength;
	}

	public void setMinWeight(Double minWeight) {
		this.minWeight = minWeight;
	}

	public void setMaxTotalSize(Double maxTotalSize) {
		this.maxTotalSize = maxTotalSize;
	}

}

