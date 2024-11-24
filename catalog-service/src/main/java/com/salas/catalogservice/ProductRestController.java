package com.salas.catalogservice;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/catalogue/products")
public class ProductRestController implements RowMapper<ProductRestController.Product> {

    private final JdbcTemplate jdbcTemplate;

    public ProductRestController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    public List<Product> catalog() {
        return this.jdbcTemplate.query("select * from t_product", this);
    }

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Product(rs.getInt("id"), rs.getString("c_body"));
    }


    public record Product(int id, String body) {
    }
}
