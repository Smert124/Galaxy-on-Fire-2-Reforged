package GoF2;

import AE.AEResourceManager;
import AE.GlobalStatus;
import HardEngine.TextParser;

public final class FileRead {
	
	public final Station loadStation(int var1) {
		return loadStationsBinary(new short[]{(short)var1})[0];
	}
	
	public static void MASTER_CONFIG() {
		try {
			String all_text = AEResourceManager.getText(1);
			for(int i = 0; i < 7; ++i) {
				String[] data = TextParser.split(all_text, ";");
				if(data.length < i) {
					data = null;
				}
				
				String[] parts = TextParser.split(data[i].trim(), ",");
				if(parts.length == 0) {
					parts = null;
				}
				
				switch(i) {
					
					case 0:
						GlobalStatus.max_stations = Integer.parseInt(parts[1].trim());
					//	System.out.println(parts[0] + ": " + parts[1]);
					break;
					
					
					
					case 1:
						GlobalStatus.max_systems = Integer.parseInt(parts[1].trim());
					//	System.out.println(parts[0] + ": " + parts[1]);
					break;
					
					
					
					case 2:
						GlobalStatus.max_ships = Integer.parseInt(parts[1].trim());
					//	System.out.println(parts[0] + ": " + parts[1]);
					break;
					
					
					
					case 3:
						GlobalStatus.max_items = Integer.parseInt(parts[1].trim());
					//	System.out.println(parts[0] + ": " + parts[1]);
					break;
					
					
					
					case 4:
						GlobalStatus.STATION_COLLISION_BOX_VISIBLE = Integer.parseInt(parts[1].trim()) == 1;
					//	System.out.println(parts[0] + ": " + parts[1]);
					break;
					
					
					
					case 5:
						GlobalStatus.shields = Integer.parseInt(parts[1].trim());
					//	System.out.println(parts[0] + ": " + parts[1]);
					break;
					
					
					
					case 6:
						GlobalStatus.NEURAL_NPC = Integer.parseInt(parts[1].trim()) == 1;
					//	System.out.println(parts[0] + ": " + parts[1]);
					break;
					
				}
				parts = null;
				data = null;
			}
			all_text = null;
			System.gc();
		} catch(Exception ex) {
			GlobalStatus.CATCHED_ERROR = "MASTER CONFIG ERROR: " + ex;
			System.out.println(GlobalStatus.CATCHED_ERROR);
		}
	}
	
	private static Station[] loadStationsBinary(short[] var0) {
		Station[] var1 = null;
		try {
			String all_text = AEResourceManager.getText(10);
			var1 = new Station[var0.length];
			int var8 = 0;
			for(int var9 = 0; var9 < GlobalStatus.max_stations; ++var9) {
				String[] data = TextParser.split(all_text, ";");
				if(data.length < var9) {
					data = null;
				}
				String[] parts = TextParser.split(data[var9], ",");
				if(parts.length == 0) {
					parts = null;
				}
				
				String var3 = parts[0].trim();
				int var4 = Integer.parseInt(parts[1].trim());
				int var5 = Integer.parseInt(parts[2].trim());
				int var6 = Integer.parseInt(parts[3].trim());
				int var7 = Integer.parseInt(parts[4].trim());
				
				for(int var10 = 0; var10 < var0.length; ++var10) {
					if(var0[var10] == var9) {
						var1[var8++] = new Station(var3, var4, var5, var6, var7);
					}
				}
				parts = null;
				data = null;
				System.gc();
			}
			all_text = null;
		} catch (Exception var11) {
			GlobalStatus.CATCHED_ERROR = "loadStationsBinary[] ERROR: " + var11;
			System.out.println(GlobalStatus.CATCHED_ERROR);
		}
		return var1;
	}
	
