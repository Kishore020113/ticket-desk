package ticket.com.example.demo;

import lombok.*;
import org.springframework.stereotype.Service;
import ticket.com.example.demo.dto.TicketResponse;
import ticket.com.example.demo.repository.TicketRepository;
import ticket.com.example.demo.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;
    public TicketResponse createTicket(String description, Long customerId, Priority priority){
        User customer = userRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer if not found : " + customerId));
        Ticket ticket = new Ticket();
        ticket.setDescription(description);
        ticket.setStatus(Status.OPEN);
        ticket.setCustomer(customer);
        ticket.setPriority(priority);

        Ticket saveTicket = ticketRepository.save(ticket);
        saveTicket.setTicketNumber("TCKT-" + String.format("%05d",saveTicket.getId()));
        Ticket finalTicket = ticketRepository.save(saveTicket);
        return new TicketResponse(
                finalTicket.getId(),
                finalTicket.getTicketNumber(),
                finalTicket.getDescription(),
                finalTicket.getPriority(),
                finalTicket.getStatus(),
                finalTicket.getCustomer().getName()
        );
    }
    public TicketResponse getTicketById(Long id){
        Ticket ticket = ticketRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Ticket not found : " + id)
        );
        return new TicketResponse(
                ticket.getId(),
                ticket.getTicketNumber(),
                ticket.getDescription(),
                ticket.getPriority(),
                ticket.getStatus(),
                ticket.getCustomer().getName()
        );
    }
    public List<TicketResponse> getAllTickets(){
        List<Ticket> tickets = ticketRepository.findAll();
        List<TicketResponse> responses = new ArrayList<>();
        for(Ticket ticket : tickets){
            responses.add(new TicketResponse(
                    ticket.getId(),
                    ticket.getTicketNumber(),
                    ticket.getDescription(),
                    ticket.getPriority(),
                    ticket.getStatus(),
                    ticket.getCustomer().getName()
            ));
        }
        return responses;
    }
    public TicketResponse updateTicketStatus(Long id, Status status){
        Ticket ticket = ticketRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Ticket Not Found : " + id)
        );
        ticket.setStatus(status);
        Ticket update = ticketRepository.save(ticket);
        return new TicketResponse(
                update.getId(),
                update.getTicketNumber(),
                update.getDescription(),
                update.getPriority(),
                update.getStatus(),
                update.getCustomer().getName()
        );
    }
}
