# Cas de test — User / UserService

## 1. Email

### 1.1 Cas nominaux

| ID  | Description | Email entrée | Email stocké | Résultat attendu |
|-----|------------|-------------|--------------|------------------|
| E1  | Email valide simple | john@doe.com | john@doe.com | Utilisateur créé |
| E2  | Email valide avec espaces | `  john@doe.com  ` | john@doe.com | Utilisateur créé |
| E3  | Email minimal valide | a@b.c | a@b.c | Utilisateur créé |

---

### 1.2 Cas d’erreur

| ID  | Description | Email entrée | Exception attendue |
|-----|------------|-------------|--------------------|
| E4  | Email null | null | IllegalArgumentException — "email must be valid" |
| E5  | Email vide | "" | IllegalArgumentException — "email must be valid" |
| E6  | Email espaces | "   " | IllegalArgumentException — "email must be valid" |
| E7  | Pas de caractère @ | john.doe.com | IllegalArgumentException — "email must be valid" |
| E8  | Plusieurs caractères @ | john@@doe.com | IllegalArgumentException — "email must be valid" |
| E9  | Pas de . après @ | john@doecom | IllegalArgumentException — "email must be valid" |
| E10 | @ en fin d’email | john@ | IllegalArgumentException — "email must be valid" |
| E11 | @ en début d’email | @doe.com | IllegalArgumentException — "email must be valid" |

---

## 2. Password

### 2.1 Cas nominaux

| ID  | Description | Password entrée | Résultat attendu |
|-----|------------|----------------|------------------|
| P1  | Password fort valide | Abcdef1! | Utilisateur créé |
| P2  | Password fort avec espaces | Abcd ef1! | Utilisateur créé |
| P3  | Longueur minimale valide (8 caractères) | A1bcdef! | Utilisateur créé |

---

### 2.2 Cas d’erreur — règles de robustesse

| ID  | Description | Password entrée | Exception attendue |
|-----|------------|----------------|--------------------|
| P4  | Longueur < 8 | Ab1!a | IllegalArgumentException — "password must be strong" |
| P5  | Absence de majuscule | abcdef1! | IllegalArgumentException — "password must be strong" |
| P6  | Absence de minuscule | ABCDEF1! | IllegalArgumentException — "password must be strong" |
| P7  | Absence de chiffre | Abcdefg! | IllegalArgumentException — "password must be strong" |
| P8  | Absence de caractère spécial | Abcdef12 | IllegalArgumentException — "password must be strong" |

---

### 2.3 Cas d’erreur — généraux

| ID  | Description | Password entrée | Exception attendue |
|-----|------------|----------------|--------------------|
| P9  | Password null | null | IllegalArgumentException — "password must be strong" |
| P10 | Password vide | "" | IllegalArgumentException — "password must be strong" |
| P11 | Password espaces | "   " | IllegalArgumentException — "password must be strong" |

---

## 3. Role

### 3.1 Cas nominaux

| ID  | Description | Role | Résultat attendu |
|-----|------------|------|------------------|
| R1  | Rôle USER | USER | Utilisateur créé |
| R2  | Rôle ADMIN | ADMIN | Utilisateur créé |

---

### 3.2 Cas d’erreur

| ID  | Description | Role | Exception attendue |
|-----|------------|------|--------------------|
| R3  | Rôle null | null | IllegalArgumentException — "role must not be null" |

---

## 4. Accès à la zone admin

### 4.1 Méthode canAccessAdminArea()

| ID  | Description | Role | Résultat attendu |
|-----|------------|------|------------------|
| A1  | Accès admin refusé | USER | false |
| A2  | Accès admin autorisé | ADMIN | true |

---

## 5. UserService.register()

### 5.1 Cas nominaux

| ID  | Description | Email | Password | Role | Résultat attendu |
|-----|------------|-------|----------|------|------------------|
| U1  | Création utilisateur standard | john@doe.com | Abcdef1! | USER | User créé |
| U2  | Création administrateur | admin@site.com | Admin123! | ADMIN | User créé |

---

### 5.2 Propagation des erreurs

| ID  | Description | Entrée invalide | Exception attendue |
|-----|------------|---------------|--------------------|
| U3  | Email invalide | john.doe.com | IllegalArgumentException — "email must be valid" |
| U4  | Password invalide | abcdef | IllegalArgumentException — "password must be strong" |
| U5  | Rôle null | null | IllegalArgumentException — "role must not be null" |

---

## 6. Intégrité des données

| ID  | Description | Vérification attendue |
|-----|------------|----------------------|
| D1  | Email trimé avant stockage | `user.getEmail()` ne contient pas d’espaces |
| D2  | Password inchangé | Password stocké == password fourni |
| D3  | Rôle stocké tel quel | `user.getRole()` == rôle fourni |

---

## Notes
- Chaque règle métier est couverte par au moins un test
- Les messages d’exception doivent être **strictement identiques**
- Les exceptions sont propagées sans interception par `UserService`

