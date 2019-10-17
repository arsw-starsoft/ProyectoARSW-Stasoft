package edu.eci.arsw.synchdrive;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.eci.arsw.synchdrive.model.Car;
import edu.eci.arsw.synchdrive.security.config.JwtTokenUtil;
import org.junit.Before;
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

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SynchdriveApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
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
	public void getAllCarsTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders
		.get("/cars").header("Authorization",authToken))
				.andDo(print())
				.andExpect(status().isAccepted());
	}

    @Test
    public void createCarTest() throws Exception{
	    Car testCar = new Car();
	    testCar.setPlate("12345");
	    testCar.setModel("sport");
	    testCar.setSeats(2);

	    mvc.perform(MockMvcRequestBuilders
                .post("/cars")
                .header("Authorization",authToken)
                .content(asJsonString(testCar))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }



    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
