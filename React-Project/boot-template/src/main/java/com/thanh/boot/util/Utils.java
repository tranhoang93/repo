package com.thanh.boot.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class Utils {

    public static synchronized void writeObjectNodeToResponse(ObjectNode node, HttpServletResponse response) throws IOException {
        response.addHeader("Content-Type", "application/json; charset=utf-8");
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8));
        ObjectMapper mapper = new ObjectMapper();
        writer.write(mapper.writeValueAsString(node));
        writer.flush();
        writer.close();
    }

    public static synchronized void writeToResponse(String content, HttpServletResponse response) throws IOException {
        response.addHeader("Content-Type", "application/json; charset=utf-8");
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8));
        writer.write(content);
        writer.flush();
        writer.close();
    }

    /**
     * Get default sort by id and DESC direction
     *
     * @param pageable
     * @return
     */
    public static synchronized Pageable getDefaultSortPageable(Pageable pageable) {
        Sort sort = pageable.getSort();
        boolean sorted = sort.isSorted();
        if (!sorted) {
            Sort defaultSort = Sort.by(Sort.Direction.DESC, "id");
            return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), defaultSort);
        }
        return pageable;
    }

    public static synchronized String getRealIp(HttpServletRequest request){
        String realIp = request.getHeader("X-Real-IP");
        if (realIp == null){
            realIp = request.getRemoteAddr();
        }
        return realIp;
    }
}
