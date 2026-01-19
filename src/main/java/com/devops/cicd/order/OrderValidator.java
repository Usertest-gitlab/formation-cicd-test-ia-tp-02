package com.devops.cicd.order;

/**
 * Classe utilitaire de validation des commandes.
 *
 * Règles de validation :
 * - la commande ne doit pas être null
 * - id ne doit pas être null ni vide
 * - quantity doit être strictement positive
 * - unitPrice doit être strictement positif
 *
 * En cas d’erreur, lever IllegalArgumentException.
 *
 * TODO :
 * - implémenter la méthode validate
 */
public final class OrderValidator {

    private OrderValidator() {
        // classe utilitaire
    }

    public static void validate(Order order) {
        // TODO: implémenter les règles de validation
    }
}
