package dev.muldev.appgestiongym.clientlogin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientLogin {
    private UUID id;
    private String username;
    private String password;
}
