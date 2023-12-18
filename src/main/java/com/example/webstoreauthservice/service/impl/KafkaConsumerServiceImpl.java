package com.example.webstoreauthservice.service.impl;

import com.example.commoncode.exception.OrderProcessingException;
import com.example.webstoreauthservice.model.dto.OrderDto;
import com.example.webstoreauthservice.service.KafkaConsumerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

  private final OrderCompletionServiceImpl orderCompletionService;

  private final ObjectMapper objectMapper;

  @Override
  @KafkaListener(topics = "order-topic", groupId = "webstore-auth-service-group")
  public void consumeOrder(String orderMessage) {
    try {
      OrderDto orderDto = objectMapper.readValue(orderMessage, OrderDto.class);
      orderCompletionService.completeOrder(orderDto);
    } catch (IOException e) {
      throw new OrderProcessingException("Ошибка обработки заказа");
    }
  }
}