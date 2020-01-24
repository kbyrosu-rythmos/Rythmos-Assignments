package com.Rythmos.RabbitMqProject;

import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class MessageSenderTesting extends MessageSenderApplicationTests {

	private static ResponseEntity<String> result;
	private String baseUrl;
	RestTemplate restTemplate;

	@Before
	public void setup() {
		restTemplate = new RestTemplate();
		baseUrl = "http://localhost:8080/rythmos-rabbitmq/header/producer?regionCode=Region2&messageData=SendingdatatoRegion2";
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
				"{\"messageProperties\":{\"headers\":{\"regionCode\":\"Region2\"},\"timestamp\":null,\"messageId\":null,\"userId\":null,\"appId\":null,\"clusterId\":null,\"type\":null,\"correlationId\":null,\"replyTo\":null,\"contentType\":\"text/plain\",\"contentEncoding\":\"UTF-8\",\"contentLength\":20,\"deliveryMode\":\"PERSISTENT\",\"expiration\":null,\"priority\":0,\"redelivered\":null,\"receivedExchange\":null,\"receivedRoutingKey\":null,\"receivedUserId\":null,\"deliveryTag\":0,\"messageCount\":null,\"consumerTag\":null,\"consumerQueue\":null,\"receivedDelay\":null,\"receivedDeliveryMode\":null,\"finalRetryForMessageWithNoId\":false,\"publishSequenceNumber\":0,\"lastInBatch\":false,\"inferredArgumentType\":null,\"targetMethod\":null,\"targetBean\":null,\"xdeathHeader\":null,\"replyToAddress\":null,\"delay\":null},\"body\":\"U2VuZGluZ2RhdGF0b1JlZ2lvbjI=\"}\n",
				str, true);
	}

}
