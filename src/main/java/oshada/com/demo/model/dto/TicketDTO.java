package oshada.com.demo.model.dto;

import lombok.Data;

@Data
public class TicketDTO {
    // We only send what the frontend actually needs
    private Long id;
    private String employeeName;
    private String issueDescription;
    private String priority; // HIGH, MEDIUM, LOW
    private String status;   // OPEN, CLOSED
}
