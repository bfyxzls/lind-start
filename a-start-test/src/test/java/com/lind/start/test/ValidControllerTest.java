package com.lind.start.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lind.start.test.dto.DepartmentDto;
import com.lind.start.test.dto.Employee;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ValidControllerTest {

	protected MockMvc mockMvc;

	ObjectMapper objectMapper;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		objectMapper = new ObjectMapper();
	}

	@SneakyThrows
	@Test
	public void initialAccount_employee_name_empty() {
		List<Employee> employees = new ArrayList<>();
		employees.add(Employee.builder().name("").email("zzl@sina.com").idNumber("110111198203182012").build());
		DepartmentDto department = DepartmentDto.builder().name("部门").employees(employees).build();
		String requestBody = objectMapper.writeValueAsString(department);
		log.info("requestBody:{}", requestBody);
		mockMvc.perform(post("/valid/add/1").accept(MediaType.APPLICATION_JSON_UTF8)
				.contentType(MediaType.APPLICATION_JSON_UTF8).content(requestBody)).andExpect(status().isOk());

	}

}
