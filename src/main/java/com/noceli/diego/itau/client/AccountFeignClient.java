package com.noceli.diego.itau.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "AccountClient", url = "${feign.account-url}")
public interface AccountFeignClient {

    @GetMapping("/account/{accountId}")
    ResponseEntity<String> getAccountDetails(@PathVariable("accountId") String accountId);
}
