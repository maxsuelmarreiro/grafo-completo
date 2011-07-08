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
                        if (ver.getAdjacente().getId() == v2 && ver.getAdjacente().getPeso() == peso) {
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
        
    }

    public ArrayList<Integer> GeraSucessores(int id_vertice) {
        ArrayList<Integer> adjacentes = new ArrayList<Integer>();
        if (orientado) {
            for (Vertice v : vertices) {
                if (v.getId() == id_vertice) {
                    while (v.getAdjacente() != null) {
                        adjacentes.add(v.getAdjacente().getId());
                       v = v.getAdjacente();
                    }
                }
            }
        } else {
            System.out.println("Este grafo não é orientado!");
        }
        return adjacentes;
    }

    public ArrayList<Integer> GeraAntecessores(int id_vertice) {
        ArrayList<Integer> adjacentes = new ArrayList<Integer>();
        Vertice vAtual;
        if (orientado) {
            for (Vertice v : vertices){

                vAtual=v;
                        while(v.getAdjacente() != null){
                            if (v.getAdjacente().getId() == id_vertice){
                            adjacentes.add(vAtual.getId());
                            }
                            v=v.getAdjacente();
                        }
                }
        } else {
            System.out.println("Este grafo não é orientado!");
        }
        return adjacentes;
    }

    public void ImprimeAntecessores(int id_vertice) {
        ArrayList<Integer> adjacentes = new ArrayList<Integer>();
        Vertice vAtual;
        if (orientado) {
            for (Vertice v : vertices){

                vAtual=v;
                        while(v.getAdjacente() != null){
                            if (v.getAdjacente().getId() == id_vertice){
                            adjacentes.add(vAtual.getId());
                            System.out.print(vAtual.getId()+"\n");
                            }
                            v=v.getAdjacente();
                        }
                }
        } else {
            System.out.println("Este grafo não é orientado!");
        }

    }

    public ArrayList<Integer> ImprimeAdjacentes(int id_vertice) {

        ArrayList<Integer> adjacentes = new ArrayList<Integer>();

         Vertice vAtual;

         if(isOrientado()){
             adjacentes=GeraSucessores(id_vertice);
         }
               
         if(!isOrientado()){
            for (Vertice v : vertices){
                    if (v.getId() == id_vertice){
                        while(v.getAdjacente() != null){
                            adjacentes.add(v.getAdjacente().getId());
                            //System.out.print(v.getAdjacente().getId()+"\n");
                            v=v.getAdjacente();
                        }
                    }
                }
        }

            for (Vertice v : vertices){

                vAtual=v;
                        while(v.getAdjacente() != null){
                            if (v.getAdjacente().getId() == id_vertice){
                            adjacentes.add(vAtual.getId());
                            //System.out.print(vAtual.getId()+"\n");
                            }
                            v=v.getAdjacente();
                        }
                }
        
        return adjacentes;
    }

    public ArrayList<Vertice> ordenaGrausVertices() {

        for (int i = vertices.size(); i > 1; i--) {
            for (int j = 1; j < i; j++) {
                if (CalculaGrau(vertices.get(j - 1).getId()) > CalculaGrau(vertices.get(j).getId())) {

                    Collections.swap(vertices, j, j - 1);
                }
            }

        }

        return vertices;
       
    }


    //Calcula o grau de um vertice de um grafo não orientado
    public int CalculaGrau(int idV) {
        int grau = 0;

        if(!orientado){
        for (Vertice v : vertices) {
            //procura o vertice de id 'idV' no array de vertices
            if (v.getId() == idV) {
                //conta os vértices que saem dele
                while (v.getAdjacente() != null) {

                    grau++;
                    v = v.getAdjacente();
                }

            }

            //procura vertice de id 'idV' nos adjacentes de v para contar quantos entram nele
            while (v.getAdjacente() != null) {
                if (v.getAdjacente().getId() == idV)// vert é adjacente
                {
                    grau++;
                }
                v = v.getAdjacente();
            }
        }
        }

        //se for orientado nao tem como calcular o grau

        return grau;
    }

    //Para grafos orientados
    public int CalculaGrauSaida(int idV) {
        int grau = 0;
        for (Vertice v : vertices) {
            //procura o vertice de id 'idV' no array de vertices
            if (v.getId() == idV) {
                //conta os vértices que saem dele
                while (v.getAdjacente() != null) {

                    grau++;
                    v = v.getAdjacente();
                }

                break; // sai do for pq já achou todos
            }
        }

        return grau;
    }


    //Para grafos orientados
    public int CalculaGrauEntrada(int idV) {
        int grau = 0;
        for (Vertice v : vertices) {
            //procura vertice de id 'idV' nos adjacentes de v para contar quantos entram nele
            while (v.getAdjacente() != null) {
                if (v.getAdjacente().getId() == idV)// vert é adjacente
                {
                    grau++;
                }
                v = v.getAdjacente();
            }
        }

        return grau;
    }

    public void OrdenaPesoArestas() {
        for (int i = arestas.size(); i > 1; i--) {
            for (int j = 1; j < i; j++) {
                if (arestas.get(j - 1).getPeso() > arestas.get(j).getPeso()) {

                    Collections.swap(arestas, j, j - 1);
                }
            }

        }

        for (Aresta a : arestas){
            System.out.println("Aresta "+a.getV1().getId()+"-"+a.getV2().getId()+":"+a.getPeso());
           
        }
    }


    public ArrayList<Integer> BuscaProfundidade(Vertice v1){
        ArrayList<Integer> visitados = new ArrayList<Integer>();
        //visitados.add(v1.getId());
        BuscaProfundidade(v1, visitados);
        return visitados;
    }

    /****************************************************************************************************************/
    //Realiza a busca de profundidade em pré-ordem
    /************************************************************************************************************/
    
    public void BuscaProfundidade(Vertice v,ArrayList<Integer> visitados){
       //System.out.println(v.getId());
       ArrayList<Vertice> adjacentes = new ArrayList<Vertice>();
       adjacentes= Adjacentes(v.getId());

       //mrca o v como visitado
       visitados.add(v.getId());

       //para cada vertice w adjacente a v faça
       for (Vertice w : adjacentes) {

           //verificar se o w ja foi visitado
           boolean visitado = false;
           for (Integer id : visitados) {
               if(id == w.getId()){
                    visitado = true;
               }
           }

           //se w não foi visitado
           if(!visitado){
                BuscaProfundidade(w, visitados);
           }
     }

    }


    public void ImprimeMatrizes() {
        System.out.println("Matriz de Incidência:");
        ImprimeMatrizdeIncidencia();
        System.out.println("");
        System.out.println("Matriz de Adjacência:");
        ImprimeMatrizdeAdjacencia();
    }

    public void ImprimeMatrizdeIncidencia() {
        String[][] matriz = new String[getVertices().size() + 1][getArestas().size() + 1];
        if (orientado) {
            matriz[0][0] = "X";
            for (int i = 0; i < getVertices().size(); i++) {
                matriz[i + 1][0] = Integer.toString(getVertices().get(i).getId());
            }
            for (int i = 0; i < getArestas().size(); i++) {
                matriz[0][i + 1] = getArestas().get(i).getV1().getId() + "-" + getArestas().get(i).getV2().getId();

             }
            for (int i = 0; i < getVertices().size(); i++){
               for (int j = 0; j < getArestas().size(); j++){
                  if(matriz[i+1][0].equals(Integer.toString(getArestas().get(j).getV1().getId()))){
                     matriz[i+1][j+1]="  1";
                   }
                   else{
                       if(matriz[i+1][0].equals(Integer.toString(getArestas().get(j).getV2().getId()))){
                        matriz[i+1][j+1]=" -1";
                        }
                       else{
                        matriz[i+1][j+1]="  0";
                       }
                    }

                }

            }
            for (int i = 0; i < getVertices().size() + 1; i++) {
                for (int j = 0; j < getArestas().size() + 1; j++) {
                    System.out.print(" " + matriz[i][j] + "  ");
                }
                System.out.println("");
            }

        }
        if (!orientado) {
            matriz[0][0] = "X";
            for (int i = 0; i < getVertices().size(); i++) {
                matriz[i + 1][0] = Integer.toString(getVertices().get(i).getId());
            }
            for (int i = 0; i < getArestas().size(); i++) {
                matriz[0][i + 1] = getArestas().get(i).getV1().getId() + "-" + getArestas().get(i).getV2().getId();

             }
            for (int i = 0; i < getVertices().size(); i++){
               for (int j = 0; j < getArestas().size(); j++){
                   if(matriz[i+1][0].equals(Integer.toString(getArestas().get(j).getV1().getId())) || matriz[i+1][0].equals(Integer.toString(getArestas().get(j).getV2().getId()))){
                     matriz[i+1][j+1]="  1";
                 }
                    else{
                        matriz[i+1][j+1]="  0";
                    }

                }

            }
            for (int i = 0; i < getVertices().size() + 1; i++) {
                for (int j = 0; j < getArestas().size() + 1; j++) {
                    System.out.print("  " + matriz[i][j] + "   ");
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
             adjacentes = GeraSucessores(getVertices().get(i).getId());
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
                   System.out.print(matriz[i][j]+"  ");
               }
                System.out.println("");
            }
        }
        if (!orientado) {
            for (int i = 0; i < getVertices().size(); i++) {
                matriz[i + 1][0] = getVertices().get(i).getId();
                matriz[0][i + 1] = getVertices().get(i).getId();
            }
            for (int i = 0; i < getVertices().size(); i++) {
                adjacentes = ImprimeAdjacentes(getVertices().get(i).getId());
                for (int j = 0; j < getVertices().size(); j++) {
                    for (Integer adjacente : adjacentes) {
                        if (getVertices().get(j).getId() == adjacente) {
                            matriz[i + 1][j + 1] = 1;
                        }
                    }
                }
            }
            for (int i = 0; i < getVertices().size() + 1; i++) {
                for (int j = 0; j < getVertices().size() + 1; j++) {
                    System.out.print(matriz[i][j] + " ");
                }
                System.out.println("");
            }
        }
    }

    public boolean existeCaminho(Vertice v1, Vertice v2) {
        ArrayList<Integer> visitados = new ArrayList<Integer>();
        visitados.add(v1.getId());


        boolean retornoOrientado = existeCaminho(v1, v2, visitados);


        if (orientado) {
        return retornoOrientado;
        } else {


            ArrayList<Integer> visitados2 = new ArrayList<Integer>();
            visitados.add(v2.getId());

            boolean retornoNaoOrientado = existeCaminho(v2, v1, visitados);

            return retornoOrientado || retornoNaoOrientado;
        }
    }

    private boolean existeCaminho(Vertice v1, Vertice v2, ArrayList<Integer> visitados) {
        boolean existe = false;

        boolean jafoi = false;

        for (Vertice vt : vertices) {
            //Para achar v1
            if (vt.getId() == v1.getId())//ACHEI V1
            {
                //procurar se v2 é adjacente de vt que é igual a v1
                while (vt.getAdjacente() != null && existe != true) {
                    //v2 é adjacente de v1
                    if (vt.getAdjacente().getId() == v2.getId()) {
                        existe = true;
                        break;
                    } //se nao é igual à v2
                    else {
                        for (int i = 0; i < visitados.size(); i++)//verifica se já foi visitado
                        {

                            if (visitados.get(i) == vt.getAdjacente().getId()) {//já foi visitado
                                jafoi = true;
                                break;
                            }
                        }
                        if (jafoi == false)// ainda não foi visitado
                        {
                            visitados.add(vt.getAdjacente().getId());// adiciona o vertice adjacente na lista de visitados
                            //faz a recursão com ele
                            existe = existeCaminho(vt.getAdjacente(), v2, visitados);

                        }

                    }
                    vt = vt.getAdjacente();
                }
            }
        }
        return existe;
    }

    //imprime todos os vertices que podem ser atingidos por vert
    public void imprimeFTD(Vertice vert) {
        boolean atingido = false;

        if(orientado){
        System.out.print("ftd(" + vert.getId() + "):");
        //achar vert na estrutura
        for (Vertice vt : vertices) {
            if (vt.getId() == vert.getId())//achei
            {
                //procura quem vert atinge
                for (Vertice vrt : vertices) {

                    atingido = existeCaminho(vt, vrt);

                    if (atingido == true)//vt é parte do fecho transitivo direto de vert
                    {
                        System.out.print(vrt.getId() + ", ");
                    }
                }
                break;
            }
        }
        }
        else
            System.out.print("Grafo nao orientado");


    }


    public ArrayList<Integer> geraFTD(Vertice vert) {
        ArrayList<Integer> ftd = new ArrayList<Integer>();

        boolean atingido = false;

        //achar vert na estrutura
        for (Vertice vt : vertices) {
            if (vt.getId() == vert.getId())//achei
            {
                //procura quem vert atinge
                for (Vertice vrt : vertices) {
                    atingido = existeCaminho(vt, vrt);

                    if (atingido == true)//vt é parte do fecho transitivo direto de vert
                    {
                        ftd.add(vrt.getId());
                    }
                }
                break;
            }
        }
        return ftd;
    }

    public ArrayList<Integer> geraFTI(Vertice vert) {
        boolean atinge;

        ArrayList<Integer> fti = new ArrayList<Integer>();

      //para cada vértice, verifica se ele alcança vert
        for (Vertice vt : vertices) {

            atinge = existeCaminho(vt, vert);
            if (atinge == true)// se tem caminho entre vt e vert, imprime vt
            {
                fti.add(vt.getId());
            }
        }

        return fti;
    }

    //Imprime todos os vértices que alcança 'vert'
    public void imprimeFTI(Vertice vert) {
        boolean atinge;

        if(orientado){
        System.out.print("fti(" + vert.getId() + ")");
        //para cada vértice, verifica se ele alcança vert
        for (Vertice vt : vertices) {

            atinge = existeCaminho(vt, vert);
            if (atinge == true)// se tem caminho entre vt e vert, imprime vt
            {
                System.out.print(vt.getId() + ", ");
            }

        }
        }
            else
                System.out.print("Grafo não é orientado");
    }

    public ArrayList<Vertice> Adjacentes(int id_vertice) {
        ArrayList<Vertice> adjacentes = new ArrayList<Vertice>();

        Vertice vAtual;
        if(!isOrientado()){
                for (Vertice v : vertices){
                        if (v.getId() == id_vertice){
                            while(v.getAdjacente() != null){
                                adjacentes.add(v.getAdjacente());
                                v=v.getAdjacente();
                            }
                        }
                    }

                for (Vertice v : vertices){

                    vAtual=v;
                            while(v.getAdjacente() != null){
                                if (v.getAdjacente().getId() == id_vertice){
                                adjacentes.add(vAtual);
                               }
                                v=v.getAdjacente();
                            }
                    }
            }
            if(isOrientado()){
                for (Vertice v : vertices){
                        if (v.getId() == id_vertice){
                            while(v.getAdjacente() != null){
                                adjacentes.add(v.getAdjacente());
                                v=v.getAdjacente();
                            }
                        }
                    }
            }
        
        return adjacentes; //retorno de um vetor com o id dos adjacentes

    }

    //retorna vetor com os adjacentes de um determinado vertice
    public ArrayList<Integer> vetorAdjacentes(int id_vertice) {

        ArrayList<Integer> adjacentes = new ArrayList<Integer>();

        Vertice vAtual;

        for (Vertice v : vertices) {
            if (v.getId() == id_vertice) {
                while (v.getAdjacente() != null) {
                    adjacentes.add(v.getAdjacente().getId());
                    v = v.getAdjacente();
                }
            }
        }

        for (Vertice v : vertices) {

            vAtual = v;
            while (v.getAdjacente() != null) {
                if (v.getAdjacente().getId() == id_vertice) {
                    adjacentes.add(vAtual.getId());
                }
                v = v.getAdjacente();
            }
        }
        return adjacentes; //retorno de um vetor com o id dos adjacentes

    }

    public ArrayList<Vertice> OrdenaGrauDecrescente() {

        for (int i = vertices.size(); i > 1; i--) {
            for (int j = 1; j < i; j++) {
                if (CalculaGrau(vertices.get(j - 1).getId()) < CalculaGrau(vertices.get(j).getId())) {

                    Collections.swap(vertices, j, j - 1);
                }
            }
        }

        return vertices;
    }

    public int[][] Coloracao(Grafo gr) {
        gr.vertices = OrdenaGrauDecrescente();

       int cores[][] = new int[2][gr.vertices.size()];

        //variavel que indica se podemos ou nao colorir o vertice com certa cor
        boolean colorir = true;

        //matriz com vértices e suas respectivas cores
        for (int lin = 0; lin <= 0; lin++) {
            for (int col = 0; col < gr.vertices.size(); col++) {
                cores[lin][col] = gr.vertices.get(col).getId();
            }
        }

        cores[1][0] = 1; // coloca a cor do primeiro elemento como 1

        int cor = 1;

        ArrayList<Integer> vAdjacentes = new ArrayList<Integer>();//lista os adjacentes

        //para cada vertice da matriz, procurar a cor para colori-lo
        for (int vrt = 1; vrt < cores[0].length; vrt++) {
            //pega os adjacentes do vertice em questao
            vAdjacentes = vetorAdjacentes(cores[0][vrt]);

            for (int cAtual = 1; cAtual <= cor; cAtual++) {

                colorir = true;

                for (int adj : vAdjacentes) {

                    //procurar ele na matriz e ver sua cor
                    for (int i = 0; i < cores[0].length; i++) {
                        //se achou na matriz
                        if (cores[0][i] == adj) {
                            //ver a cor dele
                            if (cores[1][i] == cAtual) {
                                colorir = false;
                            }
                            break;
                        }
                    }
                }
                if (colorir == true) {
                    cores[1][vrt] = cAtual;
                    break;
                }
            }

            if (colorir == false) {
                cor++;
                cores[1][vrt] = cor;
            }
        }

//        for (int col = 0; col < cores[0].length; col++) {
//            System.out.print("Vertice: " + cores[0][col] + "-" + "Cor: " + cores[1][col] + "\n");
//
//        }

        return cores;
    }

    public void geraGrafoReduzido(Grafo g) {

        ArrayList<int[]> componentes= new ArrayList<int[]>();

        ArrayList<Integer> FTD = new ArrayList<Integer>();

        ArrayList<Integer> FTI = new ArrayList<Integer>();

        int q = 0; //anda na matriz de componentes
        int c = 1; // número da componente em que o vértice se encontra
        boolean reduzir = true;


        for (Vertice vrt : vertices) {
            reduzir = true;

            for (int[] componente : componentes) {
                if(vrt.getId()==componente[0])//ja tem componente
                {
                    reduzir = false;
                    break;//nao precisa mais procurar
                }
            }

            if (reduzir) {
                FTD = g.geraFTD(vrt);
                FTI = g.geraFTI(vrt);

                //inclui o vertice em questao na componente
                int[] componente = new int[2];
                componente[0]=vrt.getId();
                componente[1]=c;
                componentes.add(componente);

                //verifica a interseção entre os conjuntos
                for (int i = 0; i < FTD.size(); i++) {
                    for (int j = 0; j < FTI.size(); j++) {

                        //a condição FTD.get(i)!=vrt.getId() é para que o vertice nao seja inserido duas vezes
                        if (FTD.get(i) == FTI.get(j) && FTD.get(i)!=vrt.getId()) {

                            int[] novacomponente = new int[2];
                            novacomponente[0]=FTD.get(i);//vetor com a interseção entre os conjuntos
                            novacomponente[1]=c;
                            componentes.add(novacomponente);

                        }
                    }

                }
                c++;
            }
        }

         
        for(int valorComponente=1;valorComponente<c;valorComponente++)
        {
            System.out.print("\nComponente "+valorComponente+": ");
             for (int[] ar : componentes) {

                 if(ar[1]==valorComponente)
                    System.out.print(ar[0]+" ");
             
             }
            
        }


    }
}
