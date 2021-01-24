package guru.springframework.msscbreweryclient.web.client;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
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
class BreweryClientTest {

	public static final UUID ID = UUID.randomUUID();

	@Autowired
	BreweryClient breweryClient;

	@Test
	void getBeerById() {
		BeerDto beerDto = breweryClient.getBeerById(UUID.randomUUID());
		assertNotNull(beerDto);
	}

	@Test
	void saveNewBeer() {
		//given
		BeerDto beerDto = BeerDto.builder().beerName("SaveNewBeer").id(UUID.randomUUID()).build();

		//when
		URI uri = breweryClient.saveNewBeer(beerDto);

		//then
		assertNotNull(uri);
		log.info("Saved BeerDto location : {}", uri);
	}

	@Test
	void updateBeer() {
		//given
		BeerDto beerDto = BeerDto.builder().beerName("SaveNewBeer").id(ID).build();

		//when
		breweryClient.updateBeer(ID, beerDto);

		//then
		log.info("Updated BeerDto location : {}", beerDto);
	}

	@Test
	void deleteBeer() {

		//when
		breweryClient.deleteBeer(ID);

		//then
		log.info("Deleted BeerDto location");
	}
}
