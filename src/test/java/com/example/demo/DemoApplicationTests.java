package com.example.demo;

import com.example.demo.independent.CalculationBodyDTO;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void simpleCalculation_Happy() throws URISyntaxException, IOException {
		OkHttp3ClientHttpRequestFactory requestFactory = new OkHttp3ClientHttpRequestFactory();
		 var request = requestFactory.createRequest(new URI("http://localhost:8080/independent/calculate"), HttpMethod.POST);
		request.getBody()
				.write(new Gson().toJson(new CalculationBodyDTO("plus", List.of(1, 3))).getBytes(StandardCharsets.UTF_8));
		int result = request.execute().getBody().read();
		Assertions.assertEquals(4,requestFactory);
	}

}
