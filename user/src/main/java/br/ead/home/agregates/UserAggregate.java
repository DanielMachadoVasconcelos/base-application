package br.ead.home.agregates;

import br.ead.home.commands.RemoveUserCommand;
import br.ead.home.commands.UpdateUserCommand;
import br.ead.home.commands.RegisterUserCommand;
import br.ead.home.events.UserRegisteredEvent;
import br.ead.home.events.UserUpdatedEvent;
import br.ead.home.events.UserRemovedEvent;
import br.ead.home.model.User;
import br.ead.home.model.UserAccount;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@Data
@Aggregate
@NoArgsConstructor
public class UserAggregate {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @AggregateIdentifier
    private String id;
    private User user;

    @CommandHandler
    public UserAggregate(RegisterUserCommand command) {

        UserAccount insecurityUserAccount = command.getUser().getAccount();
        String insecurityPassword = insecurityUserAccount.getPassword();

        User securityUser = command.getUser()
                .withAccount(insecurityUserAccount
                        .withPassword(passwordEncoder.encode(insecurityPassword)));

        AggregateLifecycle.apply(UserRegisteredEvent.builder()
                        .id(command.getId())
                        .user(securityUser)
                .build());
    }

    @CommandHandler
    public void handle(UpdateUserCommand command) {
        UserAccount insecurityUserAccount = command.getUser().getAccount();
        String insecurityPassword = insecurityUserAccount.getPassword();

        User securityUser = command.getUser()
                .withAccount(insecurityUserAccount
                        .withPassword(passwordEncoder.encode(insecurityPassword)));

        AggregateLifecycle.apply(UserUpdatedEvent.builder()
                .id(UUID.randomUUID().toString())
                .user(securityUser)
                .build());
    }

    @CommandHandler
    public void handle(RemoveUserCommand command) {
        AggregateLifecycle.apply(UserRemovedEvent.builder()
                .id(command.getId())
                .build());
    }

    @EventSourcingHandler
    public void on(UserRegisteredEvent event) {
        this.id = event.getId();
        this.user = event.getUser();
    }

    @EventSourcingHandler
    public void on(UserUpdatedEvent event) {
        this.user = event.getUser();
    }

    @EventSourcingHandler
    public void on(UserRemovedEvent event) {
        AggregateLifecycle.markDeleted();
    }
}
