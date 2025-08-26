package AE.Math;


public final class Matrix {

   private short rotationOrder;
   private int scaleX;
   private int scaleY;
   private int scaleZ;
   private int rightX;
   private int rightY;
   private int rightZ;
   private int upX;
   private int upY;
   private int upZ;
   private int dirX;
   private int dirY;
   private int dirZ;
   private int positionX;
   private int positionY;
   private int positionZ;
   private int eulerX;
   private int eulerY;
   private int eulerZ;
   private boolean isDirty;
   private static AEVector3D temp = new AEVector3D();


   public Matrix() {
      this.identity();
      this.rotationOrder = 0;
   }

   public Matrix(Matrix paramMatrix) {
      this.scaleX = paramMatrix.scaleX;
      this.scaleY = paramMatrix.scaleY;
      this.scaleZ = paramMatrix.scaleZ;
      this.rightX = paramMatrix.rightX;
      this.rightY = paramMatrix.rightY;
      this.rightZ = paramMatrix.rightZ;
      this.upX = paramMatrix.upX;
      this.upY = paramMatrix.upY;
      this.upZ = paramMatrix.upZ;
      this.dirX = paramMatrix.dirX;
      this.dirY = paramMatrix.dirY;
      this.dirZ = paramMatrix.dirZ;
      this.positionX = paramMatrix.positionX;
      this.positionY = paramMatrix.positionY;
      this.positionZ = paramMatrix.positionZ;
      this.eulerX = paramMatrix.eulerX;
      this.eulerY = paramMatrix.eulerY;
      this.eulerZ = paramMatrix.eulerZ;
      this.isDirty = paramMatrix.isDirty;
   }

   public final void identity() {
      this.scaleX = this.scaleY = this.scaleZ = 4096;
      this.rightX = 4096;
      this.upX = 0;
      this.dirX = 0;
      this.rightY = 0;
      this.upY = 4096;
      this.dirY = 0;
      this.rightZ = 0;
      this.upZ = 0;
      this.dirZ = 4096;
      this.positionX = this.positionY = this.positionZ = 0;
      this.eulerX = this.eulerY = this.eulerZ = 0;
      this.isDirty = false;
   }

   public final void set(Matrix paramMatrix) {
      this.scaleX = paramMatrix.scaleX;
      this.scaleY = paramMatrix.scaleY;
      this.scaleZ = paramMatrix.scaleZ;
      this.rightX = paramMatrix.rightX;
      this.rightY = paramMatrix.rightY;
      this.rightZ = paramMatrix.rightZ;
      this.upX = paramMatrix.upX;
      this.upY = paramMatrix.upY;
      this.upZ = paramMatrix.upZ;
      this.dirX = paramMatrix.dirX;
      this.dirY = paramMatrix.dirY;
      this.dirZ = paramMatrix.dirZ;
      this.positionX = paramMatrix.positionX;
      this.positionY = paramMatrix.positionY;
      this.positionZ = paramMatrix.positionZ;
      this.eulerX = paramMatrix.eulerX;
      this.eulerY = paramMatrix.eulerY;
      this.eulerZ = paramMatrix.eulerZ;
      this.isDirty = paramMatrix.isDirty;
   }

   public final void multiply(Matrix paramMatrix) {
      int var2 = this.rightX;
      int var3 = this.upX;
      int var4 = this.rightY;
      int var5 = this.upY;
      int var6 = this.rightZ;
      int var7 = this.upZ;
      this.positionX += this.scaleX * ((this.rightX * paramMatrix.positionX >> 12) + (this.upX * paramMatrix.positionY >> 12) + (this.dirX * paramMatrix.positionZ >> 12)) >> 12;
      this.positionY += this.scaleY * ((this.rightY * paramMatrix.positionX >> 12) + (this.upY * paramMatrix.positionY >> 12) + (this.dirY * paramMatrix.positionZ >> 12)) >> 12;
      this.positionZ += this.scaleZ * ((this.rightZ * paramMatrix.positionX >> 12) + (this.upZ * paramMatrix.positionY >> 12) + (this.dirZ * paramMatrix.positionZ >> 12)) >> 12;
      this.scaleX = this.scaleX * paramMatrix.scaleX >> 12;
      this.scaleY = this.scaleY * paramMatrix.scaleY >> 12;
      this.scaleZ = this.scaleZ * paramMatrix.scaleZ >> 12;
      this.rightX = (var2 * paramMatrix.rightX >> 12) + (var3 * paramMatrix.rightY >> 12) + (this.dirX * paramMatrix.rightZ >> 12);
      this.rightY = (var4 * paramMatrix.rightX >> 12) + (var5 * paramMatrix.rightY >> 12) + (this.dirY * paramMatrix.rightZ >> 12);
      this.rightZ = (var6 * paramMatrix.rightX >> 12) + (var7 * paramMatrix.rightY >> 12) + (this.dirZ * paramMatrix.rightZ >> 12);
      this.upX = (var2 * paramMatrix.upX >> 12) + (var3 * paramMatrix.upY >> 12) + (this.dirX * paramMatrix.upZ >> 12);
      this.upY = (var4 * paramMatrix.upX >> 12) + (var5 * paramMatrix.upY >> 12) + (this.dirY * paramMatrix.upZ >> 12);
      this.upZ = (var6 * paramMatrix.upX >> 12) + (var7 * paramMatrix.upY >> 12) + (this.dirZ * paramMatrix.upZ >> 12);
      this.dirX = (var2 * paramMatrix.dirX >> 12) + (var3 * paramMatrix.dirY >> 12) + (this.dirX * paramMatrix.dirZ >> 12);
      this.dirY = (var4 * paramMatrix.dirX >> 12) + (var5 * paramMatrix.dirY >> 12) + (this.dirY * paramMatrix.dirZ >> 12);
      this.dirZ = (var6 * paramMatrix.dirX >> 12) + (var7 * paramMatrix.dirY >> 12) + (this.dirZ * paramMatrix.dirZ >> 12);
      this.isDirty = true;
   }

