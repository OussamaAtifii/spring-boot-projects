package com.oussama.databasepostgree.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oussama.databasepostgree.TestDataUtil;
import com.oussama.databasepostgree.models.dto.AuthorDto;
import com.oussama.databasepostgree.models.entities.AuthorEntity;
import com.oussama.databasepostgree.services.AuthorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class AuthorControllerIntegrationTests {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private AuthorService authorService;

    @Autowired
    public AuthorControllerIntegrationTests(MockMvc mockMvc, AuthorService authorService) {
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
        this.authorService = authorService;
    }

    @Test
    public void testThatCreateAuthorSuccessfullyReturnsHttp201Created() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
        testAuthorA.setId(null);
        String authorJson = objectMapper.writeValueAsString(testAuthorA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateAuthorSuccessfullyReturnsSavedAuthor() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
        testAuthorA.setId(null);
        String authorJson = objectMapper.writeValueAsString(testAuthorA);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Oussama")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(25)
        );
    }

    @Test
    public void testThatListAuthorsReturnsHttpStatus200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatListAuthorsReturnsListOfAuthors() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
        authorService.save(testAuthorA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].name").value("Oussama")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].age").value(25)
        );
    }

    @Test
    public void testThatGetAuthorReturnsHttpStatus200WhenAuthorExist() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
        authorService.save(testAuthorA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatGetAuthorReturnsHttpStatus404WhenNoAuthorExist() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatGetAuthorReturnsAuthorWhenAuthorExist() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
        authorService.save(testAuthorA);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(1)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Oussama")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(25)
        );
    }

    @Test
    public void testThatFullUpdateAuthorReturnsHttpStatus404WhenNoAuthorExist() throws Exception {
        AuthorDto testAuthorDtoA = TestDataUtil.createTestAuthorDtoA();
        String authorDtoJson = objectMapper.writeValueAsString(testAuthorDtoA);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/authors/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoJson)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatFullUpdateAuthorReturnsHttpStatus200WhenAuthorExist() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
        AuthorEntity savedAuthorEntity = authorService.save(testAuthorA);

        AuthorDto testAuthorDtoA = TestDataUtil.createTestAuthorDtoA();
        String authorDtoJson = objectMapper.writeValueAsString(testAuthorDtoA);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/authors/" + savedAuthorEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatFullUpdateUpdatedExistingAuhtor() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
        AuthorEntity savedAuthorEntity = authorService.save(testAuthorA);

        AuthorEntity authorDto = TestDataUtil.createTestAuthorB();
        authorDto.setId(savedAuthorEntity.getId());

        String authorDtoUpdatedJson = objectMapper.writeValueAsString(authorDto);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/authors/" + savedAuthorEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoUpdatedJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(savedAuthorEntity.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value(authorDto.getName())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(authorDto.getAge())
        );
    }

    @Test
    public void testThatPartialUpdateExistingAuthorReturnsHttpStatus200Ok() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
        AuthorEntity savedAuthorEntity = authorService.save(testAuthorA);

        AuthorDto testAuthorDtoA = TestDataUtil.createTestAuthorDtoA();
        testAuthorDtoA.setName("Updated uwu");
        String authorDtoJson = objectMapper.writeValueAsString(testAuthorDtoA);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/authors/" + savedAuthorEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoJson)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatPartialUpdateExistingAuthorReturnsUpdatedAuthor() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
        AuthorEntity savedAuthorEntity = authorService.save(testAuthorA);

        AuthorDto testAuthorDtoA = TestDataUtil.createTestAuthorDtoA();
        testAuthorDtoA.setName("Updated uwu");
        String authorDtoJson = objectMapper.writeValueAsString(testAuthorDtoA);

        mockMvc.perform(
                MockMvcRequestBuilders.patch("/authors/" + savedAuthorEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorDtoJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").value(savedAuthorEntity.getId())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.name").value("Updated uwu")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.age").value(testAuthorDtoA.getAge())
        );
    }

    @Test
    public void testThatDeleteAuthorReturnsHttpStatus204ForNonExistingAuthor() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/authors/324")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testThatDeleteAuthorReturnsHttpStatus204WhenExistingAuthor() throws Exception {
        AuthorEntity testAuthorA = TestDataUtil.createTestAuthorA();
        AuthorEntity savedAuthorEntity = authorService.save(testAuthorA);

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/authors/" + savedAuthorEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
