
import grafos.Grafo;
import java.util.ArrayList;

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
  
        g.insereAresta(2, 1, 3);
        g.insereAresta(1, 4, 1);
        g.insereAresta(4, 3, 2);
             

        System.out.print("g.getVertices().get(1)"+g.getVertices().get(1).getId()+"\n");
        System.out.print("g.getVertices().get(2)"+g.getVertices().get(2).getId()+"\n");
        System.out.print("g.getVertices().get(3)"+g.getVertices().get(3).getId()+"\n");
        System.out.print("g.getVertices().get(3)"+g.getVertices().get(4).getId()+"\n");




        //g.removeVertice(1);

        g.removeAresta(1, 2, 3);

        //g.ImprimeGrafo();
       // g.ImprimeSucessores(1);
       // g.ImprimeAntecessores(2);

       // g.ImprimeAdjacentes(1);
       // g.CalculaGrau(1);
     
     
        //g.existeCaminho(1, 2);

        //g.ImprimeAdjacentes(4);

       // g.OrdenaGrau();
  //      System.out.println("oi");


    }

}
