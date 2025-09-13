package AE.Math;
/**
 * This class contains mathematical functions for fixed-point numbers. 
 * Constant Q defines number of [decimal] digits that represent fractional part.
 * By default Q equals 12. This means that variable of type short can fit 
 * numbers from range  7.999755859375 to -8 with step 0.000244140625.
 * min for int (32 bit) int  is -524,288.
 * min for long (64 bit) is -2,251,799,813,685,248.
 */
public final class AEMath {
	
	public static final int Q = 12;
	
	public static final int Q_1 = 1 << Q;
	
	public static final float TO_FLOAT = 1.0f / (float)Q_1;
	public static final float TO_FLOAT_SQR = TO_FLOAT / (float)Q_1;
	public static final float TO_Q = (float)Q_1;
	
	/** 1/512 = 0.001953125 **/
	public static final int Q_512th = Q_1 / 512; 
	/** 1/256 = 0.00390625 **/
	public static final int Q_256th = Q_1 / 256; 
	/** 1/64 = 0.015625 **/
	public static final int Q_64th = Q_1 / 64; 
	/** 1/32 = 0.03125 **/
	public static final int Q_32nd = Q_1 / 32;
	/** 1/16 = 0.0625 **/
	public static final int Q_16th = Q_1 / 16;
	/** 1/8 = 0.125 **/
	public static final int Q_EIGHTH = Q_1 / 8;
	/** 3/16 = 0.1875 **/
	public static final int Q_THREE_SIXTEENTHS = Q_1 * 3/16;
	/** 1/4 = 0.25 **/
	public static final int Q_QUARTER = Q_1 / 4;
	/** 3/8 = 0.375 **/
	public static final int Q_THREE_EIGHTHS = Q_1 * 3/8;
	/** 1/2 = 0.5 **/
	public static final int Q_HALF = Q_1 / 2;
	/** 3/4 = 0.75 **/
	public static final int Q_THREE_QUARTERS = Q_1 * 3/4;
	/** 3/2 = 1.5  **/
	public static final int Q_THREE_HALFS = Q_1 * 3/2;
	
	public static final int Q_2 = Q_1 * 2;
	public static final int Q_4 = Q_1 * 4;
	public static final int Q_6 = Q_1 * 6;
	public static final int Q_8 = Q_1 * 8;
	
	/** 180 degrees **/
	public static final int Q_PI = Q_1;
	/** 360 degrees **/
	public static final int Q_PI_2 = Q_1 * 2;
	/** 225 degrees **/
	public static final int Q_PI_FIVE_QUARTERS = Q_PI * 5/4;
	/** 135 degrees **/
	public static final int Q_PI_THREE_QUARTERS = Q_PI * 3/4;
	/** 90 degrees **/
	public static final int Q_PI_HALF = Q_HALF;
	/** 60 degrees **/
	public static final int Q_PI_THIRD = (Q_1) / 3;
	/** 45 degrees **/
	public static final int Q_PI_QUARTER = Q_QUARTER;
	/** 30 degrees **/
	public static final int Q_PI_SIXTH = (Q_1) / 6;
	/** 22.5 degrees **/
	public static final int Q_PI_EIGHTH = Q_EIGHTH;
	/** 11.25 degrees **/
	public static final int Q_PI_SIXTEENTH = Q_16th;
	/** 5.625 degrees **/
	public static final int Q_PI_THIRTYSECOND = Q_32nd;
	
	public static final int Q_225d = Q_PI_FIVE_QUARTERS;
	public static final int Q_180d = Q_PI;
	public static final int Q_135d = Q_PI_THREE_QUARTERS;
	public static final int Q_90d = Q_PI_HALF;
	public static final int Q_45d = Q_PI_QUARTER;
	public static final int Q_60d = Q_PI_THIRD;
	public static final int Q_30d = Q_PI_SIXTH;
	
