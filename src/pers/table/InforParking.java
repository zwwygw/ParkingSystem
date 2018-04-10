/**
* <p>Title: InforParking.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2018年4月9日<／p>
* <p>Company: CSU<／p>
* @author ZWW
* @date 2018年4月9日
* @version 1.0
*/
package pers.table;

import java.util.Date;

/**
 * @ClassName InforParking
 * @Description TODO
 * @author ZWW
 * @date   2018年4月9日上午12:18:11
 */
public class InforParking {
    public Date today;
    public int en_num = 0;
    public int ex_num = 0;
    public float fee = 0;
    public String m_id;
    
    public InforParking(int ennum,int exnum,float fee,String m_id) {
    	this.m_id = m_id;
    	this.fee = fee;
    	this.en_num = ennum;
    	this.ex_num = exnum;
    }
	public Date getToday() {
		return today;
	}
	public void setToday(Date today) {
		this.today = today;
	}
	public int getEn_num() {
		return en_num;
	}
	public void setEn_num(int en_num) {
		this.en_num = en_num;
	}
	public int getEx_num() {
		return ex_num;
	}
	public void setEx_num(int ex_num) {
		this.ex_num = ex_num;
	}
	public float getFee() {
		return fee;
	}
	public void setFee(float fee) {
		this.fee = fee;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
}
