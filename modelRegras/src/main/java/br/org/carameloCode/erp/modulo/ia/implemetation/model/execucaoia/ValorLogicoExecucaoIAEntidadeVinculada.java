package br.org.carameloCode.erp.modulo.ia.implemetation.model.execucaoia;

import com.super_bits.modulosSB.SBCore.modulos.objetos.calculos.ValorLogicoCalculoGenerico;
import br.org.carameloCode.erp.modulo.ia.api.model.execucaoia.ValorLogicoExecucaoIA;
import br.org.carameloCode.erp.modulo.ia.api.model.execucaoia.ValoresLogicosExecucaoIA;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.execucao.ExecucaoIA;
import com.super_bits.modulosSB.Persistencia.dao.UtilSBPersistencia;
import com.super_bits.modulosSB.SBCore.modulos.objetos.InfoCampos.campoInstanciado.ItfCampoInstanciado;
import com.super_bits.modulosSB.SBCore.modulos.objetos.MapaObjetosProjetoAtual;
import com.super_bits.modulosSB.SBCore.modulos.objetos.entidade.basico.ComoEntidadeSimples;
import javax.persistence.EntityManager;

@ValorLogicoExecucaoIA(calculo = ValoresLogicosExecucaoIA.ENTIDADEVINCULADA)
public class ValorLogicoExecucaoIAEntidadeVinculada
        extends
        ValorLogicoCalculoGenerico {

    public ValorLogicoExecucaoIAEntidadeVinculada(ItfCampoInstanciado pCampo) {
        super(pCampo);
    }

    @Override
    public Object getValor(Object... pEntidade) {
        if (getExecucaoIA().getEntidadeVinculada() == null) {
            if (getExecucaoIA().getEntidadeAlvoNome() != null && (getExecucaoIA().getEntidadeAlvoCodigo() != null)) {
                EntityManager em = UtilSBPersistencia.getEMPadraoNovo();
                try {
                    ComoEntidadeSimples entidade = UtilSBPersistencia.getRegistroByID(MapaObjetosProjetoAtual.getClasseDoObjetoByNome(getExecucaoIA().getEntidadeAlvoNome()), Long.valueOf(getExecucaoIA().getEntidadeAlvoCodigo()), em);

                    getExecucaoIA().setEntidadeVinculada(entidade);
                } finally {
                    UtilSBPersistencia.fecharEM(em);
                }
            }

        }
        return getExecucaoIA().getEntidadeVinculada();
    }

    public ExecucaoIA getExecucaoIA() {
        return (ExecucaoIA) getCampoInst().getObjetoRaizDoAtributo();
    }
}