   public final Matrix multiplyTo(Matrix paramMatrix1, Matrix paramMatrix2) {
      paramMatrix2.positionX = this.positionX + (this.scaleX * ((this.rightX * paramMatrix1.positionX >> 12) + (this.upX * paramMatrix1.positionY >> 12) + (this.dirX * paramMatrix1.positionZ >> 12)) >> 12);
      paramMatrix2.positionY = this.positionY + (this.scaleY * ((this.rightY * paramMatrix1.positionX >> 12) + (this.upY * paramMatrix1.positionY >> 12) + (this.dirY * paramMatrix1.positionZ >> 12)) >> 12);
      paramMatrix2.positionZ = this.positionZ + (this.scaleZ * ((this.rightZ * paramMatrix1.positionX >> 12) + (this.upZ * paramMatrix1.positionY >> 12) + (this.dirZ * paramMatrix1.positionZ >> 12)) >> 12);
      paramMatrix2.scaleX = this.scaleX * paramMatrix1.scaleX >> 12;
      paramMatrix2.scaleY = this.scaleY * paramMatrix1.scaleY >> 12;
      paramMatrix2.scaleZ = this.scaleZ * paramMatrix1.scaleZ >> 12;
      paramMatrix2.rightX = (this.rightX * paramMatrix1.rightX >> 12) + (this.upX * paramMatrix1.rightY >> 12) + (this.dirX * paramMatrix1.rightZ >> 12);
      paramMatrix2.rightY = (this.rightY * paramMatrix1.rightX >> 12) + (this.upY * paramMatrix1.rightY >> 12) + (this.dirY * paramMatrix1.rightZ >> 12);
      paramMatrix2.rightZ = (this.rightZ * paramMatrix1.rightX >> 12) + (this.upZ * paramMatrix1.rightY >> 12) + (this.dirZ * paramMatrix1.rightZ >> 12);
      paramMatrix2.upX = (this.rightX * paramMatrix1.upX >> 12) + (this.upX * paramMatrix1.upY >> 12) + (this.dirX * paramMatrix1.upZ >> 12);
      paramMatrix2.upY = (this.rightY * paramMatrix1.upX >> 12) + (this.upY * paramMatrix1.upY >> 12) + (this.dirY * paramMatrix1.upZ >> 12);
      paramMatrix2.upZ = (this.rightZ * paramMatrix1.upX >> 12) + (this.upZ * paramMatrix1.upY >> 12) + (this.dirZ * paramMatrix1.upZ >> 12);
      paramMatrix2.dirX = (this.rightX * paramMatrix1.dirX >> 12) + (this.upX * paramMatrix1.dirY >> 12) + (this.dirX * paramMatrix1.dirZ >> 12);
      paramMatrix2.dirY = (this.rightY * paramMatrix1.dirX >> 12) + (this.upY * paramMatrix1.dirY >> 12) + (this.dirY * paramMatrix1.dirZ >> 12);
      paramMatrix2.dirZ = (this.rightZ * paramMatrix1.dirX >> 12) + (this.upZ * paramMatrix1.dirY >> 12) + (this.dirZ * paramMatrix1.dirZ >> 12);
      paramMatrix2.isDirty = true;
      return paramMatrix2;
   }

   public final void translate(int paramInt1, int paramInt2, int paramInt3) {
      this.positionX += paramInt1;
      this.positionY += paramInt2;
      this.positionZ += paramInt3;
   }

   public final void translate(AEVector3D paramAEVector3D) {
      this.positionX += paramAEVector3D.x;
      this.positionY += paramAEVector3D.y;
      this.positionZ += paramAEVector3D.z;
   }

   public final void translateTo(int paramInt1, int paramInt2, int paramInt3) {
      this.positionX = paramInt1;
      this.positionY = paramInt2;
      this.positionZ = paramInt3;
   }

   public final void translateTo(AEVector3D paramAEVector3D) {
      this.positionX = paramAEVector3D.x;
      this.positionY = paramAEVector3D.y;
      this.positionZ = paramAEVector3D.z;
   }

   public final void translateForward(int paramInt) {
      this.positionX += this.dirX * paramInt >> 12;
      this.positionY += this.dirY * paramInt >> 12;
      this.positionZ += this.dirZ * paramInt >> 12;
   }

   public final AEVector3D getPosition(AEVector3D paramAEVector3D) {
      paramAEVector3D.x = this.positionX;
      paramAEVector3D.y = this.positionY;
      paramAEVector3D.z = this.positionZ;
      return paramAEVector3D;
   }

