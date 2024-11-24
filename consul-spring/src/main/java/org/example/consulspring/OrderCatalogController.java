package org.example.consulspring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderCatalogController implements RowMapper<OrderCatalogController.OrderItem> {

    private final RestTemplate restTemplate;

    private final JdbcTemplate jdbcTemplate;

    public OrderCatalogController(RestTemplate restTemplate, JdbcTemplate jdbcTemplate) {
        this.restTemplate = restTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping()
    public List<OrderItem> orderCatalogs() {
        Product[] product = restTemplate.getForObject("http://cat-service/api/catalogue/products", Product[].class);
        return List.of(new OrderItem(1, 2, product));
    }

    @Override
    public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        return null;
    }


    public record Product(int id, String body) {
    }

    public record OrderItem(int id, int c_amount, Product[] product) {
    }
}
