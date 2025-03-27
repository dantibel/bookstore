package fi.haagahelia.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
//@AutoConfigureMockMvc
public class RestAPITests {
    /* 
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    public void getAllBooks() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/books")).andDo(print()).andExpect(status().isOk()).andReturn();
        assertThat(result.getResponse().getContentAsString())
            .contains("books")
            .contains("The Invincible")
            .contains("Crime and Punishment")
            .contains("Oddysey");
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    public void findBookById() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/books/1")).andDo(print()).andExpect(status().isOk()).andReturn();
        assertThat(result.getResponse().getContentAsString())
            .contains("title").contains("Animal Farm")
            .contains("author").contains("George Orwell")
            .contains("year").contains("1945")
            .contains("isbn")
            .contains("price");
    }
            */
}

