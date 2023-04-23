package wh.phones;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import wh.phones.config.TestDbConfig;

@SpringBootTest(classes = {PhoneWarehouseAppTests.class, TestDbConfig.class})
class PhoneWarehouseAppTests {

    @Test
    void contextLoads(ApplicationContext ctx) {
        Assertions.assertNotNull(ctx);
    }

}
