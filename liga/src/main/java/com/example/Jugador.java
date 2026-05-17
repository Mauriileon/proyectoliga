package com.example;

public class Jugador {
    private String nif, nombre, apellidos, fecha, club, posicion;
    private double sueldo;
    private int numero, goles, asistencias;

    public Jugador(String nif, String nombre, String apellidos, String fecha,
            String club, double sueldo, int numero, String posicion,
            int goles, int asistencias) {
        this.nif = nif;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fecha = fecha;
        this.club = club;
        this.sueldo = sueldo;
        this.numero = numero;
        this.posicion = posicion;
        this.goles = goles;
        this.asistencias = asistencias;
    }

  
    public String getNif()         {
     return nif; }
    
     public String getNombre(){ 
        return nombre; }
   
        public String getApellidos(){ 
        return apellidos; }
    
       public String getFecha()   { 
        return fecha; }
    
       public String getClub()        { 
     return club; }
    
        public double getSueldo()      { 
        return sueldo; }
    
        public int    getNumero()      { 
        return numero; }
   
        public String getPosicion()    {
         return posicion; }
    
         public int    getGoles()       {
   
        return goles; }
    public int    getAsistencias() {
         return asistencias; }

    @Override
    public String toString() {
        return nif + " | " + nombre + " " + apellidos + " | " + posicion +
                " | Sueldo: " + sueldo + " | Goles: " + goles;
    }

    // para exportar a fichero 
    public String toCsv() {
        return String.join(";", nif, nombre, apellidos, fecha, club,
               String.valueOf(sueldo), String.valueOf(numero),
               posicion, String.valueOf(goles), String.valueOf(asistencias));
    }

    // para importar desde fichero 
    public static Jugador fromCsv(String linea) {
        String[] p = linea.split(";");
        return new Jugador(p[0], p[1], p[2], p[3], p[4],
               Double.parseDouble(p[5]), Integer.parseInt(p[6]),
               p[7], Integer.parseInt(p[8]), Integer.parseInt(p[9]));
    }
}