package com.noceli.diego.itau.client;

import com.noceli.diego.itau.client.model.AccountResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "AccountClient", url = "${feign.account-url}")
public interface AccountFeignClient {

    @GetMapping("/account/{accountId}")
    AccountResponse getAccountDetails(@PathVariable("accountId") String accountId);
}
