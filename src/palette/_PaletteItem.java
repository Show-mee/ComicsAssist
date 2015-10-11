package palette;

import java.awt.Color;
import java.awt.Point;

import canvas.CanvasItem;
import canvas.SmallPanel;
import canvas.PersistentCanvas;

/** 
 * @author Xiuming XU E-mail:gracexuxiuming@gmail.com
 * abstract class for the palette items
 * new feature: panel, all the shape items lay on the panel
 */
public abstract class _PaletteItem extends CanvasItem{

	public _PaletteItem(PersistentCanvas c, Color o, Color f) {
		super(c, o, f);
	}
}
