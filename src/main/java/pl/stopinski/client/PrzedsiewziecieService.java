package pl.stopinski.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("przedsiewziecieService")
public interface PrzedsiewziecieService extends RemoteService {
	String getWpisy();

}
