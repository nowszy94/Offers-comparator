package pl.endproject.offerscomparator.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {

    private Long id;
    private String name;
    private Double price;
    private String url;
    private String imageUrl;
    private Source source;

}
