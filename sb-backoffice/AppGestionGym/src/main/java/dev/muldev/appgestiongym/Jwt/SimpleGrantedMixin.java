
package dev.muldev.appgestiongym.Jwt;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public abstract class SimpleGrantedMixin {
    
    @JsonCreator
    public SimpleGrantedMixin(@JsonProperty("authority")String role){}
    
}
