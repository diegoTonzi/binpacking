package br.com.binpacking.domain.datatypes;

public class Measures implements Comparable<Measures>{

	private Double width;
	private Double length;
	private Double height;
	private Double weight;
	
	public Measures() {
		this.width = 0D;
		this.length = 0D;
		this.height = 0D;
		this.weight = 0D;
	}
	
	public Measures(Double width, Double length, Double height, Double weight) {
		this.width = width;
		this.length = length;
		this.height = height;
		this.weight = weight;
	}

	@Override
	public int compareTo(Measures other) {
        if(this.volume() > other.volume()){
            return -1;
        } else if (this.volume() < other.volume()) {
            return 1;
        } else {
            return 0;
        }
	}
	
	public boolean hasLessMeasures(Measures toCompare){
		if(this.width < toCompare.width() || 
				this.length < toCompare.length() || 
				this.height < toCompare.height() ||
				this.weight < toCompare.weight()){
			
			return true;
		}
		
		return false;
	}
	
	public boolean hasGreaterMeasures(Measures toCompare){
		if(this.width > toCompare.width() || 
				this.length > toCompare.length() || 
				this.height > toCompare.height() ||
				this.weight > toCompare.weight()){
			
			return true;
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return "Measures [W: " + this.width + ", L: " + this.length + ", H: " + this.height + ", WG: " + this.weight +"] ";
	}

	public Double width() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double length() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public Double height() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double weight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double perimeter() {
		return this.height + this.length + this.width;
	}
	
	public Double volume() {
		return this.height * this.length * this.width;
	}

}
