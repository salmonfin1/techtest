package com.sampalmer.techtest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PROPERTY")
public class Property {

	public enum PropertyType {
		Mansion,
		Terraced,
		Detached,
		Flat
	}

	@Id
	@Column(name = "PROPERTY_REFERENCE")
	private Long propertyReference;

	@Column(name = "PRICE")
	private Long price;

	@Column(name = "BEDROOMS")
	private Integer bedroooms;

	@Column(name = "BATHROOMS")
	private Integer bathrooms;

	@Column(name = "HOUSE_NUMBER")
	private String houseNumber;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "REGION")
	private String region;

	@Column(name = "POSTCODE")
	private String postcode;

	@Enumerated(EnumType.STRING)
	@Column(name = "PROPERTY_TYPE")
	private PropertyType propertyType;

	public Long getPropertyReference() {
		return propertyReference;
	}

	public void setPropertyReference(Long propertyReference) {
		this.propertyReference = propertyReference;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Integer getBedroooms() {
		return bedroooms;
	}

	public void setBedroooms(Integer bedroooms) {
		this.bedroooms = bedroooms;
	}

	public Integer getBathrooms() {
		return bathrooms;
	}

	public void setBathrooms(Integer bathrooms) {
		this.bathrooms = bathrooms;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public PropertyType getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(PropertyType propertyType) {
		this.propertyType = propertyType;
	}
}
