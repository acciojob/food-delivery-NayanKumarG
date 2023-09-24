package com.driver.service.impl;

import com.driver.io.entity.FoodEntity;
import com.driver.io.repository.FoodRepository;
import com.driver.service.FoodService;
import com.driver.shared.dto.FoodDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FoodServiceImpl implements FoodService{

    @Autowired
    FoodRepository foodRepository;

    @Override
    public FoodDto createFood(FoodDto food) {
        FoodEntity foodEntity = new FoodEntity();
        foodEntity.setFoodCategory(food.getFoodCategory());
        foodEntity.setFoodName(food.getFoodName());
        foodEntity.setFoodPrice(food.getFoodPrice());
        foodEntity.setFoodId(food.getFoodId());
        FoodEntity foodEntity1 = foodRepository.save(foodEntity);
        FoodDto foodDto = new FoodDto();
        foodDto.setFoodCategory(foodEntity1.getFoodCategory());
        foodDto.setFoodName(foodEntity1.getFoodName());
        foodDto.setFoodId(foodEntity1.getFoodId());
        foodDto.setFoodPrice(foodEntity1.getFoodPrice());
        return foodDto;
    }

    @Override
    public FoodDto getFoodById(String foodId) throws Exception {
        FoodEntity foodEntity = foodRepository.findByFoodId(foodId);
        FoodDto foodDto = new FoodDto();
        foodDto.setFoodCategory(foodEntity.getFoodCategory());
        foodDto.setFoodName(foodEntity.getFoodName());
        foodDto.setFoodId(foodEntity.getFoodId());
        foodDto.setFoodPrice(foodEntity.getFoodPrice());
        return foodDto;
    }

    @Override
    public FoodDto updateFoodDetails(String foodId, FoodDto foodDetails) throws Exception {
        return null;
    }

    @Override
    public void deleteFoodItem(String id) throws Exception {

    }

    @Override
    public List<FoodDto> getFoods() {
        return null;
    }
}