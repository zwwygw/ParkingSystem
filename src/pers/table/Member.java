/**
* <p>Title: Member.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2018年5月9日<／p>
* <p>Company: CSU<／p>
* @author ZWW
* @date 2018年5月9日
* @version 1.0
*/
package pers.table;

/**
 * @ClassName Member
 * @Description 会员
 * @author ZWW
 * @date   2018年5月9日上午2:01:52
 */
public class Member {
	private String name;// 会员姓名
	private String tel;// 会员电话
	private String id;// 车位编号
	private String plate_num;// 车牌号
	
	
	
	public Member(String name,String tel,String id,String p_num) {
		this.id = id;
        this.name = name;
        this.tel = tel;
		this.plate_num = p_num;
	
	}

	
	/**
	 * @Description 得到会员姓名
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * @Description 设置会员的姓名
	 * @param name 
	 * @return void
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @Description 得到会员的联系方式
	 * @return String
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @Description 设置会员的联系方式
	 * @param tel 
	 * @return void
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * @Description 得到会员车位编号
	 * @return String
	 */
	public String getId() {
		return id;
	}

	/**
	 * @Description 设置会员的车位编号
	 * @param id 
	 * @return void
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @Description 得到会员的车牌号
	 * @return String
	 */
	public String getPlate_num() {
		return plate_num;
	}

	/**
	 * @Description 设置会员的车牌号
	 * @param plate_num 
	 * @return void
	 */
	public void setPlate_num(String plate_num) {
		this.plate_num = plate_num;
	}
}
