package uri.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import uri.Host;
import uri.IPv4Address;
import uri.UriParserFactory;
import uri.*;

public class HostTest {
    public boolean isIPv4Address(Host host) {
        return host instanceof IPv4Address;
    }

    public byte[] getIPv4Octets(Host host) {
        if (!isIPv4Address(host))
            throw new IllegalArgumentException("host must be an IPv4 address");
        return ((IPv4Address) host).getOctets();
    }

    @Test
    public void testimissingscheme() {
        Uri host = UriParserFactory.create("://168.192.1.1").parse();
        assertNull(host);
    }

    @Test
    public void testimissinghost() {
        Host host = UriParserFactory.create("scheme://").parse().getHost();
        assertEquals("", host.toString());
    }

    @Test
    public void testipv4() {
        Host host = UriParserFactory.create("scheme://168.192.1.1").parse().getHost();
        assertTrue("It is ipv4", isIPv4Address(host));
    }

    @Test
    public void testocta() {
        Host host = UriParserFactory.create("scheme://168.192.1.1").parse().getHost();
        byte[] iparray = { (byte) 168, (byte) 192, 1, 1 };
        assertArrayEquals(iparray, getIPv4Octets(host));
    }

    @Test
    public void testinvalidipv4() {
        Host host = UriParserFactory.create("scheme://299.1.1.1").parse().getHost();
        assertFalse("its not ipv4 because over 255", isIPv4Address(host));
    }

    @Test
    public void testinvalidv2ipv4() {
        Host host = UriParserFactory.create("scheme://1.1.1").parse().getHost();
        assertFalse("its not ipv4 because less than 4", isIPv4Address(host));
    }

    @Test
    public void testinvalidv3ipv4() {
        Host host = UriParserFactory.create("scheme://1.1.1.1.1").parse().getHost();
        assertFalse("its not ipv4 because more than 4", isIPv4Address(host));
    }

    @Test
    public void testinvalidiv4pv4() {
        Host host = UriParserFactory.create("scheme://1..1.1").parse().getHost();
        assertFalse("its not ipv4 because missing number", isIPv4Address(host));
    }

    @Test
    public void testifempty() {
        Uri host = UriParserFactory.create("").parse();
        assertNull(host);
    }

    @Test
    public void testcorrecthost() {
        Host host = UriParserFactory.create("scheme://cms.sic.saarland").parse().getHost();
        assertEquals("cms.sic.saarland", host.toString());
    }

    @Test
    public void testincorrecthost() {
        Uri host = UriParserFactory.create("scheme://cms.sic.saarland-uni").parse();
        assertNull("invalid character -", host);
    }

}