	public static final int Q_DIV (int a, int b) { return (a<<Q)/b; }
	public static final int Q_MUL (int a, int b) { return a*b>>Q; }
	public static final int Q_SQR (int a) { return a*a>>Q; }
	
	private static final short[] SINE_QUARTER = {
	        0,   6,  13,  19,  25,  31,  38,  44,  50,  57,  63,  69,  75,  82,  88,  94, 101, 107,
	      113, 119, 126, 132, 138, 144, 151, 157, 163, 170, 176, 182, 188, 195, 201, 207, 214, 220, 226, 232, 239,
	      245, 251, 257, 264, 270, 276, 283, 289, 295, 301, 308, 314, 320, 326, 333, 339, 345, 351, 358, 364, 370,
	      376, 383, 389, 395, 401, 408, 414, 420, 426, 433, 439, 445, 451, 458, 464, 470, 476, 483, 489, 495, 501,
	      508, 514, 520, 526, 533, 539, 545, 551, 557, 564, 570, 576, 582, 589, 595, 601, 607, 613, 620, 626, 632,
	      638, 644, 651, 657, 663, 669, 675, 682, 688, 694, 700, 706, 713, 719, 725, 731, 737, 744, 750, 756, 762,
	      768, 774, 781, 787, 793, 799, 805, 811, 818, 824, 830, 836, 842, 848, 854, 861, 867, 873, 879, 885, 891,
	      897, 904, 910, 916, 922, 928, 934, 940, 946, 953, 959, 965, 971, 977, 983, 989, 995, 1001, 1007, 1014, 1020,
	      1026, 1032, 1038, 1044, 1050, 1056, 1062, 1068, 1074, 1080, 1086, 1092, 1099, 1105, 1111, 1117, 1123, 1129,
	      1135, 1141, 1147, 1153, 1159, 1165, 1171, 1177, 1183, 1189, 1195, 1201, 1207, 1213, 1219, 1225, 1231, 1237,
	      1243, 1249, 1255, 1261, 1267, 1273, 1279, 1285, 1291, 1297, 1303, 1309, 1315, 1321, 1327, 1332, 1338, 1344,
	      1350, 1356, 1362, 1368, 1374, 1380, 1386, 1392, 1398, 1404, 1409, 1415, 1421, 1427, 1433, 1439, 1445, 1451,
	      1457, 1462, 1468, 1474, 1480, 1486, 1492, 1498, 1503, 1509, 1515, 1521, 1527, 1533, 1538, 1544, 1550, 1556,
	      1562, 1567, 1573, 1579, 1585, 1591, 1596, 1602, 1608, 1614, 1620, 1625, 1631, 1637, 1643, 1648, 1654, 1660,
	      1666, 1671, 1677, 1683, 1689, 1694, 1700, 1706, 1711, 1717, 1723, 1729, 1734, 1740, 1746, 1751, 1757, 1763,
	      1768, 1774, 1780, 1785, 1791, 1797, 1802, 1808, 1813, 1819, 1825, 1830, 1836, 1842, 1847, 1853, 1858, 1864,
	      1870, 1875, 1881, 1886, 1892, 1898, 1903, 1909, 1914, 1920, 1925, 1931, 1936, 1942, 1947, 1953, 1958, 1964,
	      1970, 1975, 1981, 1986, 1992, 1997, 2002, 2008, 2013, 2019, 2024, 2030, 2035, 2041, 2046, 2052, 2057, 2062,
	      2068, 2073, 2079, 2084, 2090, 2095, 2100, 2106, 2111, 2117, 2122, 2127, 2133, 2138, 2143, 2149, 2154, 2159,
	      2165, 2170, 2175, 2181, 2186, 2191, 2197, 2202, 2207, 2213, 2218, 2223, 2228, 2234, 2239, 2244, 2249, 2255,
	      2260, 2265, 2270, 2276, 2281, 2286, 2291, 2296, 2302, 2307, 2312, 2317, 2322, 2328, 2333, 2338, 2343, 2348,
	      2353, 2359, 2364, 2369, 2374, 2379, 2384, 2389, 2394, 2399, 2405, 2410, 2415, 2420, 2425, 2430, 2435, 2440,
	      2445, 2450, 2455, 2460, 2465, 2470, 2475, 2480, 2485, 2490, 2495, 2500, 2505, 2510, 2515, 2520, 2525, 2530,
	      2535, 2540, 2545, 2550, 2555, 2559, 2564, 2569, 2574, 2579, 2584, 2589, 2594, 2598, 2603, 2608, 2613, 2618,
	      2623, 2628, 2632, 2637, 2642, 2647, 2652, 2656, 2661, 2666, 2671, 2675, 2680, 2685, 2690, 2694, 2699, 2704,
	      2709, 2713, 2718, 2723, 2727, 2732, 2737, 2741, 2746, 2751, 2755, 2760, 2765, 2769, 2774, 2779, 2783, 2788,
	      2792, 2797, 2802, 2806, 2811, 2815, 2820, 2824, 2829, 2833, 2838, 2843, 2847, 2852, 2856, 2861, 2865, 2870,
	      2874, 2878, 2883, 2887, 2892, 2896, 2901, 2905, 2910, 2914, 2918, 2923, 2927, 2932, 2936, 2940, 2945, 2949,
	      2953, 2958, 2962, 2967, 2971, 2975, 2979, 2984, 2988, 2992, 2997, 3001, 3005, 3009, 3014, 3018, 3022, 3026,
	      3031, 3035, 3039, 3043, 3048, 3052, 3056, 3060, 3064, 3068, 3073, 3077, 3081, 3085, 3089, 3093, 3097, 3102,
	      3106, 3110, 3114, 3118, 3122, 3126, 3130, 3134, 3138, 3142, 3146, 3150, 3154, 3158, 3162, 3166, 3170, 3174,
	      3178, 3182, 3186, 3190, 3194, 3198, 3202, 3206, 3210, 3214, 3217, 3221, 3225, 3229, 3233, 3237, 3241, 3244,
	      3248, 3252, 3256, 3260, 3264, 3267, 3271, 3275, 3279, 3282, 3286, 3290, 3294, 3297, 3301, 3305, 3309, 3312,
	      3316, 3320, 3323, 3327, 3331, 3334, 3338, 3342, 3345, 3349, 3352, 3356, 3360, 3363, 3367, 3370, 3374, 3378,
	      3381, 3385, 3388, 3392, 3395, 3399, 3402, 3406, 3409, 3413, 3416, 3420, 3423, 3426, 3430, 3433, 3437, 3440,
	      3444, 3447, 3450, 3454, 3457, 3461, 3464, 3467, 3471, 3474, 3477, 3481, 3484, 3487, 3490, 3494, 3497, 3500,
	      3504, 3507, 3510, 3513, 3516, 3520, 3523, 3526, 3529, 3532, 3536, 3539, 3542, 3545, 3548, 3551, 3555, 3558,
	      3561, 3564, 3567, 3570, 3573, 3576, 3579, 3582, 3585, 3588, 3591, 3594, 3597, 3600, 3603, 3606, 3609, 3612,
	      3615, 3618, 3621, 3624, 3627, 3630, 3633, 3636, 3639, 3642, 3644, 3647, 3650, 3653, 3656, 3659, 3661, 3664,
	      3667, 3670, 3673, 3675, 3678, 3681, 3684, 3686, 3689, 3692, 3695, 3697, 3700, 3703, 3705, 3708, 3711, 3713,
	      3716, 3719, 3721, 3724, 3727, 3729, 3732, 3734, 3737, 3739, 3742, 3745, 3747, 3750, 3752, 3755, 3757, 3760,
	      3762, 3765, 3767, 3770, 3772, 3775, 3777, 3779, 3782, 3784, 3787, 3789, 3791, 3794, 3796, 3798, 3801, 3803,
	      3805, 3808, 3810, 3812, 3815, 3817, 3819, 3822, 3824, 3826, 3828, 3831, 3833, 3835, 3837, 3839, 3842, 3844,
	      3846, 3848, 3850, 3852, 3854, 3857, 3859, 3861, 3863, 3865, 3867, 3869, 3871, 3873, 3875, 3877, 3879, 3881,
	      3883, 3885, 3887, 3889, 3891, 3893, 3895, 3897, 3899, 3901, 3903, 3905, 3907, 3909, 3910, 3912, 3914, 3916,
	      3918, 3920, 3921, 3923, 3925, 3927, 3929, 3930, 3932, 3934, 3936, 3937, 3939, 3941, 3943, 3944, 3946, 3948,
	      3949, 3951, 3953, 3954, 3956, 3958, 3959, 3961, 3962, 3964, 3965, 3967, 3969, 3970, 3972, 3973, 3975, 3976,
	      3978, 3979, 3981, 3982, 3984, 3985, 3987, 3988, 3989, 3991, 3992, 3994, 3995, 3996, 3998, 3999, 4001, 4002,
	      4003, 4005, 4006, 4007, 4008, 4010, 4011, 4012, 4014, 4015, 4016, 4017, 4019, 4020, 4021, 4022, 4023, 4024,
	      4026, 4027, 4028, 4029, 4030, 4031, 4032, 4034, 4035, 4036, 4037, 4038, 4039, 4040, 4041, 4042, 4043, 4044,
	      4045, 4046, 4047, 4048, 4049, 4050, 4051, 4052, 4053, 4053, 4054, 4055, 4056, 4057, 4058, 4059, 4060, 4060,
	      4061, 4062, 4063, 4064, 4064, 4065, 4066, 4067, 4067, 4068, 4069, 4070, 4070, 4071, 4072, 4072, 4073, 4074,
	      4074, 4075, 4076, 4076, 4077, 4077, 4078, 4079, 4079, 4080, 4080, 4081, 4081, 4082, 4082, 4083, 4083, 4084,
	      4084, 4085, 4085, 4086, 4086, 4087, 4087, 4088, 4088, 4088, 4089, 4089, 4089, 4090, 4090, 4090, 4091, 4091,
	      4091, 4092, 4092, 4092, 4092, 4093, 4093, 4093, 4093, 4094, 4094, 4094, 4094, 4094, 4095, 4095, 4095, 4095,
	      4095, 4095, 4095, 4096, 4096, 4096, 4096, 4096, 4096, 4096, 4096, 4096, 4096, 4096
			};
	
// Alternative computing at runtime
//	private static final short[] SINE_QUARTER = new short[1 + (1 << (Q - 2))];
//	 
//	static {
//	      float dn = 2.0f * (float)Math.PI * TO_FLOAT;
//	      for (int i = 0; i < SINE_QUARTER.length; i++) {
//	          SINE_QUARTER[i] = (short)(Math.sin(dn * i) * TO_Q + 0.5f);
//	      }
//	   }
	