   public final AEVector3D getPosition() {
      return new AEVector3D(this.positionX, this.positionY, this.positionZ);
   }

   public final int getPositionX() {
      return this.positionX;
   }

   public final int getPositionY() {
      return this.positionY;
   }

   public final int getPositionZ() {
      return this.positionZ;
   }

   public final int getDirectionX() {
      return this.dirX;
   }

   public final int getDirectionY() {
      return this.dirY;
   }

   public final int getDirectionZ() {
      return this.dirZ;
   }

   public final AEVector3D getDirection(AEVector3D paramAEVector3D) {
      paramAEVector3D.x = this.dirX;
      paramAEVector3D.y = this.dirY;
      paramAEVector3D.z = this.dirZ;
      return paramAEVector3D;
   }

   public final AEVector3D getDirection() {
      return new AEVector3D(this.dirX, this.dirY, this.dirZ);
   }

   public final AEVector3D getUp(AEVector3D paramAEVector3D) {
      paramAEVector3D.x = this.upX;
      paramAEVector3D.y = this.upY;
      paramAEVector3D.z = this.upZ;
      return paramAEVector3D;
   }

   public final AEVector3D getUp() {
      return new AEVector3D(this.upX, this.upY, this.upZ);
   }

   public final AEVector3D getRight(AEVector3D paramAEVector3D) {
      paramAEVector3D.x = this.rightX;
      paramAEVector3D.y = this.rightY;
      paramAEVector3D.z = this.rightZ;
      return paramAEVector3D;
   }

   public final AEVector3D getRight() {
      return new AEVector3D(this.rightX, this.rightY, this.rightZ);
   }

   public final void setRotationOrder(short paramInt) {
      if(this.rotationOrder != paramInt) {
         this.rotationOrder = paramInt;
         this.updateEulerAngles();
         this.isDirty = false;
      }
   }

   public final void addEulerAngles(int var1, int var2, int var3) {
      if(this.isDirty) {
         this.updateEulerAngles();
         this.isDirty = false;
      }

      this.eulerX += var1;
      this.eulerY += var2;
      this.eulerZ += var3;
      this.setRotation(this.eulerX, this.eulerY, this.eulerZ);
   }

   public final void rotateAroundDir(int paramInt) {
      int var2 = AEMath.sin(paramInt);
      paramInt = AEMath.cos(paramInt);
      int var3;
      int var4 = ((var3 = 4096 - paramInt) * this.dirX >> 12) * this.dirZ >> 12;
      int var5 = (var3 * this.dirY >> 12) * this.dirZ >> 12;
      int var6 = (var3 * this.dirX >> 12) * this.dirY >> 12;
      int var7 = var2 * this.dirY >> 12;
      int var8 = var2 * this.dirX >> 12;
      var2 = var2 * this.dirZ >> 12;
      int var9 = ((var3 * this.dirX >> 12) * this.dirX >> 12) + paramInt;
      int var10 = var6 - var2;
      int var11 = var4 + var7;
      var2 += var6;
      var6 = ((var3 * this.dirY >> 12) * this.dirY >> 12) + paramInt;
      int var12 = var5 - var8;
      var4 -= var7;
      var5 += var8;
      paramInt += (var3 * this.dirZ >> 12) * this.dirZ >> 12;
      var3 = this.rightX;
      var7 = this.rightY;
      this.rightX = (this.rightX * var9 >> 12) + (this.rightY * var10 >> 12) + (this.rightZ * var11 >> 12);
      this.rightY = (var3 * var2 >> 12) + (this.rightY * var6 >> 12) + (this.rightZ * var12 >> 12);
      this.rightZ = (var3 * var4 >> 12) + (var7 * var5 >> 12) + (this.rightZ * paramInt >> 12);
      this.upX = (this.dirY * this.rightZ >> 12) - (this.dirZ * this.rightY >> 12);
      this.upY = (this.dirZ * this.rightX >> 12) - (this.dirX * this.rightZ >> 12);
      this.upZ = (this.dirX * this.rightY >> 12) - (this.dirY * this.rightX >> 12);
      paramInt = AEMath.invSqrt((this.upX * this.upX >> 12) + (this.upY * this.upY >> 12) + (this.upZ * this.upZ >> 12));
      this.upX = this.upX * paramInt >> 12;
      this.upY = this.upY * paramInt >> 12;
      this.upZ = this.upZ * paramInt >> 12;
      this.rightX = (this.dirZ * this.upY >> 12) - (this.dirY * this.upZ >> 12);
      this.rightY = (this.dirX * this.upZ >> 12) - (this.dirZ * this.upX >> 12);
      this.rightZ = (this.dirY * this.upX >> 12) - (this.dirX * this.upY >> 12);
      this.isDirty = true;
   }

