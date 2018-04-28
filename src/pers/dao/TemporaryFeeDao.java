/**
* <p>Title: TemporaryFeeDao.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2018年4月4日<／p>
* <p>Company: CSU<／p>
* @author ZWW
* @date 2018年4月4日
* @version 1.0
*/
package pers.dao;

/**
 * @ClassName TemporaryFeeDao
 * @Description 临时表收费数据操作接口
 * @author ZWW
 * @date   2018年4月4日下午2:59:03
 */
public interface TemporaryFeeDao {
	/**
	 * @Description 车辆进入增加流水
	 * @param pnum 车牌号
	 * @param id 车位编号
	 * @return boolean 增加成功与否
	 */
	public boolean add(String pnum,String id);
	/**
	 * @Description 车辆离开完善流水信息
	 * @param pnum
	 * @param mid 
	 * @return boolean
	 */
	public boolean comp(String pnum,String mid,float price);
	/**
	 * @Description 得到车位编号
	 * @param pnum
	 * @return boolean
	 */
	public String getPId(String pnum);
	
	/**
	 * @Description 得到进入时间
	 * @param pnum
	 * @return String
	 */
	public java.util.Date getEnT(String pnum);
}
