package dev.muldev.appgestiongym.login.infrastructure;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="user_login")
@NoArgsConstructor
@AllArgsConstructor
@Data
@NamedQuery(name = "UserLoginEntity.findByUsername", query = "SELECT l FROM UserLoginEntity l WHERE l.username = :username")
public class UserLoginEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

}
