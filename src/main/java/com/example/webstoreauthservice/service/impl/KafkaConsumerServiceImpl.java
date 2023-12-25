package com.example.webstoreauthservice.service.impl;

import com.example.webstoreauthservice.model.dto.OrderDto;
import com.example.webstoreauthservice.service.KafkaConsumerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Реализация сервиса {@link KafkaConsumerService} для работы с Kafka и обработки сообщений о
 * заказах.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

  private final OrderCompletionServiceImpl orderCompletionService;

  private final ObjectMapper objectMapper;

  /**
   * Метод, обрабатывающий сообщения о заказах из Kafka-топика.
   *
   * @param orderMessage строковое представление сериализованного json-объекта OrderDto, полученное
   *                     из Kafka-топика.
   */
  @Override
  @KafkaListener(topics = "order-topic", groupId = "webstore-auth-service-group")
  public void consumeOrder(String orderMessage) {
    try {
      OrderDto orderDto = objectMapper.readValue(orderMessage, OrderDto.class);
      orderCompletionService.completeOrder(orderDto);
    } catch (IOException e) {
      log.error("Ошибка обработки заказа: {}", e.getMessage(), e);
    }
  }
}