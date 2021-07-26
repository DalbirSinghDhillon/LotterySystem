package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.LotteryTicket;

@Repository
public interface Lotteryrepository extends JpaRepository<LotteryTicket,Integer> {

	@Modifying
	@Query("update LotteryTicket lt set lt.ticketLines = ?2 where lt.id = ?1")
	void updateTicketLines(@Param(value = "id") int id, @Param(value = "newTicketLines") int newTicketLines[][]);

	@Modifying
	@Query("update LotteryTicket lt set lt.statusChecked = ?2 , canAmendTicket = ?3 where lt.id = ?1")
	void updateStatusAmend(int id,Boolean statusChecked , Boolean canAmendTicket);
	
	
	
}
