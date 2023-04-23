package wh.phones.fe;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.test.web.reactive.server.WebTestClient;
import wh.phones.be.WarehouseDirectory;
import wh.phones.be.domain.model.PhoneAvailability;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

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

    private static List<PhoneAvailability> generateDummies(int counter) {
        return new ArrayList<>() {{
            for (int i = 0; i < counter; i++) {
                add(generateDummy());
            }
        }};
    }

    private static PhoneAvailability generateDummy() {
        return new PhoneAvailability(RandomUtils.nextLong(), RandomStringUtils.randomAlphanumeric(10, 20), RandomUtils.nextBoolean());
    }

    @Test
    void listAvailability() {
        // given
        var dummiesCounter = RandomUtils.nextInt(1, 10);
        given(warehouse.listPhonesAvailability()).willReturn(generateDummies(dummiesCounter));

        // when
        var list = webClient.get()
                .uri(uriBuilder -> uriBuilder.path(listAvailabilityURI)
                        .build())
                .exchange()
                // then
                .expectStatus().isOk()
                .expectBodyList(PhoneAvailability.class).returnResult().getResponseBody();

        // then
        assertNotNull(list);
        assertEquals(dummiesCounter, list.size(), "Invalid number of availabilities");

        verify(warehouse).listPhonesAvailability();
        verifyNoMoreInteractions(warehouse);
    }

    @Test
    void bookPhone() {
        
    }

    @Test
    void returnPhone() {
    }
}