	public static int[] loadShipParts(int call_parameter) {
		int[] shippart_parameter = null;
		try {
			String all_text = AEResourceManager.getText(5);
			for(int i = 0; i < GlobalStatus.max_ships; ++i) {
				String[] data = TextParser.split(all_text, ";");
				if(data.length < i) {
					return null;
				}
				String[] parts = TextParser.split(data[i], ",");
				if(parts.length == 0) {
					return null;
				}
				byte shippart_id = Byte.parseByte(parts[0].trim());
				shippart_parameter = new int[Byte.parseByte(parts[1].trim()) * 10];
				
				for(int var5 = 0; var5 < shippart_parameter.length; var5 += 10) {
					shippart_parameter[var5] = Short.parseShort(parts[var5 + 2].trim()); // модель
					shippart_parameter[var5 + 1] = Integer.parseInt(parts[var5 + 3].trim()); // XYZ
					shippart_parameter[var5 + 2] = Integer.parseInt(parts[var5 + 4].trim()); // XYZ
					shippart_parameter[var5 + 3] = Integer.parseInt(parts[var5 + 5].trim()); // XYZ
					shippart_parameter[var5 + 4] = Short.parseShort(parts[var5 + 6].trim()); // XYZ1
					shippart_parameter[var5 + 5] = Short.parseShort(parts[var5 + 7].trim());  // XYZ1
					shippart_parameter[var5 + 6] = Short.parseShort(parts[var5 + 8].trim()); // XYZ1
					shippart_parameter[var5 + 7] = Short.parseShort(parts[var5 + 9].trim()); // XYZ2
					shippart_parameter[var5 + 8] = Short.parseShort(parts[var5 + 10].trim()); // XYZ2
					shippart_parameter[var5 + 9] = Short.parseShort(parts[var5 + 11].trim()); // XYZ2
				}
				if(shippart_id == call_parameter) {
					return shippart_parameter;
				}
				parts = null;
				data = null;
			}
			all_text = null;
		} catch (Exception var6) {
			GlobalStatus.CATCHED_ERROR = "loadShipparts ERROR: " + var6;
			System.out.println(GlobalStatus.CATCHED_ERROR);
		}
		return shippart_parameter;
	}
	
	public static short[] readWeaponProjectile(int value) {
		short[] wepon_projectile = new short[GlobalStatus.max_items];
		try {
			String all_text = AEResourceManager.getText(4);
			String[] data = TextParser.split(all_text, ";");
			for(int i = 0; i < wepon_projectile.length; ++i) {
				if(data.length < i) {
					data = null;
				}
				String[] parts = TextParser.split(data[i], ",");
				if(parts.length == 0) {
					parts = null;
				}
				wepon_projectile[i] = Short.parseShort(parts[value].trim());
			}
			data = null;
			System.gc();
		} catch (Exception ex) {
			GlobalStatus.CATCHED_ERROR = "readWeaponProjectile ERROR: " + ex;
			System.out.println(GlobalStatus.CATCHED_ERROR);
		}
		return wepon_projectile;
	}
	
	public static short[] readShipViewportOffset(int value) {
		short[] viewport_offset = new short[GlobalStatus.max_ships];
		try {
			String all_text = AEResourceManager.getText(8);
			String[] data = TextParser.split(all_text, ";");
			for(int i = 0; i < GlobalStatus.max_ships; ++i) {
				if(data.length < i) {
					data = null;
				}
				String[] parts = TextParser.split(data[i], ",");
				if(parts.length == 0) {
					parts = null;
				}
				viewport_offset[i] = Short.parseShort(parts[value].trim());
			}
			data = null;
			System.gc();
		} catch (Exception ex) {
			GlobalStatus.CATCHED_ERROR = "readShipViewportOffset ERROR: " + ex;
			System.out.println(GlobalStatus.CATCHED_ERROR);
		}
		return viewport_offset;
	}
	
	public static int[] loadStationParts(int var0, int var1) {
		int[] stationpart_parameter = null;
		try {
			String all_text = AEResourceManager.getText(9);
			String[] data = TextParser.split(all_text, ";");
			for(int i = 0; i < GlobalStatus.max_stations; ++i) {
				if(data.length < i) {
					return null;
				}
				String[] parts = TextParser.split(data[i], ",");
				if(parts.length == 0) {
					return null;
				}
				
				byte stationpart_id = Byte.parseByte(parts[0].trim());
				short var6 = Short.parseShort(parts[2].trim());
				stationpart_parameter = new int[Byte.parseByte(parts[1].trim()) * 7];
				
				for(int var5 = 0; var5 < stationpart_parameter.length; var5 += 7) {
					stationpart_parameter[var5] = Short.parseShort(parts[var5 + 2].trim());
					stationpart_parameter[var5 + 1] = Integer.parseInt(parts[var5 + 3].trim());
					stationpart_parameter[var5 + 2] = Integer.parseInt(parts[var5 + 4].trim());
					stationpart_parameter[var5 + 3] = Integer.parseInt(parts[var5 + 5].trim());
					stationpart_parameter[var5 + 4] = Short.parseShort(parts[var5 + 6].trim());
					stationpart_parameter[var5 + 5] = Short.parseShort(parts[var5 + 7].trim());
					stationpart_parameter[var5 + 6] = Short.parseShort(parts[var5 + 8].trim());
				}
				if(stationpart_id == var0) {
					return stationpart_parameter;
				}
			}
		} catch (Exception var7) {
			GlobalStatus.CATCHED_ERROR = "loadStationparts ERROR: " + var7.getMessage();
			System.out.println(GlobalStatus.CATCHED_ERROR);
		}
		return stationpart_parameter;
	}
	
