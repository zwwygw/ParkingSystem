/**
* <p>Title: MemberFee.java<／p>
* <p>Description: 对应t_memberfee表的VO<／p>
* <p>Copyright: Copyright (c) 2018年4月4日<／p>
* <p>Company: CSU<／p>
* @author ZWW
* @date 2018年4月4日
* @version 1.0
*/
package pers.table;

import java.sql.Date;

/**
 * @ClassName MemberFee
 * @Description 对应t_memberfee表的VO类
 * @author ZWW
 * @date   2018年4月4日下午4:34:08
 */
public class MemberFee {

	private String id;// 车位编号
	private String serial_num;// 流水号
	private String plate_num;// 车牌号
	private Date eff_date;// 生效期
	private Date ex_date;// 有效期
	private float fee;//收费
	private String mid;//收费员
	
	public float getFee() {
		return fee;
	}
	public void setFee(float fee) {
		this.fee = fee;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public MemberFee(String id,String m_id, float fee,
			           String s_n,String p_num,Date eff,Date ex) {
		this.id  = id;
		this.mid = m_id;
		this.fee = fee;
		this.ex_date = ex;
		this.eff_date = eff;
		this.plate_num = p_num;
		this.serial_num = s_n;	
	}
	public MemberFee(String id,String m_id,String p_num,Date eff,Date ex) {
		this.id  = id;
		this.mid = m_id;
		this.ex_date = ex;
		this.eff_date = eff;
		this.plate_num = p_num;
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
	 * @Description 获取会员的流水号
	 * @return String
	 */
	public String getSerial_num() {
		return serial_num;
	}

	/**
	 * @Description 设置会员的流水号
	 * @param serial_num 
	 * @return void
	 */
	public void setSerial_num(String serial_num) {
		this.serial_num = serial_num;
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

	/**
	 * @Description 得到会员生效期
	 * @return Date
	 */
	public Date getEff_date() {
		return eff_date;
	}

	/**
	 * @Description 设置会员生效期
	 * @param eff_date 
	 * @return void
	 */
	public void setEff_date(Date eff_date) {
		this.eff_date = eff_date;
	}

	/**
	 * @Description 得到会员到期时间
	 * @return Date
	 */
	public Date getEx_date() {
		return ex_date;
	}

	/**
	 * @Description 设置会员到期时间
	 * @param ex_date 
	 * @return void
	 */
	public void setEx_date(Date ex_date) {
		this.ex_date = ex_date;
	}
}
