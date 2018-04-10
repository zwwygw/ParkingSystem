package pers.table;

import java.util.Date;

public class TemporaryFee {
	private String id;//车位编号
	private String plate_num;//车牌号
	private String m_id;//收费员工工号
	private String serial_num;//流水号
	private float  fee;//收费
	private Date   entry_time;//进入时间
	private Date   exit_time;//退出时间
    
	public TemporaryFee(String id,String p_n,String m_id,String s_n,
			                            float fee,Date en,Date ex) {
		this.id = id;
		this.plate_num= p_n;
		this.m_id = m_id;
		this.serial_num = s_n;
		this.fee = fee;
		this.entry_time = en;
		this.exit_time = ex;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPlate_num() {
		return plate_num;
	}
	public void setPlate_num(String plate_num) {
		this.plate_num = plate_num;
	}
	public Date getEntry_time() {
		return entry_time;
	}
	public void setEntry_time(Date entry_time) {
		this.entry_time = entry_time;
	}
	public Date getExit_time() {
		return exit_time;
	}
	public void setExit_time(Date exit_time) {
		this.exit_time = exit_time;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getSerial_num() {
		return serial_num;
	}
	public void setSerial_num(String serial_num) {
		this.serial_num = serial_num;
	}
	public float getFee() {
		return fee;
	}
	public void setFee(float fee) {
		this.fee = fee;
	}
}
