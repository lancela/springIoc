package com.example.dao;

import com.example.entity.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class FollowupDao {

    private final JdbcTemplate jdbcTemplate;

    public FollowupDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int add(Long customerId, String content) {
        String sql = """
                INSERT INTO customer_followup
                (customer_id, content)
                VALUES (?, ?)
                """;

        return jdbcTemplate.update(sql, customerId, content);
    }

}