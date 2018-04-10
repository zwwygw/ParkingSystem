/**
* <p>Title: Manager.java<／p>
* <p>Description: 对应t_manager表的VO<／p>
* <p>Copyright: Copyright (c) 2018年4月4日<／p>
* <p>Company: CSU<／p>
* @author ZWW
* @date 2018年4月4日
* @version 1.0
*/
package pers.table;

/**
 * @ClassName Manager
 * @Description 对应t_manager表的VO
 * @author ZWW
 * @date   2018年4月4日下午4:26:56
 */
public class Manager {
	private int    power;//权限
	private String id;//工号
	private String pwd;//密码
	private String name;//姓名
	
	public Manager(int p,String id,String pwd,String name) {
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.power = p;
	}
	/**
	 * @Description 获取工号值
	 * @return String
	 */
	public String getId() {
		return id;
	}
	/**
	 * @Description 设置工号值
	 * @param id 
	 * @return void
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @Description 获取密码
	 * @return String
	 */
	public String getPwd() {
		return pwd;
	}
	/**
	 * @Description  设置密码值
	 * @param pwd 
	 * @return void
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	/**
	 * @Description 获取姓名
	 * @return String
	 */
	public String getName() {
		return name;
	}
	/**
	 * @Description 设置姓名值
	 * @param name 
	 * @return void
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @Description 获取权限值
	 * @return int
	 */
	public int getPower() {
		return power;
	}
	/**
	 * @Description 设置权限
	 * @param power 
	 * @return void
	 */
	public void setPower(int power) {
		this.power = power;
	}
	
}
