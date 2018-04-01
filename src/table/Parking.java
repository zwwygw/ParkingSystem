package table;

public class Parking {
   private String id;//车位编号
   private String state;//车位状态
   private String type;//车位类型
   private float  t_price;//临时价(小时)  
   private float  m_price;//会员价(月）  
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public float getT_price() {
	return t_price;
}
public void setT_price(float t_price) {
	this.t_price = t_price;
}
public float getM_price() {
	return m_price;
}
public void setM_price(float m_price) {
	this.m_price = m_price;
}

}
