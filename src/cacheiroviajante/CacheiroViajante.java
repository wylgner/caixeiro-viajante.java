/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cacheiroviajante;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.EOFException;
import org.jfree.chart.JFreeChart;

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

    public static Individuo torneio(Populacao p) {
        Random r = new Random();
        int primeiro = r.nextInt((numeroDeCidades - 1) + 1);
        int segundo = r.nextInt((numeroDeCidades - 1) + 1);
        Individuo p1 = p.getGeracao().get(primeiro);
        Individuo p2 = p.getGeracao().get(segundo);
        Individuo retorno;
        if (p1.getAptidao() < p2.getAptidao()) {
            retorno = p1;
        } else {
            retorno = p2;
        }
        return retorno;

    }

    public static ArrayList<Individuo> crossover1(Populacao p, int pontoDeCorte) {

        int pontoDec = numeroDeCidades / 2;
        ArrayList<Cidade> c = new ArrayList();

        ArrayList<Individuo> i2 = new ArrayList();

        for (int x = 0; x < numeroDeIndividuos; x++) {
            Individuo pai = torneio(p);
            pai = mutacao(pai);
            Individuo mae = torneio(p);
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

    public static void criaGrafico(Individuo ind) {
        DefaultCategoryDataset df = new DefaultCategoryDataset();
        for (int i = 0; i < numeroDeCidades; i++) {
            df.addValue(ind.getCidades().get(i).getX(), "X", "X_" + ind.getCidades().get(i).getNome());
            //  df.addValue(ind.getCidades().get(i).getY(), "Y", "Y_" + ind.getCidades().get(i).getNome());
        }
        JFreeChart criaChart = ChartFactory.createLineChart("Teste de Grafico", "X", "Y", df,
                PlotOrientation.HORIZONTAL, true, true, false);
        try {
            System.out.println("Criando...");
            OutputStream png = new FileOutputStream("Grafico.png");
            ChartUtilities.writeChartAsPNG(png, criaChart, 500, 400);
            png.close();
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
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
                    geracaoDoMelhor = i + 1;

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
