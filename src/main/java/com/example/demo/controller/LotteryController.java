package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.AdditionalLines;
import com.example.demo.ShowTicket;
import com.example.demo.ShowTicketStatus;
import com.example.demo.service.LotteryService;

@RestController
public class LotteryController {
	private LotteryService lotteryService;

	@Autowired
	public LotteryController(LotteryService lotteryService) {
		this.lotteryService = lotteryService;
	}

	// Create a ticket
	@PostMapping("ticket")
	public String creatingTicket() {
		lotteryService.createTicket();
		return "Ticket Created";
	}

	// Create a ticket with number of line entered by user
	@PostMapping("/ticket/{numberOfTicketLines}")
	public String creatingTicket(@PathVariable("numberOfTicketLines") int numberOfTicketLines) {
		lotteryService.createTicket(numberOfTicketLines);
		return "Ticket Created";
	}

	// Show all tickets
	@GetMapping("ticket")
	public List<ShowTicket> fetchingTickets() {

		return lotteryService.showAllTickets();
	}

	// Show one ticket by Id
	@GetMapping("/ticket/{id}")
	public ShowTicket fetchTicket(@PathVariable("id") int id) {

		return lotteryService.findTicket(id);

	}

	// Amend ticket lines
	@PutMapping("/ticket/{id}")
	@ResponseBody
	public ResponseEntity<?> amendTicket(@PathVariable("id") int id, @RequestBody AdditionalLines additionalLines) {
		int numberofMoreLines = additionalLines.getMoreLines();
		Boolean amended = lotteryService.amendTicket(id, numberofMoreLines);
		if (amended)
			return new ResponseEntity<>("Amended the Ticket", HttpStatus.OK);
		else
			return new ResponseEntity<>("Cannot amend Ticket", HttpStatus.NOT_MODIFIED);
	}

	// Retrieve status of ticket
	@PutMapping("/status/{id}")
	public ShowTicketStatus fetchStatus(@PathVariable("id") int id) {

		return lotteryService.getTicketStatus(id);

	}
}
