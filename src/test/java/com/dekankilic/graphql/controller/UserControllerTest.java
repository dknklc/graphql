package com.dekankilic.graphql.controller;

import com.dekankilic.graphql.model.User;
import com.dekankilic.graphql.model.enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@AutoConfigureGraphQlTester
class UserControllerTest {

    @Autowired
    GraphQlTester graphQlTester; // gives us an ability to execute queries and mutations

    @BeforeEach
    void setUp() {
        createUser(new User("dekan", "dekan@gmail.com", Role.ADMIN));
        createUser(new User("ahmet", "ahmet@gmail.com", Role.USER));
        createUser(new User("can", "can@gmail.com", Role.USER));
    }

    void createUser(User user) {
        String mutation = """
                mutation {
                    createUser(userRequest: {username: "%s", mail: "%s", role: %s}) {
                        id
                        username
                        role
                        createdAt
                        updatedAt
                    }
                }
                """.formatted(user.getUsername(), user.getMail(), user.getRole());

        graphQlTester.document(mutation).execute().path("createUser");
    }


    @Test
    void shouldReturnUserList_whenGetAllUsers() {

        // language=graphql
        String query = """
                query {
                    getAllUsers {
                        id
                        username
                        mail
                        role
                        createdAt
                        updatedAt
                    }
                }
                
                """;
        graphQlTester.document(query)
                .execute()
                .path("getAllUsers")
                .entityList(User.class)
                .hasSize(3);

    }

    @Test
    void shouldReturnNewUser_whenCreateUser() {
        String mutation = """
                mutation {
                    createUser(userRequest: {username: "example", mail: "example@gmail.com", role: ADMIN}) {
                        id
                        username
                        role
                        createdAt
                        updatedAt
                    }
                }
                """;
        graphQlTester.document(mutation)
                .execute()
                .path("createUser")
                .entity(User.class);

    }

}