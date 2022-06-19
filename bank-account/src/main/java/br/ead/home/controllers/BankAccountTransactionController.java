package br.ead.home.controllers;

import br.ead.home.commands.DepositFundsCommand;
import br.ead.home.commands.WithdrawFundsCommand;
import br.ead.home.model.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
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
    public BaseResponse depositFunds(@AuthenticationPrincipal User user,
                                     @PathVariable(value = "bank-account-id") @NotBlank String bankAccountId,
                                     @RequestBody @Valid @NotNull DepositFundsCommand command) {

        log.info("User {} deposit a amount of {} for bank account {}", user.getUsername(),
                command.getAmount(), bankAccountId);

        return BaseResponse.builder()
                .message("Founds Deposit transaction was successfully completed!")
                .build();
    }

    @PostMapping(path = "{bank-account-id}/withdraws")
    public BaseResponse withdrawFunds(@AuthenticationPrincipal User user,
                                      @PathVariable(value = "bank-account-id") @NotBlank String bankAccountId,
                                      @RequestBody @Valid @NotNull WithdrawFundsCommand command) {

        log.info("User {} withdraw a amount of {} for bank account {}", user.getUsername(),
                command.getAmount(), bankAccountId);

        return BaseResponse.builder()
                .message("Founds Withdraw transaction was successfully completed!")
                .build();
    }
}
