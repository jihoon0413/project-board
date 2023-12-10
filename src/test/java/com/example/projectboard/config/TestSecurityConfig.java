package com.example.projectboard.config;

import com.example.projectboard.domain.UserAccount;
import com.example.projectboard.repository.UserAccountRepository;
import org.mockito.BDDMockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;

@Import(SecurityConfig.class)
public class TestSecurityConfig {

    @MockBean private UserAccountRepository userAccountRepository;

    @BeforeTestMethod
    public void securitySetUp() {
        BDDMockito.given(userAccountRepository.findById(anyString())).willReturn(Optional.of(UserAccount.of(
                "jihoonTest",
                "pw",
                "jihoon-test@email.com",
                "jihoon_test",
                "test memo"
        )));
    }


}
