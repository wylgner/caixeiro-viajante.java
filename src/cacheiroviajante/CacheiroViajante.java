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

    public static Individuo crossover1(Individuo pai, Individuo mae, int pontoDeCorte) {
        Individuo filho = new Individuo();
        ArrayList<Cidade> c1 = pai.getCidades();
        ArrayList<Cidade> c2 = mae.getCidades();
        ArrayList<Cidade> x1 = new ArrayList();
        for (int i = 0; i < pontoDeCorte; i++) {

            x1.add(c1.get(i));

        }
        for (int i = pontoDeCorte; i < numeroDeCidades; i++) {
            x1.add(c2.get(i));

        }
        System.out.println(x1.size());
        filho.setCidades(x1);

        return filho;
    }

    public static Individuo crossover2(Individuo pai, Individuo mae, int pontoDeCorte) {
        Individuo filho = new Individuo();
        ArrayList<Cidade> c1 = pai.getCidades();
        ArrayList<Cidade> c2 = mae.getCidades();
        ArrayList<Cidade> x1 = new ArrayList();
        for (int i = pontoDeCorte; i < numeroDeCidades; i++) {

            x1.add(c1.get(i));

        }
        for (int i = 0; i < pontoDeCorte; i++) {
            x1.add(c2.get(i));

        }
        System.out.println(x1.size());
        filho.setCidades(x1);

        return filho;
    }

    public static void main(String[] args) {

        int numeroDeGeracoes = 0;
        Random gerador = new Random();
        Scanner sc = new Scanner(System.in);
        ArrayList<Cidade> cidades = new ArrayList();
        ArrayList<Populacao> populacao = null;
        ArrayList<Individuo> ind = new ArrayList();
        ArrayList<Individuo> i2 = new ArrayList();
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
        Populacao otima = p;
        for (int i = 0; i < numeroDeGeracoes - 1; i++) {
            for (int j = 0; j < numeroDeIndividuos; j++) {
                Individuo pai = p.getGeracao().get(i);
                Individuo mae;
                if (i == numeroDeCidades) {
                    mae = p.getGeracao().get(0);
                } else {
                    mae = p.getGeracao().get(i);
                }

                int pontoDeCorte = gerador.nextInt((numeroDeCidades - 1) + 1);

                Individuo filho1 = crossover1(pai, mae, pontoDeCorte);
                Individuo filho2 = crossover2(pai, mae, pontoDeCorte);
                i2.add(filho1);
                i2.add(filho2);

            }
            
        }

    }
}
