package top.boyn.hfut.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Boyn
 * @date 2019/11/1
 */
public class TIME_UNIT {
    private static Map<String,Integer> TIME_UNIT_MAP;
    private static Map<Integer,String> UNIT_TIME_MAP;
    static {
        TIME_UNIT_MAP = new HashMap<>();
        TIME_UNIT_MAP.put("810", 1);
        TIME_UNIT_MAP.put("1000", 2);
        TIME_UNIT_MAP.put("1020", 3);
        TIME_UNIT_MAP.put("1210", 4);
        TIME_UNIT_MAP.put("1400", 5);
        TIME_UNIT_MAP.put("1550", 6);
        TIME_UNIT_MAP.put("1600", 7);
        TIME_UNIT_MAP.put("1750", 8);
        TIME_UNIT_MAP.put("1900", 9);
        TIME_UNIT_MAP.put("2050", 10);
        TIME_UNIT_MAP.put("2100", 11);
        TIME_UNIT_MAP.put("2150", 12);

        UNIT_TIME_MAP = new HashMap<>();
        UNIT_TIME_MAP.put(1, "8:10");
        UNIT_TIME_MAP.put(2, "10:00");
        UNIT_TIME_MAP.put(3, "10:20");
        UNIT_TIME_MAP.put(4, "12:10");
        UNIT_TIME_MAP.put(5, "14:00");
        UNIT_TIME_MAP.put(6, "15:50");
        UNIT_TIME_MAP.put(7, "16:00");
        UNIT_TIME_MAP.put(8, "17:50");
        UNIT_TIME_MAP.put(9, "19:00");
        UNIT_TIME_MAP.put(10, "20:50");
        UNIT_TIME_MAP.put(11, "21:00");
        UNIT_TIME_MAP.put(12, "21:50");
    }


    public static Integer getUnit(String TIME){
        return TIME_UNIT_MAP.get(TIME);
    }

    public static String getTime(Integer UNIT){
        return UNIT_TIME_MAP.get(UNIT);
    }
}
