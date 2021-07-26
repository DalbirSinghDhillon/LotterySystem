package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.ShowTicket;
import com.example.demo.ShowTicketStatus;
import com.example.demo.entity.LotteryTicket;
import com.example.demo.repository.Lotteryrepository;

@Service
public class LotteryService {

	public LotteryService() {
		
	}

	private Lotteryrepository lotteryrepository;

	@Autowired
	public LotteryService(Lotteryrepository lotteryrepository) {
		this.lotteryrepository = lotteryrepository;
	}

	final int digitInTicketLine = 3;

	public void createTicket() {
		int min = 0;
		int max = 2;
		int ticketLines[][] = new int[3][digitInTicketLine];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Random rand = new Random();
				ticketLines[i][j] = rand.nextInt((max - min) + 1) + min;

			}

		}

		lotteryrepository.save(new LotteryTicket(false, true, ticketLines));
	}

	public void createTicket(int numberOfTicketLines) {
		int min = 0;
		int max = 2;
		int ticketLines[][] = new int[numberOfTicketLines][digitInTicketLine];
		for (int i = 0; i < numberOfTicketLines; i++) {
			for (int j = 0; j < 3; j++) {
				Random rand = new Random();
				ticketLines[i][j] = rand.nextInt((max - min) + 1) + min;

			}

		}

		lotteryrepository.save(new LotteryTicket(false, true, ticketLines));
	}

	// Show complete ticket
	public List<LotteryTicket> fetchAllTickets() {

		return lotteryrepository.findAll();
	}

	public ShowTicket findTicket(int id) {

		Optional<LotteryTicket> lotteryTicket = lotteryrepository.findById(id);

		return new ShowTicket(id, lotteryTicket.map(LotteryTicket::getTicketLines).orElse(null));
	}

	// Show only Id,Ticket lines
	public List<ShowTicket> showAllTickets() {
		List<ShowTicket> showTicket = new ArrayList<ShowTicket>();
		lotteryrepository.findAll().forEach(t -> showTicket.add(new ShowTicket(t.getId(), t.getTicketLines())));

		return showTicket;
	}

	// Amend a ticket with additional lines
	@Transactional
	public Boolean amendTicket(int id, int numberofMoreLines) {

		Optional<LotteryTicket> lotteryTicket = lotteryrepository.findById(id);

		Boolean canAmend = lotteryTicket.map(LotteryTicket::isCanAmendTicket).orElse(null);
		if (!canAmend)
			return false;

		int ticketLines[][] = lotteryTicket.map(LotteryTicket::getTicketLines).orElse(null);
		int totalLength = ticketLines.length + numberofMoreLines;
		int newTicketLines[][] = new int[totalLength][digitInTicketLine];

		int min = 0;
		int max = 2;
		for (int i = 0; i < totalLength; i++) {
			for (int j = 0; j < digitInTicketLine; j++) {

				if (i < ticketLines.length)
					newTicketLines[i][j] = ticketLines[i][j];

				else {
					Random rand = new Random();
					newTicketLines[i][j] = rand.nextInt((max - min) + 1) + min;

				}
			}
		}

		// lotteryrepository.save(new LotteryTicket(id,false, true, ticketLines));
		// lotteryrepository.updateTicketLines(id, newTicketLines);
		// lotteryrepository.saveAndFlush(new LotteryTicket(id,false, true,
		// ticketLines));
		lotteryrepository.updateTicketLines(id, newTicketLines);
		return true;
	}

	@Transactional
	public ShowTicketStatus getTicketStatus(int id) {

		Optional<LotteryTicket> lotteryTicket = lotteryrepository.findById(id);
		int ticketLines[][] = lotteryTicket.map(LotteryTicket::getTicketLines).orElse(null);
		lotteryrepository.updateStatusAmend(id, true, false);
		int status[]=new int[ticketLines.length];
		
		for (int i = 0; i < ticketLines.length; i++) {
			
					if (  (ticketLines[i][0]+ticketLines[i][1]+ticketLines[i][2] )== 2)
						status[i]=10;
					else if(  ticketLines[i][0]==ticketLines[i][1]  &&ticketLines[i][0]==ticketLines[i][2]    )
						status[i]=5;
					else if (  ticketLines[i][0]!=ticketLines[i][1]  && ticketLines[i][0]!=ticketLines[i][2]  )
						status[i]=1;
					else
						status[i]=0;
			}
		
		return new ShowTicketStatus(id,status);
	}
}
