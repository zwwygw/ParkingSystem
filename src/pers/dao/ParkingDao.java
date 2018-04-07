package dao;
/**
 * @ClassName ParkingDao
 * @Description 对车位的数据操作接口
 * @author ZWW
 * @date   2018年3月30日下午10:15:01
 */
public interface ParkingDao {

	/**
	 * @Description 得到相应类型车位数
	 * @param type
	 * @return int
	 */
	public int parkingNum(String type);
	
}
