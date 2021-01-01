
package dev.muldev.appgestiongym.staff.rest;

import dev.muldev.appgestiongym.email.domain.ServiceEmail;
import dev.muldev.appgestiongym.login.domain.ServiceUserLogin;
import dev.muldev.appgestiongym.login.domain.UserLogin;
import dev.muldev.appgestiongym.staff.domain.Staff;
import dev.muldev.appgestiongym.staff.domain.ModCredSec;
import dev.muldev.appgestiongym.staff.domain.ServiceStaff;

import java.text.SimpleDateFormat;
import java.util.List;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE})
public class CtrlPersonal {
    
    private final ServiceStaff service;
    private final BCryptPasswordEncoder bCrypt;
    private final ServiceUserLogin serviceLogin;
    private final ServiceEmail serviceEmail;

    public CtrlPersonal(ServiceStaff service, BCryptPasswordEncoder bCrypt, ServiceUserLogin serviceLogin, ServiceEmail serviceEmail) {
        this.service = service;
        this.bCrypt = bCrypt;
        this.serviceLogin = serviceLogin;
        this.serviceEmail = serviceEmail;
    }

    @PostMapping("/add")
    public ResponseEntity<Staff> saveStaff(@RequestBody Staff p){
        
        /*
            TO-DO production Multipart file with picture of staff
        */

        Staff staff = service.addStaff(p);

        //we create a temporal access to our staff based in their birth date
        UserLogin uLogin = new UserLogin();
        uLogin.setUsername(p.getNifPersonal());
        SimpleDateFormat format = new SimpleDateFormat("ddMMyy");
        String fecha = format.format(p.getFechaNacimiento());
        uLogin.setPassword(bCrypt.encode(fecha));

        try{
            serviceLogin.add(uLogin);
            //we send an email with username and pass to the staff
            serviceEmail.sendEmailWithCredentials(staff.getNifPersonal(), staff.getEmailPersonal(), fecha);
            return new ResponseEntity(staff, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }



    }
    
    @GetMapping("/listapersonal")
    public ResponseEntity<List<Staff>> listStaff(){
        List personal = service.listStaff();
        return new ResponseEntity(personal, HttpStatus.OK);
    }

    @GetMapping("/getStaffWithRole")
    public ResponseEntity<List<Staff>> listStaffWithRole(@RequestParam String role){
        return new ResponseEntity(service.getStaffWithRole(role), HttpStatus.OK);
    }
    
    @GetMapping("/get")
    public ResponseEntity<Staff> getPersonal(Authentication authentication){
        //extract info form token
        Staff staff = service.getByUsername(authentication.getName());
        return new ResponseEntity(staff, HttpStatus.OK);
        
    }
    
    @DeleteMapping("/del")
    public ResponseEntity<Boolean> deletePersonal(@RequestParam(name="idpersonal") String id){

        int idPersonal = -1;
        try{
            idPersonal = Integer.parseInt(id);
        }
        catch(NumberFormatException e){
            return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
        }
        try{
            String username = service.getOne(idPersonal).getNifPersonal();

            if(serviceLogin.delByUsername(username) && service.delById(idPersonal)){
                //se eliminan ambos registros correctamente
                return new ResponseEntity(true, HttpStatus.OK);
            }

            else{
                return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
            }
        }
        catch(NullPointerException e){
            return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("/mod")
    public ResponseEntity<Staff> editStaff(@RequestBody Staff p){
        Staff staff = service.addStaff(p);
        return new ResponseEntity(staff, HttpStatus.OK);
    }
    
    @PostMapping("/modcred")
    public ResponseEntity<Boolean> editLoginAccess(@RequestBody ModCredSec sec){

        //decodificamos la pass pasada en base64
        String nueva = "";
        String vieja = "";

        byte[] valueDecoded = Base64.decodeBase64(sec.getNueva());
        nueva = new String(valueDecoded);
        byte[] valueDecodedOld = Base64.decodeBase64(sec.getActual());
        vieja = new String(valueDecodedOld);

        //if the password is correct we update the credentials
        UserLogin userLogin = serviceLogin.loadByUsername(sec.getNomusu());

        if(bCrypt.matches(vieja, userLogin.getPassword())){
            // the old password is correct
            userLogin.setPassword(bCrypt.encode(nueva));
            serviceLogin.add(userLogin);
            return new ResponseEntity(true, HttpStatus.OK);
        }
        else{
            return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
        }
            




    }
    
    
 
    
}
