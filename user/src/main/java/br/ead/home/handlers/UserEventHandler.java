package br.ead.home.handlers;

import br.ead.home.events.UserRegisteredEvent;
import br.ead.home.events.UserRemovedEvent;
import br.ead.home.events.UserUpdatedEvent;

public interface UserEventHandler {

    void on(UserRegisteredEvent event);
    void on(UserUpdatedEvent event);
    void on(UserRemovedEvent event);
}
