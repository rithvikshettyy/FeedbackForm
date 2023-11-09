@echo FEEDBACK FORM IN JAVA and JAVAFX
cd src
set PATH_TO_FX="../lib/"
javac --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml *.java
java --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml App.java
