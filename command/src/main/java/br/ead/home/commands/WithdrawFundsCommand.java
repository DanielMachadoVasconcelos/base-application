package br.ead.home.commands;

import br.ead.home.common.commands.BaseCommand;
import lombok.Data;

import javax.validation.constraints.Positive;

@Data
public class WithdrawFundsCommand extends BaseCommand {

    @Positive
    private double amount;
}
