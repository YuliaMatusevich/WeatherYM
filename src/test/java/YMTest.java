import com.sun.jdi.IntegerValue;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class YMTest {

    //    TC_1_1  - Тест кейс:
    //    1. Открыть страницу https://openweathermap.org/
    //    2. Набрать в строке поиска город Paris
    //    3. Нажать пункт меню Search
    //    4. Из выпадающего списка выбрать Paris, FR
    //    5. Подтвердить, что заголовок изменился на "Paris, FR"

    @Test

    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\matu\\GoogleDriver\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);
        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']"));
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//button[@type='submit']"));
        searchButton.click();
        Thread.sleep(1000);

        WebElement parisFrChoiceDropdownMenu = driver.findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']"));
        parisFrChoiceDropdownMenu.click();

        WebElement h2CityNameHeader = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//h2"));

        Thread.sleep(1000);

        String actualResult = h2CityNameHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();

    }

    //HOMEWORK

    //TC_11_01
    //1.  Открыть базовую ссылку
    //2.  Нажать на пункт меню Guide
    //3.  Подтвердить, что вы перешли на страницу со ссылкой https://openweathermap.org/guide
    // и что title этой страницы OpenWeatherMap API guide - OpenWeatherMap

    @Test
    public void testTitle_WhenRedirectingToGuidePage() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\matu\\GoogleDriver\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedURL = "https://openweathermap.org/guide";
        String expectedTitleGuidePage = "OpenWeatherMap API guide - OpenWeatherMap";
        String expectedResult = expectedURL + " " + expectedTitleGuidePage;

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement findGuideMenu = driver.findElement(
                By.xpath("//div [@id = 'desktop-menu']//a[@href = '/guide']"));
        findGuideMenu.click();

        String actualResult = driver.getCurrentUrl() + " " + driver.getTitle();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();

    }
    //TC_11_02
    //TC_11_02
    //1.  Открыть базовую ссылку
    //2.  Нажать на единицы измерения Imperial: °F, mph
    //
    //3.  Подтвердить, что температура для города показана в Фарингейтах

    @Test
    public void testFahrenheitUnits_WhehChooseF() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\matu\\GoogleDriver\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        //String expectedResult = "F";
        boolean expectedResult = true;

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement findFahrenheitSwitcher = driver.findElement(
                By.xpath("//div[@class = 'switch-container']//div[text() = 'Imperial: °F, mph']"));
        findFahrenheitSwitcher.click();

        WebElement findTempIndicator = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//span[@class = 'heading']"));

//        String tempIndicator = findTempIndicator.getText();
//
//        if (tempIndicator.contains("F")) {
//            tempIndicator = "F";
//        }
//
//        String actualResult = tempIndicator;

        boolean actualResult = findTempIndicator.getText().contains("F");

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();

    }

    //TC_11_03
    //1.  Открыть базовую ссылку
    //2. Подтвердить, что внизу страницы есть панель с текстом “We use cookies which are essential for the site to work.
    // We also use non-essential cookies to help us improve our services. Any data collected is anonymised.
    // You can allow all cookies or manage them individually.”
    //3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies”

    @Test
    public void testDownpageBannerText_WhenBasicURLLoaded() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\matu\\GoogleDriver\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult1 = "We use cookies which are essential for the site to work. "
                + "We also use non-essential cookies to help us improve our services. "
                + "Any data collected is anonymised. You can allow all cookies or manage them individually.";
        String expectedResult2 = "Allow all";
        String expectedResult3 = "Manage cookies";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(6000);

        WebElement findDownBanner = driver.findElement(
                By.xpath("//div[@id='stick-footer-panel']//p[@class = 'stick-footer-panel__description']"));
        String actualResult1 = findDownBanner.getText();

        WebElement findAllowButton = driver.findElement(
                By.xpath("//div[@class = 'stick-footer-panel__btn-container']//button"));
        String actualResult2 = findAllowButton.getText();

        WebElement findManageCookiesButton = driver.findElement(
                By.xpath("//div[@class = 'stick-footer-panel__btn-container']"
                        + "//a [@href = '/cookies-settings']"));
        String actualResult3 = findManageCookiesButton.getText();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
        Assert.assertEquals(actualResult3, expectedResult3);

        driver.quit();

    }

    //TC_11_04
    //1.  Открыть базовую ссылку
    //2.  Подтвердить, что в меню Support есть 3 подменю с названиями “FAQ”, “How to start” и “Ask a question”

    @Test
    public void testDropDownMenu_OnStartPage_WhenPressSupportMenu() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\matu\\GoogleDriver\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult1 = "FAQ";
        String expectedResult2 = "How to start";
        String expectedResult3 = "Ask a question";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement findSupportDropdown = driver.findElement(
                By.xpath("//div[@id = 'support-dropdown']"));

        findSupportDropdown.click();

        WebElement checkFAQPoint = driver.findElement(
                By.xpath("//div[@id = 'desktop-menu']//ul[@class = 'dropdown-menu dropdown-visible']"
                        + "//a[@href = '/faq']"));
        String actualResult1 = checkFAQPoint.getText();

        WebElement checkHowToStartPoint = driver.findElement(
                By.xpath("//div[@id = 'desktop-menu']//ul[@class = 'dropdown-menu dropdown-visible']"
                        + "//a[@href = '/appid']"));
        String actualResult2 = checkHowToStartPoint.getText();

        WebElement checkAskQuestionPoint = driver.findElement(
                By.xpath("//div[@id = 'desktop-menu']//ul[@class = 'dropdown-menu dropdown-visible']"
                        + "//a[@href = 'https://home.openweathermap.org/questions']"));
        String actualResult3 = checkAskQuestionPoint.getText();

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
        Assert.assertEquals(actualResult3, expectedResult3);

        driver.quit();

    }



