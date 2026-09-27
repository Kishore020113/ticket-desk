package ticket.com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ticket.com.example.demo.Priority;
import ticket.com.example.demo.Status;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponse {
    private Long id;
    private String ticketNumber;
    private String description;
    private Priority priority;
    private Status status;
    private String customerName;
}
