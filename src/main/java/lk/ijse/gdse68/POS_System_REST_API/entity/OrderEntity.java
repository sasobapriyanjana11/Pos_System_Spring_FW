package lk.ijse.gdse68.POS_System_REST_API.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "orders")
@Entity
public class OrderEntity implements SuperEntity {
    @Id
    @Pattern(regexp = "O\\d{3}", message = "Order ID must follow the pattern OXXX (e.g., O001)")
    private String orderId;
    private LocalDate orderDate;

    @ManyToOne
    @JoinColumn(name = "custId", nullable = false)
    private CustomerEntity customer;

    private BigDecimal total;
    private BigDecimal discount;
    private BigDecimal subTotal;
    private BigDecimal cash;
    private BigDecimal balance;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderDetailsEntity> orderDetails;
}

