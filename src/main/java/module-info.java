module com.orestesk {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.orestesk to javafx.fxml;
    exports com.orestesk;
}
