package br.com.binpacking.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.binpacking.domain.Item;
import br.com.binpacking.domain.datatypes.Measures;

public class ItemHelper {

	public static List<Item> getCubeItens(double faceCubeSize, int quantity){
		List<Item> items = new ArrayList<Item>();
		for (int i = 0; i < quantity; i++) {
			Item item = new Item(new Measures(faceCubeSize, faceCubeSize, faceCubeSize, faceCubeSize), "id " + i, false);
			items.add(item);
		}
		return items;
	}
	
	public static List<Item> getRandomItens(int listSize){
		List<Item> items = new ArrayList<Item>();
		for (int i = 0; i < listSize; i++) {
			Item item = new Item(new Measures(rand(0.01, 10.0), rand(0.01, 10.0), rand(0.01, 10.0), rand(0.01, 10.0)), "id " + i, false);
			items.add(item);
		}
		return items;
	}
	
	private static Double rand(Double min, Double max) {
		Random rand = new Random();
		Integer result = rand.nextInt((max.intValue() - min.intValue()) + 1) + min.intValue();
		return result.doubleValue();
	}
	
	public static List<Item> getitems(){
		List<Item> items = new ArrayList<>();
		
		Measures measures = new Measures(10D, 20D, 15D, 0D);
		for (int i = 0; i < 60; i++) {
			Item item = new Item(measures, String.valueOf(i), true);
			items.add(item);
		}
		
		measures = new Measures(10D, 12D, 10D, 0D);
		for (int i = 0; i < 100; i++) {
			Item item = new Item(measures, String.valueOf(60 + i), true);
			items.add(item);
		}
		
		return items;
	}
	
