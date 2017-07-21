package pl.stopinski.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface PrzedsiewziecieServiceAsync {

	void getWpisy(AsyncCallback<String> callback);

}
