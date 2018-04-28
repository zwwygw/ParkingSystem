/**
* <p>Title: InforParkingDaoImp.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2018年4月9日<／p>
* <p>Company: CSU<／p>
* @author ZWW
* @date 2018年4月9日
* @version 1.0
*/
package pers.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import pers.dao.InforParkingDao;
import pers.data.ConnOra;
import pers.table.InforParking;

/**
 * @ClassName InforParkingDaoImp
 * @Description TODO
 * @author ZWW
 * @date   2018年4月9日上午12:32:10
 */
public class InforParkingDaoImp implements InforParkingDao{
    public InforParking getIOP(Date time) {
    	int ennum = 0;
    	int exnum = 0;
    	float fee = 0;
    	String mid = null;
    	InforParking iParking = new InforParking(ennum, exnum, fee, mid);
    	String sql = "select * from t_infor_parking where today=?";
		// Manager manager = null;
		try {
			Connection conn = ConnOra.connOracle();
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setDate(1, time);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				ennum = rs.getInt("en_num");
				exnum = rs.getInt("ex_num");
				  fee = rs.getFloat("fee");
				  mid = rs.getString("m_id");
			   iParking = new InforParking(ennum, exnum, fee, mid);
			}
			psmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    	return iParking;
    } 
	/* (non-Javadoc)
	 * @see pers.dao.InforParkingDao#upenOrex(java.sql.Date, java.lang.String)
	 */
	public boolean upEnOrEx(Date time,String type,int enorexnum) {
		boolean flag = false;
    	String sql = "update t_infor_parking set "+type+"=? where today=?";
		// Manager manager = null;
		try {
			Connection conn = ConnOra.connOracle();
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1, enorexnum);
			psmt.setDate(2, time);
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
	 * @see pers.dao.InforParkingDao#insert_infor(java.sql.Date)
	 */
	@Override
	public boolean inUpInfor(InforParking iParking) {
		boolean flag = false;
		System.out.println("更新"); 
		String sql = " MERGE INTO t_infor_parking A USING (select to_date(sysdate) as today from dual) B ON(A.today=B.today)  \r\n" + 
				"                 WHEN MATCHED THEN\r\n" + 
				"                     UPDATE SET ex_num=?,en_num=?,fee=? where A.today=B.today\r\n" + 
				"                 WHEN NOT MATCHED THEN  \r\n" + 
				"                     INSERT(A.today,A.m_id) values(B.today,?)\r\n";
		// Manager manager = null;
		try {
			Connection conn = ConnOra.connOracle();
			PreparedStatement psmt = conn.prepareStatement(sql);
			
			
			psmt.setInt(1, iParking.getEn_num());
			System.out.println(iParking.getEn_num()); 
		
			psmt.setInt(2, iParking.getEx_num());
			System.out.println(iParking.getEx_num()); 
			psmt.setFloat(3, iParking.getFee());
			System.out.println(iParking.getFee()); 
		
			psmt.setString(4, iParking.getM_id());
			System.out.println(iParking.getM_id()); 
			int rs = psmt.executeUpdate();
			System.out.println("hh"); 
			if(rs==1) {
				
				flag = true;
				System.out.println("初始化成功"); 
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return flag;
	}

}
