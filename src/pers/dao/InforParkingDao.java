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
	 * @Description 得到当日车辆进出数
	 * @param time
	 * @param type
	 * @return int
	 */
	public int enOrexnum(Date time,String type);
	public float getfee(Date time);
	/**
	 * @Description TODO
	 * @param iParking
	 * @return boolean
	 */
	public boolean inUpInfor(InforParking iParking);
}
