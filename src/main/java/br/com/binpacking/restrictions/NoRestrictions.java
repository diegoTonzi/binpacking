package br.com.binpacking.restrictions;

import br.com.binpacking.domain.datatypes.Measures;

public class NoRestrictions implements Restrictions {

	@Override
	public boolean isMinContainerRestrictionsViolated(Measures measures) {
		return false;
	}

	@Override
	public void fixMinContainerRestrictionsViolated(Measures measures) {
		// Do nothing;
	}

	@Override
	public boolean isMaxContainerRestrictionsViolated(Measures measures) {
		return false;
	}

	@Override
	public void fixMaxContainerRestrictionsViolated(Measures measures) {
		// Do nothing;
	}

	@Override
	public boolean isMinItemRestrictionsViolated(Measures measures) {
		return false;
	}

	@Override
	public void fixMinItemRestrictionsViolated(Measures measures) {
		// Do nothing;
	}

	@Override
	public boolean isMaxItemRestrictionsViolated(Measures measures) {
		return false;
	}

	@Override
	public void fixMaxItemRestrictionsViolated(Measures measures) {
		// Do nothing;
	}

	@Override
	public Double getMaxWidth() {
		return Double.MAX_VALUE;
	}

	@Override
	public Double getMaxLength() {
		return Double.MAX_VALUE;
	}

	@Override
	public Double getMaxHeight() {
		return Double.MAX_VALUE;
	}

	@Override
	public Double getMaxWeight() {
		return Double.MAX_VALUE;
	}

}
