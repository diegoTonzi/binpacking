package br.com.binpacking.main;

import java.security.InvalidParameterException;
import java.util.List;

import br.com.binpacking.controller.PackingController;
import br.com.binpacking.model.Item;
import br.com.binpacking.restrictions.UnlimitedContainer;
import br.com.binpacking.util.ItemHelper;

public class Main {

	public static void main(String[] args) {
		try {
			List<Item> items = ItemHelper.getCubeItens(10, 1000);
			PackingController controller = new PackingController(items, new UnlimitedContainer());
			controller.setViewMode(true);
			controller.arrangeItens();
			controller.printResult();
		} catch (InvalidParameterException e) {
			System.out.println(e.getMessage());
		}
	}

}
