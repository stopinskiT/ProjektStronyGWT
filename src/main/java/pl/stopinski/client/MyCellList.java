package pl.stopinski.client;

import com.google.gwt.user.cellview.client.CellList.Style;
import com.google.gwt.user.cellview.client.CellList;

public interface MyCellList extends CellList.Resources {
	
	String DEFAULT_CSS = "pl/stopinski/resources/style/CellList.css";

    @Source("pl/stopinski/resources/style/CellList.css")
    Style cellListStyle();
} 