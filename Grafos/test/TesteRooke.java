
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
        Grafo g = new Grafo();
        g.setOrientado(true);
        g.insereVertice(1);
        g.insereVertice(1);
        g.insereVertice(2);
        g.insereVertice(3);
        g.insereVertice(3);
        g.insereVertice(4);
        

        g.insereAresta(1, 2, 3);
        g.insereAresta(4, 1, 1);
        g.insereAresta(3, 2, 2);
        //g.ImprimeGrafo();
       // g.ImprimeSucessores(1);
       // g.ImprimeAntecessores(2);
        g.ImprimeAdjacentes(1);
        g.existeCaminho(1, 2);

        System.out.println("oi");

    }

}
