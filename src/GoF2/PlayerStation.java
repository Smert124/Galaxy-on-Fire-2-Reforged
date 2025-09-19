// collisions

package GoF2;

import AE.AEResourceManager;
import AE.AbstractMesh;
import AE.BoundingAAB;
import AE.BoundingVolume;
import AE.GlobalStatus;
import AE.Math.AEMath;
import AE.Math.AEVector3D;
import AE.Math.Matrix;


public final class PlayerStation extends PlayerStaticFar {
	
	private AbstractMesh[] stationParts;
	private int[] partPositions;
	private int collidingPart;
	private int maxPartDeflection;
	private AbstractMesh[] colBoxDebug;
	private int[][] partsCollisions;
	
	public PlayerStation(Station station) {
		super(-1, (AbstractMesh)null, 0, 0, 0);
		this.player.sub_28a(15000);
		new FileRead();
		int[] arrayOfInt = null;
		
		if(!Status.inAlienOrbit()) {
			arrayOfInt = FileRead.loadStationParts(station.getId(), Status.getSystem().getRace());
		}
		
		int var3;
		if(arrayOfInt == null) {
			this.stationParts = new AbstractMesh[1];
			AbstractMesh[] voidStation = this.stationParts;
			Status.inAlienOrbit();
			voidStation[0] = AEResourceManager.getGeometryResource(3337);
			this.stationParts[0].setRenderLayer(2);
			
			if(GlobalStatus.STATION_COLLISION_BOX_VISIBLE && this.colBoxDebug == null) {
				this.colBoxDebug = new AbstractMesh[1];
				this.colBoxDebug[0].setRenderLayer(2);
			}
		} else {
			this.stationParts = new AbstractMesh[arrayOfInt.length / 7];
			this.partsCollisions = new int[this.stationParts.length][];
			
			if(GlobalStatus.STATION_COLLISION_BOX_VISIBLE && this.colBoxDebug == null) {
				this.colBoxDebug = new AbstractMesh[arrayOfInt.length / 7];
			}
			
			for(int var7 = 0; var7 < this.stationParts.length; ++var7) {
				var3 = var7 * 7;
				int modelId = arrayOfInt[var3];
				this.stationParts[var7] = AEResourceManager.getGeometryResource(modelId);
				this.stationParts[var7].setRotationOrder((short)0);
				this.stationParts[var7].moveTo(arrayOfInt[var3 + 1], arrayOfInt[var3 + 2], arrayOfInt[var3 + 3]);
				this.stationParts[var7].setRotation(arrayOfInt[var3 + 4], arrayOfInt[var3 + 5], arrayOfInt[var3 + 6]);
				this.stationParts[var7].setRenderLayer(2);
				
				int[] collisionData = FileRead.loadStationPartCollision(modelId, 0);
				this.partsCollisions[var7] = collisionData;
				
				if(GlobalStatus.STATION_COLLISION_BOX_VISIBLE && this.colBoxDebug != null && collisionData != null) {
					int boxCount = collisionData.length / 6;
					
					if(boxCount > 0) {
						int offsetX = collisionData[0];
						int offsetY = collisionData[1];
						int offsetZ = collisionData[2];
						int sizeX = collisionData[3];
						int sizeY = collisionData[4];
						int sizeZ = collisionData[5];
						
						this.colBoxDebug[var7] = AEResourceManager.getGeometryResource(2510);
						this.colBoxDebug[var7].setRotationOrder((short)0);
						
						this.colBoxDebug[var7].moveTo(
							arrayOfInt[var3 + 1] + offsetX, 
							arrayOfInt[var3 + 2] + offsetY, 
							arrayOfInt[var3 + 3] + offsetZ
						);
						this.colBoxDebug[var7].setRotation(arrayOfInt[var3 + 4], arrayOfInt[var3 + 5], arrayOfInt[var3 + 6]);
						this.colBoxDebug[var7].setScale(sizeX, sizeY, sizeZ);
						this.colBoxDebug[var7].setRenderLayer(2);
					}
				}
				
				if(this.stationParts[var7].getID() == 6796 || this.stationParts[var7].getID() == 6797) {
					this.stationParts[var7].setDraw(true);
				}
			}
		}
		
		this.maxPartDeflection = 0;
		this.boundingBoxes = new BoundingVolume[this.stationParts.length];
		Matrix var8 = new Matrix();
		
		for(var3 = 0; var3 < this.stationParts.length; ++var3) {
			int modelId = this.stationParts[var3].getID();
			int[] collisionData = this.partsCollisions != null ? this.partsCollisions[var3] : null;
			
			if(collisionData != null) {
				int offsetX = collisionData[0];
				int offsetY = collisionData[1];
				int offsetZ = collisionData[2];
				int sizeX = collisionData[3];
				int sizeY = collisionData[4];
				int sizeZ = collisionData[5];
				
				this.tempVector_ = this.stationParts[var3].getPosition(this.tempVector_);
				
				if(AEMath.abs(this.tempVector_.x) > this.maxPartDeflection) {
					this.maxPartDeflection = AEMath.abs(this.tempVector_.x);
				}
				
				if(AEMath.abs(this.tempVector_.y) > this.maxPartDeflection) {
					this.maxPartDeflection = AEMath.abs(this.tempVector_.y);
				}
				
				if(AEMath.abs(this.tempVector_.z) > this.maxPartDeflection) {
					this.maxPartDeflection = AEMath.abs(this.tempVector_.z);
				}
				
				int var4 = this.tempVector_.x;
				int var5 = this.tempVector_.y;
				int var6 = this.tempVector_.z;
				
				var8.setRotation(this.stationParts[var3].getEulerX(), this.stationParts[var3].getEulerY(), this.stationParts[var3].getEulerZ());
				virtDistToCam_.set(offsetX, offsetY, offsetZ);
				
				this.position = var8.transformVectorNoScale(virtDistToCam_, this.position);
				virtDistToCam_.set(sizeX + 4096, sizeY + 4096, sizeZ + 4096);
				
				this.tempVector_ = var8.transformVectorNoScale(virtDistToCam_, this.tempVector_);
				this.boundingBoxes[var3] = new BoundingAAB(var4, var5, var6, this.position.x, this.position.y, this.position.z, sizeX, sizeY, sizeZ);
			} else {
				System.out.println("No collision data for model: " + modelId);
				this.boundingBoxes[var3] = new BoundingAAB(0, 0, 0, 0, 0, 0, 4, 4, 4);
			}
			
			if(Status.getSystem() != null) {
				arrayOfInt = Globals.getRaceUVkeyframeId_(Status.getSystem().getRace());
				this.stationParts[var3].setAnimationRangeInTime(arrayOfInt[0], arrayOfInt[1]);
				this.stationParts[var3].disableAnimation();
			}
		}
		
		this.maxPartDeflection += 4096;
		
		this.partPositions = new int[this.stationParts.length * 3];
		for(var3 = 0; var3 < this.stationParts.length; ++var3) {
			this.partPositions[var3 * 3] = this.stationParts[var3].getPosX();
			this.partPositions[var3 * 3 + 1] = this.stationParts[var3].getPosY();
			this.partPositions[var3 * 3 + 2] = this.stationParts[var3].getPosZ();
		}
	}
	
