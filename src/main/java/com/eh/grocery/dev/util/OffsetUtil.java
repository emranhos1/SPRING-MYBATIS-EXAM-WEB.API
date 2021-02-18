package com.eh.grocery.dev.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author   Md. Emran Hossain <emranhos1@gmail.com>
 * @version  1.0.00 EH
 * @since    1.0.00 EH
 */
public class OffsetUtil {
    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(OffsetUtil.class);

    public static final String  INI_OFFSET_NAME = "iniOffset";
    public static final String  END_OFFSET_NAME = "endOffset";
    public static final Integer MAX_OFFSET_SIZE = 100;

    private static int from(Integer index) {
        return (index != null && index >= 1) ? index : 1;
    }

    private static int to(Integer index, Integer limit) {
        return from(index) + limitify(limit) - 1;
    }

    private static int limitify(Integer limit) {
        return (limit != null && limit <= MAX_OFFSET_SIZE) ? limit : MAX_OFFSET_SIZE;
    }

    private static Map<String, Object> from(Map<String, Object> where, Integer index) {
        where.put(INI_OFFSET_NAME, from(index));
        return where;
    }

    private static Map<String, Object> to(Map<String, Object> where, Integer index, Integer limit) {
        where.put(END_OFFSET_NAME, to(index, limit));
        return where;
    }

    public static Map<String, Object> offsetify(Map<String, Object> where, Integer index, Integer limit) {
        to(where, index, limit);
        from(where, index);
        return where;
    }

    public static Map<String, Object> offsetify(Integer index, Integer limit) {
        Map<String, Object> where = new HashMap<>();
        return offsetify(where, index, limit);
    }

    public static Map<String, Object> offsetify(Map<String, Object> where) {
        return offsetify(where, 1, MAX_OFFSET_SIZE);
    }

    public static Map<String, Object> offsetify() {
        Map<String, Object> where = new HashMap<>();
        return offsetify(where, 1, MAX_OFFSET_SIZE);
    }

    public static Map<String, Object> offsetifyByPageAndSize(int page, int size) {
        page = page - 1;
        page = (page < 0) ? 0 : page;
        size = (size <= 0) ? 100 : ((size >= 100) ? 100 : size);

        int iniOffset = (page * size) + 1;
        int endOffset = (page * size) + size;

        Map<String,Object> param = new HashMap<>();
        param.put("iniOffset", iniOffset);
        param.put("endOffset", endOffset);
        return param;
    }

    public static Long getPageCount(Long noOfRecords, int pageSize) {
        Long pages = noOfRecords / pageSize;
        if (noOfRecords % pageSize > 0) {
            pages++;
        }
        return pages;
    }
}
