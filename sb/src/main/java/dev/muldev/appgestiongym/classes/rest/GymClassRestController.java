package dev.muldev.appgestiongym.classes.rest;

import dev.muldev.appgestiongym.classes.domain.GymClass;
import dev.muldev.appgestiongym.classes.domain.ServiceGymClass;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/classes")
@AllArgsConstructor
public class GymClassRestController {

    private final ServiceGymClass serviceGymClass;

    @PostMapping()
    public ResponseEntity<Boolean> add(@RequestBody GymClass gymClass){
        return new ResponseEntity(serviceGymClass.add(gymClass), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> del(@RequestParam String id){
        int idCasted;
        try{
            idCasted = Integer.parseInt(id);
        }
        catch(NumberFormatException e){
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity(serviceGymClass.deleteById(idCasted), HttpStatus.OK);
    }

    @PostMapping("/copy/{dateString}")
    public ResponseEntity<Boolean> copyWeek(@PathVariable String dateString){
        //we get the classes of this week and add it the same to the next week
        List <GymClass> list = getSelectedWeek(dateString, LocalDate.parse(dateString).plusDays(7).toString()).getBody();
        return new ResponseEntity(serviceGymClass.copyWeek(list), HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<GymClass>> get(){
        return new ResponseEntity(serviceGymClass.findAll(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Integer>> stats(){
        return new ResponseEntity(serviceGymClass.statsByClass(), HttpStatus.OK);
    }



    @GetMapping("/selected")
    public ResponseEntity<List<GymClass>> getSelectedWeek(@RequestParam String from, @RequestParam String to){

        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date dateFrom = format.parse(from);
            Date dateTo = format.parse(to);
            return new ResponseEntity(serviceGymClass.listByWeek(dateFrom, dateTo), HttpStatus.ACCEPTED);
        }
        catch(ParseException e){
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
    }
}
