package br.org.carameloCode.erp.modulo.ia.implemetation.model.execucaoia;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.ia.api.model.execucaoia.ValorLogicoExecucaoIA;
import br.org.carameloCode.erp.modulo.ia.api.model.execucaoia.ValoresLogicosExecucaoIA;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucao.ExecucaoIA;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoExecucaoIA(calculo = ValoresLogicosExecucaoIA.PERSONA)
public class ValorLogicoExecucaoIAPersona extends ValorLogicoCalculoGenerico {

    public ValorLogicoExecucaoIAPersona(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return getExecucaoIA().getPersona();
    }

    public ExecucaoIA getExecucaoIA() {
        return (ExecucaoIA) getCampoInst().getObjetoRaizDoAtributo();
    }
}
