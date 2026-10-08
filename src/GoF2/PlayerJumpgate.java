package GoF2;

import AE.AbstractMesh;
import AE.AEResourceManager;
import AE.BoundingSphere;
import AE.BoundingVolume;
import AE.GlobalStatus;

public final class PlayerJumpgate extends PlayerStaticFar {

    private static final int MESH_MAIN     = 0;
    private static final int MESH_GLOW     = 1;
    private static final int MESH_ACTIVATE = 2;
    private static final int MESH_NL       = 3;

    private AbstractMesh[] gateMeshes;
    private boolean animationInit;
    private boolean activated;
    private int race;

    public PlayerJumpgate(int race, int x, int y, int z, boolean visible) {
        super(-1, AEResourceManager.getGeometryResource(resolveId(Globals.JUMPGATE_MAIN_MESH, race)), x, y, z);

        this.race = race;
        this.activated = false;
        this.animationInit = false;
		
        this.setVisible(visible);

        this.gateMeshes = new AbstractMesh[4];
        this.gateMeshes[MESH_MAIN]     = this.mainMesh_;
        this.gateMeshes[MESH_GLOW]     = AEResourceManager.getGeometryResource(resolveId(Globals.JUMPGATE_GLOW_MESH, race));
        this.gateMeshes[MESH_ACTIVATE] = AEResourceManager.getGeometryResource(resolveId(Globals.JUMPGATE_ACTIVATE_MESH, race));
        this.gateMeshes[MESH_NL]       = AEResourceManager.getGeometryResource(resolveId(Globals.JUMPGATE_NL_MESH, race));

        for (int i = 0; i < this.gateMeshes.length; i++) {
            AbstractMesh m = this.gateMeshes[i];
            if (m == null) continue;
            m.setAnimationSpeed(100);
            m.setAnimationRangeInTime(0, 51);
            m.setAnimationMode((byte) 2);
            m.setRenderLayer(2);

            m.moveTo(x, y, z);
            m.setRotation(0, 2048, 0);

            // Важно: при visible == false все меши скрыты,
            // иначе var_3fd[2] снова станет "призраком"
            m.setDraw(visible);
        }

        // Раскладка для видимого состояния: MAIN + GLOW + NL видны, ACTIVATE скрыт
        if (visible) {
            if (this.gateMeshes[MESH_MAIN] != null)     this.gateMeshes[MESH_MAIN].setDraw(true);
            if (this.gateMeshes[MESH_GLOW] != null)     this.gateMeshes[MESH_GLOW].setDraw(true);
            if (this.gateMeshes[MESH_ACTIVATE] != null) this.gateMeshes[MESH_ACTIVATE].setDraw(false);
            if (this.gateMeshes[MESH_NL] != null)       this.gateMeshes[MESH_NL].setDraw(true);

            this.boundingBoxes = new BoundingVolume[1];
            this.boundingBoxes[0] = new BoundingSphere(x, y, z, 0, 0, 0, 5000);
        }

        this.mainMesh_.setRotation(0, 2048, 0);
    }

    private static int resolveId(short[] table, int race) {
		
		if(race < 0 || race >= 4) {
			race = 3;
		}
		
		return table[race];
		
	}

    public final void activate() {
        if (!this.animationInit) {
            this.activated = true;
            this.animationInit = true;
			
            if (this.gateMeshes[MESH_GLOW] != null) {
                this.gateMeshes[MESH_GLOW].setDraw(false);
            }
            if (this.gateMeshes[MESH_ACTIVATE] != null) {
                this.gateMeshes[MESH_ACTIVATE].setDraw(true);
                this.gateMeshes[MESH_ACTIVATE].setAnimationSpeed(70);
                this.gateMeshes[MESH_ACTIVATE].setAnimationRangeInTime(0, 51);
                this.gateMeshes[MESH_ACTIVATE].setAnimationMode((byte) 2);
            }

            if (this.gateMeshes[MESH_MAIN] != null) {
                this.gateMeshes[MESH_MAIN].setAnimationSpeed(100);
                this.gateMeshes[MESH_MAIN].setAnimationRangeInTime(0, 51);
                this.gateMeshes[MESH_MAIN].setAnimationMode((byte) 2);
            }

            if (this.gateMeshes[MESH_NL] != null) {
                this.gateMeshes[MESH_NL].setAnimationSpeed(100);
                this.gateMeshes[MESH_NL].setAnimationRangeInTime(0, 51);
                this.gateMeshes[MESH_NL].setAnimationMode((byte) 2);
            }
        }
    }

    public final boolean isActivated() {
        return this.activated;
    }

    public final void setPosition(int var1, int var2, int var3) {
        this.var_249 = var1;
        this.var_352 = var2;
        this.var_3c2 = var3;
        if (this.gateMeshes != null) {
            for (int i = 0; i < this.gateMeshes.length; i++) {
                if (this.gateMeshes[i] != null) {
                    this.gateMeshes[i].moveTo(var1, var2, var3);
                }
            }
        }
    }

    public void OnRelease() {
        super.OnRelease();
        this.gateMeshes = null;
    }

    public void update(long var1) {
        super.update(var1);
        if (this.gateMeshes != null && this.mainMesh_ != null) {
            AE.Math.AEVector3D p = this.mainMesh_.getLocalPos();
            AE.Math.AEVector3D s = this.mainMesh_.getScale();
            for (int i = 0; i < this.gateMeshes.length; i++) {
                AbstractMesh m = this.gateMeshes[i];
                if (m != null && m != this.mainMesh_) {
                    m.moveTo(p);
                    m.setScale(s.x, s.y, s.z);
                }
            }
        }
    }
	
    public void appendToRender() {
        if (this.gateMeshes != null) {
            for (int i = 0; i < this.gateMeshes.length; i++) {
                AbstractMesh m = this.gateMeshes[i];
                if (m != null && m.isVisible()) {
                    GlobalStatus.renderer.drawNodeInVF(m);
                }
            }
        } else {
            super.appendToRender();
        }
    }
}