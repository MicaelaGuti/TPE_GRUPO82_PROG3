#Grupo 82 - Gutierrez Micaela Agustina y Pedersen Angeles#



### Métricas Utilizadas

#### Cantidad de piezas producidas
- **Backtracking:** `piezasProducidasFinal`  
- **Greedy:** `piezasProducidasGreedy`

> Cantidad total de piezas que logra producir la solución. En el mejor de los casos, debe coincidir con el objetivo leído desde el archivo CSV.

---

#### Puestas en funcionamiento
Cantidad de máquinas utilizadas en la solución. Se busca **minimizar** este número para reducir costos operativos.

---

#### Costo de la solución
- **Backtracking:** `estadosGenerados`  
- **Greedy:** `estadosGeneradosGreedy`

> Representa cuántos estados (o decisiones) fueron evaluados durante la ejecución del algoritmo.  
> Cuanto menor este valor, más eficiente es el algoritmo.

---

#### Calidad de la solución
> La calidad de una solución se mide como la **cantidad promedio de piezas producidas por cada máquina puesta en funcionamiento**.
> Fórmula: calidad = piezas_producidas / puestas_en_funcionamiento
> Esta métrica permite evaluar cuán eficiente es una solución, priorizando aquellas que logran producir muchas piezas con la menor cantidad de máquinas posible.
