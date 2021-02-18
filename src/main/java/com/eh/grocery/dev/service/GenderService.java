package com.eh.grocery.dev.service;

import java.util.List;
import java.util.Map;

import com.eh.grocery.dev.model.Gender;
/**
 * @author   Md. Emran Hossain <emranhos1@gmail.com>
 * @version  1.0.00 EH
 * @since    1.0.00 EH
 */
public interface GenderService {
    Long countAll();
    Long pageCount(int size);
    
    List<Gender> selectAll();
    List<Gender> searchByOffset(Map<String, Object> param);

    Gender selectByCode(String code);

    void create(Gender user);
    void update(Gender user);
}
