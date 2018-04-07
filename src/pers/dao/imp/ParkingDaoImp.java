package pers.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import data.ConnOra;
import pers.dao.ParkingDao;


/**
 * @ClassName ParkingDaoImp
 * @Description 对ParkingDao的实现
 * @author ZWW
 * @date   2018年3月30日下午10:49:27
 */
public class ParkingDaoImp implements ParkingDao{

	/* (non-Javadoc)
	 * @see dao.ParkingDao#parkingNum(java.lang.String)
	 */
	public int parkingNum(String type) {
		int num = 0;
		String sql = "select count(*) count from t_parking where type=?";
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
		return num;
	}

}
