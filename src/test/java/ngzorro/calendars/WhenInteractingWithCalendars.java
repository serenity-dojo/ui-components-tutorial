package ngzorro.calendars;

import com.google.common.collect.ImmutableMap;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import ngzorro.navigation.NavigateActions;
import ngzorro.tables.NZTableActions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SerenityJUnit5Extension.class)
class WhenInteractingWithCalendars {

    /**
     * Define the webdriver instance to be used for these tests
     */
    @Managed(driver = "chrome")
    WebDriver driver;

    @Steps
    NavigateActions navigate;

    @Steps
    DatePicker datePicker;

    @BeforeEach
    void openTheRightPage() {
        navigate.toTheCalendarPage();
    }

    public static final By DATE_FIELD = By.cssSelector("nz-demo-date-picker-extra-footer input");

    @Test
    void weCanPickADateInTheCalender() {

        LocalDate dateToPick = LocalDate.now().plusDays(3);
        datePicker.forDateField(DATE_FIELD).selectDate(dateToPick);

        assertThat(datePicker.forDateField(DATE_FIELD).getSelectedDate()).isEqualTo(dateToPick);

    }

}
