package com.example.webstoreauthservice.service.impl;

import com.example.webstoreauthservice.model.dto.OrderDto;
import com.example.webstoreauthservice.model.entity.Book;
import com.example.webstoreauthservice.model.entity.OrderInfo;
import com.example.webstoreauthservice.repository.OrderInfoRepository;
import com.example.webstoreauthservice.service.OrderCompletionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderCompletionServiceImpl implements OrderCompletionService {

  private final OrderInfoRepository orderInfoRepository;

  @Override
  @Transactional
  public void completeOrder(OrderDto orderDto) {
    OrderInfo orderInfo = OrderInfo.builder()
        .userUuid(orderDto.userUuid())
        .build();

    for (Book bookDto : orderDto.books()) {
      Book book = Book.builder()
          .id(bookDto.getId())
          .title(bookDto.getTitle())
          .build();

      orderInfo.getBooks().add(book);
    }
    orderInfoRepository.save(orderInfo);
  }
}