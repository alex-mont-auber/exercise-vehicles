package com.vehicles.project;

public class Wheel {
	private String brand;
	private double diameter;

	public Wheel(String brand, double diameter)
	{
		this.brand = brand;
		this.diameter = diameter;
	}
	// DESESPERATION ZONE
	@Override
	public String toString(){
		return "Wheel [brand=" + brand + ", diameter=" + diameter + "]";
	}
		//Rewritte the equals method, for work when do Try/Catch.
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Wheel other = (Wheel) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (Double.doubleToLongBits(diameter) != Double.doubleToLongBits(other.diameter))
			return false;
		return true;
	}
	// DESESPERATION ZONE
}
