package pers.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pers.data.ConnOra;
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
		if(type.equals("all")) {
			String sql = "select count(*) count from t_parking";
			try {
				Connection conn = ConnOra.connOracle();
				PreparedStatement psmt = conn.prepareStatement(sql);
				ResultSet rs = psmt.executeQuery();
				while (rs.next()) {
					num = rs.getInt("count");
				}
				psmt.close();
				rs.close();
				conn.close();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}else {
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
		}
		return num;
	}

	/* (non-Javadoc)
	 * @see pers.dao.ParkingDao#getPNum()
	 */
	@Override
	public String getPId(String state) {
		String id = null;
		String sql = "select * from (select id from t_parking where state=? order by id) where rownum<2";
		// Manager manager = null;
		try {
			Connection conn = ConnOra.connOracle();
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, state);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				id = rs.getString("id");
			}
			psmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return id;
	}

	/* (non-Javadoc)
	 * @see pers.dao.ParkingDao#upTP()
	 */
	@Override
	public boolean upTPS(String state,String id) {
		boolean flag=false;
		String sql = "update t_parking set state=? where id=?";
		// Manager manager = null;
		try {
			Connection conn = ConnOra.connOracle();
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, state);
			psmt.setString(2, id);
			int rs = psmt.executeUpdate();
			if(rs==1) {
				flag = true;
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return flag;
	}
}
