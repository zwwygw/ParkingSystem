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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import pers.dao.InforParkingDao;
import pers.dao.TemporaryFeeDao;
import pers.data.ConnOra;
import pers.table.InforParking;
import pers.table.TemporaryFee;
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
		boolean flag = false;
	//	Connection connection = ConnOra.connOracle();
		java.util.Date now = new java.util.Date(); 
	//	SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyyMMdd");
	//	String en_time = dateFormat1.format(now);
		String s1 = dateFormat2.format(now);
		++ManagerUI.ennum;
		int en_num=ManagerUI.ennum;
		
		String s_ennum  = Integer.toBinaryString(en_num);
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
		String sql = "insert into t_TempFee(s_num,id,p_num,en_time) values(?,?,?,?)";
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
				InforParkingDao iDao=new InforParkingDaoImp();
				InforParking iParking = new InforParking(en_num, ManagerUI.exnum, ManagerUI.fee, ManagerUI.mid);
				iDao.inUpInfor(iParking);
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
	public float comp(String pnum,String mid,float price) {
		boolean flag = false;
		float tprice = 0;
		String sql ="update t_TempFee set ex_time=?,fee=?,m_id=? where p_num = ? and ex_time is null";
		java.util.Date ent =getEnT(pnum);		
		java.util.Date  date0=new java.util.Date();
		java.sql.Date  data1=new java.sql.Date(date0.getTime());
		java.util.Date ext = data1;
      	int time=Math.round((ext.getTime()-ent.getTime())/(60*1000));//得到停车时间
      	float fee = price;
      	if(time>1) {
      		 fee = Math.round(time)*price;//得到费用
      	} 	
	//	System.out.println("时间："+time);
	//	System.out.println("费用："+fee);
		try {
			Connection conn = ConnOra.connOracle();
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setDate(1, data1);
			psmt.setFloat(2, fee);
			psmt.setString(3, mid);
			psmt.setString(4, pnum);
			int rs = psmt.executeUpdate();
			if(rs==1) {
				flag = true;
				tprice = fee;
				++ManagerUI.exnum;
				ManagerUI.fee+=fee;
				InforParkingDao iDao=new InforParkingDaoImp();
				InforParking iParking = new InforParking(ManagerUI.ennum, ManagerUI.exnum, ManagerUI.fee, ManagerUI.mid);
				iDao.inUpInfor(iParking);
			}
			psmt.close();
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return tprice;
	}

	/* (non-Javadoc)
	 * @see pers.dao.TemporaryFeeDao#getPId(java.lang.String)
	 */
	@Override
	public String getPId(String pnum) {
		// TODO Auto-generated method stub
		String id = null;
		String sql = "select id from t_TempFee  where p_num = ? and ex_time is null";
		try {
			Connection conn = ConnOra.connOracle();
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, pnum);
			
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				id=rs.getString("id");
				
			}
			conn.close();
			psmt.close();
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return id;
	}

	/* (non-Javadoc)
	 * @see pers.dao.TemporaryFeeDao#getEnT(java.lang.String)
	 */
	@Override
	public java.util.Date getEnT(String pnum) {
		// TODO Auto-generated method stub
		java.util.Date date = new java.util.Date();
		String sql = "select en_time from t_tempfee where p_num=? and ex_time is null";
		try {
			Connection conn = ConnOra.connOracle();
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, pnum);
			
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				date = rs.getDate("en_time");				
			}
			conn.close();
			psmt.close();
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return date;
	}

	/* (non-Javadoc)
	 * @see pers.dao.TemporaryFeeDao#queryT(int)
	 */
	@Override
	public Vector<TemporaryFee> queryT(int page) {
		Vector<TemporaryFee> list = new Vector<TemporaryFee>();
		String sql = "select * from(select a.*,ROWNUM rn from(select * from t_TempFee order by s_num asc) a where ROWNUM<=?) where rn>?";
		try {
			Connection conn =  ConnOra.connOracle();
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1, page*TemporaryFee.PageSize);
			psmt.setInt(2, (page-1)*TemporaryFee.PageSize);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				String s_num = rs.getString("s_num");
				String id    = rs.getString("id");
				String p_num = rs.getString("p_num");
				Date en_time = rs.getDate("en_time");
				Date ex_time = rs.getDate("ex_time");
				float fee    = rs.getFloat("fee");
				String m_id  = rs.getString("m_id");
				TemporaryFee tFee = new TemporaryFee(id, p_num, m_id, s_num, fee, en_time, ex_time);
				list.add(tFee);
			//	System.out.println(sno);
			}
			psmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see pers.dao.TemporaryFeeDao#count()
	 */
	@Override
	public int count() {
		int num = 0;
		String sql = "select count(*)num from t_tempfee";
		try {
			Connection conn = ConnOra.connOracle();
			PreparedStatement psmt = conn.prepareStatement(sql);
			
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				num = rs.getInt("num");				
			}
			conn.close();
			psmt.close();
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return num;
	}

}
