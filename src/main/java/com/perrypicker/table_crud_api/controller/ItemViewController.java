package com.perrypicker.table_crud_api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/items")
public class ItemViewController {

  @GetMapping
  public String serveItemsTable() {
    return "items";
  }

}
