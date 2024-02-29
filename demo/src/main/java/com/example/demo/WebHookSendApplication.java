package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("plot")
public class WebHookSendApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebHookSendApplication.class, args);
	}
	
	@GetMapping
	public String ola(){
		return "hello";
	}


	@PostMapping("/send")
    public ResponseEntity<String> sendWebhook() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("key", "value");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(payload, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
            "http://localhost:8080/plot/webhook",
            HttpMethod.POST,
            entity,
            String.class
        );

        System.out.println("Response Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody());

        return ResponseEntity.ok("Webhook enviado com sucesso");
    }

	@PostMapping("/sendUsers")
    public ResponseEntity<String> sendWebhookComUsuario(@RequestBody Pessoa pessoa) {
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Pessoa> entity = new HttpEntity<>(pessoa, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
            "http://localhost:8080/api/pessoa",
            HttpMethod.POST,
            entity,
            String.class
        );

        System.out.println("Response Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody());

        return ResponseEntity.ok("Webhook enviado com sucesso");
    }
	
}
