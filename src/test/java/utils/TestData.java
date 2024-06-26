package utils;

import org.testng.annotations.DataProvider;

public final class TestData {
    public static final String BASE_URL = "http://localhost:3000";


    public static final String MEN = "men";
    public static final String WOMEN = "women";
    public static final String SHOES = "shoes";
    public static final String ACCESSORIES = "accessories";
    public static final String ABOUT = "about";

    public static final String WOMEN_BANNER = "women ";
    public static final String MEN_BANNER = "men ";
    public static final String ACCESSORIES_BANNER = "accessories ";
    public static final String SHOES_BANNER = "shoes ";

    public static final String HOME_END_POINT = "/";
    public static final String WOMEN_END_POINT = "/women";
    public static final String MEN_END_POINT = "/men";
    public static final String SHOES_END_POINT = "/shoes";
    public static final String ACCESSORIES_END_POINT = "/accessories";
    public static final String ABOUT_END_POINT = "/#footer-about";


    @DataProvider(name = "navigationTestData")
    public static Object[][] getNavigationTestData() {

        return new Object[][]{
                {"//nav[@class='navbar']//li/a[text()='" + MEN + "']", -1, BASE_URL + MEN_END_POINT},
                {"//nav[@class='navbar']//li/a[text()='" + WOMEN + "']", -1, BASE_URL + WOMEN_END_POINT},
                {"//nav[@class='navbar']//li/a[text()='" + SHOES + "']", -1, BASE_URL + SHOES_END_POINT},
                {"//nav[@class='navbar']//li/a[text()='" + ACCESSORIES + "']", -1, BASE_URL + ACCESSORIES_END_POINT},
                {"//nav[@class='navbar']//li/a[text()='" + ABOUT + "']", -1, BASE_URL + ABOUT_END_POINT},
                {"//section[@class='collection-container']/a/p", 0, BASE_URL + WOMEN_END_POINT},
                {"//section[@class='collection-container']/a/p", 1, BASE_URL + MEN_END_POINT},
                {"//section[@class='collection-container']/a/p", 2, BASE_URL + SHOES_END_POINT},
                {"//section[@class='collection-container']/a/p", 3, BASE_URL + ACCESSORIES_END_POINT}
        };
    }
}
