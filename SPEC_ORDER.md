# Spécification fonctionnelle — Gestion des commandes (Order)

## Objectif

Cette spécification décrit le comportement attendu pour la gestion d’une commande
dans une application Java.

Elle peut être utilisée pour :
- générer automatiquement des tests unitaires et fonctionnels (avec l’aide de l’IA),
- analyser la qualité des tests existants,
- pratiquer une approche TDD assistée par IA.

---

## 1. Modèle de données

Une commande est représentée par une classe `Order` contenant les champs suivants :

- `id` : String
- `quantity` : int
- `unitPrice` : double
- `priority` : boolean

---

## 2. Règles de validation (OrderValidator)

Avant tout calcul ou traitement, une commande doit être validée.

### 2.1 Règles générales

- La commande ne doit pas être `null`
- L’identifiant (`id`) :
    - est obligatoire (non null)
    - ne doit pas être vide ou blanc après trim

- La quantité (`quantity`) :
    - doit être strictement supérieure à 0

- Le prix unitaire (`unitPrice`) :
    - doit être strictement supérieur à 0

### 2.2 Gestion des erreurs

En cas de non-respect des règles :
- une `IllegalArgumentException` doit être levée
- le message doit être explicite et cohérent (exemples) :
    - `"order must not be null"`
    - `"id must not be blank"`
    - `"quantity must be > 0"`
    - `"unitPrice must be > 0"`

---

## 3. Service de calcul (OrderService)

La classe `OrderService` expose la méthode suivante :

```java
double computeTotal(Order order)
```

# Spécification fonctionnelle — Gestion des commandes (Order)

## 🎯 Objectif

Cette spécification décrit le comportement attendu pour la gestion d’une commande
dans une application Java.

Elle peut être utilisée pour :
- générer automatiquement des tests unitaires et fonctionnels (avec l’aide de l’IA),
- analyser la qualité des tests existants,
- pratiquer une approche TDD assistée par IA.

---

## 1. Modèle de données

Une commande est représentée par une classe `Order` contenant les champs suivants :

- `id` : String
- `quantity` : int
- `unitPrice` : double
- `priority` : boolean

---

## 2. Règles de validation (OrderValidator)

Avant tout calcul ou traitement, une commande doit être validée.

### 2.1 Règles générales

- La commande ne doit pas être `null`
- L’identifiant (`id`) :
    - est obligatoire (non null)
    - ne doit pas être vide ou blanc après trim

- La quantité (`quantity`) :
    - doit être strictement supérieure à 0

- Le prix unitaire (`unitPrice`) :
    - doit être strictement supérieur à 0

### 2.2 Gestion des erreurs

En cas de non-respect des règles :
- une `IllegalArgumentException` doit être levée
- le message doit être explicite et cohérent (exemples) :
    - `"order must not be null"`
    - `"id must not be blank"`
    - `"quantity must be > 0"`
    - `"unitPrice must be > 0"`

---

## 3. Service de calcul (OrderService)

La classe `OrderService` expose la méthode suivante :

```java
double computeTotal(Order order)
```

---

## 4. Règles métier de calcul
### 4.1 Calcul du sous-total
- subtotal = quantity × unitPrice

### 4.2 Remise sur montant élevé
- Si subtotal >= 100 :
    - une remise de 5 % est appliquée
- Sinon :
  - aucune remise

La remise est appliquée avant les éventuels frais de priorité.

### 4.3 Frais de priorité
- Si priority == true :
    - des frais fixes de 9.99 sont ajoutés
- Sinon :
    - aucun frais supplémentaire

### 4.4 Arrondi
- Le montant final doit être arrondi à 2 décimales
- L’arrondi est effectué à la fin du calcul

### 5. Ordre des opérations (important)

L’ordre exact de calcul est le suivant :
1. Validation de la commande
2. Calcul du sous-total
3. Application éventuelle de la remise
4. Ajout éventuel des frais de priorité
5. Arrondi à 2 décimales
6. Retour du montant final

### 6. Comportements attendus
Exemples indicatifs

* Une commande simple sans remise ni priorité retourne quantity × unitPrice
* Une commande avec remise applique 5 % de réduction
* Une commande prioritaire ajoute toujours 9.99, même si une remise est appliquée
* Une commande invalide provoque une exception avant tout calcul

7. Tests attendus

À partir de cette spécification, vous pouvez produire :

Tests unitaires

* validation des règles (OrderValidator)
* calcul du total (OrderService)
* cas limites (seuils, arrondi)

Tests fonctionnels légers

* scénarios complets sur computeTotal(...)
* validation du comportement métier global

Aucun test HTTP, UI ou base de données n’est attendu.
