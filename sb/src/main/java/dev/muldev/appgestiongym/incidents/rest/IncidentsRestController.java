package dev.muldev.appgestiongym.incidents.rest;

import dev.muldev.appgestiongym.incidents.domain.ServiceIncidents;
import dev.muldev.appgestiongym.incidents.domain.Incident;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/incidents")
public class IncidentsRestController {

    private final ServiceIncidents serviceIncidents;

    @PostMapping
    public ResponseEntity<Boolean> add(@RequestBody Incident incident){
        return new ResponseEntity(serviceIncidents.save(incident), HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<Incident>> findAll(){
        return new ResponseEntity(serviceIncidents.listAll(), HttpStatus.ACCEPTED);
    }

    @GetMapping("filterByMonth")
    public ResponseEntity<List<Incident>> filterByMonth(@RequestParam String month, @RequestParam String year){
        int monthInt = Integer.parseInt(month);
        int yearInt = Integer.parseInt(year);
        //we can filter by month and year
        return new ResponseEntity(serviceIncidents.filterByMonthAndYear(monthInt, yearInt), HttpStatus.ACCEPTED);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Boolean> setResolved(@PathVariable String id){

        return new ResponseEntity(serviceIncidents.setResolved(Integer.parseInt(id)), HttpStatus.OK);
    }

    @GetMapping("/costs")
    public ResponseEntity<Double> getCosts(){
        return new ResponseEntity(serviceIncidents.getCosts(), HttpStatus.OK);
    }
}
