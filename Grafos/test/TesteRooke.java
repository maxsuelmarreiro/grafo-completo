
import grafos.Grafo;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rooke
 */
public class TesteRooke {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int grau=0;
        Grafo g = new Grafo();
        g.setOrientado(false);
        g.insereVertice(1);
        g.insereVertice(1);
        g.insereVertice(2);
        g.insereVertice(3);
        g.insereVertice(3);
        g.insereVertice(4);
        

        g.insereAresta(1, 2, 3);
        g.insereAresta(4, 1, 1);
        g.insereAresta(3, 4, 2);


        //g.removeVertice(1);

        g.removeAresta(1, 2, 3);

        //g.ImprimeGrafo();
       // g.ImprimeSucessores(1);
       // g.ImprimeAntecessores(2);

        g.ImprimeAdjacentes(1);
        g.existeAresta(1, 2);
        g.existeAresta(1, 5);
        g.existeAresta(1, 4);
        g.existeAresta(1, 1);
        g.existeAresta(2, 1);

        //g.ImprimeAdjacentes(4);

        g.OrdenaGrau();
        System.out.println("oi");


    }

}