	public static int[] loadStationPartCollision(int var0, int var1) {
		int[] collision_parameter = null;
		try {
			String all_text = AEResourceManager.getText(13);
			String[] data = TextParser.split(all_text, ";");
			
			for(int i = 0; i < data.length; ++i) {
				if(data[i] == null || data[i].trim().length() == 0) {
					continue;
				}
				
				String[] parts = TextParser.split(data[i].trim(), ",");
				if(parts.length < 7) {
					continue;
				}
				
				int stationpart_id = Integer.parseInt(parts[0].trim());
				int collisionBox_count = Integer.parseInt(parts[1].trim());
				
				int requiredLength = 2 + collisionBox_count * 6;
				if(parts.length < requiredLength) {
					System.out.println("Not enough data for collision boxes for ID: " + stationpart_id);
					continue;
				}
				
				collision_parameter = new int[collisionBox_count * 6];
				
				for(int boxIndex = 0; boxIndex < collisionBox_count; boxIndex++) {
					int baseIndex = 2 + boxIndex * 6;
					
					collision_parameter[boxIndex * 6] = Integer.parseInt(parts[baseIndex].trim());     // X позиция
					collision_parameter[boxIndex * 6 + 1] = Integer.parseInt(parts[baseIndex + 1].trim()); // Y позиция
					collision_parameter[boxIndex * 6 + 2] = Integer.parseInt(parts[baseIndex + 2].trim()); // Z позиция
					collision_parameter[boxIndex * 6 + 3] = Integer.parseInt(parts[baseIndex + 3].trim()); // X размер
					collision_parameter[boxIndex * 6 + 4] = Integer.parseInt(parts[baseIndex + 4].trim()); // Y размер
					collision_parameter[boxIndex * 6 + 5] = Integer.parseInt(parts[baseIndex + 5].trim()); // Z размер
				}
				
				
					if(stationpart_id == var0) {
						if(GlobalStatus.STATION_COLLISION_BOX_VISIBLE) {
							System.out.println("Collision data for mesh ID: " + stationpart_id);
							System.out.println("Number of collision boxes: " + collisionBox_count);
							
							for(int boxIndex = 0; boxIndex < collisionBox_count; boxIndex++) {
								System.out.println("Box " + (boxIndex + 1) + ":");
								
								System.out.println("  Position relative to the mesh: (" + collision_parameter[boxIndex * 6] + ", " + collision_parameter[boxIndex * 6 + 1] + ", " + collision_parameter[boxIndex * 6 + 2] + ")");
								
								System.out.println("  Size: (" + collision_parameter[boxIndex * 6 + 3] + ", " + collision_parameter[boxIndex * 6 + 4] + ", " + collision_parameter[boxIndex * 6 + 5] + ")");
							}
						}
						return collision_parameter;
					}
				
			}
		} catch (Exception var7) {
			GlobalStatus.CATCHED_ERROR = "loadStationPartCollision ERROR: " + var7.getMessage();
			System.out.println(GlobalStatus.CATCHED_ERROR);
		}
		return collision_parameter;
	}
	
	public static Station[] loadStationsBinary(SolarSystem var0) {
		Station[] var1 = null;
		try {
			int[] var12 = var0.getStations();
			String all_text = AEResourceManager.getText(10);
			var1 = new Station[var12.length];
			int var8 = 0;
			for(int var9 = 0; var9 < GlobalStatus.max_stations; ++var9) {
				String[] data = TextParser.split(all_text, ";");
				if(data.length < var9) {
					data = null;
				}
				String[] parts = TextParser.split(data[var9], ",");
				if(parts.length == 0) {
					parts = null;
				}
				
				String station_name = parts[0].trim();
				int station_number = Integer.parseInt(parts[1].trim());
				int system_number = Integer.parseInt(parts[2].trim());
				int station_techlevel = Integer.parseInt(parts[3].trim());
				int station_planet_image = Integer.parseInt(parts[4].trim());
				
				for(int var10 = 0; var10 < var12.length; ++var10) {
					if(var12[var10] == var9) {
						var1[var8++] = new Station(station_name, station_number, system_number, station_techlevel, station_planet_image);
					}
					if(var8 == var12.length) {
						return var1;
					}
				}
				parts = null;
				data = null;
			}
			all_text = null;
		} catch (Exception var11) {
			GlobalStatus.CATCHED_ERROR = "loadStationsBinary[] (SolarSystem) ERROR: " + var11;
			System.out.println(GlobalStatus.CATCHED_ERROR);
		}
		return var1;
	}
	
