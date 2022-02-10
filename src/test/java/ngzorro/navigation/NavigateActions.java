package ngzorro.navigation;

import net.serenitybdd.core.steps.UIInteractions;
import net.thucydides.core.annotations.Step;

/**
 * UIInteractionSteps let us define action classes which regroup related actions.
 * The @Step annotation is used to indicate that this action will appear as a step in the reports.
 */
public class NavigateActions extends UIInteractions {
    public void toTheTablesDemoPage() {
        openUrl("https://ng-zorro.gitee.io/components/table/en");
    }

    public void toTheCalendarPage() {
        openUrl("https://ng.ant.design/components/date-picker/en#nz-range-picker");
    }

}
