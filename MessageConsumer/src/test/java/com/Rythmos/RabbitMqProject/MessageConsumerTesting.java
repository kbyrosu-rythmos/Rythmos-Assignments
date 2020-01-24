package com.Rythmos.RabbitMqProject;

import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class MessageConsumerTesting extends MessageConsumerApplicationTests {

	private static ResponseEntity<String> result;
	private String baseUrl;
	RestTemplate restTemplate;
	private static WebDriver driver;

	@Before
	public void setup() {
		restTemplate = new RestTemplate();
		baseUrl = "http://localhost:8081/rythmos-rabbitmq/header/consumer";
	}

	@Test
	public void testingConnectiontoRabbitMq() throws URISyntaxException {
		URI uri = new URI(baseUrl);
		result = restTemplate.getForEntity(uri, String.class);
		assertEquals(200, result.getStatusCodeValue());
	}

	@Test
	public void testingSentMessage() throws JSONException {
		String str = result.getBody();
		JSONAssert.assertEquals(
				"[\"SendingdatatoRegion2\"]",
				str, true);
	}

}
