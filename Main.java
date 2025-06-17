import java.util.ArrayList;
import utils.Maquina;

public class Main {
    public static void main(String args[]){
    Soluciones soluciones = new Soluciones("datasets\\Maquinas.csv");
      
   if(soluciones.getMaquinas().isEmpty()){
        System.out.println("No se han encontrado maquinas");
   }else{
        System.out.println("Se busca generar "+ soluciones.getPiezasObjetivo()+ " piezas, con las siguentes maquinas disponibles:");
        for (Maquina m : soluciones.getMaquinas()) {
            System.out.println(m); 
        }  
   }

    System.out.println("-----------------------------------------------------------------");

    ArrayList<Maquina> solucion=  soluciones.solucionBackTracking();
       
       
        System.out.println("Backtracking");
        if(solucion.isEmpty() || soluciones.getPiezasProducidasFinal()!= soluciones.getPiezasObjetivo()){
            System.out.println("No se ha encontrado una solucion.");
        }else{
            System.out.println("Solucion obtenida: secuencia de maquinas."); 
            for (Maquina m : solucion) {
                System.out.println(m); 
            }
            
            System.out.println("- Piezas producidas: " + soluciones.getPiezasProducidasFinal());
            System.out.println("- Puestas en funcionamiento: " + soluciones.getPuestasEnFuncionamiento());
            System.out.println("- Costo de la solucion (estados generados): " + soluciones.getEstadosGeneradosBack());
            System.out.println("- Calidad de la solucion: " + String.format("%.2f", soluciones.calidadBacktracking()) + " piezas por puestas en funcionamiento");
        }   

        System.out.println("-----------------------------------------------------------------");

    ArrayList<Maquina> solucionGreedy= soluciones.solucionGreedy(); 

        System.out.println("Greedy");
        if (solucionGreedy.isEmpty()||soluciones.getPiezasProducidasFinal()!= soluciones.getPiezasProducidasGreedy()) {
            System.out.println("No se ha encontrado una solucion.");
        } else {
            System.out.println("Solucion obtenida: secuencia de maquinas.");
            for (Maquina maquina : solucionGreedy) {
                System.out.println(maquina); 
            }
            System.out.println("- Piezas producidas: " + soluciones.getPiezasObjetivo());
            System.out.println("- Puestas en funcionamiento: " + solucionGreedy.size());
            System.out.println("- Costo de la solucion (estados generados): " + soluciones.getEstadosGeneradosGreedy());
            System.out.println("- Calidad de la solucion: " + String.format("%.2f", soluciones.calidadGreedy(solucionGreedy))+ " piezas por puestas en funcionamiento");

        }


        

    }
}
