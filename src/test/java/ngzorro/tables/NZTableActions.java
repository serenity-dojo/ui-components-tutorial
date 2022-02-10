package ngzorro.tables;

import net.serenitybdd.core.steps.UIInteractions;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NZTableActions extends UIInteractions {
    public List<Map<String, String>> rowsIn(By tableLocator) {

        List<Map<String, String>> tableContents = new ArrayList<>();
        List<String> columnHeaders = find(tableLocator).thenFindAll(By.tagName("th")).texts();

        find(tableLocator)
                .thenFindAll(By.cssSelector("tbody tr"))
                .forEach(
                        rowElement -> {
                            List<String> textValues = rowElement.thenFindAll("td").texts();
                            Map<String, String> rowValues = new HashMap<>();
                            for (int columnNumber = 0; columnNumber < textValues.size(); columnNumber++) {
                                rowValues.put(columnHeaders.get(columnNumber), textValues.get(columnNumber));
                            }
                            tableContents.add(rowValues);
                        }
                );
        return tableContents;
    }

    public void deleteRowWithName(By tableLocator, String name) {
        String action = "Delete";
        find(tableLocator)
                .thenFind(".//tr[contains(.,'{0}')]",name, action)
                .then(By.linkText(action))
                .click();
    }
}
