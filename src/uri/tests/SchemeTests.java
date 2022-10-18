package uri.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import uri.Uri;
import uri.UriParserFactory;

// if scheme is empty
public class SchemeTests {
    // if scheme is empty
    @Test
    public void testifnull() {
        Uri uri = UriParserFactory.create("").parse();
        assertNull(uri);
    }

    @Test
    public void testifcorrect() {
        Uri uri = UriParserFactory.create("http://").parse();
        assertEquals("expected http", "http", uri.getScheme());
    }

    @Test
    public void testifletternumbers() {
        Uri uri = UriParserFactory.create("h123://").parse();
        assertEquals("h123", uri.getScheme());
    }

    @Test
    public void testifolnynum() {
        Uri uri = UriParserFactory.create("1234://").parse();
        assertNull(uri);
    }

    @Test
    public void numnletter() {
        Uri uri = UriParserFactory.create("5http://").parse();
        assertNull(uri);
    }

    @Test
    public void testifhttps() {
        Uri uri = UriParserFactory.create("https://").parse();
        assertEquals("https", uri.getScheme());
    }

    @Test
    public void testifcapital() {
        Uri uri = UriParserFactory.create("HTTP://").parse();
        assertEquals("HTTP", uri.getScheme());
    }

    @Test
    public void testifcapitals() {
        Uri uri = UriParserFactory.create("HTTPS://").parse();
        assertEquals("HTTPS", uri.getScheme());
    }

    @Test
    public void testifonecaps() {
        Uri uri = UriParserFactory.create("H123://").parse();
        assertEquals("H123", uri.getScheme());

    }

    @Test
    public void testhttpwithnum() {
        Uri uri = UriParserFactory.create("http12://").parse();
        assertEquals("http12", uri.getScheme());

    }

    @Test
    public void testifhttpswithnum() {
        Uri uri = UriParserFactory.create("https12://").parse();
        assertEquals("https12", uri.getScheme());

    }

    @Test
    public void testcaphttpwithnum() {
        Uri uri = UriParserFactory.create("HTTP12://").parse();
        assertEquals("HTTP12", uri.getScheme());

    }

    @Test
    public void testcaphttpswithnum() {
        Uri uri = UriParserFactory.create("HTTPS12://").parse();
        assertEquals("HTTPS12", uri.getScheme());
    }

    public void testcaphttpsnumstart() {
        Uri uri = UriParserFactory.create("1HTTPS://").parse();
        assertNull(uri);
    }

    public void testcaphttpnumstart() {
        Uri uri = UriParserFactory.create("1HTTP://").parse();
        assertNull(uri);
    }

}
