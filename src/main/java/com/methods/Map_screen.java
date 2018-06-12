package com.methods;

import com.vars.vars;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

import static com.vars.consts.Timeout;
import static com.vars.vars.newSafeZone;

public class Map_screen extends Common {

    protected Logger logger;

    public Map_screen(AppiumDriver<WebElement> driver)  {
        super(driver);
        logger = Logger.getLogger("iOSTestLogger");
        PageFactory.initElements(new AppiumFieldDecorator(driver, Timeout, TimeUnit.SECONDS), this);
    }

    public void mapActions() {

        driver.findElementByXPath("//XCUIElementTypeApplication[@name=\"Averia Collar\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeTabBar/XCUIElementTypeButton[2]").click();
        driver.findElementByAccessibilityId("petsMap petsCenter btn").click();
        driver.findElementByAccessibilityId("petsMap userCenter btn").click();
        driver.findElementByAccessibilityId("petsMap nearbyPets btn normal").click();

    }

    public void addSafeZone(String device, String petName) {

        //should be on other screen, but it is here

        logger.info(device + ": Adding test safe zone");

        //тап на профиль пета (из профиля юзера)

        driver.findElementByAccessibilityId(petName).click();
        scrollUp("Настройка безопасной зоны");

        driver.findElementByAccessibilityId("Настройка безопасной зоны").click();

        driver.findElementByAccessibilityId("Добавить").click();

        driver.findElementByXPath("//*[@type='XCUIElementTypeTextField']").sendKeys(vars.newSafeZone);

        driver.findElementByAccessibilityId("safeZone save btn").click();

        logger.info(device + ": Saving new safe zone");

        driver.findElementByAccessibilityId("safeZone save btn").click();


        elementVisibilityWait(By.id(vars.newSafeZone));

        driver.findElementByAccessibilityId(vars.newSafeZone).click();

        elementVisibilityWait(By.id("safeZone more btn"));

        driver.findElementByAccessibilityId("safeZone more btn").click();

        elementVisibilityWait(By.id("Удалить"));

        driver.findElementByAccessibilityId("Удалить").click();

        elementVisibilityWait(By.id("Да"));

        driver.findElementByAccessibilityId("Да").click();
        logger.info(device + ": Deleting test safe zone");

        elementVisibilityWait(By.id("Настройки питомца"));

        driver.findElementByAccessibilityId("Настройки питомца").click();

        elementVisibilityWait(By.id("Averia Tester"));

        //плохой вариант т.к. имя юзера может меняться. Надо забирть, как имя пета
        driver.findElementByAccessibilityId("Averia Tester").click();
    }

}