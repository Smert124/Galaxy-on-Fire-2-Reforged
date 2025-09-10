package AE;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.m3g.Appearance;
import javax.microedition.m3g.CompositingMode;
import javax.microedition.m3g.Material;
import javax.microedition.m3g.Mesh;
import javax.microedition.m3g.Node;
import javax.microedition.m3g.PolygonMode;
import javax.microedition.m3g.Texture2D;
import javax.microedition.m3g.Transform;
import javax.microedition.m3g.TriangleStripArray;
import javax.microedition.m3g.VertexArray;
import javax.microedition.m3g.VertexBuffer;

import AE.PaintCanvas.AEGraphics3D;

public final class AEMesh extends AbstractMesh {
	
	private static float[] transformArray = new float[16];
    private DataInputStream aemFile; //var_68
    public Node node; //var_89
    private Appearance appearance;
    private TriangleStripArray triangleStripArray;
    public Transform transform;
    private boolean isGlowing; //rendering switch
    private boolean isTransparent;
    private static PolygonMode opaquePMode;
    private static PolygonMode transparentPMode;
    private static CompositingMode transparetCompositing;
    private static CompositingMode additiveCompositing;
    private static CompositingMode opaqueCompositing;
    private static Material specularMaterial;
    private static Material emissivMaterial;

    public AEMesh(int var1, String argPath, int var2) {
        this.resourceId = var1;
        setupMaterial();
        String path = argPath;
        AEMesh This = this;

        try {
            if (!path.endsWith(".aem")) {
                This.aemFile = new DataInputStream(This.getClass().getResourceAsStream(path + ".aem"));
            } else {
                This.aemFile = new DataInputStream(This.getClass().getResourceAsStream(path));
            }

            byte[] magic = new byte[9];
            This.aemFile.read(magic, 0, 9); //(buffer, offset, length)
            int version = magic[1] - (byte) '0';

            int bufferSize, i, flags;
            flags = This.aemFile.readUnsignedByte();

            if (version == 4) {
                This.aemFile.skip(14);
            }

            if ((flags & 16) != 0) {
                bufferSize = swapBytes(This.aemFile.readUnsignedShort());
                int[] indices = new int[bufferSize];
                int[] stripLengths = new int[bufferSize / 3];
                for (i = 0; i < bufferSize; ++i) {
                    indices[i] = swapBytes(This.aemFile.readUnsignedShort());
                    if (i % 3 == 0) {
                        stripLengths[i / 3] = 3;
                    }
                }

                This.triangleStripArray = new TriangleStripArray(indices, stripLengths);
                bufferSize = swapBytes(This.aemFile.readUnsignedShort());
                short[] vertexCords = new short[bufferSize * 3];
                if (version == 2) {
                    short sign, cord;
                    for (i = 0; i < bufferSize * 3; ++i) {
                        cord = (short) swapBytes(This.aemFile.readShort());
                        sign = (short) swapBytes(This.aemFile.readShort());
                        if ((sign == -1 && cord >= 0) || (sign == 0 && cord < 0)) {
                            cord *= -1;
                        }
                        vertexCords[i] = cord;
                    }
                } else if (version == 4) {
                    for (i = 0; i < bufferSize * 3; ++i) {
                        vertexCords[i] = (short) intBitRevesedToFloat(This.aemFile.readInt());
                    }
                }
                VertexArray vertexArray = new VertexArray(vertexCords.length / 3, 3, 2);
                vertexArray.set(0, vertexCords.length / 3, vertexCords);
                VertexArray uvArray = null;
                if ((flags & 2) != 0) {
                    short[] uvBuffer = new short[vertexCords.length / 3 << 1];
                    if (version == 2) {
                        for (i = 0; i < uvBuffer.length; i += 2) {
                            uvBuffer[i] = (short) swapBytes(This.aemFile.readShort());
                            uvBuffer[i + 1] = (short) (4096 - (short) swapBytes(This.aemFile.readShort()));
                        }
                    } else if (version == 4) {
                        for (i = 0; i < uvBuffer.length; i += 2) {
                            uvBuffer[i] = (short) intBitRevesedToFloat(This.aemFile.readInt(), 4096);
                            uvBuffer[i + 1] = (short) (4096 - (short) intBitRevesedToFloat(This.aemFile.readInt(), 4096));
                        }
                    }

                    (uvArray = new VertexArray(uvBuffer.length / 2, 2, 2)).set(0, uvBuffer.length / 2, uvBuffer);
                }
                VertexArray normalArray = null;
                if ((flags & 4) != 0 && version == 4) {
                    float[] normalsBuffer = new float[vertexCords.length];

                    for (i = 0; i < normalsBuffer.length; ++i) {
                        normalsBuffer[i] = intBitRevesedToFloat(This.aemFile.readInt());
                    }

                    // Преобразование float нормалей в short перед передачей в VertexArray
                    short[] shortNormalsBuffer = new short[normalsBuffer.length];
                    for (i = 0; i < normalsBuffer.length; ++i) {
                        shortNormalsBuffer[i] = (short) (normalsBuffer[i] * 32767); // Масштабирование float значений до short
                    }

                    normalArray = new VertexArray(shortNormalsBuffer.length / 3, 3, 2);
                    normalArray.set(0, shortNormalsBuffer.length / 3, shortNormalsBuffer);
                }

                VertexBuffer vertexBuffer = new VertexBuffer();
                vertexBuffer.setPositions(vertexArray, 1.0F, (float[]) null);
                vertexBuffer.setNormals(normalArray);
                if (uvArray != null) { // Проверка на null
                    vertexBuffer.setTexCoords(0, uvArray, 0.000244140625F, (float[]) null);
                }

                // Создание Mesh и Node
                Mesh mesh = new Mesh(vertexBuffer, This.triangleStripArray, null);
                This.node = mesh;

                This.isGlowing = false; //set material 1, 0
                This.isTransparent = false;
                if (path.endsWith("_add.aem")) {
                    This.isGlowing = true;
                }
                if (path.endsWith("_alpha.aem")) {
                    This.isTransparent = true;
                }

                This.transform = new Transform();
                This.transform.setIdentity();
                This.aemFile.close();
            }
        } catch (Exception var10) {
            System.out.println("ERROR | AEMeshLoader(" + argPath + ") loading error! The file is not in a valid format/damaged/missing!");
            var10.printStackTrace();
        }

        this.radius = var2;
    }

