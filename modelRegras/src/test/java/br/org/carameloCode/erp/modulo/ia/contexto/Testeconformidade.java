/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package br.org.carameloCode.erp.modulo.ia.contexto;

import com.super_bits.modulosSB.Persistencia.ConfigGeral.SBPersistencia;
import com.super_bits.modulosSB.SBCore.ConfigGeral.CarameloCode;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import org.junit.Test;
import static org.junit.Assert.*;
import testesFW.TesteJunitJPAModuloERP;
import testesFW.TesteJunitSBPersistencia;

/**
 *
 * @author salvio
 */
public class Testeconformidade extends TesteJunitJPAModuloERP {

    public Testeconformidade() {
    }

    @Test
    public void testSomeMethod() {
        // TODO review the generated test code and remove the default call to fail.

    }

    @Override
    protected void configAmbienteDesevolvimento() {
        CarameloCode.configurar(new ConfiguradorCoreIACaramelo(), SBCore.ESTADO_APP.DESENVOLVIMENTO);
        SBPersistencia.configuraJPA(new ConfigPersistenciaIACaramelo());
        gerarCodigoModelProjeto();
    }

}
