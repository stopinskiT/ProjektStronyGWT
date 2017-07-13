package pl.stopinski.client;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

class WpisCell extends AbstractCell<Wpis>{

	public WpisCell() {
		super("click", "keydown");
	}

	@Override
	public void render(Context context, Wpis wpis, SafeHtmlBuilder sb) {
		if (wpis == null) {
			return;
		}
		sb.appendHtmlConstant("<div class='tytuly'><table><tr><td><b>");
		sb.appendEscaped(wpis.getData());
		sb.appendHtmlConstant("</b></td></tr><tr><td>");
		sb.appendEscaped(wpis.getTytul());
		sb.appendHtmlConstant("</td></tr></table></div>");
	}

	@Override
	public void onBrowserEvent(Context context, Element parent, Wpis wpis, NativeEvent event,
			ValueUpdater<Wpis> valueUpdater) {
		super.onBrowserEvent(context, parent, wpis, event, valueUpdater);
		if ("click".equals(event.getType())) {
			EventTarget eventTarget = event.getEventTarget();
			if (parent.getFirstChildElement().isOrHasChild(Element.as(eventTarget))) {
				doAction(wpis, valueUpdater);
			}
		}

	}

	@Override
	protected void onEnterKeyDown(Context context, Element parent, Wpis wpis, NativeEvent event,
			ValueUpdater<Wpis> valueUpdater) {
		doAction(wpis, valueUpdater);
	}

	private void doAction(Wpis wpis, ValueUpdater<Wpis> valueUpdater) {
		Przedsiewziecie.trescWpisu.setText(wpis.getTresc());
		valueUpdater.update(wpis);
	}
}