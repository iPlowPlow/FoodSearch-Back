package fr.iplowplow.foodsearch.services.token;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

import fr.iplowplow.foodsearch.entitys.User;
import io.jsonwebtoken.*;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import static fr.iplowplow.foodsearch.properties.ApplicationPropertiesUtil.getSecretKey;

@Service
public class JWTTokenServiceImpl implements JWTTokenService{



    public String createJWT(User user) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        long tomorrowMillis = nowMillis + (1000 * 60 * 60 * 24);
        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(getSecretKey());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());



        JSONObject payload = new JSONObject();
        payload.put("username",user.getUsername());
        payload.put("admin",false);
        payload.put("iat",nowMillis);
        payload.put("exp",tomorrowMillis);

        //Let's set the JWT Claims
        return Jwts.builder()
                .setPayload(String.valueOf(payload))
                .signWith(signatureAlgorithm, signingKey)
                .compact();


    }

    public Claims decodeJWT(String jwt) {
        //This line will throw an exception if it is not a signed JWS (as expected)
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(getSecretKey()))
                .parseClaimsJws(jwt).getBody();
    }

}
