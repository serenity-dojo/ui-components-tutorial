package ngzorro.calendars;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WebElementState;
import net.serenitybdd.core.steps.UIInteractions;
import org.assertj.core.api.AbstractStandardSoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatePicker extends UIInteractions {

    private static final String DAY_CELL_CSS_LOCATOR = "css:td[title='{0}']";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("M/d/yyyy");

    private By dateFieldLocator;

    public DatePicker forDateField(By dateFieldLocator) {
        this.dateFieldLocator = dateFieldLocator;
        return this;
    }

    public void selectDate(LocalDate date) {
        String formattedDate = date.format(DATE_FORMAT);
        $(dateFieldLocator).click();
        findAll(DAY_CELL_CSS_LOCATOR,formattedDate)
                .stream()
                .filter(WebElementState::isCurrentlyVisible)
                .forEach(WebElementFacade::click);
    }

    public LocalDate getSelectedDate() {
        String dateValue = $(dateFieldLocator).getValue();
        return LocalDate.parse(dateValue);
    }
}
