package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Customer;
import com.example.model.Plane;
import com.example.repository.CustomerRepository;

@RestController
@RequestMapping("api/customer")
public class CustomerController {
	@Autowired
	private CustomerRepository customerRepository;
	@GetMapping("/Cau3")
	public List<Customer> getAllPlant() {
		return customerRepository.Cau3();
	}
	@GetMapping("/Cau8")
	public int SumLuong() {
		return customerRepository.Cau8();
	}
	@GetMapping("/Cau9")
	public List<Customer> Cau9() {
		return customerRepository.Cau9();
	}
	
}
