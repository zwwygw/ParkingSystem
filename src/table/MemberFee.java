package table;

import java.util.Date;

public class MemberFee {
	private String name;// 会员姓名
	private String tel;// 会员电话
	private String id;// 车位编号
	private String serial_num;// 流水号
	private String plate_num;// 车牌号
	private Date eff_date;// 生效期
	private Date ex_date;// 有效期

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSerial_num() {
		return serial_num;
	}

	public void setSerial_num(String serial_num) {
		this.serial_num = serial_num;
	}

	public String getPlate_num() {
		return plate_num;
	}

	public void setPlate_num(String plate_num) {
		this.plate_num = plate_num;
	}

	public Date getEff_date() {
		return eff_date;
	}

	public void setEff_date(Date eff_date) {
		this.eff_date = eff_date;
	}

	public Date getEx_date() {
		return ex_date;
	}

	public void setEx_date(Date ex_date) {
		this.ex_date = ex_date;
	}
}
