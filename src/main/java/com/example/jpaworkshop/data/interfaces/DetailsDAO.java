package com.example.jpaworkshop.data.interfaces;

import com.example.jpaworkshop.model.entity.Details;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface DetailsDAO extends GenericCRUD<Details, Integer>{
}
