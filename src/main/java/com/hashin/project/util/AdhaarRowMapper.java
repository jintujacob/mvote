package com.hashin.project.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.hashin.project.bean.AdhaarUserBean;

public class AdhaarRowMapper implements RowMapper<AdhaarUserBean>
{

    @Override
    public AdhaarUserBean mapRow(ResultSet resultSet, int rowNum) throws SQLException
    {
	AdhaarExtractor adhaarExtractor = new AdhaarExtractor();
	return adhaarExtractor.extractData(resultSet);
    }

}
