package ticket.com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import ticket.com.example.demo.Priority;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTicketRequest {
    @NotBlank(message = "The description cannot be empty")
    private String description;
    @NotNull(message = "Customer Id cannot be null")
    private Long customerId;
    @NotNull(message = "Priority should be mentioned")
    private Priority priority;
}
