/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author Omar Salazar
 */
public class Tutorial {
    
    private String tutorial;
    private int IdTutorial;
    private String Titulo;
    private String Url;
    private String Estado;
    private int prioridad;
    private int Categoria;
    private int IdCategoria;

    // Constructor vacío
    public Tutorial() {
        // Inicialización opcional de variables a valores por defecto
    }

    // Constructor con parámetros
public Tutorial(int IdTutorial, String tutorial, String Titulo, String Url, String Estado, int prioridad, int Categoria, int IdCategoria) {
    this.IdTutorial = IdTutorial;
    this.tutorial = tutorial;
    this.Titulo = Titulo;
    this.Url = Url;
    this.Estado = Estado;
    this.prioridad = prioridad;
    this.Categoria = Categoria;
    this.IdCategoria = IdCategoria;
}


    // Getters y setters

    public int getIdTutorial() {
        return IdTutorial;
    }

    public void setIdTutorial(int IdTutorial) {
        this.IdTutorial = IdTutorial;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public int getCategoria() {
        return Categoria;
    }

    public void setCategoria(int Categoria) {
        this.Categoria = Categoria;
    }

    public int getIdCategoria() {
        return IdCategoria;
    }

    public void setIdCategoria(int IdCategoria) {
        this.IdCategoria = IdCategoria;
    }

    public String getTutorial() {
        return tutorial;
    }

    public void setTutorial(String tutorial) {
        this.tutorial = tutorial;
    }
}

