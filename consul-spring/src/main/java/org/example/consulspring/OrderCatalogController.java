package org.example.consulspring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderCatalogController {

    private final RestTemplate restTemplate;

    public OrderCatalogController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping()
    public List<OrderCatalog> orderCatalogs() {
        Catalog[] catalogs = restTemplate.getForObject("http://cat-service/api/catalogs", Catalog[].class);
        return List.of(new OrderCatalog(1, catalogs));
    }


    public record Catalog(int id, String body) {
    }

    public record OrderCatalog(int id, Catalog[] catalogs) {
    }
}
