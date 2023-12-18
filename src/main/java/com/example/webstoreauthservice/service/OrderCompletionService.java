package com.example.webstoreauthservice.service;

import com.example.webstoreauthservice.model.dto.OrderDto;

public interface OrderCompletionService {

  void completeOrder(OrderDto orderDto);
}