package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class ConnOra {
	/**
	 * 一个非常标准的连接Oracle数据库的示例代码
	 */

	public static Connection connOracle() {
		Connection con = null;// 创建一个数据库连接
		// PreparedStatement pre = null;// 创建预编译语句对象，一般都是用这个而不用Statement
		// ResultSet result = null;// 创建一个结果集对象
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");// 加载Oracle驱动程序

			System.out.println("开始尝试连接数据库！");
			String url = "jdbc:oracle:" + "thin:@127.0.0.1:1521:orcl";// 127.0.0.1是本机地址，XE是精简版Oracle的默认数据库名
			String user = "C##U_32";// 用户名
			String password = "zww0902150232";// 设置的密码
			con = DriverManager.getConnection(url, user, password);// 获取连接
			System.out.println("连接成功！");
			/*
			 * String sql = "select * from manager";// 预编译语句，“？”代表参数 pre =
			 * con.prepareStatement(sql);// 实例化预编译语句
			 * 
			 * result = pre.executeQuery();// 执行查询，注意括号中不需要再加参数 while(result.next()) //
			 * 当结果集不为空时 System.out.println("账户:" + result.getString("id") + "密码:" +
			 * result.getString("pwd"));
			 */

		} catch (Exception e) {
			System.out.println("未连接");
			e.printStackTrace();
		}
		/*
		 * finally { try { // 逐一将上面的几个对象关闭，因为不关闭的话会影响性能、并且占用资源 // 注意关闭的顺序，最后使用的最先关闭 if
		 * (result != null) result.close(); if (pre != null) pre.close(); if (con !=
		 * null) con.close(); System.out.println("数据库连接已关闭！"); } catch (Exception e) {
		 * e.printStackTrace(); } }
		 */
		return con;
	}

	public void closeOracle(Connection con) {
		if (con != null)
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		System.out.println("数据库连接已关闭！");
	}

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return num;
	}
	
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
	
	/**
	 * public static void main(String[] arg0) { @SuppressWarnings("static-access")
	 * Connection connOral = new ConnOra().connOracle(); try { connOral.close();
	 * System.out.println("数据库连接已关闭！"); } catch (SQLException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } }
	 */
}
