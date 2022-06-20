package br.ead.home.commands;

import br.ead.home.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RemoveUserCommand {

    @TargetAggregateIdentifier
    private String id;
    private User user;
}
