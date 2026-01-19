package com.devops.cicd;

public final class PricingService {

    private final PricingConfig config;

    public PricingService(PricingConfig config) {
        if (config == null) {
            throw new IllegalArgumentException("config must not be null");
        }
        this.config = config;
    }

    public double applyVat(double amountExclVat) {
        return amountExclVat * (1.0 + config.getVatRate() / 100.0);
    }

    public double applyVipDiscount(double amount, boolean vip) {
        return vip ? amount * 0.9 : amount;
    }

    public double shippingCost(double amount) {
        return amount >= config.getFreeShippingThreshold() ? 0.0 : 4.99;
    }

    /**
     * Règle choisie (cohérente avec le README) :
     * - TVA appliquée d'abord : HT -> TTC
     * - remise VIP appliquée sur TTC
     * - frais de livraison ajoutés ensuite (calculés sur TTC)
     */
    public double finalTotal(double amountExclVat, boolean vip) {
        double withVat = applyVat(amountExclVat);
        double afterDiscount = applyVipDiscount(withVat, vip);
        return afterDiscount + shippingCost(withVat);
    }
}
