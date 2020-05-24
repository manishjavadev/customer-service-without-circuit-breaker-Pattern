package com.manish.javadev.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.manish.javadev.model.BookingDetail;
import com.manish.javadev.model.Customer;
import com.manish.javadev.model.CustomerResponseDetail;
import com.manish.javadev.model.VehicleResponseDetails;

@RestController
@RequestMapping(value = "/api")
public class CustomerServiceController {

	@Autowired
	private RestTemplate restTemplate;
	private Map<Integer, Customer> customerMap;

	public CustomerServiceController() {
		customerMap = new HashMap<Integer, Customer>();
		Customer customer = new Customer(100, "Vaishali", 27);
		customerMap.put(new Integer(100), customer);
	}

	@RequestMapping(value = "/bookings/{userId}")
	List<CustomerResponseDetail> findBookingDetails(@PathVariable("userId") int userId) {

		// 1st Microservice call
		VehicleResponseDetails vehicleDetails = restTemplate
				.getForObject("http://vehicle-service/api/vehicles/" + userId, VehicleResponseDetails.class);

		return vehicleDetails.getVehicleList().stream().map(vehical -> {
			// 2nd Microservice call
			BookingDetail bookingDetail = restTemplate.getForObject(
					"http://rent-service/api/rent/" + userId + "/" + vehical.getVehicalId(), BookingDetail.class);

			return new CustomerResponseDetail(customerMap.get(new Integer(userId)), vehical, bookingDetail);
		}).collect(Collectors.toList());
	}

	@RequestMapping(value = "/ping")
	public String ping() {
		return "Configuration is working fine";
	}
}
