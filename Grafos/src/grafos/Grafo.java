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

    public ArrayList<Integer> ImprimeSucessores(int id_vertice) {
         ArrayList<Integer> adjacentes = new ArrayList<Integer>();
        if (orientado) {
            for (Vertice v : vertices) {
                if (v.getId() == id_vertice) {
                    while (v.getAdjacente() != null) {
                        adjacentes.add(v.getAdjacente().getId());
                        //System.out.println(v.getAdjacente().getId());
                        v = v.getAdjacente();
                    }
                }
            }
        } else {
            System.out.println("Este grafo não é orientado!");
        }
         return adjacentes;
    }

    public ArrayList<Integer> ImprimeAntecessores(int id_vertice) {
        ArrayList<Integer> adjacentes = new ArrayList<Integer>();
        if (orientado) {
            for (Vertice v : vertices) {

                while (v.getAdjacente() != null) {
                    if (v.getAdjacente().getId() == id_vertice) {
                         adjacentes.add(v.getId());
                       // System.out.println(v.getId());
                    }
                    v = v.getAdjacente();
                }

            }
        } else {
            System.out.println("Este grafo não é orientado!");
        }
        return adjacentes;
    }

    public ArrayList<Integer> ImprimeAdjacentes(int id_vertice) {
        //ImprimeAntecessores(id_vertice);
        //ImprimeSucessores(id_vertice);
         ArrayList<Integer> adjacentes = new ArrayList<Integer>();

        for (Vertice v : vertices){
                if (v.getId() == id_vertice){
                    while(v.getAdjacente() != null){
                        //System.out.println(v.getAdjacente().getId());
                        adjacentes.add(v.getAdjacente().getId());
                        v=v.getAdjacente();
                    }
                }
            }

        for (Vertice v : vertices){

                    while(v.getAdjacente() != null){
                        if (v.getAdjacente().getId() == id_vertice){
                       // System.out.println(v.getId());
                         adjacentes.add(v.getId());
                        }
                        v=v.getAdjacente();
                    }
            }
        return adjacentes;
    }

    //mudei o retorno da funçaõ porque preciso de um vetor ordenado por grau para o algoritmo de coloraçao!!
    public ArrayList<Vertice> OrdenaGrau(){

        for (int i=vertices.size();i>1;i--){
            for (int j=1;j<i;j++){
                if (CalculaGrau(vertices.get(j-1).getId())>CalculaGrau(vertices.get(j).getId())){

                    Collections.swap(vertices, j, j-1);
                }
            }

        }

        return vertices;
//        for (Vertice v : vertices){
//            System.out.println(v.getId());
//
//        }
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


    public void OrdenaPesoArestas(){
        for (int i=arestas.size();i>1;i--){
            for (int j=1;j<i;j++){
                if (arestas.get(j-1).getPeso()>arestas.get(j).getPeso()){

                    Collections.swap(arestas, j, j-1);
                }
            }

        }

//        for (Aresta a : arestas){
//            System.out.println(a.getV1());
//            System.out.println(a.getPeso());
//            System.out.println(a.getV2());
//
//        }

    }


 /****************************************************************************************************************/
        /***  A estratégia é buscar o vértice mais profundo no grafo, sempre que possível.
        //Algoritmo:
        //Visite v e ache todos os seus vértices adjacentes
        //Visite w, o primeiro vértice adjacente de v, que ainda não foi visitado
        //Marque w como visitado e inicie uma busca em profundidade em w
        //Se todos os vértices de w já foram visitados, retorne ao último elemento de v que ainda tem vértices adjacentes não visitados
        //Repita o processo até que todos os vértices de v tenham sido visitados ***/
        /************************************************************************************************************/



    public void CriaArrayVisitados(Vertice v1){
        ArrayList<Integer> visitados = new ArrayList<Integer>();
        visitados.add(v1.getId());
        BuscaProfundidade(v1,visitados);
    }
    /****************************************************************************************************************/
       //Realiza a busca de profundidade em pré-ordem
        /************************************************************************************************************/

    public void BuscaProfundidade(Vertice v,ArrayList<Integer> visitados){
       System.out.println(v.getId());
       ArrayList<Vertice> adjacentes = new ArrayList<Vertice>();
       adjacentes= Adjacentes(v.getId());

        boolean existe=false;
        int j=0;


                for(int i=0;i<visitados.size();i++){

                    if(adjacentes.get(j).getId()== visitados.get(i)){

                    existe=true;

                    }

                }


            if(!existe){
             int cont=0;
             int k=0;
             adjacentes= Adjacentes(v.getId());
             visitados.add(v.getId());

                        for(Integer visitado:visitados){
                            if( adjacentes.get(k).getId()== visitado){
                            cont=cont+1;
                            existe=true;
                            }
                        }


                if(!existe && cont<adjacentes.size() && adjacentes.get(k)!=null){
                     BuscaProfundidade(adjacentes.get(k),visitados);
                }
                 k++;
            }

        j++;

    }
    public void ImprimeMatrizes(){
        System.out.println("Matriz de Incidência:");
        ImprimeMatrizdeIncidencia();
        System.out.println("");
        System.out.println("Matriz de Adjacência:");
        ImprimeMatrizdeAdjacencia();
    }

    public void ImprimeMatrizdeIncidencia(){
        int [][] matriz = new int [getVertices().size()+1][getArestas().size()+1];
        if(orientado){
             for(int i=0;i<getVertices().size();i++){
                 matriz[i+1][0]=getVertices().get(i).getId();
             }
             for(int i=0;i<getArestas().size();i++){
                 matriz[0][i+1]=getArestas().get(i).getPeso();

             }
            for (int i = 0; i < getVertices().size(); i++){
               for (int j = 0; j < getArestas().size(); j++){
                   if(matriz[i+1][0]==getArestas().get(j).getV1().getId()){
                     matriz[i+1][j+1]=1;
                 }
                 if(matriz[i+1][0]==getArestas().get(j).getV2().getId()){
                     matriz[i+1][j+1]=-1;
                 }
               }
               
           }
           for (int i = 0; i < getVertices().size()+1; i++){
               for (int j = 0; j < getArestas().size()+1; j++){
                   System.out.print(matriz[i][j]+" ");
               }
                System.out.println("");
           }

        }
        if(!orientado){
            for(int i=0;i<getVertices().size();i++){
                 matriz[i+1][0]=getVertices().get(i).getId();
             }
             for(int i=0;i<getArestas().size();i++){
                 matriz[0][i+1]=getArestas().get(i).getPeso();

             }
            for (int i = 0; i < getVertices().size(); i++){
               for (int j = 0; j < getArestas().size(); j++){
                   if(matriz[i+1][0]==getArestas().get(j).getV1().getId() || matriz[i+1][0]==getArestas().get(j).getV2().getId()){
                     matriz[i+1][j+1]=1;
                 }
                 
               }

           }
           for (int i = 0; i < getVertices().size()+1; i++){
               for (int j = 0; j < getArestas().size()+1; j++){
                   System.out.print(matriz[i][j]+" ");
               }
                System.out.println("");
           }
        }
    }

    public void ImprimeMatrizdeAdjacencia(){
         int [][] matriz = new int [getVertices().size()+1][getVertices().size()+1];
         ArrayList<Integer> adjacentes = new ArrayList<Integer>();
          if(orientado){
             for(int i=0;i<getVertices().size();i++){
                 matriz[i+1][0]=getVertices().get(i).getId();
                 matriz[0][i+1]=getVertices().get(i).getId();
             }
         for (int i = 0; i < getVertices().size(); i++){
             adjacentes = ImprimeSucessores(getVertices().get(i).getId());
               for (int j = 0; j < getVertices().size(); j++){
                 for(Integer adjacente:adjacentes){
                     if(getVertices().get(j).getId()==adjacente){
                         matriz[i+1][j+1]=1;
                     }
                 }
               }
           }
         for (int i = 0; i < getVertices().size()+1; i++){
               for (int j = 0; j < getVertices().size()+1; j++){
                   System.out.print(matriz[i][j]+" ");
               }
                System.out.println("");
           }
        }
         if(!orientado){
             for(int i=0;i<getVertices().size();i++){
                 matriz[i+1][0]=getVertices().get(i).getId();
                 matriz[0][i+1]=getVertices().get(i).getId();
             }
         for (int i = 0; i < getVertices().size(); i++){
             adjacentes = ImprimeAdjacentes(getVertices().get(i).getId());
               for (int j = 0; j < getVertices().size(); j++){
                 for(Integer adjacente:adjacentes){
                     if(getVertices().get(j).getId()==adjacente){
                         matriz[i+1][j+1]=1;
                     }
                 }
               }
           }
         for (int i = 0; i < getVertices().size()+1; i++){
               for (int j = 0; j < getVertices().size()+1; j++){
                   System.out.print(matriz[i][j]+" ");
               }
                System.out.println("");
           }
         }
    }

    public boolean existeCaminho (Vertice v1, Vertice v2)
    {
        ArrayList<Integer> visitados = new ArrayList<Integer>();
        visitados.add(v1.getId());

        return existeCaminho(v1,v2,visitados);
    }

    private boolean existeCaminho (Vertice v1, Vertice v2, ArrayList<Integer> visitados)
    {
       boolean existe=false;

        boolean jafoi = false;
    
        for(Vertice vt: vertices)
        {           
            //Para achar v1
            if(vt.getId()==v1.getId())//ACHEI V1
            {
              //procurar se v2 é adjacente de vt que é igual a v1
                while(vt.getAdjacente()!= null && existe!=true)
                {
                    //v2 é adjacente de v1
                    if(vt.getAdjacente().getId()==v2.getId())
                    {
                      existe=true;
                      break;
                    }
                    //se nao é igual à v2
                    else{
                        for(int i=0;i<visitados.size();i++)//verifica se já foi visitado
                        {
                            
                            if(visitados.get(i)== vt.getAdjacente().getId()){//já foi visitado
                               jafoi=true;
                                break;
                            }
                        }
                        if(jafoi==false)// ainda não foi visitado
                        {
                            visitados.add(vt.getAdjacente().getId());// adiciona o vertice adjacente na lista de visitados
                             //faz a recursão com ele
                            existe=existeCaminho (vt.getAdjacente(),v2,visitados);
                            
                        }
                        
                    }
                     vt = vt.getAdjacente();
                }
            }
      }
      return existe;
    }

    //imprime todos os vertices que podem ser atingidos por vert
    public void imprimeFTD(Vertice vert)
    {
        boolean atingido=false;

        System.out.print("ftd("+ vert.getId()+"):");
        //achar vert na estrutura
        for(Vertice vt: vertices)
        {
            if(vt.getId()==vert.getId())//achei
            {
                //procura quem vert atinge
                for(Vertice vrt: vertices)
                {
                   
                    atingido=existeCaminho(vt, vrt);
                   
                    if(atingido==true)//vt é parte do fecho transitivo direto de vert
                    {                        
                        System.out.print(vrt.getId() + ", ");
                    }
                }
                break;  
            }
        }


    }

    public ArrayList<Integer> geraFTD(Vertice vert)
    {
        ArrayList<Integer> ftd = new ArrayList<Integer>();
   
        boolean atingido=false;

       // System.out.print("ftd("+ vert.getId()+"):");
        //achar vert na estrutura
        for(Vertice vt: vertices)
        {
            if(vt.getId()==vert.getId())//achei
            {
                //procura quem vert atinge
                for(Vertice vrt: vertices)
                {
                    atingido=existeCaminho(vt, vrt);

                    if(atingido==true)//vt é parte do fecho transitivo direto de vert
                    {
                        ftd.add(vrt.getId());// System.out.print(vrt.getId() + ", ");
                    }
                }
                break;
            }
        }
        return ftd;
    }

    public ArrayList<Integer> geraFTI(Vertice vert)
    {
        boolean atinge;

       ArrayList<Integer> fti = new ArrayList<Integer>();
       

       // System.out.print("fti("+vert.getId()+")");
        //para cada vértice, verifica se ele alcança vert
        for(Vertice vt: vertices)
        {

            atinge= existeCaminho(vt, vert);
            if(atinge==true)// se tem caminho entre vt e vert, imprime vt
            {
                fti.add(vt.getId());//System.out.print(vt.getId()+", ");
            }
        }

        return fti;
    }

    //Imprime todos os vértices que alcança 'vert'
    public void imprimeFTI(Vertice vert)
    {
        boolean atinge;

        System.out.print("fti("+vert.getId()+")");
        //para cada vértice, verifica se ele alcança vert
        for(Vertice vt: vertices)
        {

            atinge= existeCaminho(vt, vert);
            if(atinge==true)// se tem caminho entre vt e vert, imprime vt
                System.out.print(vt.getId()+", ");

        }
    }

        public ArrayList<Vertice> Adjacentes(int id_vertice) {
        //ImprimeAntecessores(id_vertice);
        //ImprimeSucessores(id_vertice);
        ArrayList<Vertice> adjacentes = new ArrayList<Vertice>();

        Vertice vAtual;

        for (Vertice v : vertices){
                if (v.getId() == id_vertice){
                    while(v.getAdjacente() != null){
                        adjacentes.add(v.getAdjacente());
                      //  System.out.print(v.getAdjacente().getId()+"\n");
                        v=v.getAdjacente();
                    }
                }
            }

        for (Vertice v : vertices){

            vAtual=v;
                    while(v.getAdjacente() != null){
                        if (v.getAdjacente().getId() == id_vertice){
                        adjacentes.add(vAtual);
                     //   System.out.print(vAtual.getId()+"\n");
                        }
                        v=v.getAdjacente();
                    }
            }
        return adjacentes; //retorno de um vetor com o id dos adjacentes

    }

        //retorna vetor com os adjacentes de um determinado vertice
    public ArrayList<Integer> vetorAdjacentes(int id_vertice) {

        ArrayList<Integer> adjacentes = new ArrayList<Integer>();

        Vertice vAtual;

        for (Vertice v : vertices){
                if (v.getId() == id_vertice){
                    while(v.getAdjacente() != null){
                        adjacentes.add(v.getAdjacente().getId());
                      //  System.out.print(v.getAdjacente().getId()+"\n");
                        v=v.getAdjacente();
                    }
                }
            }

        for (Vertice v : vertices){

            vAtual=v;
                    while(v.getAdjacente() != null){
                        if (v.getAdjacente().getId() == id_vertice){
                        adjacentes.add(vAtual.getId());
                     //   System.out.print(vAtual.getId()+"\n");
                        }
                        v=v.getAdjacente();
                    }
            }
        return adjacentes; //retorno de um vetor com o id dos adjacentes

    }

    public ArrayList<Vertice> OrdenaGrauDecrescente(){

        for (int i=vertices.size();i>1;i--){
            for (int j=1;j<i;j++){
                if (CalculaGrau(vertices.get(j-1).getId())<CalculaGrau(vertices.get(j).getId())){

                    Collections.swap(vertices, j, j-1);
                }
            }
        }

       return vertices;
   }

    public void Coloracao(Grafo gr)
    {
        gr.vertices=OrdenaGrauDecrescente();
        
        int cores[][] = new int[2][gr.vertices.size()];

        //variavel que indica se podemos ou nao colorir o vertice com certa cor
        boolean colorir=true;

        //matriz com vértices e suas respectivas cores
        for(int lin = 0; lin <=0; lin++){
            for(int col = 0; col < gr.vertices.size(); col++)
                cores[lin][col]=gr.vertices.get(col).getId();
        }
        
        cores[1][0]=1; // coloca a cor do primeiro elemento como 1
        
        int cor=1;

        ArrayList<Integer> vAdjacentes= new ArrayList<Integer>();//lista os adjacentes

        //para cada vertice da matriz, procurar a cor para colori-lo
        for(int vrt=1; vrt<cores[0].length;vrt++)
        {
            //pega os adjacentes do vertice em questao
          vAdjacentes = vetorAdjacentes(cores[0][vrt]);
          
           for(int cAtual=1;cAtual<=cor;cAtual++){

               colorir=true;

               for(int adj: vAdjacentes)
                {

                   //procurar ele na matriz e ver sua cor
                    for(int i=0;i<cores[0].length;i++)
                    {
                        //se achou na matriz
                        if(cores[0][i]==adj)
                        {
                          //ver a cor dele
                            if(cores[1][i]==cAtual)
                                colorir=false;break;
                        }
                   }
               }
                    if(colorir==true)
                    {
                       cores[1][vrt]=cAtual; break;
                    }
                }
            
            if(colorir==false)
            {
                cor++;
                cores[1][vrt]=cor;
            }
    }

        for(int col = 0; col < cores[0].length; col++){
                   System.out.print("Vertice: "+cores[0][col]+"-"+"Cor: "+cores[1][col]+"\n");
                  
                }
 }

    public void geraGrafoReduzido(Grafo g)
    {
       int componentes[][] = new int[2][g.getVertices().size()];//matriz que contem o vertice e seu componente

       ArrayList<Integer> FTD = new ArrayList<Integer>();

       ArrayList<Integer> FTI = new ArrayList<Integer>();

       int q =0; //anda na matriz de componentes
       int c= 1; // número da componente em que o vértice se encontra
       boolean reduzir= true;
       

        for(Vertice vrt: vertices)
        {
            reduzir=true;

            //verificar se o vertice já tem componente
            for(int l=0; l<componentes[0].length;l++)
            {
                if(vrt.getId()==componentes[0][l]){//o vertice já tem componente
                  reduzir=false;
                    break;//nao precisa mais procurar
                }
            }//fim do for de verificar se tá na componente

            if(reduzir==true)
            {
                 FTD= g.geraFTD(vrt);
                 FTI= g.geraFTI(vrt);

                 //inclui o vertice em questao na componente
                 componentes[0][q]=vrt.getId();
                 componentes[1][q]=c;

            //verifica a interseção entre os conjuntos
                for (int i=0; i<FTD.size();i++)
                {
                    for (int j=0;j<FTI.size();j++)
                 {
                    if (FTD.get(i)==FTI.get(j))
                    {
                        componentes[0][q]=FTD.get(i);//vetor com a interseção entre os conjuntos
                        componentes[1][q]=c;
                        q++;
                    }
                 }//ACHEI A INTERSEÇÃO

                }
                c++;

            }
          }

          for(int col=0;col<componentes[0].length;col++){
              System.out.print("Vertice: "+componentes[0][col]+",Componente"+componentes[1][col]+"\n");
          }
  }

}




