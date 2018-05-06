package com.sampalmer.techtest.service;

import com.sampalmer.techtest.PropertyRepository;
import com.sampalmer.techtest.model.Property;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PropertyService {

	private final PropertyRepository repository;

	public PropertyService(PropertyRepository repository) {
		this.repository = repository;
	}

	public Double getAverageByPostcodeOutward(String postcodeOutward) {
		return repository.findAveragePriceByPostcodeOutward(postcodeOutward);
	}

	public Double getAverageByPropertyType(Property.PropertyType propertyType) {
		return repository.findAveragePriceByPropertyType(propertyType);
	}

	public List<Property> getTopPercentMostExpensive(Integer percent) {
		Long count = repository.count();
		Long limit = Math.round(count / (100.0/percent));
		return repository.getPropertiesWithLimit(limit);
	}
}
