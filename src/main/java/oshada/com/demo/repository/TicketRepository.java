package oshada.com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import oshada.com.demo.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    // Custom query logic comes here if needed
}