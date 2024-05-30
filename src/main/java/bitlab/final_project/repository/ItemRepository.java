package bitlab.final_project.repository;

import bitlab.final_project.dto.ItemView;
import bitlab.final_project.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
