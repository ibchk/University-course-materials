package ee.taltech.iti0200.webbrowser;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class WebBrowser {
    private String homePage = "google.com";

    private String currentLocation = "google.com";

    private LinkedList<String> history = new LinkedList<String>();
    private LinkedList<String> listBack = new LinkedList<String>();
    private LinkedList<String> listForward = new LinkedList<String>();
    private LinkedList<String> bookmarks = new LinkedList<String>();
    private boolean addedOrNot = false;

    private void addingHomepageInBeginning() {
        if (!addedOrNot) {
            history.add("google.com");
            listBack.add("google.com");
            addedOrNot = true;
        }
    }

    private void clearingForwardList() {
        if (listForward.size() > 0) {
            listForward.clear();
        }
    }

    /**
     * Goes to homepage.
     */
    public void homePage() {
        addingHomepageInBeginning();
        currentLocation = homePage;
        history.add(currentLocation);
        listBack.add(currentLocation);
        clearingForwardList();
    }

    /**
     * Goes back to previous page.
     */
    public void back() {
        addingHomepageInBeginning();
        if (listBack.size() > 1) {
            currentLocation = listBack.get(listBack.size() - 2);
            history.add(currentLocation);
            listForward.add(listBack.get(listBack.size() - 1));
            listBack.remove(listBack.size() - 1);
        }
    }

    /**
     * Goes forward to next page.
     */
    public void forward() {
        addingHomepageInBeginning();
        if (listForward.size() > 0) {
            currentLocation = listForward.get(listForward.size() - 1);
            history.add(currentLocation);
            listBack.add(listForward.get(listForward.size() - 1));
            listForward.remove(listForward.size() - 1);
        }
    }

    /**
     * Go to a webpage.
     *
     * @param webpage url to go to
     */
    public void goTo(String url) {
        addingHomepageInBeginning();
        currentLocation = url;
        history.add(currentLocation);
        listBack.add(currentLocation);
        clearingForwardList();

    }

    /**
     * Add a webpage as a bookmark.
     */
    public void addAsBookmark() {
        if (!bookmarks.contains(currentLocation)) {
            bookmarks.add(currentLocation);
        }
    }

    /**
     * Remove a bookmark.
     *
     * @param bookmark to remove
     */
    public void removeBookmark(String bookmark) {
        bookmarks.remove(bookmark);
    }

    public List<String> getBookmarks() {
        return bookmarks;
    }

    public void setHomePage(String homePageNew) {
        homePage = homePageNew;
    }

    private Map<String, Integer> mapCreation() {
        Map<String, Integer> mapOfPages = new HashMap<>();
        for (String page : history) {
            if (mapOfPages.containsKey(page)) {
                mapOfPages.put(page, mapOfPages.get(page) + 1);
            } else {
                mapOfPages.put(page, 1);
            }
        }
        return mapOfPages;
    }

    private String correctLineCalculator(int len, int i, int times, String onePage) {
        String top3 = "";
        if (i == len - 1 && times == 1) {
            top3 = onePage + " - " + times + " visit";
        } else if (times == 1) {
            top3 = onePage + " - " + times + " visit\n";
        } else if (i == len - 1) {
            top3 = onePage + " - " + times + " visits";
        } else {
            top3 = onePage + " - " + times + " visits\n";
        }
        return top3;
    }

    private boolean whichPageIsFirst(String oldPage, String newPage) {
        for (String page : history) {
            if (page.equals(oldPage)) {
                return false;
            } else if (page.equals(newPage)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get top 3 visited pages.
     *
     * @return a String that contains top three visited pages separated with a newline "\n"
     */
    public String getTop3VisitedPages() {
        addingHomepageInBeginning();
        StringBuilder top3 = new StringBuilder();
        Map<String, Integer> mapOfPages = mapCreation();
        LinkedList<String> top3List = new LinkedList<String>();
        int len = mapOfPages.size();
        if (len > 3) {
            len = 3;
        }
        for (int i = 0; i < len; i++) {
            String onePage = "";
            int times = 0;
            for (String page : mapOfPages.keySet()) {
                if (!top3List.contains(page) && mapOfPages.get(page) >= times) {
                    if (mapOfPages.get(page) > times) {
                        onePage = page;
                        times = mapOfPages.get(page);
                    } else if (whichPageIsFirst(onePage, page)) {
                        onePage = page;
                        times = mapOfPages.get(page);
                    }
                }
            }
            top3List.add(onePage);
            top3.append(correctLineCalculator(len, i, times, onePage));
        }
        return top3.toString();
    }

    /**
     * Returns a list of all visited pages.
     * <p>
     * Not to be confused with pages in your back-history.
     * <p>
     * For example, if you visit "facebook.com" and hit back(),
     * then the whole history would be: ["google.com", "facebook.com", "google.com"]
     *
     * @return list of all visited pages
     */
    public List<String> getHistory() {
        addingHomepageInBeginning();
        return history;
    }

    /**
     * Returns the active web page (string).
     *
     * @return active web page
     */
    public String getCurrentUrl() {
        return currentLocation;
    }
}
