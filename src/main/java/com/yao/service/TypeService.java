package com.yao.service;

import com.yao.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TypeService {
    Type saveType(Type type);
    Type getType(Long id);


    Type updateType(Long id, Type type);
    List<Type> listType();
    List<Type> findTop(Integer size);
    Page<Type> listType(Pageable pageable);

    Type getTypeByName(String name);
    void deleteType(Long id);


}
