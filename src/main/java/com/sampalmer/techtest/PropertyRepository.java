package com.sampalmer.techtest;

import com.sampalmer.techtest.model.Property;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

	@Query(value = "SELECT * FROM PROPERTY ORDER BY PRICE DESC LIMIT ?1", nativeQuery = true)
	List<Property> getPropertiesWithLimit(Long limit);

	@Query(value = "SELECT avg(price) from Property p where p.postcode like concat(?1, ' %') ")
	Double findAveragePriceByPostcodeOutward(String postcodeOutward);

	@Query(value = "SELECT avg(price) from Property p where p.propertyType = ?1")
	Double findAveragePriceByPropertyType(Property.PropertyType propertyType);


}
