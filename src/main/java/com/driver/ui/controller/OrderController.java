package com.driver.ui.controller;

import java.util.List;
import java.util.UUID;

import com.driver.model.request.OrderDetailsRequestModel;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.OrderDetailsResponse;
import com.driver.service.OrderService;
import com.driver.shared.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	OrderService orderService;
	@GetMapping(path="/{id}")
	public OrderDetailsResponse getOrder(@PathVariable String id) throws Exception{

		try {
			OrderDto orderDto = orderService.getOrderById(id);

			OrderDetailsResponse orderDetailsResponse = new OrderDetailsResponse();
			orderDetailsResponse.setOrderId(orderDto.getOrderId());
			orderDetailsResponse.setCost(orderDto.getCost());
			orderDetailsResponse.setItems(orderDto.getItems());
			orderDetailsResponse.setUserId(orderDto.getUserId());
			orderDetailsResponse.setStatus(orderDto.isStatus());
			return orderDetailsResponse;
		}
		catch(Exception e)
		{
			return new OrderDetailsResponse();
		}
	}
	
	@PostMapping()
	public OrderDetailsResponse createOrder(@RequestBody OrderDetailsRequestModel order) {

		OrderDto orderDto = new OrderDto();
		orderDto.setCost(order.getCost());
		orderDto.setUserId(order.getUserId());
		orderDto.setItems(order.getItems());
		orderDto.setOrderId(String.valueOf(UUID.randomUUID()));
		orderDto.setStatus(true);
		OrderDto orderdto =orderService.createOrder(orderDto);
		OrderDetailsResponse orderDetailsResponse = new OrderDetailsResponse();
		orderDetailsResponse.setOrderId(orderdto.getOrderId());
		orderDetailsResponse.setCost(orderdto.getCost());
		orderDetailsResponse.setItems(orderdto.getItems());
		orderDetailsResponse.setUserId(orderdto.getUserId());
		orderDetailsResponse.setStatus(orderdto.isStatus());
		return orderDetailsResponse;
	}
		
	@PutMapping(path="/{id}")
	public OrderDetailsResponse updateOrder(@PathVariable String id, @RequestBody OrderDetailsRequestModel order) throws Exception{
		OrderDto orderDto = new OrderDto();
		orderDto.setCost(order.getCost());
		orderDto.setUserId(order.getUserId());
		orderDto.setItems(order.getItems());
		OrderDto orderdto = orderService.updateOrderDetails(id , orderDto);
		OrderDetailsResponse orderDetailsResponse = new OrderDetailsResponse();
		orderDetailsResponse.setOrderId(orderdto.getOrderId());
		orderDetailsResponse.setCost(orderdto.getCost());
		orderDetailsResponse.setItems(orderdto.getItems());
		orderDetailsResponse.setUserId(orderdto.getUserId());
		orderDetailsResponse.setStatus(orderdto.isStatus());
		return orderDetailsResponse;
	}
	
	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteOrder(@PathVariable String id) throws Exception {
		orderService.deleteOrder(id);
		OperationStatusModel operationStatusModel = new OperationStatusModel();
		operationStatusModel.setOperationResult("SUCCESS");
		operationStatusModel.setOperationName("DELETE");
		return operationStatusModel;
	}
	
	@GetMapping()
	public List<OrderDetailsResponse> getOrders() {
		
		return null;
	}
}
