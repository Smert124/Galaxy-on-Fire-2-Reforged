package AE;

import AE.Math.AEMath;
import AE.Math.AEVector3D;


public final class AEBoundingSphere {

   public AEVector3D center;
   public int radius;


   private AEBoundingSphere(int x, int y, int z, int radius) {
      this.center = new AEVector3D(x, y, z);
      this.radius = radius;
   }

   public AEBoundingSphere() {
      this(0, 0, 0, 0);
   }

   public AEBoundingSphere(AEBoundingSphere other) {
      this.center = new AEVector3D(other.center.x, other.center.y, other.center.z);
      this.radius = other.radius;
   }

   public final void merge(AEBoundingSphere other) {
      if(this.radius == 0) {
         this.set(other);
      } else if(other.radius == 0) {
         this.set(this);
      } else {
         int var4 = other.center.x - this.center.x;
         int var5 = other.center.y - this.center.y;
         int var6 = other.center.z - this.center.z;
         int var7;
         int var8 = (var7 = other.radius - this.radius) * var7;
         int var9 = var4 * var4 + var5 * var5 + var6 * var6;
         if(var8 >= var9) {
            if(var7 >= 0) {
               this.set(other.center, other.radius);
            } else {
               this.set(this.center, this.radius);
            }
         } else {
            var8 = AEMath.sqrt((long)var9 << 12);
            var7 = (int)(((long)((var7 << 12) + var8) << 12) / (long)(var8 * 2));
            this.setXYZR(this.center.x + (int)((long)var7 * ((long)var4 << 12) >> 24), this.center.y + (int)((long)var7 * ((long)var5 << 12) >> 24), this.center.z + (int)((long)var7 * ((long)var6 << 12) >> 24), (var8 >> 12) + other.radius + this.radius >> 1);
         }
      }

   }

   private void set(AEBoundingSphere other) {
      this.center.set(other.center);
      this.radius = other.radius;
   }

   public final void setXYZR(int x, int y, int z, int radius) {
      this.center.x = x;
      this.center.y = y;
      this.center.z = z;
      this.radius = radius;
   }

   private void set(AEVector3D center, int radius) {
      this.center.set(center);
      this.radius = radius;
   }
   
   // Проверка пересечения двух сфер
   public final boolean intersects(AEBoundingSphere other) {
      int distanceSquared = (int)((long)(this.center.x - other.center.x) * (this.center.x - other.center.x) >> 12) +
                            (int)((long)(this.center.y - other.center.y) * (this.center.y - other.center.y) >> 12) +
                            (int)((long)(this.center.z - other.center.z) * (this.center.z - other.center.z) >> 12);
      int radiusSum = this.radius + other.radius;
      return distanceSquared <= radiusSum * radiusSum;
   }

   // Проверка, находится ли точка внутри сферы
   public final boolean containsPoint(AEVector3D point) {
      int distanceSquared = (int)((long)(this.center.x - point.x) * (this.center.x - point.x) >> 12) +
                            (int)((long)(this.center.y - point.y) * (this.center.y - point.y) >> 12) +
                            (int)((long)(this.center.z - point.z) * (this.center.z - point.z) >> 12);
      return distanceSquared <= this.radius * this.radius;
   }

   // Расширение сферы, чтобы включить точку
   public final void expandToInclude(AEVector3D point) {
      if (!containsPoint(point)) {
         int dx = point.x - this.center.x;
         int dy = point.y - this.center.y;
         int dz = point.z - this.center.z;
         int distance = AEMath.invSin((int)((long)dx * dx >> 12) + (int)((long)dy * dy >> 12) + (int)((long)dz * dz >> 12));
         int newRadius = (distance >> 1) + this.radius;
         this.center.x += (int)((long)(dx * (newRadius - this.radius)) >> 12);
         this.center.y += (int)((long)(dy * (newRadius - this.radius)) >> 12);
         this.center.z += (int)((long)(dz * (newRadius - this.radius)) >> 12);
         this.radius = newRadius;
      }
   }

   // Вычисление объема сферы
   public final int volume() {
      return (int)((long)this.radius * this.radius * this.radius * 4 / 3 * 3.141592653589793);
   }

   public final String toString() {
      return "AEBoundingSphere | x: " + this.center.x + " y: " + this.center.y + " z: " + this.center.z + " r: " + this.radius;
   }
}
