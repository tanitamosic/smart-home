package securityproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import securityproject.model.home.House;
import securityproject.model.user.StandardUser;

import java.util.List;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {

    House findHouseByIdAndIsActive(Long id, Boolean isActive);
    List<House> findHousesByIsActive(Boolean isActive);
//    List<House> findHousesByRenterAndIsActive(StandardUser renter, Boolean isActive);
//    List<House> findHousesByOwnerAndIsActive(StandardUser owner, Boolean isActive);
}
