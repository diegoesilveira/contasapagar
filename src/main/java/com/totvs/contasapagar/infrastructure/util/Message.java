package com.totvs.contasapagar.infrastructure.util;

public class Message {
    public static final String CONTA_NAO_ENCONTRADA = "Conta não encontrada: ";
    public static final String CONTA_JA_PAGA = "Conta já paga: ";
    public static final String SITUACAO_INVALIDA = "Situação inválida: ";
    public static final String CSV_IMPORT_ERROR = "Erro ao importar CSV: ";
    public static final String ERRO_INTERNO = "Erro interno do servidor: ";
    public static final String USUARIO_NAO_ENCONTRADO = "Usuário não encontrado";

    public static final String VALOR_CONTA_DEVE_SER_MAIOR_QUE_ZERO = "Valor da conta deve ser maior que zero";
    public static final String VALOR_VENCIMENTO_NAO_PODE_SER_DO_PASSADO = "Data de vencimento não pode ser no passado";
    public static final String CONTA_ESTA_PENDENTE_OU_CANCELADA = "A conta está em situação 'PENDENTE' ou está 'CANCELADA'.";
    public static final String CONTA_INVALIDA_PARA_PAGAMENTO = "Conta inválida para pagamento";
    public static final String ERRO_PARA_RECUPERAR_CONTA = "Ocorreu um erro ao buscar a conta";

    public static final String ERRO_AO_LER_ARQUIVO_CSV = "Erro ao ler arquivo CSV";
}
