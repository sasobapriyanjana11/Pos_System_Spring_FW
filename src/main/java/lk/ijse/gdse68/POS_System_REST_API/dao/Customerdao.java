package lk.ijse.gdse68.POS_System_REST_API.dao;

import lk.ijse.gdse68.POS_System_REST_API.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Customerdao extends JpaRepository<CustomerEntity,String> {

}
