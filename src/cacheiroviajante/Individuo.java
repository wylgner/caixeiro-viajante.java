/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cacheiroviajante;

/**
 *
 * @author wylgn
 */
public class Individuo {

    public int distancia;
    public int id_cidade;
    
    public Individuo() {

    }

    public Individuo(int distancia, int id_cidade) {
        this.distancia = distancia;
        this.id_cidade = id_cidade;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public int getId_cidade() {
        return id_cidade;
    }

    public void setId_cidade(int id_cidade) {
        this.id_cidade = id_cidade;
    }

}
