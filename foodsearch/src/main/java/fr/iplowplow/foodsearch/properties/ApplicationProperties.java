package fr.iplowplow.foodsearch.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class ApplicationProperties {

    @Value("${jwt.secret}")
    private String secretKey;


}
