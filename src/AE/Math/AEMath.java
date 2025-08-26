package AE.Math;


public final class AEMath {

   public static final short[] SINE_QUARTER = new short[]{(short)0, (short)6, (short)13, (short)19, (short)25, (short)31, (short)38, (short)44, (short)50, (short)57, (short)63, (short)69, (short)75, (short)82, (short)88, (short)94, (short)101, (short)107, (short)113, (short)119, (short)126, (short)132, (short)138, (short)144, (short)151, (short)157, (short)163, (short)170, (short)176, (short)182, (short)188, (short)195, (short)201, (short)207, (short)214, (short)220, (short)226, (short)232, (short)239, (short)245, (short)251, (short)257, (short)264, (short)270, (short)276, (short)283, (short)289, (short)295, (short)301, (short)308, (short)314, (short)320, (short)326, (short)333, (short)339, (short)345, (short)351, (short)358, (short)364, (short)370, (short)376, (short)383, (short)389, (short)395, (short)401, (short)408, (short)414, (short)420, (short)426, (short)433, (short)439, (short)445, (short)451, (short)458, (short)464, (short)470, (short)476, (short)483, (short)489, (short)495, (short)501, (short)508, (short)514, (short)520, (short)526, (short)533, (short)539, (short)545, (short)551, (short)557, (short)564, (short)570, (short)576, (short)582, (short)589, (short)595, (short)601, (short)607, (short)613, (short)620, (short)626, (short)632, (short)638, (short)644, (short)651, (short)657, (short)663, (short)669, (short)675, (short)682, (short)688, (short)694, (short)700, (short)706, (short)713, (short)719, (short)725, (short)731, (short)737, (short)744, (short)750, (short)756, (short)762, (short)768, (short)774, (short)781, (short)787, (short)793, (short)799, (short)805, (short)811, (short)818, (short)824, (short)830, (short)836, (short)842, (short)848, (short)854, (short)861, (short)867, (short)873, (short)879, (short)885, (short)891, (short)897, (short)904, (short)910, (short)916, (short)922, (short)928, (short)934, (short)940, (short)946, (short)953, (short)959, (short)965, (short)971, (short)977, (short)983, (short)989, (short)995, (short)1001, (short)1007, (short)1014, (short)1020, (short)1026, (short)1032, (short)1038, (short)1044, (short)1050, (short)1056, (short)1062, (short)1068, (short)1074, (short)1080, (short)1086, (short)1092, (short)1099, (short)1105, (short)1111, (short)1117, (short)1123, (short)1129, (short)1135, (short)1141, (short)1147, (short)1153, (short)1159, (short)1165, (short)1171, (short)1177, (short)1183, (short)1189, (short)1195, (short)1201, (short)1207, (short)1213, (short)1219, (short)1225, (short)1231, (short)1237, (short)1243, (short)1249, (short)1255, (short)1261, (short)1267, (short)1273, (short)1279, (short)1285, (short)1291, (short)1297, (short)1303, (short)1309, (short)1315, (short)1321, (short)1327, (short)1332, (short)1338, (short)1344, (short)1350, (short)1356, (short)1362, (short)1368, (short)1374, (short)1380, (short)1386, (short)1392, (short)1398, (short)1404, (short)1409, (short)1415, (short)1421, (short)1427, (short)1433, (short)1439, (short)1445, (short)1451, (short)1457, (short)1462, (short)1468, (short)1474, (short)1480, (short)1486, (short)1492, (short)1498, (short)1503, (short)1509, (short)1515, (short)1521, (short)1527, (short)1533, (short)1538, (short)1544, (short)1550, (short)1556, (short)1562, (short)1567, (short)1573, (short)1579, (short)1585, (short)1591, (short)1596, (short)1602, (short)1608, (short)1614, (short)1620, (short)1625, (short)1631, (short)1637, (short)1643, (short)1648, (short)1654, (short)1660, (short)1666, (short)1671, (short)1677, (short)1683, (short)1689, (short)1694, (short)1700, (short)1706, (short)1711, (short)1717, (short)1723, (short)1729, (short)1734, (short)1740, (short)1746, (short)1751, (short)1757, (short)1763, (short)1768, (short)1774, (short)1780, (short)1785, (short)1791, (short)1797, (short)1802, (short)1808, (short)1813, (short)1819, (short)1825, (short)1830, (short)1836, (short)1842, (short)1847, (short)1853, (short)1858, (short)1864, (short)1870, (short)1875, (short)1881, (short)1886, (short)1892, (short)1898, (short)1903, (short)1909, (short)1914, (short)1920, (short)1925, (short)1931, (short)1936, (short)1942, (short)1947, (short)1953, (short)1958, (short)1964, (short)1970, (short)1975, (short)1981, (short)1986, (short)1992, (short)1997, (short)2002, (short)2008, (short)2013, (short)2019, (short)2024, (short)2030, (short)2035, (short)2041, (short)2046, (short)2052, (short)2057, (short)2062, (short)2068, (short)2073, (short)2079, (short)2084, (short)2090, (short)2095, (short)2100, (short)2106, (short)2111, (short)2117, (short)2122, (short)2127, (short)2133, (short)2138, (short)2143, (short)2149, (short)2154, (short)2159, (short)2165, (short)2170, (short)2175, (short)2181, (short)2186, (short)2191, (short)2197, (short)2202, (short)2207, (short)2213, (short)2218, (short)2223, (short)2228, (short)2234, (short)2239, (short)2244, (short)2249, (short)2255, (short)2260, (short)2265, (short)2270, (short)2276, (short)2281, (short)2286, (short)2291, (short)2296, (short)2302, (short)2307, (short)2312, (short)2317, (short)2322, (short)2328, (short)2333, (short)2338, (short)2343, (short)2348, (short)2353, (short)2359, (short)2364, (short)2369, (short)2374, (short)2379, (short)2384, (short)2389, (short)2394, (short)2399, (short)2405, (short)2410, (short)2415, (short)2420, (short)2425, (short)2430, (short)2435, (short)2440, (short)2445, (short)2450, (short)2455, (short)2460, (short)2465, (short)2470, (short)2475, (short)2480, (short)2485, (short)2490, (short)2495, (short)2500, (short)2505, (short)2510, (short)2515, (short)2520, (short)2525, (short)2530, (short)2535, (short)2540, (short)2545, (short)2550, (short)2555, (short)2559, (short)2564, (short)2569, (short)2574, (short)2579, (short)2584, (short)2589, (short)2594, (short)2598, (short)2603, (short)2608, (short)2613, (short)2618, (short)2623, (short)2628, (short)2632, (short)2637, (short)2642, (short)2647, (short)2652, (short)2656, (short)2661, (short)2666, (short)2671, (short)2675, (short)2680, (short)2685, (short)2690, (short)2694, (short)2699, (short)2704, (short)2709, (short)2713, (short)2718, (short)2723, (short)2727, (short)2732, (short)2737, (short)2741, (short)2746, (short)2751, (short)2755, (short)2760, (short)2765, (short)2769, (short)2774, (short)2779, (short)2783, (short)2788, (short)2792, (short)2797, (short)2802, (short)2806, (short)2811, (short)2815, (short)2820, (short)2824, (short)2829, (short)2833, (short)2838, (short)2843, (short)2847, (short)2852, (short)2856, (short)2861, (short)2865, (short)2870, (short)2874, (short)2878, (short)2883, (short)2887, (short)2892, (short)2896, (short)2901, (short)2905, (short)2910, (short)2914, (short)2918, (short)2923, (short)2927, (short)2932, (short)2936, (short)2940, (short)2945, (short)2949, (short)2953, (short)2958, (short)2962, (short)2967, (short)2971, (short)2975, (short)2979, (short)2984, (short)2988, (short)2992, (short)2997, (short)3001, (short)3005, (short)3009, (short)3014, (short)3018, (short)3022, (short)3026, (short)3031, (short)3035, (short)3039, (short)3043, (short)3048, (short)3052, (short)3056, (short)3060, (short)3064, (short)3068, (short)3073, (short)3077, (short)3081, (short)3085, (short)3089, (short)3093, (short)3097, (short)3102, (short)3106, (short)3110, (short)3114, (short)3118, (short)3122, (short)3126, (short)3130, (short)3134, (short)3138, (short)3142, (short)3146, (short)3150, (short)3154, (short)3158, (short)3162, (short)3166, (short)3170, (short)3174, (short)3178, (short)3182, (short)3186, (short)3190, (short)3194, (short)3198, (short)3202, (short)3206, (short)3210, (short)3214, (short)3217, (short)3221, (short)3225, (short)3229, (short)3233, (short)3237, (short)3241, (short)3244, (short)3248, (short)3252, (short)3256, (short)3260, (short)3264, (short)3267, (short)3271, (short)3275, (short)3279, (short)3282, (short)3286, (short)3290, (short)3294, (short)3297, (short)3301, (short)3305, (short)3309, (short)3312, (short)3316, (short)3320, (short)3323, (short)3327, (short)3331, (short)3334, (short)3338, (short)3342, (short)3345, (short)3349, (short)3352, (short)3356, (short)3360, (short)3363, (short)3367, (short)3370, (short)3374, (short)3378, (short)3381, (short)3385, (short)3388, (short)3392, (short)3395, (short)3399, (short)3402, (short)3406, (short)3409, (short)3413, (short)3416, (short)3420, (short)3423, (short)3426, (short)3430, (short)3433, (short)3437, (short)3440, (short)3444, (short)3447, (short)3450, (short)3454, (short)3457, (short)3461, (short)3464, (short)3467, (short)3471, (short)3474, (short)3477, (short)3481, (short)3484, (short)3487, (short)3490, (short)3494, (short)3497, (short)3500, (short)3504, (short)3507, (short)3510, (short)3513, (short)3516, (short)3520, (short)3523, (short)3526, (short)3529, (short)3532, (short)3536, (short)3539, (short)3542, (short)3545, (short)3548, (short)3551, (short)3555, (short)3558, (short)3561, (short)3564, (short)3567, (short)3570, (short)3573, (short)3576, (short)3579, (short)3582, (short)3585, (short)3588, (short)3591, (short)3594, (short)3597, (short)3600, (short)3603, (short)3606, (short)3609, (short)3612, (short)3615, (short)3618, (short)3621, (short)3624, (short)3627, (short)3630, (short)3633, (short)3636, (short)3639, (short)3642, (short)3644, (short)3647, (short)3650, (short)3653, (short)3656, (short)3659, (short)3661, (short)3664, (short)3667, (short)3670, (short)3673, (short)3675, (short)3678, (short)3681, (short)3684, (short)3686, (short)3689, (short)3692, (short)3695, (short)3697, (short)3700, (short)3703, (short)3705, (short)3708, (short)3711, (short)3713, (short)3716, (short)3719, (short)3721, (short)3724, (short)3727, (short)3729, (short)3732, (short)3734, (short)3737, (short)3739, (short)3742, (short)3745, (short)3747, (short)3750, (short)3752, (short)3755, (short)3757, (short)3760, (short)3762, (short)3765, (short)3767, (short)3770, (short)3772, (short)3775, (short)3777, (short)3779, (short)3782, (short)3784, (short)3787, (short)3789, (short)3791, (short)3794, (short)3796, (short)3798, (short)3801, (short)3803, (short)3805, (short)3808, (short)3810, (short)3812, (short)3815, (short)3817, (short)3819, (short)3822, (short)3824, (short)3826, (short)3828, (short)3831, (short)3833, (short)3835, (short)3837, (short)3839, (short)3842, (short)3844, (short)3846, (short)3848, (short)3850, (short)3852, (short)3854, (short)3857, (short)3859, (short)3861, (short)3863, (short)3865, (short)3867, (short)3869, (short)3871, (short)3873, (short)3875, (short)3877, (short)3879, (short)3881, (short)3883, (short)3885, (short)3887, (short)3889, (short)3891, (short)3893, (short)3895, (short)3897, (short)3899, (short)3901, (short)3903, (short)3905, (short)3907, (short)3909, (short)3910, (short)3912, (short)3914, (short)3916, (short)3918, (short)3920, (short)3921, (short)3923, (short)3925, (short)3927, (short)3929, (short)3930, (short)3932, (short)3934, (short)3936, (short)3937, (short)3939, (short)3941, (short)3943, (short)3944, (short)3946, (short)3948, (short)3949, (short)3951, (short)3953, (short)3954, (short)3956, (short)3958, (short)3959, (short)3961, (short)3962, (short)3964, (short)3965, (short)3967, (short)3969, (short)3970, (short)3972, (short)3973, (short)3975, (short)3976, (short)3978, (short)3979, (short)3981, (short)3982, (short)3984, (short)3985, (short)3987, (short)3988, (short)3989, (short)3991, (short)3992, (short)3994, (short)3995, (short)3996, (short)3998, (short)3999, (short)4001, (short)4002, (short)4003, (short)4005, (short)4006, (short)4007, (short)4008, (short)4010, (short)4011, (short)4012, (short)4014, (short)4015, (short)4016, (short)4017, (short)4019, (short)4020, (short)4021, (short)4022, (short)4023, (short)4024, (short)4026, (short)4027, (short)4028, (short)4029, (short)4030, (short)4031, (short)4032, (short)4034, (short)4035, (short)4036, (short)4037, (short)4038, (short)4039, (short)4040, (short)4041, (short)4042, (short)4043, (short)4044, (short)4045, (short)4046, (short)4047, (short)4048, (short)4049, (short)4050, (short)4051, (short)4052, (short)4053, (short)4053, (short)4054, (short)4055, (short)4056, (short)4057, (short)4058, (short)4059, (short)4060, (short)4060, (short)4061, (short)4062, (short)4063, (short)4064, (short)4064, (short)4065, (short)4066, (short)4067, (short)4067, (short)4068, (short)4069, (short)4070, (short)4070, (short)4071, (short)4072, (short)4072, (short)4073, (short)4074, (short)4074, (short)4075, (short)4076, (short)4076, (short)4077, (short)4077, (short)4078, (short)4079, (short)4079, (short)4080, (short)4080, (short)4081, (short)4081, (short)4082, (short)4082, (short)4083, (short)4083, (short)4084, (short)4084, (short)4085, (short)4085, (short)4086, (short)4086, (short)4087, (short)4087, (short)4088, (short)4088, (short)4088, (short)4089, (short)4089, (short)4089, (short)4090, (short)4090, (short)4090, (short)4091, (short)4091, (short)4091, (short)4092, (short)4092, (short)4092, (short)4092, (short)4093, (short)4093, (short)4093, (short)4093, (short)4094, (short)4094, (short)4094, (short)4094, (short)4094, (short)4095, (short)4095, (short)4095, (short)4095, (short)4095, (short)4095, (short)4095, (short)4096, (short)4096, (short)4096, (short)4096, (short)4096, (short)4096, (short)4096, (short)4096, (short)4096, (short)4096, (short)4096};


