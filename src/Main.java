import java.lang.*;
import java.io.*;

//inicio de la clase TestGrafos
class Main{

    public static void main(String arg[])throws IOException{
        int opc =0;
        BufferedReader Teclado;
        Teclado=new BufferedReader(new InputStreamReader(System.in));
        Grafo F= new Grafo();
        F.inicializar_grafo();
        F.inicializar_matriz_de_adyacencia_distancias();
        F.ingresar_vertice();
        F.ingresar_arista_vertice();
        do{ //menu principal
            System.out.println("1. Imprimir Vertices");
            System.out.println("2. Imprimir Aristas de Vertice");
            System.out.println("3. Consultar Distancia entre dos Vertices");
            System.out.println("4. Salir");
            System.out.println("digite la opcion");
            opc=Integer.parseInt(Teclado.readLine());
            switch(opc){
                case 1: F.imprimir_vertices();
                    break;
                case 2: F.imprimirAristasVertice();
                    break;
                case 3: F.consultarDistanciaOrigenDestino();
                    break;
            }
        }while(opc!=4);
        F.destruir_grafo();
    }
}