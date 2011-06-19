
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
//        g.setOrientado(true);
//        g.insereVertice(1);
//        g.insereVertice(1);
//        g.insereVertice(2);
//        g.insereVertice(3);
//        g.insereVertice(3);
//        g.insereVertice(4);
//
//        g.insereAresta(2, 1, 3);
//        g.insereAresta(1, 4, 1);
//        g.insereAresta(4, 3, 2);
//        g.insereAresta(3,1, 7);

//        g.insereVertice(1);//0
//        g.insereVertice(2);//1
//        g.insereVertice(3);//2
//        g.insereVertice(4);//3
//        g.insereVertice(5);//4
//        g.insereVertice(6);//5
//        g.insereVertice(7);//6
//
//        g.insereAresta(1,2,10);
//        g.insereAresta(2,3,11);
//        g.insereAresta(3,6,12);
//        g.insereAresta(6,3,13);
//        g.insereAresta(2,5,14);
//        g.insereAresta(4,1,15);
//        g.insereAresta(5,4,16);
//        g.insereAresta(5,6,17);
//        g.insereAresta(7,6,18);
//        g.insereAresta(7,4,19);
//        g.insereAresta(7,5,20);



        
//        System.out.print("Estou imprimindo o FTD de: " + g.getVertices().get(4).getId()+"\n");
//        g.imprimeFTD(g.getVertices().get(4));
//
//        boolean tem1=false;
//        boolean tem2=false;
//        boolean tem3=false;
//        boolean tem4=false;
//        boolean tem5=false;
//        boolean tem6=false;
//        boolean tem7=false;

        
//        //verificar se tem caminho do 5 para todos
//       tem1= g.existeCaminho(g.getVertices().get(4),g.getVertices().get(0));
//       tem2= g.existeCaminho(g.getVertices().get(4),g.getVertices().get(1));
//        tem3= g.existeCaminho(g.getVertices().get(4),g.getVertices().get(2),visitados);
//        tem4= g.existeCaminho(g.getVertices().get(4),g.getVertices().get(3),visitados);
//        tem5= g.existeCaminho(g.getVertices().get(4),g.getVertices().get(4),visitados);
//        tem6= g.existeCaminho(g.getVertices().get(4),g.getVertices().get(5),visitados);
//        tem7= g.existeCaminho(g.getVertices().get(4),g.getVertices().get(6),visitados);
//
//      //ver se os outros sao caminho
//       System.out.print(g.getVertices().get(0).getId()+" é caminho ?"+tem1+"\n");
//       System.out.print(g.getVertices().get(1).getId()+" é caminho ?"+tem2+"\n");
//        System.out.print(g.getVertices().get(2).getId()+" é caminho?"+tem3+"\n");
//        System.out.print(g.getVertices().get(3).getId()+" é caminho?"+tem4+"\n");
//        System.out.print(g.getVertices().get(4).getId()+" é caminho?"+tem5+"\n");
//        System.out.print(g.getVertices().get(5).getId()+" é caminho?"+tem6+"\n");
//        System.out.print(g.getVertices().get(6).getId()+" é caminho?"+tem7+"\n");


        //g.removeVertice(1);

        //g.removeAresta(1, 2, 3);

        //g.ImprimeGrafo();
       // g.ImprimeSucessores(1);
       // g.ImprimeAntecessores(2);

       // g.ImprimeAdjacentes(1);
       // g.CalculaGrau(1);
     
     
       // boolean tem = g.existeCaminho(g.getVertices().get(0),g.getVertices().get(2));
       // System.out.print(tem);
        //g.ImprimeAdjacentes(4);

      
     //   System.out.println("oi");

//        g.imprimeFTD(g.getVertices().get(6));
//        g.imprimeFTI(g.getVertices().get(6));

           g.insereVertice(1);//0
           g.insereVertice(2);//1
           g.insereVertice(3);//2
           g.insereVertice(4);//3
           g.insereVertice(5);//4

           g.insereAresta(1,2,1);
           g.insereAresta(1,5,1);
           g.insereAresta(2,3,1);
           g.insereAresta(2,4,1);
           g.insereAresta(2,5,1);
           g.insereAresta(3,4,1);
           g.insereAresta(4,5,1);

           g.Coloracao(g);

    }

}
