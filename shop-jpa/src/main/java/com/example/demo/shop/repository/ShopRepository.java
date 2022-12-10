package com.example.demo.shop.repository;


import com.example.demo.shop.domain.Shop;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ShopRepository extends JpaRepository<Shop, Long> {


}
