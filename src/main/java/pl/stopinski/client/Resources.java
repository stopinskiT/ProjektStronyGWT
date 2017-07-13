package pl.stopinski.client;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.TextResource;

public interface Resources extends ClientBundle {

	@Source("pl/stopinski/resources/grafiki/ja.jpg")
	ImageResource ja();
	
	@Source("pl/stopinski/resources/grafiki/motyw_head.png")
	ImageResource kwiat();
	
	@Source("pl/stopinski/resources/xml/wpisy.txt")
	TextResource wpisy();
}