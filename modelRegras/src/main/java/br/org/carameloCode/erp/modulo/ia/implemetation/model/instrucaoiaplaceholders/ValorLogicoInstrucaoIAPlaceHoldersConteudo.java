package br.org.carameloCode.erp.modulo.ia.implemetation.model.instrucaoiaplaceholders;

import com.super_bits.modulosSB.SBCore.UtilGeral.stringSubstituicao.MapaSubstituicao;
import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.ia.api.model.instrucaoiaplaceholders.ValorLogicoInstrucaoIAPlaceHolders;
import br.org.carameloCode.erp.modulo.ia.api.model.instrucaoiaplaceholders.ValoresLogicosInstrucaoIAPlaceHolders;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.instrucao.InstrucaoIAPlaceHolders;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

@ValorLogicoInstrucaoIAPlaceHolders(calculo = ValoresLogicosInstrucaoIAPlaceHolders.CONTEUDO)
public class ValorLogicoInstrucaoIAPlaceHoldersConteudo
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoInstrucaoIAPlaceHoldersConteudo(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {

        if (!isCacheAtivado()) {
            StringBuilder conteudo = new StringBuilder();
            conteudo.append("Utilize SOMENTE ESSES  placeholders para acessar dados do usuário, não utilize fisrtname direto, os placeholders que acessam os dados do contato são apenas esses, e serão traduzidos ao enviar para a api, eles vão ser traduzidos, para outras plataformas, e precisam ser gerados desta forma  que iniciam com [contatoPrincipal.] são referente ao contato, [dados.] são dados extras , e direto como [nome] são referente a empresa");
            getPlaceHolders().getpalavrasChave().stream().
                    map(cont -> "," + cont).
                    forEach(conteudo::append);
            getInstrucaoIAPlaceHolders().setConteudo(conteudo.toString());
            setCacheSegundosPadrao(999999999);
        }
        return getInstrucaoIAPlaceHolders().getConteudo();
    }

    /**
     * Placeholders disponíveis; no CRM eram obtidos de
     * UtilMauticSinc.getPlaceHolders(), sobrescreva para informá-los.
     */
    protected MapaSubstituicao getPlaceHolders() {
        return new MapaSubstituicao();
    }

    public InstrucaoIAPlaceHolders getInstrucaoIAPlaceHolders() {
        return (InstrucaoIAPlaceHolders) getCampoInst()
                .getObjetoRaizDoAtributo();
    }
}
