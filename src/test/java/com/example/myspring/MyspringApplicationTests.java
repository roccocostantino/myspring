package com.example.myspring;

import com.example.myspring.app.controller.CellulareRestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MyspringApplicationTests {

	@Autowired
	private CellulareRestController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}
}
