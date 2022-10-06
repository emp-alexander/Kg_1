module com.example.lr1_kg {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lr1_kg to javafx.fxml;
    exports com.example.lr1_kg;
}