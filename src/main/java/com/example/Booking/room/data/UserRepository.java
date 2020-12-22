package com.example.Booking.room.data;

import com.example.Booking.room.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {

    private JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> findAll() {
        String query = "SELECT * FROM user";
        List<User> users =
                jdbcTemplate.query(query, new UserMapper());
        return users;
    }

    public User findByUsername(String  username) {
        String query = "SELECT * FROM user WHERE username = " + "'"+username+"'";
        User user =
                jdbcTemplate.queryForObject(query, new UserMapper());
        return user;
    }

    public void save(User user) {
        String query = "INSERT INTO user (firstname,surname,username,passW) VALUES (?,?,?,?);";
        Object[] data = new Object[]
                { user.getFirstname(), user.getSurname(), user.getUsername(), user.getPassW() };
        jdbcTemplate.update(query, data);
    }

    public void deleteByUsername(String username) {
        String query = "DELETE FROM user WHERE username = " + "'"+username+"'";
        jdbcTemplate.update(query);
    }


    class UserMapper implements RowMapper<User> {


        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            String firstname = resultSet.getString("firstname");
            String surname = resultSet.getString("surname");
            String username = resultSet.getString("username");
            String passW= resultSet.getString("passW");
            User user = new User(firstname,surname,username,passW);
            return user;
        }
    }

}