    private AEMesh(AEMesh loader) {
        super(loader);
        setupMaterial();
        this.node = loader.node;
        this.triangleStripArray = loader.triangleStripArray;
        this.transform = loader.transform;
        this.isGlowing = loader.isGlowing;
        this.renderLayer = loader.renderLayer;
        this.draw = loader.draw;
        this.radius = loader.radius;
        this.resourceId = loader.resourceId;
    }

    private static void setupMaterial() { //void sub_30
        if (opaquePMode == null) {
            (opaquePMode = new PolygonMode()).setCulling(PolygonMode.CULL_BACK);// 	CULL_BACK
            opaquePMode.setShading(PolygonMode.SHADE_SMOOTH);// SHADE_SMOOTH
            opaquePMode.setPerspectiveCorrectionEnable(true);
            opaquePMode.setLocalCameraLightingEnable(true);
            opaquePMode.setTwoSidedLightingEnable(true);
            opaquePMode.setWinding(PolygonMode.WINDING_CCW); //WINDING_CCW
        }

        if (transparentPMode == null) {
            (transparentPMode = new PolygonMode()).setCulling(PolygonMode.CULL_BACK); //CULL_NONE
            transparentPMode.setShading(PolygonMode.SHADE_FLAT); // SHADE_SMOOTH
            transparentPMode.setPerspectiveCorrectionEnable(true);
        }

        if (additiveCompositing == null) {
            (additiveCompositing = new CompositingMode()).setBlending(CompositingMode.ALPHA_ADD);
            additiveCompositing.setDepthTestEnable(true);
            additiveCompositing.setDepthWriteEnable(false);
        }
        if (transparetCompositing == null) {
            (transparetCompositing = new CompositingMode()).setBlending(CompositingMode.ALPHA);
            transparetCompositing.setDepthTestEnable(true);
            transparetCompositing.setDepthWriteEnable(false);
        }

        if (opaqueCompositing == null) {
            (opaqueCompositing = new CompositingMode()).setBlending(CompositingMode.ALPHA);
            opaqueCompositing.setDepthTestEnable(true);
            opaqueCompositing.setDepthWriteEnable(true);
        }

        if(specularMaterial == null) {
            (specularMaterial = new Material()).setColor(Material.DIFFUSE, 0xFF444444); //diffuse, ARGB
            specularMaterial.setColor(Material.SPECULAR, GoF2.Level.skyNormalizedLight()); //specular, ARGB
            specularMaterial.setVertexColorTrackingEnable(false);
            specularMaterial.setShininess(127.0F); //1-127
        }
        if(emissivMaterial == null) {
            (emissivMaterial = new Material()).setColor(Material.EMISSIVE, 0xFFFFFFFF); //EMISSIVE
            emissivMaterial.setVertexColorTrackingEnable(true);
        }
    }