	public static int sin(int angle) {
		angle &= (Q_PI - 1);
		if (angle >= Q_PI_THREE_QUARTERS) {
			return -SINE_QUARTER[Q_PI - angle];
		}
		if (angle >= Q_PI_HALF) {
			return -SINE_QUARTER[angle - Q_PI_HALF];
		}
		if (angle >= Q_PI_QUARTER) {
				return SINE_QUARTER[Q_PI_HALF - angle];
		}
		return SINE_QUARTER[angle];
		
	}
	
	public static int getPercentInt(int value, int percent) {
		return (value / 100) * percent;
    }
	
	public static float getPercentFloat(float value, float percent) {
		return (value / 100) * percent;
	}
	
	public static int atan2_Q15(int y, int x) {
		if(x == 0) {
			// Особые случаи: x = 0
			return y >= 0 ? 16384 : 49152; // 90 или 270 градусов в формате Q15
        }
		
		int absX = Math.abs(x);
        int absY = Math.abs(y);
		
		if(absX > absY) {
			int z = (y << 12) / x;
            int angle = AEMath.atan(z);
			if(x < 0) {
				if(y >= 0) {
                    angle += 32768; // 180 градусов в формате Q15
                } else {
                    angle -= 32768;
                }
            }
            return angle;
        } else {
            int z = (x << 12) / y;
            int angle = 16384 - AEMath.atan(z); // 90 градусов в формате Q15
            if(y < 0) {
                angle -= 32768; // 180 градусов в формате Q15
            }
            return angle;
        }
    }
	
