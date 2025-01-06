package com.perrypicker.table_crud_api.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.perrypicker.table_crud_api.entity.Item;
import com.perrypicker.table_crud_api.repository.IItemRepository;

import jakarta.transaction.Transactional;

@Service
public class ItemService {

  private IItemRepository itemRepository;

  // @Autowired
  public ItemService(IItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }

  // Obtener todos los artículos
  public List<Item> getAllItems() {
    return itemRepository.findAll();
  }

  // Obtener artículo por ID
  public Item getItemById(long theId) {
    return itemRepository.findById(theId).orElse(null);
  }

  // Guardar producto
  @Transactional
  public void saveItem(Item theItem) {
    itemRepository.save(theItem);
  }

  // Eliminar producto
  @Transactional
  public void deleteItem(long theId) {
    itemRepository.deleteById(theId);
  }

}
