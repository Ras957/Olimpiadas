/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.List;

/**
 *
 * @author Francisco Miguel Carrasquilla Rodríguez-Córdoba
 * <afcarrasquilla@iesfrancisidlosrios.es>
 */
public class Headquarter {
    protected Integer id;
    protected String name;
    protected float budget;
    protected List<SportComplex> complexes;

    public Headquarter(String name, float budget) {
        this.name = name;
        this.budget = budget;
    }

    public Headquarter() {
        this.name = "";
        this.budget = 0;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the budget
     */
    public float getBudget() {
        return budget;
    }

    /**
     * @param budget the budget to set
     */
    public void setBudget(float budget) {
        this.budget = budget;
    }

    /**
     * @return the complexes
     */
    public List<SportComplex> getComplexes() {
        return complexes;
    }

    /**
     * @param complexes the complexes to set
     */
    public void setComplexes(List<SportComplex> complexes) {
        this.complexes = complexes;
    }
    
    @Override
    public String toString(){
        return "ID: "+this.id+" Nombre: "+this.name+" Presupuesto "+this.budget;
    }
}










