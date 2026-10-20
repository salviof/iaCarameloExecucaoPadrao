package br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucao;

import br.org.carameloCode.erp.modulo.ia.entidadesJPA.instrucao.InstrucaoIA;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.persona.Persona;

import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.ia.ComoExecucaoIA;
import com.super_bits.modulos.SBAcessosModel.model.UsuarioSB;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.ListenerEntidadePadrao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampoValorLogico;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import com.super_bits.modulosSB.SBCore.modulos.objetos.registro.EntidadeSimples;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * Registro de uma chamada à IA: quem executou, com qual persona e instruções, o
 * model file enviado e o consumo de tokens.
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(plural = "Execuções de IA", tags = "Execução de IA", icone = "fa fa-robot")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoEntidade")
@EntityListeners(ListenerEntidadePadrao.class)
public class ExecucaoIA extends EntidadeSimples implements ComoExecucaoIA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;

    @Column(nullable = false, updatable = false, insertable = false)
    private String tipoEntidade;

    @Temporal(TemporalType.TIMESTAMP)
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_DATAINSERCAO, label = "Data e hora")
    private Date dataHora;

    @ManyToOne(targetEntity = UsuarioSB.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.REG_USUARIO_INSERCAO, descricao = "Usuário que solicitou a execução.", label = "Usuário")
    private UsuarioSB usuario;

    @ManyToOne(targetEntity = TipoExecucaoIA.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, descricao = "Tipo de conteúdo gerado; define o formato de saída esperado da IA.", label = "Tipo de execução")
    private TipoExecucaoIA tipoExecucao;

    @ManyToOne(targetEntity = Persona.class)
    @InfoCampo(tipo = FabTipoAtributoObjeto.OBJETO_DE_UMA_LISTA, descricao = "Persona usada para gerar o conteúdo.", label = "Persona")
    @InfoCampoValorLogico(nomeCalculo = "Persona")
    private Persona persona;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    private String entidadeAlvoNome;
    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    private String entidadeAlvoCodigo;

    @Transient
    @InfoCampoValorLogico(nomeCalculo = "Entidade vinculada")
    private ComoEntidadeSimples entidadeVinculada;

    @ManyToMany
    @JoinTable(name = "execucaoIA_instrucaoIA",
            joinColumns = @JoinColumn(name = "execucaoIA_id"),
            inverseJoinColumns = @JoinColumn(name = "instrucaoIA_id"))
    @InfoCampo(tipo = FabTipoAtributoObjeto.LISTA_OBJETOS_PUBLICOS, descricao = "Instruções enviadas nesta execução, além das já vinculadas à persona.", label = "Instruções")
    private List<InstrucaoIA> instrucoes = new ArrayList<>();

    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO, descricao = "Nova mensagem do usuário, adicionada ao final do diálogo com role user.", label = "Nova mensagem")
    @Column(columnDefinition = "TEXT")
    private String novaMensagem;

    @Column(columnDefinition = "TEXT")
    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO, descricao = "Último JSON de resposta da IA, adicionado ao final do diálogo com role assistant.", label = "Nova resposta")
    private String novaResposta;

    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO, descricao = "Model file (markdown) enviado à IA, gerado a partir da persona e das instruções.", label = "Model file da IA")
    @InfoCampoValorLogico(nomeCalculo = "Texto Model.md")
    @Column(columnDefinition = "TEXT")
    private String dialogoJson;

    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO, descricao = "Model file (markdown) enviado à IA, gerado a partir da persona e das instruções.", label = "Model file da IA")
    @InfoCampoValorLogico(nomeCalculo = "Texto Model.md")
    @Column(columnDefinition = "TEXT")
    private String systemPromptMD;

    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE, descricao = "Tokens enviados à IA (model file, instruções e mensagem).", label = "Tokens de entrada")
    private long tokensInput;

    @InfoCampo(tipo = FabTipoAtributoObjeto.QUANTIDADE, descricao = "Tokens gerados pela IA na resposta.", label = "Tokens de saída")
    private long tokensOutput;

    @InfoCampo(tipo = FabTipoAtributoObjeto.MOEDA_REAL, descricao = "Custo da execução, calculado a partir dos tokens de entrada e saída.", label = "Valor gasto")
    @InfoCampoValorLogico(nomeCalculo = "Valor Gasto")
    private double valorGasto;

    @InfoCampo(tipo = FabTipoAtributoObjeto.TEXTO_SIMPLES)
    public String hashUltimaExecucaoSucesso;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public UsuarioSB getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioSB usuario) {
        this.usuario = usuario;
    }

    public TipoExecucaoIA getTipoExecucao() {
        return tipoExecucao;
    }

    public void setTipoExecucao(TipoExecucaoIA tipoExecucao) {
        this.tipoExecucao = tipoExecucao;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<InstrucaoIA> getInstrucoes() {
        return instrucoes;
    }

    public void setInstrucoes(List<InstrucaoIA> instrucoes) {
        this.instrucoes = instrucoes;
    }

    public String getNovaMensagem() {
        return novaMensagem;
    }

    public void setNovaMensagem(String novaMensagem) {
        this.novaMensagem = novaMensagem;
    }

    public String getSystemPromptMD() {
        return systemPromptMD;
    }

    public void setSystemPromptMD(String systemPromptMD) {
        this.systemPromptMD = systemPromptMD;
    }

    public long getTokensInput() {
        return tokensInput;
    }

    public void setTokensInput(long tokensInput) {
        this.tokensInput = tokensInput;
    }

    public long getTokensOutput() {
        return tokensOutput;
    }

    public void setTokensOutput(long tokensOutput) {
        this.tokensOutput = tokensOutput;
    }

    public double getValorGasto() {
        return valorGasto;
    }

    public void setValorGasto(double valorGasto) {
        this.valorGasto = valorGasto;
    }

    public String getDialogoJson() {
        return dialogoJson;
    }

    public void setDialogoJson(String dialogoJson) {
        this.dialogoJson = dialogoJson;
    }

    public String getNovaResposta() {
        return novaResposta;
    }

    public void setNovaResposta(String novaResposta) {
        this.novaResposta = novaResposta;
    }

    public String getTipoEntidade() {
        return tipoEntidade;
    }

    public String getHashUltimaExecucaoSucesso() {
        return hashUltimaExecucaoSucesso;
    }

    public void setHashUltimaExecucaoSucesso(String hashUltimaExecucaoSucesso) {
        this.hashUltimaExecucaoSucesso = hashUltimaExecucaoSucesso;
    }

    public String getEntidadeAlvoNome() {
        return entidadeAlvoNome;
    }

    public void setEntidadeAlvoNome(String entidadeAlvoNome) {
        this.entidadeAlvoNome = entidadeAlvoNome;
    }

    public String getEntidadeAlvoCodigo() {
        return entidadeAlvoCodigo;
    }

    public void setEntidadeAlvoCodigo(String entidadeAlvoCodigo) {
        this.entidadeAlvoCodigo = entidadeAlvoCodigo;
    }

    public ComoEntidadeSimples getEntidadeVinculada() {
        return entidadeVinculada;
    }

    public void setEntidadeVinculada(ComoEntidadeSimples entidadeVinculada) {
        this.entidadeVinculada = entidadeVinculada;
    }

}
