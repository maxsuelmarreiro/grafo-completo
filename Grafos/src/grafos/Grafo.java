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
   ;

    public Grafo() {
        vertices = new ArrayList<Vertice>();
    }

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

    public boolean verificaVertice(int v) {
        /**********************************************/
        /*** Verifica se o vertice já está no grafo ***/
        /**********************************************/
        boolean jaesta = false;
        for (Vertice vertice : vertices) {
            if (vertice.getId() == v) {
                jaesta = true;
                break;
            }
        }

        return jaesta;
    }

    public void insereVertice(int v) {

        /*************************************/
        /*** Se não tem o vertice no grafo ***/
        /*************************************/
        if (!verificaVertice(v)) {
            //cria um objeto vertice e adiciona ele no grafo
            Vertice ver = new Vertice();
            ver.setId(v);
            vertices.add(ver);
        }

    }

    public void insereAresta(int v1, int v2, int peso) {
        if (verificaVertice(v1) && verificaVertice(v2)) {
            /********************************/
            /*** Busca o v1 no array list ***/
            /********************************/
            for (Vertice vertice : vertices) {
                if (vertice.getId() == v1) {

                    Vertice v = vertice;
                    /*************************************************/
                    /*** Percorre até achar o ultimo cara da lista ***/
                    /*************************************************/

                    while (v.getAdjacente() != null) {

                        v = v.getAdjacente();


                    }

                    //Esse "v" é o ultimo cara!
                    if (v.getAdjacente() == null) {
                        //então coloca o v2 depois dele
                        Vertice ver2 = new Vertice();
                        ver2.setId(v2);
                        ver2.setPeso(peso);
                        v.setAdjacente(ver2);
                    }
                   // break;
                }
            }
        }
    }
   
    public void ImprimeGrafo() {
        if (orientado) {
            System.out.println("1");
        } else {
            System.out.println("0");
        }
         
        for (Vertice vertice : vertices){
            Vertice v = vertice;
            
            while (v.getAdjacente()!= null){
                System.out.print(vertice.getId());
                System.out.print("-");
                System.out.print(v.getAdjacente().getId());
                System.out.print(":");

                System.out.println(v.getAdjacente().getPeso());

                v = v.getAdjacente();

            }
            if (v.getAdjacente()== null){
                System.out.print(vertice.getId());
                System.out.print("-");
                System.out.print("*");
                System.out.print(":");
                System.out.println("0");
            }

        }
    }

    public boolean existeCaminho(int v1, int v2) {
        boolean retorno = false;
        if (verificaVertice(v1) && verificaVertice(v2)){
            /********************************/
            /*** Busca o v1 no array list ***/
            /********************************/
            for (Vertice vertice : vertices){
                if (vertice.getId() == v1){
                    Vertice v = vertice;
                    /*******************************/
                    /*** Percorre até achar o v2 ***/
                    /*******************************/
                    while (v.getAdjacente() != null) {
                        if(v.getId() == v2){
                            retorno = true;
                            break;
                        }
                        v = v.getAdjacente();
                    }
                    break;
                }
            }
        }
        return retorno;
    }

    public void ImprimeSucessores(int id_vertice){
        for (Vertice v : vertices){
            if (v.getId() == id_vertice){
                System.out.println(v.getAdjacente().getId());
            }
        }
    }
}
