/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.org.carameloCode.erp.modulo.ia.controller;

import br.org.carameloCode.erp.modulo.ia.acoes.FabAcaoIAExecucao;
import br.org.carameloCode.erp.modulo.ia.acoes.InfoAcaoIAExecucao;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucao.ExecucaoIA;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucao.TipoExecucaoIA;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.instrucao.InstrucaoIA;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.persona.Persona;
import br.org.coletivoJava.integracoes.claude.api.FabIAClaude;
import com.super_bits.modulos.SBAcessosModel.controller.resposta.RespostaComGestaoEMRegraDeNegocioPadrao;
import com.super_bits.modulosSB.Persistencia.dao.ControllerAbstratoSBPersistencia;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCJson;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCStringValidador;
import com.super_bits.modulosSB.SBCore.integracao.libRestClient.WS.conexaoWebServiceClient.ItfRespostaWebServiceSimples;
import com.super_bits.modulosSB.SBCore.modulos.Controller.Interfaces.ItfRespostaAcaoDoSistema;
import com.super_bits.modulosSB.SBCore.modulos.TratamentoDeErros.ErroRegraDeNegocio;
import jakarta.json.JsonObject;

/**
 *
 * @author salvio
 */
public class ModuloExecucaoAcao extends ControllerAbstratoSBPersistencia {

    @InfoAcaoIAExecucao(acao = FabAcaoIAExecucao.PERSONA_IA_CTR_SALVAR_MERGE)
    public static ItfRespostaAcaoDoSistema personaIaAtualizar(Persona pPersonaIA) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pPersonaIA), pPersonaIA) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                setRetorno(atualizarEntidade(pPersonaIA));
            }
        }.getResposta();
    }

    @InfoAcaoIAExecucao(acao = FabAcaoIAExecucao.INSTRUCAO_IA_CTR_SALVAR_MERGE)
    public static ItfRespostaAcaoDoSistema instrucaoIaAtualizar(InstrucaoIA pInstrucao) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pInstrucao), pInstrucao) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                setRetorno(atualizarEntidade(pInstrucao));
            }
        }.getResposta();
    }

    @InfoAcaoIAExecucao(acao = FabAcaoIAExecucao.TIPO_EXECUCAO_IA_CTR_SALVAR_MERGE)
    public static ItfRespostaAcaoDoSistema tipoExecucaoIaAtualizar(TipoExecucaoIA pTipoExecucao) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pTipoExecucao), pTipoExecucao) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                setRetorno(atualizarEntidade(pTipoExecucao));
            }
        }.getResposta();
    }

    @InfoAcaoIAExecucao(acao = FabAcaoIAExecucao.PERSONA_IA_CTR_REMOVER)
    public static ItfRespostaAcaoDoSistema personaIaRemover(Persona pPersonaIA) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pPersonaIA), pPersonaIA) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                removerEntidade(pPersonaIA);
            }
        }.getResposta();
    }

    /**
     * Salva a execução (os valores lógicos adicionam a novaMensagem ao
     * dialogoJson e geram o systemPromptMD), envia o diálogo à IA e grava a
     * resposta, que é adicionada ao diálogo como mensagem do assistente.
     *
     * O hash do diálogo já respondido evita chamar a IA de novo quando não há
     * mensagem nova.
     */
    @InfoAcaoIAExecucao(acao = FabAcaoIAExecucao.EXECUCAO_IA_CTR_EXECUTAR_DIALOGO)
    public static ItfRespostaAcaoDoSistema executarDialogo(ExecucaoIA pExecucao) {
        return new RespostaComGestaoEMRegraDeNegocioPadrao(getNovaRespostaAutorizaChecaNulo(pExecucao), pExecucao) {
            @Override
            public void regraDeNegocio() throws ErroRegraDeNegocio {
                //Persona é usada em valor lógico, garante carregamento da entidade

                //Totais já gravados, o objeto recebido pode estar desatualizado
                long tokensInputGravados = 0;
                long tokensOutputGravados = 0;

                if (pExecucao.getId() == null) {
                    throw new ErroRegraDeNegocio("Inicie primeiro um dialogo, antes de executar");
                }
                ///Carrega direto no banco reaproveitando só a nova mensagem, que é o input do usuário evitando lasyExceoptions nos valores logicos, e processamento indevido
                ExecucaoIA execucao = loadEntidade(pExecucao);
                if (execucao == null) {
                    throw new ErroRegraDeNegocio("Execução não encontrada");
                }
                tokensInputGravados = execucao.getTokensInput();
                tokensOutputGravados = execucao.getTokensOutput();
                String novaFrase = pExecucao.getNovaMensagem();

                execucao.setNovaMensagem(novaFrase);
                execucao = atualizarEntidade(execucao);
                execucao.setTokensInput(Math.max(execucao.getTokensInput(), tokensInputGravados));
                execucao.setTokensOutput(Math.max(execucao.getTokensOutput(), tokensOutputGravados));
                String dialogo = execucao.getDialogoJson();
                if (dialogo == null || UtilCRCJson.getJsonArrayByTexto(dialogo).isEmpty()) {
                    throw new ErroRegraDeNegocio("Não existe dialogo regustrado");
                }
                if (String.valueOf(dialogo.hashCode()).equals(execucao.getHashUltimaExecucaoSucesso())) {
                    addAviso("O diálogo já foi processado pela IA");
                    setRetorno(execucao);
                    return;
                }
                String outputConfig = null;
                if (execucao.getTipoExecucao() != null
                        && !UtilCRCStringValidador.isNuloOuEmbranco(execucao.getTipoExecucao().getStructuredOutputConfig())) {
                    outputConfig = "{\"format\":" + execucao.getTipoExecucao().getStructuredOutputConfig() + "}";
                }
                //modelo vazio usa o MODELO_PADRAO da integração; não passar null literal no primeiro
                //parâmetro, o getAcao(ComoUsuario, Object...) seria escolhido e os parâmetros deslocados
                String modelo = "";
                ItfRespostaWebServiceSimples resposta = FabIAClaude.DIALOGO_ENVIAR
                        .getAcao(modelo, execucao.getSystemPromptMD(), dialogo, outputConfig).getResposta();
                if (resposta == null || !resposta.isSucesso()) {
                    throw new ErroRegraDeNegocio("Falha comunicando com a IA - "
                            + (resposta == null ? "sem resposta" : resposta.getRespostaTexto()));
                }
                JsonObject jsonResposta = resposta.getRespostaComoObjetoJson();
                String motivoParada = jsonResposta.getString("stop_reason", "");
                if ("refusal".equals(motivoParada) || "max_tokens".equals(motivoParada)) {
                    throw new ErroRegraDeNegocio("A IA não concluiu a resposta (" + motivoParada + ")");
                }
                //Soma os tokens desta chamada ao total já usado no diálogo
                JsonObject uso = jsonResposta.getJsonObject("usage");
                if (uso != null) {
                    execucao.setTokensInput(execucao.getTokensInput()
                            + uso.getInt("input_tokens", 0)
                            + uso.getInt("cache_creation_input_tokens", 0)
                            + uso.getInt("cache_read_input_tokens", 0));
                    execucao.setTokensOutput(execucao.getTokensOutput() + uso.getInt("output_tokens", 0));
                }
                execucao.setNovaResposta(resposta.getRespostaTexto());
                //Ao atualizar, a novaResposta é adicionada ao dialogoJson
                execucao = atualizarEntidade(execucao);
                execucao.setHashUltimaExecucaoSucesso(String.valueOf(execucao.getDialogoJson().hashCode()));
                setRetorno(execucao);
            }
        }.getResposta();
    }

}
