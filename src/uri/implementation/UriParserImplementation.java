package uri.implementation;

import uri.Uri;
import uri.UriParser;

public class UriParserImplementation implements UriParser {
	String containuri;

	public UriParserImplementation(String uri) {
		this.containuri = uri;
	}

	public Uri parse() {
		UriImplementation uriimp = new UriImplementation(containuri);
		if (uriimp.getScheme() == null) {
			return null;
		}
		if (uriimp.getQuery() != null && uriimp.getQuery().equals("invalid")) {
			return null;
		}
		if (uriimp.getUserInfo() != null && uriimp.getUserInfo().equals("invalid")) {
			return null;
		}
		if (uriimp.getPath() != null && uriimp.getPath().equals("invalid")){
			return null;
		}
		return uriimp;
	}

}
