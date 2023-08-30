module net.niceeli.gravitarviewer.gravitarviewer {
    requires javafx.controls;
    requires javafx.fxml;


    opens net.niceeli.gravitarviewer.gravitarviewer to javafx.fxml;
    exports net.niceeli.gravitarviewer.gravitarviewer;
}