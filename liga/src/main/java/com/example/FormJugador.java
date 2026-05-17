package com.example;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

/*Crea una clase FormEntidad que extienda GridPane. Añade controles, uno por fila, para cada campo, debe de haber slider para valores númericos, textfield para texto normal, ComboBox para selección de varias opciones, y checkbox o radiobox también. Si no hay campos para dichos controles, amplía la tabla con más campos.

El GridPane debe tener etiquetas en la columna 0 y controles en la columna 1. Usa setHgap(10) y setVgap(8).

 */

public class FormJugador extends GridPane {

    private Button btnMostrarDatos;
    private Button btnLimpiar;

    private final TextField tfNif;
    private final TextField tfNombre;
    private final TextField tfApellidos;
    private final TextField tfFecha; // formato yyyy-MM-dd
    private final TextField tfClub;
    private final ComboBox<String> cmbPosicion;

    private final Slider slSueldo;
    private final Slider slNumero;
    private final Slider slGoles;
    private final Slider slAsistencias;
    private final Label lblSueldo;
    private final Label lblNumero;
    private final Label lblGoles;
    private final Label lblAsistencias;
    private final RadioButton rbComunitario;
    private final RadioButton rbExtranjero;
    private final ToggleGroup tgBotonNacionalidad;

    private final Label lblInfo = new Label(); // muestra datos al pulsar el botón

    public FormJugador() {

        tfNif = new TextField("Introduce el NIF del jugador");

        tfNombre = new TextField("Nombre");

        tfApellidos = new TextField("Apellidos");

        tfFecha = new TextField("(YYYY-MM-DD)");

        tfClub = new TextField("CLUB");

        lblSueldo = new Label("Sueldo del Jugador");
        slSueldo = new Slider(0, 1000000, 500000);
        slSueldo.setShowTickLabels(true);
        slSueldo.setShowTickMarks(true);
        slSueldo.setMajorTickUnit(100000);
        slSueldo.setBlockIncrement(10000);
        slSueldo.valueProperty().addListener((obs, oldVal, newVal) -> {
            lblSueldo.setText("Sueldo del Jugador: " + newVal.intValue() + " €");
        });

        lblGoles = new Label("Número de Goles del Jugador");
        slGoles = new Slider(0, 100, 50);
        slGoles.setShowTickLabels(true);
        slGoles.setShowTickMarks(true);
        slGoles.setMajorTickUnit(10);
        slGoles.setBlockIncrement(1);
        slGoles.valueProperty().addListener((obs, oldVal, newVal) -> {
            lblGoles.setText("Número de Goles del Jugador : " + newVal.intValue());
        });

        lblNumero = new Label("Número del Jugador");
        slNumero = new Slider(0, 25, 10);
        slNumero.setShowTickLabels(true);
        slNumero.setShowTickMarks(true);
        slNumero.setMajorTickUnit(2);
        slNumero.setBlockIncrement(1);
        // añadir un listener para que se vea el numero al laodo que se va seleccionando
        slNumero.valueProperty().addListener((obs, oldVal, newVal) -> {
            lblNumero.setText("Número del Jugador: " + newVal.intValue());
        });

        lblAsistencias = new Label("Número de Asistencias del Jugador");
        slAsistencias = new Slider(0, 100, 50);
        slAsistencias.setShowTickLabels(true);
        slAsistencias.setShowTickMarks(true);
        slAsistencias.setMajorTickUnit(10);
        slAsistencias.setBlockIncrement(1);
        // añadir un listener para que se vea el numero al laodo que se va seleccionando

        slAsistencias.valueProperty().addListener((obs, oldVal, newVal) -> {
            lblAsistencias.setText("Número de Asistencias del Jugador: " + newVal.intValue());
        });

        tgBotonNacionalidad = new ToggleGroup();
        rbComunitario = new RadioButton("Comunitario");
        rbExtranjero = new RadioButton("Extranjero");
        rbComunitario.setToggleGroup(tgBotonNacionalidad);
        rbExtranjero.setToggleGroup(tgBotonNacionalidad);

        cmbPosicion = new ComboBox<>();
        cmbPosicion.getItems().addAll("Portero", "Defensa", "Mediocentro", "Delantero");

        this.setHgap(10);
        this.setVgap(8);

        btnLimpiar = new Button("Limpiar");
        btnMostrarDatos = new Button("Guardar");

        // quiero que apareca todo uno debajo del otro
        this.add(new Label("NIF:"), 0, 0);
        this.add(tfNif, 1, 0);
        this.add(new Label("Nombre:"), 0, 1);
        this.add(tfNombre, 1, 1);
        this.add(new Label("Apellidos:"), 0, 2);
        this.add(tfApellidos, 1, 2);
        this.add(new Label("Fecha de Nacimiento:"), 0, 3);
        this.add(tfFecha, 1, 3);
        this.add(new Label("Club:"), 0, 4);
        this.add(tfClub, 1, 4);
        this.add(lblSueldo, 0, 5);
        this.add(slSueldo, 1, 5);
        this.add(lblNumero, 0, 6);
        this.add(slNumero, 1, 6);
        this.add(lblGoles, 0, 7);
        this.add(slGoles, 1, 7);
        this.add(lblAsistencias, 0, 8);
        this.add(slAsistencias, 1, 8);
        this.add(new Label("Posición:"), 0, 9);
        this.add(cmbPosicion, 1, 9);
        this.add(rbComunitario, 0, 10);
        this.add(rbExtranjero, 1, 10);
        this.add(btnLimpiar, 0, 11);
        this.add(btnMostrarDatos, 1, 11);
        this.add(lblInfo, 0, 12, 2, 1); // label info ocupa 2 columnas

        btnLimpiar.setOnAction(e -> limpiar());

        btnMostrarDatos.setOnAction(e -> {
            String texto = "NIF: " + tfNif.getText() +
                    " | Nombre: " + tfNombre.getText() +
                    " | Apellidos: " + tfApellidos.getText() +
                    " | Fecha: " + tfFecha.getText() +
                    " | Club: " + tfClub.getText() +
                    " | Sueldo: " + (int) slSueldo.getValue() +
                    " | Número: " + (int) slNumero.getValue() +
                    " | Goles: " + (int) slGoles.getValue() +
                    " | Asistencias: " + (int) slAsistencias.getValue() +
                    " | Posición: " + cmbPosicion.getValue() +
                    " | Tipo: " + (rbComunitario.isSelected() ? "Comunitario" : "Extranjero");
            lblInfo.setText(texto);
        });
    }

    // devuelve un objeto Jugador con los datos del formulario
    public Jugador getJugador() {
        return new Jugador(
                tfNif.getText(), tfNombre.getText(), tfApellidos.getText(),
                tfFecha.getText(), tfClub.getText(), slSueldo.getValue(),
                (int) slNumero.getValue(), cmbPosicion.getValue(),
                (int) slGoles.getValue(), (int) slAsistencias.getValue());
    }

    // permite cambiar la acción del botón desde App.java
    public void setOnGuardar(EventHandler<ActionEvent> handler) {
        btnMostrarDatos.setOnAction(handler);
    }

    public void limpiar() {
        tfNif.clear();
        tfNombre.clear();
        tfApellidos.clear();
        tfFecha.clear();
        tfClub.clear();
        slSueldo.setValue(0);
        slNumero.setValue(0);
        slGoles.setValue(0);
        slAsistencias.setValue(0);
        cmbPosicion.getSelectionModel().selectFirst();
        rbComunitario.setSelected(false);
        rbExtranjero.setSelected(false);
        lblInfo.setText("");
    }

}