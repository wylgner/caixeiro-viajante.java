/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cacheiroviajante;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Wylgner Emiliano
 */
public class CacheiroViajante {

    public static int numeroDeCidades = 0;
    public static int numeroDeCromossomos = 100;

    private static int[][] newCromossomo(int[][] cromossomos) {

        int[] cromoTemp = new int[numeroDeCidades];
        //int[] vetCidades = new int[numeroDeCidades];
        int i, j;
        for (i = 0; i < numeroDeCromossomos; i++) {
            boolean crom_valido = false;
            while (!crom_valido) {
                crom_valido = true;
                cromoTemp = resetaCromossomo();

                // Gera os cromossomos
                for (j = 0; j < numeroDeCidades; j++) {
                    cromoTemp[j] = geraValorDoCromossomo(cromoTemp);
                }
                crom_valido = cromossomoValido(cromoTemp, cromossomos);
            }
            cromossomos[i] = cromoTemp;
        }
        return cromossomos;

    }
    public static int [] preencheVet(){
         int[] vet = new int[numeroDeCidades];
         for(int i = 0; i < numeroDeCidades; i++){
             vet[i] = i;
         }
         return vet;

    }
       private static int geraValorDoCromossomo(int[] c_tmp) {
        int crom_temp;
        boolean valido;
        do {
            crom_temp = new Random().nextInt(numeroDeCidades);
            valido = true;
            for (int i = 0; i < numeroDeCidades; i++) {
                if (c_tmp[i] == crom_temp) {
                    valido = false;
                }
            }
        } while (!valido);
        return crom_temp;
    }
    private static boolean cromossomoValido(int[] c_tmp, int[][] cromossomos) {
        boolean crom_valido = true;
        for (int i = 0; i < numeroDeCromossomos; i++) {
            int n_iguais = 0;
            for (int j = 0; j < numeroDeCidades; j++) {
                if (c_tmp[i] == cromossomos[i][j]) {
                    n_iguais++;
                }
            }
            if (n_iguais == numeroDeCidades) {
                crom_valido = false;
            }
        }
        return crom_valido;
    }

 

    private static int[] resetaCromossomo() {
        int[] c = new int[numeroDeCidades];
        int i;
        for (i = 0; i < numeroDeCidades; i++) {
            c[i] = -1;
        }
        return c;
    }

    public static void main(String[] args) {

        int numeroDeGeracoes = 0;
        Random gerador = new Random();

        Scanner sc = new Scanner(System.in);

        //    ArrayList<Individuo> mapa = new ArrayList();
        System.out.println("Digite a quantidade de cidades:");
        numeroDeCidades = sc.nextInt();
        System.out.println("Digite a quantidade de Gerações:");
        int mapa[][] = new int[numeroDeCidades][numeroDeCidades];
        numeroDeGeracoes = sc.nextInt();
        for (int i = 0; i < numeroDeCidades; i++) {
            for (int j = 0; j < numeroDeCidades; j++) {
                mapa[i][j] = 0;
            }
        }
        for (int i = 0; i < numeroDeCidades; i++) {
            for (int j = 0; j < numeroDeCidades; j++) {
                if (i == j) {
                    mapa[i][j] = 0;
                } else if (mapa[i][j] == 0) {

                    mapa[i][j] = gerador.nextInt((2000) + 1);
                    mapa[j][i] = mapa[i][j];
                }
            }
        }
        for (int i = 0; i < numeroDeCidades; i++) {
            System.out.println();
            for (int j = 0; j < numeroDeCidades; j++) {
                System.out.printf("%d ", mapa[i][j]);
            }
        }
        int[][] cromossomos = new int[numeroDeCromossomos][numeroDeCidades];
        int[] resultados = new int[numeroDeCromossomos];
        newCromossomo(cromossomos);
      for(int i = 0; i< numeroDeCromossomos; i++){
          System.out.printf("Cromossomo %d = ", i);
           for (int j = 0; j < numeroDeCidades; j++) {
                System.out.printf("%d", cromossomos[i][j]);
           }
           System.out.println("");
      }
//        for (int i = 0; i < numeroDeCidades; i++) {
//            for (int j = 0; j < numeroDeCidades; j++) {
//                Individuo ind = new Individuo(gerador.nextInt((2000) + 1), i);
//                mapa.add(ind);
//            }
//
//        }
//
//        for (Individuo i : mapa) {
//            System.out.println("Id: " + i.getId_cidade());
//            System.out.println("Distancia: " + i.getDistancia());
//        }
    }
}
