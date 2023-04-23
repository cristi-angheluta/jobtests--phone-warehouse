package wh.phones.fe;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.reactive.server.WebTestClient;
import wh.phones.PhoneWarehouseApp;
import wh.phones.config.Constants;
import wh.phones.config.TestDbConfig;

import java.util.List;

import static java.lang.String.format;
import static java.time.Duration.ofMinutes;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;
import static org.springframework.test.jdbc.JdbcTestUtils.deleteFromTables;
import static org.springframework.test.web.reactive.server.WebTestClient.bindToServer;

@Profile("test")
@SpringBootTest(classes = {PhoneWarehouseApp.class, TestDbConfig.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("e2e :: PhoneController ['/phones/*']")
class PhoneControllerIntegrationTest {

    @LocalServerPort
    private int webServerPort;

    private WebTestClient webClient;

    @Autowired
    private JdbcTemplate template;

    @BeforeEach
    void setUp() {
        String baseUrl = format("http://localhost:%s/phones", webServerPort);
        webClient = bindToServer().baseUrl(baseUrl).responseTimeout(ofMinutes(1)).build();
        //
        int devicesNo = countRowsInTable(template, Constants.Db.TABLE__PHONES);
        assertEquals(10, devicesNo, "test-init: Wrong number of devices");

        int bookingsNo = countRowsInTable(template, Constants.Db.TABLE__BOOKINGS);
        assertEquals(0, bookingsNo, "test-init: Wrong number of bookings");

        int phoneNotReturned = countRowsInTable(template, Constants.Db.VIEW__PHONES_NOT_RETURNED);
        assertEquals(0, phoneNotReturned, "test-init: Wrong number of not returned phones");
    }

    @AfterEach
    void tearDown() {
        deleteFromTables(template, Constants.Db.TABLE__PHONES);
        deleteFromTables(template, Constants.Db.TABLE__BOOKINGS);
    }

    @DisplayName("List available devices :: Fetch all phones as available")
    @Test
    void fetch_all_phones_as_available() {
        // given - nothing to set

        // when
        var body = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/availability")
                        .build())
                .exchange()
                // then
                .expectStatus().is2xxSuccessful()
                .expectBody(List.class).returnResult().getResponseBody();
        // TODO @cristi uncomment next assertion
            /*assertThat(body).hasSize(10).allMatch(o -> {
                if(o instanceof PhoneAvailability pa){
                    return pa.available();
                }
                return false;
            });*/

    }
}
