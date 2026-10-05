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

/**
 * Clase que representa el formulario para introducir los datos de un jugador.
 *
 * La clase hereda de GridPane para poder organizar los diferentes
 * controles del formulario en filas y columnas.
 *
 * En la columna 0 se colocan las etiquetas y en la columna 1
 * los controles correspondientes.
 */
public class FormJugador extends GridPane {

    // =========================
    // BOTONES
    // =========================

    /** Botón para guardar y mostrar los datos del jugador. */
    private Button btnMostrarDatos;

    /** Botón para limpiar todos los campos del formulario. */
    private Button btnLimpiar;


    // =========================
    // CAMPOS DE TEXTO
    // =========================

    /** Campo donde se introduce el NIF del jugador. */
    private final TextField tfNif;

    /** Campo donde se introduce el nombre del jugador. */
    private final TextField tfNombre;

    /** Campo donde se introducen los apellidos del jugador. */
    private final TextField tfApellidos;

    /** Campo donde se introduce la fecha de nacimiento. */
    private final TextField tfFecha;

    /** Campo donde se introduce el nombre del club. */
    private final TextField tfClub;


    // =========================
    // COMBOBOX
    // =========================

    /**
     * ComboBox que permite seleccionar la posición
     * que ocupa el jugador.
     */
    private final ComboBox<String> cmbPosicion;


    // =========================
    // SLIDERS
    // =========================

    /** Slider utilizado para seleccionar el sueldo. */
    private final Slider slSueldo;

    /** Slider utilizado para seleccionar el número del jugador. */
    private final Slider slNumero;

    /** Slider utilizado para seleccionar el número de goles. */
    private final Slider slGoles;

    /** Slider utilizado para seleccionar el número de asistencias. */
    private final Slider slAsistencias;


    // =========================
    // LABELS DE LOS SLIDERS
    // =========================

    /**
     * Label que muestra el valor actual seleccionado
     * para el sueldo.
     */
    private final Label lblSueldo;

    /** Label que muestra el número del jugador seleccionado. */
    private final Label lblNumero;

    /** Label que muestra el número de goles seleccionado. */
    private final Label lblGoles;

    /** Label que muestra el número de asistencias seleccionado. */
    private final Label lblAsistencias;


    // =========================
    // RADIO BUTTONS
    // =========================

    /** RadioButton para seleccionar un jugador comunitario. */
    private final RadioButton rbComunitario;

    /** RadioButton para seleccionar un jugador extranjero. */
    private final RadioButton rbExtranjero;

    /**
     * Grupo al que pertenecen los dos RadioButton.
     *
     * Al estar dentro del mismo ToggleGroup solamente
     * se puede seleccionar una de las dos opciones.
     */
    private final ToggleGroup tgBotonNacionalidad;


    // =========================
    // INFORMACIÓN
    // =========================

    /**
     * Label utilizado para mostrar los datos del jugador
     * cuando se pulsa el botón Guardar.
     */
    private final Label lblInfo;


