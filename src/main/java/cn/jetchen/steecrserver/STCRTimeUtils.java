package cn.jetchen.steecrserver;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: STCRTimeUtils
 * @Description: 时间工具类
 * @Author: Jet.Chen
 * @Date: 2019/7/17 14:11
 * @Version: 1.0
 **/
public class STCRTimeUtils {
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String formateDate(Timestamp date) {
        return date == null ? DATE_FORMAT.format(new Date()) : DATE_FORMAT.format(date);
    }

    public static String formateDateTime(Timestamp dateTime) {
        return dateTime == null ? DATETIME_FORMAT.format(new Date()) : DATETIME_FORMAT.format(dateTime);
    }

}
