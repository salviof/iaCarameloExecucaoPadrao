package br.org.carameloCode.erp.modulo.ia.util;

import br.org.carameloCode.erp.modulo.ia.entidadesJPA.instrucao.InstrucaoIA;
import br.org.carameloCode.erp.modulo.ia.entidadesJPA.persona.Persona;
import java.util.List;

/**
 * Geração do model file (markdown) da persona, movido de UtilCRM_IA
 * (carameloCodeCRM).
 */
public class UtilIAModelFile {

    private static void adicionarSecao(StringBuilder pTexto, String pTitulo, String pConteudo) {
        if (!temTexto(pConteudo)) {
            return;
        }
        pTexto.append("## ").append(pTitulo).append("\n\n")
                .append(pConteudo.trim()).append("\n\n");
    }

    private static boolean temTexto(String pTexto) {
        return pTexto != null && !pTexto.trim().isEmpty();
    }

    /**
     * Cada item (separado por pSeparador) vira um tópico; itens que já começam
     * com marcador de lista são mantidos como estão.
     */
    private static void adicionarSecaoLista(StringBuilder pTexto, String pTitulo, String pConteudo, String pSeparador) {
        if (!temTexto(pConteudo)) {
            return;
        }
        pTexto.append("## ").append(pTitulo).append("\n\n");
        for (String item : pConteudo.split(pSeparador)) {
            if (!temTexto(item)) {
                continue;
            }
            String itemLimpo = item.trim();
            if (itemLimpo.startsWith("-") || itemLimpo.startsWith("*") || itemLimpo.matches("^\\d+[.)].*")) {
                pTexto.append(itemLimpo).append("\n");
            } else {
                pTexto.append("- ").append(itemLimpo).append("\n");
            }
        }
        pTexto.append("\n");
    }

    /**
     * Monta o model file (markdown) da persona, incluindo as instruções
     * informadas na seção "Base de conhecimento".
     */
    public static String gerarTextoModelFile(Persona persona, List<InstrucaoIA> pInstrucoes, String pInstrucaoPrincipal) {
        StringBuilder textoModel = new StringBuilder();

        textoModel.append("# Persona: ")
                .append(temTexto(persona.getNome()) ? persona.getNome().trim() : "Sem nome")
                .append("\n\n");
        if (temTexto(persona.getDescricao())) {
            textoModel.append("> ").append(persona.getDescricao().trim().replace("\n", "\n> ")).append("\n\n");
        }
        textoModel.append("Você é a persona descrita abaixo. Siga rigorosamente todas as seções deste documento ao gerar qualquer conteúdo.\n\n");

        adicionarSecao(textoModel, "Tipo de persona", persona.getTipoPersona());
        adicionarSecao(textoModel, "Objetivo", persona.getObjetivo());
        adicionarSecao(textoModel, "Público-alvo", persona.getPublicoAlvo());
        adicionarSecao(textoModel, "Contexto", persona.getContexto());
        adicionarSecao(textoModel, "Tom de voz", persona.getTonalidade());
        if (temTexto(persona.getIdioma())) {
            adicionarSecao(textoModel, "Idioma", "Responda sempre em " + persona.getIdioma().trim()
                    + ", a menos que seja explicitamente solicitado outro idioma.");
        }
        adicionarSecaoLista(textoModel, "Regras de resposta", persona.getRegrasResposta(), "\n");
        if (pInstrucaoPrincipal != null) {
            adicionarSecaoLista(textoModel, "Instrucao principal", pInstrucaoPrincipal, "\n");
        }

        adicionarSecaoLista(textoModel, "Formatos de resposta padrão", persona.getTipoRespostasPadrao(), "\n");

        StringBuilder restricoes = new StringBuilder();
        if (persona.getLimitePalavras() > 0) {
            restricoes.append("- Limite máximo de ")
                    .append(persona.getLimitePalavras())
                    .append(" palavras por resposta.\n");
        }
        if (temTexto(persona.getPalavrasProibidas())) {
            restricoes.append("- Nunca utilize as seguintes palavras ou expressões:\n");
            for (String palavra : persona.getPalavrasProibidas().split("[,;\\n]")) {
                if (temTexto(palavra)) {
                    restricoes.append("  - ").append(palavra.trim()).append("\n");
                }
            }
        }
        if (restricoes.length() > 0) {
            textoModel.append("## Restrições\n\n").append(restricoes).append("\n");
        }

        adicionarSecao(textoModel, "Instruções adicionais", persona.getInstrucoesAdicionais());

        if (pInstrucoes != null) {
            StringBuilder conhecimento = new StringBuilder();
            for (InstrucaoIA instrucao : pInstrucoes) {
                if (instrucao == null || !instrucao.isAtivo() || !temTexto(instrucao.getConteudo())) {
                    continue;
                }
                conhecimento.append("### ");
                if (instrucao.getTipoInstrucao() != null && temTexto(instrucao.getTipoInstrucao().getNome())) {
                    conhecimento.append(instrucao.getTipoInstrucao().getNome().trim()).append(": ");
                }
                conhecimento.append(temTexto(instrucao.getNome()) ? instrucao.getNome().trim() : "Sem título")
                        .append("\n\n")
                        .append(instrucao.getConteudo().trim()).append("\n\n");
            }
            if (conhecimento.length() > 0) {
                textoModel.append("## Base de conhecimento\n\n")
                        .append("Use as informações abaixo como referência ao produzir o conteúdo.\n\n")
                        .append(conhecimento);
            }
        }

        return textoModel.toString().trim();
    }

}