	public static int atan(int x) {
	   // Approximation of arctan(x) for integers
	   // Use the Taylor series for arctan(x)
	   // arctan(x) ≈ x - x^3/3 + x^5/5 - x^7/7 + ...
	   long x2 = (long)x * x;
	   long x3 = x2 * x;
	   long x5 = x3 * x2;
	   long x7 = x5 * x2;
	   
	   int result = (int)(x - x3 / 3 + x5 / 5 - x7 / 7);
	   
	   // Масштабируем результат для формата Q15
	   return (result * 32768) / 100000;
	}

	public static int cos(int angle) {
		angle -= Q_PI_QUARTER;
		angle &= (Q_PI - 1);
		if (angle >= Q_PI_THREE_QUARTERS) {
			return SINE_QUARTER[Q_PI - angle];
		}
		if (angle >= Q_PI_HALF) {
			return SINE_QUARTER[angle - Q_PI_HALF];
		}
		if (angle >= Q_PI_QUARTER) {
			return -SINE_QUARTER[Q_PI_HALF - angle];
		}
		return -SINE_QUARTER[angle];
		
	}

   public static int invSin(int n) {
      int n2 = Q_PI - (n * n >> Q);
      if (n2 <= 0) {
          return AEMath.atan2(n, 0);
      }
      return AEMath.atan2(n, AEMath.sqrt(n2));
  }

