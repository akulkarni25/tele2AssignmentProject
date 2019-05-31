
package com.tele2.assignment.subscriptionapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tele2.assignment.subscriptionapi.domain.Subscription;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SubscriptionApiApplication.class)
public class SubscriptionApiApplicationTests {
	private MockMvc mvc;
	   @Autowired
	   WebApplicationContext webApplicationContext;
	  private String mapToJson(Object obj) throws JsonProcessingException {
		      ObjectMapper objectMapper = new ObjectMapper();
		      return objectMapper.writeValueAsString(obj);
		   }
	  private <T> T mapFromJson(String json, Class<T> clazz)
		      throws JsonParseException, JsonMappingException, IOException {
		      
		      ObjectMapper objectMapper = new ObjectMapper();
		      return objectMapper.readValue(json, clazz);
		   }
	  
	  @Before
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	   public void getSubscriptionList() throws Exception {
	      String uri = "/api/subscriptions/";
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      System.out.println("************"+status+"****************");
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      List<Subscription> subscriptionList = this.mapFromJson(content, List.class);
	      assertTrue(subscriptionList.size() > 0);
	   }
	
	@Test
	   public void getNoSubscription() throws Exception {
	      String uri = "/api/subscriptions/10";
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	      
	      int status = mvcResult.getResponse().getStatus();
	      System.out.println("************"+status+"****************");
	      assertEquals(404, status);	     
	   }
}
