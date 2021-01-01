package dev.muldev.appgestiongym.clientlogin.infrastructure.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="client_login")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(name = "ClientLogin.findByUsername", query = "SELECT c FROM ClientLoginEntity c WHERE c.username = :username"),
})
public class ClientLoginEntity {

    @Id
    private UUID id;
    @Column(unique=true)
    private String username;
    private String password;

}
