

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package kruskalrecursivo;
/**
 *
 * @author camilovelandia
 */
import java.util.*;

public class KruskalRecursivo {
    public int[] padre; // arreglo que representa los padres en el conjunto disjunto.
    public int[] rango; // arreglo que almacena el rango de cada nodo en el conjunto disjunto.
    public List<Arista> aristas; // lista de aristas del grafo.

    // constructor: inicializa los conjuntos y estructuras necesarias.
    public KruskalRecursivo(int vertices) {
        padre = new int[vertices]; // inicializa el arreglo de padres.
        rango = new int[vertices]; // inicializa el arreglo de rangos.
        for (int i = 0; i < vertices; i++) {
            padre[i] = i; // cada nodo es su propio padre inicialmente.
            rango[i] = 0; // el rango de cada nodo comienza en 0.
        }
        aristas = new ArrayList<>(); // inicializa la lista de aristas.
    }

    // clase interna Arista: representa una arista con origen, destino y peso.
    public static class Arista {
        public int origen, destino, peso; // nodos de origen y destino, y el peso de la arista.

        // constructor de Arista: inicializa los valores de la arista.
        public Arista(int origen, int destino, int peso) {
            this.origen = origen;
            this.destino = destino;
            this.peso = peso;
        }
    }

    // metodo encontrar: encuentra la raiz del conjunto al que pertenece un nodo.
    public int encontrar(int nodo) {
        if (padre[nodo] != nodo) {
            padre[nodo] = encontrar(padre[nodo]); // compresion de camino.
        }
        return padre[nodo];
    }

    // metodo unir: une dos conjuntos disjuntos basandose en sus rangos.
    public void unir(int nodo1, int nodo2) {
        int raiz1 = encontrar(nodo1);
        int raiz2 = encontrar(nodo2);

        if (raiz1 != raiz2) { // si pertenecen a diferentes conjuntos.
            if (rango[raiz1] > rango[raiz2]) { 
                padre[raiz2] = raiz1; // el conjunto con mayor rango absorbe al otro.
            } else if (rango[raiz1] < rango[raiz2]) {
                padre[raiz1] = raiz2;
            } else {
                padre[raiz2] = raiz1; // si tienen el mismo rango, uno absorbe al otro y aumenta su rango.
                rango[raiz1]++;
            }
        }
    }

    // metodo kruskalRecursivo: construye el MST recursivamente.
    public void kruskalRecursivo(List<Arista> mst, int i) {
        // condicion de parada: se completa el MST o se procesan todas las aristas.
        if (mst.size() == padre.length - 1 || i == aristas.size()) {
            return;
        }

        Arista arista = aristas.get(i); // selecciona la siguiente arista.
        int raizOrigen = encontrar(arista.origen); // encuentra las raices de los nodos de la arista.
        int raizDestino = encontrar(arista.destino);

        // si la arista no forma un ciclo, se incluye al MST.
        if (raizOrigen != raizDestino) {
            mst.add(arista);
            unir(arista.origen, arista.destino); // une los conjuntos de los nodos.
        }

        // llamada recursiva para procesar la siguiente arista.
        kruskalRecursivo(mst, i + 1);
    }

    // metodo obtenerMST: ordena las aristas y genera el MST.
    public List<Arista> obtenerMST() {
        aristas.sort(Comparator.comparingInt(arista -> arista.peso)); // ordena las aristas por peso.
        List<Arista> mst = new ArrayList<>(); // lista para almacenar el MST.
        kruskalRecursivo(mst, 0); // inicia el proceso recursivo.
        return mst;
    }

    // metodo agregarArista: incluye una arista al grafo.
    public void agregarArista(int origen, int destino, int peso) {
        aristas.add(new Arista(origen, destino, peso));
    }

    // metodo main: prueba el algoritmo con un grafo de ejemplo.
    public static void main(String[] args) {
        int vertices = 5; // numero de vertices en el grafo.
        KruskalRecursivo kruskal = new KruskalRecursivo(vertices);

        // se incluyen las aristas al grafo.
        kruskal.agregarArista(0, 1, 2);
        kruskal.agregarArista(0, 3, 6);
        kruskal.agregarArista(1, 2, 3);
        kruskal.agregarArista(1, 3, 8);
        kruskal.agregarArista(1, 4, 5);
        kruskal.agregarArista(2, 4, 7);
        kruskal.agregarArista(3, 4, 9);

        // se obtiene y muestra el MST.
        List<Arista> mst = kruskal.obtenerMST();
        System.out.println("Aristas en el minimum spanning tree (arbol recubridor minimo):");
        for (Arista arista : mst) {
            System.out.println("Origen: " + arista.origen + " - Destino: " + arista.destino + " Peso: " + arista.peso);
        }
    }
}