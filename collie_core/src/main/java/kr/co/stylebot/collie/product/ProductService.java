package kr.co.stylebot.collie.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductDAO productDAO;
    public List<ProductDTO> findAll() {

        return productDAO.findAll();
    }
}