package com.perrypicker.table_crud_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "item")
public class Item {

  @Column(name = "item_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private long id;

  @Column(name = "item_detail")
  private String detail;

  @Column(name = "item_price")
  private float price;

  @Column(name = "item_stock")
  private int stock;

  public Item() {
  }

  public Item(String detail, float price, int stock) {
    this.detail = detail;
    this.price = price;
    this.stock = stock;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  @Override
  public String toString() {
    return "Item [id=" + id + ", detail=" + detail + ", price=" + price + ", stock=" + stock + "]";
  }

}