   public final void rotateAroundUp(int paramInt) {
      int var2 = AEMath.sin(paramInt);
      paramInt = AEMath.cos(paramInt);
      int var3;
      int var4 = ((var3 = 4096 - paramInt) * this.upX >> 12) * this.upZ >> 12;
      int var5 = (var3 * this.upY >> 12) * this.upZ >> 12;
      int var6 = (var3 * this.upX >> 12) * this.upY >> 12;
      int var7 = var2 * this.upY >> 12;
      int var8 = var2 * this.upX >> 12;
      var2 = var2 * this.upZ >> 12;
      int var9 = ((var3 * this.upX >> 12) * this.upX >> 12) + paramInt;
      int var10 = var6 - var2;
      int var11 = var4 + var7;
      var2 += var6;
      var6 = ((var3 * this.upY >> 12) * this.upY >> 12) + paramInt;
      int var12 = var5 - var8;
      var4 -= var7;
      var5 += var8;
      paramInt += (var3 * this.upZ >> 12) * this.upZ >> 12;
      var3 = this.dirX;
      var7 = this.dirY;
      this.dirX = (this.dirX * var9 >> 12) + (this.dirY * var10 >> 12) + (this.dirZ * var11 >> 12);
      this.dirY = (var3 * var2 >> 12) + (this.dirY * var6 >> 12) + (this.dirZ * var12 >> 12);
      this.dirZ = (var3 * var4 >> 12) + (var7 * var5 >> 12) + (this.dirZ * paramInt >> 12);
      this.rightX = (this.dirZ * this.upY >> 12) - (this.dirY * this.upZ >> 12);
      this.rightY = (this.dirX * this.upZ >> 12) - (this.dirZ * this.upX >> 12);
      this.rightZ = (this.dirY * this.upX >> 12) - (this.dirX * this.upY >> 12);
      paramInt = AEMath.invSqrt((this.rightX * this.rightX >> 12) + (this.rightY * this.rightY >> 12) + (this.rightZ * this.rightZ >> 12));
      this.rightX = this.rightX * paramInt >> 12;
      this.rightY = this.rightY * paramInt >> 12;
      this.rightZ = this.rightZ * paramInt >> 12;
      this.dirX = (this.upZ * this.rightY >> 12) - (this.upY * this.rightZ >> 12);
      this.dirY = (this.upX * this.rightZ >> 12) - (this.upZ * this.rightX >> 12);
      this.dirZ = (this.upY * this.rightX >> 12) - (this.upX * this.rightY >> 12);
      this.isDirty = true;
   }

   public final void rotateAroundRight(int paramInt) {
      int var2 = AEMath.sin(paramInt);
      paramInt = AEMath.cos(paramInt);
      int var3;
      int var4 = ((var3 = 4096 - paramInt) * this.rightX >> 12) * this.rightZ >> 12;
      int var5 = (var3 * this.rightY >> 12) * this.rightZ >> 12;
      int var6 = (var3 * this.rightX >> 12) * this.rightY >> 12;
      int var7 = var2 * this.rightY >> 12;
      int var8 = var2 * this.rightX >> 12;
      var2 = var2 * this.rightZ >> 12;
      int var9 = ((var3 * this.rightX >> 12) * this.rightX >> 12) + paramInt;
      int var10 = var6 - var2;
      int var11 = var4 + var7;
      var2 += var6;
      var6 = ((var3 * this.rightY >> 12) * this.rightY >> 12) + paramInt;
      int var12 = var5 - var8;
      var4 -= var7;
      var5 += var8;
      paramInt += (var3 * this.rightZ >> 12) * this.rightZ >> 12;
      var3 = this.upX;
      var7 = this.upY;
      this.upX = (this.upX * var9 >> 12) + (this.upY * var10 >> 12) + (this.upZ * var11 >> 12);
      this.upY = (var3 * var2 >> 12) + (this.upY * var6 >> 12) + (this.upZ * var12 >> 12);
      this.upZ = (var3 * var4 >> 12) + (var7 * var5 >> 12) + (this.upZ * paramInt >> 12);
      this.dirX = (this.rightY * this.upZ >> 12) - (this.rightZ * this.upY >> 12);
      this.dirY = (this.rightZ * this.upX >> 12) - (this.rightX * this.upZ >> 12);
      this.dirZ = (this.rightX * this.upY >> 12) - (this.rightY * this.upX >> 12);
      paramInt = AEMath.invSqrt((this.dirX * this.dirX >> 12) + (this.dirY * this.dirY >> 12) + (this.dirZ * this.dirZ >> 12));
      this.dirX = this.dirX * paramInt >> 12;
      this.dirY = this.dirY * paramInt >> 12;
      this.dirZ = this.dirZ * paramInt >> 12;
      this.upX = (this.dirY * this.rightZ >> 12) - (this.dirZ * this.rightY >> 12);
      this.upY = (this.dirZ * this.rightX >> 12) - (this.dirX * this.rightZ >> 12);
      this.upZ = (this.dirX * this.rightY >> 12) - (this.dirY * this.rightX >> 12);
      this.isDirty = true;
   }

   public final void setEulerY(int var1) {
      this.setRotation(this.eulerX, var1, this.eulerZ);
   }

