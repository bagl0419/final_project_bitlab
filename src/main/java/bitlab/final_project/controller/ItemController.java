package bitlab.final_project.controller;

import bitlab.final_project.dto.ItemCreate;
import bitlab.final_project.dto.ItemView;
import bitlab.final_project.entity.Item;
import bitlab.final_project.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("{id}")
    public ItemView getItem(@PathVariable Long id) {
        return itemService.getItemById(id);
    }

    @GetMapping
    public List<ItemView> getItems() {
        return itemService.getAllItems();
    }

    @PostMapping
    public Item createItem(@RequestBody ItemCreate itemCreate) {
        return itemService.createItem(itemCreate);
    }

    @PutMapping
    public Item editItem(@RequestBody Item item) {
        return itemService.editItem(item);
    }

    @DeleteMapping("{id}")
    public void deleteItem(@PathVariable Long id) {
        itemService.deleteById(id);
    }
}
