/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.ia.contexto;

import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ArmazenamentoComunicacaoTransient;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.ServicoComunicacaoDesktop;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.servicosCore.ComoServicoComunicacao;

/**
 *
 * @author salvio
 */
public class ServicoComunicacaoIATestes extends ServicoComunicacaoDesktop implements ComoServicoComunicacao {

    public ServicoComunicacaoIATestes() {
        super(ArmazenamentoComunicacaoTransient.class);
    }

    @Override
    public String getTokenDispositivoNotificacao(ComoUsuario pUsuario) {
        return pUsuario.getEmail();
    }

}