	public static Agent[] loadAgents() {
		Agent[] var0 = null;
		try {
			String all_text = AEResourceManager.getText(2);
			String[] lines = TextParser.split(all_text, ";");
			var0 = new Agent[GlobalStatus.max_agents];
			for(int var12 = 0; var12 < var0.length; ++var12) {
				String line = lines[var12].trim();
				if(line.length() == 0) {
					continue;
				}
				String[] parts = TextParser.split(line, ",");
				if(parts.length < 9) {
					continue;
				}
				
				String var2 = parts[0].trim();
				int var3 = Integer.parseInt(parts[1]);
				int var4 = Integer.parseInt(parts[2]);
				int var5 = Integer.parseInt(parts[3]);
				int var6 = Integer.parseInt(parts[4]);
				boolean var7 = Integer.parseInt(parts[5]) == 1;
				int var8 = Integer.parseInt(parts[6]);
				int var9 = Integer.parseInt(parts[7]);
				int var10 = Integer.parseInt(parts[8]);
				
				var0[var12] = new Agent(var3, var2, var4, var5, var6, var7, var8, var9, var10);
				
				if(parts.length > 9) {
					int var15 = Integer.parseInt(parts[9]);
					if(var15 > 0) {
						byte[] var16 = new byte[var15];
						for(int i = 0; i < var15; i++) {
							var16[i] = Byte.parseByte(parts[10 + i]);
						}
						var0[var12].setImageParts(var16);
					}
				}
				
				line = null;
				parts = null;
			}
			all_text = null;
			lines = null;
			System.gc();
			
		} catch (Exception var13) {
			GlobalStatus.CATCHED_ERROR = "loadAgents ERROR: " + var13;
			System.out.println(GlobalStatus.CATCHED_ERROR);
		}
		return var0;
	}
	
	public static int[][] loadTurrets() {
		int parameter[][] = null;
		try {
			String all_text = AEResourceManager.getText(7);
			String[] data = TextParser.split(all_text, ";");
			parameter = new int[data.length - 1][4];
			for(int x = 0; x < data.length - 1; ++x) {
				if(data.length - 1 < x) {
					return null;
				}
				String[] parts = TextParser.split(data[x].trim(), ",");
				if(parts.length == 0) {
				   return null;
				}
				for(int j = 0; j < parts.length; j++) {
					parameter[x][j] = Integer.parseInt(parts[j].trim());
				}
				parts = null;
				
			}
			data = null;
			all_text = null;
			System.gc();
		} catch (Exception ex) {
			GlobalStatus.CATCHED_ERROR = "loadTurrets ERROR: " + ex.getMessage();
			System.out.println(GlobalStatus.CATCHED_ERROR);
		}
		return parameter;
	}
	
	public static int loadInterface(int lineNumber, int paramIndex) {
		try {
			String allText = AEResourceManager.getText(12);
			String[] lines = TextParser.split(allText, ";");
			if(lineNumber >= lines.length) {
				return 0;
			}
			String line = lines[lineNumber].trim();
			String[] parts = TextParser.split(line, ",");
			if(paramIndex >= 1 && paramIndex < parts.length) {
				return Integer.parseInt(parts[paramIndex].trim());
			}
			lines = null;
			allText = null;
			System.gc();
		} catch (Exception ex) {
			GlobalStatus.CATCHED_ERROR = "loadInterface ERROR: " + ex.getMessage();
			System.out.println(GlobalStatus.CATCHED_ERROR);
		}
		return 0;
	}
	
