package edu.eci.arsw.synchdrive;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.eci.arsw.synchdrive.model.Car;
import edu.eci.arsw.synchdrive.model.Customer;
import edu.eci.arsw.synchdrive.model.Driver;
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

    @Test
    public void searchCarByPlateTest() throws Exception{
        Car testCar = new Car();
        testCar.setPlate("123");
        testCar.setModel("sport");
        testCar.setSeats(2);

        mvc.perform(MockMvcRequestBuilders
                .post("/cars")
                .header("Authorization",authToken)
                .content(asJsonString(testCar))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        //not found
	    mvc.perform(MockMvcRequestBuilders
                .get("/cars/not-a-plate")
                .header("Authorization",authToken))
                .andExpect(status().isNotFound());
	    //found
	    mvc.perform(MockMvcRequestBuilders
                .get("/cars/123")
                .header("Authorization",authToken))
                .andExpect(status().isAccepted());
    }

    @Test
    public void getAllCustomersTest() throws Exception{
	    mvc.perform(MockMvcRequestBuilders
                .get("/users")
                .header("Authorization",authToken))
                .andExpect(status().isAccepted());
    }

    @Test
    public void createCustomerTest() throws Exception{
        Customer testCustomer = new Customer();
        testCustomer.setEmail("test@email.com");
        testCustomer.setPassword("test-password");
        testCustomer.setName("test");

        mvc.perform(MockMvcRequestBuilders
                .post("/users")
                .header("Authorization",authToken)
                .content(asJsonString(testCustomer))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void searchCustomerByEmail() throws Exception{
        Customer testCustomer = new Customer();
        testCustomer.setEmail("test2@email.com");
        testCustomer.setPassword("test-password");
        testCustomer.setName("test");

        mvc.perform(MockMvcRequestBuilders
                .post("/users")
                .header("Authorization",authToken)
                .content(asJsonString(testCustomer))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
	    //not found
	    mvc.perform(MockMvcRequestBuilders
                .get("/users/not-a-user")
                .header("Authorization",authToken))
                .andExpect(status().isNotFound());

	    //found
        mvc.perform(MockMvcRequestBuilders
                .get("/users/test2@email.com")
                .header("Authorization",authToken))
                .andExpect(status().isAccepted());
    }

    @Test
    public void getAllDriversTest() throws Exception{
	    mvc.perform(MockMvcRequestBuilders
                .get("/drivers")
                .header("Authorization",authToken))
                .andExpect(status().isAccepted());
    }

    @Test
    public void createDriverTest() throws Exception{
	    Driver testDriver = new Driver();
	    testDriver.setEmail("driver@test.com");
	    testDriver.setPassword("test");

	    mvc.perform(MockMvcRequestBuilders
                .post("/drivers")
                .header("Authorization",authToken)
                .content(asJsonString(testDriver))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void searchDriverByEmail() throws Exception{
        Driver testDriver = new Driver();
        testDriver.setEmail("driver1@test.com");
        testDriver.setPassword("test");

        //creating
        mvc.perform(MockMvcRequestBuilders
                .post("/drivers")
                .header("Authorization",authToken)
                .content(asJsonString(testDriver))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        //not found
        mvc.perform(MockMvcRequestBuilders
                .get("/drivers/not-a-driver")
                .header("Authorization",authToken))
                .andExpect(status().isNotFound());

        //found
        mvc.perform(MockMvcRequestBuilders
                .get("/drivers/driver1@test.com")
                .header("Authorization",authToken))
                .andExpect(status().isAccepted());
    }

    //Method for making a json string
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
