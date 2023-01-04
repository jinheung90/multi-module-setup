package kr.co.stylebot.collie.product;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProductDAO {

    List<ProductDTO> findAll();




}
