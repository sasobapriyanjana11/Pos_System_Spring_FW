package lk.ijse.gdse68.POS_System_REST_API.service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.POS_System_REST_API.customObj.ItemResponse;
import lk.ijse.gdse68.POS_System_REST_API.dto.impl.ItemDTO;
import lk.ijse.gdse68.POS_System_REST_API.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Override
    public void saveItem(ItemDTO itemDTO) {

    }

    @Override
    public List<ItemDTO> getAllItems() {
        return null;
    }

    @Override
    public ItemResponse getSelectedItem(String id) {
        return null;
    }

    @Override
    public void updateItem(String id, ItemDTO itemDTO) {

    }

    @Override
    public void deleteItem(String id) {

    }
}
