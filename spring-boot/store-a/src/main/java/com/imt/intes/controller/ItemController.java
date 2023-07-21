package com.imt.intes.controller;

import com.imt.intes.model.ItemEntity;
import com.imt.intes.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/service/item")
public class ItemController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity loadAllItem (@PathParam("number") int number, @PathParam("page") int page) {
        logger.info("Load all item (number: {}, page: {})", number, page);
        Page<ItemEntity> entities = itemService.findAllItemEntityByPage(PageRequest.of(page, number));
        if (entities.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/search/{nameFilter}")
    public ResponseEntity loadItemByName (@PathVariable String nameFilter, @PathParam("number") int number, @PathParam("page") int page) {
        logger.info("Load all item by name: {} (number: {}, page: {})", nameFilter, number, page);
        if (nameFilter != null) {
            if (nameFilter.isEmpty()) nameFilter = "*";
            nameFilter = nameFilter.replace("*", "%");
            nameFilter = nameFilter.replace(".", "?");
            Page<ItemEntity> entities = itemService.searchItemByNamePageable(nameFilter, PageRequest.of(page, number));
            if (entities.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(entities);
        }
        return ResponseEntity.badRequest().body("Name filter malformed");
    }

    @PostMapping
    public ResponseEntity saveNewItem (@RequestBody ItemEntity item) {
        logger.info("Save new item");
        try {
            return new ResponseEntity(itemService.createNewItem(item), HttpStatus.CREATED);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity updateItem (@RequestBody ItemEntity item) {
        logger.info("Update item");
        try {
            return ResponseEntity.ok(itemService.updateItem(item));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity deleteItem (@RequestBody ItemEntity item) {
        logger.info("Delete item");
        try {
            itemService.deleteItem(item);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteItem (@PathVariable long id) {
        logger.info("Delete item by id: {}", id);
        try {
            itemService.deleteItem(id);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
