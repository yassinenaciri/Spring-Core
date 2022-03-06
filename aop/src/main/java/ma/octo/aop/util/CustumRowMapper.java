package ma.octo.aop.util;

import ma.octo.aop.entity.Language;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustumRowMapper implements RowMapper<Language> {

    @Override
    public Language mapRow(ResultSet rs, int rowNum) throws SQLException {
        Language language = new Language(rs.getString("ID"),rs.getString("NAME"),rs.getString("Author"),rs.getString("FILEEXTENSION"));
        return language;

    }
}