//    TC_11_05
//1. Открыть базовую ссылку
//2. Нажать пункт меню Support → Ask a question
//3. Заполнить поля Email, Subject, Message
//4. Не подтвердив CAPTCHA, нажать кнопку Submit
//5. Подтвердить, что пользователю будет показана ошибка “reCAPTCHA verification failed, please try again.”

    @Test
    public void testCapcha_WhenUseAskQuestionInSupportMenu_NoMarkingCapcha() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\matu\\GoogleDriver\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String email = "blabla@gmail.com";
        String text = "BlaBlaBla";
        String reCapcha = "reCAPTCHA verification failed, please try again.";
        boolean expectedResult = true;

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement findSupportDropdown = driver.findElement(
                By.xpath("//div[@id = 'support-dropdown']"));
        findSupportDropdown.click();

        WebElement findAskQuestionPoint = driver.findElement(
                By.xpath("//div[@id = 'desktop-menu']//ul[@class = 'dropdown-menu dropdown-visible']"
                        + "//a[@href = 'https://home.openweathermap.org/questions']"));
        findAskQuestionPoint.click();
        Thread.sleep(5000);

        // Store the current window handle
        String winHandleBefore = driver.getWindowHandle();

        // Switch to new window opened
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }

        WebElement findEmailField = driver.findElement(
                By.xpath("//input[@id = 'question_form_email']"));
        findEmailField.click();
        findEmailField.sendKeys(email);

        WebElement findSubjectField = driver.findElement(
                By.xpath("//select[@id = 'question_form_subject']"));
        findSubjectField.click();

        WebElement chooseSubjectSales = driver.findElement(
                By.xpath("//select[@id = 'question_form_subject']/option[@value = 'Sales']"));
        chooseSubjectSales.click();

        WebElement findTextAreaField = driver.findElement(
                By.xpath("//textarea[@id = 'question_form_message']"));
        findTextAreaField.click();
        findTextAreaField.sendKeys(text);

        WebElement findSubmitButton = driver.findElement(
                By.xpath("//input[@value = 'Submit']"));
        findSubmitButton.click();

        WebElement capchaVerificationFailedMessage = driver.findElement(
                By.xpath("//div[@class = 'help-block']"));
        String message = capchaVerificationFailedMessage.getText();

        boolean actualResult = capchaVerificationFailedMessage.getText().equals(reCapcha);

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();

   }

   //6
    //WebDriverWait wait = new WebDriverWait(driver, 25);
    //        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
    //                By.xpath("//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha')]")));
    //
    //        wait.until(ExpectedConditions.elementToBeClickable(
    //                By.xpath("//div[@class='recaptcha-checkbox-border']"))).click();
    //        Thread.sleep(5000);




    //TC_11_07
    //1.  Открыть базовую ссылку
    //2.  Нажать на единицы измерения Imperial: °F, mph
    //
    //3.  Нажать на единицы измерения Metric: °C, m/s
    //4.  Подтвердить, что в результате этих действий, единицы измерения температуры изменились с F на С

    @Test
    public void testUnitsOfMeasure_WhenSwitch() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\matu\\GoogleDriver\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "Changed";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement findFahrenheitSwitcher = driver.findElement(
                By.xpath("//div[@class = 'switch-container']//div[text() = 'Imperial: °F, mph']"));
        findFahrenheitSwitcher.click();

        WebElement findTempIndicator = driver.findElement(
                By.xpath("//div [@class = 'current-temp']//span[@class = 'heading']"));

        String tempIndicatorF = findTempIndicator.getText();

        if (tempIndicatorF.contains("F")) {
            tempIndicatorF = "F";
        } else {tempIndicatorF = "Error";}


        WebElement findCelsiusSwitcher = driver.findElement(
                By.xpath("//div[@class = 'switch-container']//div[text() = 'Metric: °C, m/s']"));
        findFahrenheitSwitcher.click();

        WebElement findTempIndicatorC = driver.findElement(
                By.xpath("//div [@class = 'current-temp']//span[@class = 'heading']"));

        String tempIndicatorC = findTempIndicator.getText();

        if (tempIndicatorC.contains("C")) {
            tempIndicatorC = "C";
        } else {tempIndicatorC = "Error";}

        String actualResult = "";
        if (!tempIndicatorF.equals(tempIndicatorC)) {
            actualResult = "Changed";
        }

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    //TC_11_08
    //1.  Открыть базовую ссылку
    //2.  Нажать на лого компании
    //
    //3.  Дождаться, когда произойдет перезагрузка сайта, и подтвердить, что текущая ссылка не изменилась

    @Test
    public void testURL_WhenPressLogo() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\matu\\GoogleDriver\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "Not Changed";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement logo = driver.findElement(By.xpath("//li[@class = 'logo']"));
        logo.click();

        String newUrl = driver.getCurrentUrl();
        String actualResult = "";
        if (url.equals(newUrl)) {
            actualResult = "Not Changed";
        }

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    //TC_11_09
    //Открыть базовую ссылку
    //2.  В строке поиска в навигационной панели набрать “Rome”
    //
    //3.  Нажать клавишу Enter
    //4.  Подтвердить, что вы перешли на страницу в ссылке которой содержатся слова “find” и “Rome”
    //5. Подтвердить, что в строке поиска на новой странице вписано слово “Rome”

    @Test
    public void testSearchFieldAndLink_WhenEnterCityAndKeydoardEnter() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\matu\\GoogleDriver\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Rome";
        String expectedResult1 = "correct link";
        String expectedResult2 = "correct value";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement findSearchField = driver.findElement(
                By.xpath("//div[@id = 'desktop-menu']//input [@type = 'text']"));
        findSearchField.click();
        findSearchField.sendKeys(cityName);
        findSearchField.sendKeys(Keys.ENTER);

        String newURL = driver.getCurrentUrl();
        String actualResult1 = "";
        if (newURL.contains("find") && newURL.contains("Rome")) {
            actualResult1 = "correct link";
        }

        WebElement checkSearchField = driver.findElement(
                By.xpath("//input[@id = 'search_str']"));

        String searchFieldValue = checkSearchField.getAttribute("value");
        String actualResult2 = "";
        if (searchFieldValue.contains("Rome")) {
            actualResult2 = "correct value";
        }

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);

        driver.quit();
    }

    //TC_11_10
    //1.  Открыть базовую ссылку
    //2.  Нажать на пункт меню API
    //3.  Подтвердить, что на открывшейся странице пользователь видит 30 оранжевых кнопок

    @Test
    public void testQuantityOFOrangeButtons_WhenDownloadApiPage() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\matu\\GoogleDriver\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        int expectedResult = 30;

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement findAPIMenu = driver.findElement(
                By.xpath("//div[@id = 'desktop-menu']//a[@href = '/api']"));
        findAPIMenu.click();

        List<WebElement> findSubscribeButtons  = driver.findElements(
                By.xpath("//main[@class = 'wrapper']//a[@class = 'btn_block orange round']"));
        int count1 = findSubscribeButtons.size();

        List<WebElement> findOneCallButtons  = driver.findElements(
                By.xpath("//main[@class = 'wrapper']//a[@class = 'ow-btn round btn-orange']"));
        int count2 = findOneCallButtons.size();

        int actualResult = count1 + count2;

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();

    }

    //Method 2

    @Test
    public void testQuantityOFOrangeButtons_WhenDownloadApiPage2 () throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\matu\\GoogleDriver\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        int expectedResult = 30;

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement findAPIMenu = driver.findElement(
                By.xpath("//div[@id = 'desktop-menu']//a[@href = '/api']"));
        findAPIMenu.click();

        List <WebElement> findOrangeButtons = driver.findElements(
               By.xpath("//a[contains(@class, 'orange')]"));


        int actualResult = findOrangeButtons.size();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();

    }




//      @Test
//
//      public void testH2TagText_WhenSearchingCityCountry(){
//          System.setProperty("webdriver.chrome.driver", "C:\\Users\\matu\\GoogleDriver\\chromedriver_win32\\chromedriver.exe");
//
//          WebDriver driver = new ChromeDriver();


//          driver.quit();
//
//     }


}