package uri.implementation;

import java.util.regex.Pattern;

import uri.Host;
import uri.Uri;

public class UriImplementation implements Uri {
	String containuri;

	public UriImplementation(String uri) {
		this.containuri = uri;
	}

	@Override
	public String getScheme() {
		String myscheme = containuri.split("://")[0];
		if (Pattern.matches("(^[0-9])\\w*", myscheme)) {
			return null;
		}
		if (myscheme == "") {
			return null;
		}
		if (Pattern.matches("\\w+", myscheme)) {
			return myscheme;
		} else
			return null;
	}

	@Override
	public String getQuery() {
		if (containuri.contains("?")) {
			if (containuri.split("\\?").length > 2) {
				return "invalid";
			} else {
				String[] query = containuri.split("\\?", 2);
				String myquery = query[1];
				if(myquery.isEmpty()){
					return "";
				}
				if (Pattern.matches("^((\\w*\\&*\\=*)*|%([\\dA-Fa-f]){2})$", query[1])) {
					return query[1];
				} else
					return "invalid";
			}
		}
		return null;
	}

	@Override
	public String getUserInfo() {
		if (containuri.contains("@")) {
			String rest = containuri.split("://")[1];
			if (rest.split("@").length >= 2) {
				return "invalid";
			}
			String[] myuserinfo = rest.split("@", 2);
			String userinfo = myuserinfo[0];
			if (userinfo.isEmpty()) {
				return "";
			}
			if (Pattern.matches("^((\\w*\\.*\\:*)*|%[\\dA-Fa-f]{2})$", myuserinfo[0])) {
				return myuserinfo[0];
			}

			else
				return "invalid";

		} else
			return null;
	}

	@Override
	public Host getHost() {
		// if(containuri.contains("@")){
		// String rest = containuri.split("@")[1];
		// if(rest.contains("/")){
		// String host = rest.split("/")[0];
		// if(Pattern.matches("\\d\\.\\d\\.\\d\\.\\d"))
		// if(Pattern.matches("(\\w*\\.*)+", host)){
		// return host
		// }
		// }
		// }
		// TODO implement this
		return null;
	}

	@Override
	public String getPath() {
		if (containuri.split("/").length > 3) {
			String[] rest = containuri.split("/", 4);
			String part4 = rest[3];
			part4 = "/" + part4;
			if (part4.contains("?")) {
				String mypath = part4.split("?")[0];

				if (Pattern.matches("^\\/(\\w*\\/*\\.*)*", mypath)) {
					return mypath;
				} else
					return "invalid";
			} else {
				String mypath = part4;
				if (Pattern.matches("^\\/(\\w*\\/*\\.*)*", mypath)) {
					return mypath;
				} else
					return "invalid";
			}
		}
		return ("");
	}

}
