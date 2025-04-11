package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.demoblaze.com/");
        WebElement pr1= driver.findElement(By.xpath("(//a[@href=\"prod.html?idp_=1\"])[2]"));
        pr1.click();
        WebElement btn=driver.findElement(By.xpath("//a[text()=\"Add to cart\"]"));
        btn.click();
        Thread.sleep(1000);
        driver.switchTo().alert().accept();
        driver.navigate().back();
        driver.navigate().back();
        WebElement pr2= driver.findElement(By.xpath("(//a[@href=\"prod.html?idp_=2\"])[2]"));
        pr2.click();
        WebElement btn2=driver.findElement(By.xpath("//a[text()=\"Add to cart\"]"));
        btn2.click();
        Thread.sleep(1000);
        driver.switchTo().alert().accept();
        Thread.sleep(1000);
        WebElement cart=driver.findElement(By.id("cartur"));
        cart.click();
        Thread.sleep(2000);
        WebElement price=driver.findElement(By.id("totalp"));
        String pp=price.getText().replaceAll("[^0-9]", "");
        System.out.println("Price" +pp);
        Thread.sleep(2000);
    }
}