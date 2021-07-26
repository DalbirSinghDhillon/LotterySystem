package com.example.demo;

public class ShowTicket {
	private int id;
	private int ticketLines[][];
	
	public ShowTicket(int id, int ticketLines[][]) {
		this.id=id;
		this.ticketLines = ticketLines;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int[][] getTicketLines() {
		return ticketLines;
	}

	public void setTicketLines(int[][] ticketLines) {
		this.ticketLines = ticketLines;
	}

	public ShowTicket() {

	}
}
