package guru.springframework.msscbreweryclient.web.client;

import guru.springframework.msscbreweryclient.web.model.CustomerDto;
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
public class CustomerClient {

	private final RestTemplate restTemplate;
	private final String CUSTOMER_PATH_V1 = "/api/v1/customer/";

	public CustomerDto getCustomerById(UUID uuid) {
		return restTemplate
				.getForObject(apiHost + CUSTOMER_PATH_V1 + UUID.randomUUID().toString(), CustomerDto.class);
	}

	public URI createCustomer(CustomerDto customerDto) {
		URI uri = null;

		try {
			uri = restTemplate.postForLocation(apiHost + CUSTOMER_PATH_V1, customerDto);
		} catch (Exception e) {
			log.error(e.getMessage());
		}

		return uri;
	}

	public void updateCustomer(UUID uuid, CustomerDto CustomerDto) {
		try {
			restTemplate.put(apiHost + CUSTOMER_PATH_V1 + uuid.toString(), CustomerDto);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	public void deleteCustomer(UUID uuid) {
		restTemplate.delete(apiHost + CUSTOMER_PATH_V1 + uuid.toString());
	}

	@Setter
	private String apiHost;

}
