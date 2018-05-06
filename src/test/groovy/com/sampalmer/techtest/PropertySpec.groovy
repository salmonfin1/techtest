package com.sampalmer.techtest

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest(classes = TechTestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PropertySpec extends Specification {

	@Value('${local.server.port}')
	private def port

	PropertyClient client

	def setup() {
		client = new PropertyClient(port)
	}

	@Unroll
	def "When getting the average for a postcode #code, I get the answer or null"() {
		given:
			def postcode = 'W1F'
			def average = 1158750.0
			if (code == 'inward') {
				postcode = '3FT'
				average = null
			}
		when:

			def response = client.sendRequest('postcodemean', ['postcodeOutward' : postcode])
		then:
			response == average
		where:
			code << ['outward','inward']
	}


	@Unroll
	def "When getting the average price for a #type property, I get #average"() {
		when:
			def response = client.sendRequest('averagedifference', ['propertyType': type])
			print(response.averagePrice)
		then:
			response.averagePrice == average
		where:
			type       | average
			'Terraced' | 230861.0
			'Mansion'  | 1799375.0
			'Detached' | 320579.125
			'Flat'     | 363999.75
	}

	@Unroll
	def "When I get the top #percent percent most expensive, it returns #number results"() {
		given:
			def topPrices = [7500000,2500000,1010000,1000000,999999,755000,755000,750000,574833,550000,545444,275000]
			def allMatch = true
		when:
			def response = client.sendRequest('topmostexpensive', ['percent': percent])
		then:
			response.size() == Math.round(24/(100/percent))
			for (int i=0; i<response.size(); i++) {
				allMatch = (response[i].price == topPrices[i])
				if (!allMatch) {
					break
				}
			}
		allMatch
		where:
			percent << [10,20,30,40]
	}

}