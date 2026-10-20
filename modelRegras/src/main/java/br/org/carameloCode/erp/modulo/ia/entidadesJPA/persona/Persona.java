package br.org.carameloCode.erp.modulo.ia.entidadesJPA.persona;

import br.org.carameloCode.erp.modulo.ia.entidadesJPA.instrucao.InstrucaoIA;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.ia.ComoPersona;
import br.org.coletivoJava.fw.api.erp.ia.escopo.ERP_IA;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.ListenerEntidadePadrao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.EntidadeSimples;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
@InfoObjetoSB(plural = "Personas", tags = "Persona", icone = "fa fa-users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoEntidade")
@EntityListeners(ListenerEntidadePadrao.class)
public class Persona extends EntidadeSimples implements ComoPersona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;

    @Enumerated(EnumType.STRING)
    private ERP_IA tipoIA = ERP_IA.CLAUDE;

    @InfoCampo(tipo = FabTipoAtributoObjeto.IMG_PEQUENA)
    private String imagemAvatarPersona;

    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO, descricao = "Resumo curto de quem é a persona (ex.: consultora de marketing digital especialista em pequenos negócios).", label = "Descrição")
    private String descricao;

    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO, descricao = "Tom de voz usado pela persona (ex.: amigável, profissional, formal, descontraído, inspirador).", label = "Tom de voz")
    private String tonalidade;

    @Column(nullable = false, updatable = false, insertable = false)
    private String tipoEntidade;

    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO, descricao = "O que o conteúdo gerado deve alcançar (ex.: gerar leads, educar o cliente, divulgar uma promoção, fidelizar).", label = "Objetivo")
    private String objetivo;

    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO, descricao = "Regras que a IA deve seguir sempre, uma por linha (ex.: não citar concorrentes; sempre terminar com uma chamada para ação).", label = "Regras de resposta")
    private String regrasResposta;

    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE, descricao = "Quantidade máxima de palavras por resposta. Use 0 para não limitar.", label = "Limite de palavras")
    private int limitePalavras;

    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO, descricao = "Informações sobre a empresa, produtos, serviços e área de atuação que a IA precisa conhecer para escrever com propriedade.", label = "Contexto")
    private String contexto;

    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO, descricao = "Orientações extras para situações específicas que não se encaixam nos demais campos.", label = "Instruções adicionais")
    private String instrucoesAdicionais;

    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO, descricao = "Para quem a persona escreve (ex.: possíveis clientes, empresários, jovens, idosos). Influencia a linguagem e a complexidade do conteúdo.", label = "Público-alvo")
    private String publicoAlvo;

    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO, descricao = "Idioma em que o conteúdo será gerado (ex.: português do Brasil).", label = "Idioma")
    private String idioma;

    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO, descricao = "Formatos de conteúdo esperados, um por linha (ex.: e-mail marketing com assunto e corpo; post para Instagram com hashtags).", label = "Formatos de resposta padrão")
    private String tipoRespostasPadrao;

    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO, descricao = "Palavras ou expressões que a IA nunca deve usar, separadas por vírgula ou uma por linha.", label = "Palavras proibidas")
    private String palavrasProibidas;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_ATIVO_INATIVO, descricao = "Indica se a persona está disponível para uso na geração de conteúdo.", label = "Ativa")
    private boolean ativo;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES, descricao = "Classificação da persona (ex.: interna, externa, buyer persona).", label = "Tipo de persona")
    private String tipoPersona;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "persona_instrucaoIA",
            joinColumns = @JoinColumn(name = "persona_id"),
            inverseJoinColumns = @JoinColumn(name = "instrucaoIA_id"))
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS, descricao = "Instruções compartilhadas (perfil do cliente, dados do produto, visão de mundo, etc.) que esta persona deve conhecer.", label = "Instruções compartilhadas")
    private List<InstrucaoIA> instrucoesCompartilhadas = new ArrayList<>();

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES, descricao = "Instrução em markdown gerada automaticamente a partir dos demais campos, enviada à IA.", label = "Model file da IA")
    @InfoCampoValorLogico(nomeCalculo = "Texto Model.md")
    @Column(length = 8000)
    private String textoModelFileIA;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String getTonalidade() {
        return tonalidade;
    }

    @Override
    public void setTonalidade(String tonalidade) {
        this.tonalidade = tonalidade;
    }

    @Override
    public String getObjetivo() {
        return objetivo;
    }

    @Override
    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    @Override
    public String getRegrasResposta() {
        return regrasResposta;
    }

    @Override
    public void setRegrasResposta(String regrasResposta) {
        this.regrasResposta = regrasResposta;
    }

    @Override
    public int getLimitePalavras() {
        return limitePalavras;
    }

    @Override
    public void setLimitePalavras(int limitePalavras) {
        this.limitePalavras = limitePalavras;
    }

    @Override
    public String getContexto() {
        return contexto;
    }

    @Override
    public void setContexto(String contexto) {
        this.contexto = contexto;
    }

    @Override
    public String getInstrucoesAdicionais() {
        return instrucoesAdicionais;
    }

    @Override
    public void setInstrucoesAdicionais(String instrucoesAdicionais) {
        this.instrucoesAdicionais = instrucoesAdicionais;
    }

    @Override
    public String getTipoPersona() {
        return tipoPersona;
    }

    @Override
    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    @Override
    public String getPublicoAlvo() {
        return publicoAlvo;
    }

    @Override
    public void setPublicoAlvo(String publicoAlvo) {
        this.publicoAlvo = publicoAlvo;
    }

    @Override
    public String getIdioma() {
        return idioma;
    }

    @Override
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    @Override
    public String getTipoRespostasPadrao() {
        return tipoRespostasPadrao;
    }

    @Override
    public void setTipoRespostasPadrao(String tipoRespostasPadrao) {
        this.tipoRespostasPadrao = tipoRespostasPadrao;
    }

    @Override
    public String getPalavrasProibidas() {
        return palavrasProibidas;
    }

    @Override
    public void setPalavrasProibidas(String palavrasProibidas) {
        this.palavrasProibidas = palavrasProibidas;
    }

    @Override
    public boolean isAtivo() {
        return ativo;
    }

    @Override
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String getTextoModelFileIA() {
        return textoModelFileIA;
    }

    @Override
    public void setTextoModelFileIA(String s) {
        this.textoModelFileIA = s;
    }

    public List<InstrucaoIA> getInstrucoesCompartilhadas() {
        return instrucoesCompartilhadas;
    }

    public void setInstrucoesCompartilhadas(List<InstrucaoIA> instrucoesCompartilhadas) {
        this.instrucoesCompartilhadas = instrucoesCompartilhadas;
    }

    public String getImagemAvatarPersona() {
        return imagemAvatarPersona;
    }

    public void setImagemAvatarPersona(String imagemAvatarPersona) {
        this.imagemAvatarPersona = imagemAvatarPersona;
    }

}
