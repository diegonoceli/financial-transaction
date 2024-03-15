package com.noceli.diego.itau.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(name = "BacenClient", url = "${feign.bacen-url}")
public interface BacenFeignClient {

    @GetMapping("/notifyTransaction")
    ResponseEntity<String> notifyTransaction();
}
