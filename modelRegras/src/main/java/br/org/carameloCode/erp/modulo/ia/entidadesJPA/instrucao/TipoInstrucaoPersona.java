package br.org.carameloCode.erp.modulo.ia.entidadesJPA.instrucao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.ia.ComoTipoInstrucaoPersona;
import com.super_bits.modulosSB.Persistencia.registro.persistidos.EntidadeSimplesORM;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoCampo;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campo.FabTipoAtributoObjeto;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Registros criados a partir de {@link FabTipoInstrucaoPersona}.
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(tags = "Tipo de instrução da persona", plural = "Tipos de instrução da persona", icone = "fa fa-tags")
public class TipoInstrucaoPersona extends EntidadeSimplesORM implements ComoTipoInstrucaoPersona {

    @Id
    @InfoCampo(tipo = FabTipoAtributoObjeto.ID)
    private Long id;

    @InfoCampo(tipo = FabTipoAtributoObjeto.NOME)
    private String nome;

    @InfoCampo(tipo = FabTipoAtributoObjeto.DESCRITIVO, descricao = "Explica que tipo de informação deve ser cadastrada neste tipo de instrução.", label = "Descrição")
    private String descricao;

    @InfoCampo(tipo = FabTipoAtributoObjeto.ICONE)
    private String icone;

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

}
