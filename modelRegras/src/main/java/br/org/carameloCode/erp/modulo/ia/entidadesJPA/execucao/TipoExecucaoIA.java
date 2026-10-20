package br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.ia.ComoTipoExecucaoIA;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoVerdadeiroOuFalso;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Tipo de execução de IA. Os tipos padrão são criados por
 * {@link FabTipoExecucaoIA}, e novos tipos podem ser cadastrados pelo usuário.
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(tags = "Tipo de execução de IA", plural = "Tipos de execução de IA", icone = "fa fa-gears")
public class TipoExecucaoIA extends EntidadeSimplesORM implements ComoTipoExecucaoIA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;

    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO, descricao = "Para que serve este tipo de execução e que conteúdo a IA deve produzir.", label = "Descrição")
    private String descricao;

    @InfoCampo(tipo = FabTipoAtributoObjeto.ICONE)
    private String icone;

    @Column(columnDefinition = "TEXT")
    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO)
    private String instrucaoPrincipal;

    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO, descricao = "Formato de saída estruturada em JSON (objeto format do output_config, com type json_schema e schema). Vazio: a IA responde em texto livre.", label = "Formato de saída (structured output)")
    @Column(columnDefinition = "TEXT")
    private String structuredOutputConfig;
    @Column(columnDefinition = "TEXT")

    private String outputConfigOpenIA;
    @Column(columnDefinition = "TEXT")
    private String outputConfigCpilot;
    @Column(columnDefinition = "TEXT")
    private String outputConfigDeepSeek;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    @InfoCampoVerdadeiroOuFalso()
    private boolean habilitarPLaceHolders = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public String getStructuredOutputConfig() {
        return structuredOutputConfig;
    }

    public void setStructuredOutputConfig(String structuredOutputConfig) {
        this.structuredOutputConfig = structuredOutputConfig;
    }

    public String getInstrucaoPrincipal() {
        return instrucaoPrincipal;
    }

    public void setInstrucaoPrincipal(String instrucaoPrincipal) {
        this.instrucaoPrincipal = instrucaoPrincipal;
    }

    public String getOutputConfigOpenIA() {
        return outputConfigOpenIA;
    }

    public void setOutputConfigOpenIA(String outputConfigOpenIA) {
        this.outputConfigOpenIA = outputConfigOpenIA;
    }

    public String getOutputConfigCpilot() {
        return outputConfigCpilot;
    }

    public void setOutputConfigCpilot(String outputConfigCpilot) {
        this.outputConfigCpilot = outputConfigCpilot;
    }

    public String getOutputConfigDeepSeek() {
        return outputConfigDeepSeek;
    }

    public void setOutputConfigDeepSeek(String outputConfigDeepSeek) {
        this.outputConfigDeepSeek = outputConfigDeepSeek;
    }

    public boolean isHabilitarPLaceHolders() {
        return habilitarPLaceHolders;
    }

    public void setHabilitarPLaceHolders(boolean habilitarPLaceHolders) {
        this.habilitarPLaceHolders = habilitarPLaceHolders;
    }

}
