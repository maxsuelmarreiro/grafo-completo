/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package grafos;

/**
 *
 * @author rooke
 */
public class Vertice {
    private Vertice adjacente;
    private int id;
    private int peso;
    int cor=0;

    public Vertice getAdjacente() {
        return adjacente;
    }

    public void setAdjacente(Vertice adjacente) {
        this.adjacente = adjacente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    
}
