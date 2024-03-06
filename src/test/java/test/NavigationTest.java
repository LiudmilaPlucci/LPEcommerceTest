package test;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestData;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static utils.TestData.BASE_URL;

public final class NavigationTest extends BaseTest {
//    @Severity(SeverityLevel.NORMAL)
//    @Story("")
//    @Description("testBaseUrlLanding")
//    @Test(description = "TC1369-01 - Running Domains tests under a Bronze subscription with partial test access. Valid.")
    @Test
    public void testBaseUrlLanding() {
        getPage().navigate(BASE_URL);

//        Allure.step("Bla bla");
        assertThat(getPage()).hasURL(BASE_URL + TestData.HOME_END_POINT);

    }

//    @Severity(SeverityLevel.NORMAL)
//    @Story("Tests Bronze")
//    @Description("testWomenMenuNavigatesForWomenPage")
//    @Test(description = "TC1371-01 - Running Chapters tests under a Bronze subscription with partial test access. Valid.",
//            dependsOnMethods = {"testBaseUrlLanding"})
    @Test
    public void testWomenMenuNavigatesForWomenPage() {

        if (getIsOnHomePage()) {

            getPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(TestData.WOMEN).setExact(true)).click();

//            Allure.step("Assertion: Bla bla");
            assertThat(getPage()).hasURL(BASE_URL + TestData.WOMEN_END_POINT);
        }else{
            Assert.fail();
        }
    }

    @Test
    public void testMenMenuNavigatesForMenPage() {

        getPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(TestData.MEN).setExact(true)).click();

        assertThat(getPage()).hasURL(BASE_URL + TestData.MEN_END_POINT);
    }

    @Test
    public void testAccessoriesMenuNavigatesForAccessoriesPage() {

        getPage().getByRole(AriaRole.NAVIGATION).getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName(TestData.ACCESSORIES)).click();

        assertThat(getPage()).hasURL(BASE_URL + TestData.ACCESSORIES_END_POINT);
    }

    @Test
    public void testShoesMenuNavigatesForShoesPage() {

        getPage().getByRole(AriaRole.NAVIGATION).getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName(TestData.SHOES)).click();

        assertThat(getPage()).hasURL(BASE_URL + TestData.SHOES_END_POINT);
    }

    @Test
    public void testAboutMenuNavigatesForAboutPage() {

        getPage().getByRole(AriaRole.NAVIGATION).getByRole(AriaRole.LINK, new Locator.GetByRoleOptions().setName(TestData.ABOUT)).click();

        assertThat(getPage()).hasURL(BASE_URL + TestData.ABOUT_END_POINT);
    }
}
