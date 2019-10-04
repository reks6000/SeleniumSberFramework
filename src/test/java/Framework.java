import junit.framework.ComparisonFailure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;


public class Framework {
    WebDriver driver;
    Wait<WebDriver> wait;

    public Framework(WebDriver driver, Wait<WebDriver> wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void print() {
        System.out.println("Lol kek");
    }

    public WebElement wait(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        wait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public WebElement waitAndClick(String xpath) {
        WebElement element = wait(xpath);
        element.click();
        return element;
    }

    public WebElement waitAndHover(String xpath) {
        Actions action = new Actions(driver);
        WebElement element = wait(xpath);
        action.moveToElement(element).perform();
        return element;
    }

    public WebElement waitAndSendKey(String xpath, String key) {
        WebElement element = wait(xpath);
        element.sendKeys(key);
        return element;
    }

    public WebElement waitAndSendKeysArray(String xpath, ArrayList<String> keys) {
        WebElement element = wait(xpath);
        List<WebElement> elements = driver.findElements(By.xpath(xpath));
        Iterator<String> iterator = keys.iterator();
        for (WebElement webElement : elements) {
            if (iterator.hasNext()) {
                webElement.sendKeys(iterator.next());
            }
        }
        return element;
    }

    public void check(String xpath, String expected) throws Exception{
        WebElement element = wait(xpath);
        try {
            assertEquals(expected, element.getText());
        } catch (ComparisonFailure e) {
            System.err.println("Error: wrong check");
            driver.quit();
            throw new Error();
        }
    }

    public void checkArray(String xpath, ArrayList<String> keys) {
        List<WebElement> elements = driver.findElements(By.xpath(xpath));
        Iterator<String> iterator = keys.iterator();
        for (WebElement webElement : elements) {
            if (iterator.hasNext()) {
                try {
                    assertEquals(iterator.next(), webElement.getAttribute("value"));
                } catch (ComparisonFailure e) {
                    System.err.println("Error: wrong check");
                    driver.quit();
                    throw new Error();
                }
            }
        }
    }

    public Select select(String xpath, String text) {
        Select region = new Select(wait(xpath));
        region.selectByVisibleText(text);
        return region;
    }

    public void switchToNextWindow() throws Exception{
        String currentWindow = driver.getWindowHandle();
        ArrayList<String> handles =  new ArrayList<String> (driver.getWindowHandles());
        for(String windowHandle  : handles) {
            if(!windowHandle.equals(currentWindow)) {
                driver.switchTo().window(windowHandle);
                driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            }
        }
    }
}
