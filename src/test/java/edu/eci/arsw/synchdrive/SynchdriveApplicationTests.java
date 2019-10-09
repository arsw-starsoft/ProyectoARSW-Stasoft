package edu.eci.arsw.synchdrive;

import edu.eci.arsw.synchdrive.model.Customer;
import edu.eci.arsw.synchdrive.persistence.UserRepository;
import edu.eci.arsw.synchdrive.security.config.JwtTokenUtil;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SynchdriveApplication.class)
@AutoConfigureMockMvc
public class SynchdriveApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	private String authToken;

	@Before
	public void createAuthToken(){
		if (authToken == null)
			authToken = "Bearer " + jwtTokenUtil.createTestToken("test_user");
	}

	@Test
	public void getAllCarsAPI() throws Exception {
		mvc.perform(MockMvcRequestBuilders
		.get("/cars").header("Authorization",authToken))
				.andDo(print())
				.andExpect(status().isAccepted());
	}


}
