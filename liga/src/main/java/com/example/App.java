package com.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URI;
import java.net.http.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class App extends Application {

    private final ArrayList<Jugador> listaJugadores = new ArrayList<>();
    private FormJugador formJugador;
    private TextArea taListado;
    private TabPane tabPane;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Gestión de Jugadores");

        formJugador = new FormJugador();
        formJugador.setOnGuardar(e -> guardarJugador());

        // pestaña formulario
        ScrollPane scroll = new ScrollPane(formJugador);
        scroll.setFitToWidth(true);
        Tab tabFormulario = new Tab("Formulario", scroll);

        // pestaña listado
        taListado = new TextArea();
        taListado.setEditable(false);
        Button btnActualizar = new Button("Actualizar");
        btnActualizar.setOnAction(e -> actualizarListado());
        VBox vboxListado = new VBox(8, btnActualizar, taListado);
        vboxListado.setStyle("-fx-padding: 10;");
        VBox.setVgrow(taListado, Priority.ALWAYS);
        Tab tabListado = new Tab("Listado", vboxListado);

        // pestaña buscar en internet (P5)
        Tab tabApi = crearTabApi();

        tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.getTabs().addAll(tabFormulario, tabListado, tabApi);

        BorderPane root = new BorderPane();
        root.setTop(crearMenuBar(stage));
        root.setCenter(tabPane);

        Scene scene = new Scene(root, 520, 560);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    private void guardarJugador() {
        Jugador j = formJugador.getJugador();
        listaJugadores.add(j);
        try {
            JugadorDAO.insertar(j);
        } catch (SQLException ex) {
            new Alert(Alert.AlertType.ERROR, ex.getMessage()).showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Jugador " + j.getNombre() + " guardado.");
        alert.showAndWait();
        formJugador.limpiar();
    }

    private void actualizarListado() {
        try {
            List<Jugador> jugadores = JugadorDAO.listar();
            StringBuilder sb = new StringBuilder();
            for (Jugador j : jugadores) sb.append(j.toString()).append("\n");
            taListado.setText(sb.isEmpty() ? "(Sin jugadores)" : sb.toString());
        } catch (SQLException ex) {
            new Alert(Alert.AlertType.ERROR, ex.getMessage()).showAndWait();
        }
    }

    private Tab crearTabApi() {
        TextField tfBusqueda = new TextField();
        tfBusqueda.setPromptText("Título de la película...");
        TextArea taResult = new TextArea();
        taResult.setEditable(false);
        Label lblTitulo = new Label("Título: —");
        Label lblRating = new Label("IMDb: —");
        Button btnBuscar = new Button("Buscar");

        btnBuscar.setOnAction(e -> {
            String url = "https://www.omdbapi.com/?t=" +
                         tfBusqueda.getText().trim().replace(" ", "+") +
                         "&apikey=TU_CLAVE_AQUI";
            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest req = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
                String json = client.send(req, HttpResponse.BodyHandlers.ofString()).body();
                taResult.setText(json);
                if (json.contains("\"Response\":\"False\"")) {
                    taResult.setText("Película no encontrada.");
                } else {
                    lblTitulo.setText("Título: " + extraerJson(json, "Title"));
                    lblRating.setText("IMDb: "   + extraerJson(json, "imdbRating"));
                }
            } catch (Exception ex) {
                new Alert(Alert.AlertType.ERROR, ex.getMessage()).showAndWait();
            }
        });

        VBox vbox = new VBox(8, new HBox(8, tfBusqueda, btnBuscar), taResult, lblTitulo, lblRating);
        vbox.setStyle("-fx-padding: 10;");
        VBox.setVgrow(taResult, Priority.ALWAYS);
        return new Tab("Buscar en internet", vbox);
    }

    private String extraerJson(String json, String campo) {
        String buscar = "\"" + campo + "\":\"";
        int ini = json.indexOf(buscar);
        if (ini == -1) return "N/A";
        ini += buscar.length();
        int fin = json.indexOf("\"", ini);
        return fin == -1 ? "N/A" : json.substring(ini, fin);
    }

    private MenuBar crearMenuBar(Stage stage) {
        MenuBar menuBar = new MenuBar();

        Menu mArchivo = new Menu("Archivo");
        MenuItem miNuevo = new MenuItem("Nuevo jugador");

        
        miNuevo.setOnAction(e -> { tabPane.getSelectionModel().select(0); formJugador.limpiar(); });
        MenuItem miExportar = new MenuItem("Exportar listado...");



        miExportar.setOnAction(e -> exportarFichero(stage));
        MenuItem miImportar = new MenuItem("Importar listado...");


        miImportar.setOnAction(e -> importarFichero(stage));
        MenuItem miSalir = new MenuItem("Salir");


        miSalir.setOnAction(e -> Platform.exit());
        mArchivo.getItems().addAll(miNuevo, miExportar, miImportar, new SeparatorMenuItem(), miSalir);

        Menu mVer = new Menu("Ver");
        MenuItem miListado = new MenuItem("Listado");

        miListado.setOnAction(e -> tabPane.getSelectionModel().select(1));
        mVer.getItems().add(miListado);

        Menu mAyuda = new Menu("Ayuda");
        MenuItem miAcerca = new MenuItem("Acerca de...");
        miAcerca.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Acerca de");
            alert.setContentText("Gestión de Jugadores\nTu Nombre");
            alert.showAndWait();
        });
        mAyuda.getItems().add(miAcerca);

        menuBar.getMenus().addAll(mArchivo, mVer, mAyuda);
        return menuBar;
    }

    private void exportarFichero(Stage stage) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Texto (*.txt)", "*.txt"));
        File file = fc.showSaveDialog(stage);
        if (file == null) return;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Jugador j : listaJugadores) { bw.write(j.toCsv()); bw.newLine(); }
            new Alert(Alert.AlertType.INFORMATION, "Exportados: " + listaJugadores.size()).showAndWait();
        } catch (IOException ex) {
            new Alert(Alert.AlertType.ERROR, ex.getMessage()).showAndWait();
        }
    }

    private void importarFichero(Stage stage) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Texto (*.txt)", "*.txt"));
        File file = fc.showOpenDialog(stage);
        if (file == null) return;
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.isBlank()) { listaJugadores.add(Jugador.fromCsv(linea)); count++; }
            }
            new Alert(Alert.AlertType.INFORMATION, "Importados: " + count).showAndWait();
        } catch (IOException ex) {
            new Alert(Alert.AlertType.ERROR, ex.getMessage()).showAndWait();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}