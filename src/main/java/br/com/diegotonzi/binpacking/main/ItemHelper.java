package br.com.diegotonzi.binpacking.main;

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
	
}
