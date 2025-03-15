package springboot_backend_ecommerce.auth;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;
    private String email;
    private String role;

    /**
     *  @param isVerified verification variable
     */
    private boolean isVerified = false;

    // Getters and Setters
}