	public static SolarSystem[] loadSystemsBinary() {
		SolarSystem[] var0 = null;
		try {
			String all_text = AEResourceManager.getText(11);
			var0 = new SolarSystem[GlobalStatus.max_systems];
			for(int system_number = 0; system_number < GlobalStatus.max_systems; ++system_number) {
				String[] data = TextParser.split(all_text, ";");
				if(data.length < system_number) {
					data = null;
				}
				String[] parts = TextParser.split(data[system_number].trim(), ",");
				if(parts.length == 0) {
					parts = null;
				}
				
				String system_name = parts[0].trim();
				int system_safety = Integer.parseInt(parts[1].trim());
				boolean system_visible = Integer.parseInt(parts[2].trim()) == 1;
				boolean system_visible_debug = true;
				int system_race = Integer.parseInt(parts[3].trim());
				int system_x = Integer.parseInt(parts[4].trim());
				int system_y = Integer.parseInt(parts[5].trim());
				int system_z = Integer.parseInt(parts[6].trim());
				int system_retranslator_installed = Integer.parseInt(parts[7].trim());
				int sun_type = Integer.parseInt(parts[8].trim());
				int system_color_RGB[] = {Integer.parseInt(parts[9].trim()), Integer.parseInt(parts[10].trim()), Integer.parseInt(parts[11].trim())};
				int system_stations_id[] = new int[Integer.parseInt(parts[12].trim())];
				
				int var14;
				for(var14 = 0; var14 < system_stations_id.length; ++var14) {
					system_stations_id[var14] = Integer.parseInt(parts[13 + var14].trim());
				}
				
				int[] systems_end_file = new int[0];
				int[] systems_retranslator = new int[0];
				var14 = Integer.parseInt(parts[13 + system_stations_id.length].trim());
				if(var14 > 0) {
					systems_retranslator = new int[var14];
					for(var14 = 0; var14 < systems_retranslator.length; ++var14) {
						systems_retranslator[var14] = Integer.parseInt(parts[14 + system_stations_id.length + var14].trim());
					}
				}
				
				if(GlobalStatus.cheat_mode == true) {
					system_visible_debug = true;
				} else {
					system_visible_debug = system_visible;
				}
				
				var0[system_number] = new SolarSystem(system_number, system_name, system_safety, system_visible_debug, system_race, system_x, system_y, system_z, system_retranslator_installed, sun_type, system_color_RGB, system_stations_id, systems_retranslator, systems_end_file);
				parts = null;
				data = null;
			}
			all_text = null;
		} catch (Exception var17) {
			GlobalStatus.CATCHED_ERROR = "loadSystemsBinary ERROR: " + var17;
			System.out.println(GlobalStatus.CATCHED_ERROR);
		}
		return var0;
	}
	
	public static Item[] loadItemsBinary() {
		Item[] var0 = null;
		try {
			String all_text = AEResourceManager.getText(3);
			String[] lines = TextParser.split(all_text, ";");
			var0 = new Item[GlobalStatus.max_items];
			for(int var6 = 0; var6 < GlobalStatus.max_items; ++var6) {
				String line = lines[var6].trim();
				if(line.length() == 0) {
					return null;
				}
				String[] parts = TextParser.split(line, ",");
				int[] var3 = null;
				int[] var4 = null;
				int[] var5 = null;
				
				if(parts.length > 0) {
					int lengthVar3 = Integer.parseInt(parts[0]);
					if(lengthVar3 > 0) {
						var3 = new int[lengthVar3];
						for(int i = 0; i < var3.length; i++) {
							var3[i] = Integer.parseInt(parts[1 + i]);
						}
					}
				}
				
				if(parts.length > (var3 != null ? var3.length : 0) + 1) {
					int lengthVar4 = Integer.parseInt(parts[(var3 != null ? var3.length : 0) + 1]);
					if(lengthVar4 > 0) {
						var4 = new int[lengthVar4];
						for(int i = 0; i < var4.length; i++) {
							var4[i] = Integer.parseInt(parts[(var3 != null ? var3.length : 0) + 2 + i]);
						}
					}
				}
				
				if(parts.length > (var3 != null ? var3.length : 0) + (var4 != null ? var4.length : 0) + 2) {
					int lengthVar5 = Integer.parseInt(parts[(var3 != null ? var3.length : 0) + (var4 != null ? var4.length : 0) + 2]);
					if(lengthVar5 > 0) {
						var5 = new int[lengthVar5];
						for(int i = 0; i < var5.length; i++) {
							var5[i] = Integer.parseInt(parts[(var3 != null ? var3.length : 0) + (var4 != null ? var4.length : 0) + 3 + i]);
						}
					}
				}
				
				var0[var6] = new Item(var3, var4, var5);
				
				var3 = null;
				var4 = null;
				var5 = null;
				line = null;
				parts = null;
			}
			lines = null;
			all_text = null;
			System.gc();
			
		} catch (Exception var8) {
			GlobalStatus.CATCHED_ERROR = "loadItemsBinary ERROR: " + var8;
			System.out.println(GlobalStatus.CATCHED_ERROR);
		}
		return var0;
	}
	
