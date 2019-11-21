package com.yao.service;

import com.yao.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TagService {
    Tag saveTag(Tag type);

    Optional<Tag> getTag(Long id);

    Tag getTagByName(String name);

    Page<Tag> listTag(Pageable pageable);

    Tag updateTag(Long id, Tag type);
    List<Tag> listTag();

    List<Tag> listTag(String ids);

    void deleteTag(Long id);
}
