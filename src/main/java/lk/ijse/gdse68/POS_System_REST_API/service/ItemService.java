package lk.ijse.gdse68.POS_System_REST_API.service;

import lk.ijse.gdse68.POS_System_REST_API.customObj.CustomerResponse;
import lk.ijse.gdse68.POS_System_REST_API.customObj.ItemResponse;
import lk.ijse.gdse68.POS_System_REST_API.dto.impl.CustomerDTO;
import lk.ijse.gdse68.POS_System_REST_API.dto.impl.ItemDTO;

import java.util.List;

public interface ItemService {
    void saveItem(ItemDTO itemDTO);

    List<ItemDTO> getAllItems();

    ItemResponse getSelectedItem(String id);

    void updateItem(String id, ItemDTO itemDTO);

    void deleteItem(String id);
}
