package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.test.web.servlet.MockMvc;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.demo.repository.Lotteryrepository;
import com.example.demo.service.LotteryService;


@WebMvcTest
class LotteryControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
    private LotteryService lotteryService;
	
	@Test
	void testcreatingTicket() throws Exception {
		 this.mockMvc.perform(post("/ticket"))
         .andExpect(status().isOk())
         .andExpect(content().string("Ticket Created"));;  

	}
	
	@Test
	void testfetchingTickets() throws Exception {
		 this.mockMvc.perform(get("/ticket"))
         .andExpect(status().isOk());
         //.andExpect(content().string("Ticket Created"));;  

	}
	
	

}
