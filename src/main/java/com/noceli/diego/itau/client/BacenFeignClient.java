package com.noceli.diego.itau.client;

import com.noceli.diego.itau.client.model.BacenTransactionRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name = "BacenClient", url = "${feign.bacen-url}")
public interface BacenFeignClient {

    @PostMapping("/notifyTransaction")
    ResponseEntity<Void> notifyTransaction(@RequestBody BacenTransactionRequest transactionRequest);
}
