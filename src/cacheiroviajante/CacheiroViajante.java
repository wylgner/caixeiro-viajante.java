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
    public static int numeroDeIndividuos = 0;

    private static int[][] newCromossomo(int[][] cromossomos) {

        int[] cromoTemp = new int[numeroDeCidades];
        //int[] vetCidades = new int[numeroDeCidades];
        int i, j;
        for (i = 0; i < numeroDeIndividuos; i++) {
            boolean crom_valido = false;
            while (!crom_valido) {
                crom_valido = true;
                cromoTemp = resetaCromossomo();

                for (j = 0; j < numeroDeCidades; j++) {
                    cromoTemp[j] = geraValorDoCromossomo(cromoTemp);
                }
                crom_valido = true;
            }
            cromossomos[i] = cromoTemp;
        }
        return cromossomos;

    }

    public static int[] preencheVet() {
        int[] vet = new int[numeroDeCidades];
        for (int i = 0; i < numeroDeCidades; i++) {
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

    private static int[] resetaCromossomo() {
        int[] c = new int[numeroDeCidades];
        int i;
        for (i = 0; i < numeroDeCidades; i++) {
            c[i] = -1;
        }
        return c;
    }

    public static ArrayList<Cidade> criaIndividuos(int numeroDeCidades) {
        ArrayList<Cidade> cidades = new ArrayList();
        Random gerador = new Random();
        for (int i = 0; i < numeroDeCidades; i++) {

            Cidade cromo = new Cidade("c" + i, gerador.nextInt((10000) + 1), gerador.nextInt((10000) + 1));
            cidades.add(cromo);

        }

        return cidades;
    }

    public static void main(String[] args) {

        int numeroDeGeracoes = 0;

        Scanner sc = new Scanner(System.in);
        ArrayList<Cidade> cidades = new ArrayList();
        ArrayList<Populacao> populacao = null;
        ArrayList<Individuo> ind = new ArrayList();
        System.out.println("Digite a quantidade de cidades:");
        numeroDeCidades = sc.nextInt();
        System.out.println("Digite a quantidade de Gerações:");
        numeroDeGeracoes = sc.nextInt();
          System.out.println("Digite a quantidade da população:");
        numeroDeIndividuos = sc.nextInt();
        cidades = criaIndividuos(numeroDeCidades);

        for (Cidade s : cidades) {

            System.out.println("Cidade: " + s.getNome());
            System.out.println("X:" + s.getX());
            System.out.println("Y:" + s.getY());

        }

        for (int i = 0; i < numeroDeIndividuos; i++) {
            cidades = criaIndividuos(numeroDeCidades);
            Individuo n = new Individuo(numeroDeCidades, cidades);
            n.fitness();
            ind.add(n);
        }
        Populacao p = new Populacao(ind);

//        int[][] cromossomos = new int[numeroDeIndividuos][numeroDeCidades];
//        int[] resultados = new int[numeroDeIndividuos];
//        cromossomos = newCromossomo(cromossomos);
//        for (int i = 0; i < numeroDeIndividuos; i++) {
//            System.out.printf("Individuo %d = ", i);
//            for (int j = 0; j < numeroDeCidades; j++) {
//                System.out.printf("%d", cromossomos[i][j]);
//            }
//            System.out.println("");
//        }
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
