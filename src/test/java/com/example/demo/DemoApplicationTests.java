package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(
        classes = DemoApplication.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.MethodName.class)
class DemoApplicationTests {

    @Autowired
    MockMvc mockMvc;

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
    public void test01PostResource() throws Exception {

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
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.name").value("Lapo"))
                .andExpect(jsonPath("$.surname").value("Pancani"))
                .andExpect(jsonPath("$.address").value("Via Rossi, Firenze"))
                .andExpect(jsonPath("$.email").value("infolp@pippo.com"));
    }

    @Test
    public void test02PutResource() throws Exception {

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
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.name").value("Mario"))
                .andExpect(jsonPath("$.surname").value("Rossi"))
                .andExpect(jsonPath("$.address").value("Via Verdi, Arezzo"))
                .andExpect(jsonPath("$.email").value("infolp@pippo.com"));
    }

    @Test
    public void test03GetResource() throws Exception {
        mockMvc.perform(
                        get(context + "/user/1")
                                .headers(httpHeaders)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.name").value("Mario"))
                .andExpect(jsonPath("$.surname").value("Rossi"))
                .andExpect(jsonPath("$.address").value("Via Verdi, Arezzo"))
                .andExpect(jsonPath("$.email").value("infolp@pippo.com"));
    }

    @Test
    public void test04SearchResource() throws Exception {
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
    public void test05PostResource() throws Exception {

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
    public void test06SearchWithSort() throws Exception {
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
    public void test07SearchWithSortAndSize() throws Exception {
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
    public void test08SearchWithName() throws Exception {
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
    public void test08SearchWithSurName() throws Exception {
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

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    public void test09DeleteResource(int userId) throws Exception {
        mockMvc.perform(
                        delete(context + "/user/" + userId)
                                .headers(httpHeaders)
                ).andExpect(status().isNoContent());
    }

}
