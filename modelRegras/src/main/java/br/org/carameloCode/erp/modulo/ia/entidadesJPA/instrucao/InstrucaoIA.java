package br.org.carameloCode.erp.modulo.ia.entidadesJPA.instrucao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.ia.ComoInstrucaoIA;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.ListenerEntidadePadrao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.EntidadeSimples;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

/**
 * Bloco de conhecimento reutilizável entre personas (perfil do cliente, dados
 * do produto, visão de mundo, etc.) usado pelas personas e execuções de IA.
 * Pode ser especializado por herança (SINGLE_TABLE) quando um tipo precisar de
 * atributos próprios.
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(plural = "Instruções de IA", tags = "Instrução de IA", icone = "fa fa-book-open")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoEntidade")
@EntityListeners(ListenerEntidadePadrao.class)
public class InstrucaoIA extends EntidadeSimples implements ComoInstrucaoIA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME, descricao = "Título curto que identifica a instrução (ex.: Perfil do cliente B2B, Plano Premium).", label = "Título")
    private String nome;

    @ManyToOne(targetEntity = TipoInstrucaoPersona.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, descricao = "Assunto da instrução (ex.: perfil do cliente, dados do produto, visão de mundo).", label = "Tipo de instrução")
    private TipoInstrucaoPersona tipoInstrucao;

    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO, descricao = "Conteúdo que a IA deve conhecer sobre o assunto. Escreva de forma direta e objetiva; pode usar tópicos.", label = "Conteúdo")
    @Column(columnDefinition = "TEXT")
    @InfoCampoValorLogico(nomeCalculo = "Conteúdo", somenteLeitura = false)
    private String conteudo;

    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_ATIVO_INATIVO, descricao = "Instruções inativas não são enviadas à IA, mesmo vinculadas a uma persona.", label = "Ativa")
    private boolean ativo = true;

    @Column(nullable = false, updatable = false, insertable = false)
    private String tipoEntidade;

    @InfoCampo(tipo = FabTipoAtributoObjeto.VERDADEIRO_FALSO)
    private boolean informarPlaceHoldersDisponiveis = false;

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

    public TipoInstrucaoPersona getTipoInstrucao() {
        return tipoInstrucao;
    }

    public void setTipoInstrucao(TipoInstrucaoPersona tipoInstrucao) {
        this.tipoInstrucao = tipoInstrucao;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getTipoEntidade() {
        return tipoEntidade;

    }

    public boolean isInformarPlaceHoldersDisponiveis() {
        return informarPlaceHoldersDisponiveis;
    }

    public void setInformarPlaceHoldersDisponiveis(boolean informarPlaceHoldersDisponiveis) {
        this.informarPlaceHoldersDisponiveis = informarPlaceHoldersDisponiveis;
    }

}
