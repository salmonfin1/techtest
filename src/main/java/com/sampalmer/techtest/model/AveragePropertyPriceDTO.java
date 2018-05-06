package com.sampalmer.techtest.model;

public class AveragePropertyPriceDTO {

	private Property.PropertyType propertyType;

	private Double averagePrice;

	public AveragePropertyPriceDTO(Property.PropertyType propertyType, Double averagePrice) {
		this.propertyType = propertyType;
		this.averagePrice = averagePrice;
	}

	public Property.PropertyType getPropertyType() {
		return propertyType;
	}

	public Double getAveragePrice() {
		return averagePrice;
	}
}