	public static List<Item> getStaticItems(){
		List<Item> items = new ArrayList<Item>();
		
		items.add(new Item(new Measures(55.0, 9.0, 55.0, 0.0), "item " + 1, true));
		items.add(new Item(new Measures(55.0, 9.0, 55.0, 0.0), "item " + 2, true));
		items.add(new Item(new Measures(55.0, 9.0, 55.0, 0.0), "item " + 3, true));
		items.add(new Item(new Measures(8.0, 14.0, 14.0, 0.0), "item " + 4, true));
		items.add(new Item(new Measures(1.2, 15.0, 70.0, 0.0), "item " + 5, true));
		items.add(new Item(new Measures(1.2, 15.0, 70.0, 0.0), "item " + 6, true));
		items.add(new Item(new Measures(1.2, 15.0, 70.0, 0.0), "item " + 7, true));
		items.add(new Item(new Measures(8.0, 4.5, 9.5, 0.0), "item " + 8, true));
		items.add(new Item(new Measures(5.5, 2.0, 13.5, 0.0), "item " + 9, true));
		items.add(new Item(new Measures(5.5, 2.0, 13.5, 0.0), "item " + 10, true));
		items.add(new Item(new Measures(5.0, 2.0, 7.0, 0.0), "item " + 11, true));
		items.add(new Item(new Measures(3.8, 1.0, 5.0, 0.0), "item " + 12, true));
		items.add(new Item(new Measures(3.8, 1.0, 5.0, 0.0), "item " + 13, true));
		items.add(new Item(new Measures(3.8, 1.0, 5.0, 0.0), "item " + 14, true));
		
		items.add(new Item(new Measures(55.0, 9.0, 55.0, 0.0), "item " + 15, true));
		items.add(new Item(new Measures(55.0, 9.0, 55.0, 0.0), "item " + 16, true));
		items.add(new Item(new Measures(55.0, 9.0, 55.0, 0.0), "item " + 17, true));
		items.add(new Item(new Measures(8.0, 14.0, 14.0, 0.0), "item " + 18, true));
		items.add(new Item(new Measures(1.2, 15.0, 70.0, 0.0), "item " + 19, true));
		items.add(new Item(new Measures(1.2, 15.0, 70.0, 0.0), "item " + 20, true));
		items.add(new Item(new Measures(1.2, 15.0, 70.0, 0.0), "item " + 21, true));
		items.add(new Item(new Measures(8.0, 4.5, 9.5, 0.0), "item " + 22, true));
		items.add(new Item(new Measures(5.5, 2.0, 13.5, 0.0), "item " + 23, true));
		items.add(new Item(new Measures(5.5, 2.0, 13.5, 0.0), "item " + 24, true));
		items.add(new Item(new Measures(5.0, 2.0, 7.0, 0.0), "item " + 25, true));
		items.add(new Item(new Measures(3.8, 1.0, 5.0, 0.0), "item " + 26, true));
		items.add(new Item(new Measures(3.8, 1.0, 5.0, 0.0), "item " + 27, true));
		items.add(new Item(new Measures(3.8, 1.0, 5.0, 0.0), "item " + 28, true));
		
		
		items.add(new Item(new Measures(55.0, 9.0, 55.0, 0.0), "item " + 29, true));
		items.add(new Item(new Measures(55.0, 9.0, 55.0, 0.0), "item " + 30, true));
		items.add(new Item(new Measures(55.0, 9.0, 55.0, 0.0), "item " + 31, true));
		items.add(new Item(new Measures(8.0, 14.0, 14.0, 0.0), "item " + 32, true));
		items.add(new Item(new Measures(1.2, 15.0, 70.0, 0.0), "item " + 33, true));
		items.add(new Item(new Measures(1.2, 15.0, 70.0, 0.0), "item " + 34, true));
		items.add(new Item(new Measures(1.2, 15.0, 70.0, 0.0), "item " + 35, true));
		items.add(new Item(new Measures(8.0, 4.5, 9.5, 0.0), "item " + 36, true));
		items.add(new Item(new Measures(5.5, 2.0, 13.5, 0.0), "item " + 37, true));
		items.add(new Item(new Measures(5.5, 2.0, 13.5, 0.0), "item " + 38, true));
		items.add(new Item(new Measures(5.0, 2.0, 7.0, 0.0), "item " + 39, true));
		items.add(new Item(new Measures(3.8, 1.0, 5.0, 0.0), "item " + 40, true));
		items.add(new Item(new Measures(3.8, 1.0, 5.0, 0.0), "item " + 41, true));
		items.add(new Item(new Measures(3.8, 1.0, 5.0, 0.0), "item " + 42, true));
		
		items.add(new Item(new Measures(55.0, 9.0, 55.0, 0.0), "item " + 43, true));
		items.add(new Item(new Measures(55.0, 9.0, 55.0, 0.0), "item " + 44, true));
		items.add(new Item(new Measures(55.0, 9.0, 55.0, 0.0), "item " + 45, true));
		items.add(new Item(new Measures(8.0, 14.0, 14.0, 0.0), "item " + 46, true));
		items.add(new Item(new Measures(1.2, 15.0, 70.0, 0.0), "item " + 47, true));
		items.add(new Item(new Measures(1.2, 15.0, 70.0, 0.0), "item " + 48, true));
		items.add(new Item(new Measures(1.2, 15.0, 70.0, 0.0), "item " + 49, true));
		items.add(new Item(new Measures(8.0, 4.5, 9.5, 0.0), "item " + 50, true));
		items.add(new Item(new Measures(5.5, 2.0, 13.5, 0.0), "item " + 51, true));
		items.add(new Item(new Measures(5.5, 2.0, 13.5, 0.0), "item " + 52, true));
		items.add(new Item(new Measures(5.0, 2.0, 7.0, 0.0), "item " + 53, true));
		items.add(new Item(new Measures(3.8, 1.0, 5.0, 0.0), "item " + 54, true));
		items.add(new Item(new Measures(3.8, 1.0, 5.0, 0.0), "item " + 55, true));
		items.add(new Item(new Measures(3.8, 1.0, 5.0, 0.0), "item " + 56, true));
		
		items.add(new Item(new Measures(55.0, 9.0, 55.0, 0.0), "item " + 57, true));
		items.add(new Item(new Measures(55.0, 9.0, 55.0, 0.0), "item " + 58, true));
		items.add(new Item(new Measures(55.0, 9.0, 55.0, 0.0), "item " + 59, true));
		items.add(new Item(new Measures(8.0, 14.0, 14.0, 0.0), "item " + 60, true));
		items.add(new Item(new Measures(1.2, 15.0, 70.0, 0.0), "item " + 61, true));
		items.add(new Item(new Measures(1.2, 15.0, 70.0, 0.0), "item " + 62, true));
		items.add(new Item(new Measures(1.2, 15.0, 70.0, 0.0), "item " + 63, true));
		items.add(new Item(new Measures(8.0, 4.5, 9.5, 0.0), "item " + 64, true));
		items.add(new Item(new Measures(5.5, 2.0, 13.5, 0.0), "item " + 65, true));
		items.add(new Item(new Measures(5.5, 2.0, 13.5, 0.0), "item " + 66, true));
		items.add(new Item(new Measures(5.0, 2.0, 7.0, 0.0), "item " + 67, true));
		items.add(new Item(new Measures(3.8, 1.0, 5.0, 0.0), "item " + 68, true));
		items.add(new Item(new Measures(3.8, 1.0, 5.0, 0.0), "item " + 69, true));
		items.add(new Item(new Measures(3.8, 1.0, 5.0, 0.0), "item " + 70, true));
		
		items.add(new Item(new Measures(55.0, 9.0, 55.0, 0.0), "item " + 71, true));
		items.add(new Item(new Measures(55.0, 9.0, 55.0, 0.0), "item " + 72, true));
		items.add(new Item(new Measures(55.0, 9.0, 55.0, 0.0), "item " + 73, true));
		items.add(new Item(new Measures(8.0, 14.0, 14.0, 0.0), "item " + 74, true));
		items.add(new Item(new Measures(1.2, 15.0, 70.0, 0.0), "item " + 75, true));
		items.add(new Item(new Measures(1.2, 15.0, 70.0, 0.0), "item " + 76, true));
		items.add(new Item(new Measures(1.2, 15.0, 70.0, 0.0), "item " + 77, true));
		items.add(new Item(new Measures(8.0, 4.5, 9.5, 0.0), "item " + 78, true));
		items.add(new Item(new Measures(5.5, 2.0, 13.5, 0.0), "item " + 79, true));
		items.add(new Item(new Measures(5.5, 2.0, 13.5, 0.0), "item " + 80, true));
		items.add(new Item(new Measures(5.0, 2.0, 7.0, 0.0), "item " + 81, true));
		items.add(new Item(new Measures(3.8, 1.0, 5.0, 0.0), "item " + 82, true));
		items.add(new Item(new Measures(3.8, 1.0, 5.0, 0.0), "item " + 83, true));
		items.add(new Item(new Measures(3.8, 1.0, 5.0, 0.0), "item " + 84, true));
		
		return items;
	}
	
}
