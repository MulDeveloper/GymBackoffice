/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.appgestiongym;

import java.util.Optional;

/**
 *
 * @author bunn3
 */
public interface UserRepository {
    Optional<User> search(Integer id);
}
