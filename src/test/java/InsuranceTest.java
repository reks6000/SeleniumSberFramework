import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class InsuranceTest extends BaseTest{

    @Test
    public void test1() throws Exception{
        website = "http://www.sberbank.ru/ru/person";
        driver.get(website);

        xpath = "//*[@class='lg-menu__text'][text()='Страхование']";
        fw.waitAndHover(xpath);
        xpath = "//*[@class='lg-menu__sub-link'][text()='Страхование путешественников']";
        fw.waitAndClick(xpath);
        xpath = "//h1[text()='Страхование путешественников']";
        fw.check(xpath, "Страхование путешественников");
        xpath = "//div[@data-pid='SBRF-TEXT-1021974']//a/img";
        fw.waitAndClick(xpath);
        fw.switchToNextWindow();
        xpath = "//*[@class='b-form-box-title ng-binding'][text()='Минимальная']";
        fw.waitAndClick(xpath);
        xpath = "//span[@class='b-continue-btn'][text()='Оформить']";
        fw.waitAndClick(xpath);
        xpath = "//section[@class='b-form-main-section']//*[@type='text']";
        ArrayList<String> keys = new ArrayList<String>(Arrays.asList("Surname", "Given names", "21062000", "Фамилия" , "Имя", "Отчество",
                "19041995", "1488", "425311", "05082004", "Кем выдан"));
        fw.waitAndSendKeysArray(xpath, keys);
        keys.set(2, "21.06.2000");
        keys.set(6, "19.04.1995");
        keys.set(9, "05.08.2004");
        fw.checkArray(xpath, keys);
        xpath = "//span[text()='Продолжить']";
        fw.waitAndClick(xpath);
        xpath = "//div[text()='Заполнены не все обязательные поля']";
        fw.check(xpath, "Заполнены не все обязательные поля");
    }
}