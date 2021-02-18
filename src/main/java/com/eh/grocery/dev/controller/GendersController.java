package com.eh.grocery.dev.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eh.grocery.dev.model.Gender;
import com.eh.grocery.dev.service.GenderService;
/**
 * @author   Md. Emran Hossain <emranhos1@gmail.com>
 * @version  1.0.00 EH
 * @since    1.0.00 EH
 */
@RestController
@RequestMapping("/gender")
public class GendersController {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(GendersController.class);

    @Autowired
    GenderService service;

    @GetMapping("/all")
    public Map<String, Object> findAll() {
        Map<String, Object> res = new HashMap<String, Object>();

        List<Gender> genders = service.selectAll();
        if(!CollectionUtils.isEmpty(genders)){
            res.put("data", genders);
        } else {
            res.put("data", null);
        }
        
        long total = service.countAll();
        String message = CollectionUtils.isEmpty(genders) ? "No Record Found" : "Records Found";
        res.put("data", genders);
        res.put("total", total);
        res.put("success", CollectionUtils.isEmpty(genders) ? false : true);
        res.put("message", message);
        return res;
    }

    @GetMapping("/{code}")
    public Map<String, Object> findByCode(@PathVariable("code") String code) {
        Map<String, Object> res = new HashMap<String, Object>();
        Gender responseData = null;
        
        Gender gender = service.selectByCode(code);
        if(!ObjectUtils.isEmpty(gender)){
            responseData = gender;
        } else {
            responseData = null;
        }

        String message = ObjectUtils.isEmpty(responseData) ? "No Record Found" : "One Record Found";
        res.put("data", responseData);
        res.put("total", ObjectUtils.isEmpty(responseData) ? 0 : 1);
        res.put("success", ObjectUtils.isEmpty(responseData) ? false : true);
        res.put("message", message);
        return res;
    }

    @PostMapping("/add")
    public Map<String, Object> create(@RequestBody Gender gender) {

        Map<String, Object> response = new HashMap<String, Object>();

        gender.setEntryDate(new Date());
        gender.setEntryUser("SELF");
        service.create(gender);

        response.put("data", gender);
        response.put("success", true);
        response.put("message", "One Record Insert");
        return response;
    }

    @PutMapping("/update")
    public Map<String, Object> update(@RequestBody Gender gender) {

        Map<String, Object> response = new HashMap<String, Object>();
        gender.setAmendDate(new Date());
        gender.setAmendUser("SELF");
        service.update(gender);

        response.put("data", gender);
        response.put("success", true);
        response.put("message", "One Record Updated");
        return response;
    }
}
