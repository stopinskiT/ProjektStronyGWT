package pl.stopinski.server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import pl.stopinski.client.PrzedsiewziecieService;

public class PrzedsiewziecieServiceImpl extends RemoteServiceServlet implements PrzedsiewziecieService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getWpisy() {
		String filePath = PrzedsiewziecieServiceImpl.class.getResource( "/" ).getPath() + "pl/stopinski/resources/xml/wpisy.txt";
		StringBuffer sb = new StringBuffer();
		try {
			BufferedReader in = new BufferedReader(new FileReader(filePath));
			String line;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			in.close();
		} catch (IOException exc) {
			sb.append("<Wpisy><Wpis><Data>2017-07-17</Data><Tytul>Błąd</Tytul><Tresc>");
			sb.append(exc.getMessage());
			sb.append("</Tresc></Wpis></Wpisy>");
		}
		return sb.toString();
	}

}
