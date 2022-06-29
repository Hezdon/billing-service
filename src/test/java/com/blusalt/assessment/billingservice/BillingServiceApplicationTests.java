package com.blusalt.assessment.billingservice;

import com.blusalt.assessment.billingservice.dto.CustomerFund;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
@SpringBootTest
class BillingServiceApplicationTests {

	private MockMvc mockMvc;


	@Autowired
	private WebApplicationContext webApplicationContext;


	@BeforeEach
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();

	}

	@Test
	@DisplayName(value = "Add New Fund Test")
	@SneakyThrows
	public void addFund_Successful(){

		CustomerFund fund = new CustomerFund().withAmount("100").withCustomerId(1);
		MvcResult result = this.mockMvc.perform(post(getUri("/api/v1/billing/customer/fund"))

				.content(new ObjectMapper().writeValueAsString(fund))
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.message").value("Pending"))
				.andExpect(jsonPath("$.amount").value(fund.getAmount()))
				.andExpect(jsonPath("$.customerId").value(fund.getCustomerId()))
				.andExpect(status().isOk())
				.andReturn();

	}

	private URI getUri(String serviceUrl) {
		URI uri = null;
		try {
			uri = new URI(serviceUrl);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return uri;
	}

}
