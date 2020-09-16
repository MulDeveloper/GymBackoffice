/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.muldev.appgestiongym.Jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 *
 * @author bunn3
 */
public class CheckToken {
    
    public CheckToken(){};
    
    //comprobamos el token
    public Boolean decodeJWT(TokenUser tkn){
            String[] split_string = tkn.getToken().split("\\.");
            String base64EncodedHeader = split_string[0];
            String base64EncodedBody = split_string[1];
            String base64EncodedSignature = split_string[2];

            Base64 base64Url = new Base64(true);

            String body = new String(base64Url.decode(base64EncodedBody));
            
            Map<String, String> resp = new HashMap<String, String>();
            String[] pairs = body.split(",");
            for (int i=0;i<pairs.length;i++) {
                String pair = pairs[i];
                String[] keyValue = pair.split(":");
                String key = keyValue[0].replace("{", "").replace("\"", "");
                resp.put(key, keyValue[1]);
            }
    
            
            String expiracion = resp.get("exp").replace("}", "");
            expiracion = expiracion + "000";
            String username = resp.get("sub");
            
            //comprobamos que el token este activo
            Date fecha = new Date(System.currentTimeMillis());
            Date fechaToken = new Date(new Long(expiracion));
            

            //si esta activo y se corresponde con el usuario pasado, damos acceso al recurso
            if(fechaToken.before(fecha)){
                //el token ha expirado
                return false;
            }
            else{
                //token correcto
                return true;
            }
            
        
    }
}
