/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.org.carameloCode.erp.modulo.ia.acoes;

import br.org.carameloCode.erp.modulo.ia.api.model.persona.CPPersona;
import br.org.carameloCode.erp.modulo.ia.contexto.instrucaoia.CPInstrucaoIA;
import br.org.carameloCode.erp.modulo.ia.contexto.instrucaoia.CPTipoExecucaoIA;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucao.ExecucaoIA;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucao.TipoExecucaoIA;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.instrucao.InstrucaoIA;

import br.org.carameloCode.erp.modulo.ia.entidadesJPA.persona.Persona;
import com.super_bits.modulos.SBAcessosModel.controller.FabModulosSistemaSB;
import com.super_bits.modulos.SBAcessosModel.controller.InfoModulosSistemaSB;
import com.super_bits.modulos.SBAcessosModel.fabricas.ComoFabricaDeAcoesPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoController;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoFormulario;
import com.super_bits.modulosSB.SBCore.modulos.Controller.anotacoes.InfoTipoAcaoGestaoEntidade;
import com.super_bits.modulosSB.SBCore.modulos.comunicacao.FabTipoRespostaComunicacao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.icones.FabIconeFontAwesome;

/**
 *
 * @author salvio
 */
@InfoModulosSistemaSB(modulo = FabModulosSistemaSB.COMUNICACAO)
public enum FabAcaoIAExecucao implements ComoFabricaDeAcoesPersistencia {

