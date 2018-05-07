package com.sampalmer.techtest.controller;

import com.sampalmer.techtest.model.AveragePropertyPriceDTO;
import com.sampalmer.techtest.model.Property;
import com.sampalmer.techtest.service.PropertyService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.sampalmer.techtest.model.Property.PropertyType;

@RestController
public class PropertyController {

	private PropertyService propertyService;

	public PropertyController(PropertyService propertyService) {
		this.propertyService = propertyService;
	}

	@RequestMapping("postcodemean")
	public @ResponseBody ResponseEntity<Double> meanPostcodeOutward(@RequestParam String postcodeOutward) {
		Double average = propertyService.getAverageByPostcodeOutward(postcodeOutward.toUpperCase());
		return ResponseEntity.ok(average);
	}

	@RequestMapping("averagedifference")
	public @ResponseBody
	ResponseEntity<AveragePropertyPriceDTO> averageByPropertyType(@RequestParam(value = "propertyType") PropertyType propertyType) {
		Double average = propertyService.getAverageByPropertyType(propertyType);
		return ResponseEntity.ok(new AveragePropertyPriceDTO(propertyType, average));
	}

	@RequestMapping("topmostexpensive")
	public @ResponseBody ResponseEntity<List<Property>> topTenMostExpensive(@RequestParam Integer percent) {
		List<Property> properties = propertyService.getTopPercentMostExpensive(percent);
		return ResponseEntity.ok(properties);
	}

}
