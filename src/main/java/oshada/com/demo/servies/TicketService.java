package oshada.com.demo.servies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oshada.com.demo.model.Ticket;
import oshada.com.demo.model.dto.TicketDTO;
import oshada.com.demo.repository.TicketRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service // Tells Spring: "This holds business logic"
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public List<TicketDTO> getAllTickets() {
        // 1. Get raw data from DB
        List<Ticket> tickets = ticketRepository.findAll();

        // 2. Convert Entity -> DTO (Mapping)
        // In real jobs, we use "ModelMapper" or "MapStruct", but manual is good for learning
        return tickets.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Helper method to convert one Ticket to one TicketDTO
    private TicketDTO convertToDTO(Ticket ticket) {
        TicketDTO dto = new TicketDTO();
        dto.setId(ticket.getId());
        dto.setEmployeeName(ticket.getEmployeeName());
        dto.setIssueDescription(ticket.getIssueDescription());
        dto.setPriority(ticket.getPriority());
        dto.setStatus(ticket.getStatus());
        return dto;
    }

    // Inside TicketService.java

    public TicketDTO createTicket(TicketDTO ticketDTO) {
        // 1. Convert DTO to Entity
        Ticket ticket = new Ticket();
        ticket.setEmployeeName(ticketDTO.getEmployeeName());
        ticket.setIssueDescription(ticketDTO.getIssueDescription());
        ticket.setPriority(ticketDTO.getPriority());

        // 2. Set Default Logic (Business Logic lives here!)
        // If the frontend didn't send a status, default to "OPEN"
        if (ticketDTO.getStatus() == null || ticketDTO.getStatus().isEmpty()) {
            ticket.setStatus("OPEN");
        } else {
            ticket.setStatus(ticketDTO.getStatus());
        }

        // 3. Save to DB (The ID is generated here)
        Ticket savedTicket = ticketRepository.save(ticket);

        // 4. Convert back to DTO to return to the API
        return convertToDTO(savedTicket);
    }
}
