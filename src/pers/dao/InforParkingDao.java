/**
* <p>Title: InforParkingDao.java<／p>
* <p>Description: <／p>
* <p>Copyright: Copyright (c) 2018年4月9日<／p>
* <p>Company: CSU<／p>
* @author ZWW
* @date 2018年4月9日
* @version 1.0
*/
package pers.dao;

import java.sql.Date;

import pers.table.InforParking;

/**
 * @ClassName InforParkingDao
 * @Description TODO
 * @author ZWW
 * @date   2018年4月9日上午12:30:31
 */
public interface InforParkingDao {
	/**
	 * @Description 得到相应的日常统计
	 * @param time
	 * @return InforParking
	 */
	public InforParking getIOP(Date time);	
	/**
	 * @Description 更新当日车辆进出数
	 * @param time
	 * @param type
	 * @param enorexnum
	 * @return boolean
	 */
	public boolean upEnOrEx(Date time,String type,int enorexnum);
	/**
	 * @Description 插入或更新车位日常信息表
	 * @param iParking
	 * @return boolean
	 */
	public boolean inUpInfor(InforParking iParking);
}
