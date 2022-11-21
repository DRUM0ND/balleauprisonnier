java --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml,javafx.media -jar balleauprisonnier_fx-0.0.1-jar-with-dependencies.jar
::cmd /k
::if not defined in_subprocess (cmd /k set in_subprocess=y ^& %0 %*) & exit ) :: used to see java errors, from stackoverflow