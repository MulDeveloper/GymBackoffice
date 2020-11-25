/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.appgestiongym.Clients.Domain;

import java.util.List;


public interface ServiceClient {
    List<ClientMembership> lastTen();
    List<Client> searchByInput(String input);
    List<Client> listAll();
    Client add(Client c);
}
