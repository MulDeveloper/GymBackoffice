/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.appgestiongym;

import java.util.Objects;

/**
 *
 * @author bunn3
 */
public class User {
    private Integer id;
    private String name;

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;

        if (!(other instanceof User)) return false;

        User otherUser = (User) other;
        return Objects.equals(id, otherUser.id) && Objects.equals(name, otherUser.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
