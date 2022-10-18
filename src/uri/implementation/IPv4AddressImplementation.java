package uri.implementation;

import uri.IPv4Address;

// TODO implement this class or another Implementation of IPv4Address
public class IPv4AddressImplementation extends HostImplementation implements IPv4Address {
	String ipv4;
	public IPv4AddressImplementation(String host) {
		super(host);
		this.ipv4 = host;
		// TODO implement this
	}

	@Override
	public byte[] getOctets() {
		// TODO implement this
		UriParserImplementation myuri = new UriParserImplementation(host);
		byte[] array = new byte[4];
		byte a = array[0];
		byte b = array[1];
		byte c = array[2];
		byte d = array[3];
		

		// byte a = array[0]

		return array;
	}

	@Override
	public String toString() {
		// TODO implement this
		return null;
	}

}