   public final void setRotation(int var1, int var2, int var3) {
      this.eulerX = var1;
      this.eulerY = var2;
      this.eulerZ = var3;
      this.isDirty = false;
      int var4 = AEMath.sin(var1);
      int var5 = AEMath.sin(var2);
      int var6 = AEMath.sin(var3);
      var1 = AEMath.cos(var1);
      var2 = AEMath.cos(var2);
      var3 = AEMath.cos(var3);
      int var7;
      int var8;
      int var9;
      switch(this.rotationOrder) {
      case 0:
         var7 = var1 * var3 >> 12;
         var8 = var3 * var4 >> 12;
         var9 = var5 * var6 >> 12;
         this.rightX = var2 * var3 >> 12;
         this.upX = -(var2 * var6 >> 12);
         this.dirX = var5;
         this.rightY = (var8 * var5 >> 12) + (var1 * var6 >> 12);
         this.upY = var7 - (var9 * var4 >> 12);
         this.dirY = -(var2 * var4 >> 12);
         this.rightZ = -(var7 * var5 >> 12) + (var4 * var6 >> 12);
         this.upZ = var8 + (var9 * var1 >> 12);
         this.dirZ = var1 * var2 >> 12;
         return;
      case 1:
         var7 = var2 * var6 >> 12;
         var8 = var5 * var6 >> 12;
         this.rightX = var2 * var3 >> 12;
         this.upX = -var6;
         this.dirX = var3 * var5 >> 12;
         this.rightY = (var4 * var5 >> 12) + (var7 * var1 >> 12);
         this.upY = var1 * var3 >> 12;
         this.dirY = -(var2 * var4 >> 12) + (var8 * var1 >> 12);
         this.rightZ = -(var1 * var5 >> 12) + (var7 * var4 >> 12);
         this.upZ = var3 * var4 >> 12;
         this.dirZ = (var1 * var2 >> 12) + (var8 * var4 >> 12);
         return;
      case 2:
         var7 = var2 * var3 >> 12;
         var8 = var4 * var6 >> 12;
         var9 = var5 * var3 >> 12;
         this.rightX = var7 + (var8 * var5 >> 12);
         this.upX = (var9 * var4 >> 12) - (var2 * var6 >> 12);
         this.dirX = var1 * var5 >> 12;
         this.rightY = var1 * var6 >> 12;
         this.upY = var1 * var3 >> 12;
         this.dirY = -var4;
         this.rightZ = -var9 + (var8 * var2 >> 12);
         this.upZ = (var7 * var4 >> 12) + (var5 * var6 >> 12);
         this.dirZ = var1 * var2 >> 12;
         return;
      case 3:
         var7 = var4 * var5 >> 12;
         var8 = var1 * var2 >> 12;
         var9 = var2 * var4 >> 12;
         this.rightX = var2 * var3 >> 12;
         this.upX = var7 - (var8 * var6 >> 12);
         this.dirX = (var1 * var5 >> 12) + (var9 * var6 >> 12);
         this.rightY = var6;
         this.upY = var1 * var3 >> 12;
         this.dirY = -(var3 * var4 >> 12);
         this.rightZ = -(var3 * var5 >> 12);
         this.upZ = var9 + ((var1 * var5 >> 12) * var6 >> 12);
         this.dirZ = var8 - (var7 * var6 >> 12);
         return;
      case 4:
         var7 = var4 * var5 >> 12;
         var8 = var2 * var4 >> 12;
         this.rightX = (var2 * var3 >> 12) - (var7 * var6 >> 12);
         this.upX = -(var1 * var6 >> 12);
         this.dirX = (var3 * var5 >> 12) + (var8 * var6 >> 12);
         this.rightY = (var7 * var3 >> 12) + (var2 * var6 >> 12);
         this.upY = var1 * var3 >> 12;
         this.dirY = -(var8 * var3 >> 12) + (var5 * var6 >> 12);
         this.rightZ = -(var1 * var5 >> 12);
         this.upZ = var4;
         this.dirZ = var1 * var2 >> 12;
         return;
      case 5:
         var7 = var5 * var6 >> 12;
         var9 = var3 * var5 >> 12;
         this.rightX = var2 * var3 >> 12;
         this.upX = (var9 * var4 >> 12) - (var1 * var6 >> 12);
         this.dirX = (var9 * var1 >> 12) + (var4 * var6 >> 12);
         this.rightY = var2 * var6 >> 12;
         this.upY = (var1 * var3 >> 12) + (var7 * var4 >> 12);
         this.dirY = -(var3 * var4 >> 12) + (var7 * var1 >> 12);
         this.rightZ = -var5;
         this.upZ = var2 * var4 >> 12;
         this.dirZ = var1 * var2 >> 12;
      default:
      }
   }

   public final int getEulerX() {
      if(this.isDirty) {
         this.updateEulerAngles();
         this.isDirty = false;
      }

      return this.eulerX;
   }

   public final int getEulerY() {
      if(this.isDirty) {
         this.updateEulerAngles();
         this.isDirty = false;
      }

      return this.eulerY;
   }

   public final int getEulerZ() {
      if(this.isDirty) {
         this.updateEulerAngles();
         this.isDirty = false;
      }

      return this.eulerZ;
   }

