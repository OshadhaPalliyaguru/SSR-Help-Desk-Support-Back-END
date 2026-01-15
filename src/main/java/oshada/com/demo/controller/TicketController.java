package oshada.com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import oshada.com.demo.model.dto.TicketDTO;
import oshada.com.demo.servies.TicketService;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "http://localhost:3000")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public List<TicketDTO> getAllTickets() {

        return ticketService.getAllTickets();
    }

    // Inside TicketController.java

    @PostMapping // This maps to POST requests
    public TicketDTO createTicket(@RequestBody TicketDTO ticketDTO) {
        // @RequestBody tells Spring: "Take the JSON from the request and turn it into a Java Object"
        return ticketService.createTicket(ticketDTO);
    }
}