    /**
     * Constructor de la clase FormJugador.
     *
     * En este método se crean y configuran todos los controles
     * del formulario y posteriormente se colocan dentro del GridPane.
     */
    public FormJugador() {

        // ==================================================
        // CREACIÓN DE LOS CAMPOS DE TEXTO
        // ==================================================

        // Campo para introducir el NIF.
        tfNif = new TextField();
        tfNif.setPromptText("Introduce el NIF");

        // Campo para introducir el nombre.
        tfNombre = new TextField();
        tfNombre.setPromptText("Nombre");

        // Campo para introducir los apellidos.
        tfApellidos = new TextField();
        tfApellidos.setPromptText("Apellidos");

        // Campo para introducir la fecha de nacimiento.
        // Se utiliza el formato YYYY-MM-DD.
        tfFecha = new TextField();
        tfFecha.setPromptText("YYYY-MM-DD");

        // Campo para introducir el club.
        tfClub = new TextField();
        tfClub.setPromptText("Club");


        // ==================================================
        // SLIDER DEL SUELDO
        // ==================================================

        // Label que muestra el sueldo actual.
        lblSueldo = new Label("Sueldo: 500000 €");

        /*
         * El Slider permite seleccionar un sueldo entre
         * 0 y 1.000.000 euros.
         *
         * El valor inicial es de 500.000 euros.
         */
        slSueldo = new Slider(0, 1000000, 500000);

        // Mostramos las marcas y los valores del Slider.
        slSueldo.setShowTickLabels(true);
        slSueldo.setShowTickMarks(true);

        // Cada marca principal representa 100.000 euros.
        slSueldo.setMajorTickUnit(100000);

        // El desplazamiento del Slider será de 10.000 euros.
        slSueldo.setBlockIncrement(10000);

        /*
         * Listener que se ejecuta cada vez que cambia
         * el valor del Slider.
         *
         * De esta forma el Label muestra siempre
         * el sueldo seleccionado.
         */
        slSueldo.valueProperty().addListener(
                (observable, valorAnterior, valorNuevo) -> {

                    lblSueldo.setText(
                            "Sueldo: "
                            + valorNuevo.intValue()
                            + " €"
                    );
                }
        );


        // ==================================================
        // SLIDER DEL NÚMERO DEL JUGADOR
        // ==================================================

        lblNumero = new Label("Número: 10");

        /*
         * El número del jugador puede estar entre 0 y 25.
         * El valor inicial es 10.
         */
        slNumero = new Slider(0, 25, 10);

        slNumero.setShowTickLabels(true);
        slNumero.setShowTickMarks(true);
        slNumero.setMajorTickUnit(5);
        slNumero.setBlockIncrement(1);

        /*
         * Actualizamos el Label cuando el usuario
         * mueve el Slider.
         */
        slNumero.valueProperty().addListener(
                (observable, valorAnterior, valorNuevo) -> {

                    lblNumero.setText(
                            "Número: "
                            + valorNuevo.intValue()
                    );
                }
        );


        // ==================================================
        // SLIDER DE GOLES
        // ==================================================

        lblGoles = new Label("Goles: 50");

        /*
         * El número de goles puede estar entre 0 y 100.
         * El valor inicial es 50.
         */
        slGoles = new Slider(0, 100, 50);

        slGoles.setShowTickLabels(true);
        slGoles.setShowTickMarks(true);
        slGoles.setMajorTickUnit(10);
        slGoles.setBlockIncrement(1);

        // Actualizamos el Label al cambiar el número de goles.
        slGoles.valueProperty().addListener(
                (observable, valorAnterior, valorNuevo) -> {

                    lblGoles.setText(
                            "Goles: "
                            + valorNuevo.intValue()
                    );
                }
        );


        // ==================================================
        // SLIDER DE ASISTENCIAS
        // ==================================================

        lblAsistencias = new Label("Asistencias: 50");

        /*
         * El número de asistencias puede estar entre 0 y 100.
         * El valor inicial es 50.
         */
        slAsistencias = new Slider(0, 100, 50);

        slAsistencias.setShowTickLabels(true);
        slAsistencias.setShowTickMarks(true);
        slAsistencias.setMajorTickUnit(10);
        slAsistencias.setBlockIncrement(1);

        // Actualizamos el Label al cambiar las asistencias.
        slAsistencias.valueProperty().addListener(
                (observable, valorAnterior, valorNuevo) -> {

                    lblAsistencias.setText(
                            "Asistencias: "
                            + valorNuevo.intValue()
                    );
                }
        );


        // ==================================================
        // COMBOBOX DE POSICIÓN
        // ==================================================

        // Creamos el ComboBox.
        cmbPosicion = new ComboBox<>();

        // Añadimos las posiciones disponibles.
        cmbPosicion.getItems().addAll(
                "Portero",
                "Defensa",
                "Mediocentro",
                "Delantero"
        );

        // Seleccionamos la primera posición por defecto.
        cmbPosicion.getSelectionModel().selectFirst();


        // ==================================================
        // RADIO BUTTONS DE NACIONALIDAD
        // ==================================================

        /*
         * Creamos un ToggleGroup para que los dos
         * RadioButton funcionen como opciones exclusivas.
         */
        tgBotonNacionalidad = new ToggleGroup();

        // Creamos las dos opciones.
        rbComunitario = new RadioButton("Comunitario");
        rbExtranjero = new RadioButton("Extranjero");

        // Introducimos ambos RadioButton en el mismo grupo.
        rbComunitario.setToggleGroup(tgBotonNacionalidad);
        rbExtranjero.setToggleGroup(tgBotonNacionalidad);

        // Seleccionamos Comunitario inicialmente.
        rbComunitario.setSelected(true);


        // ==================================================
        // LABEL PARA MOSTRAR LA INFORMACIÓN
        // ==================================================

        // Inicialmente el Label está vacío.
        lblInfo = new Label();


        // ==================================================
        // CREACIÓN DE LOS BOTONES
        // ==================================================

        btnLimpiar = new Button("Limpiar");
        btnMostrarDatos = new Button("Guardar");


        // ==================================================
        // CONFIGURACIÓN DEL GRIDPANE
        // ==================================================

        /*
         * Hgap establece el espacio horizontal entre
         * las columnas.
         */
        setHgap(10);

        /*
         * Vgap establece el espacio vertical entre
         * las filas.
         */
        setVgap(8);


        // ==================================================
        // COLOCACIÓN DE LOS CONTROLES
        // ==================================================

        /*
         * El primer número indica la columna.
         * El segundo número indica la fila.
         *
         * Columna 0 -> etiquetas.
         * Columna 1 -> controles.
         */

        add(new Label("NIF:"), 0, 0);
        add(tfNif, 1, 0);

        add(new Label("Nombre:"), 0, 1);
        add(tfNombre, 1, 1);

        add(new Label("Apellidos:"), 0, 2);
        add(tfApellidos, 1, 2);

        add(new Label("Fecha de nacimiento:"), 0, 3);
        add(tfFecha, 1, 3);

        add(new Label("Club:"), 0, 4);
        add(tfClub, 1, 4);

        add(lblSueldo, 0, 5);
        add(slSueldo, 1, 5);

        add(lblNumero, 0, 6);
        add(slNumero, 1, 6);

        add(lblGoles, 0, 7);
        add(slGoles, 1, 7);

        add(lblAsistencias, 0, 8);
        add(slAsistencias, 1, 8);

        add(new Label("Posición:"), 0, 9);
        add(cmbPosicion, 1, 9);

        add(rbComunitario, 0, 10);
        add(rbExtranjero, 1, 10);

        add(btnLimpiar, 0, 11);
        add(btnMostrarDatos, 1, 11);

        /*
         * lblInfo ocupa las dos columnas de la última fila.
         *
         * 0 -> columna inicial
         * 12 -> fila
         * 2 -> número de columnas que ocupa
         * 1 -> número de filas que ocupa
         */
        add(lblInfo, 0, 12, 2, 1);


        // ==================================================
        // EVENTO DEL BOTÓN LIMPIAR
        // ==================================================

        /*
         * Cuando se pulsa el botón Limpiar se ejecuta
         * el método limpiarFormulario().
         */
        btnLimpiar.setOnAction(evento -> {
            limpiarFormulario();
        });


        // ==================================================
        // EVENTO DEL BOTÓN GUARDAR
        // ==================================================

        /*
         * Cuando se pulsa Guardar se recogen los datos
         * introducidos en todos los controles y se muestran
         * en el Label lblInfo.
         */
        btnMostrarDatos.setOnAction(evento -> {

            String nacionalidad;

            /*
             * Comprobamos qué RadioButton está seleccionado
             * para obtener la nacionalidad del jugador.
             */
            if (rbComunitario.isSelected()) {
                nacionalidad = "Comunitario";
            } else {
                nacionalidad = "Extranjero";
            }

            /*
             * Construimos una cadena con todos los datos
             * del jugador.
             */
            String texto =
                    "NIF: " + tfNif.getText()
                    + " | Nombre: " + tfNombre.getText()
                    + " | Apellidos: " + tfApellidos.getText()
                    + " | Fecha: " + tfFecha.getText()
                    + " | Club: " + tfClub.getText()
                    + " | Sueldo: " + (int) slSueldo.getValue()
                    + " €"
                    + " | Número: " + (int) slNumero.getValue()
                    + " | Goles: " + (int) slGoles.getValue()
                    + " | Asistencias: " + (int) slAsistencias.getValue()
                    + " | Posición: " + cmbPosicion.getValue()
                    + " | Nacionalidad: " + nacionalidad;

            // Mostramos los datos en pantalla.
            lblInfo.setText(texto);
        });
    }


