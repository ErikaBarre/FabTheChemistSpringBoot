package fab.the.chemist.springbootjpa.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PersonRowMapper implements RowMapper<fab.the.chemist.springbootjpa.bean.Person>{

	@Override
	public fab.the.chemist.springbootjpa.bean.Person mapRow(ResultSet rs, int rowNum) throws SQLException {
		fab.the.chemist.springbootjpa.bean.Person person = new fab.the.chemist.springbootjpa.bean.Person();
		person.setId(rs.getInt("PE_ID"));
		person.setName(rs.getString("PE_NAME"));
		person.setLocation(rs.getString("PE_LOCATION"));
		person.setBirthDate(rs.getDate("PE_BIRTH_DATE"));
		return person;
	}

		
}
