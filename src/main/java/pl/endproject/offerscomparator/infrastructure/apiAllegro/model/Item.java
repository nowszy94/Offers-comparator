package pl.endproject.offerscomparator.infrastructure.apiAllegro.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {
    private String title;
    private Double price;
    private Double priceWithShipping;
    private String url;
    private String imageUrl;
    private String address;
    private String condition;
}
