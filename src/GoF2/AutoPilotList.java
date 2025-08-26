package GoF2;

import AE.GlobalStatus;
import AE.PaintCanvas.Font;
import HardEngine.AEButtonManager;

// меню автопилота

public final class AutoPilotList {
	
	private int selection;
	private int posX;
	private int posY;
	private int width;
	private String[] rows = new String[5];
	private int rowsNum = 0;
	public static AEButtonManager[] autopilotListButton;
	
	public AutoPilotList(Level var1) {
		
		autopilotListButton = new AEButtonManager[rows.length];
		for(int count = 0; count < autopilotListButton.length; ++count) {
			autopilotListButton[count] = new AEButtonManager();
		}
		
		if(Level.programmedStation != null) {
			this.rows[0] = GlobalStatus.gameText.getText(270) + ": " + Level.programmedStation.getName(); // куда летим?
			++this.rowsNum;
		}
		
		if(Status.getSystem().sub_308()) {
			this.rows[1] = GlobalStatus.gameText.getText(271); // ретранслятор
			++this.rowsNum;
		}
		
		if(Status.getStation().getTecLevel() != 0) {
			this.rows[2] = Status.getStation().getName() + " " + GlobalStatus.gameText.getText(40); // станция
			++this.rowsNum;
		}
		
		this.rows[3] = GlobalStatus.gameText.getText(273); // поле астероидов
		++this.rowsNum;
		
		if(var1.getPlayer().getRoute() != null && !var1.getPlayer().getRoute().getLastWaypoint().reached_) {
			this.rows[4] = GlobalStatus.gameText.getText(294); // точка назначения
			++this.rowsNum;
		}
		
		this.width = 0;
		
		for(int var3 = 0; var3 < this.rows.length; ++var3) {
			int var2;
			if(this.rows[var3] != null && (var2 = Font.getTextWidth(this.rows[var3], 0)) + 19 > this.width) {
				this.width = var2 + 19;
			}
		}
		
		this.posX = (GlobalStatus.var_e75 - this.width) / 2;
		this.posY = (GlobalStatus.var_eb6 - (this.rowsNum * 10 + 12)) / 2;
		
		while(this.rows[this.selection] == null) {
			this.up();
		}
	}
	
	public final int getSelection() {
		return this.selection;
	}
	
	public final void down() {
		if(this.selection > 0) {
			--this.selection;
		} else {
			this.selection = 4;
		}
		
		if(this.rows[this.selection] == null) {
			this.down();
		}
	}
	
	public final void up() {
		if(this.selection < 4) {
			++this.selection;
		} else {
			this.selection = 0;
		}
		
		if(this.rows[this.selection] == null) {
			this.up();
		}
	}
	
	public final void draw() {
		Layout.drawMenuWindow(GlobalStatus.gameText.getText(293), autopilotListButton[3].standartButtonX - autopilotListButton[3].standartButtonWidth, this.posY, autopilotListButton[3].standartButtonWidth * 2,  12 + (this.rowsNum + 1) * (autopilotListButton[3].standartButtonHeight + this.rows.length) - 5);
		
		//SharedVariables.graphics.setColor(240, 0, 255);
		//SharedVariables.graphics.drawRect(autopilotListButton[3].standartButtonX - autopilotListButton[3].standartButtonWidth, this.var_189, autopilotListButton[3].standartButtonWidth * 2,  12 + (this.buttonID + 1) * (autopilotListButton[3].standartButtonHeight + this.buttonName.length) - 5);
		
		int var1 = 0;
		
		for(int var2 = 0; var2 < this.rows.length; ++var2) {
			if(this.rows[var2] != null) {
				autopilotListButton[var2].drawStandartButton(Globals.rectRoundedButtonNormal, Globals.rectRoundedButtonPressed, GlobalStatus.var_e75 / 2, this.posY + autopilotListButton[var2].standartButtonHeight + var1++ * (autopilotListButton[var2].standartButtonHeight + 5) + 30);
				Layout.drawTextItem(this.rows[var2], autopilotListButton[var2].standartButtonX, autopilotListButton[var2].standartButtonY, this.width, this.selection == var2);
			}
		}
	}
	
	public static void pointerPressed(int x, int y) {
		for(int count = 0; count < autopilotListButton.length; ++count) {
			if(autopilotListButton[count] != null) {
				autopilotListButton[count].standartButtonTouch(x, y);
			}
		}
	}
	
	public static void pointerReleased(int x, int y) {
		for(int count = 0; count < autopilotListButton.length; ++count) {
			if(autopilotListButton[count] != null) {
				autopilotListButton[count].buttonsTouchReleased(x, y);
			}
		}
	}
}
