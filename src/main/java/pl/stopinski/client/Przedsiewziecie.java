package pl.stopinski.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.google.gwt.xml.client.XMLParser;
import com.google.gwt.xml.client.impl.DOMParseException;

public class Przedsiewziecie implements EntryPoint {

	private static ArrayList<Wpis> listaWpisow = new ArrayList<Wpis>();
	public static HTML trescWpisu = new HTML();
	private String XMLdoParsowania;

	public void onModuleLoad() {
		Resources resources = GWT.create(Resources.class);
		Image ja = new Image(resources.ja());
		ja.addStyleName("ja");
		Image kwiat = new Image(resources.kwiat());
		kwiat.setStyleName("motyw");
		XMLdoParsowania = resources.wpisy().getText();

		parsujXML();

		WpisCell wpisCell = new WpisCell();

		ProvidesKey<Wpis> keyProvider = new ProvidesKey<Wpis>() {
			public Object getKey(Wpis item) {
				return (item == null) ? null : item.getData();
			}
		};

		SelectionModel<Wpis> selectionModel = new SingleSelectionModel<Wpis>(keyProvider);
		CellList.Resources clResources = GWT.create(MyCellList.class);
		clResources.cellListStyle().ensureInjected();
		CellList<Wpis> listaTytulow = new CellList<Wpis>(wpisCell, clResources, keyProvider);
		listaTytulow.setRowCount(listaWpisow.size(), true);
		listaTytulow.setRowData(0, listaWpisow);
		listaTytulow.setSelectionModel(selectionModel);
		selectionModel.setSelected(listaWpisow.get(0), true);

		ListDataProvider<Wpis> dataProvider = new ListDataProvider<Wpis>();
		dataProvider.addDataDisplay(listaTytulow);

		List<Wpis> list = dataProvider.getList();
		for (Wpis wpis : listaWpisow) {
			list.add(wpis);
		}

		trescWpisu.setHTML(listaWpisow.get(0).getTresc());

		FlowPanel tytuly = new FlowPanel();
		tytuly.add(listaTytulow);

		PopupPanel wizytowka = new PopupPanel(true);
		wizytowka.setAnimationEnabled(true);
		wizytowka.setGlassEnabled(true);
		wizytowka.setTitle("Tadeusz Stopiński");
		VerticalPanel ukladWizytowki = new VerticalPanel();
		Button zamknij = new Button("X");
		zamknij.addStyleName("zamknij");
		zamknij.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				wizytowka.hide();
			}
		});
		ukladWizytowki.add(zamknij);
		ukladWizytowki.add(ja);
		StackLayoutPanel mojeDane = new StackLayoutPanel(Unit.PX);
		mojeDane.add(new HTMLPanel("Tadeusz Stopiński"), new HTMLPanel("Imię i nazwisko:"), 30);
		mojeDane.add(new HTMLPanel("stopinski.tadeusz@gmail.com"), new HTMLPanel("Kontakt:"), 30);
		mojeDane.add(
				new HTMLPanel(
						"<a href='https://www.facebook.com/tadeusz.stopinski.5' target='_blank'>Facebook</a></br><a href='https://twitter.com/t_stopinski' target='_blank'>Twitter</a></br><a href='https://www.instagram.com/quinta_essentia/' target='_blank'>Instagram</a>"),
				new HTMLPanel("Znajdź mnie w Internecie:"), 30);
		mojeDane.setPixelSize(250, 250);
		ukladWizytowki.add(mojeDane);
		wizytowka.setWidget(ukladWizytowki);

		DockLayoutPanel glownyPanel = new DockLayoutPanel(Unit.PX);

		FlowPanel toolbar = new FlowPanel();
		toolbar.addStyleName("naglowek");
		Button button = new Button("Autor");
		button.addStyleName("oMnieButton");
		button.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				wizytowka.center();
			}
		});
		toolbar.add(kwiat);
		toolbar.add(button);
		
		HTMLPanel stopka = new HTMLPanel("Strona zaprojektowana i wytworzona przez Tadeusza Stopińskiego");
		stopka.addStyleName("stopka");
		
		glownyPanel.addNorth(toolbar, 100);
		glownyPanel.addSouth(stopka, 40);
		glownyPanel.addWest(new ScrollPanel(tytuly), 200);
		glownyPanel.add(new ScrollPanel(trescWpisu));
		
		RootLayoutPanel rp = RootLayoutPanel.get();
		rp.add(glownyPanel);
	}

	private void parsujXML() {

		try {
			Document dokumentXML = XMLParser.parse(XMLdoParsowania);
			NodeList listaZWpisami = dokumentXML.getElementsByTagName("Wpis");
			for (int i = 0; i < listaZWpisami.getLength(); i++) {
				Node wpisNode = listaZWpisami.item(i);
				String dataWpisu = ((Element) wpisNode).getElementsByTagName("Data").item(0).getFirstChild()
						.getNodeValue();
				String tytulWpisu = ((Element) wpisNode).getElementsByTagName("Tytul").item(0).getFirstChild()
						.getNodeValue();
				String trescWpisu = ((Element) wpisNode).getElementsByTagName("Tresc").item(0).getFirstChild()
						.getNodeValue();
				listaWpisow.add(new Wpis(dataWpisu, tytulWpisu, trescWpisu));
			}
		} catch (DOMParseException e) {
			e.getMessage();
		}

	}

}