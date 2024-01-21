package test;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.testng.annotations.Test;
import utils.TestData;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static utils.TestData.BASE_URL;

public final class NavigationTest extends BaseTest {

    @Test
    public void testBaseUrlLanding(){
        getPage().navigate(BASE_URL);

        assertThat(getPage()).hasURL(BASE_URL + TestData.HOME_END_POINT);

    }
    @Test
    public void testWomenMenuNavigatesForWomenPage(){
        getPage().navigate(BASE_URL);
        getPage().getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName(TestData.WOMEN).setExact(true)).click();

        assertThat(getPage()).hasURL(BASE_URL + TestData.WOMEN_END_POINT);
    }

    @Test
    public void testMenMenuNavigatesForMenPage(){
        getPage().navigate(BASE_URL);
        getPage().getByRole(AriaRole.LINK,new Page.GetByRoleOptions().setName(TestData.MEN).setExact(true)).click();

        assertThat(getPage()).hasURL(BASE_URL + TestData.MEN_END_POINT);
    }

    @Test
    public void testAccessoriesMenuNavigatesForAccessoriesPage(){
        getPage().navigate(BASE_URL);
        getPage().getByRole(AriaRole.NAVIGATION).getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName(TestData.ACCESSORIES)).click();

        assertThat(getPage()).hasURL(BASE_URL + TestData.ACCESSORIES_END_POINT);
    }

    @Test
    public void testShoesMenuNavigatesForShoesPage(){
        getPage().navigate(BASE_URL);
        getPage().getByRole(AriaRole.NAVIGATION).getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName(TestData.SHOES)).click();

        assertThat(getPage()).hasURL(BASE_URL + TestData.SHOES_END_POINT);
    }
    @Test
    public void testAboutMenuNavigatesForAboutPage(){
        getPage().navigate(BASE_URL);
        getPage().getByRole(AriaRole.NAVIGATION).getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName(TestData.ABOUT)).click();

        assertThat(getPage()).hasURL(BASE_URL + TestData.ABOUT_END_POINT);
    }
}