   public static int sin(int angle) {
      return (angle &= 4095) >= 3072?-SINE_QUARTER[4096 - angle]:(angle >= 2048?-SINE_QUARTER[angle - 2048]:(angle >= 1024?SINE_QUARTER[2048 - angle]:SINE_QUARTER[angle]));
   }
   
    public static int getPercentInt(int value, int percent) {
		return (value / 100) * percent;
    }
	
	public static float getPercentFloat(float value, float percent) {
		return (value / 100) * percent;
	}

   public static int cos(int angle) {
      angle -= 1024;
      return (angle &= 4095) >= 3072?SINE_QUARTER[4096 - angle]:(angle >= 2048?SINE_QUARTER[angle - 2048]:(angle >= 1024?-SINE_QUARTER[2048 - angle]:-SINE_QUARTER[angle]));
   }

   public static int invSin(int value) {
      int var1;
      return (var1 = 4096 - (value * value >> 12)) <= 0?atan2(value, 0):atan2(value, sqrt((long)var1));
   }

   public static int atan2(int value, int helper) {
      int var2 = abs(value) + 1;
      if(helper >= 0) {
         helper = (helper - var2 << 12) / (helper + var2);
         return value < 0?-(512 - (helper * 512 >> 12)):512 - (helper * 512 >> 12);
      } else {
         helper = (helper + var2 << 12) / (var2 - helper);
         return value < 0?-(1536 - (helper * 512 >> 12)):1536 - (helper * 512 >> 12);
      }
   }

