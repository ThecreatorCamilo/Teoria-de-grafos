/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package primrecursivo;
/**
 *
 * @author camilovelandia
 */
import java.util.*;

public class PrimRecursivo {
    // clase PrimRecursivo: configura estructuras para aplicar el algoritmo de Prim y encontrar el MST.

    private int[][] grafo; // matriz de adyacencia que representa el grafo.
    private boolean[] enMST; // indica si un nodo ya esta incluido en el MST.
    private int[] peso_minimo; // peso minimo necesario para conectar cada nodo al MST.
    private int[] padre; // nodo padre de cada vertice en el MST.

    // constructor: inicializa las estructuras necesarias.
    public PrimRecursivo(int[][] grafo) {
        this.grafo = grafo; // asigna la matriz de adyacencia.
        int vertices = grafo.length; // numero de vertices del grafo.

        enMST = new boolean[vertices]; // marca todos los nodos como no incluidos en el MST.
        peso_minimo = new int[vertices]; // inicializa pesos minimos como infinito.
        Arrays.fill(peso_minimo, Integer.MAX_VALUE);

        padre = new int[vertices]; // inicializa todos los nodos sin padre.
        Arrays.fill(padre, -1);
    }

    public void prim(int u) {
        enMST[u] = true;
        // construccion del MST, eligiendo aristas con el menor peso que conecten un nodo en el MST con un nodo fuera de el.
        for (int v = 0; v < grafo.length; v++) {
            if (grafo[u][v] != 0 && !enMST[v] && grafo[u][v] < peso_minimo[v]) {
                padre[v] = u;
                peso_minimo[v] = grafo[u][v];
            }
        }

       // encuentra el siguiente nodo no incluido en el MST con el peso minimo.
        int siguienteNodo = -1;
        int minKey = Integer.MAX_VALUE;

        // recorre todos los nodos del grafo.
        for (int v = 0; v < grafo.length; v++) {
            // verifica si el nodo no esta en el MST y si tiene un peso menor que minKey.
            if (!enMST[v] && peso_minimo[v] < minKey) {
                minKey = peso_minimo[v]; // actualiza el peso minimo encontrado.
                siguienteNodo = v; // guarda el nodo con el peso minimo.
            }
        }

        // si se encontro un nodo valido, llama recursivamente al metodo prim.
        if (siguienteNodo != -1) {
            prim(siguienteNodo);
        }
    }
    //impresion del mst
    public void mostrarMST() {
        for (int i = 1; i < grafo.length; i++) {
            System.out.println("Padre: " + padre[i] + " - Nodo conectado: "  + i + " Peso:  " + grafo[i][padre[i]]);
        }
    }
    //llamado de la clase principal para asi imprimir las aristas del mst
    public static void main(String[] args) {
        int[][] grafo = {
            {0, 2, 0, 6, 0},
            {2, 0, 3, 8, 5},
            {0, 3, 0, 0, 7},
            {6, 8, 0, 0, 9},
            {0, 5, 7, 9, 0}
        };

        PrimRecursivo prim = new PrimRecursivo(grafo);
        prim.prim(0);
        System.out.println("Aristas en el minimum spanning tree (arbol recubridor minimo):");
        prim.mostrarMST();
    }
}