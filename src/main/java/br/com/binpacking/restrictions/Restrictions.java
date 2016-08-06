package br.com.binpacking.restrictions;

import br.com.binpacking.domain.datatypes.Measures;

public interface Restrictions {

	boolean isMinContainerRestrictionsViolated(Measures measures);

	void fixMinContainerRestrictionsViolated(Measures measures);

	boolean isMaxContainerRestrictionsViolated(Measures measures);

	void fixMaxContainerRestrictionsViolated(Measures measures);
	
	boolean isMinItemRestrictionsViolated(Measures measures);

	void fixMinItemRestrictionsViolated(Measures measures);

	boolean isMaxItemRestrictionsViolated(Measures measures);

	void fixMaxItemRestrictionsViolated(Measures measures);

	Double getMaxWidth();

	Double getMaxLength();

	Double getMaxHeight();

	Double getMaxWeight();

}