   private void updateEulerAngles() {
      switch(this.rotationOrder) {
      case 0:
         this.eulerY = AEMath.invSin(this.dirX);
         if(this.eulerY < 2048) {
            if(this.eulerY > -2048) {
               this.eulerX = AEMath.atan2(-this.dirY, this.dirZ);
               this.eulerZ = AEMath.atan2(-this.upX, this.rightX);
               return;
            }

            this.eulerX = -AEMath.atan2(this.rightY, this.upY);
            this.eulerZ = 0;
            return;
         }

         this.eulerX = AEMath.atan2(this.rightY, this.upY);
         this.eulerZ = 0;
         return;
      case 1:
         this.eulerZ = AEMath.invSin(-this.upX);
         if(this.eulerZ < 2048) {
            if(this.eulerZ > -2048) {
               this.eulerX = AEMath.atan2(this.upZ, this.upY);
               this.eulerY = AEMath.atan2(this.dirX, this.rightX);
               return;
            }

            this.eulerX = -AEMath.atan2(-this.rightZ, this.dirZ);
            this.eulerY = 0;
            return;
         }

         this.eulerX = AEMath.atan2(-this.rightZ, this.dirZ);
         this.eulerY = 0;
         return;
      case 2:
         this.eulerX = AEMath.invSin(-this.dirY);
         if(this.eulerX < 2048) {
            if(this.eulerX > -2048) {
               this.eulerY = AEMath.atan2(this.dirX, this.dirZ);
               this.eulerZ = AEMath.atan2(this.rightY, this.upY);
               return;
            }

            this.eulerY = -AEMath.atan2(-this.upX, this.rightX);
            this.eulerZ = 0;
            return;
         }

         this.eulerY = AEMath.atan2(-this.upX, this.rightX);
         this.eulerZ = 0;
         return;
      case 3:
         this.eulerZ = AEMath.invSin(this.rightY);
         if(this.eulerZ < 2048) {
            if(this.eulerZ > -2048) {
               this.eulerY = AEMath.atan2(-this.rightZ, this.rightX);
               this.eulerX = AEMath.atan2(-this.dirY, this.upY);
               return;
            }

            this.eulerY = -AEMath.atan2(this.upZ, this.dirZ);
            this.eulerX = 0;
            return;
         }

         this.eulerY = AEMath.atan2(this.upZ, this.dirZ);
         this.eulerX = 0;
         return;
      case 4:
         this.eulerX = AEMath.invSin(this.upZ);
         if(this.eulerX < 2048) {
            if(this.eulerX > -2048) {
               this.eulerZ = AEMath.atan2(-this.upX, this.upY);
               this.eulerY = AEMath.atan2(-this.rightZ, this.dirZ);
               return;
            }

            this.eulerZ = -AEMath.atan2(this.dirX, this.rightX);
            this.eulerY = 0;
            return;
         }

         this.eulerZ = AEMath.atan2(this.dirX, this.rightX);
         this.eulerY = 0;
         return;
      case 5:
         this.eulerY = AEMath.invSin(-this.rightZ);
         if(this.eulerY < 2048) {
            if(this.eulerY > -2048) {
               this.eulerZ = AEMath.atan2(this.rightY, this.rightX);
               this.eulerX = AEMath.atan2(this.upZ, this.dirZ);
               return;
            }

            this.eulerZ = -AEMath.atan2(-this.upX, this.dirX);
            this.eulerX = 0;
            return;
         } else {
            this.eulerZ = AEMath.atan2(-this.upX, this.dirX);
            this.eulerX = 0;
         }
      default:
      }
   }

   public final void setScale(int var1, int var2, int var3) {
      this.scaleX = var1;
      this.scaleY = var2;
      this.scaleZ = var3;
   }

   public final AEVector3D copyScaleTo(AEVector3D var1) {
      var1.x = this.scaleX;
      var1.y = this.scaleY;
      var1.z = this.scaleZ;
      return var1;
   }

   public final AEVector3D getScale() {
      return this.copyScaleTo(new AEVector3D());
   }

   public final Matrix getInverse(Matrix var1) {
      var1.scaleX = 16777216 / this.scaleX;
      var1.scaleY = 16777216 / this.scaleY;
      var1.scaleZ = 16777216 / this.scaleZ;
      var1.rightX = this.rightX;
      var1.upX = this.rightY;
      var1.dirX = this.rightZ;
      var1.rightY = this.upX;
      var1.upY = this.upY;
      var1.dirY = this.upZ;
      var1.rightZ = this.dirX;
      var1.upZ = this.dirY;
      var1.dirZ = this.dirZ;
      var1.positionX = var1.scaleX * (-(var1.rightX * this.positionX >> 12) - (var1.upX * this.positionY >> 12) - (var1.dirX * this.positionZ >> 12)) >> 12;
      var1.positionY = var1.scaleY * (-(var1.rightY * this.positionX >> 12) - (var1.upY * this.positionY >> 12) - (var1.dirY * this.positionZ >> 12)) >> 12;
      var1.positionZ = var1.scaleZ * (-(var1.rightZ * this.positionX >> 12) - (var1.upZ * this.positionY >> 12) - (var1.dirZ * this.positionZ >> 12)) >> 12;
      var1.isDirty = true;
      return var1;
   }

   public final void setRows(AEVector3D paramAEVector3D1, AEVector3D paramAEVector3D2, AEVector3D paramAEVector3D3) {
      this.rightX = paramAEVector3D1.x;
      this.upX = paramAEVector3D2.x;
      this.dirX = paramAEVector3D3.x;
      this.rightY = paramAEVector3D1.y;
      this.upY = paramAEVector3D2.y;
      this.dirY = paramAEVector3D3.y;
      this.rightZ = paramAEVector3D1.z;
      this.upZ = paramAEVector3D2.z;
      this.dirZ = paramAEVector3D3.z;
      this.isDirty = true;
   }

