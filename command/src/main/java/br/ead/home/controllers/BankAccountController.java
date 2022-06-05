package br.ead.home.controllers;

import br.ead.home.commands.OpenAccountCommand;
import br.ead.home.model.dto.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/bank-accounts")
public class BankAccountController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse openAccount(@RequestBody @Valid @NotNull OpenAccountCommand command) {
        log.info("A bank account for customer {} is open with initial amount {}.",
                command.getAccountHolder(), command.getOpeningBalance());

        return BaseResponse.builder()
                .message("Bank Account was successfully opened!")
                .build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(path = "/{bank-account-id}/close")
    public BaseResponse closeAccount(@PathVariable("bank-account-id") @NotBlank String bankAccountId) {
        log.info("The bank account {} was closed.", bankAccountId);
        return BaseResponse.builder()
                .message("Bank Account was successfully closed!")
                .build();
    }
}
