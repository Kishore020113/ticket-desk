package ticket.com.example.demo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ticket.com.example.demo.Status;
import ticket.com.example.demo.TicketService;
import ticket.com.example.demo.dto.CreateTicketRequest;
import ticket.com.example.demo.dto.TicketResponse;
import ticket.com.example.demo.dto.UpdateStatusRequest;

import java.util.List;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;
    @PostMapping
    public TicketResponse create(@Valid @RequestBody CreateTicketRequest request){
        return ticketService.createTicket(request.getDescription(),
                request.getCustomerId(),
                request.getPriority());
    }
    @GetMapping("/{id}")
    public TicketResponse getById(@PathVariable Long id){
        return ticketService.getTicketById(id);
    }
    @GetMapping
    public List<TicketResponse> getAllTickets() {
           return ticketService.getAllTickets();
    }
    @PatchMapping("/{id}/status")
    public TicketResponse updateStatus(@Valid @PathVariable Long id, @RequestBody UpdateStatusRequest status){
         return ticketService.updateTicketStatus(id,status.getStatus());
    }
}
