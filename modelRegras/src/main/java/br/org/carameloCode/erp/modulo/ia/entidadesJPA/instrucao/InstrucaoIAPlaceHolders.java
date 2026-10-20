package br.org.carameloCode.erp.modulo.ia.entidadesJPA.instrucao;

import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.anotacoes.InfoObjetoSB;
import javax.persistence.Entity;

/**
 * Instrução de IA que informa os placeholders disponíveis (ex.:
 * [contatoPrincipal.nome], [dados.NOME_DADO]), para a IA utilizá-los no
 * conteúdo gerado em vez de valores fixos.
 *
 * @author salvio
 */
@Entity
@InfoObjetoSB(plural = "Instruções de placeholders", tags = "Instrução de placeholders", icone = "fa fa-code")
public class InstrucaoIAPlaceHolders extends InstrucaoIA {

}
