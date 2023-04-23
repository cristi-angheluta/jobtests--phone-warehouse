package wh.phones.fe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.web.reactive.server.WebTestClient;
import wh.phones.be.WarehouseDirectory;

@Profile("test")
@DisplayName("Controllers :: PhoneController ['/phones/*']")
@WebFluxTest(controllers = PhoneController.class)
class PhoneControllerTest {

    private final String listAvailabilityURI = "/phones/availability";
    private final String bookPhoneURI = "/phones/book";
    private final String returnPhoneURI = "/phones/return";
    @Autowired
    private WebTestClient webClient;

    @MockBean
    private WarehouseDirectory warehouse;

    @BeforeEach
    void setUp() {
    }

    @Test
    void listAvailability() {
    }

    @Test
    void bookPhone() {
    }

    @Test
    void returnPhone() {
    }
}
