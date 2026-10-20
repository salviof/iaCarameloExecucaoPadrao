package br.org.carameloCode.erp.modulo.ia.implemetation.model.persona;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.ia.api.model.persona.ValorLogicoPersona;
import br.org.carameloCode.erp.modulo.ia.api.model.persona.ValoresLogicosPersona;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.persona.Persona;
import br.org.carameloCode.erp.modulo.ia.util.UtilIAModelFile;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;

/**
 * Gera o model file (markdown) que descreve a persona para a IA, usado como
 * instrução de sistema na geração de conteúdo.
 */
@ValorLogicoPersona(calculo = ValoresLogicosPersona.TEXTOMODELFILEIA)
public class ValorLogicoPersonaTextoModelFileIA
        extends
        ValorLogicoCalculoGenerico {

    private static final int TAMANHO_MAXIMO = 8000;

    public ValorLogicoPersonaTextoModelFileIA(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        Persona persona = getPersona();
        String texto = UtilIAModelFile.gerarTextoModelFile(persona, persona.getInstrucoesCompartilhadas(), null);
        if (texto.length() > TAMANHO_MAXIMO) {
            texto = texto.substring(0, TAMANHO_MAXIMO);
        }
        persona.setTextoModelFileIA(texto);
        return persona.getTextoModelFileIA();
    }

    public Persona getPersona() {
        return (Persona) getCampoInst().getObjetoRaizDoAtributo();
    }
}
