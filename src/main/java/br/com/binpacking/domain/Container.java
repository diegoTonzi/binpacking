package br.com.binpacking.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.binpacking.domain.datatypes.Line;
import br.com.binpacking.domain.datatypes.Measures;
import br.com.binpacking.domain.datatypes.Point;
import br.com.binpacking.restrictions.Restrictions;

public class Container {

	private Restrictions restrictions;
	private Measures measures;
	private List<Item> items;
	private List<Point> entryPoints;

	private Measures measuresFake;
	private Double sumOfSidesFake;

	public Container(Restrictions restrictions) {
		this.restrictions = restrictions;
		this.measures = new Measures();
		this.measuresFake = new Measures();
		this.items = new ArrayList<Item>();
		this.entryPoints = new ArrayList<Point>();
		this.entryPoints.add(new Point(
				new Line(0D, restrictions.getMaxWidth()), 
				new Line(0D, restrictions.getMaxLength()),
				new Line(0D, restrictions.getMaxHeight())));
	}

	public boolean add(Item item) {
		Integer index = getBestEntryPoint(item);
		if (index == null) {
			return false;
		}
		
		Point reference = entryPoints.get(index).clone();
		add(item, reference);
		
		entryPoints.remove(index.intValue());
		createEntryPoints(item, reference);
		updateEntryPoints();
		Collections.sort(entryPoints);
		return true;
	}
	
	private Integer getBestEntryPoint(Item item) {
		this.sumOfSidesFake = 0D;
		Integer index = calculateBestEntryPoint(item);

		item.switchWidthLength();
		Integer rotatedIndex = calculateBestEntryPoint(item);
		if (rotatedIndex == null) {
			item.switchWidthLength();
		} else {
			index = rotatedIndex;
		}

		return index;
	}
	
	private Integer calculateBestEntryPoint(Item item) {
		Integer index = null;

		for (int i = 0; i < entryPoints.size(); i++) {
			Point point = entryPoints.get(i);

			if (item.fits(point)) {
				updateMeasuresFake(item, point);
				if (!restrictions.isMaxContainerRestrictionsViolated(this.measuresFake)) {
					if (measuresFake.perimeter() < this.sumOfSidesFake || this.sumOfSidesFake == 0) {
						this.sumOfSidesFake = measuresFake.perimeter();
						index = i;
					}
				}
			}
		}

		return index;
	}
	
	private void updateMeasuresFake(Item item, Point point) {
		if (point.width().begin() + item.getWidth() > measures.width()) {
			measuresFake.setWidth(point.width().begin() + item.getWidth());
		} else {
			measuresFake.setWidth(measures.width());
		}

		if (point.length().begin() + item.getLength() > measures.length()) {
			measuresFake.setLength(point.length().begin() + item.getLength());
		} else {
			measuresFake.setLength(measures.length());
		}

		if (point.height().begin() + item.getHeight() > measures.height()) {
			measuresFake.setHeight(point.height().begin() + item.getHeight());
		} else {
			measuresFake.setHeight(measures.height());
		}
	}

	private void add(Item item, Point point) {
		point.width().setEnd(point.width().begin() + item.getWidth());
		point.length().setEnd(point.length().begin() + item.getLength());
		point.height().setEnd(point.height().begin() + item.getHeight());

		item.setPoint(point);
		items.add(item);
		updateContainerSize(item);
	}

	private void updateContainerSize(Item item) {
		if (item.getPoint().width().end() > measures.width()) {
			measures.setWidth(item.getPoint().width().end());
		}

		if (item.getPoint().length().end() > measures.length()) {
			measures.setLength(item.getPoint().length().end());
		}

		if (item.getPoint().height().end() > measures.height()) {
			measures.setHeight(item.getPoint().height().end());
		}
		
		measures.setWeight(measures.weight() + item.getMeasures().weight());
		
	}

	private void createEntryPoints(Item item, Point reference) {
		if (reference.isInBaseOfContainer()) {
			createPointsInBaseOfContainer(item);
		} else {
			createPointsAboveBaseOfContainer(item, reference);
		}
		updatePointLineHeight(item);
	}

	private void createPointsInBaseOfContainer(Item item) {
		Line w = new Line(item.getPoint().width().end(), restrictions.getMaxWidth());
		Line l = new Line(item.getPoint().length().begin(), restrictions.getMaxLength());
		Line h = new Line(item.getPoint().height().begin(), restrictions.getMaxHeight());
		Point point = new Point(w, l, h);
		entryPoints.add(point);

		w = new Line(item.getPoint().width().begin(), restrictions.getMaxWidth());
		l = new Line(item.getPoint().length().end(), restrictions.getMaxLength());
		h = new Line(item.getPoint().height().begin(), restrictions.getMaxHeight());
		point = new Point(w, l, h);
		entryPoints.add(point);
	}

	private void createPointsAboveBaseOfContainer(Item item, Point reference) {
		Line w = new Line(item.getPoint().width().end(), reference.width().end());
		Line l = new Line(item.getPoint().length().begin(), reference.length().end());
		Line h = new Line(item.getPoint().height().begin(), restrictions.getMaxHeight());
		Point point = new Point(w, l, h);
		entryPoints.add(point);
		
		w = new Line(item.getPoint().width().begin(), reference.width().end());
		l = new Line(item.getPoint().length().end(), reference.length().end());
		h = new Line(item.getPoint().height().begin(), restrictions.getMaxHeight());
		point = new Point(w, l, h);
		entryPoints.add(point);
	}

	private void updatePointLineHeight(Item item) {
		Line w = new Line(item.getPoint().width().begin(), item.getPoint().width().end());
		Line l = new Line(item.getPoint().length().begin(), item.getPoint().length().end());
		Line h = new Line(item.getPoint().height().end(), restrictions.getMaxHeight());
		Point point = new Point(w, l, h);
		entryPoints.add(point);
	}

	private void updateEntryPoints() {
		for (Point entryPoint : entryPoints) {
			for (Item item : items) {
				if (entryPoint.isInBaseOfContainer() || entryPoint.isSameHeight(item)) {
					updateEntryPoints(entryPoint, item);
				}
			}
		}
	}

	private void updateEntryPoints(Point entryPoint, Item item) {
		if (item.getPoint().isIntersectLineWidth(entryPoint)) {
			entryPoint.width().setEnd(item.getPoint().width().begin());
		}
		if (item.getPoint().isIntersectLineLength(entryPoint)) {
			entryPoint.length().setEnd(item.getPoint().length().begin());
		}
	}

	public Measures getMeasures() {
		return this.measures;
	}

	public Measures getUpdatedMeasures() {
		return this.measuresFake;
	}

	public List<Item> getItems() {
		return this.items;
	}

	public List<Point> getEntryPoints() {
		return this.entryPoints;
	}

	public Restrictions getRestrictions() {
		return this.restrictions;
	}

	@Override
	public String toString() {
		return items.size() + " items \n" + "Volume: " + measures.volume() + "\n" + "Measures: " + measures.toString() + "\n";
	}

}
