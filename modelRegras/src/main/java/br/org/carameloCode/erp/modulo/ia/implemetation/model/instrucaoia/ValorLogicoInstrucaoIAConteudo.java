package br.org.carameloCode.erp.modulo.ia.implemetation.model.instrucaoia;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.ia.api.model.instrucaoia.ValorLogicoInstrucaoIA;
import br.org.carameloCode.erp.modulo.ia.api.model.instrucaoia.ValoresLogicosInstrucaoIA;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.instrucao.InstrucaoIA;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoInstrucaoIA(calculo = ValoresLogicosInstrucaoIA.CONTEUDO)
public class ValorLogicoInstrucaoIAConteudo extends ValorLogicoCalculoGenerico {

    public ValorLogicoInstrucaoIAConteudo(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public InstrucaoIA getInstrucaoIA() {
        return (InstrucaoIA) getCampoInst().getObjetoRaizDoAtributo();
    }

    @Override
    public Object getValor(Object... pEntidade) {
        return getInstrucaoIA().getConteudo();
    }

}
