package br.ead.home.controllers;

import br.ead.home.commands.OpenAccountCommand;
import br.ead.home.common.model.AccountType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin", password = "password", roles = {"ADMIN"})
public class BankAccountControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Should open a Current Bank Account when requested")
    public void shouldUpdateTheServiceWhenTheServiceAlreadyExists() throws Exception {
        // given: the account details
        var expectedAccountHolder = "daniel.vasconcelos";
        var expectedAccountType = AccountType.CURRENT;
        var expectedOpeningBalance = 100.0;

        //when: the command is dispatched
        var command = new OpenAccountCommand(expectedAccountHolder, expectedAccountType, expectedOpeningBalance);
        var response = openABankAccount(command);

        //then: the account is opened
        response.andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Bank Account was successfully opened!"))
                .andExpect(jsonPath("$.id").isNotEmpty());
    }

    private ResultActions openABankAccount(OpenAccountCommand command) throws Exception {
        // given: the account details
        var content = objectMapper.writeValueAsString(command);

        var request = post("/api/v1/bank-accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        return mvc.perform(request).andDo(print());
    }
}
