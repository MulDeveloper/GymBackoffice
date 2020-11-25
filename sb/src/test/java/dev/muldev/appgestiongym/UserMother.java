/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.appgestiongym;

/**
 *
 * @author bunn3
 */
final class UserMother {
    static final int RAFA_ID = 1;
    static final int JAVI_ID = 2;

    private static final String RAFA_NAME = "Rafa";
    private static final String JAVI_NAME = "Javi";

    static User rafa() {
        return new User(RAFA_ID, RAFA_NAME);
    }

    static User javi() {
        return new User(JAVI_ID, JAVI_NAME);
    }
}