	public static Ship[] loadShipsBinary() {
		Ship[] var0 = null;
		try {
			String all_text = AEResourceManager.getText(6);
			all_text = TextParser.removeComments(all_text);
			var0 = new Ship[GlobalStatus.max_ships];
			String[] ship_name = new String[GlobalStatus.max_ships];
			String[] data = TextParser.split(all_text, ";");
			for(int i = 0; i < Math.min(data.length, GlobalStatus.max_ships); ++i) {
				if(data[i] == null || data[i].trim().length() == 0) {
					continue;
				}
				String[] parts = TextParser.split(data[i].trim(), ",");
				if(parts.length < 10) {
					GlobalStatus.CATCHED_ERROR = "Invalid ship data at line " + i + ": " + data[i];
					System.out.println(GlobalStatus.CATCHED_ERROR);
					continue;
				}
				
				ship_name[i] = parts[0].trim();
				int ship_id = Integer.parseInt(parts[1].trim());
				int ship_armor = Integer.parseInt(parts[2].trim());
				int ship_cargo = Integer.parseInt(parts[3].trim());
				int ship_price = Integer.parseInt(parts[4].trim());
				int ship_primary_wepon = Integer.parseInt(parts[5].trim());
				int ship_secondary_weapon = Integer.parseInt(parts[6].trim());
				int ship_turret = Integer.parseInt(parts[7].trim());
				int ship_equipment = Integer.parseInt(parts[8].trim());
				int ship_control = Integer.parseInt(parts[9].trim());
				
				var0[i] = new Ship(ship_name, ship_id, ship_armor, ship_cargo, ship_price, ship_primary_wepon, ship_secondary_weapon, ship_turret, ship_equipment, (float)ship_control);
				
			//	System.out.println(ship_name[i] + "," + ship_id + "," + ship_armor + "," + ship_cargo + "," + ship_price + "," + ship_primary_wepon + "," + ship_secondary_weapon + "," + ship_turret + "," + ship_equipment + "," + ship_control + ";");
			}
			all_text = null;
			System.gc();
        } catch(Exception ex) {
			GlobalStatus.CATCHED_ERROR = "loadShipsBinary ERROR: " + ex;
			System.out.println(GlobalStatus.CATCHED_ERROR);
		}
		return var0;
	}
	
	
	public static String[] loadNamesBinary(int var0, boolean var1, boolean var2) {
		String[] names = null;
		int textId = -1;
		
		switch (var0) {
			
			case 0:
				textId = var2 ? (var1 ? 100 : 101) : 102;
            break;
			
			
			
			case 1:
				textId = var2 ? 103 : 104;
			break;
			
			
			
			case 2:
				textId = var2 ? 105 : 106;
            break;
			
			
			
			case 3:
				if(var2) {
					textId = GlobalStatus.random.nextInt(2) == 0 ? 100 : 105;
				} else {
					textId = GlobalStatus.random.nextInt(2) == 0 ? 102 : 106;
				}
            break;
			
			
			
			case 4:
				textId = var2 ? 107 : 108;
            break;
			
			
			
			case 5:
				if(!var2) {
					return null;
				}
				textId = 109;
            break;
			
			
			
			case 6:
				textId = var2 ? 110 : 111;
            break;
			
			
			
			case 7:
            if (!var2) {
                return null;
            }
            textId = 112;
            break;
			
			
			
			case 8:
				if(var2) {
					textId = GlobalStatus.random.nextInt(2) == 0 ? 100 : 105;
				} else {
					textId = GlobalStatus.random.nextInt(2) == 0 ? 102 : 106;
				}
            break;
			
			
			
			default:
				return null;
				
		}
		try {
			String all_text = AEResourceManager.getText(textId);
			if(all_text == null) {
				GlobalStatus.CATCHED_ERROR = "loadNamesBinary ERROR: Text not found for ID " + textId;
				System.out.println(GlobalStatus.CATCHED_ERROR);
				return null;
			}
			String[] data = TextParser.split(all_text, ";");
			int names_length = data.length - 1;
			names = new String[names_length];
			for(int count = 0; count < names_length; ++count) {
				names[count] = data[count].trim();
			}
		} catch (Exception var5) {
			GlobalStatus.CATCHED_ERROR = "loadNamesBinary ERROR: " + var5;
			System.out.println(GlobalStatus.CATCHED_ERROR);
		}
		return names;
	}
}