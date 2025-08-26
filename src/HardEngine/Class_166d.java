package HardEngine;

import javax.microedition.io.ConnectionNotFoundException;

public final class Class_166d extends Thread {

   private Class_b0 var_25;
   private byte[] var_19e;
   private boolean var_1db;
   private byte[] var_24a;
   private int var_3cd;
   private Class_86b var_3d7;
   private Class_2b0 var_3f0;
   private boolean var_4a1;
   private int var_582;
   private static Class_ca6 var_5fe;


   public Class_166d(Class_2b0 var1, Class_86b var2, Class_b0 var3, int var4, byte[] var5, int var6) {
      this(var1, var2, var3, var4, var5, false, (byte[])null, var6);
   }

   public Class_166d(Class_2b0 var1, Class_86b var2, Class_b0 var3, int var4, byte[] var5, boolean var6, byte[] var7, int var8) {
      this.var_24a = null;
      this.var_4a1 = false;
      this.var_582 = -1;
      this.var_3d7 = var2;
      this.var_25 = var3;
      this.var_3f0 = var1;
      this.var_19e = var5;
      this.var_1db = var6;
      this.var_24a = var7;
      this.var_3cd = var4;
      this.var_582 = var8;
   }

   public final Class_166d sub_69() {
      return new Class_166d(this.var_3f0, this.var_3d7, this.var_25, this.var_3cd, this.var_19e, this.var_1db, this.var_24a, this.var_582);
   }

   public final void sub_25b() {
      if(!this.var_4a1) {
         this.var_25.sub_121();
      }

      this.var_4a1 = true;
   }

   public final int sub_2a4() {
      return this.var_3cd;
   }

   public final void run() {
      boolean var1 = false;
      boolean var2 = false;
      byte[] var3 = null;
      boolean var18 = false;

      int var32;
      label174: {
         label178: {
            label179: {
               label180: {
                  label170: {
                     label181: {
                        try {
                           var18 = true;
                           this.var_3d7.sub_167(this.var_3cd);
                           byte[][] var4;
                           if(this.var_1db) {
                              var4 = this.var_25.sub_10d(this.var_3cd, this.var_19e, this.var_24a);
                           } else {
                              var4 = this.var_25.sub_b0(this.var_3cd, this.var_19e, this.var_24a);
                           }

                           var32 = Integer.parseInt(new String(var4[0]));
                           var3 = var4[1];
                           var18 = false;
                           break label178;
                        } catch (ConnectionNotFoundException var26) {
                           var18 = false;
                           break label170;
                        } catch (Class_808 var27) {
                           var18 = false;
                           break label180;
                        } catch (SecurityException var28) {
                           var18 = false;
                           break label179;
                        } catch (Class_674 var29) {
                           var18 = false;
                        } catch (Exception var30) {
                           var30.printStackTrace();
                           var32 = 900;
                           var2 = true;
                           var18 = false;
                           break label181;
                        } finally {
                           if(var18) {
                              try {
                                 this.var_25.sub_150();
                              } catch (Exception var19) {
                                 ;
                              }

                           }
                        }

                        var32 = 902;
                        var2 = true;

                        try {
                           this.var_25.sub_150();
                        } catch (Exception var21) {
                           ;
                        }
                        break label174;
                     }

                     try {
                        this.var_25.sub_150();
                     } catch (Exception var20) {
                        ;
                     }
                     break label174;
                  }

                  var32 = 903;
                  var2 = true;

                  try {
                     this.var_25.sub_150();
                  } catch (Exception var24) {
                     ;
                  }
                  break label174;
               }

               var32 = 903;
               var2 = true;

               try {
                  this.var_25.sub_150();
               } catch (Exception var23) {
                  ;
               }
               break label174;
            }

            var32 = 904;
            var2 = true;

            try {
               this.var_25.sub_150();
            } catch (Exception var22) {
               ;
            }
            break label174;
         }

         try {
            this.var_25.sub_150();
         } catch (Exception var25) {
            ;
         }
      }

      if(!this.var_4a1) {
         if(!var2) {
            this.var_3d7.sub_192();
         }

         this.var_3d7.sub_61(this.var_3cd, var32, var3, this.var_582);
      }

      this.var_25 = null;
      this.var_3d7 = null;
      (var_5fe = new Class_ca6(this, this, this.var_3f0)).start();
      this.var_3f0 = null;
   }

   public static void sub_2c3() {
      var_5fe = null;
   }
}
