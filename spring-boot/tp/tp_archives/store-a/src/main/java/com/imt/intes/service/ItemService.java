package com.imt.intes.service;

import com.imt.intes.model.ItemEntity;
import com.imt.intes.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<ItemEntity> findAllItemEntity () {
        List<ItemEntity> result = new ArrayList<>();
        itemRepository.findAll().forEach(result::add);
        return result;
    }

    public Page<ItemEntity> findAllItemEntityByPage(Pageable page) {
        return itemRepository.findAllByOrderByName(page);
    }
    public Optional<ItemEntity> findOneItem(long id) {
        return itemRepository.findById(id);
    }

    public Page<ItemEntity> searchItemByNamePageable(String nameFilter, Pageable page) {
        return itemRepository.findAllByNameLikeOrderByName(nameFilter, page);
    }

    public ItemEntity createNewItem (String name) {
        ItemEntity newItem = new ItemEntity();
        newItem.setName(name);
        return itemRepository.save(newItem);
    }

    public ItemEntity createNewItem (ItemEntity item) throws Exception {
        if (item.getName() == null || item.getName().isEmpty()) {
            throw new Exception("Your item must have a name");
        }
        if (itemRepository.findFirstByName(item.getName()).isPresent()) {
            throw new Exception("An item with the same name exists");
        }
        return itemRepository.save(item);
    }

    public ItemEntity updateItem(ItemEntity item) throws Exception {
        if (item.getId() == 0 || item.getName() == null || item.getName().isEmpty()) {
            throw new Exception("Your item is malformed");
        }
        Optional<ItemEntity> existingItemResult = itemRepository.findById(item.getId());
        if (existingItemResult.isPresent()) {
            ItemEntity existingItem = existingItemResult.get();
            existingItem.setName(item.getName());
            existingItem.setWeight(item.getWeight());
            existingItem.setPrice(item.getPrice());
            return itemRepository.save(existingItem);
        } else {
            throw new Exception("Your item does not exist");
        }
    }

    public void deleteItem(long id) {
        itemRepository.deleteById(id);
    }

    public void deleteItem(ItemEntity item) throws Exception {
        if (item.getId() == 0) throw new Exception("The id is missing from item");
        this.deleteItem(item.getId());
    }
}