	public final void update(long var1) {
		if(this.stationParts != null) {
			for(int var3 = 0; var3 < this.stationParts.length; ++var3) {
				this.tempVector_ = GlobalStatus.renderer.sub_85().getLocalPos(this.tempVector_);
				this.position.set(this.partPositions[var3 * 3], this.partPositions[var3 * 3 + 1], this.partPositions[var3 * 3 + 2]);
				this.position.subtract(this.tempVector_, virtDistToCam_);
				int var2;
				if((var2 = virtDistToCam_.getLength()) > 28000) {
					virtDistToCam_.normalize();
					virtDistToCam_.scale(28000);
					virtDistToCam_.add(this.tempVector_);
					this.stationParts[var3].moveTo(virtDistToCam_);
					var2 = (int)(28000.0F / (float)var2 * 4096.0F);
					this.stationParts[var3].setScale(var2, var2, var2);
				} else {
					this.stationParts[var3].setScale(4096, 4096, 4096);
					this.stationParts[var3].moveTo(this.partPositions[var3 * 3], this.partPositions[var3 * 3 + 1], this.partPositions[var3 * 3 + 2]);
				}
			}
		}
	}
	
	public final void appendToRender() {
		if(this.stationParts != null) {
			for(int var1 = 0; var1 < this.stationParts.length; ++var1) {
				GlobalStatus.renderer.drawNodeInVF(this.stationParts[var1]);
				if(GlobalStatus.STATION_COLLISION_BOX_VISIBLE && this.colBoxDebug != null && this.colBoxDebug[var1] != null) {
					GlobalStatus.renderer.forceRenderModel(this.colBoxDebug[var1]);
				}
			}
		}
	}
	
	public final AEVector3D getPosition(AEVector3D var1) {
		var1.set(0, 0, 0);
		return var1;
	}
	
	public final boolean outerCollide(int var1, int var2, int var3) {
		return (float)var1 < this.player.var_21b && (float)var1 > -this.player.var_21b && (float)var2 < this.player.var_21b && (float)var2 > -this.player.var_21b && (float)var3 < this.player.var_21b && (float)var3 > -this.player.var_21b;
	}
	
	public final boolean outerCollide(AEVector3D var1) {
		int var4 = var1.z;
		int var3 = var1.y;
		int var2 = var1.x;
		PlayerStation var6 = this;
		if(var2 < this.maxPartDeflection && var2 > -this.maxPartDeflection && var3 < this.maxPartDeflection && var3 > -this.maxPartDeflection && var4 < this.maxPartDeflection && var4 > -this.maxPartDeflection && this.boundingBoxes != null) {
			for(int var5 = 0; var5 < var6.boundingBoxes.length; ++var5) {
				if(var6.boundingBoxes[var5].isPointInBounding(var2, var3, var4)) {
					var6.collidingPart = var5;
					return true;
				}
			}
		}
		return false;
	}
	
	public final AEVector3D getProjectionVector_(AEVector3D var1) {
		return this.boundingBoxes != null?this.boundingBoxes[this.collidingPart].getProjectionVector(var1):null;
	}
	
	public final void OnRelease() {
		if(this.stationParts != null) {
			for(int var1 = 0; var1 < this.stationParts.length; ++var1) {
				if(this.stationParts[var1] != null) {
					this.stationParts[var1].OnRelease();
					this.stationParts[var1] = null;
				}
			}
		}
		
		this.stationParts = null;
		this.partPositions = null;
		this.partsCollisions = null;
	}
}