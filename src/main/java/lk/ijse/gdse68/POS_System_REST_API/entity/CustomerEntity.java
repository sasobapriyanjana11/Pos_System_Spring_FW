package lk.ijse.gdse68.POS_System_REST_API.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "customer")
@Entity
public class CustomerEntity implements SuperEntity {
    @Id
    @Pattern(regexp = "C\\d{3}", message = "Customer ID must follow the pattern CXXX (e.g., C001)")
    private String id;

    @Pattern(regexp = "[A-Za-z ]+", message = "Name should only contain letters and spaces")
    private String name;

    private String address;
    private double salary;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderEntity> orders;
}

