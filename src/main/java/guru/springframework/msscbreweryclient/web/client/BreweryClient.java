package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.BeerDto;
import java.net.URI;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author zawadma
 * @date 24/01/2021 11:10
 */
@Component
@ConfigurationProperties(value = "sfg.brewery", ignoreUnknownFields = false)
@RequiredArgsConstructor
@Slf4j
public class BreweryClient {

	private final RestTemplate restTemplate;
	private final String BEER_PATH_V1 = "/api/v1/beer/";

	public BeerDto getBeerById(UUID uuid) {
		return restTemplate
				.getForObject(apiHost + BEER_PATH_V1 + UUID.randomUUID().toString(), BeerDto.class);
	}

	public URI saveNewBeer(BeerDto beerDto) {
		URI uri = null;

		try {
			uri = restTemplate.postForLocation(apiHost + BEER_PATH_V1, beerDto);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return uri;
	}

	public void updateBeer(UUID uuid, BeerDto beerDto) {
		try {
			restTemplate.put(apiHost + BEER_PATH_V1 + uuid.toString(), beerDto);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	public void deleteBeer(UUID uuid) {
		restTemplate.delete(apiHost + BEER_PATH_V1 + uuid.toString());
	}

	@Setter
	private String apiHost;

}
