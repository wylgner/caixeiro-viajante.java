/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cacheiroviajante;

import java.util.ArrayList;
import java.util.Collections;
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

    public static ArrayList<Cidade> criaIndividuos(int numeroDeCidades) {
        ArrayList<Cidade> cidades = new ArrayList();
        Random gerador = new Random();
        for (int i = 0; i < numeroDeCidades; i++) {

            Cidade cromo = new Cidade("c" + i, gerador.nextInt((100) + 1), gerador.nextInt((100) + 1));
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
            Collections.shuffle(cidades);
            Individuo n = new Individuo(numeroDeCidades, cidades);
            ind.add(n);
        }
        Populacao p = new Populacao(ind);
        p.ordenaIndividuos();
        //crossover

    }
}