    @InfoTipoAcaoGestaoEntidade(entidade = ExecucaoIA.class)
    EXECUCAO_IA_MB_GESTAO,
    @InfoTipoAcaoController()
    EXECUCAO_IA_CTR_EXECUTAR_DIALOGO,
    @InfoTipoAcaoGestaoEntidade(icone = "fa fa-book-open", nomeAcao = "Instruções de IA", entidade = InstrucaoIA.class)
    INSTRUCAO_IA_MB_GESTAO,
    @InfoTipoAcaoFormulario(icone = "fa fa-book-open", nomeAcao = "Listar instruções de IA", campos = {CPInstrucaoIA.id, CPInstrucaoIA.nome, CPInstrucaoIA.tipoinstrucao, CPInstrucaoIA.ativo})
    INSTRUCAO_IA_FRM_LISTAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Ver instrução de IA", campos = {CPInstrucaoIA.id, CPInstrucaoIA.nome, CPInstrucaoIA.tipoinstrucao, CPInstrucaoIA.ativo, CPInstrucaoIA.conteudo})
    INSTRUCAO_IA_FRM_VISUALIZAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Nova instrução de IA", campos = {CPInstrucaoIA.nome, CPInstrucaoIA.tipoinstrucao, CPInstrucaoIA.ativo, CPInstrucaoIA.conteudo})
    INSTRUCAO_IA_FRM_NOVO,
    @InfoTipoAcaoFormulario(nomeAcao = "Editar instrução de IA", campos = {CPInstrucaoIA.nome, CPInstrucaoIA.tipoinstrucao, CPInstrucaoIA.ativo, CPInstrucaoIA.conteudo})
    INSTRUCAO_IA_FRM_EDITAR,
    @InfoTipoAcaoController(nomeAcao = "Salvar", iconeFonteAnsowame = FabIconeFontAwesome.REG_SALVAR)
    INSTRUCAO_IA_CTR_SALVAR_MERGE,
    @InfoTipoAcaoGestaoEntidade(icone = "fa fa-gears", nomeAcao = "Tipos de execução de IA", entidade = TipoExecucaoIA.class)
    TIPO_EXECUCAO_IA_MB_GESTAO,
    @InfoTipoAcaoFormulario(icone = "fa fa-gears", nomeAcao = "Listar tipos de execução de IA", campos = {CPTipoExecucaoIA.id, CPTipoExecucaoIA.nome, CPTipoExecucaoIA.descricao})
    TIPO_EXECUCAO_IA_FRM_LISTAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Ver tipo de execução de IA", campos = {CPTipoExecucaoIA.id, CPTipoExecucaoIA.nome, CPTipoExecucaoIA.descricao, CPTipoExecucaoIA.icone,
        "[separador: Formato de saída por IA]", CPTipoExecucaoIA.structuredoutputconfig, CPTipoExecucaoIA.outputconfigopenia, CPTipoExecucaoIA.outputconfigcpilot, CPTipoExecucaoIA.outputconfigdeepseek})
    TIPO_EXECUCAO_IA_FRM_VISUALIZAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Novo tipo de execução de IA", campos = {CPTipoExecucaoIA.nome, CPTipoExecucaoIA.descricao, CPTipoExecucaoIA.icone,
        "[separador: Formato de saída por IA]", CPTipoExecucaoIA.structuredoutputconfig, CPTipoExecucaoIA.outputconfigopenia, CPTipoExecucaoIA.outputconfigcpilot, CPTipoExecucaoIA.outputconfigdeepseek})
    TIPO_EXECUCAO_IA_FRM_NOVO,
    @InfoTipoAcaoFormulario(nomeAcao = "Editar tipo de execução de IA", campos = {CPTipoExecucaoIA.nome, CPTipoExecucaoIA.descricao, CPTipoExecucaoIA.icone,
        "[separador: Formato de saída por IA]", CPTipoExecucaoIA.structuredoutputconfig, CPTipoExecucaoIA.outputconfigopenia, CPTipoExecucaoIA.outputconfigcpilot, CPTipoExecucaoIA.outputconfigdeepseek})
    TIPO_EXECUCAO_IA_FRM_EDITAR,
    @InfoTipoAcaoController(nomeAcao = "Salvar", iconeFonteAnsowame = FabIconeFontAwesome.REG_SALVAR)
    TIPO_EXECUCAO_IA_CTR_SALVAR_MERGE,
    @InfoTipoAcaoGestaoEntidade(icone = "fa fa-android", nomeAcao = "Personas IA", entidade = Persona.class)
    PERSONA_IA_MB_GESTAO,
    @InfoTipoAcaoFormulario(icone = "fa fa-android", nomeAcao = "Listar Personas IA", campos = {CPPersona.id, CPPersona.nome, CPPersona.descricao, CPPersona.publicoalvo, CPPersona.ativo})
    PERSONA_IA_FRM_LISTAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Ver mais detalhes", campos = {"[separador: Informacoes Basicas da Persona]", CPPersona.id, CPPersona.nome, CPPersona.descricao, "[separador: Informacoes tecnicas da Persona]", CPPersona.publicoalvo, CPPersona.ativo, CPPersona.contexto, CPPersona.idioma, CPPersona.objetivo, CPPersona.palavrasproibidas, CPPersona.limitepalavras, CPPersona.instrucoesadicionais, CPPersona.tiporespostaspadrao, CPPersona.tonalidade, CPPersona.regrasresposta})
    PERSONA_IA_FRM_VISUALIZAR,
    @InfoTipoAcaoFormulario(nomeAcao = "Criar Persona IA", campos = {
        "[separador: Identificacao]", CPPersona.nome, CPPersona.tipopersona, CPPersona.ativo, CPPersona.descricao, "imagemAvatarPersona",
        "[separador: Publico e objetivo]", CPPersona.publicoalvo, CPPersona.objetivo, CPPersona.contexto,
        "[separador: Estilo de escrita]", CPPersona.tonalidade, CPPersona.idioma, CPPersona.tiporespostaspadrao,
        "[separador: Regras e restricoes]", CPPersona.regrasresposta, CPPersona.limitepalavras, CPPersona.palavrasproibidas,
        "[separador: Instrucoes]", CPPersona.instrucoesadicionais, CPPersona.instrucoescompartilhadas})
    PERSONA_IA_FRM_NOVO,
    @InfoTipoAcaoFormulario(nomeAcao = "Editar Persona IA", campos = {
        "[separador: Identificacao]", CPPersona.nome, CPPersona.tipopersona, CPPersona.ativo, CPPersona.descricao, "imagemAvatarPersona",
        "[separador: Publico e objetivo]", CPPersona.publicoalvo, CPPersona.objetivo, CPPersona.contexto,
        "[separador: Estilo de escrita]", CPPersona.tonalidade, CPPersona.idioma, CPPersona.tiporespostaspadrao,
        "[separador: Regras e restricoes]", CPPersona.regrasresposta, CPPersona.limitepalavras, CPPersona.palavrasproibidas,
        "[separador: Instrucoes]", CPPersona.instrucoesadicionais, CPPersona.instrucoescompartilhadas})
    PERSONA_IA_FRM_EDITAR,
    @InfoTipoAcaoController(nomeAcao = "Salvar Persona", iconeFonteAnsowame = FabIconeFontAwesome.REG_SALVAR)
    PERSONA_IA_CTR_SALVAR_MERGE,
    @InfoTipoAcaoController(nomeAcao = "Excluir", exibirModalConfirmacao = true,
            fraseComunicação = "Deseja excluir a persona [nome] ?",
            respostasComunicacaoPersonalizada = {FabTipoRespostaComunicacao.SIM, FabTipoRespostaComunicacao.NAO})
    PERSONA_IA_CTR_REMOVER,

}
