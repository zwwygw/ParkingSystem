/**
* <p>Title: MemberDaoImp.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2018年5月9日<／p>
* <p>Company: CSU<／p>
* @author ZWW
* @date 2018年5月9日
* @version 1.0
*/
package pers.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import pers.table.Member;
import pers.dao.MemberDao;
import pers.data.ConnOra;

/**
 * @ClassName MemberDaoImp
 * @Description TODO
 * @author ZWW
 * @date   2018年5月9日上午2:11:50
 */
public class MemberDaoImp implements MemberDao{

	/* (non-Javadoc)
	 * @see pers.dao.MemberDao#add(java.lang.reflect.Member)
	 */
	@Override
	public boolean add(Member member) {
		boolean flag = false;	
		String sql = "insert into t_Member(id,p_num,name,tel) values(?,?,?,?)";
		
		try {
			Connection conn = ConnOra.connOracle();
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, member.getId());
			psmt.setString(2, member.getPlate_num());
			psmt.setString(3, member.getName());
			psmt.setString(4, member.getTel());
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
	 * @see pers.dao.MemberDao#checkM(java.lang.String)
	 */
	@Override
	public String checkM(String pnum) {
		String id="";
		String sql = "select id from t_member where p_num=?";
		try {
			Connection conn = ConnOra.connOracle();
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, pnum);
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
	 * @see pers.dao.MemberDao#getId(java.lang.String)
	 */
	@Override
	public String getId() {
		String id = null;
		String sql = "select * from (select id from t_parking where state='空' and type='临时' order by id) where rownum<2";
		// Manager manager = null;
		try {
			Connection conn = ConnOra.connOracle();
			PreparedStatement psmt = conn.prepareStatement(sql);
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

}
