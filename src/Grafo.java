import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//inicio de la clase Grafos
class Grafo {

    //atributos;
    private static int matriz_distancia[][];
    String[] ciudades = {"Armenia","Calarcá","Montenegro","Quimbaya","La Tebaida","Circasia","Génova","Pijao","Córdoba",
            "Salento","Buenavista","Filandia"};

    NodoVertice cabv, movilv;
    NodoArista caba, movila;

    //metodos
    Grafo(){}

    public void inicializar_grafo(){
        cabv=null; // inicializando lista de vertices
        caba=null; // inicializando lista de aristas
    }
    public static void inicializar_matriz_de_adyacencia_distancias(){
        matriz_distancia = new int[][]{
                {0, 7, 13, 23, 18, 12, 55, 34, 27, 25, 31, 29},
                {7, 0, 18, 28, 26, 13, 55, 34, 28, 26, 31, 29},
                {13, 18, 0, 11, 19, 16, 62, 42, 35, 33, 38, 28},
                {23, 28, 11, 0, 29, 26, 73, 52, 45, 37, 49, 17},
                {18, 26, 19, 29, 0, 29, 55, 40, 33, 43, 36, 46},
                {12, 13, 16, 26, 29, 0, 67, 46, 39, 18, 42, 21},
                {55, 55, 62, 73, 55, 67, 0, 46, 42, 80, 34, 84},
                {34, 34, 42, 52, 40, 46, 46, 0, 12, 60, 12, 63},
                {27, 28, 35, 45, 33, 39, 42, 12, 0, 53, 18, 56},
                {25, 26, 33, 37, 43, 18, 80, 60, 53, 0, 56, 20},
                {31, 31, 38, 49, 36, 42, 34, 12, 18, 56, 0, 59},
                {29, 29, 28, 17, 46, 21, 84, 63, 56, 20, 59, 0}
        };

    }
    public void ingresar_vertice() {
        for (String ciudad : ciudades) {
            NodoVertice nuevo = new NodoVertice();
            nuevo.nombrev = new String(ciudad);
            nuevo.siga = null;
            nuevo.sigv = cabv;
            cabv = nuevo;
        }
    }

    public void ingresar_arista_vertice() {
        for (int i = 0; i < matriz_distancia.length; i++) {
            caba = null; // Reinicializar caba para cada vértice
            movilv = cabv;

            for (int j = 0; j < matriz_distancia[i].length; j++) {
                int distancia = matriz_distancia[i][j];

                if (distancia != 0) {
                    NodoArista nuevo = new NodoArista();
                    nuevo.distancia = distancia;

                    while (movilv != null) {
                        if (movilv.nombrev.equals(ciudades[j])) {
                            nuevo.sigv = movilv;
                            break;
                        }
                        movilv = movilv.sigv;
                    }

                    nuevo.siga = caba;
                    caba = nuevo;
                }
            }

            movilv = cabv;
            while (movilv != null) {
                if (movilv.nombrev.equals(ciudades[i])) {
                    movilv.siga = caba;
                    break;
                }
                movilv = movilv.sigv;
            }
        }
    }

    public void imprimir_vertices() {
        movilv = cabv;
        while (movilv != null) {
            System.out.println(movilv.nombrev);
            movilv = movilv.sigv;
        }
    }

    public void imprimirAristasVertice() throws IOException {
        int ban = 0;
        int iterator=ciudades.length-1;
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        String vertice = "";
        System.out.println("Digite el nombre del vertice");
        vertice = teclado.readLine();
        movilv = cabv;
        while ((ban == 0) && (movilv != null)) {
            if (movilv.nombrev.equals(vertice)) {
                ban = 1;
                movila = movilv.siga;
                while (movila != null) {
                    if (ciudades[iterator].equals(movilv.nombrev)){
                        iterator--;
                    }
                    System.out.println("A " + ciudades[iterator] + " distancia = " + movila.distancia);
                    movila = movila.siga;
                    iterator--;
                }
            }
            movilv = movilv.sigv;
        }
    }

    public void consultarDistanciaOrigenDestino() throws IOException {
        int ban = 0;
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
        String verticeOrigen = "";
        String verticeDestino = "";
        System.out.println("Digite el nombre del vertice de Origen");
        verticeOrigen = teclado.readLine();
        System.out.println("Digite el nombre del vertice de Destino");
        verticeDestino = teclado.readLine();
        movilv = cabv;
        int iterator=ciudades.length-1;
        while ((ban == 0) && (movilv != null)) {
            if (movilv.nombrev.equals(verticeOrigen)) {
                ban = 1;
                movila = movilv.siga;
                while (movila != null) {
                    if (ciudades[iterator].equals(movilv.nombrev)){
                        iterator--;
                    }
                    if (ciudades[iterator].equals(verticeDestino)) {
                        System.out.println("De " + verticeOrigen + " A " + verticeDestino + " distancia = " + movila.distancia);
                        break;
                    }
                    iterator--;
                    movila = movila.siga;
                }
            }
            movilv = movilv.sigv;
        }
    }


    public void destruir_grafo() {
        NodoVertice borrav;
        NodoArista borraa;
        borrav = cabv;
        movilv = cabv;
        while (movilv != null) {
            movilv = movilv.sigv;
            borraa = borrav.siga;
            movila = borrav.siga;
            while (movila != null) {
                movila = movila.siga;
                borraa = null;
                borraa = movila;
            }
            borrav = null;
            borrav = movilv;
        }
    }
}// //fin de la clase Grafos
