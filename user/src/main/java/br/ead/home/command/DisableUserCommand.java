package br.ead.home.command;

import br.ead.home.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DisableUserCommand {

    private String commandId;
    private User user;
}
