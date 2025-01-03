/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dijkstrarecursivo.java;
/**
 *
 * @author camilovelandia
 */
import java.util.*;

public class DijkstraRecursivo {

    private int[][] grafo; // matriz de adyacencia que representa el grafo.
    private int[] dist; // arreglo que almacena las distancias minimas desde el nodo fuente.
    private boolean[] visitado; // arreglo que indica si un nodo ya fue visitado.

    // constructor: inicializa las estructuras necesarias.
    public DijkstraRecursivo(int[][] grafo) {
        this.grafo = grafo; // asigna la matriz de adyacencia.
        int V = grafo.length; // numero de vertices del grafo.
        dist = new int[V]; // inicializa el arreglo de distancias.
        Arrays.fill(dist, Integer.MAX_VALUE); // establece todas las distancias como infinito.
        visitado = new boolean[V]; // inicializa el arreglo de visitados como false.
    }

    // metodo dijkstra: aplica el algoritmo de Dijkstra desde el nodo fuente `u`.
    public void dijkstra(int u) {
        visitado[u] = true; // marca el nodo actual como visitado.

        // busca caminos mas cortos desde el nodo actual a sus vecinos.
        for (int v = 0; v < grafo.length; v++) {
            // actualiza la distancia si el nodo no fue visitado, hay una conexion, y se encuentra un camino mas corto.
            if (!visitado[v] && grafo[u][v] != 0 && dist[u] + grafo[u][v] < dist[v]) {
                dist[v] = dist[u] + grafo[u][v];
            }
        }

        // selecciona el siguiente nodo con la distancia minima.
        int siguienteNodo = -1;
        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < grafo.length; i++) {
            if (!visitado[i] && dist[i] < minDist) {
                minDist = dist[i]; // actualiza la distancia minima encontrada.
                siguienteNodo = i; // guarda el nodo con la distancia minima.
            }
        }

        // si se encontro un nodo valido, llama recursivamente a dijkstra.
        if (siguienteNodo != -1) {
            dijkstra(siguienteNodo);
        }
    }

    // metodo mostrarDistancias: imprime las distancias desde el nodo fuente.
    public void mostrarDistancias() {
        System.out.println("Distancias desde el nodo fuente:");
        for (int i = 0; i < dist.length; i++) {
            System.out.println("Nodo " + i + " : " + dist[i]);
        }
    }

    // metodo main: crea un grafo de ejemplo, aplica Dijkstra desde el nodo fuente y muestra los resultados.
    public static void main(String[] args) {
        int[][] grafo = { // matriz de adyacencia que define el grafo.
            {0, 10, 20, 0, 0},
            {10, 0, 5, 16, 2},
            {20, 5, 0, 20, 10},
            {0, 16, 20, 0, 8},
            {0, 2, 10, 8, 0}
        };

        // impresion de distancias desde el nodo fuente.
        DijkstraRecursivo dijkstra = new DijkstraRecursivo(grafo); // crea una instancia con el grafo.
        dijkstra.dist[0] = 0; // establece la distancia al nodo fuente como 0.
        dijkstra.dijkstra(0); // aplica el algoritmo desde el nodo 0.
        dijkstra.mostrarDistancias(); // muestra las distancias calculadas.
    }
}