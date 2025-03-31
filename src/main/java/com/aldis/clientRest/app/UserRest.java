package com.aldis.clientRest.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.naming.AuthenticationException;


@Service
public class UserRest {

    @Value("${mss.user.rest.host}")
    private String mssUserRestHost;


    public boolean authenticate(String token) throws AuthenticationException {
        try {
            String url = mssUserRestHost + "/auth/verify-access-token";

            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.set("token", token);

            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new AuthenticationException("Invalid token");
            }

            return true;
        } catch (Exception e) {
            throw new AuthenticationException("Invalid token");
        }
    }
}
