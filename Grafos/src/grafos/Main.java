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
         

      try {
         FileReader reader = new FileReader("grafo.txt");
         BufferedReader leitor = new BufferedReader(reader);
         StringTokenizer vert_peso = null;
         StringTokenizer vert = null;


         while ((linha = leitor.readLine()) != null) {


            vert_peso = new StringTokenizer(linha, ": -");
            ArrayList<String> aresta =new ArrayList<String>();
            
            while (vert_peso.hasMoreTokens()) {

               aresta.add(vert_peso.nextToken());
               vert = new StringTokenizer(linha, "-");
            }
            System.out.println(aresta);
         }
         leitor.close();
         reader.close();

      } catch (Exception e) {
         e.printStackTrace();
      }
    }

}
