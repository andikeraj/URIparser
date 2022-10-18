package uri.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import uri.Uri;
import uri.UriParserFactory;

public class querytests {
    @Test
    public void testnoquery() {
        Uri query = UriParserFactory.create("scheme://host").parse();
        assertNotNull("missing query", query);
    }

    @Test
    public void testinvalidquery() {
        Uri query = UriParserFactory.create("scheme://host?qu-ery").parse();
        assertNull("invalid character", query);
    }

    @Test
    public void testinvalidqueryv2() {
        Uri query = UriParserFactory.create("scheme://host?qu?ery").parse();
        assertNull("extra ?", query);
    }

    @Test
    public void testinvalidquerynum() {
        Uri query = UriParserFactory.create("scheme://host?12-345").parse();
        assertNull("invalid character", query);
    }

    @Test
    public void testemptyquery() {
        String query = UriParserFactory.create("scheme://host?").parse().getQuery();
        assertEquals("", query);
    }

    @Test
    public void testnumbersquery() {
        String query = UriParserFactory.create("scheme://host?153785").parse().getQuery();
        assertEquals("153785", query);
    }

    @Test
    public void testnumberscharquery() {
        String query = UriParserFactory.create("scheme://host?br12kr").parse().getQuery();
        assertEquals("br12kr", query);
    }

    @Test
    public void testnormalquery() {
        String query = UriParserFactory.create("scheme://host?query").parse().getQuery();
        assertEquals("query", query);
    }

    @Test
    public void tesquerywithand() {
        String query = UriParserFactory.create("scheme://host?que&ery").parse().getQuery();
        assertEquals("que&ery", query);
    }

    @Test
    public void testequalsquery() {
        String query = UriParserFactory.create("scheme://host?que=ery").parse().getQuery();
        assertEquals("que=ery", query);
    }

    @Test
    public void testequalnumquery() {
        String query = UriParserFactory.create("scheme://host?123=45").parse().getQuery();
        assertEquals("123=45", query);
    }

    @Test
    public void testandquerynum() {
        String query = UriParserFactory.create("scheme://host?123&4").parse().getQuery();
        assertEquals("123&4", query);
    }

    @Test
    public void testandequalquery() {
        String query = UriParserFactory.create("scheme://host?ta&qif=sha").parse().getQuery();
        assertEquals("ta&qif=sha", query);
    }
}
