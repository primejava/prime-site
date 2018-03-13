package org.primejava.basic.util;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CapacityUtil {
	
	public static final long CAPACITY_UNIT = 1000L;
	public static final long CAPACITY_SECTORS = 512L;
	
	public static final long CAPACITY_MB = CAPACITY_UNIT * CAPACITY_UNIT ;
	public static final long CAPACITY_GB = CAPACITY_UNIT * CAPACITY_UNIT * CAPACITY_UNIT;
	public static final long CAPACITY_TB = CAPACITY_UNIT * CAPACITY_UNIT * CAPACITY_UNIT * CAPACITY_UNIT;
	public static final long CAPACITY_PB = CAPACITY_UNIT * CAPACITY_UNIT * CAPACITY_UNIT * CAPACITY_UNIT * CAPACITY_UNIT;
	
	public static final BigInteger CAPACITY_GB_1024 = BigInteger.valueOf(1024l * 1024 * 1024);
	public static final BigInteger CAPACITY_BIG_UNIT = BigInteger.valueOf(CAPACITY_UNIT);
	public static final BigInteger CAPACITY_BIG_MB = BigInteger.valueOf(CAPACITY_MB);
	public static final BigInteger CAPACITY_BIG_GB = BigInteger.valueOf(CAPACITY_GB);
	public static final BigInteger CAPACITY_BIG_TB = BigInteger.valueOf(CAPACITY_TB);
	public static final BigInteger CAPACITY_BIG_PB = BigInteger.valueOf(CAPACITY_PB);
	public static final BigInteger CAPACITY_BIG_SECTORS = BigInteger.valueOf(CAPACITY_SECTORS);
	
	//convert byte to GB
	public static BigInteger getGBSizeFromByte(BigInteger capacity){
		return capacity.divide(CAPACITY_BIG_GB);
	}
	
	//convert GB to byte
	public static BigInteger getBigByteSizeFromGB(long capacity){
		return BigInteger.valueOf(capacity).multiply(CAPACITY_BIG_GB);
	}
	
	public static String toMKByte(final long size) {
        if (size >= 0L && size < 1024L) {
            return (new StringBuilder(String.valueOf(size))).append("B").toString();
        }
        if (size >= 1024L && size < 0x100000L) {
            return (new StringBuilder(String.valueOf(size / 1024L))).append("KB").toString();
        }
        if (size >= 0x100000L && size < 0x40000000L) {
            return (new StringBuilder(String.valueOf(size / 0x100000L))).append("MB").toString();
        }
        if (size >= 0x40000000L && size < 0x10000000000L) {
            BigDecimal longs = new BigDecimal(Double.valueOf((new StringBuilder(String.valueOf(size))).toString())
                    .toString());
            BigDecimal sizeMB = new BigDecimal(Double.valueOf("1073741824").toString());
            String result = longs.divide(sizeMB, 2, 4).toString();
            return (new StringBuilder(String.valueOf(result))).append("GB").toString();
        } else {
            BigDecimal longs = new BigDecimal(Double.valueOf((new StringBuilder(String.valueOf(size))).toString())
                    .toString());
            BigDecimal sizeMB = new BigDecimal(Double.valueOf("1099511627776").toString());
            String result = longs.divide(sizeMB, 2, 4).toString();
            return (new StringBuilder(String.valueOf(result))).append("TB").toString();
        }
    }

}
