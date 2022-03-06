package ma.octo.aop.repository.impl;

import ma.octo.aop.entity.Language;
import ma.octo.aop.repository.LanguageRepository;
import ma.octo.aop.util.CustumRowMapper;
import org.apache.commons.logging.Log;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Component
@Primary
public class LanguageJdbcRepositoryImpl implements LanguageRepository {

    final DataSource dataSource;

    private JdbcTemplate jdbctemplate;


    public LanguageJdbcRepositoryImpl(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
        this.jdbctemplate =new JdbcTemplate(dataSource);
        jdbctemplate.execute("create table Language ( id varchar(255) not null, name varchar(255) not null, author varchar(255) not null, fileExtension varchar(255) not null, primary key(id));");
        jdbctemplate.execute("insert into Language values('java', 'java', 'James Gosling', 'java');" +
            "insert into Language values('cpp', 'C++', 'Bjarne Stroustrup', 'cpp');" +
            "insert into Language values('csharp', 'C#', 'Andres Hejlsberg', 'cs');" +
            "insert into Language values('perl', 'Perl', 'Larry Wall', 'pl');" +
            "insert into Language values('haskel', 'Haskell', 'Simon Peyton', 'hs');" +
            "insert into Language values('lua', 'Lua', 'Luiz Henrique', 'lua');" +
            "insert into Language values('python', '4Python', 'Guido van Rossum', 'py');");
    }

    @Override
    public Optional<Language> findByExtension(String extension) {
        var query = "SELECT * FROM LANGUAGE WHERE FILEEXTENSION='"+ extension+ "'";
        Language language = null;
        try {
            language = jdbctemplate.queryForObject(query, new CustumRowMapper());
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return Optional.ofNullable(language);
    }

    @Override
    public Optional<Language> findById(String id) {
        var query = "SELECT * FROM LANGUAGE WHERE id='"+ id+ "'";
        Language language = null;
        try {
            language = jdbctemplate.queryForObject(query, new CustumRowMapper());
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return Optional.ofNullable(language);
    }

    @Override
    public List<Language> findAll() {
        var query = "SELECT * FROM language  ";
        List<Language> language ;
        try {
            language = jdbctemplate.query(query, new CustumRowMapper());
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}
