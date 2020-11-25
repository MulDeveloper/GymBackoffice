/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.appgestiongym;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import static org.jboss.logging.MDC.put;

/**
 *
 * @author bunn3
 */
public class MuldevStaffUserRepo implements UserRepository{
    
    private Map<Integer, User> users = Collections.unmodifiableMap(new HashMap<Integer, User>() {
        {
            put(UserMother.RAFA_ID, UserMother.rafa());
            put(UserMother.JAVI_ID, UserMother.javi());
        }
    });

    @Override
    public Optional<User> search(Integer id) {
         return Optional.ofNullable(users.get(id));
    }
    
    
}
