package pers.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import pers.data.ConnOra;
import pers.table.Parking;
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
	public int parkingNullNum(String type) {
		int num = 0;
		if(type.equals("all")) {
			String sql = "select count(*) from t_parking where state='空'";
			try {
				Connection conn = ConnOra.connOracle();
				PreparedStatement psmt = conn.prepareStatement(sql);
				ResultSet rs = psmt.executeQuery();
				while (rs.next()) {
					num = rs.getInt(1);
				}
				psmt.close();
				rs.close();
				conn.close();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}else if(type.equals("临时")){
			String sql = "select count(*) from t_parking where type='临时' and state='空'";
			// Manager manager = null;
			//System.out.println(num);
			PreparedStatement psmt =null;
			ResultSet rs = null;
			try {
				Connection conn = ConnOra.connOracle();
				psmt = conn.prepareStatement(sql);
			//	psmt.setString(1, type);
			    rs = psmt.executeQuery();
				while (rs.next()) {
					num = rs.getInt(1);
				}
				psmt.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}else if(type.equals("会员")){
			String sql = "select count(*) from t_parking where type='会员' and state='空'";
			// Manager manager = null;
			//System.out.println(num);
			PreparedStatement psmt =null;
			ResultSet rs = null;
			try {
				Connection conn = ConnOra.connOracle();
				psmt = conn.prepareStatement(sql);
			//	psmt.setString(1, type);
			    rs = psmt.executeQuery();
				while (rs.next()) {
					num = rs.getInt(1);
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
		String sql = "select * from (select id from t_parking where state=? and type='临时' order by id) where rownum<2";
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

	/* (non-Javadoc)
	 * @see pers.dao.ParkingDao#checkP(java.lang.String)
	 */
	@Override
	public boolean checkState(String id) {
		boolean flag = false;
		String state = null;
		String sql = "select state from t_parking where id=?";
		try {
			Connection conn = ConnOra.connOracle();
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				state = rs.getString("state");
				if(state.equals(null)) {
					JOptionPane.showMessageDialog(null, "该车位不存在！", "警告", JOptionPane.WARNING_MESSAGE);
				}else if(state.equals("空")){
					flag = true;
				}
			}
			psmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("flag"+flag);
		return flag;
	}

	/* (non-Javadoc)
	 * @see pers.dao.ParkingDao#getPrice(java.lang.String)
	 */
	@Override
	public float getPrice(String id,int n) {
		// TODO Auto-generated method stub
		float price=0;
		float t_price=0;		
		float m_price=0;		
		String sql = "select m_price,t_price from t_parking where id=?";
		try {
			Connection conn = ConnOra.connOracle();
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				m_price=rs.getFloat("m_price");
				t_price=rs.getFloat("t_price");
			}
			psmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(n==0) {
			price = t_price;
		}else {
			price = m_price;
		}
		return price;
	}

	/* (non-Javadoc)
	 * @see pers.dao.ParkingDao#addParking(java.lang.String, float, float)
	 */
	@Override
	public boolean addParking(Parking parking) {
		// TODO Auto-generated method stub
		boolean flag = false;
		String sql="insert into t_parking values(?,?,?,?,?)";
		try {
			Connection conn = ConnOra.connOracle();
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, parking.getId());
			psmt.setString(2, parking.getState());
			psmt.setString(3, parking.getType());
			psmt.setFloat(4, parking.getT_price());
			psmt.setFloat(5, parking.getM_price());
			int rs = psmt.executeUpdate();
			if(rs>0) {
				flag = true;
			}
			conn.close();
			psmt.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}

	/* (non-Javadoc)
	 * @see pers.dao.ParkingDao#delParking(java.lang.String)
	 */
	@Override
	public boolean delParking(String id) {
		boolean flag = false;
		String sql = "delete t_parking where id=?";
		try {
			Connection conn = ConnOra.connOracle();
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			int rs = psmt.executeUpdate();
			if(rs>0) {
				flag = true;
			}
			psmt.close();
			conn.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return flag;
	}

	/* (non-Javadoc)
	 * @see pers.dao.ParkingDao#upTPT(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean upTPT(String type, String id) {
		boolean flag=false;
		String sql = "update t_parking set type=? where id=?";
		// Manager manager = null;
		if(checkState(id)) {
			try {
				Connection conn = ConnOra.connOracle();
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(1, type);
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
		}else {
			JOptionPane.showMessageDialog(null, "该车位已被占！", "警告", JOptionPane.WARNING_MESSAGE);
		}
		return flag;
	}

	/* (non-Javadoc)
	 * @see pers.dao.ParkingDao#checkM(java.lang.String)
	 */
	@Override
	public boolean checkM(String id) {
		String type="";
		String state="";
		boolean flag = false;
		String sql = "select type,state from t_parking where id=?";
		try {
			Connection conn = ConnOra.connOracle();
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				type = rs.getString("type");
				state=rs.getString("state");
			}
			if(type.equals("临时") && state.equals("空")) {
				flag=true;
			}
			psmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
}
