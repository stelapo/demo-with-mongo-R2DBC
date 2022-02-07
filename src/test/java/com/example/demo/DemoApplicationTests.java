package com.example.demo;

import com.example.demo.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(
        classes = DemoMongoApplication.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.MethodName.class)
@ActiveProfiles("dev")
class DemoApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebTestClient webTestClient;

    final String context = "/demo/1.0.0";
    HttpHeaders httpHeaders;

    @BeforeEach
    void setup() {
        this.httpHeaders = new HttpHeaders();
        ArrayList<MediaType> mediaTypesAccepted = new ArrayList<>();
        mediaTypesAccepted.add(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(mediaTypesAccepted);
    }

    @Test
    public void test01_PostResource_mock() throws Exception {

        /*User userRequest = User.builder().name("Lapo").surname("Pancani").email("infolp@pippo.com").address("Via Rossi, Firenze").build();

        webTestClient.post().uri(context + "/user")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(userRequest), User.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.userId").isEqualTo(1);*/

        MvcResult result = mockMvc.perform(
                        post(context + "/user")
                                .headers(httpHeaders)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\n" +
                                        "    \"email\": \"infolp@pippo.com\",\n" +
                                        "    \"address\": \"Via Rossi, Firenze\",\n" +
                                        "    \"surname\": \"Pancani\",\n" +
                                        "    \"name\": \"Lapo\"\n" +
                                        "}")
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.name").value("Lapo"))
                .andExpect(jsonPath("$.surname").value("Pancani"))
                .andExpect(jsonPath("$.address").value("Via Rossi, Firenze"))
                .andExpect(jsonPath("$.email").value("infolp@pippo.com"))
                .andReturn();
        System.out.println("ECCOMI: " + result.getResponse().getContentAsString());

    }

    @Test
    public void test02_PostResource_wtc() throws Exception {

        User userRequest = User.builder().name("Lapo").surname("Pancani").email("infolp@pippo.com").address("Via Rossi, Firenze").build();

        webTestClient.post().uri(context + "/user")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(userRequest), User.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.userId").isEqualTo(2)
                .jsonPath("$.name").isEqualTo("Lapo")
                .jsonPath("$.surname").isEqualTo("Pancani")
                .jsonPath("$.address").isEqualTo("Via Rossi, Firenze")
                .jsonPath("$.email").isEqualTo("infolp@pippo.com");

    }

    @Test
    public void test03_PutResourc_mock() throws Exception {

        mockMvc.perform(
                        put(context + "/user/1")
                                .headers(httpHeaders)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\n" +
                                        "    \"email\": \"infolp@pippo.com\",\n" +
                                        "    \"address\": \"Via Verdi, Arezzo\",\n" +
                                        "    \"surname\": \"Rossi\",\n" +
                                        "    \"name\": \"Mario\"\n" +
                                        "}")
                )
                .andExpect(status().isCreated())
                //.andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.name").value("Mario"))
                .andExpect(jsonPath("$.surname").value("Rossi"))
                .andExpect(jsonPath("$.address").value("Via Verdi, Arezzo"))
                .andExpect(jsonPath("$.email").value("infolp@pippo.com"));
    }

    @Test
    public void test04_PutResource_wct() throws Exception {
        User userRequest = User.builder().name("Mario").surname("Rossi").email("infolp@pippo.com").address("Via Verdi, Arezzo").build();

        webTestClient.put().uri(context + "/user/2")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(userRequest), User.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.userId").isEqualTo(2)
                .jsonPath("$.name").isEqualTo("Mario")
                .jsonPath("$.surname").isEqualTo("Rossi")
                .jsonPath("$.address").isEqualTo("Via Verdi, Arezzo")
                .jsonPath("$.email").isEqualTo("infolp@pippo.com");
    }

    @Test
    public void test05_GetResource_mock() throws Exception {
        mockMvc.perform(
                        get(context + "/user/1")
                                .headers(httpHeaders)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(2))
                .andExpect(jsonPath("$.name").value("Mario"))
                .andExpect(jsonPath("$.surname").value("Rossi"))
                .andExpect(jsonPath("$.address").value("Via Verdi, Arezzo"))
                .andExpect(jsonPath("$.email").value("infolp@pippo.com"));
    }

    @Test
    public void test06_GetResource_wtc() throws Exception {
        webTestClient.get().uri(context + "/user/2")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.userId").isEqualTo(2)
                .jsonPath("$.name").isEqualTo("Mario")
                .jsonPath("$.surname").isEqualTo("Rossi")
                .jsonPath("$.address").isEqualTo("Via Verdi, Arezzo")
                .jsonPath("$.email").isEqualTo("infolp@pippo.com");
    }

    /*@Test
    public void test04_SearchResource() throws Exception {
        mockMvc.perform(
                        get(context + "/users")
                                .headers(httpHeaders)
                                .queryParam("name", "io")
                                .queryParam("surname", "ss")
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.totalElements").value(1))
                .andExpect(jsonPath("$.content[0].userId").value(1))
                .andExpect(jsonPath("$.content[0].name").value("Mario"))
                .andExpect(jsonPath("$.content[0].surname").value("Rossi"))
                .andExpect(jsonPath("$.content[0].address").value("Via Verdi, Arezzo"))
                .andExpect(jsonPath("$.content[0].email").value("infolp@pippo.com"));
    }

    @Test
    public void test05_PostResource() throws Exception {

        mockMvc.perform(
                        post(context + "/user")
                                .headers(httpHeaders)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\n" +
                                        "    \"email\": \"infolp@pippo.com\",\n" +
                                        "    \"address\": \"Via Rossi, Firenze\",\n" +
                                        "    \"surname\": \"Pancani\",\n" +
                                        "    \"name\": \"Lapo\"\n" +
                                        "}")
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId").value(2))
                .andExpect(jsonPath("$.name").value("Lapo"))
                .andExpect(jsonPath("$.surname").value("Pancani"))
                .andExpect(jsonPath("$.address").value("Via Rossi, Firenze"))
                .andExpect(jsonPath("$.email").value("infolp@pippo.com"));
    }

    @Test
    public void test06_SearchWithSort() throws Exception {
        mockMvc.perform(
                        get(context + "/users")
                                .headers(httpHeaders)
                                .queryParam("sort", "userId,desc")
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.totalElements").value(2))
                .andExpect(jsonPath("$.content[0].userId").value(2))
                .andExpect(jsonPath("$.content[0].name").value("Lapo"))
                .andExpect(jsonPath("$.content[0].surname").value("Pancani"))
                .andExpect(jsonPath("$.content[0].address").value("Via Rossi, Firenze"))
                .andExpect(jsonPath("$.content[0].email").value("infolp@pippo.com"));
    }

    @Test
    public void test07_SearchWithPageable() throws Exception {
        mockMvc.perform(
                        get(context + "/users")
                                .headers(httpHeaders)
                                .queryParam("sort", "userId,desc")
                                .queryParam("page", "1")
                                .queryParam("size", "1")
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.totalElements").value(2))
                .andExpect(jsonPath("$.content[0].userId").value(1))
                .andExpect(jsonPath("$.content[0].name").value("Mario"))
                .andExpect(jsonPath("$.content[0].surname").value("Rossi"))
                .andExpect(jsonPath("$.content[0].address").value("Via Verdi, Arezzo"))
                .andExpect(jsonPath("$.content[0].email").value("infolp@pippo.com"));
    }

    @Test
    public void test08_SearchWithName() throws Exception {
        mockMvc.perform(
                        get(context + "/users")
                                .headers(httpHeaders)
                                .queryParam("name", "lap")
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.totalElements").value(1))
                .andExpect(jsonPath("$.content[0].userId").value(2))
                .andExpect(jsonPath("$.content[0].name").value("Lapo"))
                .andExpect(jsonPath("$.content[0].surname").value("Pancani"))
                .andExpect(jsonPath("$.content[0].address").value("Via Rossi, Firenze"))
                .andExpect(jsonPath("$.content[0].email").value("infolp@pippo.com"));
    }

    @Test
    public void test09_SearchWithSurName() throws Exception {
        mockMvc.perform(
                        get(context + "/users")
                                .headers(httpHeaders)
                                .queryParam("surname", "ss")
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.totalElements").value(1))
                .andExpect(jsonPath("$.content[0].userId").value(1))
                .andExpect(jsonPath("$.content[0].name").value("Mario"))
                .andExpect(jsonPath("$.content[0].surname").value("Rossi"))
                .andExpect(jsonPath("$.content[0].address").value("Via Verdi, Arezzo"))
                .andExpect(jsonPath("$.content[0].email").value("infolp@pippo.com"));
    }

    @Test
    public void test10_SearchWithSearchString() throws Exception {
        mockMvc.perform(
                        get(context + "/search")
                                .headers(httpHeaders)
                                .queryParam("searchString", "name:\"m\"~surname:\"r\"")
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.totalElements").value(1))
                .andExpect(jsonPath("$.content[0].userId").value(1))
                .andExpect(jsonPath("$.content[0].name").value("Mario"))
                .andExpect(jsonPath("$.content[0].surname").value("Rossi"))
                .andExpect(jsonPath("$.content[0].address").value("Via Verdi, Arezzo"))
                .andExpect(jsonPath("$.content[0].email").value("infolp@pippo.com"));
    }

    @Test
    public void test11_SearchWith_SearchString_And_Pageable() throws Exception {
        mockMvc.perform(
                        get(context + "/search")
                                .headers(httpHeaders)
                                .queryParam("searchString", "name:\"a\"")
                                .queryParam("sort", "userId,asc")
                                .queryParam("page", "0")
                                .queryParam("size", "2")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.totalElements").value(2))
                .andExpect(jsonPath("$.content[0].userId").value(1))
                .andExpect(jsonPath("$.content[1].userId").value(2));
    }

    @Test
    public void test12_SearchWith_SearchStringEmpty_BAD_REQUEST() throws Exception {
        mockMvc.perform(
                        get(context + "/search")
                                .headers(httpHeaders)
                                .queryParam("searchString", "searchString", "")
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.violations[0].fieldName").value("searchUsers.searchString"))
                .andExpect(jsonPath("$.violations[0].message").value("must match \"((\\w+?)(:|<|>)\"([^\"]+)\"[~]?)+?\""));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    public void test13_DeleteResource(int userId) throws Exception {
        mockMvc.perform(
                        delete(context + "/user/" + userId)
                                .headers(httpHeaders)
                ).andExpect(status().isNoContent());
    }

    @Test
    public void test14_PutResource_NOT_FOUND() throws Exception {

        mockMvc.perform(
                        put(context + "/user/1")
                                .headers(httpHeaders)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\n" +
                                        "    \"email\": \"infolp@pippo.com\",\n" +
                                        "    \"address\": \"Via Verdi, Arezzo\",\n" +
                                        "    \"surname\": \"Rossi\",\n" +
                                        "    \"name\": \"Mario\"\n" +
                                        "}")
                )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.httpStatus").value(404))
                .andExpect(jsonPath("$.error").value("Resource Not Found"))
                .andExpect(jsonPath("$.message").value("User not found with id 1"));
    }

    @Test
    public void test15_GetResource() throws Exception {
        mockMvc.perform(
                        get(context + "/user/2")
                                .headers(httpHeaders)
                )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.httpStatus").value(404))
                .andExpect(jsonPath("$.error").value("Resource Not Found"))
                .andExpect(jsonPath("$.message").value("User not found with id 2"));
    }

    @Test
    public void test16_PostResource_EMAIL_NOT_VALID() throws Exception {

        mockMvc.perform(
                        post(context + "/user")
                                .headers(httpHeaders)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\n" +
                                        "    \"email\": \"infolp\",\n" +
                                        "    \"address\": \"Via Rossi, Firenze\",\n" +
                                        "    \"surname\": \"Pancani\",\n" +
                                        "    \"name\": \"Lapo\"\n" +
                                        "}")
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.violations[0].fieldName").value("email"))
                .andExpect(jsonPath("$.violations[0].message").value("Email should be valid"));
    }*/

}
