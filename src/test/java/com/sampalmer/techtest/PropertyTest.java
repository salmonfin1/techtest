package com.sampalmer.techtest;

import com.sampalmer.techtest.model.Property;
import com.sampalmer.techtest.service.PropertyService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PropertyTest {

	private PropertyRepository repository;

	private PropertyService propertyService;

	@Before
	public void setup() {
		repository = Mockito.mock(PropertyRepository.class);
		propertyService = new PropertyService(repository);
	}

	@Test
	public void testAverageByPostcode() {
		when(repository.findAveragePriceByPostcodeOutward("W1F")).thenReturn(100.0);
		Double average = propertyService.getAverageByPostcodeOutward("W1F");
		assertEquals(average, Double.valueOf(100));
		verify(repository, times(1)).findAveragePriceByPostcodeOutward("W1F");
	}

	@Test
	public void testAverageByPropertyType() {
		Property.PropertyType propertyType = Property.PropertyType.Flat;
		when(repository.findAveragePriceByPropertyType(propertyType)).thenReturn(100.0);
		Double average = propertyService.getAverageByPropertyType(propertyType);
		assertEquals(average, Double.valueOf(100));
		verify(repository, times(1)).findAveragePriceByPropertyType(propertyType);
	}

	@Test
	public void testTopMostExpensive() {
		when(repository.count()).thenReturn(20L);
		Property property = new Property();
		Long price = 1000000L;
		property.setPrice(price);
		when(repository.getPropertiesWithLimit(anyLong())).thenReturn(Collections.singletonList(property));
		List<Property> mostExpensive = propertyService.getTopPercentMostExpensive(10);
		assertEquals(mostExpensive.get(0).getPrice(), price);
		verify(repository, times(1)).count();
		verify(repository, times(1)).getPropertiesWithLimit(2L);

		Mockito.reset(repository);
		when(repository.count()).thenReturn(40L);
		propertyService.getTopPercentMostExpensive(20);
		verify(repository, times(1)).count();
		verify(repository, times(1)).getPropertiesWithLimit(8L);
	}
}
