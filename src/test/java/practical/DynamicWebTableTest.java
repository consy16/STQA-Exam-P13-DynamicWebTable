
package practical;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DynamicWebTableTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void dynamicWebTableTest() {

        String filePath = System.getProperty("user.dir")
                + "/src/test/resources/dynamic-table.html";

        driver.get("file://" + filePath);

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                By.cssSelector("#tableBody tr"), 0));


        // STUDENT TASK 1:
        // Extract and display all student records.

        List<WebElement> rows = new ArrayList<>();

        // TASK 1 - WRITE YOUR CODE HERE



        System.out.println("Total rows: " + rows.size());

        assertEquals("Table should contain 10 students",
                10, rows.size());


        // Sorting by marks in ascending order.

        driver.findElement(By.id("sortMarks")).click();

        List<Integer> ascendingMarks = getMarksFromTable();

        assertTrue("Marks should be sorted in ascending order",
                isAscending(ascendingMarks));

        System.out.println("Ascending Marks: " + ascendingMarks);


        // Sorting by marks in descending order.

        driver.findElement(By.id("sortMarks")).click();

        List<Integer> descendingMarks = getMarksFromTable();

        assertTrue("Marks should be sorted in descending order",
                isDescending(descendingMarks));

        System.out.println("Descending Marks: " + descendingMarks);


        // STUDENT TASK 2:
        // Apply Pass filter and validate the displayed rows

        List<WebElement> filteredRows = new ArrayList<>();

        // TASK 2 - WRITE YOUR CODE HERE



        assertEquals("Pass filter should display 6 students",
                6, filteredRows.size());

        for (WebElement row : filteredRows) {

            String result = row.findElements(By.tagName("td"))
                    .get(4).getText();

            assertEquals("Pass", result);
        }

        System.out.println("Pass filter validation completed successfully.");
    }


    // Helper method to collect marks from the table.

    private List<Integer> getMarksFromTable() {

        List<Integer> marks = new ArrayList<>();

        List<WebElement> rows = driver.findElements(
                By.cssSelector("#tableBody tr"));

        for (WebElement row : rows) {

            List<WebElement> cells = row.findElements(
                    By.tagName("td"));

            int mark = Integer.parseInt(cells.get(3).getText());

            marks.add(mark);
        }

        return marks;
    }


    // Check ascending order.

    private boolean isAscending(List<Integer> values) {

        for (int i = 0; i < values.size() - 1; i++) {

            if (values.get(i) > values.get(i + 1)) {
                return false;
            }
        }

        return true;
    }


    // Check descending order.

    private boolean isDescending(List<Integer> values) {

        for (int i = 0; i < values.size() - 1; i++) {

            if (values.get(i) < values.get(i + 1)) {
                return false;
            }
        }

        return true;
    }


    @After
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}
