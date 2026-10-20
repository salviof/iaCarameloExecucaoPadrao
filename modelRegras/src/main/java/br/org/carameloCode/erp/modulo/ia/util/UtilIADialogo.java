package br.org.carameloCode.erp.modulo.ia.util;

import com.super_bits.modulosSB.SBCore.UtilGeral.UtilCRCJson;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonString;
import jakarta.json.JsonValue;

/**
 * Leitura e montagem do diálogo com a IA, no formato de mensagens da API
 * (/v1/messages): um array de {role, content}, onde content pode ser texto ou
 * lista de blocos.
 */
public class UtilIADialogo {

    public static final String ROLE_USUARIO = "user";
    public static final String ROLE_ASSISTENTE = "assistant";

    /**
     * Diálogo a partir do texto JSON; texto nulo ou vazio resulta em diálogo
     * vazio.
     */
    public static JsonArray getDialogoByTexto(String pDialogoJson) {
        if (pDialogoJson == null || pDialogoJson.isEmpty()) {
            return JsonValue.EMPTY_JSON_ARRAY;
        }
        return UtilCRCJson.getJsonArrayByTexto(pDialogoJson);
    }

    /**
     * Novo diálogo com a mensagem adicionada ao final.
     */
    public static JsonArray adicionarMensagem(JsonArray pDialogo, String pRole, JsonValue pConteudo) {
        return Json.createArrayBuilder(pDialogo)
                .add(Json.createObjectBuilder()
                        .add("role", pRole)
                        .add("content", pConteudo))
                .build();
    }

    /**
     * Content da última mensagem do role informado, como está no diálogo.
     */
    public static JsonValue getConteudoUltimaMensagem(JsonArray pDialogo, String pRole) {
        for (int i = pDialogo.size() - 1; i >= 0; i--) {
            JsonObject mensagem = pDialogo.getJsonObject(i);
            if (pRole.equals(mensagem.getString("role", null))) {
                return mensagem.get("content");
            }
        }
        return null;
    }

    /**
     * Texto da última mensagem do role informado; content pode ser texto ou
     * lista de blocos (usa o primeiro bloco de texto).
     */
    public static String getTextoUltimaMensagem(JsonArray pDialogo, String pRole) {
        JsonValue conteudo = getConteudoUltimaMensagem(pDialogo, pRole);
        if (conteudo == null) {
            return null;
        }
        if (conteudo.getValueType() == JsonValue.ValueType.STRING) {
            return ((JsonString) conteudo).getString();
        }
        if (conteudo.getValueType() == JsonValue.ValueType.ARRAY) {
            for (JsonValue bloco : conteudo.asJsonArray()) {
                if (bloco.getValueType() == JsonValue.ValueType.OBJECT
                        && "text".equals(bloco.asJsonObject().getString("type", null))) {
                    return bloco.asJsonObject().getString("text", null);
                }
            }
        }
        return null;
    }

    /**
     * Content a ser gravado no diálogo para a resposta da IA: se a resposta
     * for o JSON da API (/v1/messages), usa o content dela inteiro (blocos de
     * texto, thinking e tool_use precisam voltar sem alteração nas próximas
     * chamadas); se não for JSON, usa o texto como content.
     */
    public static JsonValue getConteudoRespostaApi(String pRespostaApi) {
        if (pRespostaApi == null || pRespostaApi.trim().isEmpty()) {
            return null;
        }
        try {
            JsonObject resposta = UtilCRCJson.getJsonObjectByTextoComTratamento(pRespostaApi);
            if (resposta != null && resposta.containsKey("content")) {
                return resposta.get("content");
            }
        } catch (Throwable t) {
            // não é JSON, a resposta é tratada como texto
        }
        return Json.createValue(pRespostaApi);
    }

}
