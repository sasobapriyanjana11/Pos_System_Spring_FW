package lk.ijse.gdse68.POS_System_REST_API.dto.impl;

import lk.ijse.gdse68.POS_System_REST_API.customObj.CustomerResponse;
import lk.ijse.gdse68.POS_System_REST_API.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO implements SuperDTO, CustomerResponse {
    private String id;
    private String name;
    private String address;
    private double salary;
}
