/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.carameloCode.erp.modulo.ia.contexto;

import com.super_bits.modulos.SBAcessosModel.ConfigPermissoesAcessoModelAbstrato;
import com.super_bits.modulosSB.SBCore.ConfigGeral.SBCore;
import com.super_bits.modulosSB.SBCore.UtilGeral.MapaAcoesSistema;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.permissoes.token.ItfTokenAcessoDinamico;
import com.super_bits.modulosSB.SBCore.modulos.erp.FabTipoAgenteOrganizacao;
import com.super_bits.modulosSB.SBCore.modulos.fabrica.ComoFabricaAcoes;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimplesSomenteLeitura;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoGrupoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoUsuario;
import com.super_bits.modulosSB.SBCore.modulos.view.menu.ComoMenusDeSessao;

import br.org.carameloCode.erp.modulo.ia.controller.ModuloExecucaoAcao;
import com.super_bits.modulos.SBAcessosModel.model.ContatoAonimoTransitorio;

import org.coletivojava.fw.api.objetoNativo.view.menu.MenuSBFW;
import org.coletivojava.fw.api.objetoNativo.view.menu.MenusDaSessao;
import org.coletivojava.fw.api.tratamentoErros.FabErro;

/**
 *
 * @author sfurbino
 */
public class ConfigPermissaoIaExecucao extends ConfigPermissoesAcessoModelAbstrato {

    public ConfigPermissaoIaExecucao() {
        super(new Class[]{ModuloExecucaoAcao.class});
    }

    public ConfigPermissaoIaExecucao(Class[] pClassesControllers) {
        super(pClassesControllers);
    }

    @Override
    public FabTipoAgenteOrganizacao getTipoAgente(ComoUsuario pUsuario) {

        return FabTipoAgenteOrganizacao.ATENDIMENTO;
    }

    @Override
    protected boolean persistirPermissoesNoBanco() {
        super.persistirPermissoesNoBanco(); //To change body of generated methods, choose Tools | Templates.

        try {

            MapaAcoesSistema.getListaTodasAcoes().stream().filter(ac -> ac.isPrecisaPermissao()).forEach(acP -> {

            });
            //EntityManager emmodulo = UtilSBPersistencia.getNovoEMIniciandoTransacao();
            //UtilSBPersistenciaFabricas.persistirRegistrosDaFabrica(FabModulosCRM.class, emmodulo, UtilSBPersistenciaFabricas.TipoOrdemGravacao.ORDERNAR_POR_ID);
            //UtilSBPersistencia.finzalizaTransacaoEFechaEM(emmodulo);

            return true;
        } catch (Throwable t) {
            SBCore.RelatarErro(FabErro.SOLICITAR_REPARO, "Erro configurando permissões", t);
            return false;
        }
    }

    @Override
    public ComoMenusDeSessao definirMenu(ComoGrupoUsuario pGrupo) {
        if (pGrupo == null) {
            return new MenusDaSessao(new MenuSBFW());
        }
        return new MenusDaSessao(new MenuSBFW());
    }

    @Override
    public boolean isObjetoPermitidoUsuario(ComoUsuario pUsuario, ComoEntidadeSimplesSomenteLeitura pObjeto) {

        return true;
    }

    @Override
    public ItfTokenAcessoDinamico gerarTokenDinamico(ComoFabricaAcoes pAcao, ComoEntidadeSimplesSomenteLeitura pItem, String pEmail) {
        return super.gerarTokenDinamico(pAcao, pItem, pEmail);
    }

    @Override
    public ComoUsuario gerarUsuarioConvidado(String pNome, String pTelefone) {

        return new ContatoAonimoTransitorio(pNome, pTelefone);
    }

}
