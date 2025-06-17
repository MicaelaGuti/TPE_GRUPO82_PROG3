
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;


import utils.CSVReader;
import utils.Maquina;

public class Soluciones {
    private LinkedList<Maquina> maquinas;
    private int piezasObjetivo; 
    private ArrayList<Maquina> resultadoFinal; 
    private int estadosGenerados = 0;
    private int puestasEnFuncionamiento = 0;
    private int piezasProducidasFinal = 0;
    private int estadosGeneradosGreedy=0; 
    private int piezasProducidasGreedy=0; 
    

    public Soluciones(String maquinasPath) {
        CSVReader reader = new CSVReader();
        this.maquinas = new LinkedList<>();
        this.resultadoFinal= new ArrayList<>();
        this.piezasObjetivo = reader.leerArchivo(maquinasPath, maquinas);
        

    }    

    public LinkedList<Maquina> getMaquinas() {
        return maquinas;
    }

    public int getPiezasObjetivo(){
        return piezasObjetivo; 
    }
    
    public int getEstadosGenerados() {
        return estadosGenerados;
    }

    public int getPuestasEnFuncionamiento() {
        return puestasEnFuncionamiento;
    }

    public int getPiezasProducidasFinal() {
        return piezasProducidasFinal;
    }
    
    
    public int getPiezasProducidasGreedy() {
        return piezasProducidasGreedy;
    }

    public int getEstadosGeneradosGreedy() {
        return estadosGeneradosGreedy;
    }


 /*
 * Estrategia de resolucion mediante Backtracking:
 * 
 * - El objetivo es encontrar una combinacion de maquinas que produzcan exactamente la cantidad total
 *   de piezas requeridas, minimizando la cantidad de puestas en funcionamiento (es decir, el numero de maquinas usadas).
 * 
 * - Se genera un arbol de exploracion donde cada nivel representa la eleccion de una nueva maquina a utilizar.
 *   En cada paso, se intenta agregar una maquina cuya cantidad de piezas no exceda el resto necesario.
 * 
 * - El estado actual esta representado por:
 *     * La cantidad acumulada de piezas producidas (`piezasProducidas`).
 *     * El camino parcial formado por las maquinas elegidas hasta ese punto (`caminoActual`).
 * 
 * - El estado final se alcanza cuando la suma de piezas producidas coincide exactamente con el total requerido.
 *   En ese caso, si el camino actual tiene menos maquinas que la mejor solucion encontrada hasta ahora, se actualiza la solucion optima.
 * 
 * - Podas aplicadas:
 *     * Si una maquina produce mas piezas que las que faltan, no se considera.
 *     * Si ya existe una solucion parcial mejor (menos maquinas), se evita guardar soluciones peores.
 *     
 * - La solucion resultante es la combinacion de maquinas que permite llegar a la cantidad de piezas exacta,
 *   con el menor numero posible de puestas en funcionamiento.
 */

    public ArrayList<Maquina> solucionBackTracking() {
        ArrayList<Maquina> m = new ArrayList<>();
        solucionBackTracking(0, m);
        puestasEnFuncionamiento= resultadoFinal.size();
        piezasProducidasFinal = 0;
        for (Maquina maquina : resultadoFinal) {
            piezasProducidasFinal += maquina.getCantPiezas();
        }
        return resultadoFinal;
    }

    private void solucionBackTracking(int piezasProducidas, ArrayList<Maquina> caminoActual) {
        estadosGenerados++;
        if (piezasProducidas == piezasObjetivo) {
            if (caminoActual.size() < resultadoFinal.size() || resultadoFinal.isEmpty()) {
                resultadoFinal.clear();
                resultadoFinal.addAll(caminoActual);
            }
        } else {
            for (Maquina m : maquinas) {
                int resto = piezasObjetivo - piezasProducidas;
                if (m.getCantPiezas() <= resto) {
                    caminoActual.add(m);
                    piezasProducidas += m.getCantPiezas();
                    solucionBackTracking(piezasProducidas, caminoActual);
                    piezasProducidas -= m.getCantPiezas();
                    caminoActual.remove(caminoActual.size() - 1);// Elimina el ultimo

                }
            }

        }

    }

    
    public double calidadBacktracking() {
        if (puestasEnFuncionamiento == 0) 
            return 0;
        return (double) piezasProducidasFinal / puestasEnFuncionamiento;
    }
/*
 * Estrategia de resolucion mediante Greedy:
 * 
 * - El enfoque Greedy consiste en seleccionar, en cada paso, la maquina mas "conveniente" segun un criterio de orden.
 *   En este caso, las maquinas se ordenan (previamente con `Collections.sort(maquinas)`) para favorecer aquellas
 *   que producen mas piezas, priorizando asi las maquinas de mayor capacidad.
 * 
 * - En cada iteracion del algoritmo:
 *     * Se selecciona la siguiente maquina en la lista ordenada.
 *     * Si la maquina puede agregarse sin superar el total de piezas requeridas, se incluye en la solucion.
 *     * Se continua hasta que la suma total de piezas alcance o supere el objetivo, o hasta agotar las opciones.
 * 
 * - El estado se compone de:
 *     * La lista de maquinas seleccionadas (`solucion`).
 *     * La cantidad acumulada de piezas (`piezas`).
 * 
 * - No hay retroceso ni busqueda exhaustiva: se toman decisiones locales optimas esperando un resultado global razonable.
 * 
 * - Esta estrategia es eficiente y rapida, pero no garantiza una solucion optima en todos los casos.
 *   Por ejemplo, puede dejar de lado combinaciones que usan mas maquinas chicas pero que en conjunto alcanzan mejor el objetivo.
 * 
 * - El metodo devuelve una lista con las maquinas seleccionadas. Si no se logra cumplir con la cantidad de piezas requeridas,
 *   se retorna una lista vacia, y se puede informar que no se ha encontrado una solucion.
 */


    public ArrayList<Maquina> solucionGreedy(){
        ArrayList<Maquina> solucion= new ArrayList<>(); 
        int piezas=0; 
        Collections.sort(maquinas); 
        int i=0; 
        estadosGeneradosGreedy = 0;
    
        while ((i<maquinas.size() && piezas<piezasObjetivo)) {
            estadosGeneradosGreedy++; // cuenta cada intento de evaluar una maquina
            Maquina maquina= maquinas.get(i);
            if(piezasObjetivo-piezas >= maquina.getCantPiezas()){
                solucion.add(maquina);
                piezas+= maquina.getCantPiezas(); 
                piezasProducidasGreedy+= maquina.getCantPiezas(); 
            }else{
                i++; 
            }
        }
        return solucion; 
    }   

    

    
    public double calidadGreedy(ArrayList<Maquina> solucionGreedy) {
        if (solucionGreedy.size() == 0) 
            return 0;
        return (double) piezasProducidasGreedy / solucionGreedy.size();
    }
    
}