   public static int invSqrt(int value) {
      int var1 = value >> 1;
      value = Float.floatToIntBits((float)value * 2.4414062E-4F);
      return abs((value = (int)(Float.intBitsToFloat(value = 1597463174 - (value >> 1)) * 4096.0F)) * (6144 - ((var1 * value >> 12) * value >> 12)) >> 12);
   }

   public static int sqrt(long value) {
      if(value == 0L) {
         return 0;
      } else {
         int var2 = Integer.MAX_VALUE;
         long var4 = (long)Integer.MAX_VALUE;

         for(int var3 = 30; var3 >= 0; --var3) {
            var2 >>= 1;
            long var7;
            if((var7 = value - (var4 * var4 >> 12)) > 0L) {
               var4 += (long)var2;
            } else {
               if(var7 >= 0L) {
                  return (int)var4;
               }

               var4 -= (long)var2;
            }
         }

         return (int)var4;
      }
   }
   
   // тангенс
   public static int tan(int angle) {
	   return div(sin(angle), cos(angle));
   }
   
   // Котангенс
   public static int cot(int angle) {
      return div(cos(angle), sin(angle));
   }
   
   // Натуральный логарифм (приближенное значение)
   public static int log(int value) {
      // Приближенное значение натурального логарифма
      // Using the Taylor series for ln(x)
      int result = 0;
      int term = value - 1024;
      int divisor = 1;

      while (term > 0) {
         result += div(term, divisor);
         term = mul(term, value - 1024);
         divisor++;
      }

      return result;
   }
   
