
package dev.muldev.appgestiongym.Personal;

import dev.muldev.appgestiongym.AccesoPersonal.AccesoPersonal;
import dev.muldev.appgestiongym.AccesoPersonal.ServiceAccesoPersonal;
import dev.muldev.appgestiongym.Jwt.CheckToken;
import dev.muldev.appgestiongym.Jwt.TokenUser;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api")
@RestController
public class CtrlPersonal {
    
    @Autowired
    private ServicePersonal service;
    
    @Autowired
    private BCryptPasswordEncoder bCrypt;
    
    @Autowired
    private ServiceAccesoPersonal serviceAcceso;
    
    //llamamos a la capa de servicio y hacemos la logica del objeto personal que le pasamos
    @PostMapping("/add")
    public ResponseEntity<PersonalGym> altaPersonal(@RequestBody PersonalGym p){
        
        /*
        
        TO - DO production
        
        Si desplegamos esta app en produccion debemos capturar el MultipartFile de la foto del form
        y pasarla a un servicio de almacenamiento de imagenes con el nombre del usuario y la extension
        
        Por el momento como no es un entorno de produccion real, no usaremos foto
        
        */
        
        PersonalGym personal = service.altaPersonal(p);
        
        //creamos el acceso personal con password temporal de fecha de nacimiento
        
        AccesoPersonal acceso = new AccesoPersonal();
        acceso.setIdpersonal(personal.getIdpersonal());
        acceso.setUsername(personal.getNifPersonal());
        SimpleDateFormat format = new SimpleDateFormat("ddMMyy");
        String fecha = format.format(p.getFechaNacimiento());
        acceso.setPasswordPersonal(bCrypt.encode(fecha));
        
        try{
            serviceAcceso.altaAcceso(acceso);
            return new ResponseEntity(personal, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }

    }
    
    @GetMapping("/listapersonal")
    public ResponseEntity<List<PersonalGym>> listaPersonal(@RequestParam(name="nomusu") String nomusu,
            @RequestParam(name="token") String token){
        
        CheckToken checkToken = new CheckToken();
        
        TokenUser tkn = new TokenUser(token,nomusu);
        
        List personal = service.listaPersonal();
       
        if(checkToken.decodeJWT(tkn)){
            return new ResponseEntity(personal, HttpStatus.OK);
        }
        else{
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        
    }
    
 
    
}
