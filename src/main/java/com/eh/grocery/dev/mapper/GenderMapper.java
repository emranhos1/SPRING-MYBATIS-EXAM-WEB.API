package com.eh.grocery.dev.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.eh.grocery.dev.model.Gender;
/**
 * @author   Md. Emran Hossain <emranhos1@gmail.com>
 * @version  1.0.00 EH
 * @since    1.0.00 EH
 */
@Mapper
public interface GenderMapper {

    Long countAll();
    Long pageCount(int size);
    
    List<Gender> selectAll();
    List<Gender> searchByOffset(Map<String, Object> param);

    Gender selectByCode(String code);

    void insert(Gender user);
    void update(Gender user);
}
