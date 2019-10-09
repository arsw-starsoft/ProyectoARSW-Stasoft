package edu.eci.arsw.synchdrive;

import edu.eci.arsw.synchdrive.security.config.JwtTokenUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static junit.framework.TestCase.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class JwtTokenAuthenticationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Test
    public void shouldNotAllowAccessToCarsToUnauthenticatedUsers() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/cars")).andExpect(status().isUnauthorized());
    }

    @Test
    public void shouldNotAllowAccessToUsersToUnauthenticatedUsers() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/users")).andExpect(status().isUnauthorized());
    }

    @Test
    public void shouldNotAllowAccessToDriversToUnauthenticatedUsers() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/drivers")).andExpect(status().isUnauthorized());
    }

    @Test
    public void shouldGenerateAuthToken() throws Exception {
        String token = jwtTokenUtil.createTestToken("test_user");
        assertNotNull(token);
        mvc.perform(MockMvcRequestBuilders.get("/cars").header("Authorization","Bearer "+ token)).andExpect(status().isAccepted());
    }

}
