/**


AUTO-AIM GUNS. NEED FOR THERMAL WEAPONS.


**/

package GoF2;

import AE.AbstractMesh;
import AE.Math.AEVector3D;
import AE.GlobalStatus;

public final class GuidedGun extends ObjectGun {
	
    private AEVector3D targetPos = new AEVector3D();
    private AEVector3D dirToTarget = new AEVector3D();
    private AEVector3D temp = new AEVector3D();
    private AEVector3D direction = new AEVector3D();
    private Radar radar;
    private boolean hasRadar = false;
    private Trail[] trails;
    private static AEVector3D tempPos = new AEVector3D();

    public GuidedGun(Gun var1, AbstractMesh var2) {
        super(var1, var2);
		
		if(GlobalStatus.EFFECTS_QUALITY) {
			this.trails = new Trail[var1.projectilesPos.length];
			
			for(int i = 0; i < this.trails.length; i++) {
				this.trails[i] = new Trail(5, 16);
				this.trails[i].setWidth(80);
			}
			
		}
		
	}

    public final void setRadar(Radar var1) {
        this.radar = var1;
        this.hasRadar = true;
    }

    public final void update(long var1) {
        this.gun.calcCharacterCollision(var1);
        
        if(this.gun.inAir) {
            int dt = (int)var1;
            Player lockedTarget = null;
            if(this.hasRadar && this.radar != null) {
                KIPlayer lockedEnemy = this.radar.getLockedEnemy();
                if(lockedEnemy != null && lockedEnemy.player.isActive() && 
                    !lockedEnemy.player.isDead() && !lockedEnemy.player.isAsteroid()) {
                    lockedTarget = lockedEnemy.player;
                }
            }
            
            for(int i = 0; i < this.gun.projectilesPos.length; i++) {
                if(GlobalStatus.EFFECTS_QUALITY && this.gun.fired && this.gun.projectilesTimeLeft[i] > 0) {
					this.trails[i].reset(this.gun.projectilesPos[i]);
				}
                
                if(this.gun.projectilesTimeLeft[i] > 0) {
                    if(lockedTarget != null && this.gun.projectilesTimeLeft[i] < this.gun.range) {
                        lockedTarget.getPosition(targetPos);
                        dirToTarget.x = targetPos.x - this.gun.projectilesPos[i].x;
                        dirToTarget.y = targetPos.y - this.gun.projectilesPos[i].y;
                        dirToTarget.z = targetPos.z - this.gun.projectilesPos[i].z;
                        
                        int distX = dirToTarget.x;
                        int distY = dirToTarget.y;
                        int distZ = dirToTarget.z;
                        
                        if(distX < 20000 && distX > -20000 && 
                            distY < 20000 && distY > -20000 && 
                            distZ < 20000 && distZ > -20000) {
                            
                            temp.x = targetPos.x - this.gun.projectilesPos[i].x;
                            temp.y = targetPos.y - this.gun.projectilesPos[i].y;
                            temp.z = targetPos.z - this.gun.projectilesPos[i].z;
                            
                            direction.set(this.gun.projectilesDir[i]);
                            
                            temp.subtract(direction);
                            
                            temp.scale(dt);
                            
                            this.gun.projectilesDir[i] = direction.add(temp, this.gun.projectilesDir[i]);
                            
                            this.gun.projectilesDir[i].normalize();
                            
                            this.gun.projectilesDir[i].scale((int)(this.gun.projectileSpeed * (float)dt) << 12);
                            this.gun.projectilesDir[i].x >>= 12;
                            this.gun.projectilesDir[i].y >>= 12;
                            this.gun.projectilesDir[i].z >>= 12;
                        }
                    }
                    
                    tempPos.set(this.gun.projectilesPos[i]);
					
					if(GlobalStatus.EFFECTS_QUALITY) {
						this.trails[i].update(tempPos);
					}
					
				}
            }
            
            this.gun.fired = false;
        }
    }
    
    public void render() {
        if(GlobalStatus.EFFECTS_QUALITY && this.gun.inAir && this.trails != null) {
            for(int i = 0; i < this.trails.length; i++) {
                if(this.gun.projectilesTimeLeft[i] > 0) {
                    this.trails[i].render();
                }
            }
        }
        
        super.render();
    }
    
    public void OnRelease() {
        super.OnRelease();
        if(GlobalStatus.EFFECTS_QUALITY && this.trails != null) {
            for(int i = 0; i < this.trails.length; i++) {
                if(this.trails[i] != null) {
                    this.trails[i].OnRelease();
                }
            }
            this.trails = null;
        }
    }
}