package br.org.carameloCode.erp.modulo.ia.implemetation.model.execucaoia;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.ia.api.model.execucaoia.ValorLogicoExecucaoIA;
import br.org.carameloCode.erp.modulo.ia.api.model.execucaoia.ValoresLogicosExecucaoIA;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucao.ExecucaoIA;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoExecucaoIA(calculo = ValoresLogicosExecucaoIA.VALORGASTO)
public class ValorLogicoExecucaoIAValorGasto extends ValorLogicoCalculoGenerico {

    public ValorLogicoExecucaoIAValorGasto(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    public ExecucaoIA getExecucaoIA() {
        return (ExecucaoIA) getCampoInst().getObjetoRaizDoAtributo();
    }
    //Valor cobrado pela gestão dos tokens na api
    private double fatorManutencaoAPI = 0;

    //Preço em dólar por milhão de tokens do modelo padrão (claude-sonnet-5-5)
    private static final double PRECO_USD_MILHAO_TOKENS_INPUT = 2.00;
    private static final double PRECO_USD_MILHAO_TOKENS_OUTPUT = 10.00;

    //Cotação usada para converter o custo em dólar para real
    private double cotacaoDolarReal = 5.50;

    @Override
    public Object getValor(Object... pEntidade) {
        double tokensInput = getExecucaoIA().getTokensInput();
        double tokensOutput = getExecucaoIA().getTokensOutput();
        double custoDolar = tokensInput / 1_000_000 * PRECO_USD_MILHAO_TOKENS_INPUT
                + tokensOutput / 1_000_000 * PRECO_USD_MILHAO_TOKENS_OUTPUT;
        //fatorManutencaoAPI -> Porcentegem a mais que será cobrado do cliente, referente a hospedagem e manutençao do serviço de IA
        //fator 0 = sem acréscimo, 0.5 = +50%, 1 = dobra o valor
        double valor = custoDolar * cotacaoDolarReal * (1 + fatorManutencaoAPI);
        getExecucaoIA().setValorGasto(valor);
        return getExecucaoIA().getValorGasto();
    }

}
