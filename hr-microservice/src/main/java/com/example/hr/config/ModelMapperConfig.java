package com.example.hr.config;

import com.example.hr.domain.BirthYear;
import com.example.hr.domain.EmployeeLombok;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hr.document.EmployeeDocument;
import com.example.hr.domain.Employee;
import com.example.hr.domain.FullName;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.entity.EmployeeEntity;

@Configuration
public class ModelMapperConfig {

	private static final Converter<HireEmployeeRequest,Employee> 
	HIRE_EMPLOYEE_REQUEST_TO_EMPLOYEE_CONVERTER = context -> {
		var request = context.getSource();
		return new Employee.Builder()
				           .tcKimlikNo(request.getIdentity())
				           .fullName(request.getFirstName(), request.getLastName())
				           .iban(request.getIban())
				           .salary(request.getSalary(),request.getCurrency())
				           .birthYear(request.getBirthYear())
				           .photo(request.getPhoto())
				           .jobStyle(request.getJobStyle().name())
				           .department(request.getDepartment().name())
				           .build();
	};
	
	
	private static final Converter<EmployeeEntity,Employee> 
	EMPLOYEE_ENTITY_TO_EMPLOYEE_CONVERTER = context -> {
		var entity = context.getSource();
		return new Employee.Builder()
				.tcKimlikNo(entity.getIdentity())
				.fullName(entity.getFirstName(), entity.getLastName())
				.iban(entity.getIban())
				.salary(entity.getSalary(),entity.getCurrency())
				.birthYear(entity.getBirthYear())
				.photo(entity.getPhoto())
				.jobStyle(entity.getJobStyle().name())
				.department(entity.getDepartment().name())
				.build();
	};
	
	
	private static final Converter<EmployeeDocument,Employee> 
	EMPLOYEE_DOCUMENT_TO_EMPLOYEE_CONVERTER = context -> {
		var document = context.getSource();
		return new Employee.Builder()
				.tcKimlikNo(document.getIdentity())
				.fullName(document.getFirstName(), document.getLastName())
				.iban(document.getIban())
				.salary(document.getSalary(),document.getCurrency())
				.birthYear(document.getBirthYear())
				.photo(document.getPhoto())
				.jobStyle(document.getJobStyle().name())
				.department(document.getDepartment().name())
				.build();
	};
	
	private static final Converter<Employee,EmployeeResponse> 
	EMPLOYEE_TO_EMPLOYEE_RESPONSE_CONVERTER = context -> {
		var employee = context.getSource();
		var response = new EmployeeResponse();
		response.setIdentity(employee.getTcKimlikNo().getValue());
		FullName fullName = employee.getFullName();
		response.setFirstName(fullName.getFirstName());
		response.setLastName(fullName.getLastName());
		response.setIban(employee.getIban().getValue());
		response.setSalary(employee.getSalary().getValue());
		response.setCurrency(employee.getSalary().getCurrency());
		response.setDepartment(employee.getDepartment());
		response.setJobStyle(employee.getJobStyle());
		response.setBirthYear(employee.getBirthYear().getValue());
		response.setPhoto(employee.getPhoto().getBase64Values());
		return response;
	};

	
	private static final Converter<Employee,EmployeeEntity> 
	EMPLOYEE_TO_EMPLOYEE_ENTITY_CONVERTER = context -> {
		var employee = context.getSource();
		var entity = new EmployeeEntity();
		entity.setIdentity(employee.getTcKimlikNo().getValue());
		FullName fullName = employee.getFullName();
		entity.setFirstName(fullName.getFirstName());
		entity.setLastName(fullName.getLastName());
		entity.setIban(employee.getIban().getValue());
		entity.setSalary(employee.getSalary().getValue());
		entity.setCurrency(employee.getSalary().getCurrency());
		entity.setDepartment(employee.getDepartment());
		entity.setJobStyle(employee.getJobStyle());
		entity.setBirthYear(employee.getBirthYear().getValue());
		entity.setPhoto(employee.getPhoto().getValues());
		return entity;
	};
	
	
	private static final Converter<Employee,EmployeeDocument> 
	EMPLOYEE_TO_EMPLOYEE_DOCUMENT_CONVERTER = context -> {
		var employee = context.getSource();
		var document = new EmployeeDocument();
		document.setIdentity(employee.getTcKimlikNo().getValue());
		FullName fullName = employee.getFullName();
		document.setFirstName(fullName.getFirstName());
		document.setLastName(fullName.getLastName());
		document.setIban(employee.getIban().getValue());
		document.setSalary(employee.getSalary().getValue());
		document.setCurrency(employee.getSalary().getCurrency());
		document.setDepartment(employee.getDepartment());
		document.setJobStyle(employee.getJobStyle());
		document.setBirthYear(employee.getBirthYear().getValue());
		document.setPhoto(employee.getPhoto().getBase64Values());
		return document;
	};
	
	@Bean
	ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		modelMapper.addConverter(HIRE_EMPLOYEE_REQUEST_TO_EMPLOYEE_CONVERTER, HireEmployeeRequest.class, Employee.class);
		modelMapper.addConverter(EMPLOYEE_TO_EMPLOYEE_RESPONSE_CONVERTER, Employee.class, EmployeeResponse.class);
		modelMapper.addConverter(EMPLOYEE_TO_EMPLOYEE_ENTITY_CONVERTER, Employee.class, EmployeeEntity.class);
		modelMapper.addConverter(EMPLOYEE_ENTITY_TO_EMPLOYEE_CONVERTER, EmployeeEntity.class, Employee.class);
		modelMapper.addConverter(EMPLOYEE_TO_EMPLOYEE_DOCUMENT_CONVERTER, Employee.class, EmployeeDocument.class);
		modelMapper.addConverter(EMPLOYEE_DOCUMENT_TO_EMPLOYEE_CONVERTER, EmployeeDocument.class, Employee.class);
		return modelMapper;
	}
}
