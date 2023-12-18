package com.example.webstoreauthservice.service;

public interface KafkaConsumerService {

  void consumeOrder(String orderMessage);
}