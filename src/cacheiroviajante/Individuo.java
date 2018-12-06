/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cacheiroviajante;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author wylgn
 */
public class Individuo {

    private ArrayList<Cidade> cidades = new ArrayList();
    private int tamanho;
    private float aptidao;

    public Individuo(int tamanho, ArrayList<Cidade> cidades) {

        this.cidades = cidades;
        this.tamanho = tamanho;
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

    public int fitness() {
        Cidade c1, c2;
        float soma = 0;
        float hipot = 0;
        int i = 1;
        Iterator it = cidades.iterator();
        while (it.hasNext()) {
            if (i == tamanho) {
                c2 = cidades.get(0);
            } else {
                c2 = cidades.get(i);
            }
            c1 = (Cidade) it.next();
            float x1, y1;
            x1 = (c1.getX() - c2.getX());
            y1 = ((c1.getY() - c2.getY()));
            hipot = (x1 * x1) + (y1 * y1);
            soma += Math.pow(hipot, 2);

            System.out.println(c1.getNome());
            System.out.println(c2.getNome());
            System.out.println(soma);
            i++;
        }
        aptidao = soma;
        return 0;
    }

}
