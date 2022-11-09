module com.orestesk {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    opens com.orestesk to javafx.fxml;
    exports com.orestesk;
}
