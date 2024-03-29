package com.yao.service;

import com.yao.NotFoundException;
import com.yao.dao.TypeRepository;
import com.yao.po.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TypeServiceImpl implements TypeService{
   @Autowired
    private TypeRepository typeRepository;
    @Transactional
    @Override
    public Type saveType(Type type) {
     return    typeRepository.save(type);
    }

    @Transactional
    @Override
    public Type getType(Long id) {
       Optional<Type> t= typeRepository.findById(id);
       return  t.get();
    }


    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
       Optional<Type> type1=typeRepository.findById(id);
        if(type1==null){
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(type,type1);
        return typeRepository.save(type);
    }

 @Override
 public List<Type> listType() {
  return typeRepository.findAll();
 }

    @Override
    public List<Type> findTop(Integer size) {
         Sort sort=new Sort(Sort.Direction.DESC,"blogs.size");
          Pageable pageable=PageRequest.of(0,size,sort);
        return typeRepository.findTop(pageable);
    }

    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Type getTypeByName(String name) {
    return typeRepository.findByName(name);
    }

    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);

    }
}
