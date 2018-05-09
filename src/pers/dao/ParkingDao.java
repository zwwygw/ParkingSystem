package pers.dao;

import pers.table.Parking;

/**
 * @ClassName ParkingDao
 * @Description 对车位的数据操作接口
 * @author ZWW
 * @date   2018年3月30日下午10:15:01
 */
public interface ParkingDao {

	/**
	 * @Description 得到相应类型空车位数
	 * @param type
	 * @return int
	 */
	public int parkingNullNum(String type);
	/**
	 * @Description 得到临时车位相应状态的第一个车位编号
	 * @param state
	 * @return String
	 */
	public String getPId(String state);
	/**
	 * @Description 更新t_parking表车位的状态state
	 * @param state
	 * @param id
	 * @return boolean
	 */
	public boolean upTPS(String state,String id);
	/**
	 * @Description 车位状态是否为空
	 * @param id
	 * @return boolean
	 */
	public boolean checkState(String id);
	
	/**
	 * @Description 得到单价(n=0为临时）
	 * @param id
	 * @return float
	 */
	public float getPrice(String id,int n);
	public boolean addParking(Parking parking);
    public boolean delParking(String id);
	public boolean upTPT(String type,String id);
	public boolean checkM(String id);
}
