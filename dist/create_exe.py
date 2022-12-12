import subprocess

# jdk\bin\java.exe --module-path javafx\lib --add-modules javafx.controls,javafx.fxml,javafx.swing -jar setup.jar

jdk_path = "jdk\\bin\\java.exe"
jfx_path = "javafx\\lib"
modules = "javafx.controls,javafx.fxml,javafx.swing"
command = jdk_path + " --module-path " + jfx_path + " --add-modules " + modules + " -jar setup.jar"

subprocess.call(command, shell=True)