    public final void setTexture(ITexture var1) {
        Texture2D[] var2 = ((JSRTexture) var1).getTexturesArray();
        if (this.node instanceof Mesh) {
            Mesh mesh = (Mesh) this.node;
            for (int i = 0; i < mesh.getSubmeshCount(); i++) {
                Appearance appearance = new Appearance();
                appearance.setTexture(0, var2[0]);
                if(this.isGlowing) {
                    appearance.setCompositingMode(additiveCompositing);
                    appearance.setPolygonMode(transparentPMode);
                } else if(this.isTransparent) {
                    appearance.setCompositingMode(transparetCompositing);
                    appearance.setPolygonMode(transparentPMode);
                } else {
                    appearance.setCompositingMode(opaqueCompositing);
                    appearance.setPolygonMode(opaquePMode);
                    appearance.setMaterial(specularMaterial);
                }
                mesh.setAppearance(i, appearance);
            }
        }
    }

    public final GraphNode clone() {
        return new AEMesh(this);
    }

    public final void OnRelease() {}

    public final void render() { //render on false
        if (!this.isGlowing) {
            this.matrix.toFloatArray(transformArray);
            this.transform.set(transformArray);
            if (this.node != null && AEGraphics3D.graphics3D != null) {
                AEGraphics3D.graphics3D.render(this.node, this.transform);
            }
        }
    }

    public final void renderTransparent() { //render on true
        if (this.isGlowing) {
            this.matrix.toFloatArray(transformArray);
            this.transform.set(transformArray);
            if (this.node != null && AEGraphics3D.graphics3D != null) {
                AEGraphics3D.graphics3D.render(this.node, this.transform);
            }
        }
    }
	
	public void checkNormals() {
		if(node instanceof Mesh) {
			Mesh mesh = (Mesh) node;
			VertexBuffer vertexBuffer = mesh.getVertexBuffer();
			
			// Получите нормали из VertexBuffer
			VertexArray normalArray = vertexBuffer.getNormals();
			
			// Проверьте, что нормали доступны
			if(normalArray != null) {
				System.out.println("Normals are available.");
				
				// Создайте буфер для хранения нормалей
				short[] normalBuffer = new short[3];
				
				// Получите первую нормаль
				normalArray.get(0, 1, normalBuffer); // 0 - индекс вершины, 1 - количество нормалей
				short nx = normalBuffer[0];
				short ny = normalBuffer[1];
				short nz = normalBuffer[2];
				System.out.println("First normal: (" + nx + ", " + ny + ", " + nz + ")");
			} else {
				System.out.println("Normals are not available.");
			}
		}
	}

    private static int swapBytes(int var0) { //sub_2a5
        return (var0 & 255) << 8 | var0 >> 8 & 255;
    }

    private static float intBitRevesedToFloat(int rawInt) throws IOException {
        int swappedInt = ((rawInt & 0xff000000) >>> 24) |
                ((rawInt & 0x00ff0000) >>> 8) |
                ((rawInt & 0x0000ff00) << 8) |
                ((rawInt & 0x000000ff) << 24);
        return Float.intBitsToFloat(swappedInt);
    }

    private static float intBitRevesedToFloat(int rawInt, int scale) throws IOException {
        int swappedInt = ((rawInt & 0xff000000) >>> 24) |
                ((rawInt & 0x00ff0000) >>> 8) |
                ((rawInt & 0x0000ff00) << 8) |
                ((rawInt & 0x000000ff) << 24);
        return Float.intBitsToFloat(swappedInt) * scale;
    }
}
