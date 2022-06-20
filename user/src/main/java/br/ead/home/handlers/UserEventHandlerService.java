package br.ead.home.handlers;

import br.ead.home.events.UserRegisteredEvent;
import br.ead.home.events.UserRemovedEvent;
import br.ead.home.events.UserUpdatedEvent;
import br.ead.home.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;

@Slf4j
@AllArgsConstructor
@ProcessingGroup("user-group")
public class UserEventHandlerService implements UserEventHandler {

    UserRepository userRepository;

    @Override
    @EventHandler
    public void on(UserRegisteredEvent event) {
        userRepository.save(event.getUser());
    }

    @Override
    @EventHandler
    public void on(UserUpdatedEvent event) {
        userRepository.save(event.getUser());
    }

    @Override
    @EventHandler
    public void on(UserRemovedEvent event) {
        userRepository.deleteById(event.getId());
    }
}