   public final void toFloatArray(float[] var1) {
      var1[0] = (float)(this.scaleX * this.rightX) * 5.9604645E-8F;
      var1[1] = (float)(this.scaleY * this.upX) * 5.9604645E-8F;
      var1[2] = (float)(this.scaleZ * this.dirX) * 5.9604645E-8F;
      var1[3] = (float)this.positionX;
      var1[4] = (float)(this.scaleX * this.rightY) * 5.9604645E-8F;
      var1[5] = (float)(this.scaleY * this.upY) * 5.9604645E-8F;
      var1[6] = (float)(this.scaleZ * this.dirY) * 5.9604645E-8F;
      var1[7] = (float)this.positionY;
      var1[8] = (float)(this.scaleX * this.rightZ) * 5.9604645E-8F;
      var1[9] = (float)(this.scaleY * this.upZ) * 5.9604645E-8F;
      var1[10] = (float)(this.scaleZ * this.dirZ) * 5.9604645E-8F;
      var1[11] = (float)this.positionZ;
      var1[12] = 0.0F;
      var1[13] = 0.0F;
      var1[14] = 0.0F;
      var1[15] = 1.0F;
   }

   public final String toString() {
      String var1 = "";
      var1 = var1 + "|\t" + (this.scaleX * this.rightX >> 12) + ",\t" + (this.scaleY * this.upX >> 12) + ",\t" + (this.scaleZ * this.dirX >> 12) + ",\t" + this.positionX + "\t|\n";
      var1 = var1 + "|\t" + (this.scaleX * this.rightY >> 12) + ",\t" + (this.scaleY * this.upY >> 12) + ",\t" + (this.scaleZ * this.dirY >> 12) + ",\t" + this.positionY + "\t|\n";
      return var1 + "AEMatrix: | \t" + (this.scaleX * this.rightZ >> 12) + ",\t" + (this.scaleY * this.upZ >> 12) + ",\t" + (this.scaleZ * this.dirZ >> 12) + ",\t" + this.positionZ + "\t|\n";
   }

   public final AEVector3D transformVector(AEVector3D var1) {
      int var2 = var1.x;
      int var3 = var1.y;
      int var4 = var1.z;
      var1.x = ((this.scaleX * this.rightX >> 12) * var2 >> 12) + ((this.scaleY * this.upX >> 12) * var3 >> 12) + ((this.scaleZ * this.dirX >> 12) * var4 >> 12) + this.positionX;
      var1.y = ((this.scaleX * this.rightY >> 12) * var2 >> 12) + ((this.scaleY * this.upY >> 12) * var3 >> 12) + ((this.scaleZ * this.dirY >> 12) * var4 >> 12) + this.positionY;
      var1.z = ((this.scaleX * this.rightZ >> 12) * var2 >> 12) + ((this.scaleY * this.upZ >> 12) * var3 >> 12) + ((this.scaleZ * this.dirZ >> 12) * var4 >> 12) + this.positionZ;
      return var1;
   }

   public final AEVector3D transformVector(AEVector3D var1, AEVector3D var2) {
      var2.x = ((this.scaleX * this.rightX >> 12) * var1.x >> 12) + ((this.scaleY * this.upX >> 12) * var1.y >> 12) + ((this.scaleZ * this.dirX >> 12) * var1.z >> 12) + this.positionX;
      var2.y = ((this.scaleX * this.rightY >> 12) * var1.x >> 12) + ((this.scaleY * this.upY >> 12) * var1.y >> 12) + ((this.scaleZ * this.dirY >> 12) * var1.z >> 12) + this.positionY;
      var2.z = ((this.scaleX * this.rightZ >> 12) * var1.x >> 12) + ((this.scaleY * this.upZ >> 12) * var1.y >> 12) + ((this.scaleZ * this.dirZ >> 12) * var1.z >> 12) + this.positionZ;
      return var2;
   }

   public final AEVector3D transformVector2(AEVector3D var1, AEVector3D var2) {
      long var3 = ((long)(this.scaleX * this.rightX >> 12) * (long)var1.x >> 12) + ((long)(this.scaleY * this.upX >> 12) * (long)var1.y >> 12) + ((long)(this.scaleZ * this.dirX >> 12) * (long)var1.z >> 12) + (long)this.positionX;
      long var5 = ((long)(this.scaleX * this.rightY >> 12) * (long)var1.x >> 12) + ((long)(this.scaleY * this.upY >> 12) * (long)var1.y >> 12) + ((long)(this.scaleZ * this.dirY >> 12) * (long)var1.z >> 12) + (long)this.positionY;
      long var7 = ((long)(this.scaleX * this.rightZ >> 12) * (long)var1.x >> 12) + ((long)(this.scaleY * this.upZ >> 12) * (long)var1.y >> 12) + ((long)(this.scaleZ * this.dirZ >> 12) * (long)var1.z >> 12) + (long)this.positionZ;
      var2.x = (int)var3;
      var2.y = (int)var5;
      var2.z = (int)var7;
      return var2;
   }

   public final AEVector3D transformVectorNoScale(AEVector3D var1, AEVector3D var2) {
      var2.x = (this.rightX * var1.x >> 12) + (this.upX * var1.y >> 12) + (this.dirX * var1.z >> 12);
      var2.y = (this.rightY * var1.x >> 12) + (this.upY * var1.y >> 12) + (this.dirY * var1.z >> 12);
      var2.z = (this.rightZ * var1.x >> 12) + (this.upZ * var1.y >> 12) + (this.dirZ * var1.z >> 12);
      return var2;
   }

