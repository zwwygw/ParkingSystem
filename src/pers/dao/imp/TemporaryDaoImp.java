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
import java.text.SimpleDateFormat;
import java.util.Date;

import oracle.net.aso.i;
import pers.dao.TemporaryFeeDao;
import pers.data.ConnOra;
import pers.ui.ManagerUI;

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
		Date now = new Date(); 
		SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy/MM/dd");
		String en_time = dateFormat1.format(now);
		String s1 = dateFormat2.format(now);
		String s_ennum  = Integer.toBinaryString(ManagerUI.ennum);
		int len = s_ennum.length();
		if(len<4) {
			for(int j = 0; j < 4-len; j++) {
				s_ennum = "0"+s_ennum;
				//System.out.println(string);
			}
		}
		String s_num = s1+id+s_ennum;
		java.util.Date  date0=new java.util.Date();
		java.sql.Date  data1=new java.sql.Date(date0.getTime());
		String sql = "insert into t_TempFee(s_num,id,p_num,en_time) values(?,?,?,?);";
		// Manager manager = null;
		try {
			Connection conn = ConnOra.connOracle();
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, s_num);
			psmt.setString(2, id);
			psmt.setString(3, pnum);
			psmt.setDate(4, data1);
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
	 * @see dao.TemporaryFeeDao#comp(java.lang.String)
	 */
	@Override
	public boolean comp(String pnum) {
		// TODO Auto-generated method stub
		return false;
	}

}
