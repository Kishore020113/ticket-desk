package ticket.com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;
import ticket.com.example.demo.Role;

@Getter
@Setter
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private Role role;
}