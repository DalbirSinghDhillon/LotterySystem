package com.example.demo;

public class ShowTicketStatus {
	private int id;
	private int ticketLinesStatus[];
	
	public ShowTicketStatus(int id, int[] ticketLinesStatus) {
		super();
		this.id = id;
		this.ticketLinesStatus = ticketLinesStatus;
	}

	public ShowTicketStatus() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int[] getTicketLinesStatus() {
		return ticketLinesStatus;
	}

	public void setTicketLinesStatus(int[] ticketLinesStatus) {
		this.ticketLinesStatus = ticketLinesStatus;
	}
	
	
}
