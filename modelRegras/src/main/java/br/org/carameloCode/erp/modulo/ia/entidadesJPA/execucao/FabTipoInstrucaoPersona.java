package br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucao;

import br.org.carameloCode.erp.modulo.ia.entidadesJPA.instrucao.TipoInstrucaoPersona;
import com.super_bits.modulosSB.Persistencia.fabrica.ComoFabricaComPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoDaFabrica;

/**
 * Tipos de instrução compartilhada entre personas. Para um novo tipo, basta
 * adicionar uma constante com id inédito.
 *
 * @author salvio
 */
public enum FabTipoInstrucaoPersona implements ComoFabricaComPersistencia {

    @InfoObjetoDaFabrica(classeObjeto = TipoInstrucaoPersona.class, id = 1, icone = "fa fa-user-tag", nomeObjeto = "Perfil do cliente")
    PERFIL_CLIENTE,
    @InfoObjetoDaFabrica(classeObjeto = TipoInstrucaoPersona.class, id = 2, icone = "fa fa-box-open", nomeObjeto = "Dados do produto")
    DADOS_PRODUTO,
    @InfoObjetoDaFabrica(classeObjeto = TipoInstrucaoPersona.class, id = 3, icone = "fa fa-building", nomeObjeto = "Dados da empresa")
    DADOS_EMPRESA,
    @InfoObjetoDaFabrica(classeObjeto = TipoInstrucaoPersona.class, id = 4, icone = "fa fa-globe", nomeObjeto = "Visão de mundo")
    VISAO_DE_MUNDO,
    @InfoObjetoDaFabrica(classeObjeto = TipoInstrucaoPersona.class, id = 5, icone = "fa fa-file-lines", nomeObjeto = "Exemplos de conteúdo")
    EXEMPLOS_CONTEUDO,
    @InfoObjetoDaFabrica(classeObjeto = TipoInstrucaoPersona.class, id = 6, icone = "fa fa-circle-info", nomeObjeto = "Outros")
    OUTROS;

    @Override
    public TipoInstrucaoPersona getRegistro() {
        return (TipoInstrucaoPersona) ComoFabricaComPersistencia.super.getRegistro();
    }

}
