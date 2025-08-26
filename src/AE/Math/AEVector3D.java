package AE.Math;


public final class AEVector3D {

   public int x;
   public int y;
   public int z;


   public AEVector3D(int x, int y, int z) {
      this.x = x;
      this.y = y;
      this.z = z;
   }

   public AEVector3D() {
      this.x = this.y = this.z = 0;
   }

   public AEVector3D(AEVector3D other) {
      this.x = other.x;
      this.y = other.y;
      this.z = other.z;
   }

   public final void set(AEVector3D other) {
      this.x = other.x;
      this.y = other.y;
      this.z = other.z;
   }

   public final void set(int x, int y, int z) {
      this.x = x;
      this.y = y;
      this.z = z;
   }

   public final int dot(AEVector3D other) {
      return (this.x * other.x >> 12) + (this.y * other.y >> 12) + (this.z * other.z >> 12);
   }

   public final int dotPrecise(AEVector3D other) {
      return (int)(((long)this.x * (long)other.x >> 12) + ((long)this.y * (long)other.y >> 12) + ((long)this.z * (long)other.z >> 12));
   }

   public final AEVector3D crossProduct(AEVector3D other, AEVector3D result) {
      result.x = (this.y * other.z >> 12) - (this.z * other.y >> 12);
      result.y = (this.z * other.x >> 12) - (this.x * other.z >> 12);
      result.z = (this.x * other.y >> 12) - (this.y * other.x >> 12);
      return result;
   }

   public final AEVector3D add(AEVector3D other, AEVector3D result) {
      result.x = this.x + other.x;
      result.y = this.y + other.y;
      result.z = this.z + other.z;
      return result;
   }

   public final void add(AEVector3D other) {
      this.x += other.x;
      this.y += other.y;
      this.z += other.z;
   }

   public final AEVector3D subtract(AEVector3D other, AEVector3D result) {
      result.x = this.x - other.x;
      result.y = this.y - other.y;
      result.z = this.z - other.z;
      return result;
   }

   public final void subtract(AEVector3D other) {
      this.x -= other.x;
      this.y -= other.y;
      this.z -= other.z;
   }

   public final void scale(int scalar) {
      this.x = (int)((long)scalar * (long)this.x >> 12);
      this.y = (int)((long)scalar * (long)this.y >> 12);
      this.z = (int)((long)scalar * (long)this.z >> 12);
   }

   public final void normalize() {
      int var3 = AEMath.invSqrt((int)(((long)this.x * (long)this.x >> 12) + ((long)this.y * (long)this.y >> 12) + ((long)this.z * (long)this.z >> 12)));
      this.x = this.x * var3 >> 12;
      this.y = this.y * var3 >> 12;
      this.z = this.z * var3 >> 12;
   }

   public final int getLength() {
      return AEMath.sqrt(((long)this.x * (long)this.x >> 12) + ((long)this.y * (long)this.y >> 12) + ((long)this.z * (long)this.z >> 12));
   }
   
   // Длина вектора
   public final int magnitude() {
      return AEMath.invSin(getLength());
   }

   // Угол между двумя векторами
   public final int angleBetween(AEVector3D other) {
      int dotProduct = dot(other);
      int magnitudeProduct = magnitude() * other.magnitude();
      return AEMath.atan2_Q15(dotProduct, magnitudeProduct);
   }

   // Проекция этого вектора на другой вектор
   public final AEVector3D projectOnto(AEVector3D other, AEVector3D result) {
      int dot = dot(other);
      int magSquared = other.getLength();
      result.x = (dot * other.x) / magSquared;
      result.y = (dot * other.y) / magSquared;
      result.z = (dot * other.z) / magSquared;
      return result;
   }

   // Расстояние между двумя векторами
   public final int distanceTo(AEVector3D other) {
      AEVector3D diff = new AEVector3D();
      subtract(other, diff);
      return diff.magnitude();
   }

   // Линейная интерполяция между двумя векторами
   public final AEVector3D lerp(AEVector3D other, int alpha, AEVector3D result) {
      result.x = this.x + ((other.x - this.x) * alpha >> 12);
      result.y = this.y + ((other.y - this.y) * alpha >> 12);
      result.z = this.z + ((other.z - this.z) * alpha >> 12);
      return result;
   }
   
   public final String toString() {
     System.out.println("AEVector3D | " + this.x + ",\t" + this.y + ",\t" + this.z);
      return "AEVector3D | " + this.x + ",\t" + this.y + ",\t" + this.z;
   }
}
