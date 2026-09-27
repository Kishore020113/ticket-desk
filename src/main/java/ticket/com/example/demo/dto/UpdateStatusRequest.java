package ticket.com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ticket.com.example.demo.Status;

@Getter
@Setter
public class UpdateStatusRequest {
    @NotNull(message = "It cannot be null or empty")
    private Status status;
}
