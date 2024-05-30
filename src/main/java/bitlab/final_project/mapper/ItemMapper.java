package bitlab.final_project.mapper;

import bitlab.final_project.dto.ItemCreate;
import bitlab.final_project.dto.ItemView;
import bitlab.final_project.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ItemMapper {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);
    ItemView toView(Item item);
    List<ItemView> toView(List<Item> items);
    Item toEntity(ItemCreate itemCreate);
}
