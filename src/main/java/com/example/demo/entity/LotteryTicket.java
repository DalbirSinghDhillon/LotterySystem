package com.example.demo.entity;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LotteryTicket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private boolean statusChecked;
	private boolean canAmendTicket;
	private int ticketLines[][];

	public LotteryTicket(boolean status, boolean amend, int ticketLines[][]) {
		statusChecked = status;
		canAmendTicket = amend;
		// Arrays.sort(ticketLines);
		this.ticketLines = ticketLines;

	}
	public LotteryTicket(int id,boolean status, boolean amend, int ticketLines[][]) {
		this.id=id;
		statusChecked = status;
		canAmendTicket = amend;
		// Arrays.sort(ticketLines);
		this.ticketLines = ticketLines;

	}

	public LotteryTicket() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isStatusChecked() {
		return statusChecked;
	}

	public void setStatusChecked(boolean statusChecked) {
		this.statusChecked = statusChecked;
	}

	
	public boolean isCanAmendTicket() {
		return canAmendTicket;
	}

	public void setCanAmendTicket(boolean canAmendTicket) {
		this.canAmendTicket = canAmendTicket;
	}




	public int[][] getTicketLines() {
		return ticketLines;
	}

	public void setTicketLines(int[][] ticketLines) {
		this.ticketLines = ticketLines;
	}

}
