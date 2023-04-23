package wh.phones;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import wh.phones.config.TestDbConfig;

@DisplayName("App")
@SpringBootTest(classes = {PhoneWarehouseAppTests.class, TestDbConfig.class})
class PhoneWarehouseAppTests {

    @DisplayName("App Context is properly initialized")
    @Test
    void contextLoads(ApplicationContext ctx) {
        Assertions.assertNotNull(ctx);
    }
}
