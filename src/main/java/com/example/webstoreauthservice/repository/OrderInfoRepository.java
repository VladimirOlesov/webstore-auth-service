package com.example.webstoreauthservice.repository;

import com.example.webstoreauthservice.model.entity.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderInfoRepository extends JpaRepository<OrderInfo, Long> {

}