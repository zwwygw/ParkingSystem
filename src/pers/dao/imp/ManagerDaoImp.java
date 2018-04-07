/**
* <p>Title: ManagerDaoImp.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2018<／p>
* <p>Company: CSU<／p>
* @author ZWW
* @date 2018年3月30日
* @version 1.0
*/
package dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import dao.ManagerDao;
import data.ConnOra;

/**
 * @ClassName ManagerDaoImp
 * @Description 对ManagerDao接口的实现
 * @author ZWW
 * @date   2018年3月30日下午10:21:06
 */
public class ManagerDaoImp implements ManagerDao {

	/* (non-Javadoc)
	 * @see dao.ManagerDao#check(java.lang.String, java.lang.String)
	 */
	@Override
	public HashMap<String, Object> check(String tf, String pf) {
		boolean flag = false;
		System.out.println(pf);
		String sql = "select * from t_manager where id=?";
		// Manager manager = null;
		String id = null;
		String pwd = null;
		int    power = 0;
		HashMap<String, Object> hMap = new HashMap<String, Object>();
		try {
			Connection conn = ConnOra.connOracle();
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, tf);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				id = rs.getString("id");
				pwd = rs.getString("pwd");
				power = rs.getInt("power");
			}
			psmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (tf.equals(id) && pf.equals(pwd)) {
			flag = true;
		}
		System.out.println(flag);
		hMap.put("flag", flag);
		hMap.put("power",power);
		return hMap;
	}

	/* (non-Javadoc)
	 * @see dao.ManagerDao#judgeP(java.lang.String)
	 */
	@Override
	public int judgeP(String tf) {
		int power = 0;
		String sql = "select * from t_manager where id=?";
		try {
			Connection conn = ConnOra.connOracle();
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, tf);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				power = rs.getInt("power");
			}
			psmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return power;
	}
 
}
