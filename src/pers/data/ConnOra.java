/**
* <p>Title: ConnOra.java<／p>
* <p>Description: 实现数据库的连接的与关闭<／p>
* <p>Copyright: Copyright (c) 2018年4月4日<／p>
* <p>Company: CSU<／p>
* @author ZWW
* @date 2018年4月4日
* @version 1.0
*/
package pers.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @ClassName ConnOra
 * @Description 实现数据库的连接与关闭
 * @author ZWW
 * @date   2018年4月4日下午4:16:47
 */
public class ConnOra {

	/**
	 * @Description 连接数据库
	 * @return Connection
	 */
	public static Connection connOracle() {
		Connection con = null;// 创建一个数据库连接
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");// 加载Oracle驱动程序

			System.out.println("开始尝试连接数据库！");
			String url = "jdbc:oracle:" + "thin:@127.0.0.1:1521:orcl";// 127.0.0.1是本机地址，XE是精简版Oracle的默认数据库名
			String user = "C##U_32";// 用户名
			String password = "zww0902150232";// 设置的密码
			con = DriverManager.getConnection(url, user, password);// 获取连接
			System.out.println("连接成功！");

		} catch (Exception e) {
			System.out.println("未连接");
			e.printStackTrace();
		}

		return con;
	}

	/**
	 * @Description 关闭数据库连接
	 * @param con 
	 * @return void
	 */
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
}