   // Экспоненциальная функция (приближенное значение)
   public static int exp(int value) {
      // Using the Taylor series for exp(x)
      int result = 1024;
      int term = 1024;
      int divisor = 1;

      for (int i = 1; i < 10; i++) {
         term = mul(term, value);
         result += div(term, divisor);
         divisor++;
      }

      return result;
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

   public static final int max(int a, int b) {
      return a >= b?a:b;
   }

   public static final float max(float a, float b) {
      return a >= b?a:b;
   }

   public static final int min(int a, int b) {
      return a <= b?a:b;
   }

   public static final float min(float value, float max) {
      return value <= max?value:max;
   }

   public static final int abs(int value) {
      return value < 0?-value:value;
   }
   
   // Гиперболический синус
   public static int sinh(int value) {
      return div(exp(value) - exp(-value), 2);
   }

   // Гиперболический косинус
   public static int cosh(int value) {
      return div(exp(value) + exp(-value), 2);
   }

   // Гиперболический тангенс
   public static int tanh(int value) {
      return div(sinh(value), cosh(value));
   }

   // Округление до ближайшего целого числа
   public static int round(int value) {
      return (value + 128) & ~255;
   }

   // Перевод градусов в радианы
   public static int toRadians(int degrees) {
      return mul(degrees, 114); // 114 ≈ π / 180 * 2048
   }

   // Перевод радиан в градусы
   public static int toDegrees(int radians) {
      return mul(radians, 16); // 16 ≈ 180 / π * 2048
   }

   // Вспомогательные функции для умножения и деления
   public static int mul(int a, int b) {
      return (int)(((long)a * (long)b) >> 12);
   }

   public static int div(int a, int b) {
      return (int)(((long)a << 12) / b);
   }

}
