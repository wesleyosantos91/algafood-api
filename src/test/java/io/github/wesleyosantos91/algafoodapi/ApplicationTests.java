package io.github.wesleyosantos91.algafoodapi;

import io.github.wesleyosantos91.algafoodapi.utils.config.ContainerBaseConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests extends ContainerBaseConfig {

	@Test
	void contextLoads() {
		Assertions.assertTrue(true);
	}

}
