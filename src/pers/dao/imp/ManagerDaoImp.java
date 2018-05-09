/**
* <p>Title: ManagerDaoImp.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2018<／p>
* <p>Company: CSU<／p>
* @author ZWW
* @date 2018年3月30日
* @version 1.0
*/
package pers.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import pers.dao.ManagerDao;
import pers.data.ConnOra;
import pers.table.Manager;

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
		String sql = "select power from t_manager where id=?";
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

	/* (non-Javadoc)
	 * @see pers.dao.ManagerDao#add(pers.table.Manager)
	 */
	@Override
	public boolean add(Manager manager) {
		boolean flag = false;
		String sql = "insert into t_manager values(?,?,?,?)";
		try {
			Connection connection = ConnOra.connOracle();
			PreparedStatement psmt = connection.prepareStatement(sql);
			psmt.setString(1, manager.getId());
			psmt.setString(2, manager.getPwd());
			psmt.setString(3, manager.getName());
			psmt.setInt(4, manager.getPower());
			int rs = psmt.executeUpdate();
			if(rs == 1) {
				flag = true;
			}
			psmt.close();
			connection.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}	
		return flag;
	}

	/* (non-Javadoc)
	 * @see pers.dao.ManagerDao#delManager(java.lang.String)
	 */
	@Override
	public boolean delManager(String id) {
		boolean flag = false;
		String sql = "delete from t_manager where id=?";
		try {
			Connection conn = ConnOra.connOracle();
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			if (psmt.executeUpdate() > 0) {
				flag = true;
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	/* (non-Javadoc)
	 * @see pers.dao.ManagerDao#findManager(java.lang.String)
	 */
	@Override
	public String findManager(String id) {
		String sql = "select name from t_manager where id=?";
		String name = null;
		try {
			Connection conn = ConnOra.connOracle();
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				name = rs.getString("name");
			}
			psmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;
	}

	/* (non-Javadoc)
	 * @see pers.dao.ManagerDao#upMP(java.lang.String)
	 */
	@Override
	public boolean upMP(String id,int power) {
		boolean flag = true;
		String sql = "update t_manager set power=? where id=?";
		try {
			Connection conn = ConnOra.connOracle();
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1, power);
			psmt.setString(2, id);
			int rs=psmt.executeUpdate();
			if(rs > 0) {
				flag = true;
			}
			psmt.close();
			conn.close();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}
	
}
