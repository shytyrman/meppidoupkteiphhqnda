package com.example.meppidoupkteiphhqnda;

import com.example.meppidoupkteiphhqnda.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("local")
class MeppidoupkteiphhqndaApplicationTests {

    PersonRepository repository = Mockito.mock();

    @Test
    void contextLoads() {
    }

}
