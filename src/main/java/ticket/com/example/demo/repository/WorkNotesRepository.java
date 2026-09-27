package ticket.com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ticket.com.example.demo.WorkNotes;

public interface WorkNotesRepository extends JpaRepository<WorkNotes, Long> {
}
