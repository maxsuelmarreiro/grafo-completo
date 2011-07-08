/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package grafos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author Fran
 */
public class Main {



   
    public static void main(String[] args) {

        String linha = null;
        int grau=0;

        Grafo g = new Grafo();
        Vertice v =new Vertice();
        Aresta a = new Aresta();

      try {
         FileReader reader = new FileReader("grafo.txt");
         BufferedReader leitor = new BufferedReader(reader);
         StringTokenizer numero = null;
         StringTokenizer vert = null;
         ArrayList<String> numeros =new ArrayList<String>();

         while ((linha = leitor.readLine()) != null) {


            numero = new StringTokenizer(linha, ": -");
            
            
            while (numero.hasMoreTokens()) {

               numeros.add(numero.nextToken());
               //vert = new StringTokenizer(linha, "-");
            }
            //

         }
         leitor.close();
         reader.close();
         if(numeros.get(0).equals("0")){
            // System.out.println("false");
             g.setOrientado(false);
         }
         else{
             if(numeros.get(0).equals("1")){
               //  System.out.println("true");
                 g.setOrientado(true);
             }
         }
         /****************************************************/
        /*** Faz a inserção dos vértices ***/
        /****************************************************/
         for(int i=1;i<(numeros.size());i=i+3){
             g.insereVertice(Integer.parseInt(numeros.get(i)));
           //  System.out.println(numeros.get(i));
         }
         //System.out.println("pares:  ");
         for(int i=2;i<(numeros.size());i=i+3){
             g.insereVertice(Integer.parseInt(numeros.get(i)));
            // System.out.println(numeros.get(i));
         }
         /****************************************************/
        /*** Faz a inserção das arestas***/
        /****************************************************/
         for(int i=1;i<(numeros.size());i=i+3){

               g.insereAresta(Integer.parseInt(numeros.get(i)),Integer.parseInt(numeros.get(i+1)),Integer.parseInt(numeros.get(i+2)));
               //System.out.println(numeros.get(i)+":"+numeros.get(i+1)+"-"+numeros.get(i+2));


         }

         //g.ImprimeMatrizes();
         //g.ImprimeGrafo();
         //g.ImprimeAdjacentes(5);
        // g.ImprimeAntecessores(5);
         System.out.println(g.getVertices().get(1).getId() + "oiii");

         ArrayList<Integer> abc = g.CriaArrayVisitados(g.getVertices().get(1));
          for (Integer integer : abc) {
              System.out.println(integer);
          }
        // System.out.println(numeros);
      } catch (Exception e) {
         e.printStackTrace();
      }
    }

}
