package uri.tests;

import uri.Uri;
import uri.UriParserFactory;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class pathtest {
    @Test
    public void testemptypath() {
        String path = UriParserFactory.create("scheme://").parse().getPath();
        assertEquals("", path);
    }

    @Test
    public void testinvaliddash() {
        Uri path = UriParserFactory.create("scheme://host/path-brr").parse();
        assertNull("Fail: path contains -", path);
    }

    @Test
    public void testinalidat() {
        Uri path = UriParserFactory.create("scheme://host/path@brr").parse();
        assertNull("Fail: path contains @", path);
    }

    @Test
    public void testinalidv1() {
        Uri path = UriParserFactory.create("scheme://host/pathbrr%").parse();
        assertNull("Fail: path contains %", path);
    }

    @Test
    public void testinalidv2() {
        Uri path = UriParserFactory.create("scheme://host/%1").parse();
        assertNull("Fail: one character after %", path);
    }

    @Test
    public void testinalidtv3() {
        Uri path = UriParserFactory.create("scheme://host/path;brr").parse();
        assertNull("Fail: invalid char ;", path);
    }

    @Test
    public void testinalidv4() {
        Uri path = UriParserFactory.create("scheme://host/path:brr").parse();
        assertNull("Fail: invalid char :", path);
    }

    @Test
    public void testinalidv5() {
        Uri path = UriParserFactory.create("scheme://host/path,brr").parse();
        assertNull("Fail: invalid char ,", path);
    }

    @Test
    public void testinalidand() {
        Uri path = UriParserFactory.create("scheme://host/path&brr").parse();
        assertNull("Fail: path contains &", path);
    }

    @Test
    public void testcorrectpath() {
        String path = UriParserFactory.create("scheme://host/path").parse().getPath();
        assertEquals("/path", path);
    }

    @Test
    public void testcorrectpathnum() {
        String path = UriParserFactory.create("schme://host/123").parse().getPath();
        assertEquals("/123", path);
    }

    @Test
    public void testdoublepath() {
        String path = UriParserFactory.create("scheme://host/path/path1").parse().getPath();
        assertEquals("/path/path1", path);
    }

    @Test
    public void testcorrctpath() {
        Uri path = UriParserFactory.create("scheme://host/%path").parse();
        assertNull(path);
    }

    @Test
    public void testnumnletterpath() {
        String path = UriParserFactory.create("scheme://host/path43/").parse().getPath();
        assertEquals("/path43/", path);
    }

}
