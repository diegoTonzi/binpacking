package br.com.diegotonzi.binpacking.main;

import java.security.InvalidParameterException;
import java.util.List;

import br.com.diegotonzi.binpacking.controller.PackingController;
import br.com.diegotonzi.binpacking.model.Item;
import br.com.diegotonzi.binpacking.restrictions.UnlimitedContainer;
import br.com.diegotonzi.binpacking.util.ItemHelper;

public class Main {

	public static void main(String[] args) {
		try {
			List<Item> items = ItemHelper.getStaticItems();
			PackingController controller = new PackingController(items, new UnlimitedContainer());
			controller.setViewMode(true);
			controller.arrangeItens();
			controller.printResult();
		} catch (InvalidParameterException e) {
			System.out.println(e.getMessage());
		}
	}

}
