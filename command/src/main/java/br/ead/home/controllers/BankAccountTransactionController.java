package br.ead.home.controllers;

import br.ead.home.commands.DepositFundsCommand;
import br.ead.home.commands.WithdrawFundsCommand;
import br.ead.home.model.dto.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/bank-accounts/")
public class BankAccountTransactionController {

    @PostMapping(path = "{bank-account-id}/deposits")
    public BaseResponse depositFunds(
            @PathVariable(value = "bank-account-id") @NotBlank String bankAccountId,
            @RequestBody @Valid @NotNull DepositFundsCommand command) {
        log.info("A deposit amount of {} was received for bank account {}", command.getAmount(), bankAccountId);
        return BaseResponse.builder()
                .message("Founds Deposit transaction was successfully completed!")
                .build();
    }


    @PostMapping(path = "{bank-account-id}/withdraws")
    public BaseResponse withdrawFunds(
            @PathVariable(value = "bank-account-id") @NotBlank String bankAccountId,
            @RequestBody @Valid @NotNull WithdrawFundsCommand command) {
        log.info("A withdraw amount of {} was received for bank account {}", command.getAmount(), bankAccountId);
        return BaseResponse.builder()
                .message("Founds Withdraw transaction was successfully completed!")
                .build();
    }
}
