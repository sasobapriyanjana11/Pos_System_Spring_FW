package lk.ijse.gdse68.POS_System_REST_API.util;

import lk.ijse.gdse68.POS_System_REST_API.dto.impl.CustomerDTO;
import lk.ijse.gdse68.POS_System_REST_API.dto.impl.ItemDTO;
import lk.ijse.gdse68.POS_System_REST_API.entity.CustomerEntity;
import lk.ijse.gdse68.POS_System_REST_API.entity.ItemEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapping {
    @Autowired
    private ModelMapper modelMapper;

    public CustomerDTO convertCustomerEntityToCustomerDTO(CustomerEntity customerEntity) {
        return modelMapper.map(customerEntity, CustomerDTO.class);
    }

    public CustomerEntity convertCustomerDTOToCustomerEntity(CustomerDTO customerDTO) {
        return modelMapper.map(customerDTO, CustomerEntity.class);
    }

    public List<CustomerDTO> convertCustomerEntityListToCustomerDTOList(List<CustomerEntity> customerEntityList) {
        return modelMapper.map(customerEntityList, new TypeToken<List<CustomerDTO>>() {}.getType());
    }

    public ItemDTO convertItemEntityToItemDTO(ItemEntity itemEntity){
        return modelMapper.map(itemEntity,ItemDTO.class);
    }

    public ItemEntity convertItemDTOToItemEntity(ItemDTO itemDTO){
        return modelMapper.map(itemDTO,ItemEntity.class);
    }

    public List<ItemDTO> convertItemEntityListToItemDTOList(List<ItemEntity> itemEntityList) {
        return modelMapper.map(itemEntityList, new TypeToken<List<ItemDTO>>() {}.getType());
    }

}
