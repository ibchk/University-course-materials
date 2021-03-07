package ee.taltech.iti0200.webbrowser;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WebBrowserTest {

    public static final int MAKEFORWARD70TIMES = 70;
    public static final int MAKEFORWARD60TIMES = 60;
    public static final int MAKEADDTOLIST60TIMESSTART = 938;
    public static final int MAKEADDTOLIST60TIMESEND = 998;
    public static final int MAKEBACK60TIMES = 60;
    public static final int GOTOPAGE4TIMES = 7;
    public static final int GOTOPAGES10TIMESSTART = 34;
    public static final int GOTOPAGES10TIMESEND = 44;

    @org.junit.jupiter.api.Test
    void homePage() {
        WebBrowser myBrowser = new WebBrowser();
        myBrowser.homePage();
        assertEquals("google.com", myBrowser.getCurrentUrl());
        myBrowser.setHomePage("facebook.com");
        assertEquals("google.com", myBrowser.getCurrentUrl());
        myBrowser.homePage();
        assertEquals("facebook.com", myBrowser.getCurrentUrl());
    }

    @org.junit.jupiter.api.Test
    void back() {
        WebBrowser myBrowser = new WebBrowser();
        myBrowser.homePage();
        myBrowser.setHomePage("facebook.com");
        myBrowser.homePage();
        myBrowser.back();
        assertEquals("google.com", myBrowser.getCurrentUrl());
        myBrowser.goTo("facebook.com");
        myBrowser.goTo("facebook.com");
        myBrowser.goTo("vk.com");
        myBrowser.goTo("facebook.com");
        myBrowser.back();
        assertEquals("vk.com", myBrowser.getCurrentUrl());
        myBrowser.back();
        myBrowser.back();
        myBrowser.back();
        myBrowser.back();
        myBrowser.back();
        myBrowser.back();
        assertEquals("google.com", myBrowser.getCurrentUrl());
    }

    @org.junit.jupiter.api.Test
    void forward() {
        WebBrowser myBrowser = new WebBrowser();
        myBrowser.homePage();
        myBrowser.goTo("facebook.com");
        myBrowser.goTo("facebook.com");
        myBrowser.goTo("vk.com");
        myBrowser.goTo("facebook.com");
        myBrowser.back();
        myBrowser.forward();
        assertEquals("facebook.com", myBrowser.getCurrentUrl());
        myBrowser.back();
        assertEquals("vk.com", myBrowser.getCurrentUrl());
        myBrowser.back();
        myBrowser.back();
        myBrowser.back();
        myBrowser.forward();
        myBrowser.forward();
        myBrowser.forward();
        myBrowser.forward();
        myBrowser.forward();
        myBrowser.forward();
        assertEquals("facebook.com", myBrowser.getCurrentUrl());
    }

    @org.junit.jupiter.api.Test
    void goTo() {
        WebBrowser myBrowser = new WebBrowser();
        myBrowser.goTo("nike.com");
        myBrowser.goTo("google.com");
        myBrowser.goTo("nike.com");
        myBrowser.goTo("nike.com");
        myBrowser.goTo("vk.com");
        assertEquals(new LinkedList<String>(Arrays.asList("google.com", "nike.com", "google.com",
                "nike.com", "nike.com", "vk.com")), myBrowser.getHistory());
    }

    @org.junit.jupiter.api.Test
    void addAsBookmark() {
        WebBrowser myBrowser = new WebBrowser();
        myBrowser.goTo("nike.com");
        myBrowser.forward();
        myBrowser.back();
        myBrowser.setHomePage("facebook.com");
        assertEquals(new LinkedList<String>(), myBrowser.getBookmarks());
        myBrowser.addAsBookmark();
        assertEquals(new LinkedList<String>(Collections.singletonList("google.com")), myBrowser.getBookmarks());
        myBrowser.forward();
        myBrowser.addAsBookmark();
        assertEquals(new LinkedList<String>(Arrays.asList("google.com", "nike.com")), myBrowser.getBookmarks());
    }

    @org.junit.jupiter.api.Test
    void removeBookmark() {
        WebBrowser myBrowser = new WebBrowser();
        myBrowser.goTo("nike.com");
        myBrowser.forward();
        myBrowser.back();
        myBrowser.setHomePage("facebook.com");
        myBrowser.addAsBookmark();
        myBrowser.forward();
        myBrowser.addAsBookmark();
        myBrowser.removeBookmark("facebook.com");
        assertEquals(4, myBrowser.getHistory().size());
        assertEquals(new LinkedList<String>(Arrays.asList("google.com", "nike.com")), myBrowser.getBookmarks());
        myBrowser.removeBookmark("google.com");
        assertEquals(new LinkedList<String>(Collections.singletonList("nike.com")), myBrowser.getBookmarks());
    }

    @org.junit.jupiter.api.Test
    void setHomePage() {
        WebBrowser myBrowser = new WebBrowser();
        myBrowser.homePage();
        assertEquals("google.com", myBrowser.getCurrentUrl());
        myBrowser.goTo("nike.com");
        myBrowser.setHomePage("youtube.com");
        assertEquals("nike.com", myBrowser.getCurrentUrl());
        myBrowser.homePage();
        assertEquals("youtube.com", myBrowser.getCurrentUrl());
    }

    @org.junit.jupiter.api.Test
    void getTop3VisitedPages() {
        WebBrowser myBrowser = new WebBrowser();
        assertEquals("google.com - 1 visit", myBrowser.getTop3VisitedPages());
        for (int i = GOTOPAGES10TIMESSTART; i < GOTOPAGES10TIMESEND; i++) {
            myBrowser.goTo("page" + i);
        }
        assertEquals("google.com - 1 visit\npage34 - 1 visit\npage35 - 1 visit", myBrowser.getTop3VisitedPages());
        for (int i = 0; i < 100; i++) {
            myBrowser.goTo("page" + i);
        }
        for (int i = 0; i < 5; i++) {
            myBrowser.goTo("page" + i);
        }
        for (int i = 3; i < GOTOPAGE4TIMES; i++) {
            myBrowser.goTo("page" + i);
        }
        assertEquals("page3 - 3 visits\npage4 - 3 visits\npage34 - 2 visits", myBrowser.getTop3VisitedPages());
    }

    @org.junit.jupiter.api.Test
    void getHistory() {
        WebBrowser myBrowser = new WebBrowser();
        assertEquals((Collections.singletonList("google.com")), myBrowser.getHistory());
        for (int i = 0; i < 1000; i++) {
            myBrowser.goTo("page" + i);
        }
        LinkedList<String> newList = new LinkedList<>();
        newList.add(myBrowser.getHistory().get(0));
        for (int i = 0; i < 1000; i++) {
            newList.add("page" + i);
        }
        assertEquals((newList), myBrowser.getHistory());
        for (int i = 0; i < MAKEBACK60TIMES; i++) {
            myBrowser.back();
        }
        for (int i = MAKEADDTOLIST60TIMESEND; i > MAKEADDTOLIST60TIMESSTART; i--) {
            newList.add("page" + i);
        }
        assertEquals((newList), myBrowser.getHistory());
    }

    @org.junit.jupiter.api.Test
    void getCurrentUrl() {
        WebBrowser myBrowser = new WebBrowser();
        for (int i = 0; i < 1000; i++) {
            myBrowser.goTo("page" + i);
        }
        myBrowser.goTo("google.com");
        for (int i = 0; i < MAKEFORWARD60TIMES; i++) {
            myBrowser.back();
        }
        for (int i = 0; i < MAKEFORWARD70TIMES; i++) {
            myBrowser.forward();
        }
        myBrowser.homePage();
        myBrowser.setHomePage("page1");
        myBrowser.homePage();
        assertEquals("page1", myBrowser.getCurrentUrl());
        myBrowser.back();
        assertEquals("google.com", myBrowser.getCurrentUrl());
    }
}
