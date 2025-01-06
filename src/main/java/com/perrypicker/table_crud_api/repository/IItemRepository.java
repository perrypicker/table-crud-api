package com.perrypicker.table_crud_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.perrypicker.table_crud_api.entity.Item;

@Repository
public interface IItemRepository extends JpaRepository<Item, Long> {

}
