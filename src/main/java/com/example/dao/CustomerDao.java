package com.example.dao;

import com.example.entity.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;


// 负责数据库访问
@Repository
public class CustomerDao {
    //    执行 SQL
    private final JdbcTemplate jdbcTemplate;

    public CustomerDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 查询全部客户
    public List<Customer> findAll() {

        String sql = "SELECT * FROM customer";
        // 将数据库每一行转换为 Java 对象 负责数据库结果映射
        RowMapper<Customer> rowMapper = new RowMapper<Customer>() {
            @Override
            public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
                // rowNum 表示当前处理的是第几行，从 0 开始。
                Customer customer = new Customer();

                customer.setId(rs.getLong("id"));
                customer.setName(rs.getString("name"));
                customer.setPhone(rs.getString("phone"));
                customer.setEmail(rs.getString("email"));
                customer.setCompany(rs.getString("company"));
                customer.setAddress(rs.getString("address"));
                customer.setStatus(rs.getInt("status"));

                return customer;
            }
        };
        // 查询多条数据并返回 List 查询多条记录
        return jdbcTemplate.query(sql, rowMapper);
    }

    // 新增客户
    public int add(Customer customer) {
        String sql = """
                INSERT INTO customer
                (name, phone, email, company, address, status)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        return jdbcTemplate.update(
                sql,
                customer.getName(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getCompany(),
                customer.getAddress(),
                customer.getStatus()
        );
    }

    // 修改客户
    public int update(Customer customer) {
        String sql = """
                UPDATE customer
                SET name = ?,
                    phone = ?,
                    email = ?,
                    company = ?,
                    address = ?,
                    status = ?
                WHERE id = ?
                """;

        return jdbcTemplate.update(
                sql,
                customer.getName(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getCompany(),
                customer.getAddress(),
                customer.getStatus(),
                customer.getId()
        );
    }

    // 删除客户
    public int deleteById(Long id) {
        String sql = "DELETE FROM customer WHERE id = ?";

        return jdbcTemplate.update(sql, id);
    }
    // 查询单个客户
    public Customer findById(Long id) {
        String sql = "SELECT * FROM customer WHERE id = ?";

        return jdbcTemplate.queryForObject(
                sql,
                (rs, rowNum) -> {
                    Customer customer = new Customer();

                    customer.setId(rs.getLong("id"));
                    customer.setName(rs.getString("name"));
                    customer.setPhone(rs.getString("phone"));
                    customer.setEmail(rs.getString("email"));
                    customer.setCompany(rs.getString("company"));
                    customer.setAddress(rs.getString("address"));
                    customer.setStatus(rs.getInt("status"));

                    return customer;
                },
                id
        );
    }


    //新增客户后，拿到 MySQL 自动生成的主键 ID。
    public Long addAndReturnId(Customer customer) {
        String sql = """
                INSERT INTO customer
                (name, phone, email, company, address, status)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    sql,
                    Statement.RETURN_GENERATED_KEYS
            );

            ps.setString(1, customer.getName());
            ps.setString(2, customer.getPhone());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getCompany());
            ps.setString(5, customer.getAddress());
            ps.setInt(6, customer.getStatus());

            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }
}