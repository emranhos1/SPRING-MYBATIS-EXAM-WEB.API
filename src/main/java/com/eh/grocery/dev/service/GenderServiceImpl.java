package com.eh.grocery.dev.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eh.grocery.dev.model.Gender;
import com.eh.grocery.dev.util.OffsetUtil;
import com.eh.grocery.dev.mapper.GenderMapper;
/**
 * @author   Md. Emran Hossain <emranhos1@gmail.com>
 * @version  1.0.00 EH
 * @since    1.0.00 EH
 */
@Service
@Transactional(readOnly=false)
public class GenderServiceImpl implements GenderService{

    @SuppressWarnings("unused")
    private static final Logger log = LoggerFactory.getLogger(GenderServiceImpl.class);

    @Autowired
    GenderMapper mapper;

    @Override
    public Long countAll() {
        return mapper.countAll();
    }

    @Override
    public Long pageCount(int size) {
        Long noOfRecords = mapper.countAll();
        Long pages = OffsetUtil.getPageCount(noOfRecords, size);
        return pages;
    }

    @Override
    public List<Gender> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public List<Gender> searchByOffset(Map<String, Object> param) {
        return mapper.searchByOffset(param);
    }

    @Override
    public Gender selectByCode(String code) {
        return mapper.selectByCode(code);
    }

    @Override
    public void create(Gender gender) {
        mapper.insert(gender);
    }

    @Override
    public void update(Gender gender) {
        mapper.update(gender);
    }
}
