/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package grafos;

import java.util.ArrayList;


/**
 *
 * @author Fran
 */
public class Grafo {
    private ArrayList<Vertice> vertices;
    private boolean orientado;

    public boolean isOrientado() {
        return orientado;
    }

    public void setOrientado(boolean orientado) {
        this.orientado = orientado;
    }

    public ArrayList<Vertice> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Vertice> vertices) {
        this.vertices = vertices;
    }

    public void ImprimeGrafo() {
        if (orientado) {
            System.out.println("1");
        }
        else{
            System.out.println("0");
            }
        for (Vertice vertice : vertices){
            while (vertice.getAdjacente()!= null){
                System.out.print(vertice.getId());
                System.out.print("-");
                System.out.print(vertice.getAdjacente().getId());
                System.out.print(":");
                System.out.print(vertice.getPeso());


            }
            if (vertice.getAdjacente()== null){
                System.out.print(vertice.getId());
                System.out.print("-");
                System.out.print("*");
                System.out.print(":");
                System.out.print("0");
            }

        }
    }
    
}
