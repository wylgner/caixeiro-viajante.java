/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cacheiroviajante;

import java.util.ArrayList;
import java.util.Iterator;
import java.lang.Comparable;
import java.util.Collections;

/**
 *
 * @author wylgn
 */
public class Individuo implements Comparable<Individuo> {

    private ArrayList<Cidade> cidades = new ArrayList();
    private int tamanho;
    private float aptidao;

    public Individuo(int tamanho, ArrayList<Cidade> cidades) {

        this.cidades = cidades;
        this.tamanho = tamanho;
        fitness();
    }

    public Individuo() {
    }

    public ArrayList<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(ArrayList<Cidade> Cidades) {
        this.cidades = Cidades;

    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public float getAptidao() {
        return aptidao;
    }

    public void setAptidao(float aptidao) {
        this.aptidao = aptidao;
    }

    public void printFitness() {
        Cidade c1, c2;
        Iterator it = cidades.iterator();
//        int i = 1;
//        System.out.println("---------------------------------------------------");
//        while (it.hasNext()) {
//            c1 = (Cidade) it.next();
//            if (i == cidades.size()) {
//                c2 = cidades.get(0);
//            } else {
//                c2 = cidades.get(i);
//            }
//            System.out.println(c1.getNome());
//            System.out.println(c2.getNome());
//            System.out.printf("%f", aptidao);
//            System.out.println("");
//            System.out.println("");
//            i++;
//        }
    }

    public int fitness() {
        Cidade c1, c2;
        float soma = 0;
        float hipot = 0;
        aptidao = 0;
        int i = 1;
        Iterator it = cidades.iterator();
        System.out.println("---------------------------------------------------");
        while (it.hasNext()) {
            if (i == cidades.size()) {
                c2 = cidades.get(0);
            } else {
                c2 = cidades.get(i);
            }
            c1 = (Cidade) it.next();
            float x1 = 0, y1 = 0;
            x1 = Math.abs((c1.getX() - c2.getX()));
            y1 = Math.abs(((c1.getY() - c2.getY())));
            hipot = (x1 * x1) + (y1 * y1);
            soma += Math.sqrt(hipot);

            System.out.println(c1.getNome());
            System.out.println(c2.getNome());
            System.out.printf("%.2f", soma);
            System.out.println("");
            i++;
        }
        aptidao = soma;
        return 0;
    }

    public int comparar(Individuo i1, Individuo i2) {
        if (i1.getAptidao() < i2.getAptidao()) {
            return -1;
        } else if (i1.getAptidao() > i2.getAptidao()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int compareTo(Individuo o) {
        if (this.aptidao < o.getAptidao()) {
            return -1;
        } else if (this.aptidao > o.getAptidao()) {
            return 1;
        } else {
            return 0;
        }
    }

}
