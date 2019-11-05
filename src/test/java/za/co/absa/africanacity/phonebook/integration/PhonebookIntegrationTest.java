package za.co.absa.africanacity.phonebook.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import za.co.absa.africanacity.phonebook.controller.PhonebookController;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PhonebookIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private final static String MEDIA_TYPE = "text/html;charset=UTF-8";

    @Test
    public void testIfCanReachPhonebookLaunchEndpoint() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/phonebook"))
                .andExpect(MockMvcResultMatchers.content().contentType(MEDIA_TYPE))
                .andExpect(status().isOk());
    }

    @Test
    public void testIfCanReachPhonebookAddNewEndpoint() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/addnew"))
                .andExpect(MockMvcResultMatchers.content().contentType(MEDIA_TYPE))
                .andExpect(status().isOk());
    }

    @Test
    public void testIfCanReachPhonebookAddEndpoint() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/add"))
                .andExpect(MockMvcResultMatchers.content().contentType(MEDIA_TYPE))
                .andExpect(status().isOk());
    }

    @Test
    public void testIfCanReachPhonebookSearchEndpoint() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/search?option=name&search=WEW"))
                .andExpect(MockMvcResultMatchers.content().contentType(MEDIA_TYPE))
                .andExpect(status().isOk());
    }

    @Test
    public void testIfCanReachPhonebookDeleteEndpoint() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/entry/delete?id=1"))
                .andExpect(status().isOk());
    }
}
