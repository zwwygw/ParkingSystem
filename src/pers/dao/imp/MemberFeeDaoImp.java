/**
* <p>Title: MemberFeeDaoImp.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2018年4月25日<／p>
* <p>Company: CSU<／p>
* @author ZWW
* @date 2018年4月25日
* @version 1.0
*/
package pers.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import pers.dao.InforParkingDao;
import pers.dao.MemberFeeDao;
import pers.dao.ParkingDao;
import pers.data.ConnOra;
import pers.table.InforParking;
import pers.table.MemberFee;
import pers.ui.ManagerUI;

/**
 * @ClassName MemberFeeDaoImp
 * @Description TODO
 * @author ZWW
 * @date   2018年4月25日下午3:36:17
 */
public class MemberFeeDaoImp implements MemberFeeDao{

	/* (non-Javadoc)
	 * @see pers.dao.MemberFeeDao#add(java.lang.String, java.lang.String)
	 */
	@Override
	public int add(MemberFee mFee) {
		int rfee = 0;
		java.util.Date now = new java.util.Date(); 	
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyyMMdd");
		String s1 = dateFormat2.format(now);
		String s_num = 'S'+s1+mFee.getId();
		
		java.util.Date ex = mFee.getEx_date();
		java.util.Date eff = mFee.getEff_date();
		float time=(float) (ex.getTime()-eff.getTime())/(24*60*60*1000);//得到停车时间
		System.out.println(time+"day");
		ParkingDao pDao = new ParkingDaoImp();
		float price = pDao.getPrice(mFee.getId(), 1);
		float fee = price*Math.round(time);
		String sql = "insert into t_MemberFee(s_num,id,p_num,eff_date,ex_date,fee,m_id) values(?,?,?,?,?,?,?)";
			
		try {
			Connection conn = ConnOra.connOracle();
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, s_num);
			psmt.setString(2, mFee.getId());
			psmt.setString(3, mFee.getPlate_num());
			psmt.setDate(4, mFee.getEff_date());
			psmt.setDate(5, mFee.getEx_date());
			psmt.setFloat(6, fee);
			psmt.setString(7, mFee.getMid());
			int rs = psmt.executeUpdate();
			if(rs==1) {
				rfee = (int) fee;
			}
			psmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rfee;
	}
}
