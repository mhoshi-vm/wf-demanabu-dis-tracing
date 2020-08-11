package com.example.demo;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}

@RestController
class HelloRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloRestController.class);

    @GetMapping("/hello")
    public ResponseEntity<String> hello (@RequestHeader Map<String, String> header) throws InterruptedException{

            printAllHeaders(header);

            // Generate bad request 10%
            if ((long)(Math.random()*10%10) == 1) {
                return ResponseEntity.badRequest().body("Good Bye World!");
            }
            int randomNumber = (int) (Math.random()*100);

            if (randomNumber > 97) {
                //　Wait for 5 seconds in 2%
                Thread.sleep(5000);
            }else if (randomNumber > 90) {
                // Wait for 2 seconds in 10%
                Thread.sleep(2000);
            }
            return ResponseEntity.ok("Hello World!");
    }

    private void printAllHeaders(Map<String, String> headers) {
        headers.forEach((key, value) -> {
            LOGGER.info(String.format("Header '%s' = %s", key, value));
        });
    }
}
