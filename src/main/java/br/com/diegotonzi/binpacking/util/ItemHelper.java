package br.com.diegotonzi.binpacking.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.diegotonzi.binpacking.model.Item;
import br.com.diegotonzi.binpacking.model.Measures;

public class ItemHelper {

	
	public static List<Item> getRandomItens(int listSize){
		List<Item> items = new ArrayList<Item>();
		for (int i = 0; i < listSize; i++) {
			Item item = new Item(new Measures(rand(0.01, 10.0), rand(0.01, 10.0), rand(0.01, 10.0), rand(0.01, 10.0)));
			items.add(item);
		}
		return items;
	}
	
	private static Double rand(Double min, Double max) {
		Random rand = new Random();
		Integer result = rand.nextInt((max.intValue() - min.intValue()) + 1) + min.intValue();
		return result.doubleValue();
	}
	
	public static List<Item> getStaticItems(){
		List<Item> items = new ArrayList<Item>();
		
		items.add(new Item(new Measures(55.0, 9.0, 55.0, 0.0)));
		items.add(new Item(new Measures(55.0, 9.0, 55.0, 0.0)));
		items.add(new Item(new Measures(55.0, 9.0, 55.0, 0.0)));
		items.add(new Item(new Measures(8.0, 14.0, 14.0, 0.0)));
		items.add(new Item(new Measures(1.2, 15.0, 70.0, 0.0)));
		items.add(new Item(new Measures(1.2, 15.0, 70.0, 0.0)));
		items.add(new Item(new Measures(1.2, 15.0, 70.0, 0.0)));
		items.add(new Item(new Measures(8.0, 4.5, 9.5, 0.0)));
		items.add(new Item(new Measures(5.5, 2.0, 13.5, 0.0)));
		items.add(new Item(new Measures(5.5, 2.0, 13.5, 0.0)));
		items.add(new Item(new Measures(5.0, 2.0, 7.0, 0.0)));
		items.add(new Item(new Measures(3.8, 1.0, 5.0, 0.0)));
		items.add(new Item(new Measures(3.8, 1.0, 5.0, 0.0)));
		items.add(new Item(new Measures(3.8, 1.0, 5.0, 0.0)));
		
		items.add(new Item(new Measures(55.0, 9.0, 55.0, 0.0)));
		items.add(new Item(new Measures(55.0, 9.0, 55.0, 0.0)));
		items.add(new Item(new Measures(55.0, 9.0, 55.0, 0.0)));
		items.add(new Item(new Measures(8.0, 14.0, 14.0, 0.0)));
		items.add(new Item(new Measures(1.2, 15.0, 70.0, 0.0)));
		items.add(new Item(new Measures(1.2, 15.0, 70.0, 0.0)));
		items.add(new Item(new Measures(1.2, 15.0, 70.0, 0.0)));
		items.add(new Item(new Measures(8.0, 4.5, 9.5, 0.0)));
		items.add(new Item(new Measures(5.5, 2.0, 13.5, 0.0)));
		items.add(new Item(new Measures(5.5, 2.0, 13.5, 0.0)));
		items.add(new Item(new Measures(5.0, 2.0, 7.0, 0.0)));
		items.add(new Item(new Measures(3.8, 1.0, 5.0, 0.0)));
		items.add(new Item(new Measures(3.8, 1.0, 5.0, 0.0)));
		items.add(new Item(new Measures(3.8, 1.0, 5.0, 0.0)));
		
		
		items.add(new Item(new Measures(55.0, 9.0, 55.0, 0.0)));
		items.add(new Item(new Measures(55.0, 9.0, 55.0, 0.0)));
		items.add(new Item(new Measures(55.0, 9.0, 55.0, 0.0)));
		items.add(new Item(new Measures(8.0, 14.0, 14.0, 0.0)));
		items.add(new Item(new Measures(1.2, 15.0, 70.0, 0.0)));
		items.add(new Item(new Measures(1.2, 15.0, 70.0, 0.0)));
		items.add(new Item(new Measures(1.2, 15.0, 70.0, 0.0)));
		items.add(new Item(new Measures(8.0, 4.5, 9.5, 0.0)));
		items.add(new Item(new Measures(5.5, 2.0, 13.5, 0.0)));
		items.add(new Item(new Measures(5.5, 2.0, 13.5, 0.0)));
		items.add(new Item(new Measures(5.0, 2.0, 7.0, 0.0)));
		items.add(new Item(new Measures(3.8, 1.0, 5.0, 0.0)));
		items.add(new Item(new Measures(3.8, 1.0, 5.0, 0.0)));
		items.add(new Item(new Measures(3.8, 1.0, 5.0, 0.0)));
		
		items.add(new Item(new Measures(55.0, 9.0, 55.0, 0.0)));
		items.add(new Item(new Measures(55.0, 9.0, 55.0, 0.0)));
		items.add(new Item(new Measures(55.0, 9.0, 55.0, 0.0)));
		items.add(new Item(new Measures(8.0, 14.0, 14.0, 0.0)));
		items.add(new Item(new Measures(1.2, 15.0, 70.0, 0.0)));
		items.add(new Item(new Measures(1.2, 15.0, 70.0, 0.0)));
		items.add(new Item(new Measures(1.2, 15.0, 70.0, 0.0)));
		items.add(new Item(new Measures(8.0, 4.5, 9.5, 0.0)));
		items.add(new Item(new Measures(5.5, 2.0, 13.5, 0.0)));
		items.add(new Item(new Measures(5.5, 2.0, 13.5, 0.0)));
		items.add(new Item(new Measures(5.0, 2.0, 7.0, 0.0)));
		items.add(new Item(new Measures(3.8, 1.0, 5.0, 0.0)));
		items.add(new Item(new Measures(3.8, 1.0, 5.0, 0.0)));
		items.add(new Item(new Measures(3.8, 1.0, 5.0, 0.0)));
		
		items.add(new Item(new Measures(55.0, 9.0, 55.0, 0.0)));
		items.add(new Item(new Measures(55.0, 9.0, 55.0, 0.0)));
		items.add(new Item(new Measures(55.0, 9.0, 55.0, 0.0)));
		items.add(new Item(new Measures(8.0, 14.0, 14.0, 0.0)));
		items.add(new Item(new Measures(1.2, 15.0, 70.0, 0.0)));
		items.add(new Item(new Measures(1.2, 15.0, 70.0, 0.0)));
		items.add(new Item(new Measures(1.2, 15.0, 70.0, 0.0)));
		items.add(new Item(new Measures(8.0, 4.5, 9.5, 0.0)));
		items.add(new Item(new Measures(5.5, 2.0, 13.5, 0.0)));
		items.add(new Item(new Measures(5.5, 2.0, 13.5, 0.0)));
		items.add(new Item(new Measures(5.0, 2.0, 7.0, 0.0)));
		items.add(new Item(new Measures(3.8, 1.0, 5.0, 0.0)));
		items.add(new Item(new Measures(3.8, 1.0, 5.0, 0.0)));
		items.add(new Item(new Measures(3.8, 1.0, 5.0, 0.0)));
		
		items.add(new Item(new Measures(55.0, 9.0, 55.0, 0.0)));
		items.add(new Item(new Measures(55.0, 9.0, 55.0, 0.0)));
		items.add(new Item(new Measures(55.0, 9.0, 55.0, 0.0)));
		items.add(new Item(new Measures(8.0, 14.0, 14.0, 0.0)));
		items.add(new Item(new Measures(1.2, 15.0, 70.0, 0.0)));
		items.add(new Item(new Measures(1.2, 15.0, 70.0, 0.0)));
		items.add(new Item(new Measures(1.2, 15.0, 70.0, 0.0)));
		items.add(new Item(new Measures(8.0, 4.5, 9.5, 0.0)));
		items.add(new Item(new Measures(5.5, 2.0, 13.5, 0.0)));
		items.add(new Item(new Measures(5.5, 2.0, 13.5, 0.0)));
		items.add(new Item(new Measures(5.0, 2.0, 7.0, 0.0)));
		items.add(new Item(new Measures(3.8, 1.0, 5.0, 0.0)));
		items.add(new Item(new Measures(3.8, 1.0, 5.0, 0.0)));
		items.add(new Item(new Measures(3.8, 1.0, 5.0, 0.0)));
		
		return items;
	}
	
}
