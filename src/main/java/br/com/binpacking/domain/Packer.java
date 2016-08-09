package br.com.binpacking.domain;

import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import br.com.binpacking.restrictions.NoRestrictions;
import br.com.binpacking.restrictions.Restrictions;
import br.com.binpacking.util.DrawContainer;

public class Packer {

	private List<Item> items;
	private Restrictions restrictions;
	private boolean graphicalResult;
	private boolean logResult;
	private List<Container> containers;
	private List<DrawContainer> drawList;
	private Calendar startPacking;
	private Calendar endPacking;

	public Packer(Pack pack) {
		items = pack.getItems();
		restrictions = pack.getRestrictions();
		graphicalResult = pack.isGraphicalEnable();
		logResult = pack.isLogEnable();
		
		checkItemRestrictions();

		if (this.graphicalResult) {
			drawList = new ArrayList<DrawContainer>();
			drawList.add(new DrawContainer());
		}

		containers = new ArrayList<Container>();
		containers.add(new Container(this.restrictions));

		for (Item item : items) {
			item.sortSides();
		}
		
		Collections.sort(this.items);
		Collections.reverse(this.items);
	}

	private void checkItemRestrictions() {
		for (Item item : items) {
			if (restrictions.isMaxItemRestrictionsViolated(item.getMeasures())){
				restrictions.fixMaxItemRestrictionsViolated(item.getMeasures());
			}
			if (restrictions.isMinItemRestrictionsViolated(item.getMeasures())){
				restrictions.fixMinItemRestrictionsViolated(item.getMeasures());
			}
		}
	}

	public Packer arrangeItems() {
		this.startPacking = Calendar.getInstance();

		boolean added;
		for (Item item : items) {
			added = false;
			for (int i = 0; i < containers.size(); i++) {
				added = containers.get(i).add(item);
				if (added) {
					break;
				}
			}
			if (!added) {
				createContainer();
				containers.get(containers.size() - 1).add(item);
			}
			showGraphicalResult();
		}
		checkContainerRestrictions();

		this.endPacking = Calendar.getInstance();
		this.logResult();
		return this;
	}

	private void showGraphicalResult() {
		if (this.graphicalResult) {
			for (int i = 0; i < containers.size(); i++) {
				if (drawList.get(i) != null) {
					drawList.get(i).drawing(containers.get(i));
				}
			}
		}
	}

	private void createContainer() {
		containers.add(new Container(this.restrictions));
		if (this.graphicalResult) {
			drawList.add(new DrawContainer());
		}
	}
	
	private void checkContainerRestrictions() {
		for (Container container : containers) {
			if (restrictions.isMaxContainerRestrictionsViolated(container.getMeasures())){
				restrictions.fixMaxContainerRestrictionsViolated(container.getMeasures());
			}
			if (restrictions.isMinContainerRestrictionsViolated(container.getMeasures())){
				restrictions.fixMinContainerRestrictionsViolated(container.getMeasures());
			}
		}
	}

	private void logResult(){
		if(logResult){
	        int count = 0;
	        for (Container container : containers) {
	            count++;
	            System.out.println("------------------------------------------------------------------------------------------------------------\n");
	            System.out.println("Container " + count);
	            System.out.println(container.toString());
	            String itemsStr = "Items:\n";
	            for (Item item : container.getItems()) {
	                itemsStr += item.toString() + "\n";
	            }
	            System.out.println(itemsStr);   
	        }
	        
	        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyy - HH:mm:ss:SSS");
	        System.out.println("------------------------------------------------------------------------------------------------------------\n");
	        System.out.println("Start Packing: " + fmt.format(startPacking.getTime()));
	        System.out.println("End Packing: " + fmt.format(endPacking.getTime()));
	        System.out.println("Total time: " + (endPacking.getTimeInMillis() - startPacking.getTimeInMillis()) + " millis");
		} 
    }
	
	public List<Container> getContainers() {
		return containers;
	}

	public Calendar getStartPacking() {
		return startPacking;
	}

	public Calendar getEndPacking() {
		return endPacking;
	}
	
	public static class Pack {

		private List<Item> items;
		private Restrictions restrictions;
		private boolean graphical;
		private boolean log;

		public Pack(List<Item> items) {
			if(items == null || items.isEmpty()){
				throw new InvalidParameterException("Inform the items to be pack");
			}
			this.items = items;
			this.restrictions = new NoRestrictions();
		}

		public Pack use(Restrictions restrictions) {
			this.restrictions = restrictions;
			return this;
		}

		public Pack enableLog() {
			this.log = true;
			return this;
		}

		public Pack enableGraphical() {
			this.graphical = true;
			return this;
		}

		public Packer start() {
			return new Packer(this).arrangeItems();
		}

		public List<Item> getItems() {
			return items;
		}

		public Restrictions getRestrictions() {
			return restrictions;
		}

		public boolean isGraphicalEnable() {
			return graphical;
		}

		public boolean isLogEnable() {
			return log;
		}

	}

}
