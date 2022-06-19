package br.ead.home.controllers;

import br.ead.home.commands.OpenAccountCommand;
import br.ead.home.model.BaseResponse;
import br.ead.home.model.OpenAccountResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/bank-accounts")
public class BankAccountController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse openAccount(@AuthenticationPrincipal User user,
                                    @RequestBody @Valid @NotNull OpenAccountCommand command) {

        log.info("User {} opened a bank account for customer {} with initial amount {}.",
                user.getUsername(), command.getAccountHolder(), command.getOpeningBalance());

        return OpenAccountResponse.builder()
                .message("Bank Account was successfully opened!")
                .id(UUID.randomUUID().toString())
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(path = "/{bank-account-id}/close")
    public BaseResponse closeAccount(@AuthenticationPrincipal User user,
                                     @PathVariable("bank-account-id") @NotBlank String bankAccountId) {

        log.info("User {} closed the bank account {}", user.getUsername(), bankAccountId);

        return OpenAccountResponse.builder()
                .message("Bank Account was successfully closed!")
                .id(UUID.randomUUID().toString())
                .build();
    }
}
