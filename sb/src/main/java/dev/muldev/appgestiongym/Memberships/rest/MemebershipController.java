package dev.muldev.appgestiongym.memberships.rest;

import dev.muldev.appgestiongym.memberships.domain.Membership;
import dev.muldev.appgestiongym.memberships.domain.ServiceMembership;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Calendar;

@RestController
@RequestMapping("/api/memberships")
public class MemebershipController {

    private final ServiceMembership serviceMembership;

    public MemebershipController(ServiceMembership serviceMembership) {
        this.serviceMembership = serviceMembership;
    }

    @GetMapping
    public ResponseEntity<Membership> getOne(@RequestParam("id") String id){
        int idMembership = Integer.parseInt(id);
        return new ResponseEntity(serviceMembership.getOne(idMembership), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Boolean> update(@RequestBody Membership membership,
                                          @RequestParam("months") String months){

        Calendar cal = Calendar.getInstance();
        cal.setTime(membership.getFechaAbonadoHasta());
        cal.add(Calendar.MONTH, Integer.parseInt(months));
        membership.setFechaAbonadoHasta(cal.getTime());
        serviceMembership.add(membership);
        return new ResponseEntity(true, HttpStatus.OK);
    }

}
