package ngzorro.tables;

import com.google.common.collect.ImmutableMap;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ngzorro.navigation.NavigateActions;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SerenityJUnit5Extension.class)
class WhenInteractingWithTables {

    /**
     * Define the webdriver instance to be used for these tests
     */
    @Managed(driver = "chrome")
    WebDriver driver;

    @Steps
    NavigateActions navigate;

    @Steps
    NZTableActions nzTable;

    @BeforeEach
    void openTheRightPage() {
        navigate.toTheTablesDemoPage();
    }

    public static final By DEMO_TABLE_BASIC = By.xpath("//nz-demo-table-basic//table");

    @Test
    void weCanReadTheRowsInATable() {
        List<Map<String,String>> rows = nzTable.rowsIn(DEMO_TABLE_BASIC);

        assertThat(rows.size()).isEqualTo(3);
        assertThat(rows.get(0).get("Name")).isEqualTo("John Brown");
        assertThat(rows.get(1).get("Name")).isEqualTo("Jim Green");
        assertThat(rows.get(2).get("Name")).isEqualTo("Joe Black");
    }

    @Test
    void weCanDeletaARowFromTheTable() {

        nzTable.deleteRowWithName(By.xpath("//nz-demo-table-basic//table"), "John Brown");
    }

    @Test
    void comparingTables() {
        List<Map<String,String>> actualTable = Arrays.asList(
                ImmutableMap.of("Name","John Brown", "Age","32"),
                ImmutableMap.of("Name","Jim Green", "Age","42"));

        List<Map<String,String>> expectedTable = Arrays.asList(
                ImmutableMap.of("Name","John Brown", "Age","33"),
                ImmutableMap.of("Name","Jim Green", "Age","42"));

        assertThat(actualTable).isEqualTo(expectedTable);
    }

}
