package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.OrderDetailsResponse;
import com.driver.service.FoodService;
import com.driver.shared.dto.FoodDto;
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
@RequestMapping("/foods")
public class FoodController {

	@Autowired
	FoodService foodService;

	@GetMapping(path="/{id}")
	public FoodDetailsResponse getFood(@PathVariable String id) throws Exception{

		try {
			FoodDto foodDto = foodService.getFoodById(id);

			FoodDetailsResponse foodDetailsResponse = new FoodDetailsResponse();
			foodDetailsResponse.setFoodCategory(foodDto.getFoodCategory());
			foodDetailsResponse.setFoodName(foodDto.getFoodName());
			foodDetailsResponse.setFoodId(foodDto.getFoodId());
			foodDetailsResponse.setFoodPrice(foodDto.getFoodPrice());
			return foodDetailsResponse;
		}
		catch(Exception e)
		{
			return new FoodDetailsResponse();
		}
	}

	@PostMapping("/create")
	public FoodDetailsResponse createFood(@RequestBody FoodDetailsRequestModel foodDetails) {

		FoodDto foodDtorequest = new FoodDto();
		foodDtorequest.setFoodPrice(foodDetails.getFoodPrice());
		foodDtorequest.setFoodName(foodDetails.getFoodName());
		foodDtorequest.setFoodCategory(foodDetails.getFoodCategory());
		foodDtorequest.setFoodId(foodDtorequest.getFoodId());
		FoodDto foodDto = foodService.createFood(foodDtorequest);
		FoodDetailsResponse foodDetailsResponse = new FoodDetailsResponse();
		foodDetailsResponse.setFoodCategory(foodDto.getFoodCategory());
		foodDetailsResponse.setFoodName(foodDto.getFoodName());
		foodDetailsResponse.setFoodId(foodDto.getFoodId());
		foodDetailsResponse.setFoodPrice(foodDto.getFoodPrice());
		return foodDetailsResponse;
	}

	@PutMapping(path="/{id}")
	public FoodDetailsResponse updateFood(@PathVariable String id, @RequestBody FoodDetailsRequestModel foodDetails) throws Exception{
		FoodDto foodDtorequest = new FoodDto();
		foodDtorequest.setFoodPrice(foodDetails.getFoodPrice());
		foodDtorequest.setFoodName(foodDetails.getFoodName());
		foodDtorequest.setFoodCategory(foodDetails.getFoodCategory());
		foodDtorequest.setFoodId(foodDtorequest.getFoodId());
		FoodDto foodDto = foodService.updateFoodDetails(id , foodDtorequest);
		FoodDetailsResponse foodDetailsResponse = new FoodDetailsResponse();
		foodDetailsResponse.setFoodCategory(foodDto.getFoodCategory());
		foodDetailsResponse.setFoodName(foodDto.getFoodName());
		foodDetailsResponse.setFoodId(foodDto.getFoodId());
		foodDetailsResponse.setFoodPrice(foodDto.getFoodPrice());
		return foodDetailsResponse;
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteFood(@PathVariable String id) throws Exception{
		foodService.deleteFoodItem(id);
		OperationStatusModel operationStatusModel = new OperationStatusModel();
		operationStatusModel.setOperationName("DELETE");
		operationStatusModel.setOperationResult("SUCCESS");

		return operationStatusModel;
	}
	
	@GetMapping()
	public List<FoodDetailsResponse> getFoods() {
		List<FoodDetailsResponse> foodDetailsResponses = new ArrayList<>();
		List<FoodDto> foodDtoList = foodService.getFoods();
		for(FoodDto foodDto : foodDtoList)
		{
			FoodDetailsResponse foodDetailsResponse = new FoodDetailsResponse();
			foodDetailsResponse.setFoodCategory(foodDto.getFoodCategory());
			foodDetailsResponse.setFoodName(foodDto.getFoodName());
			foodDetailsResponse.setFoodId(foodDto.getFoodId());
			foodDetailsResponse.setFoodPrice(foodDto.getFoodPrice());
			foodDetailsResponses.add(foodDetailsResponse);
		}
		return foodDetailsResponses;
	}
}
