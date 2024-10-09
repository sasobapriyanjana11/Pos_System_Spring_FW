package lk.ijse.gdse68.POS_System_REST_API.service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.gdse68.POS_System_REST_API.customObj.ItemResponse;
import lk.ijse.gdse68.POS_System_REST_API.dao.Itemdao;
import lk.ijse.gdse68.POS_System_REST_API.dto.impl.ItemDTO;
import lk.ijse.gdse68.POS_System_REST_API.entity.ItemEntity;
import lk.ijse.gdse68.POS_System_REST_API.exception.DataPersistFailedException;
import lk.ijse.gdse68.POS_System_REST_API.exception.ItemNotFoundException;
import lk.ijse.gdse68.POS_System_REST_API.service.ItemService;
import lk.ijse.gdse68.POS_System_REST_API.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private Itemdao itemDAO;

    @Autowired
    private Mapping mapping;

    @Override
    public void saveItem(ItemDTO itemDTO) {
        ItemEntity save = itemDAO.save(mapping.convertItemDTOToItemEntity(itemDTO));
        if (save == null){
            throw new DataPersistFailedException("cannot save item");
        }
    }

    @Override
    public List<ItemDTO> getAllItems() {
        return mapping.convertItemEntityListToItemDTOList(itemDAO.findAll());
    }

    @Override
    public ItemResponse getItemById(String id) {
        if(itemDAO.existsById(id)){
            return mapping.convertItemEntityToItemDTO(itemDAO.getReferenceById(id));
        }else{
            throw new ItemNotFoundException("Item not found ");
        }
    }

    @Override
    public void updateItem(String id, ItemDTO itemDTO) {
        Optional<ItemEntity> tmp = itemDAO.findById(id);
        if (!tmp.isPresent()) {
            throw new ItemNotFoundException("item not found");
        }else {
            tmp.get().setName(itemDTO.getName());
            tmp.get().setPrice(itemDTO.getPrice());
            tmp.get().setQty(itemDTO.getQty());
        }
    }

    @Override
    public void deleteItem(String id) {
        Optional<ItemEntity>tmp=itemDAO.findById(id);
        if(!tmp.isPresent()){
            throw new ItemNotFoundException("Item not found");
        }else{
            itemDAO.deleteById(id);
        }

    }
}
