package guru.springframework.msscbreweryclient.web.client;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import guru.springframework.msscbreweryclient.web.model.CustomerDto;
import java.net.URI;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author zawadma
 * @date 24/01/2021 11:19
 */
@SpringBootTest
@Slf4j
class CustomerClientTest {

	public static final UUID ID = UUID.randomUUID();

	@Autowired
	CustomerClient customerClient;

	@Test
	void getCustomerById() {
		CustomerDto customerDto = customerClient.getCustomerById(UUID.randomUUID());
		assertNotNull(customerDto);
	}

	@Test
	void createCustomer() {
		//given
		CustomerDto customerDto = CustomerDto.builder().id(ID).build();

		//when
		URI uri = customerClient.createCustomer(customerDto);

		//then
		assertNotNull(uri);
		log.info("Saved CustomerDto location : {}", uri);
	}

	@Test
	void updateCustomer() {
		//given
		CustomerDto customerDto = CustomerDto.builder().id(ID).build();

		//when
		customerClient.updateCustomer(ID, customerDto);

		//then
		log.info("Updated CustomerDto location : {}", customerDto);
	}

	@Test
	void deleteCustomer() {

		//when
		customerClient.deleteCustomer(ID);

		//then
		log.info("Deleted CustomerDto location");
	}
}
