package br.com.binpacking.main;

import java.security.InvalidParameterException;
import java.util.List;

import br.com.binpacking.domain.Item;
import br.com.binpacking.domain.Packer;
import br.com.binpacking.domain.datatypes.Measures;
import br.com.binpacking.restrictions.LimitedRestrictions;
import br.com.binpacking.restrictions.Restrictions;
import br.com.binpacking.util.ItemHelper;

public class Main {

	public static void main(String[] args) {
		try {
			List<Item> items = ItemHelper.getCubeItens(10, 3001);
//			List<Item> items = ItemHelper.getRandomItens(3000);
//			List<Item> items = ItemHelper.getStaticItems();
			Restrictions restrictions = getLimitedRestriction();
			new Packer.Pack(items)
					.use(restrictions)
					.enableLog()
					.enableGraphical()
					.start();
			
		} catch (InvalidParameterException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static Restrictions getLimitedRestriction(){
		return new LimitedRestrictions(
				new Measures(0D, 0D, 0D, 0D), 
				new Measures(100D, 100D, 100D, Double.MAX_VALUE), 
				new Measures(0D, 0D, 0D, 0D), 
				new Measures(100D, 100D, 100D, Double.MAX_VALUE));
	}

}
