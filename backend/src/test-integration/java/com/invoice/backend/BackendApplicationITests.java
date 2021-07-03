package com.invoice.backend;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendApplicationITests {

	@Test
	void contextLoads() {
		Assertions.assertDoesNotThrow(() -> this);
	}

}
