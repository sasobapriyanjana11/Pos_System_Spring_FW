package lk.ijse.gdse68.POS_System_REST_API.customObj;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemErrorResponse implements Serializable,ItemResponse {
    private int errorCode;
    private String errorMessage;
}
