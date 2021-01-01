/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.appgestiongym.clients.domain;

import java.util.List;
import java.util.Map;


public interface ServiceClient {
    List<ClientMembership> lastTen();
    List<Client> searchByInput(String input);
    List<Client> listAll();
    Client add(Client c);
    Client getOne(int id);
    Boolean deleteById(int id);
    Map<String, Integer> generateMapStatsByAgeRange();
}
