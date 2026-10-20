/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.ia.contexto;

import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucao.FabTipoInstrucaoPersona;
import com.super_bits.modulosSB.Persistencia.ConfigGeral.ItfConfigSBPersistencia;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCDataHora;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabrica;

/**
 *
 * @author Salvio
 */
public class ConfigPersistenciaIACaramelo implements ItfConfigSBPersistencia {

    @Override
    public String bancoPrincipal() {
        return "iaExecucoesTeste";
    }

    @Override
    public String[] bancosExtra() {
        return new String[0];
    }

    @Override
    public String formatoDataBanco() {
        return UtilCRCDataHora.datahoraSistemaFr.toString();
    }

    @Override
    public String formatoDataUsuario() {
        return UtilCRCDataHora.horaUsuarioFr.toString();
    }

    @Override
    public String pastaImagensJPA() {
        return "/img";
    }

    @Override
    public void criarBancoInicial() {

    }

    @Override
    public Class<? extends ComoFabrica>[] fabricasRegistrosIniciais() {
        return new Class[]{
            FabTipoInstrucaoPersona.class//    FabAtividadeCRMAutoexecucao.class
        };
    }

}
