package br.com.binpacking.restrictions;

import java.security.InvalidParameterException;

import br.com.binpacking.domain.datatypes.Measures;

public class LimitedRestrictions implements Restrictions {

	private Measures minItemRestrictions;
	private Measures maxItemRestrictions;
	private Measures minContainerRestrictions;
	private Measures maxContainerRestrictions;
	
	
	
	public LimitedRestrictions(Measures minItemRestrictions, Measures maxItemRestrictions, Measures minContainerRestrictions, Measures maxContainerRestrictions) {
		this.minItemRestrictions = minItemRestrictions;
		this.maxItemRestrictions = maxItemRestrictions;
		this.minContainerRestrictions = minContainerRestrictions;
		this.maxContainerRestrictions = maxContainerRestrictions;
	}

	@Override
	public boolean isMinContainerRestrictionsViolated(Measures measures) {
		return measures.hasLessMeasures(minContainerRestrictions);
	}

	@Override
	public void fixMinContainerRestrictionsViolated(Measures measures) {
		throw new InvalidParameterException("Some created container exceed the minimum size allowed");
	}

	@Override
	public boolean isMaxContainerRestrictionsViolated(Measures measures) {
		return measures.hasGreaterMeasures(maxContainerRestrictions);
	}

	@Override
	public void fixMaxContainerRestrictionsViolated(Measures measures) {
		throw new InvalidParameterException("Some created container exceed the maximum size allowed");

	}

	@Override
	public boolean isMinItemRestrictionsViolated(Measures measures) {
		return measures.hasLessMeasures(minItemRestrictions);
	}

	@Override
	public void fixMinItemRestrictionsViolated(Measures measures) {
		throw new InvalidParameterException("There are some item(s) that exceeds the minimum size allowed");
	}

	@Override
	public boolean isMaxItemRestrictionsViolated(Measures measures) {
		return measures.hasGreaterMeasures(maxItemRestrictions);
	}

	@Override
	public void fixMaxItemRestrictionsViolated(Measures measures) {
		throw new InvalidParameterException("There are some item(s) that exceeds the maximum size allowed");
	}

	@Override
	public Double getMaxWidth() {
		return maxContainerRestrictions.getWidth();
	}

	@Override
	public Double getMaxLength() {
		return maxContainerRestrictions.getLength();
	}

	@Override
	public Double getMaxHeight() {
		return maxContainerRestrictions.getHeight();
	}

	@Override
	public Double getMaxWeight() {
		return maxContainerRestrictions.getWeight();
	}

}
