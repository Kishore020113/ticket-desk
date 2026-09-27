package ticket.com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ticket.com.example.demo.Status;
import ticket.com.example.demo.Ticket;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
    List<Ticket> findByStatus(Status status);
    List<Ticket> findByCustomerId(Long customerId);
}
