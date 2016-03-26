package br.com.diegotonzi.binpacking.model;

public class Measures implements Comparable<Measures>{

	private Double width;
	private Double length;
	private Double height;
	private Double weight;
	
	public Measures(Double width, Double length, Double height, Double weight) {
		this.width = width;
		this.length = length;
		this.height = height;
		this.weight = weight;
	}

	@Override
	public int compareTo(Measures other) {
        if(this.getVolume() > other.getVolume()){
            return -1;
        } else if (this.getVolume() < other.getVolume()) {
            return 1;
        } else {
            return 0;
        }
	}
	
	@Override
	public String toString() {
		return new StringBuilder()
				.append("[Width: " + this.width)
				.append(", Length: " + this.length)
				.append(", Height: " + this.height )
				.append(", Volume: " + this.getVolume() )
				.append(", Sum of Sides: " + this.getSumOfSides() )
				.append(", Weight: " + this.weight )
				.append("]")
				.toString();
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getSumOfSides() {
		return this.height + this.length + this.width;
	}
	
	public Double getVolume() {
		return this.height * this.length * this.width;
	}

}
