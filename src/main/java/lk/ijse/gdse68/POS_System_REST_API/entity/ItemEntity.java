package lk.ijse.gdse68.POS_System_REST_API.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "item")
@Entity
public class ItemEntity implements SuperEntity {
    @Id
    @Pattern(regexp = "I\\d{3}", message = "Item code must follow the pattern IXXX (e.g., I001)")
    private String code;

    @Pattern(regexp = "[A-Za-z ]+", message = "Item name should only contain letters and spaces")
    private String name;
    private int qty;
    private BigDecimal price;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetailsEntity> orderDetails;
}