    /**
     * Obtiene los datos introducidos en el formulario
     * y crea un objeto de tipo Jugador.
     *
     * Este método permite obtener los datos del formulario
     * desde otra clase, por ejemplo desde App.java.
     *
     * @return un objeto Jugador con los datos introducidos.
     */
    public Jugador getJugador() {

        return new Jugador(
                tfNif.getText(),
                tfNombre.getText(),
                tfApellidos.getText(),
                tfFecha.getText(),
                tfClub.getText(),
                slSueldo.getValue(),
                (int) slNumero.getValue(),
                cmbPosicion.getValue(),
                (int) slGoles.getValue(),
                (int) slAsistencias.getValue()
        );
    }


    /**
     * Permite cambiar la acción que se ejecuta al pulsar
     * el botón Guardar desde otra clase.
     *
     * Por ejemplo, App.java puede utilizar este método
     * para decidir qué hacer con los datos del formulario.
     *
     * @param handler acción que se ejecutará al pulsar Guardar.
     */
    public void setOnGuardar(EventHandler<ActionEvent> handler) {

        btnMostrarDatos.setOnAction(handler);
    }


    /**
     * Limpia todos los campos del formulario.
     *
     * Los campos de texto se vacían, los Sliders vuelven
     * a cero, se selecciona la primera posición y se
     * vuelve a seleccionar la opción Comunitario.
     */
    public void limpiarFormulario() {

        // ------------------------------------------
        // Limpiar los campos de texto
        // ------------------------------------------

        tfNif.clear();
        tfNombre.clear();
        tfApellidos.clear();
        tfFecha.clear();
        tfClub.clear();


        // ------------------------------------------
        // Reiniciar los Sliders
        // ------------------------------------------

        slSueldo.setValue(0);
        slNumero.setValue(0);
        slGoles.setValue(0);
        slAsistencias.setValue(0);


        // ------------------------------------------
        // Reiniciar el ComboBox
        // ------------------------------------------

        cmbPosicion.getSelectionModel().selectFirst();


        // ------------------------------------------
        // Reiniciar los RadioButton
        // ------------------------------------------

        rbComunitario.setSelected(true);


        // ------------------------------------------
        // Borrar la información mostrada
        // ------------------------------------------

        lblInfo.setText("");
    }
}