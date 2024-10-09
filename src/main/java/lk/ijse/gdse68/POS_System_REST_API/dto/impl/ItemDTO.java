package lk.ijse.gdse68.POS_System_REST_API.dto.impl;

import lk.ijse.gdse68.POS_System_REST_API.customObj.ItemResponse;
import lk.ijse.gdse68.POS_System_REST_API.dto.SuperDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO implements SuperDTO , ItemResponse {
    private String code;
    private String name;
    private int qty;
    private BigDecimal price;
}