   public final AEVector3D transformVectorNoScale2(AEVector3D var1, AEVector3D var2) {
      long var3 = ((long)this.rightX * (long)var1.x >> 12) + ((long)this.upX * (long)var1.y >> 12) + ((long)this.dirX * (long)var1.z >> 12);
      long var5 = ((long)this.rightY * (long)var1.x >> 12) + ((long)this.upY * (long)var1.y >> 12) + ((long)this.dirY * (long)var1.z >> 12);
      long var7 = ((long)this.rightZ * (long)var1.x >> 12) + ((long)this.upZ * (long)var1.y >> 12) + ((long)this.dirZ * (long)var1.z >> 12);
      var2.x = (int)var3;
      var2.y = (int)var5;
      var2.z = (int)var7;
      return var2;
   }

   public final AEVector3D[] transformVectorsNoScale(AEVector3D[] var1, AEVector3D[] var2) {
      for(int var3 = var2.length - 1; var3 >= 0; --var3) {
         var2[var3].x = (this.rightX * var1[var3].x >> 12) + (this.upX * var1[var3].y >> 12) + (this.dirX * var1[var3].z >> 12);
         var2[var3].y = (this.rightY * var1[var3].x >> 12) + (this.upY * var1[var3].y >> 12) + (this.dirY * var1[var3].z >> 12);
         var2[var3].z = (this.rightZ * var1[var3].x >> 12) + (this.upZ * var1[var3].y >> 12) + (this.dirZ * var1[var3].z >> 12);
      }

      return var2;
   }

   public final AEVector3D inverseTransformVector(AEVector3D var1) {
      int var2 = 16777216 / this.scaleX;
      int var3 = 16777216 / this.scaleY;
      int var4 = 16777216 / this.scaleZ;
      int var5 = var2 * (-(this.rightX * this.positionX >> 12) - (this.rightY * this.positionY >> 12) - (this.rightZ * this.positionZ >> 12)) >> 12;
      int var6 = var3 * (-(this.upX * this.positionX >> 12) - (this.upY * this.positionY >> 12) - (this.upZ * this.positionZ >> 12)) >> 12;
      int var7 = var4 * (-(this.dirX * this.positionX >> 12) - (this.dirY * this.positionY >> 12) - (this.dirZ * this.positionZ >> 12)) >> 12;
      int var8 = var1.x;
      int var9 = var1.y;
      int var10 = var1.z;
      var1.x = ((var2 * this.rightX >> 12) * var8 >> 12) + ((var3 * this.rightY >> 12) * var9 >> 12) + ((var4 * this.rightZ >> 12) * var10 >> 12) + var5;
      var1.y = ((var2 * this.upX >> 12) * var8 >> 12) + ((var3 * this.upY >> 12) * var9 >> 12) + ((var4 * this.upZ >> 12) * var10 >> 12) + var6;
      var1.z = ((var2 * this.dirX >> 12) * var8 >> 12) + ((var3 * this.dirY >> 12) * var9 >> 12) + ((var4 * this.dirZ >> 12) * var10 >> 12) + var7;
      return var1;
   }

   public final AEVector3D transformVectorNoScale(AEVector3D var1) {
      int var2 = var1.x;
      int var3 = var1.y;
      int var4 = var1.z;
      var1.x = (this.rightX * var2 >> 12) + (this.rightY * var3 >> 12) + (this.rightZ * var4 >> 12);
      var1.y = (this.upX * var2 >> 12) + (this.upY * var3 >> 12) + (this.upZ * var4 >> 12);
      var1.z = (this.dirX * var2 >> 12) + (this.dirY * var3 >> 12) + (this.dirZ * var4 >> 12);
      return var1;
   }

   public final void setOrientation(AEVector3D var1) {
      temp.set(var1);
      temp.normalize();
      this.dirX = temp.x;
      this.dirY = temp.y;
      this.dirZ = temp.z;
      this.upX = 0;
      this.upY = 4096;
      this.upZ = 0;
      this.rightX = (this.upY * this.dirZ >> 12) - (this.upZ * this.dirY >> 12);
      this.rightY = (this.upZ * this.dirX >> 12) - (this.upX * this.dirZ >> 12);
      this.rightZ = (this.upX * this.dirY >> 12) - (this.upY * this.dirX >> 12);
      temp.x = this.rightX;
      temp.y = this.rightY;
      temp.z = this.rightZ;
      temp.normalize();
      this.rightX = temp.x;
      this.rightY = temp.y;
      this.rightZ = temp.z;
      this.upX = (this.dirY * this.rightZ >> 12) - (this.dirZ * this.rightY >> 12);
      this.upY = (this.dirZ * this.rightX >> 12) - (this.dirX * this.rightZ >> 12);
      this.upZ = (this.dirX * this.rightY >> 12) - (this.dirY * this.rightX >> 12);
      temp.x = this.upX;
      temp.y = this.upY;
      temp.z = this.upZ;
      temp.normalize();
      this.upX = temp.x;
      this.upY = temp.y;
      this.upZ = temp.z;
   }

}
