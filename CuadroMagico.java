import java.util.*;

class Matriz{
    public int matriz [][];
    public int matrizObjetivo[][];
    public int matrizTemporal[][];
    public int arreglo[];

    public Matriz(){
        int cont = 1;
        this.matriz = new int[4][4];
        this.matrizObjetivo = new int[4][4];
        this.matrizTemporal = new int[4][4];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                this.matriz[i][j] = -1;
                this.matrizTemporal[i][j] = -1;
                this.matrizObjetivo[i][j] = cont;
                cont++;
            }
        }
        this.arreglo = new int[16];
    }

    public void llenaMatriz(){
        int vectorDeNumeros[] = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        int i, j, k=0;
        Random x = new Random();
        for(int valor : vectorDeNumeros){
            do{
                i = x.nextInt(4);
                j = x.nextInt(4);
            }while(this.matriz[i][j] != -1);
            this.matriz[i][j] = valor;
        }

        for(i = 0; i < 4; i++){
            for( j = 0; j < 4; j++){
                arreglo[k] = this.matriz[i][j];
                k++;
            }
        }

        System.out.println("*** CUADRO MÁGICO INICIAL ***");
        muestraMatriz();
    }

    public void muestraMatriz(){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                System.out.print(" "+this.matriz[i][j]);
            }
            System.out.println();
        }
    }

    public void copiaMatriz(){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                this.matrizTemporal[i][j] = this.matriz[i][j];
            }
        }
    }

    public boolean juega(int i, int j){
        //CHECANDO LAS POSICIONES A LAS QUE PODEMOS MOVER EL 0
        int aux;
        boolean flag = false;
        copiaMatriz();
        //ESQUINAS
        if((i == 0 || i == 3) && (j == 0 || j == 3)){
            System.out.println("ENTRA");
            //CORREMOS LA PRIMERA FUNCIÓN DE COSTO 
            int derecha1=0, izquierda1=0, arriba1=0, abajo1=0;
            
             //Mueve el cero a la derecha o izquierda
                if(j+1 == 4){ //Nos tenemos que mover a la izquierda
                    aux = this.matrizTemporal[i][j-1];
                    this.matrizTemporal[i][j] = aux;
                    this.matrizTemporal[i][j-1] = 0;
                    izquierda1 = botonesEnSuLugar(this.matrizTemporal);
                    copiaMatriz();
                }else{          //Nos tenemos que mover a la derecha
                    aux = this.matrizTemporal[i][j+1];
                    this.matrizTemporal[i][j] = aux;
                    this.matrizTemporal[i][j+1] = 0;
                    derecha1 = botonesEnSuLugar(this.matrizTemporal);
                    copiaMatriz();
                }
                if(i+1 == 4){ //Nos movemos para abajo
                    aux = this.matrizTemporal[i-1][j];
                    this.matrizTemporal[i][j] = aux;
                    this.matrizTemporal[i-1][j] = 0;
                    abajo1 = botonesEnSuLugar(this.matrizTemporal);
                    copiaMatriz();
                }else{  //Nos movemos para arriba
                    aux = this.matrizTemporal[i+1][j];
                    this.matrizTemporal[i][j] = aux;
                    this.matrizTemporal[i+1][j] = 0;
                    arriba1 = botonesEnSuLugar(this.matrizTemporal);
                    copiaMatriz();
                }

                if(i == 0 && j == 0){
                    if(derecha1 > arriba1 ){
                        aux = this.matriz[i][j+1];
                        this.matriz[i][j] = aux;
                        this.matriz[i][j+1] = 0;
                    }else{
                        aux = this.matriz[i+1][j];
                        this.matriz[i][j] = aux;
                        this.matriz[i+1][j] = 0;
                    }
                }

                if(i == 0 && j == 3){
                    if(izquierda1 > arriba1 ){
                        aux = this.matriz[i][j-1];
                        this.matriz[i][j] = aux;
                        this.matriz[i][j-1] = 0;
                    }else{
                        aux = this.matriz[i+1][j];
                        this.matriz[i][j] = aux;
                        this.matriz[i+1][j] = 0;
                    }
                }

                if(i == 3 && j == 0){
                    if(derecha1 > abajo1 ){
                        aux = this.matriz[i][j+1];
                        this.matriz[i][j] = aux;
                        this.matriz[i][j+1] = 0;
                    }else{
                        aux = this.matriz[i-1][j];
                        this.matriz[i][j] = aux;
                        this.matriz[i-1][j] = 0;
                    }
                }

                if(i == 3 && j == 3){
                    if(izquierda1 > abajo1 ){
                        aux = this.matriz[i][j-1];
                        this.matriz[i][j] = aux;
                        this.matriz[i][j-1] = 0;
                    }else{
                        aux = this.matriz[i-1][j];
                        this.matriz[i][j] = aux;
                        this.matriz[i-1][j] = 0;
                    }
                }
            flag = true; 
            return(true);
        }

        //CENTRO
        if((i == 1 || i == 2) && (j == 1 || j == 2)){
            int derecha, izquierda, arriba, abajo;

             //The cero goes down
                aux = this.matrizTemporal[i-1][j];
                this.matrizTemporal[i][j] = aux;
                this.matrizTemporal[i-1][j] = 0;
                abajo = botonesEnSuLugar(this.matrizTemporal);
                copiaMatriz();
            
            //The cero goes right
                aux = this.matrizTemporal[i][j+1];
                this.matrizTemporal[i][j] = aux;
                this.matrizTemporal[i][j+1] = 0;
                derecha = botonesEnSuLugar(this.matrizTemporal);
                copiaMatriz();
            
            //The cero goes up
                aux = this.matrizTemporal[i+1][j];
                this.matrizTemporal[i][j] = aux;
                this.matrizTemporal[i+1][j] = 0;
                arriba = botonesEnSuLugar(this.matrizTemporal);
                copiaMatriz();
            
            //The cero goes left
                aux = this.matrizTemporal[i][j-1];
                this.matrizTemporal[i][j] = aux;
                this.matrizTemporal[i][j-1] = 0;
                izquierda = botonesEnSuLugar(this.matrizTemporal);
                copiaMatriz();

                if((arriba > abajo) && (arriba > izquierda) && (arriba > derecha)){
                    aux = this.matriz[i+1][j];
                    this.matriz[i][j] = aux;
                    this.matriz[i+1][j] = 0;
                }else{
                    if((abajo > arriba) && (abajo > izquierda) && (abajo > derecha)){
                        aux = this.matriz[i-1][j];
                        this.matriz[i][j] = aux;
                        this.matriz[i-1][j] = 0;
                    }else{
                        if((izquierda > derecha) && (izquierda > arriba) && (izquierda > abajo)){
                            aux = this.matrizTemporal[i][j-1];
                            this.matrizTemporal[i][j] = aux;
                            this.matrizTemporal[i][j-1] = 0;
                        }else{
                            if((derecha > izquierda) && (derecha > arriba) && (derecha > abajo)){
                                aux = this.matrizTemporal[i][j+1];
                                this.matrizTemporal[i][j] = aux;
                                this.matrizTemporal[i][j+1] = 0;
                            }else{
                                if(arriba == abajo && abajo == izquierda && izquierda == derecha){
                                    //ESCOGER ALEATORIAMENTE
                                }
                            }
                        }
                    }
                }

            flag = true; 
            return(true);
        }

        //LATERALES SUPERIOR E INFERIOR
        if((i == 0 || i == 3) && (j == 1 || j == 2)){
            int izq, der, arr=0, abaj=0;
            if(i-1 == -1){
                aux = this.matrizTemporal[i+1][j];
                this.matrizTemporal[i][j] = aux;
                this.matrizTemporal[i+1][j] = 0;
                arr = botonesEnSuLugar(this.matrizTemporal);
                copiaMatriz();
            }else{
                aux = this.matrizTemporal[i-1][j];
                this.matrizTemporal[i][j] = aux;
                this.matrizTemporal[i-1][j] = 0;
                abaj = botonesEnSuLugar(this.matrizTemporal);
                copiaMatriz();
            }

                aux = this.matrizTemporal[i][j+1];
                this.matrizTemporal[i][j] = aux;
                this.matrizTemporal[i][j+1] = 0;
                der = botonesEnSuLugar(this.matrizTemporal);
                copiaMatriz();
                aux = this.matrizTemporal[i][j-1];
                this.matrizTemporal[i][j] = aux;
                this.matrizTemporal[i][j-1] = 0;
                izq = botonesEnSuLugar(this.matrizTemporal);
                copiaMatriz();

                if(i == 0 && (j == 1 || j == 2)){
                    if(arr > der && arr > izq){
                        aux = this.matriz[i+1][j];
                        this.matriz[i][j] = aux;
                        this.matriz[i+1][j] = 0;
                    }else{
                        if(der > arr && der > izq){
                            aux = this.matriz[i][j+1];
                            this.matriz[i][j] = aux;
                            this.matriz[i][j+1] = 0;
                        }else{
                            if(izq > der && izq > arr){
                                aux = this.matriz[i][j-1];
                                this.matriz[i][j] = aux;
                                this.matriz[i][j-1] = 0;
                            }else{
                                //SON IGUALES
                            }
                        }
                    }
                }

                if(i == 3 && (j == 1 || j == 2)){
                    if(abaj > der && abaj > izq){
                        aux = this.matriz[i-1][j];
                        this.matriz[i][j] = aux;
                        this.matriz[i-1][j] = 0;
                    }else{
                        if(der > abaj && der > izq){
                            aux = this.matriz[i][j+1];
                            this.matriz[i][j] = aux;
                            this.matriz[i][j+1] = 0;
                        }else{
                            if(izq > der && izq > abaj){
                                aux = this.matriz[i][j-1];
                                this.matriz[i][j] = aux;
                                this.matriz[i][j-1] = 0;
                            }else{
                                //SON IGUALES
                            }
                        }
                    }
                }
            flag = true; 
            return(true);
        }

        //LATERALES IZQUIERDA Y DERECHA
        if((i == 1 || i == 2) && (j == 0 || j == 3)){
            int up, down, left=0, right=0;
            if(j-1 == -1){
                aux = this.matrizTemporal[i][j+1];
                this.matrizTemporal[i][j] = aux;
                this.matrizTemporal[i][j+1] = 0;
                right = botonesEnSuLugar(this.matrizTemporal);
                copiaMatriz();
            }else{
                aux = this.matrizTemporal[i][j-1];
                this.matrizTemporal[i][j] = aux;
                this.matrizTemporal[i][j-1] = 0;
                left = botonesEnSuLugar(this.matrizTemporal);
                copiaMatriz();
            }

            aux = this.matrizTemporal[i+1][j];
            this.matrizTemporal[i][j] = aux;
            this.matrizTemporal[i+1][j] = 0;
            up = botonesEnSuLugar(this.matrizTemporal);
            copiaMatriz();

            aux = this.matrizTemporal[i-1][j];
            this.matrizTemporal[i][j] = aux;
            this.matrizTemporal[i-1][j] = 0;
            down = botonesEnSuLugar(this.matrizTemporal);
            copiaMatriz();


            if(j == 0 && (i == 1 || i == 2)){
                if(up > down && up > right){
                    aux = this.matriz[i+1][j];
                    this.matriz[i][j] = aux;
                    this.matriz[i+1][j] = 0;
                }else{
                    if(right > up && right > down){
                        aux = this.matriz[i][j+1];
                        this.matriz[i][j] = aux;
                        this.matriz[i][j+1] = 0;
                    }else{
                        if(down > up && down > right){
                            aux = this.matriz[i-1][j];
                            this.matriz[i][j] = aux;
                            this.matriz[i-1][j] = 0;
                        }else{
                            //SON IGUALES
                        }
                    }
                }
            }

            if(j == 3 && (i == 1 || i == 2)){
                if(down > left && down > up){
                    aux = this.matriz[i-1][j];
                    this.matriz[i][j] = aux;
                    this.matriz[i-1][j] = 0;
                }else{
                    if(left > down && left > up){
                        aux = this.matriz[i][j-1];
                        this.matriz[i][j] = aux;
                        this.matriz[i][j-1] = 0;
                    }else{
                        if(up > left && up > down){
                            aux = this.matriz[i+1][j];
                            this.matriz[i][j] = aux;
                            this.matriz[i+1][j] = 0;
                        }else{
                            //SON IGUALES
                        }
                    }
                }
            }else{
               
            }

            flag = true; 
            return(true);
        }
        return flag;

    }

    public void ejecutaMovimiento(){
        System.out.println("--- ESTADO ANTERIOR ---");
        muestraMatriz();
        System.out.println();
        finDeMovimiento:
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(this.matriz[i][j] == 0){
                    juega(i,j);    //Si la función nos regresa un true es que sí se hizo un movimiento
                    break finDeMovimiento;
                }
            }
        }
        System.out.println("--- ESTADO ACTUAL ---");
        muestraMatriz();
        System.out.println();
    }

    public boolean compruebaSolucionabilidad(){
        boolean inversionesPar = false;
        boolean inversionesImpar = false;
        boolean posicionDelCeroPar = false;
        boolean posicionDelCeroImpar = false;
        boolean solucionable = false;
        int cont=0;

        finPrimeraComprobacion:
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(this.matriz[i][j] == 0 && (i == 0 || i == 2)){
                    posicionDelCeroPar = true;
                    break finPrimeraComprobacion;
                }else{
                    if(this.matriz[i][j] == 0 && (i == 1 || i == 3)){
                        posicionDelCeroImpar = true;
                        break finPrimeraComprobacion;
                    }
                }
            }
        }

        for(int i = 0; i < 16; i++){
            for( int j = (i+1); j < 16; j++){
                if(arreglo[i] > arreglo[j] && (arreglo[j] != 0)){
                    cont++;
                }
            }
        }

        if(cont % 2 == 0){
            inversionesPar = true;  
        }else{
            inversionesImpar = true;
        }

        if(posicionDelCeroPar && inversionesImpar)
            solucionable = true;
        if(posicionDelCeroImpar && inversionesPar)
            solucionable = true;

        return(solucionable);

    }

    public int botonesEnSuLugar(int matriz[][]){
        int cont = 0;
        System.out.println("ENTRA");
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(matriz[i][j] == this.matrizObjetivo[i][j]){
                    cont++;
                }
            }
        }

        return cont;
    }

    public int botonesFueraDeSuLugar(int matriz[][]){
        int cont = 0;

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(matriz[i][j] != this.matrizObjetivo[i][j]){
                    cont++;
                }
            }
        }

        return cont;
    }


}

public class CuadroMagico{
    public static void main(String args[]){
        Matriz cuadroMagico = new Matriz();
        cuadroMagico.llenaMatriz();
        if(cuadroMagico.compruebaSolucionabilidad()){
            for(int i = 0; i < 50; i++){
                cuadroMagico.ejecutaMovimiento();
                System.out.println("---------------------------------");
            }
        }else{
            System.out.println("DEBIDO A LA DISPOCIÓN INICIAL DE LA MATRIZ NO SE PUEDE RESOLVER EL PUZZLE");
        }
 
    }
}