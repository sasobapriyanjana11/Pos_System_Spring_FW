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
@Table(name = "order_detail")
@Entity
public class OrderDetailsEntity implements SuperEntity {
    @Id
    @Pattern(regexp = "OD\\d{3,}", message = "Order Detail ID must follow the pattern ODXXX (e.g., OD001)")
    private String od_id;

    @ManyToOne
    @JoinColumn(name = "orderId", nullable = false)
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "itemCode", nullable = false)
    private ItemEntity item;

    private BigDecimal unitPrice;
    private int qty;


}


