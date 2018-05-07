package com.sampalmer.techtest

import groovyx.net.http.RESTClient

class PropertyClient {

	def client

	PropertyClient(port) {

		client = new RESTClient("http://localhost:$port/")
		client.handler.failure = { resp, data ->
			resp.data = data
			return resp
		}
	}

	def sendRequest(path, params) {
		def resp = client.get(
				"path": path,
				"query" : params
		)
		return resp.responseData
	}
}
