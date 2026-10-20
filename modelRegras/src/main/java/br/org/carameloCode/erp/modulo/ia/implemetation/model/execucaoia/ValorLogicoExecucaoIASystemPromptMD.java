package br.org.carameloCode.erp.modulo.ia.implemetation.model.execucaoia;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.ia.api.model.execucaoia.ValorLogicoExecucaoIA;
import br.org.carameloCode.erp.modulo.ia.api.model.execucaoia.ValoresLogicosExecucaoIA;
import br.org.carameloCode.erp.modulo.ia.contexto.instrucaoia.CPInstrucaoIA;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucao.ExecucaoIA;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.instrucao.InstrucaoIA;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.instrucao.InstrucaoIAPlaceHolders;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.persona.Persona;
import br.org.carameloCode.erp.modulo.ia.util.UtilIAModelFile;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

@ValorLogicoExecucaoIA(calculo = ValoresLogicosExecucaoIA.SYSTEMPROMPTMD)
public class ValorLogicoExecucaoIASystemPromptMD
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoExecucaoIASystemPromptMD(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        ExecucaoIA execucao = getExecucaoIA();

        EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
        try {
            if (execucao.getPersona() == null) {
                return execucao.getSystemPromptMD();
            }
            List<InstrucaoIA> instrucoes = new ArrayList<>();
            Persona persona = UtilSBPersistencia.loadEntidade(execucao.getPersona(), em);
            if (persona.getInstrucoesCompartilhadas() != null) {
                instrucoes.addAll(execucao.getPersona().getInstrucoesCompartilhadas());
            }
            if (execucao.getInstrucoes() != null) {
                for (InstrucaoIA instrucao : execucao.getInstrucoes()) {
                    if (!instrucoes.contains(instrucao)) {
                        instrucoes.add(instrucao);
                    }
                }
            }

            if (execucao.getTipoExecucao().isHabilitarPLaceHolders()) {
                InstrucaoIAPlaceHolders instrucao = new InstrucaoIAPlaceHolders();
                instrucao.getCPinst(CPInstrucaoIA.conteudo).getValor();
                instrucoes.add(instrucao);
            }

            execucao.setSystemPromptMD(
                    UtilIAModelFile.gerarTextoModelFile(persona, instrucoes, getExecucaoIA().getTipoExecucao().getInstrucaoPrincipal()));
        } finally {
            UtilSBPersistencia.fecharEM(em);
        }
        return execucao.getSystemPromptMD();
    }

    public ExecucaoIA getExecucaoIA() {
        return (ExecucaoIA) getCampoInst().getObjetoRaizDoAtributo();
    }
}
