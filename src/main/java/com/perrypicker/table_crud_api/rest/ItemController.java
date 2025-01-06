package com.perrypicker.table_crud_api.rest;

import org.springframework.web.bind.annotation.RestController;

import com.perrypicker.table_crud_api.dto.ItemDTO;
import com.perrypicker.table_crud_api.entity.Item;
import com.perrypicker.table_crud_api.service.ItemService;

import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ItemController {

  private ItemService itemService;

  // @Autowired
  public ItemController(ItemService itemService) {
    this.itemService = itemService;
  }

  // obtener todos los items
  @GetMapping
  public ResponseEntity<List<?>> getAllItems() {
    List<Item> tempItems = itemService.getAllItems();
    return ResponseEntity.ok(tempItems);
    // return itemService.getAllItems();
  }

  // obtener item por id
  @GetMapping("/{id}")
  public ResponseEntity<?> getItemById(@PathVariable long id) {
    Item tempItem = itemService.getItemById(id);
    if (tempItem == null || tempItem.equals(null)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404. Item with id \"" + id + "\" not found. 😖");
    }
    return ResponseEntity.ok(tempItem);
  }

  // guardar nuevo item
  @PostMapping
  public ResponseEntity<Item> saveItem(@RequestBody ItemDTO itemDTO) {
    Item theItem = new Item();
    theItem.setId(itemDTO.getId());
    theItem.setDetail(itemDTO.getDetail());
    theItem.setPrice(itemDTO.getPrice());
    theItem.setStock(itemDTO.getStock());

    itemService.saveItem(theItem);
    return ResponseEntity.status(HttpStatus.CREATED).body(theItem);
  }

  // actualizar item
  @PutMapping("/{id}")
  public ResponseEntity<?> updateItem(@PathVariable long id, @RequestBody Item theItem) {
    Item tempItem = itemService.getItemById(id);
    if (tempItem == null || tempItem.equals(null)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404. The item was not found.");
    } else {
      theItem.setId(id);
      itemService.saveItem(theItem);
      return ResponseEntity.ok(theItem);
    }
  }

  // eliminar item
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteItem(@PathVariable long id) {
    Item tempItem = itemService.getItemById(id);
    if (tempItem == null || tempItem.equals(null)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("404. The item was not found.");
    } else {
      itemService.deleteItem(id);
      return ResponseEntity.ok(tempItem);
    }
  }

}
