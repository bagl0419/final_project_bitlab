package bitlab.final_project.service;

import bitlab.final_project.dto.ItemCreate;
import bitlab.final_project.dto.ItemEdit;
import bitlab.final_project.dto.ItemView;
import bitlab.final_project.entity.Item;
import bitlab.final_project.mapper.ItemMapper;
import bitlab.final_project.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public ItemView createItem(ItemCreate itemCreate) {
        Optional<Item> optionalItem = itemRepository.findByName(itemCreate.getName());
        if (optionalItem.isPresent()) {
            throw new RuntimeException("Этот товар уже существует!");
        }
        Item item = ItemMapper.INSTANCE.toEntity(itemCreate);
        Item savedItem = itemRepository.save(item);
        return ItemMapper.INSTANCE.toView(savedItem);
    }


    public ItemView editItem(ItemEdit itemEdit) {
        Optional<Item> optionalItem = itemRepository.findById(itemEdit.getId());

        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            ItemMapper.INSTANCE.updateItemFromDto(itemEdit, item);
            Item updatedItem = itemRepository.save(item);
            return ItemMapper.INSTANCE.toView(updatedItem);
        } else {
            throw new RuntimeException("Товар не найден, ID: " + itemEdit.getId());
        }
    }

    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }
}
