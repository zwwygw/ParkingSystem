/**
* <p>Title: TemporaryDaoImp.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2018年4月4日<／p>
* <p>Company: CSU<／p>
* @author ZWW
* @date 2018年4月4日
* @version 1.0
*/
package pers.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pers.dao.TemporaryFeeDao;
import pers.data.ConnOra;

/**
 * @ClassName TemporaryDaoImp
 * @Description 实现TemporaryDao接口
 * @author ZWW
 * @date   2018年4月4日下午3:37:40
 */
public class TemporaryDaoImp implements TemporaryFeeDao{

	/* (non-Javadoc)
	 * @see dao.TemporaryFeeDao#add(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean add(String pnum, String id) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Connection connection = ConnOra.connOracle();
		
		String sql = "insert into t_TempFee values(?,?,?,?,?,?,?);";
		// Manager manager = null;
		try {
			Connection conn = ConnOra.connOracle();
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, type);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				num = rs.getInt("count");
			}
			psmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return flag;
	}

	/* (non-Javadoc)
	 * @see dao.TemporaryFeeDao#comp(java.lang.String)
	 */
	@Override
	public boolean comp(String pnum) {
		// TODO Auto-generated method stub
		return false;
	}

}
