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
    public static int taxaDeMutacao = 0;
    public static int numeroDeGeracoes = 0;

    public static ArrayList<Cidade> criaIndividuos(int numeroDeCidades) throws InterruptedException {
        ArrayList<Cidade> cidades = new ArrayList();
        Random gerador = new Random();
        for (int i = 0; i < numeroDeCidades; i++) {

            Cidade cromo = new Cidade("c" + i, gerador.nextInt((100) + 1), gerador.nextInt((100) + 1));
            cidades.add(cromo);

        }

        return cidades;
    }

    public static Individuo getPai(Populacao p, int pos) {
        Individuo i = new Individuo();
        i = p.getGeracao().get(pos);

        for (int j = pos; j < p.getGeracao().size(); j++) {
            Individuo c = p.getGeracao().get(j);
            if (i.getAptidao() > c.getAptidao()) {
                i = c;
            }
        }

        return i;
    }

    public static Individuo getMae(Populacao p, int pos) {
        Individuo i = new Individuo();
        try {
            i = p.getGeracao().get(pos);
        } catch (Exception e) {
            i = p.getGeracao().get(pos - 1);
        }

        for (int j = pos; j < p.getGeracao().size(); j++) {
            Individuo c = p.getGeracao().get(j);
            if (i.getAptidao() > c.getAptidao()) {
                i = c;
            }
        }

        return i;
    }

    public static Individuo mutacao(Individuo ind) {
        Random r = new Random();
        Cidade aux = new Cidade();
        ArrayList cid = ind.getCidades();
        int pos1 = r.nextInt((numeroDeCidades - 1) + 1);
        int pos2 = r.nextInt((numeroDeCidades - 1) + 1);
        int loop = r.nextInt((numeroDeCidades - 1) + 1);
        for (int i = 0; i < loop; i++) {
            if (r.nextInt((100) + 1) <= taxaDeMutacao) {
                Collections.shuffle(cid);

            }
        }

        ind.setCidades(cid);
        return ind;
    }

    public static ArrayList<Individuo> crossover1(Populacao p, int pontoDeCorte) {
         
        int pontoDec = numeroDeCidades / 2;
        ArrayList<Cidade> c = new ArrayList();
        
        ArrayList<Individuo> i2 = new ArrayList();
       
        for (int x = 0; x < numeroDeIndividuos; x++) {
            Individuo pai = getPai(p, x);
            pai = mutacao(pai);
            Individuo mae = getMae(p, x + 1);
            mae = mutacao(mae);
            ArrayList<Cidade> x1 = new ArrayList();
           
            c.clear();
            for (int i = 0; i < pontoDec; i++) {

                c.add(pai.getCidades().get(i));
            }
            for (int j = pontoDec; j < numeroDeCidades; j++) {
                c.add(mae.getCidades().get(j));

            }
            Individuo id = new Individuo(c.size(), c);

            //filho.setCidades(x1);
            i2.add(id);

        }

        return i2;
    }

    public static void main(String[] args) throws InterruptedException {

        Random gerador = new Random();
        Scanner sc = new Scanner(System.in);
        ArrayList<Cidade> cidades = new ArrayList();
        ArrayList<Populacao> todasGeracos = null;
        ArrayList<Individuo> ind = new ArrayList();

        System.out.println("Digite a quantidade de cidades:");
        numeroDeCidades = sc.nextInt();
        System.out.println("Digite a quantidade de Gerações:");
        numeroDeGeracoes = sc.nextInt();
        System.out.println("Digite a quantidade da população:");
        numeroDeIndividuos = sc.nextInt();
        System.out.println("Digite a taxa de mutação:");
        taxaDeMutacao = sc.nextInt();
        cidades = criaIndividuos(numeroDeCidades);

        for (Cidade s : cidades) {

            System.out.println("Cidade: " + s.getNome());
            System.out.println("X:" + s.getX());
            System.out.println("Y:" + s.getY());

        }

        for (int i = 0; i < numeroDeIndividuos; i++) {
            Collections.shuffle(cidades);
            Individuo n = new Individuo(numeroDeCidades, cidades);
            n.printFitness();
            ind.add(n);
        }
        Populacao p = new Populacao(ind);
        p.ordenaIndividuos();
        System.out.println("Média de Aptdão da população: " + p.getFitness());
        System.out.println("-----------------------------------------------------");
        Populacao otima = p;
        Individuo IndTop = p.getGeracao().get(0);
        int geracaoDoMelhor = 0;

        for (int i = 0; i < numeroDeGeracoes - 1; i++) {
            ArrayList<Individuo> i2 = new ArrayList();

            i2 = crossover1(p, numeroDeCidades);
            p.setGeracao(i2);

            p.ordenaIndividuos();
            p.calculaMediaFitness();

            System.out.println("Média de Aptdão da população: " + p.getFitness());
            System.out.println("-----------------------------------------------------");
            for (Individuo ixa : p.getGeracao()) {
                if (ixa.getAptidao() < IndTop.getAptidao()) {
                    IndTop = ixa;
                    geracaoDoMelhor = i+1;
                    
                }
            }

        }
        System.out.println("----------------------------------------------------------");

        System.out.println("Melhor Indivíduo de todas as Gerações: ");
        for (Cidade c : IndTop.getCidades()) {
            System.out.println(c.getNome());
        }
        System.out.println(IndTop.getCidades().get(0).getNome());
        System.out.println("Aptdão: " + IndTop.getAptidao());
        System.out.println("Geração: " + geracaoDoMelhor);

    }

}
