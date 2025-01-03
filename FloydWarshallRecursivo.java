/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package floydwarshallrecursivo;
/**
 *
 * @author camilovelandia
 */

import java.util.Arrays;

public class FloydWarshallRecursivo {

    public static final int INF = 99999; // constante para representar una distancia infinita (inaccesible) entre nodos.
    private int[][] dist;  // matriz que almacena las distancias minimas entre todos los pares de vertices.
    private int[][] next;  // matriz que almacenara informacion para reconstruir el camino mas corto entre dos vertices.
    private int vertices;  // numero de vertices en el grafo.

    // constructor de la clase. Inicializa la matriz de distancias y la matriz auxiliar.
    public FloydWarshallRecursivo(int vertices) {
        this.vertices = vertices;  // guarda el numero de vertices del grafo.
        dist = new int[vertices][vertices];  // crea una matriz `vertices x vertices` para distancias minimas.
        next = new int[vertices][vertices];  // crea una matriz `vertices x vertices` para reconstruccion de caminos.

        // inicializa las matrices `dist` y `next`.
        for (int i = 0; i < vertices; i++) {
            Arrays.fill(dist[i], INF);  
            dist[i][i] = 0;  // la distancia de un nodo a si mismo es 0.

            for (int j = 0; j < vertices; j++) {
                if (i != j) {  // si los nodos son diferentes se inicializa la matriz `next` con -1, indicando que aun no hay caminos definidos.
                    next[i][j] = -1; 
                }   
            }
        }
    }

    // metodo que agrega una arista al grafo con peso
    public void agregarArista(int origen, int destino, int peso) {
        dist[origen][destino] = peso;
        next[origen][destino] = destino; 
    }

    // metodo para ejecutar el algoritmo Floyd-Warshall con distancias y recorridos
    public void floydWarshall() {
        for (int k = 0; k < vertices; k++) {
            for (int i = 0; i < vertices; i++) {
                for (int j = 0; j < vertices; j++) {
                    // si el camino atraves de k es mas corto, actualizamos distancias y el recorrido
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k]; // actualizamos el siguiente nodo en el camino mas corto
                    }
                }
            }
        }
    }

    // metodo que imprime el camino mas corto entre dos nodos
    public void reconstruirCamino(int origen, int destino) {
        // si no hay un camino entre los nodos, se informa al usuario.
        if (next[origen][destino] == -1) {
            System.out.println("No hay camino de " + origen + " a " + destino);
            return;
        }

        // imprime el camino mas corto desde el nodo origen hasta el destino.
        System.out.print("Camino mas corto de " + origen + " a " + destino + ": " + origen);
        while (origen != destino) {
            // avanza al siguiente nodo en el camino.
            origen = next[origen][destino];
            System.out.print(" -> " + origen);
        }
        System.out.println(); // nueva linea despues de imprimir el camino.
    }

    // metodo mostrarDistancias: imprime la matriz de distancias minimas entre todos los pares de nodos.
    public void mostrarDistancias() {
        System.out.println("Matriz de distancias minimas:");
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                // imprime "INF" si la distancia es infinita, de lo contrario, imprime la distancia calculada.
                if (dist[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println(); // nueva linea despues de imprimir una fila de la matriz.
        }
    }

    public static void main(String[] args) {
        int vertices = 4;
        FloydWarshallRecursivo grafo = new FloydWarshallRecursivo(vertices);

        grafo.agregarArista(0, 1, 2);
        grafo.agregarArista(0, 2, 7);
        grafo.agregarArista(1, 2, 5);
        grafo.agregarArista(1, 3, 3);
        grafo.agregarArista(2, 3, 3);

        grafo.floydWarshall();

        //impresion de matriz de distancias minimas
        grafo.mostrarDistancias();

        grafo.reconstruirCamino(0, 3);
        grafo.reconstruirCamino(2, 1);
    }
}