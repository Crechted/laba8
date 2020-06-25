package Client.Application;

import Client.Application.OrganizationCircle;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.util.Duration;

public class Animation {

    public static void startAnimationRemove(OrganizationCircle organizationCircle) {
        FadeTransition ft = new FadeTransition(Duration.seconds(1), organizationCircle);
        ft.setFromValue(1);
        ft.setToValue(0);
        ScaleTransition st = new ScaleTransition(Duration.seconds(1), organizationCircle);
        st.setToX(1.5f);
        st.setToY(1.5f);

        ParallelTransition pt = new ParallelTransition();
        pt.getChildren().addAll(ft, st);
        pt.play();
    }

    public static void startAnimationClicked(OrganizationCircle organizationCircle) {
        ScaleTransition st = new ScaleTransition(Duration.millis(250), organizationCircle);
        st.setToX(1.2f);
        st.setToY(1.2f);
        st.setAutoReverse(true);
        st.setCycleCount(2);
        st.play();
    }
}