	public static int atan2(final int y, int x) {
		final int var2 = abs(y) + 1;
		if (x >= 0) {
			x = (x - var2 << Q) / (x + var2);
			if (y < 0) {
				return -(Q_EIGHTH - (x * Q_EIGHTH >> Q));
			}
			return Q_EIGHTH - (x * Q_EIGHTH >> Q);
		}
		x = (x + var2 << Q) / (var2 - x);
		if (y < 0) {
			return -(Q_THREE_EIGHTHS - (x * Q_EIGHTH >> Q));
		}
		return Q_THREE_EIGHTHS - (x * Q_EIGHTH >> Q);
	}

	public static int invSqrt(int x) {
		final int x_half = x >> 1;
		x = Float.floatToIntBits(x * TO_FLOAT);       // evil floating point bit level hacking
		x = 0x5F375A86 - (x >> 1);                         // what the fuck?  
		x = (int)(Float.intBitsToFloat(x) * TO_Q);
		x = x * (Q_THREE_HALFS - ((x_half * x >> Q) * x >> Q)) >> Q; // 1st iteration
		return abs(x);
	}

	public static int sqrt(final long x) {
		if (x == 0L) {
			return 0;
		}
		int n = Integer.MAX_VALUE;
		long y = Integer.MAX_VALUE;

		for(int i = 30; i >= 0; --i) {
			n >>= 1;
			long temp = x - (y * y >> Q);
			if (temp > 0L) {
				y += n;
			} else {
				if (temp >= 0L) {
					return (int)y;
				}
				y -= n;
			}
		}

		return (int)y;
	}

	public static final int max(final int a, final int b) {
		return a >= b ? a : b;
	}

	public static final float max(final float a, final float b) {
		return a >= b ? a : b;
	}

	public static final int min(final int a, final int b) {
		return a <= b ? a : b;
	}

	public static final float min(final float a, final float b) { 
		return a <= b ? a : b;
	}

	public static final int abs(final int x) {
		return x < 0 ? -x : x;
	}
}