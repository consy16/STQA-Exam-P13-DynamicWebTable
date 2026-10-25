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

        // TASK 1: Extract and display all student records.
        // TODO 1a: Find all table rows using the #tableBody tr selector.
        // TODO 1b: Print the total number of rows and assert that there are 10.
        // TODO 1c: For every row, find its td cells and print all five values.

        List<WebElement> rows = new ArrayList<>();

        System.out.println("Total rows: " + rows.size());
        assertEquals("Table should contain 10 students", 10, rows.size());

        // TODO: Add the loop to print each student's ID, name, course, marks and result.


        // TASK 2: Validate ascending and descending sorting by marks.
        // TODO 2a: Click the sortMarks button and collect the marks.
        // TODO 2b: Assert that the marks are in ascending order.
        // TODO 2c: Click sortMarks again, collect the marks and assert descending order.

        driver.findElement(By.id("sortMarks")).click();
        List<Integer> ascendingMarks = getMarksFromTable();
        assertTrue("Marks should be sorted in ascending order",
                isAscending(ascendingMarks));

        driver.findElement(By.id("sortMarks")).click();
        List<Integer> descendingMarks = getMarksFromTable();
        assertTrue("Marks should be sorted in descending order",
                isDescending(descendingMarks));


        // TASK 3: Apply the Pass filter and validate the displayed rows.
        // TODO 3a: Select the option with value Pass from #resultFilter.
        // TODO 3b: Find the displayed rows and assert that exactly 6 remain.
        // TODO 3c: Check that the result cell (fifth cell) of every row says Pass.

        List<WebElement> filteredRows = new ArrayList<>();
        assertEquals("Pass filter should display 6 students",
                6, filteredRows.size());

        // TODO: Loop through filteredRows and assert that each row's fifth cell is "Pass".
    }

    private List<Integer> getMarksFromTable() {
        // TODO: Find all table rows, read marks from the fourth cell,
        // convert each mark to an integer, and return the list.
        return new ArrayList<>();
    }

    private boolean isAscending(List<Integer> values) {
        // TODO: Return false if any value is greater than the next value.
        // Otherwise, return true.
        return false;
    }

    private boolean isDescending(List<Integer> values) {
        // TODO: Return false if any value is less than the next value.
        // Otherwise, return true.
        return false;
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
