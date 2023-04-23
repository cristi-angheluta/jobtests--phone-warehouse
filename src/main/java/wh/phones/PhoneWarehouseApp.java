package wh.phones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class PhoneWarehouseApp {
    public static void main(String[] args) {
        SpringApplication.run(PhoneWarehouseApp.class, args);
    }
}
