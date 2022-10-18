package uri.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import uri.Uri;
import uri.UriParserFactory;

public class userinfotests {
    @Test
    public void testemptynouser() {
        String uinfo = UriParserFactory.create("scheme://@").parse().getUserInfo();
        assertEquals("", uinfo);
    }
    @Test
    public void testinouser() {
        Uri uinfo = UriParserFactory.create("scheme://").parse();
        assertNull(uinfo.getUserInfo());
    }

    @Test
    public void testilegallchar() {
        Uri uinfo = UriParserFactory.create("scheme://brr-grr@").parse();
        assertNull(uinfo);
    }
    @Test
    public void testdubatchar() {
        Uri uinfo = UriParserFactory.create("scheme://brr@grr@").parse();
        assertNull(uinfo);
    }

    @Test
    public void testcorrectuser() {
        String uinfo = UriParserFactory.create("scheme://userinfo@").parse().getUserInfo();
        assertEquals("userinfo", uinfo);
    }

    @Test
    public void testfornumbers() {
        String uinfo = UriParserFactory.create("scheme://21345@").parse().getUserInfo();
        assertEquals("21345", uinfo);
    }

    @Test
    public void testfornumnchar() {
        String uinfo = UriParserFactory.create("scheme://brr123@").parse().getUserInfo();
        assertEquals("brr123", uinfo);
    }

    @Test
    public void testcapscorrectuser() {
        String uinfo = UriParserFactory.create("scheme://USERINFO@").parse().getUserInfo();
        assertEquals("USERINFO", uinfo);
    }

    @Test
    public void testdotcorrectuser() {
        String uinfo = UriParserFactory.create("scheme://user.info@").parse().getUserInfo();
        assertEquals("user.info", uinfo);
    }
}
