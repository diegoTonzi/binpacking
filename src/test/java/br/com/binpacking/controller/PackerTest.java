package br.com.binpacking.controller;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import br.com.binpacking.domain.Item;
import br.com.binpacking.domain.Packer;
import br.com.binpacking.domain.datatypes.Measures;
import br.com.binpacking.restrictions.LimitedRestrictions;
import br.com.binpacking.restrictions.Restrictions;
import br.com.binpacking.util.ItemHelper;

public class PackerTest {

	@Test
	public void packaginCubeItemsInLimmitedContainer(){
		List<Item> items = ItemHelper.getCubeItens(10, 1001);
		Restrictions restrictions = getLimitedRestriction();
		Packer packer = new Packer.Pack(items)
				.use(restrictions)
				.start();
		assertEquals(packer.getContainers().size(), 2);
		assertEquals(packer.getContainers().get(1).getItems().size(), 1);
	}
	
	private Restrictions getLimitedRestriction(){
		return new LimitedRestrictions(
				new Measures(0D, 0D, 0D, 0D), 
				new Measures(100D, 100D, 100D, Double.MAX_VALUE), 
				new Measures(0D, 0D, 0D, 0D), 
				new Measures(100D, 100D, 100D, Double.MAX_VALUE));
	}
	
}
