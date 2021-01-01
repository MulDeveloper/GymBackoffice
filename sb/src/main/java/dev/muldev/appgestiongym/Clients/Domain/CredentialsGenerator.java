package dev.muldev.appgestiongym.clients.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Data
@AllArgsConstructor
public class CredentialsGenerator {
    private String username;
    private Date birthDate;
    private String email;


    public Credentials GenerateCredentials(){
        SimpleDateFormat format = new SimpleDateFormat("ddMMyy");
        String date = format.format(birthDate);
        String pass = createPasswordByBirthDate(date);
        return new Credentials(username, pass, email);
    }

    public String createPasswordByBirthDate(String date) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 2) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();

        String pass = saltStr+date;
        return pass;

    }
}
