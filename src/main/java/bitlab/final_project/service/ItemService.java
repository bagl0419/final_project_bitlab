package bitlab.final_project.service;

import bitlab.final_project.dto.ItemCreate;
import bitlab.final_project.dto.ItemView;
import bitlab.final_project.entity.Item;
import bitlab.final_project.mapper.ItemMapper;
import bitlab.final_project.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemView getItemById(Long id) {
        Item item = itemRepository.findById(id).orElse(null);
        return ItemMapper.INSTANCE.toView(item);
    }

    public List<ItemView> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return ItemMapper.INSTANCE.toView(items);
    }

    public Item createItem(ItemCreate itemCreate) {
        Item item = ItemMapper.INSTANCE.toEntity(itemCreate);
        return itemRepository.save(item);
    }


    public Item editItem(Item item) {
        return itemRepository.save(item);
    }

    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }
}
