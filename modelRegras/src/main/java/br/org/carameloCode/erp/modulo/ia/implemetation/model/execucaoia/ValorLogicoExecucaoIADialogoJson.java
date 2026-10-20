package br.org.carameloCode.erp.modulo.ia.implemetation.model.execucaoia;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.ia.api.model.execucaoia.ValorLogicoExecucaoIA;
import br.org.carameloCode.erp.modulo.ia.api.model.execucaoia.ValoresLogicosExecucaoIA;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucao.ExecucaoIA;
import br.org.carameloCode.erp.modulo.ia.util.UtilIADialogo;
import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCJson;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonValue;

@ValorLogicoExecucaoIA(calculo = ValoresLogicosExecucaoIA.DIALOGOJSON)
public class ValorLogicoExecucaoIADialogoJson
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoExecucaoIADialogoJson(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    /**
     * Adiciona a novaMensagem (role user) e depois a novaResposta (role
     * assistant) ao final do diálogo, no formato {role, content} da API. Não
     * duplica quando o cálculo roda de novo: cada uma só é adicionada se
     * diferir da última mensagem do mesmo role.
     */
    @Override
    public Object getValor(Object... pEntidade) {
        JsonArray dialogo = UtilIADialogo.getDialogoByTexto(getExecucaoIA().getDialogoJson());
        String ultimaMensagem = getExecucaoIA().getNovaMensagem();
        if (ultimaMensagem != null && !ultimaMensagem.trim().isEmpty()
                && !ultimaMensagem.equals(UtilIADialogo.getTextoUltimaMensagem(dialogo, UtilIADialogo.ROLE_USUARIO))) {
            dialogo = UtilIADialogo.adicionarMensagem(dialogo, UtilIADialogo.ROLE_USUARIO, Json.createValue(ultimaMensagem));
        }
        JsonValue conteudoResposta = UtilIADialogo.getConteudoRespostaApi(getExecucaoIA().getNovaResposta());
        if (conteudoResposta != null
                && !conteudoResposta.equals(UtilIADialogo.getConteudoUltimaMensagem(dialogo, UtilIADialogo.ROLE_ASSISTENTE))) {
            dialogo = UtilIADialogo.adicionarMensagem(dialogo, UtilIADialogo.ROLE_ASSISTENTE, conteudoResposta);
        }
        getExecucaoIA().setDialogoJson(UtilCRCJson.getTextoByJsonArray(dialogo));
        return getExecucaoIA().getDialogoJson();
    }

    public ExecucaoIA getExecucaoIA() {
        return (ExecucaoIA) getCampoInst().getObjetoRaizDoAtributo();
    }
}
