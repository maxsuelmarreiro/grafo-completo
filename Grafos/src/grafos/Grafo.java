/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package grafos;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Fran
 */
public class Grafo {

    private ArrayList<Vertice> vertices;
    private boolean orientado;
    private ArrayList<Aresta> arestas;


    public Grafo() {
        vertices = new ArrayList<Vertice>();
        arestas = new ArrayList<Aresta>();
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

    public ArrayList<Aresta> getArestas() {
        return arestas;
    }

    public void setArestas(ArrayList<Aresta> arestas) {
        this.arestas = arestas;
    }

    public boolean verificaVertice(int v) {
        /**********************************************/
        /*** Verifica se a aresta já está no grafo ***/
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

    public boolean verificaAresta(int v1, int v2, int peso) {
        /**********************************************/
        /*** Verifica se o vertice já está no grafo ***/
        /**********************************************/
        boolean jaesta = false;
        for (Aresta aresta : arestas) {
            if (aresta.getV1().getId() == v1 && aresta.getV2().getId() == v2 && aresta.getPeso() == peso) {
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

    public void removeVertice(int v) {
        if (verificaVertice(v)) {
            /*******************************************/
            /*** Remove o vertice da lista principal ***/
            /*******************************************/
            for (Vertice vertice : vertices) {
                if (vertice.getId() == v) {
                    vertices.remove(vertice);
                    break;
                }
            }
            for (Vertice vertice : vertices) {
                Vertice ver = vertice;
                //enquanto não for o ultimo
                while (ver.getAdjacente() != null) {
                    //se o próximo é o vertice procurado
                    if (ver.getAdjacente().getId() == v) {
                        //então pego o adjacente do "proximo" e coloco ele no lugar do "proximo"
                        ver.setAdjacente(ver.getAdjacente().getAdjacente());
                    }
                    /*****************************************************************************************/
                    /* usado para o caso de ter removido o ultimo elemento, logo o adjacente seria null    ***/
                    /* e consequentimente o "ver" receberia null e daria erro na proxima iteração do while ***/
                    /*****************************************************************************************/
                    if (ver.getAdjacente() != null) {
                        ver = ver.getAdjacente();
                    }
                }
                if (ver.getAdjacente() == null) {
                    if (ver.getId() == v) {
                        ver = null;
                    }
                }

            }
        }

        /****************************************************/
        /*** Apaga todas as arestas que tem o vertice "v" ***/
        /****************************************************/
        for (int i = 0; i < arestas.size(); i++) {
            Aresta aresta = arestas.get(i);
            if (aresta.getV1().getId() == v || aresta.getV2().getId() == v) {
                arestas.remove(i);
                i--;
            }
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

                        /*************************/
                        /*** Adiciona a aresta ***/
                        /*************************/
                        Aresta aresta = new Aresta();

                        aresta.setV1(vertice);
                        aresta.setV2(ver2);
                        aresta.setPeso(peso);

                        arestas.add(aresta);
                    }
                    // break;
                }
            }
        }
    }

    public void removeAresta(int v1, int v2, int peso) {
        if (verificaVertice(v1) && verificaVertice(v2) && verificaAresta(v1, v2, peso)) {
            for (Vertice vertice : vertices) {
                /******************************************/
                /*** Se for o cara que eu to procurando ***/
                /******************************************/
                if (vertice.getId() == v1) {
                    Vertice ver = vertice;
                    //enquanto não for o ultimo
                    while (ver.getAdjacente() != null) {
                        //se o próximo é o vertice procurado
                        if (ver.getAdjacente().getId() == v2 && ver.getAdjacente().getPeso() == peso){
                            //então pego o adjacente do "proximo" e coloco ele no lugar do "proximo"
                            ver.setAdjacente(ver.getAdjacente().getAdjacente());
                        }
                        /*****************************************************************************************/
                        /* usado para o caso de ter removido o ultimo elemento, logo o adjacente seria null    ***/
                        /* e consequentimente o "ver" receberia null e daria erro na proxima iteração do while ***/
                        /*****************************************************************************************/
                        if (ver.getAdjacente() != null) {
                            ver = ver.getAdjacente();
                        }
                    }
                    if (ver.getAdjacente() == null) {
                        if (ver.getId() == v2 && ver.getPeso() == peso) {
                            ver = null;
                        }
                    }
                    break;
                }
            }
        }

        /****************************************************/
        /*** Apaga todas as arestas que tem o vertice "v" ***/
        /****************************************************/
        for (int i = 0; i < arestas.size(); i++) {
            Aresta aresta = arestas.get(i);
            if (aresta.getV1().getId() == v1 && aresta.getV2().getId() == v2 && aresta.getPeso() == peso) {
                arestas.remove(i);
                i--;
            }
        }

    }

    public void ImprimeGrafo() {
        if (orientado) {
            System.out.println("1");
        } else {
            System.out.println("0");
        }

        for (Vertice vertice : vertices) {
            Vertice v = vertice;

            while (v.getAdjacente() != null) {
                System.out.print(vertice.getId());
                System.out.print("-");
                System.out.print(v.getAdjacente().getId());
                System.out.print(":");

                System.out.println(v.getAdjacente().getPeso());

                v = v.getAdjacente();

            }
            if (v.getAdjacente() == null) {
                System.out.print(vertice.getId());
                System.out.print("-");
                System.out.print("*");
                System.out.print(":");
                System.out.println("0");
            }

        }
    }

    public boolean existeAresta(int v1, int v2) {
        boolean retorno = false;
        if (verificaVertice(v1) && verificaVertice(v2)) {
            /********************************/
            /*** Busca o v1 no array list ***/
            /********************************/
            for (Vertice vertice : vertices) {
                if (vertice.getId() == v1) {
                    /*****************************************************************/
                    /*** Se o vertice não tem adjacentes não precisa nem percorrer ***/
                    /*****************************************************************/
                    if (vertice.getAdjacente() == null) {
                        break;
                    }

                    /***********************************************************/
                    /*** Se tem, começa do segundo e percorre até achar o v2 ***/
                    /***********************************************************/
                    Vertice v = vertice.getAdjacente();
                    while (v.getAdjacente() != null) {
                        if (v.getId() == v2) {
                            retorno = true;
                            break;
                        }
                        v = v.getAdjacente();
                    }
                    if (v.getAdjacente() == null) {
                        if (v.getId() == v2) {
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

    public void ImprimeSucessores(int id_vertice) {
        if (orientado) {
            for (Vertice v : vertices) {
                if (v.getId() == id_vertice) {
                    while (v.getAdjacente() != null) {
                        System.out.println(v.getAdjacente().getId());
                        v = v.getAdjacente();
                    }
                }
            }
        } else {
            System.out.println("Este grafo não é orientado!");
        }
    }

    public void ImprimeAntecessores(int id_vertice) {
        if (orientado) {
            for (Vertice v : vertices) {

                while (v.getAdjacente() != null) {
                    if (v.getAdjacente().getId() == id_vertice) {
                        System.out.println(v.getId());
                    }
                    v = v.getAdjacente();
                }

            }
        } else {
            System.out.println("Este grafo não é orientado!");
        }
    }

    public void ImprimeAdjacentes(int id_vertice) {
        //ImprimeAntecessores(id_vertice);
        //ImprimeSucessores(id_vertice);
        for (Vertice v : vertices){
                if (v.getId() == id_vertice){
                    while(v.getAdjacente() != null){
                        System.out.println(v.getAdjacente().getId());
                        v=v.getAdjacente();
                    }
                }
            }

        for (Vertice v : vertices){

                    while(v.getAdjacente() != null){
                        if (v.getAdjacente().getId() == id_vertice){
                        System.out.println(v.getId());
                        }
                        v=v.getAdjacente();
                    }

            }
    }

    public void OrdenaGrau(){

        for (int i=vertices.size();i>1;i--){
            for (int j=1;j<i;j++){
                if (CalculaGrau(vertices.get(j-1).getId())>CalculaGrau(vertices.get(j).getId())){

                    Collections.swap(vertices, j, j-1);
                }
            }

        }

        for (Vertice v : vertices){
            System.out.println(v.getId());
           
        }
    }

    public int CalculaGrau(int idV)
    {
         int grau=0;
         for(Vertice v: vertices)
         {
           //procura o vertice de id 'idV' no array de vertices
           if(v.getId()== idV)
           {
               //conta os vértices que saem dele
               while(v.getAdjacente()!=null){

                   grau++;
                   v = v.getAdjacente();
               } 

           }
           
            //procura vertice de id 'idV' nos adjacentes de v para contar quantos entram nele
             while (v.getAdjacente()!=null)
             {
                 if(v.getAdjacente().getId()== idV)// vert é adjacente
                     grau++;
                  v = v.getAdjacente();
             }
       }
      
         return grau;
    }


    public int CalculaGrauSaida (int idV)
    {
       int grau=0;
       for(Vertice v: vertices)
       {
           //procura o vertice de id 'idV' no array de vertices
           if(v.getId()== idV)
           {
               //conta os vértices que saem dele
               while(v.getAdjacente()!=null){

                   grau++;
                   v = v.getAdjacente();
               } 

               break; // sai do for pq já achou todos
           }
       }
       System.out.print("O grau é: "+grau);
       return grau;
    }

    public int CalculaGrauEntrada (int idV)
    {
         int grau=0;
         for(Vertice v: vertices)
         {
             //procura vertice de id 'idV' nos adjacentes de v para contar quantos entram nele
             while (v.getAdjacente()!=null)
             {
                 if(v.getAdjacente().getId()== idV)// vert é adjacente
                     grau++;
                  v = v.getAdjacente();
             }
         }
         System.out.print("O grau é: "+grau);
         return grau;
    }

}
